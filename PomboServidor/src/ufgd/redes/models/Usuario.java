package ufgd.redes.models;
// Generated 26/04/2015 20:07:04 by Hibernate Tools 4.3.1


import java.util.Date;

/**
 * Usuario generated by hbm2java
 */
public class Usuario  implements java.io.Serializable {


     private Integer id;
     private String username;
     private String password;
     private String image;
     private boolean ativo = false;
     private Date lastLogin;
     private Date dateCreate;

    public Usuario() {
    }
    public Usuario(String username){
        this.username=username;
    }
    public Usuario(String username, String password) {
       this.username = username;
       this.password = password;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
    
    public String getUsername() {
        return this.username.toLowerCase();
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return this.password;
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
        return this.lastLogin;
    }
    
    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }
    public Date getDateCreate() {
        return this.dateCreate;
    }
    
    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }




}


