/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.senac.bolsadevalores;

import java.util.Date;

/**
 *
 * @author Rodolfo
 */
public class Compras {
    
    private Integer id;
    private Integer quantidade;
    private Double valor;
    private Double custo;
    private Date data;
    private String tickerAcao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getCusto() {
        return custo;
    }

    public void setCusto(Double custo) {
        this.custo = custo;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getTickerAcao() {
        return tickerAcao;
    }

    public void setTickerAcao(String tikerAcao) {
        this.tickerAcao = tikerAcao;
    }
    
}
