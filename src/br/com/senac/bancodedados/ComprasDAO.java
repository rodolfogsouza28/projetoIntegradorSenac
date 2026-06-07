/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.bancodedados;
import br.com.senac.bolsadevalores.Acoes;
import br.com.senac.bolsadevalores.Compras;
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
public class ComprasDAO {
    
    private final Conexao conexao;
    private final Connection conn;

    public ComprasDAO() {
        this.conexao = new Conexao();
        this.conn = conexao.getConexao();
    }
    
    public boolean isEmpty() {
        return conn == null;
    }
    
    public boolean cadastrar(Compras compra){
        
        String sql = "insert into compras (quantidade, valor_unitario, custo, data, ticker_acao) values (?,?,?,?,?)";
        
        try {
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ps.setInt(1,compra.getQuantidade());
            ps.setDouble(2, compra.getValor());
            ps.setDouble(3,compra.getCusto());
            ps.setDate(4,new java.sql.Date(compra.getData().getTime()));
            ps.setString(5,compra.getTickerAcao());

            int linhasAfetadas = ps.executeUpdate();
            
            conexao.desconectar();
            
            return linhasAfetadas > 0;
        } catch ( SQLException sqle ) {
            return false;
        }
    }
    
    public List<Compras> getCompras(Integer id){
               
        try {
            String sql;
            PreparedStatement ps;
        
            if (id == null) {
                sql = "SELECT * FROM compras";
                ps = this.conn.prepareStatement(sql);
            } else {
                sql = "SELECT * FROM compras WHERE idCompra = ?";
                ps = this.conn.prepareStatement(sql);
                ps.setInt(1,id);
            }

            ResultSet rs = ps.executeQuery();
        
            List<Compras> lista = new ArrayList<>();
            
            while (rs.next()) { 
                Compras compra = new Compras();
                     
                compra.setId(rs.getInt("idCompra"));
                compra.setQuantidade(rs.getInt("quantidade"));
                compra.setValor(rs.getDouble("valor_unitario"));
                compra.setCusto(rs.getDouble("custo"));
                compra.setData(rs.getDate("data"));
                compra.setTickerAcao(rs.getString("ticker_acao"));
                        
                lista.add(compra);    
            }
            
            conexao.desconectar();
          
            return lista;
            
        } catch (SQLException sqle ) {
            return null;
        }
    }
    
}
