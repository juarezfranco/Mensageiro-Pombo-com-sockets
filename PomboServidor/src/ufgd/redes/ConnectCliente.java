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
import static ufgd.redes.utils.Constantes.FOUND;
import static ufgd.redes.utils.Constantes.OK;
import static ufgd.redes.utils.Constantes.SEM_AUTORIZACAO;

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
     * Lista de usuarios online no sistemas.
     */
    Map<String, PrintStream> usuariosOnline;

    /**
     * Construtor
     * @param socketCliente
     * @param usuariosOnline
     * @throws IOException 
     */
    public ConnectCliente(Socket socketCliente, Map<String, PrintStream> usuariosOnline) throws IOException {
        this.socketCliente = socketCliente;
        receive = new Scanner(socketCliente.getInputStream());
        sender = new PrintStream(socketCliente.getOutputStream());
        this.usuariosOnline = usuariosOnline;
    }
    
    
    /**
     * Função executada ao iniciar Thread.
     */
    @Override
    public void run() {
        System.out.println("#conexão ESTABELECIDA com o cliente " + socketCliente.getInetAddress().getHostAddress() + " #");
        try {
            /** 1º PASSO: espera usuario se logar **/
            while (receive.hasNextLine()) {
                if(initAutenticacao())
                    break;
            }
            /** 2º PASSO:  envia lista de usuarios**/
            while (receive.hasNextLine()) {
                enviarListaUsuarios();
            }
            /** 3º PASSO: inicia a comunicação de troca de mensagens do bate papo **/
            while (receive.hasNextLine()) {
                initComunicacao();
            }
        } catch (IOException ex) {
            Logger.getLogger(PomboServidor.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            try {
                encerraConexao();
            } catch (IOException ex) {
                Logger.getLogger(ConnectCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("#Conexão ENCERRADA com o cliente " + socketCliente.getInetAddress().getHostAddress() + " #");
    }

    /**
     * Envia lista de todos os usuarios para o cliente que solicitou.
     */
    private void enviarListaUsuarios() {
        String json = receive.nextLine();
        Map<String, String> dados = new Gson().fromJson(json, Map.class);
        String tipo = dados.get("tipo");
        if(tipo.equals("list_all_users")){
            List<Usuario> usuarios = new DAOUsuario().listAll(usuario.getUsername());
            //altera quem está online
            for(Usuario u : usuarios){
                if(usuariosOnline.containsKey(u.getUsername()))
                    u.setAtivo(true);
            }
            sender.println(new Gson().toJson(usuarios));
        }
    }

    /**
     * Valida comunicação inicial entre cliente e servidor
     * verifica se usuario está fazendo login ou criando nova conta,
     * Retonra true apenas quando usuario consegue fazer login
     * 
     * @return 
     */
    private boolean initAutenticacao(){
        boolean result = false;
        //recebe json enviado pelo usuario
        String json = receive.nextLine();
        //converte json
        Map<String, String> dados = new Gson().fromJson(json, Map.class);
        //recupera tipo de ação que usuario deseja realizar ("auth": fazer login/ "new_account": criar nova conta)
        String tipo = dados.get("tipo");
        String username = dados.get("username");//recupera nome de usuario
        String password = dados.get("password");//recupera senha
        //vericia se usuario deseja fazer login
        if (tipo.equals("auth")) {
            if (autenticarUsuario(username, password)) {
                //enviar lista de usuarios do sistema
                enviarListaUsuarios();
                result = true;
            }
        }
        //verifica se usuario deseja criar nova conta
        if (tipo.equals("new_account")) {
            //apenas cria conta do usuario, não sai do laço while
            criarConta(username, password);
        }
        return result;
    } 
    
    /**
     * Inicia loop de comunicação infinita com cliente, ou até cliente
     * encerrar comunicação.
     * @throws IOException 
     */
    private void initComunicacao() throws IOException {

        //le json enviado pelo remetente
        String json = receive.nextLine();
        //recupera mensagens de dados do remetente, que contem usuario destinatario e mensagem.
        Message message = new Gson().fromJson(json, Message.class);
        message.setRemetente(usuario.getUsername());
        //recuperara destinatario da mensagem
        String destinatario = message.getDestinatario();
        //recupera printStream do destinatario para enviar a mensagem
        PrintStream senderDestinatario = getPrintStreamDestinatario(destinatario);
        //se sender estiver online envia mensagem
        if (senderDestinatario != null) {
            //envia mensagem ao destinatario
            senderDestinatario.println(json);
        } //se nao estiver online entao salve a mensagem para enviar quando estiver online
        else {
            //...
        }
    }

    /**
     * Método responsável por autenticar usuario.
     */
    private boolean autenticarUsuario(String username, String password) {
        usuario = new Usuario();
        usuario.setUsername(username);
        usuario.setPassword(password);
        DAOUsuario dao = new DAOUsuario();
        //Autentica usuario com dados do banco de dados
        if (dao.autentica(username, password)) {
            //envia resposta para usuario dizendo que está autorizado
            sender.println(OK);
            //adiciona usuario para lista de usuarios
            usuariosOnline.put(usuario.getUsername(), sender);
            return true;
        } else {
            //envia resposta para usuario dizendo que está sem autorização para login
            sender.println(SEM_AUTORIZACAO);
            return false;
        }
    }

    /**
     * Metodo responsável por criar nova conta de usuario.
     *
     * @param username
     * @param password
     */
    private void criarConta(String username, String password) {
        DAOUsuario dao = new DAOUsuario();
        //verifica se usuario ja existe
        if (!dao.existe(username)) {
            //se não existir cadastra usuario
            dao.inserir(new Usuario(username, password));
            //envia resposta ao usuario
            sender.println(OK);
        } else {
            //envia resposta ao usuario
            sender.println(FOUND);
        }

    }

    /**
     * Retorna printStream do usuario online solicitado pelo username, se nao
     * estiver online, retorna null
     *
     * @param username
     * @return
     */
    private PrintStream getPrintStreamDestinatario(String username) {
        //verifica se usuario esta online
        if (usuariosOnline.containsKey(username)) {
            //retorna printStream do usuario destinatario
            return usuariosOnline.get(username);
        }
        return null;
    }
    
    /**
     * Finaliza objetos responsáveis pela comunicação entre cliente e servidor
     * @throws IOException 
     */
    public void encerraConexao() throws IOException{
        //FINALIZA CONEXÃO
        //remove usuario da lista de usuarios online
        if(usuario!=null)//verifica se chegou a ser instanciado o objeto do usuario
            if(usuariosOnline.containsKey(usuario.getUsername()))//verifica se usuario chegou a completar login
                usuariosOnline.remove(usuario.getUsername());//remove usuario da lista de usuarios online
        //fechar Scanner
        receive.close();
        //fecha PrintStram
        sender.close();
        //fecha socket do usuario
        socketCliente.close();
    }
}
