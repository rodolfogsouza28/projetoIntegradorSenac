/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.bancodedados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Rodolfo
 */
public class Conexao {
    
    private Connection conn;
    private final String url = "jdbc:mysql://localhost:3306/bolsadevalores"; //Nome da base de dados
    private final String user = "root"; //nome do usuário do MySQL
    private final String password = "85623car"; //senha do MySQL
    
    
/**
 * Função que cria a conexão com o Banco e retorna a conexão
 */
    
    public Connection getConexao(){     
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            return conn;
        } catch (ClassNotFoundException | SQLException ex) {
            return null;
        }
    }
    
 /**
 * Função que fecha a conexão com o Banco de Dados
 */
    public void desconectar(){
        try {
            conn.close();
        } catch (SQLException ex) { 
        }
    }
    
}
