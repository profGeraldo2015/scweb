/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Relatorio
 */
public class Relatorio {

    PreparedStatement pstm;
    ResultSet rs;
    String W_DT_SALDO;
    private Date datai;
    private Date w_dt_saldo;
    private Date ATUAL2;
    private String ATUAL;
    private String DIA_ANT;
    SaldoInicial saldo_inic;
    String DB;
    AcessoDB bd;

    public Relatorio(String DB) {
        bd = new AcessoDB(DB);
        this.DB = DB;

    }
    //AcessoDB bd = new AcessoDB();

    public List<MovimentoBean> listagem(String opcao, String conta, String inicio, String fim) {

        List<MovimentoBean> listagem = new ArrayList();
        String sql = null;

        sql = "select * from movimento where (" + opcao + " >= '" + inicio + "' and " + opcao + " <= '" + fim + "') and (ct_debito = " + conta + " or ct_credito=" + conta + ") order by " + opcao;

        System.out.println("sql " + sql);

        try {
            pstm = bd.conectar().prepareStatement(sql);
            // pstm.setString(1,conta);
            rs = pstm.executeQuery();
            MovimentoBean li;
            while (rs.next()) {
                li = new MovimentoBean();

                li.setDebito(rs.getString("ct_debito"));
                li.setCredito(rs.getString("ct_credito"));
                li.setEmissao(rs.getDate("dt_emissao"));
                li.setHistorico(rs.getString("hist"));
                li.setObs(rs.getString("obs"));
                li.setValor(rs.getFloat("valorus"));
                li.setVencimento(rs.getDate("dt_vencto"));
                listagem.add(li);
            }
            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listagem;
    }

    public List<MovimentoBean> listagem(String opcao, String inicio, String fim) {

        List<MovimentoBean> listagem = new ArrayList();
        String sql = null;
        sql = "select * from movimento where (" + opcao + " >= '" + inicio + "' and " + opcao + " <= '" + fim + "') order by " + opcao;

        //sql = "select * from movimento order by " + opcao;
        try {
            pstm = bd.conectar().prepareStatement(sql);
            // pstm.setString(1,conta);
            rs = pstm.executeQuery();
            MovimentoBean li;
            while (rs.next()) {
                li = new MovimentoBean();

                li.setDebito(rs.getString("ct_debito"));
                li.setCredito(rs.getString("ct_credito"));
                li.setEmissao(rs.getDate("dt_emissao"));
                li.setHistorico(rs.getString("hist"));
                li.setObs(rs.getString("obs"));
                li.setValor(rs.getFloat("valorus"));
                li.setVencimento(rs.getDate("dt_vencto"));
                listagem.add(li);
            }
            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return listagem;
    }

    public String transformaData(String data) {

        String dia = data.substring(0, 2);
        String mes = data.substring(3, 5);
        String ano = data.substring(6, 10);
        String datas = ano + "-" + mes + "-" + dia;

        return (datas);
    }

}
