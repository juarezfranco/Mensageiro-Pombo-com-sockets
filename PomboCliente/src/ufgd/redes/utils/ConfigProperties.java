/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Classe responsável por salvar externamente informações de configuração do sistema
 * @author franco
 */
public class ConfigProperties {
    private static final String CAMINHO_PROPERTIES = "config.properties";
    private static final String ENDERECO_DEFAULT_SERVIDOR = "127.0.0.1";
    private static final String PORTA_DEFAULT_SERVIDOR = "12345";
    Properties prop;
    
    
    public ConfigProperties (){
        prop = new Properties();
    }
    
    private void criarProperties() throws FileNotFoundException, IOException{
        FileOutputStream output  = new FileOutputStream(CAMINHO_PROPERTIES);
        prop.setProperty("SERVIDOR", ENDERECO_DEFAULT_SERVIDOR);
        prop.setProperty("PORTA", PORTA_DEFAULT_SERVIDOR);
        prop.store(output, null);
        output.close();
    }
    
    public Properties getProperties() throws FileNotFoundException, IOException{
        if(! new File(CAMINHO_PROPERTIES).exists() ){
            criarProperties();
        }else{
            FileInputStream input = new FileInputStream(CAMINHO_PROPERTIES);
            prop.load(input);
        }
        return prop;
    }
}
