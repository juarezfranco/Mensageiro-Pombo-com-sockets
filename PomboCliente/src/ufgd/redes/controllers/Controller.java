package ufgd.redes.controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;
import ufgd.redes.models.Message;
import ufgd.redes.models.Usuario;
import ufgd.redes.socket.MySocket;
import ufgd.redes.socket.ReceiveMessage;
import ufgd.redes.utils.ConfigProperties;
import static ufgd.redes.utils.Constantes.ACAO;
import static ufgd.redes.utils.Constantes.NOVA_MENSAGEM;
import static ufgd.redes.utils.Constantes.AUTH;
import static ufgd.redes.utils.Constantes.DIGITANDO;
import static ufgd.redes.utils.Constantes.ENCERRAR_SESSAO;
import static ufgd.redes.utils.Constantes.LISTA_CONTATOS;
import static ufgd.redes.utils.Constantes.NOT_DIGITANDO;
import static ufgd.redes.utils.Constantes.NOVA_CONTA;
import static ufgd.redes.utils.Constantes.OK;
import static ufgd.redes.utils.Constantes.UPDATE_PERFIL;
import ufgd.redes.views.JanelaMain;

/**
 *
 * @author franco
 */
public class Controller {
    private Usuario usuario;
    private MySocket socket;
    private JanelaMain contexto;
    
    public Controller() throws IOException{
        Properties properties = new ConfigProperties().getProperties();
        String servidor = properties.getProperty("SERVIDOR");
        int porta = Integer.parseInt(properties.getProperty("PORTA"));
        socket = new MySocket(servidor,porta);//cria socket
        usuario = new Usuario();
    }
    public void setContexto(JanelaMain contexto){
        
        this.contexto = contexto;
    }
    /**
     * Método resposável por gerenciar as novas mensagens que chegam
     * @param message 
     */
    public void gerenciarNovaMensagem(Message message) {
        String tipo = message.getTipo();
        switch(tipo){
            case NOVA_MENSAGEM:
                novaMensagem(message);
                break;
            case LISTA_CONTATOS:
                listaContatos(message);
                break;
            case UPDATE_PERFIL:
                atualizarPerfilContato(message);
                break;
            case ENCERRAR_SESSAO:
                encerrarAplicacao();
                break;
            case ACAO:
                novaAcao(message);
                break;
                
        }
        
    }
    private void novaAcao(Message message){
        if(message.getMsg().equals(DIGITANDO)){
            contexto.atualizarAcao(message);
        }
            
    }
    
    public void digitando(Usuario destinatario, boolean youare) {
        Message message = new Message();
        message.setTipo(ACAO);
        message.setDestinatario(destinatario);
        if(youare)
            message.setMsg(DIGITANDO);
        else
            message.setMsg(NOT_DIGITANDO);
        socket.sendToServer(message.toJson());
    }
    private void atualizarPerfilContato(Message message){
        contexto.atualizarPerfilContato(message.getRemetente());
    }
    private void encerrarAplicacao(){
        
        contexto.encerrarAplicaAplicacao();
    }
    
    private void listaContatos(Message message){
        //converte mensagem para lista de usuarios
        Type tipo = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> contatos = new Gson().fromJson(message.getMsg(), tipo);
        contexto.preencherListaContatos(contatos);
    }
    
   
    private void novaMensagem(Message message){
        Usuario remetente = message.getRemetente();
        String msg = message.getMsg();
        contexto.exibirNovaMensageRecebida(remetente, msg);  
    }
    
    public void iniciarThreadReceiveMessage(){
        //cria thread que recebe mensagens
        ReceiveMessage receive = new ReceiveMessage(this);
        new Thread(receive).start();
    }
    
    
    /**
     * Solicita ao servidor para verficar autenticidade do login
     * @param username
     * @param password
     * @return 
     */
    public boolean autenticarUsuario(String username, String password){
        //instancia mensagem que sera envida ao servidro
        Message message = new Message();
        //seta tipo da mensagem
        message.setTipo(AUTH);
        //inicia campos do usuario 
        usuario.setUsername(username);
        usuario.setPassword(password);
        //adiciona usuario como remetente
        message.setRemetente(usuario);
        //transforma mensagem em JSON e envia ao servidor
        socket.sendToServer(message.toJson());
        //recebe resposta do servidor
        String resposta = socket.receiveToServer();
        message = new Gson().fromJson(resposta, Message.class);
        if(message.getMsg().equals(OK)){
            usuario = message.getDestinatario();
            return true;
        }
        return false;
    }
    public void updateUsuario(){
        Message message = new Message();
        message.setTipo(UPDATE_PERFIL);
        message.setRemetente(usuario);
        socket.sendToServer(message.toJson());
        
    }
    /**
     * Solicita ao servidor para criar nova conta de usuario se possível
     * @param username
     * @param password
     * @param image
     * @return 
     */
    public boolean criarConta(String username, String password, String image){
        Message message = new Message();
        message.setTipo(NOVA_CONTA);
        message.setRemetente(new Usuario(username,password,image));
        //transforma mensagem em JSON e envia ao servidor
        socket.sendToServer(message.toJson());
        //recebe resposta do servidor
        String resposta = socket.receiveToServer();
        return resposta.equals(OK);
    }
    
    /**
     * Envia uma mensagem para o servidor
     * @param destinatario
     * @param msg 
     */
    public void enviarMensagem(Usuario destinatario, String msg){
        Message message = new Message();
        message.setTipo(NOVA_MENSAGEM);
        message.setDestinatario(destinatario);
        message.setMsg(msg);
        socket.sendToServer(message.toJson());
        
    }
    
    /**
     * Encerra socket
     * @throws IOException 
     */
    public void close() throws IOException{
        if(socket!=null){
            socket.close();
            socket=null;
        }
    }
    
    
    
    // GETTERS AND SETTERS.
    
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public MySocket getSocket() {
        return socket;
    }

    public void setSocket(MySocket socket) {
        this.socket = socket;
    }   
}
