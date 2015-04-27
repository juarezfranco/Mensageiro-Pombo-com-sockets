package ufgd.redes.socket;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import ufgd.redes.models.Usuario;
import java.lang.reflect.Type;
import static ufgd.redes.util.Constantes.OK;
import static ufgd.redes.util.Constantes.SEM_AUTORIZACAO;

/**
 *
 * @author franco
 */
public class MySocket extends Socket{
    private static final String SERVIDOR = "127.0.0.1";
    private static final int PORTA = 12345;
    
    private PrintStream sender;
    private Scanner receive;
    
    /**
     * Construtor do socket
     * @throws IOException 
     */
    public MySocket() throws IOException{
        super(SERVIDOR, PORTA);
        //inicializa PrintStream responsável por enviar mensagens através do socket
        sender = new PrintStream(this.getOutputStream());
        //inicializa Scanner responsável por receber mensagens
        receive= new Scanner(this.getInputStream());
        
    }
    
    /**
     * Recupera lista de usuarios do sistema
     * @return 
     */
    public List<Usuario> getListaUsuarios(){
        //cria corpo pedido
        Map<String, String> bodyMessage = new HashMap();
        bodyMessage.put("tipo", "list_all_users");
        //envia pedido ao servidor
        sendToServer(new Gson().toJson(bodyMessage));
        //recebe pedido do servidor
        String jsonResponse = receiveToServer();
        //converte json para List
        Type tipo = new TypeToken<List<Usuario>>(){}.getType();
        return new Gson().fromJson(jsonResponse, tipo);
    }
    /**
     * Método responsável por enviar mensagem ao servidor,
     * envia um JSON de um bundle.
     * @param message
     */
    public void sendToServer(String message){
        //envia mensagem para servidor
        sender.println(message);
    }
    /**
     * Método responsável por receber mensagem do servidor.
     * @return 
     */
    public String receiveToServer(){
        receive.hasNextLine();
        return receive.nextLine();
    }
    
    
    /**
     * Método responsável por autenticar login do usuario
     * @param username
     * @param password
     * @return 
     */
    public boolean autenticarUsuario(String username, String password){
        //cria corpo da mensagem
        Map<String, String> bodyMessage = new HashMap();
        //escolhe tipo da solicitação
        bodyMessage.put("tipo","auth");
        //insere username e password no corpo da mensagem
        bodyMessage.put("username", username);
        bodyMessage.put("password", password);
        //envia mensagem para o servidor
        sendToServer(new Gson().toJson(bodyMessage));
        //recebe resposta do servidor
        String resposta = receiveToServer();
        if(resposta.equals(SEM_AUTORIZACAO))
            return false;
        else
            return true;
    }
    /**
     * Método responsável por solicitar ao servidor para criar conta de um usuario
     * @param username
     * @param password
     * @return 
     */
    public boolean criarConta(String username, String password){
        //cria corpo da mensagem
        Map<String, String> bodyMessage = new HashMap();
        //escolhe tipo da solicitação
        bodyMessage.put("tipo","new_account");
        bodyMessage.put("username", username);
        bodyMessage.put("password", password);
        //envia mensagem
        sendToServer(new Gson().toJson(bodyMessage));
        //recebe resposta se foi criado ou nao a conta
        String resposta = receiveToServer();
        if(resposta.equals(OK))
            return true;
        else
            return false;
    }
    
    @Override
    public void close() throws IOException{
        if(sender!=null)
            sender.close();//finaliza PrintStream
        super.close();
    }
    
}
