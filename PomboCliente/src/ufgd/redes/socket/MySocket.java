package ufgd.redes.socket;

import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author franco
 */
public class MySocket extends Socket{
    
    private final PrintStream sender;
    private final Scanner receive;
    
    /**
     * Construtor do socket
     * @param servidor
     * @param porta
     * @throws IOException 
     */
    public MySocket(String servidor, int porta) throws IOException{
        super( servidor, porta);
        //inicializa PrintStream responsável por enviar mensagens através do socket
        sender = new PrintStream(this.getOutputStream());
        //inicializa Scanner responsável por receber mensagens
        receive= new Scanner(this.getInputStream());
        
    }
    
    /**
     * Método responsável por enviar mensagem ao servidor,
     * envia um JSON de um bundle.
     * @param message
     * 
     */
    public void sendToServer(String message){
        //envia mensagem para servidor
        sender.println(message);
    }
    /**
     * Método responsável por receber ultima mensagem do servidor.
     * @return 
     */
    public String receiveToServer(){
        while(receive.hasNextLine())
            return receive.nextLine();
        return null;
    }  
    
    @Override
    public void close() throws IOException{
        if(sender!=null)
            sender.close();//finaliza PrintStream
        super.close();
    }
    
}
