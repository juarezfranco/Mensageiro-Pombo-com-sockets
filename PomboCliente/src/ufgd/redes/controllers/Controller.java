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
import static ufgd.redes.utils.Constantes.NOVA_MENSAGEM;
import static ufgd.redes.utils.Constantes.USER_OFFLINE;
import static ufgd.redes.utils.Constantes.USER_ONLINE;
import static ufgd.redes.utils.Constantes.AUTH;
import static ufgd.redes.utils.Constantes.LISTA_CONTATOS;
import static ufgd.redes.utils.Constantes.NOVA_CONTA;
import static ufgd.redes.utils.Constantes.OK;
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
    
    public void gerenciarNovaMensagem(Message message){
        String tipo = message.getTipo();
        switch(tipo){
            case NOVA_MENSAGEM:
                novaMensagem(message);
                break;
            case USER_ONLINE:
                novoUserOnline(message);
                break;
            case USER_OFFLINE:
                novoUserOffline(message);
                break;
            case LISTA_CONTATOS:
                listaContatos(message);
                break;
                
        }
        
    }
    private void listaContatos(Message message){
        //converte mensagem para lista de usuarios
        Type tipo = new TypeToken<List<Usuario>>(){}.getType();
        List<Usuario> contatos = new Gson().fromJson(message.getMsg(), tipo);
        contexto.preencherListaContatos(contatos);
    }
    
    private void novoUserOffline(Message message){
        Usuario contatoOffline = message.getRemetente();
        contexto.novoContatoOffline(contatoOffline);
    }
    
    private void novoUserOnline(Message message){
        Usuario contatoOnline = message.getRemetente();
        contexto.novoContatoOnline(contatoOnline);
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
        //retorna boolean indicando se está autorizado ou nao
        return resposta.equals(OK);
    }
    
    /**
     * Solicita ao servidor para criar nova conta de usuario se possível
     * @param username
     * @param password
     * @param image
     * @return 
     */
    public boolean criarConta(String username, String password, int image){
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
        if(socket!=null)
            socket.close();
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
