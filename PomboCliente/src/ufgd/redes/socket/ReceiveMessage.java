/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes.socket;

import com.google.gson.Gson;
import ufgd.redes.controllers.Controller;
import ufgd.redes.models.Message;

/**
 * Classe responsável por receber mensagens do servidor
 * @author franco
 */
public class ReceiveMessage implements Runnable{
    MySocket socket;
    Controller controller;
    public ReceiveMessage(Controller controller){
        this.controller = controller;
        this.socket     = controller.getSocket();
    }
    
    @Override
    public void run() {
        while(true){
            String jsonResponse = socket.receiveToServer();
            Message message   = new Gson().fromJson(jsonResponse, Message.class);
            //passa mensagem para o controller, ele sabe o que fazer com a mensagem
            controller.gerenciarNovaMensagem(message);
        }
    }
    
}
