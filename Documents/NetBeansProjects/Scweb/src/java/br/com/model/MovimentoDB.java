package br.com.model;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MovimentoDB {

    PreparedStatement pstm;
    ResultSet rs;

    String cadastraMovto = "INSERT INTO movimento (CT_DEBITO,CT_CREDITO,DT_EMISSAO,HIST,OBS,VALORUS,DT_VENCTO) VALUES(?,?,?,?,?,?,?)";
    //String alteraMovto = "UPDATE movimento SET NUMERO = ?, DESCRICAO = ?, SALDO_INIC = ?, DT_SALDO = ?, SALDO_INIV = ?, C_SAIVUS = ?, C_SAIEUS = ?, DT_SAUS = ? where idPLANO = ?";

    String alteraMovto = "UPDATE movimento SET CT_DEBITO = ?, CT_CREDITO = ?,DT_EMISSAO = ?,HIST = ?,OBS = ? ,VALORUS = ? ,DT_VENCTO = ? where idmovimento = ?";

    String excluiMovto = "DELETE FROM movimento WHERE idMovimento = ?";

    //String alteraCliente = "UPDATE CLIENTE SET NOME = ?, ENDERECO = ?, CIDADE = ?, ESTADO = ?, "+
    //        "TELEFONE = ?, CELULAR = ?, EMAIL = ? WHERE CODIGO = ?";
    //AcessoDB bd = new AcessoDB();
    AcessoDB bd;

    /**
     * Creates a new instance of ClienteDB
     */
    public MovimentoDB() {
        bd = new AcessoDB();
    }

    public MovimentoDB(String DB) {
        this.bd = new AcessoDB(DB);
    }

    public void alterarMovto(MovimentoBean movto) {
        try {
            pstm = bd.conectar().prepareStatement(alteraMovto);

            pstm.setString(1, movto.getDebito());
            pstm.setString(2, movto.getCredito());
            pstm.setDate(3, (Date) movto.getEmissao());
            pstm.setString(4, movto.getHistorico());
            pstm.setString(5, movto.getObs());
            pstm.setFloat(6, movto.getValor());
            pstm.setDate(7, (Date) movto.getVencimento());
            pstm.setInt(8, movto.getIdmovimento());
            System.out.println("cheqguei aqui..alterar.to quase");
            pstm.executeUpdate();
            bd.desconectar();
            System.out.println("gravei alterar");
        } catch (SQLException erro) {
            erro.printStackTrace();
            System.out.println("Ocorreu um erro de SQL: " + erro);
            //throw new RuntimeException(erro);

        }
    }

    public void cadastrarMovto(MovimentoBean movto) {
        try {
            pstm = bd.conectar().prepareStatement(cadastraMovto);
            pstm.setString(1, movto.getDebito());
            pstm.setString(2, movto.getCredito());
            pstm.setDate(3, (Date) movto.getEmissao());

            pstm.setString(4, movto.getHistorico());
            pstm.setString(5, movto.getObs());
            pstm.setFloat(6, movto.getValor());
            pstm.setDate(7, (Date) movto.getVencimento());

            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException erro) {
            //erro.printStackTrace();  
            System.out.println("Ocorreu um erro de SQL: " + erro);
            //throw new RuntimeException(erro);
        }
    }

    public void excluirMovto(MovimentoBean movto) {
        try {
            pstm = bd.conectar().prepareStatement(excluiMovto);
            pstm.setInt(1, movto.getIdmovimento());
            pstm.executeUpdate();
            bd.desconectar();
        } catch (SQLException erro) {
            //erro.printStackTrace();  
            //System.out.println("Ocorreu um erro de SQL: " + erro); 
            throw new RuntimeException(erro);
        }
    }

    public List<MovimentoBean> listarMovtos(String nome, String campo) {
        List<MovimentoBean> movtos = new ArrayList();
        String consultaMovto = "SELECT * FROM MOVIMENTO WHERE " + campo + " LIKE '%" + nome + "%' order by dt_vencto,IDMOVIMENTO";
        System.out.println(consultaMovto);
        try {
            pstm = bd.conectar().prepareStatement(consultaMovto);
            //pstm.setString(5,nome);
            rs = pstm.executeQuery();
            MovimentoBean li;
            while (rs.next()) {
                li = new MovimentoBean();
                li.setIdmovimento(rs.getInt("idMovimento"));
                li.setDebito(rs.getString("ct_debito"));
                li.setCredito(rs.getString("ct_credito"));
                li.setEmissao(rs.getDate("dt_emissao"));
                li.setHistorico(rs.getString("hist"));
                li.setObs(rs.getString("obs"));
                li.setValor(rs.getFloat("valorus"));
                li.setVencimento(rs.getDate("dt_vencto"));
                System.out.println(li.toString());
                movtos.add(li);
            }
            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movtos;
    }

    public List<MovimentoBean> consultaMovto(String opcao, String DATAI, String DATAF, String conta) {
        List<MovimentoBean> movtos = new ArrayList();
        String consultaMovto = "";
        if (conta.equals(null) || conta.equals("")) {
            consultaMovto = "select * from movimento where " + opcao + " >= '" + DATAI + "' AND " + opcao + " <= '" + DATAF + "' order by " + opcao + ",IDMOVIMENTO";
        } else {
            consultaMovto = "select * from movimento where " + opcao + " >= '" + DATAI + "' AND " + opcao + " <= '" + DATAF + "' and (ct_debito = '" + conta + "' || ct_credito = '" + conta + "')order by " + opcao + ",IDMOVIMENTO";
        }

        try {
            pstm = bd.conectar().prepareStatement(consultaMovto);
            //pstm.setString(5,nome);
            rs = pstm.executeQuery();
            MovimentoBean li;
            while (rs.next()) {
                li = new MovimentoBean();
                li.setIdmovimento(rs.getInt("idMovimento"));
                li.setDebito(rs.getString("ct_debito"));
                li.setCredito(rs.getString("ct_credito"));
                li.setEmissao(rs.getDate("dt_emissao"));
                li.setHistorico(rs.getString("hist"));
                li.setObs(rs.getString("obs"));
                li.setValor(rs.getFloat("valorus"));
                li.setVencimento(rs.getDate("dt_vencto"));
                System.out.println(li.toString());
                movtos.add(li);
            }
            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return movtos;
    }
}
