/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.bancodedados;

import br.com.senac.bolsadevalores.Compras;
import br.com.senac.bolsadevalores.Vendas;
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
public class VendasDAO {
    
    
    private final Conexao conexao;
    private final Connection conn;

    public VendasDAO() {
        this.conexao = new Conexao();
        this.conn = conexao.getConexao();
    }
    
    public boolean isEmpty() {
        return conn == null;
    }
    
    public boolean cadastrar(Vendas venda){
        
        String sql = "insert into vendas (quantidade, valor_unitario, custo, data, ticker_acao) values (?,?,?,?,?)";
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1,venda.getQuantidade());
            ps.setDouble(2, venda.getValor());
            ps.setDouble(3,venda.getCusto());
            ps.setDate(4,new java.sql.Date(venda.getData().getTime()));
            ps.setString(5,venda.getTickerAcao());

            int linhasAfetadas = ps.executeUpdate();
            
            conexao.desconectar();
            
            return linhasAfetadas > 0;
        } catch ( SQLException sqle ) {
            return false;
        }
    }
    
    public List<Vendas> getVendas(Integer id){
               
        try {
            String sql;
            PreparedStatement ps;
        
            if (id == null) {
                sql = "SELECT * FROM vendas";
                ps = this.conn.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM vendas WHERE idVenda = ?";
                ps = this.conn.prepareStatement(sql);
                ps.setInt(1,id);
            }

            ResultSet rs = ps.executeQuery();
        
            List<Vendas> lista = new ArrayList<>();
            
            while (rs.next()) { 
                Vendas venda = new Vendas();
                     
                venda.setId(rs.getInt("idVenda"));
                venda.setQuantidade(rs.getInt("quantidade"));
                venda.setValor(rs.getDouble("valor_unitario"));
                venda.setCusto(rs.getDouble("custo"));
                venda.setData(rs.getDate("data"));
                venda.setTickerAcao(rs.getString("ticker_acao"));
                        
                lista.add(venda);    
            }
            
            conexao.desconectar();
          
            return lista;
            
        } catch (SQLException sqle ) {
            return null;
        }
    }
    
    
}
