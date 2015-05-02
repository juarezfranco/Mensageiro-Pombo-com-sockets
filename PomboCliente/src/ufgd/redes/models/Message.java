package ufgd.redes.models;

import com.google.gson.Gson;

/**
 * Classe responsável por conter todas informações necessárias para ser enviadas
 * ao servidor.
 *
 * @author franco
 */
public class Message {

    int id;
    String tipo;
    Usuario remetente;
    Usuario destinatario;
    String msg;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Usuario getRemetente() {
        return remetente;
    }

    public void setRemetente(Usuario remetente) {
        this.remetente = remetente;
    }

    public Usuario getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(Usuario destinatario) {
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
