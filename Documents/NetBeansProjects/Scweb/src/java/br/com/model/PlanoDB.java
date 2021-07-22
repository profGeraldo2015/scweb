/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class PlanoDB {

    /**
     *
     * @author ProfGeraldo
     */
    PreparedStatement pstm;
    ResultSet rs;

    String cadastraPlano = "INSERT INTO PLANO (NUMERO, DESCRICAO, SALDO_INIC, DT_SALDO, SALDO_INIV, C_SAIVUS, C_SAIEUS, DT_SAUS) VALUES(?,?,?,?,?,?,?,?)";
    String alteraPlano = "UPDATE PLANO SET numero = ?,DESCRICAO = ?, SALDO_INIC = ?, DT_SALDO = ?, SALDO_INIV = ?, C_SAIVUS = ?, C_SAIEUS = ?, DT_SAUS = ? where IdPlano = ?";
    String excluiPlano = "DELETE FROM PLANO WHERE NUMERO = ?";

    //String alteraCliente = "UPDATE CLIENTE SET NOME = ?, ENDERECO = ?, CIDADE = ?, ESTADO = ?, "+
    //        "TELEFONE = ?, CELULAR = ?, EMAIL = ? WHERE CODIGO = ?";
    AcessoDB bd ;

    /**
     * Creates a new instance of ClienteDB
     */
    public PlanoDB(String DB) {

        this.bd = new AcessoDB(DB);
    }

    public void alterarPlano(PlanoBean plano) {
        try {
            pstm = bd.conectar().prepareStatement(alteraPlano);

            System.out.println(plano.toString());

            pstm.setString(1, plano.getNumero());
            pstm.setString(2, plano.getDescricao());
            pstm.setFloat(3, plano.getSaldo_inic());
            pstm.setDate(4, (Date) plano.getDt_saldo());
            pstm.setFloat(5, plano.getSaldo_iniv());
            pstm.setFloat(6, plano.getC_saivus());
            pstm.setFloat(7, plano.getC_saieus());
            pstm.setDate(8, (Date) plano.getDt_saus());
            pstm.setInt(9, plano.getIdPLANO());

            pstm.executeUpdate();
            //pstm.executeUpdate(alteraPlano);

            bd.desconectar();
        } catch (SQLException erro) {
            erro.printStackTrace();
            System.out.println("Ocorreu um erro de SQL: " + erro);
            throw new RuntimeException(erro);

        }
    }

    public void cadastrarPlano(PlanoBean plano) {
        try {
            System.out.println(cadastraPlano);
            System.out.println(plano.toString());
            pstm = bd.conectar().prepareStatement(cadastraPlano);
            pstm.setString(1, plano.getNumero());
            pstm.setString(2, plano.getDescricao());
            pstm.setFloat(3, plano.getSaldo_inic());
            pstm.setDate(4, (Date) plano.getDt_saldo());
            pstm.setFloat(5, plano.getSaldo_iniv());
            pstm.setFloat(6, plano.getC_saivus());
            pstm.setFloat(7, plano.getC_saieus());
            pstm.setDate(8, (Date) plano.getDt_saus());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException erro) {
            //erro.printStackTrace();
            System.out.println("Ocorreu um erro de SQL: " + erro);
            throw new RuntimeException(erro);
        }
    }

    public void excluirPlano(PlanoBean plano) {
        try {
            pstm = bd.conectar().prepareStatement(excluiPlano);
            // pstm.setInt(1, plano.getIsbn());
            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException erro) {
            //erro.printStackTrace();
            //System.out.println("Ocorreu um erro de SQL: " + erro);
            throw new RuntimeException(erro);
        }
    }

    public List<PlanoBean> listarPlanos(String nome, String campo) {

        List<PlanoBean> planos = new ArrayList();

        String consultaPlano;

        if ( campo.equals("")) {
            consultaPlano = "SELECT * FROM PLANO order by numero";

        }else{
            consultaPlano = "SELECT * FROM PLANO WHERE " + campo + " LIKE '%" + nome + "%' order by numero";
        }
        try {
            pstm = bd.conectar().prepareStatement(consultaPlano);
            pstm.setString(1, nome);
            rs = pstm.executeQuery();
            PlanoBean li;
            while (rs.next()) {
                li = new PlanoBean();

                li.setNumero(rs.getString("numero"));
                li.setDescricao(rs.getString("descricao"));
                li.setSaldo_inic(rs.getFloat("saldo_inic"));
                li.setSaldo_iniv(rs.getFloat("saldo_iniv"));
                li.setDt_saldo(rs.getDate("dt_saldo"));
                li.setDt_saus(rs.getDate("dt_saus"));
                li.setC_saieus(rs.getFloat("c_saieus"));
                li.setC_saivus(rs.getFloat("c_saivus"));

                planos.add(li);
            }
            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return planos;
    }

    public String pegaDesc(String conta) {

        String descricao="";

        String consultaPlano;

        consultaPlano = "SELECT * FROM PLANO order by numero";

        try {
            pstm = bd.conectar().prepareStatement(consultaPlano);
           // pstm.setString(1, conta);
            rs = pstm.executeQuery();
            while (rs.next()) {

                if (rs.getString("numero").equals(conta)) {
                    descricao = rs.getString("descricao");
                    break;
                }else{
                    descricao = "Não encontrada";
                }
            }
            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return descricao;
    }

    public List<PlanoBean> carregaComboPlano() {
        List<PlanoBean> comboplano = new ArrayList();
        String sql = "SELECT NUMERO,DESCRICAO,C_SAIVUS FROM PLANO ORDER BY NUMERO";

        try {
            pstm = bd.conectar().prepareStatement(sql);

            rs = pstm.executeQuery();
            PlanoBean li;
            while (rs.next()) {
                li = new PlanoBean();
                li.setNumero(rs.getString("numero"));
                li.setDescricao(rs.getString("descricao"));
                li.setSaldo_iniv(rs.getFloat("C_SAIVUS"));

                comboplano.add(li);
            }
            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return comboplano;
    }

}
