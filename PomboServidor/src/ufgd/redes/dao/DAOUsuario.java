package ufgd.redes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ufgd.redes.models.Usuario;

/**
 * Classe responsável por realizar CRUD com banco de dados
 * @author franco
 */
public class DAOUsuario extends ConnectionFactory {

    /**
     * Nome da tabela no banco relativo ao modelo Usuario.
     */
    public static final String TABELA = "usuario";

    
    public DAOUsuario() {
    }

    /**
     * Insere usuario na base de dados
     * @param usuario
     * @return 
     */
    public boolean inserir(Usuario usuario) {
        boolean result = true;
        String sql = "INSERT INTO " + TABELA + " (username, password) VALUES (? ,?)";
        
        PreparedStatement stmt=null;
        
        try {
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());
            stmt.execute();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
            result=false;
        }finally{
            fecharConexao(stmt,null);   
        }
        return result;
    }

    /**
     * Verifica se login está correto
     * @param username
     * @param password
     * @return 
     */
    public boolean autentica(String username, String password){
        boolean result = false;
        String sql = "SELECT id FROM "+TABELA+" WHERE username = ? AND password = ? LIMIT 1";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while(rs.next()){
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fecharConexao(stmt,rs);
        }
        return result;
    }
    
    /**
     * Lista todos os usuarios no banco de dados
     * @param username
     * @return 
     */
    public List<Usuario> listAll(String username){
        boolean result = false;
        String sql = "SELECT username FROM "+TABELA+" WHERE username != ? ORDER BY username ASC";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList();
        try {
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setUsername(rs.getString("username"));
                usuarios.add(usuario);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fecharConexao(stmt,rs);
        }
        return usuarios;
    }
    
    /**
     * Verifica se existe algum usuario com o mesmo username passado pelo parametro
     * @param username
     * @return 
     */
    public boolean existe(String username) {
        boolean result = false;
        String sql = "SELECT id FROM "+TABELA+" WHERE username = ? LIMIT 1";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()){
                result = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fecharConexao(stmt,rs);
        }
        return result;
    }
}
