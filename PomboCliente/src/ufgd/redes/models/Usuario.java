package ufgd.redes.models;

import java.util.Date;

/**
 * Pojo de usuario
 * @author franco
 */
public class Usuario{

    private String username;
    private String password;
    private int image;
    private boolean ativo=false;
    private Date lastLogin;
    
    public Usuario() {
    }
    public Usuario(String username,String password,int image){
        this.username = username;
        this.password = password;
        this.image=image;
    }
    public Usuario(String username) {
        this.username = username;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername(){
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    
    
    
    @Override
    public String toString(){
        String status;
        if(ativo)
            status="(online)";
        else
            status="(offilne)";
        return username+" "+status;
    }

    

}
