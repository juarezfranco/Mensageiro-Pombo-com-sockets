package ufgd.redes.controllers;

import java.io.IOException;
import java.util.List;
import ufgd.redes.models.Message;
import ufgd.redes.models.Usuario;
import ufgd.redes.socket.MySocket;
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
        usuario = new Usuario();
        socket = new MySocket();//cria socket
    }
    public void setContexto(JanelaMain contexto){
        this.contexto = contexto;
    }
    
    public void gerenciarNovaMensagem(Message message){
        String remetente = message.getRemetente();
        String msg = message.getMsg();
        contexto.showReceiveMessage(remetente, msg);
        
    }
    
    /**
     * Solicita ao Servidor todos os usuarios do sistemas
     * @return 
     */
    public List<Usuario> getListaUsuarios(){
        return socket.getListaUsuarios();
    }
    /**
     * Solicita ao servidor para verficar autenticidade do login
     * @param username
     * @param password
     * @return 
     */
    public boolean autenticarUsuario(String username, String password){
        usuario.setUsername(username);
        usuario.setPassword(password);
        return socket.autenticarUsuario(username, password);
    }
    
    /**
     * Solicita ao servidor para criar nova conta de usuario se possível
     * @param username
     * @param password
     * @return 
     */
    public boolean criarConta(String username, String password){
        return socket.criarConta(username, password);
    }
    
    /**
     * Envia uma mensagem para o servidor
     * @param destinatario
     * @param mensagem 
     */
    public void enviarMensagem(Usuario destinatario, String mensagem){
        Message message = new Message(destinatario.getUsername(), mensagem);
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
    
    
    
    /** GETTERS AND SETTERS. **/
    
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
