package ufgd.redes.models;

import com.google.gson.Gson;

/**
 * Classe responsável por conter todas informações necessárias para ser enviadas
 * ao servidor.
 *
 * @author franco
 */
public class Message {

    
    String remetente;
    String destinatario;
    String msg;

    /**
     * Construtor do pacote
     *
     * @param remetente
     * @param msg
     */
    public Message(String remetente, String destinatario, String msg) {
        this.remetente = remetente;
        this.destinatario =destinatario;
        this.msg = msg;
    }

    public String getRemetente() {
        return remetente;
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
    
    

    /**
     * Transforma em Json
     *
     * @return json
     */
    public String toJson() {
        return new Gson().toJson(this);
    }
}
