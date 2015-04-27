package ufgd.redes;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

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
    private static Map<String, PrintStream> usuariosOnline;
    
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
    
    
    
    
    
}
