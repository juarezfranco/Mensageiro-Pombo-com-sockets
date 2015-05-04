package ufgd.redes;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import ufgd.redes.models.Message;

/**
 *
 * @author franco
 */
public class PomboServidor {
    /**
     * Porta que o servidor deve escutar
     */
    private static final int PORTA = 12345;
    
    /**
     * Lista de usuarios onlines
     * Chave: username do usuario, Valor: PrintStream.
     */
    public static Map<String, PrintStream> usuariosOnline;
    
    
    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        
        
        ServerSocket servidor = new ServerSocket(PORTA);
        System.out.println("# Servidor incializado escutando porta "+PORTA+" #");
        usuariosOnline = new HashMap();
        
        while(true){
            //espera novo cliente se conectar
            Socket cliente =  servidor.accept();
            //cria thread para novo cliente
            new Thread(new ConnectCliente(cliente, usuariosOnline)).start();   
        }
        
    }
    
    /**
     * Função respnsável por enviar mensagem para todos os usuarios online
     * @param message
     */
    public static void distribuirMensagem(Message message){
        
        for(Entry<String,PrintStream>entry : usuariosOnline.entrySet()){
            if(!message.getRemetente().getUsername().equals(entry.getKey())){
                PrintStream print = entry.getValue();
                print.println(message.toJson());
            }
        }
    }    
}
