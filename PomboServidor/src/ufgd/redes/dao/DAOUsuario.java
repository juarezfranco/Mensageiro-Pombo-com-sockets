package ufgd.redes.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ufgd.redes.models.Message;
import ufgd.redes.models.Usuario;
import static ufgd.redes.utils.Constantes.NOVA_MENSAGEM;

/**
 * Classe responsável por realizar CRUD com banco de dados
 * @author franco
 */
public class DAOUsuario extends ConnectionFactory {

    /**
     * Nome da tabela no banco relativo ao modelo Usuario.
     */
    public static final String TABELA_USUARIOS = "usuarios";
    public static final String TABELA_MSG_PENDENTES="mensagens_pendentes";

    
    public DAOUsuario() {}
    
    
    /**
     * Altera dados do usuario
     * @param usuario 
     */
    public void update(Usuario usuario){
        String sql = "UPDATE "+TABELA_USUARIOS+" SET image = ? WHERE username = ?";
        PreparedStatement stmt = null;
        try {    
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, usuario.getImage());
            stmt.setString(2, usuario.getUsername());
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fecharConexao(stmt,null);   
        }
        
    }
    /**
     * Atualiza ultimo login do usuario
     * @param usuario 
     */
    public void updateLastLogin(Usuario usuario){
        String sql ="UPDATE "+TABELA_USUARIOS+" SET last_login=now() WHERE USERNAME ='"+usuario.getUsername()+"'";
        PreparedStatement stmt=null;
        try {
            stmt = getConexao().prepareStatement(sql);
            stmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fecharConexao(stmt,null);   
        }
    }
    /**
     * Insere usuario na base de dados
     * @param usuario
     * @return 
     */
    public boolean inserir(Usuario usuario) {
        boolean result = true;
        String sql = "INSERT INTO " + TABELA_USUARIOS + " (username, password,image) VALUES (? ,?,?)";
        
        PreparedStatement stmt=null;
        
        try {
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, usuario.getUsername().toLowerCase());
            stmt.setString(2, usuario.getPassword());
            stmt.setString(3,usuario.getImage());
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
        String sql = "SELECT username FROM "+TABELA_USUARIOS+" WHERE username = ? AND password = ? LIMIT 1";
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
    public List<Usuario> listarTodosUsuarios(String username){
        boolean result = false;
        String sql = "SELECT username, image, last_login FROM "+TABELA_USUARIOS+" WHERE username != ? ORDER BY username ASC";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Usuario> usuarios = new ArrayList();
        try {
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, username);
            rs = stmt.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario();
                usuario.setUsername(rs.getString("username").toLowerCase());
                usuario.setImage(rs.getString("image"));
                usuario.setLastLogin(rs.getTimestamp("last_login"));
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
        String sql = "SELECT username FROM "+TABELA_USUARIOS+" WHERE username = ? LIMIT 1";
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
    
    public void guardarMensagem(Message message){
        String sql = "INSERT INTO "+TABELA_MSG_PENDENTES+" (remetente, destinatario, mensagem) VALUES (?,?,?)";
        PreparedStatement stmt = null;
        try{
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, message.getRemetente().getUsername());
            stmt.setString(2, message.getDestinatario().getUsername());
            stmt.setString(3, message.getMsg());
            stmt.execute();
        }catch(SQLException ex){
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fecharConexao(stmt,null);
        }
    }
    public void removerMensagemPendente(int id){
        String sql = "DELETE FROM "+TABELA_MSG_PENDENTES+" WHERE id = ? ";
        PreparedStatement stmt = null;
        try{
            stmt = getConexao().prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
        }catch(SQLException ex){
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fecharConexao(stmt,null);
        }
    }
    public List<Message> listarMensagensPendentes(Usuario destinatario){
        
        String sql = " SELECT id, remetente, mensagem, data, image, last_login"
                +    " FROM "+TABELA_MSG_PENDENTES+", "+TABELA_USUARIOS
                +    " WHERE remetente=username && destinatario = ? ORDER BY data ASC";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Message> mensagens = new ArrayList<Message>();
        try{
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, destinatario.getUsername());
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Message message = new Message();
                message.setTipo(NOVA_MENSAGEM);
                Usuario usuario = new Usuario();
                usuario.setUsername(rs.getString("remetente"));
                usuario.setImage(rs.getString("image"));
                usuario.setLastLogin(rs.getTimestamp("last_login"));
                message.setRemetente(usuario);
                //message.setDestinatario(destinatario);
                message.setId(rs.getInt("id"));
                message.setData(rs.getTimestamp("data"));
                message.setMsg(rs.getString("mensagem"));
                mensagens.add(message);
            }
        }catch(SQLException ex){
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            fecharConexao(stmt,rs);
        }
        return mensagens;
    }
    public Usuario getUsuario(String username) {
        String sql = "SELECT * FROM "+TABELA_USUARIOS+" WHERE username=? LIMIT 1";
        PreparedStatement stmt = null;
        Usuario usuario=null;
        try{
            stmt = getConexao().prepareStatement(sql);
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                usuario = new Usuario();
                usuario.setUsername(username);
                usuario.setImage(rs.getString("image"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOUsuario.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
}
