/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufgd.redes.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author franco
 */
abstract class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost/pombo";
    private static final String USER= "root";
    private static final String PASS= "bola1993";
            
    private Connection conexao;

    public Connection getConexao() throws SQLException {
        
         if(conexao!=null && !conexao.isClosed())
                return conexao;
        conexao = DriverManager.getConnection(URL, USER, PASS);
        return conexao;
    }
    
    public void fecharConexao(PreparedStatement stmt, ResultSet rs){
        try {
            if(conexao!=null)
                conexao.close();
            if(stmt!=null)
                stmt.close();
            if(rs!=null)
                rs.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
