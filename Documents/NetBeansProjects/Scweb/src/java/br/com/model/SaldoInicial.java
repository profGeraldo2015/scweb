/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

/**
 *
 * @author User
 */
public class SaldoInicial {

    private String descricao;
    private Float SaldoAnterior;
    private Date DataInicial;
    PreparedStatement pstm;
    ResultSet rs;
    AcessoDB bd;

    public SaldoInicial(String conta,String DB) {

        bd = new AcessoDB(DB);
        this.pegaSaldo(conta);
    }
    public SaldoInicial() {

        //bd = new AcessoDB();

    }
    @Override
    public String toString() {
        return "SaldoInicial{" + "descricao=" + descricao + ", SaldoAnterior=" + SaldoAnterior + ", DataInicial=" + DataInicial +  '}';
    }


    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getSaldoAnterior() {
        return SaldoAnterior;
    }

    public void setSaldoAnterior(Float SaldoAnterior) {
        this.SaldoAnterior = SaldoAnterior;
    }

    public Date getDataInicial() {
        return DataInicial;
    }

    public void setDataInicial(Date DataInicial) {
        this.DataInicial = DataInicial;
    }
    public void pegaSaldo(String conta){

          //  AcessoDB bd = new AcessoDB();
            String sql = "SELECT * FROM PLANO WHERE NUMERO = " + conta ;
            try{
                pstm = bd.conectar().prepareStatement(sql);
                rs = pstm.executeQuery();
                rs.first();
                //setSaldoAnterior(rs.getFloat("SALDO_INIV"));
                setSaldoAnterior(rs.getFloat("C_SAIVUS"));

                setDescricao(rs.getString("DESCRICAO"));

                //setDataInicial(rs.getDate("DT_SALDO"));
                setDataInicial(rs.getDate("DT_SAUS"));
                bd.desconectar();
            } catch(Exception e){
                e.printStackTrace();
            }

    }
     public String pegaDescricao(String conta){

         //AcessoDB bd = new AcessoDB();
            String sql = "SELECT * FROM PLANO WHERE NUMERO = " + conta ;
            try{
                pstm = bd.conectar().prepareStatement(sql);
                rs = pstm.executeQuery();
                rs.first();
                this.setSaldoAnterior(rs.getFloat("C_SAIVUS"));
                this.setDescricao(rs.getString("DESCRICAO"));
                this.setDataInicial(rs.getDate("DT_SAUS"));

                bd.desconectar();
            } catch(Exception e){
                e.printStackTrace();
            }
            String descri = this.getDescricao();

    return descri;
    }
}
