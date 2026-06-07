/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.bancodedados;

import br.com.senac.bolsadevalores.Acoes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rodolfo
 */
public class AcoesDAO {
    
    private final Conexao conexao;
    private final Connection conn;

    public AcoesDAO() {
        this.conexao = new Conexao();
        this.conn = conexao.getConexao();
    }
    
    public boolean isEmpty() {
        return conn == null;
    }
    
    public boolean excluir(String ticker){
        
        String sql = "DELETE FROM acoes WHERE ticker = ?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,ticker);
            
            
            int linhasAfetadas = ps.executeUpdate();
                    
            conexao.desconectar();
            return linhasAfetadas >0;
            
        } catch (SQLException sqle ) {
            System.out.print(sqle.getMessage());
            return false;
        }
    }
    
    public boolean cadastrar(Acoes acao){
        
        String sql = "insert into acoes (ticker, cnpj, nome) values (?,?,?)";
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1,acao.getTicker());
            ps.setString(2, acao.getCnpj());
            ps.setString(3,acao.getNome());

            int linhasAfetadas = ps.executeUpdate();
            
            conexao.desconectar();
            
            return linhasAfetadas > 0;
        } catch ( SQLException sqle ) {
            return false;
        }
    }
    
    public Acoes pesquisar(String ticker){
        
        String sql = "SELECT * FROM acoes WHERE ticker = ?";
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setString(1,ticker);
            ResultSet rs = ps.executeQuery();
        
            Acoes acao = new Acoes();
          
            rs.next(); 
            
            acao.setTicker(rs.getString("ticker"));
            acao.setNome(rs.getString("nome"));
            acao.setCnpj(rs.getString("cnpj"));
            
            conexao.desconectar();
          
            return acao;
            
        } catch (SQLException sqle ) {
            return null;
        }
    }
    
    public boolean editar(Acoes acao){
        
        String sql = "UPDATE acoes SET nome=?, cnpj=? WHERE ticker=?";
        
        try {
            PreparedStatement ps = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE,
                    ResultSet.CONCUR_UPDATABLE);
            ps.setString(1,acao.getNome());
            ps.setString(2, acao.getCnpj());
            ps.setString(3, acao.getTicker());
            
            int linhasAfetadas = ps.executeUpdate();
                    
            conexao.desconectar();
          
            return linhasAfetadas > 0;
            
        } catch (SQLException sqle ) {
            return false;
        }
    }
    
    public List<Acoes> getAcoes(){
        
        String sql = "SELECT * FROM acoes";
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
        
            List<Acoes> lista = new ArrayList<>();
            
            while (rs.next()) { 
                Acoes acao = new Acoes();
                        
                acao.setTicker(rs.getString("ticker"));
                acao.setCnpj(rs.getString("cnpj"));
                acao.setNome(rs.getString("nome"));
                        
                lista.add(acao);    
            }
            
            conexao.desconectar();
          
            return lista;
            
        } catch (SQLException sqle ) {
            return null;
        }
    }
    
}
