package ufgd.redes.model;

/**
 * @author franco
 */
public class Usuario {

    private String ip;
    private String nome;

    public Usuario() {
    }

    public Usuario(String nome, String ip) {
        this.ip = ip;
        this.nome = nome;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    @Override
    public String toString(){
        return nome;
    }

}
