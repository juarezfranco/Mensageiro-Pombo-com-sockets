/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes.utils;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe responsável por conter funções do projeto
 * 
 * @author Juarez A. Franco Jr
 */
public class Util {
    
    
    public static Image getIconeJanela(){
        // coloca uma figura na barra de título da janela  
        URL url = Util.class.getClass().getResource("/ufgd/redes/utils/imagens/icone.png");  
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);  
        return imagemTitulo;
    }
    /**
     * Função responsável de enviar notificação ao sistema operacional para ser exibida na tela
     * 
     * @param imagemName
     * @param titulo
     * @param conteudo 
     */
    public static void exibirNotificacao(String imagemName, String titulo, String conteudo){
        String so = System.getProperty("os.name").toLowerCase();
        String pacote = "/ufgd/redes/utils/imagens/profiles/";
        try {
            String destinoImagem=copiarArquivoParaPastaTemp(pacote,imagemName);
            //notificação para linux
            if(so.contains("linux")){
                //formar o comando que será enviado ao terminal do sistema operacional
                String[] cmd = {"notify-send",//nome do programa externo que exibe notificação
                    "-i",//ativar imagem
                    destinoImagem,//caminho da imagem
                    titulo,
                    conteudo};
                //enviar o comando para o sistema
                Runtime.getRuntime().exec(cmd);
            }
        } catch (IOException ex) {
            System.err.println("Falha ao exibir notificação de mensagem");
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Função responsável por copiar um arquivo interno do pacote do projeto para a pasta temporária do sistema operacional
     * @param arquivo
     * @throws FileNotFoundException
     * @throws IOException 
     * @return String destino
     */
    private static String copiarArquivoParaPastaTemp(String pacote, String nomeArquivo) throws FileNotFoundException, IOException{
        //nome de origem da imagem relativo ao seu pacote
        String origem = pacote+nomeArquivo;
        //destino onde a imagem será colada, para a pasta tmp
        String destino = System.getProperty("java.io.tmpdir") + "/" + nomeArquivo;
        //recupera referencia da imagem que esta no pacote
        InputStream in = Util.class.getClass().getClass().getResourceAsStream(origem);
        //output responśavel por salvar imagem no destino que foi passado por parametro
        OutputStream out = new FileOutputStream(destino);
        //instancia vetor de bytes do tamanho de bytes da imagem
        byte[] buffer = new byte[in.available()];
        //transfere imagem para o buffer em memória
        in.read(buffer);
        //escreve imagem em memória para o destino
        out.write(buffer);
        //fecha arquivos
        if(in!=null)
            in.close();
        if(out!=null)
            out.close();
        //retorna caminho absoluto do destino da imagem
        return destino;
    }
}
