/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes;

import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ufgd.redes.dao.DAOUsuario;
import ufgd.redes.models.Message;
import ufgd.redes.models.Usuario;
import static ufgd.redes.utils.Constantes.AUTH;
import static ufgd.redes.utils.Constantes.FOUND;
import static ufgd.redes.utils.Constantes.LISTA_CONTATOS;
import static ufgd.redes.utils.Constantes.NOVA_CONTA;
import static ufgd.redes.utils.Constantes.NOVA_MENSAGEM;
import static ufgd.redes.utils.Constantes.OK;
import static ufgd.redes.utils.Constantes.SEM_AUTORIZACAO;
import static ufgd.redes.utils.Constantes.USER_OFFLINE;
import static ufgd.redes.utils.Constantes.USER_ONLINE;

/**
 *
 * Classe responsável por gerenciar conexão com um Socket do usuario, enviando e
 * recebendo mensagens do mesmo.
 *
 * @author franco
 */
public class ConnectCliente implements Runnable {

    /**
     * Socket que mantem conexão com o cliente.
     */
    Socket socketCliente;

    /**
     * Responsável por receber mensagens do cliente.
     */
    Scanner receive;

    /**
     * Responsável por enviar mensagens para o cliente.
     */
    PrintStream sender;

    /**
     * Cliente "dono" da thread.
     */
    Usuario usuario;

    /**
     * Status de autenticacao
     */
    boolean isAuth;
    

    /**
     * Construtor
     *
     * @param socketCliente
     * @param usuariosOnline
     * @throws IOException
     */
    public ConnectCliente(Socket socketCliente, Map<String, PrintStream> usuariosOnline) throws IOException {
        this.socketCliente = socketCliente;
        this.isAuth = false;
        this.receive = new Scanner(socketCliente.getInputStream());
        this.sender = new PrintStream(socketCliente.getOutputStream());
        PomboServidor.usuariosOnline = usuariosOnline;
    }

    /**
     * Função executada ao iniciar Thread.
     */
    @Override
    public void run() {
        System.out.println("#conexão ESTABELECIDA com o cliente " + socketCliente.getInetAddress().getHostAddress() + " #");

        while (receive.hasNextLine()) {
            novaComunicacao(receive.nextLine());
        }
        avisarOffline();
        
        try {
            encerraConexao();
        } catch (IOException ex) {
            Logger.getLogger(ConnectCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        System.out.println("#Conexão ENCERRADA com o cliente " + socketCliente.getInetAddress().getHostAddress() + " #");
    }
    
    /**
     * Inicia loop de comunicação "infinita" com cliente, ou até cliente encerrar
     * comunicação.
     *
     * @throws IOException
     */
    private void novaComunicacao(String json) {
        //recupera mensagens de dados do remetente.
        Message message = new Gson().fromJson(json, Message.class);
        //verifica que tipo de mensagem cliente enviou
        switch(message.getTipo()){
            case AUTH:
                autenticarUsuario(message);
                break;
            case LISTA_CONTATOS:
                if(isAuth) enviarListaUsuarios();
                break;
            case NOVA_CONTA:
                criarConta(message);
                break;
            case NOVA_MENSAGEM:
                if(isAuth) enviarMensagemParaDestinatario(message);
                break;
        }
        
    }
    private void autenticarUsuario(Message message){
        String username = message.getRemetente().getUsername().toLowerCase();
        String password = message.getRemetente().getPassword();
        
        DAOUsuario dao = new DAOUsuario();
        if(dao.autentica(username,password)){
            //envia resposta para usuario dizendo que está autorizado
            this.sender.println(OK);
            //flag de autorização para cliente enviar msg para outros
            this.isAuth=true;
            //salva usuario corrente
            this.usuario = message.getRemetente();
            this.usuario.setUsername(username);
            //limpa a senha deste usuario para não enviar senha a outros usuarios
            this.usuario.setPassword(null);
            //adiciona usuario para lista de usuarios online
            PomboServidor.usuariosOnline.put(usuario.getUsername(), sender);
            //envia lista de usuarios para cliente
            enviarListaUsuarios();
            avisarOnline();
        }else{
            //envia resposta para usuario dizendo que está sem autorização para login
            sender.println(SEM_AUTORIZACAO);
        }
    }
     
    /**
     * Método responsável por criar conta do usuario, só deve criar se username não existir
     * @param message 
     */
    private void criarConta(Message message){
        String username = message.getRemetente().getUsername().toLowerCase();
        String password = message.getRemetente().getPassword();
        DAOUsuario dao = new DAOUsuario();
        //verifica se usuario ja existe
        if (!dao.existe(username)) {
            //se não existir cadastra usuario
            dao.inserir(message.getRemetente());
            System.out.println(": Nova conta criada: USERNAME "+username);
            //envia resposta ao usuario
            sender.println(OK);
        } else {
            //envia resposta ao usuario
            sender.println(FOUND);
        }
    }            
    
    /**
     * Método responsável por avisar todos os usuarios online que este ficou offline.
     */
    private void avisarOffline(){
        if(usuario!=null){
            Message message = new Message();
            message.setTipo(USER_OFFLINE);
            message.setRemetente(usuario);
            String json = new Gson().toJson(message);
            PomboServidor.distribuirMensagem(json,usuario.getUsername());
        }
    }
    
    /**
     * Método responsável avisar todos os usuarios online que este ficou online.
     */
    private void avisarOnline(){
        Message message = new Message();
        message.setTipo(USER_ONLINE);
        message.setRemetente(usuario);
        String json = new Gson().toJson(message);
        PomboServidor.distribuirMensagem(json,usuario.getUsername());
    }
    
    /**
     * Método responsável por envia lista de todos os usuarios.
     */
    private void enviarListaUsuarios() {
        Message message = new Message();
        //seta tipo da mensagem
        message.setTipo(LISTA_CONTATOS);
        //recupera todos os usuario do banco de dados
        List<Usuario> usuarios = new DAOUsuario().listAll(usuario.getUsername());
        //altera quem está online
        for (Usuario u : usuarios) {
            if (PomboServidor.usuariosOnline.containsKey(u.getUsername())) {
                u.setAtivo(true);
            }
        }
        //Transforma lista de usuarios em JSON e adicona como corpo da Mensagem
        message.setMsg(new Gson().toJson(usuarios));
        //transforma mensagem em JSON e envia para cliente;
        sender.println(message.toJson());
        
    }

    

    /**
     * Método responsável por enviar mensagem para um destinatário
     * @param message 
     */
    private void enviarMensagemParaDestinatario(Message message){
        //adiciona remetente na mensagem
        message.setRemetente(usuario);
        System.out.println(": Nova mensagem transmitida: de '"+message.getRemetente().getUsername()+"' para '"+message.getDestinatario().getUsername()+"'");
        //recupera printStream do destinatario para enviar a mensagem
        PrintStream senderDestinatario = getPrintStreamDestinatario(message.getDestinatario());
        //se sender estiver online envia mensagem
        if (senderDestinatario != null) {
            //envia mensagem ao destinatario
            senderDestinatario.println(new Gson().toJson(message));
        } 
        //se nao estiver online entao salve a mensagem para enviar quando estiver online
        else {
            //...
        }
    }

    /**
     * Retorna printStream do usuario online solicitado pelo username, se nao
     * estiver online, retorna null
     *
     * @param username
     * @return
     */
    private PrintStream getPrintStreamDestinatario(Usuario destinatario) {
        //verifica se usuario esta online
        if (PomboServidor.usuariosOnline.containsKey(destinatario.getUsername())) {
            //retorna printStream do usuario destinatario
            return PomboServidor.usuariosOnline.get(destinatario.getUsername());
        }
        return null;
    }

    /**
     * Finaliza objetos responsáveis pela comunicação entre cliente e servidor
     *
     * @throws IOException
     */
    public void encerraConexao() throws IOException {
        //FINALIZA CONEXÃO
        //remove usuario da lista de usuarios online
        if (usuario != null)//verifica se chegou a ser instanciado o objeto do usuario
        {
            if (PomboServidor.usuariosOnline.containsKey(usuario.getUsername()))//verifica se usuario chegou a completar login
            {
                PomboServidor.usuariosOnline.remove(usuario.getUsername());//remove usuario da lista de usuarios online
            }        //fechar Scanner
        }
        if(receive!=null)
            receive.close();
        //fecha PrintStram
        if(sender!=null)
            sender.close();
        //fecha socket do usuario
        if(socketCliente!=null)
            socketCliente.close();
    }
}
