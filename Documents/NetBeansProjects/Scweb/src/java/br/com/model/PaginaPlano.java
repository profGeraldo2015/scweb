/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geraldo
 */
public class PaginaPlano {

    PreparedStatement pstm;
    ResultSet rs;
    SimpleDateFormat data;
    private DecimalFormat df;
    AcessoDB bd;

    public PaginaPlano(String  DB) {

        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        data = new SimpleDateFormat("dd/MM/yyyy");
        this.bd = new AcessoDB(DB);
    }

    //AcessoDB bd = new AcessoDB(DB);

    public int qtdReg() {

        int qtd = 0;

        String sql = "SELECT COUNT(*) AS c FROM plano";

        try {
            pstm = bd.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();
            qtd = Integer.parseInt(rs.getString("c"));

            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return qtd;
    }

    public int numPag(int limite) {

        int numPag = 0;
        int qtd = 0;

        String sql = "SELECT COUNT(*) AS c FROM plano";

        try {
            pstm = bd.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            rs.next();
            qtd = Integer.parseInt(rs.getString("c"));
            //achar a qunatidade de paginas e qual pagina o offset está

            if (qtd % limite == 0) {
                numPag = (qtd / limite);
            } else {
                numPag = (qtd / limite) + 1;
            }

            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numPag;
    }

    public List<PlanoBean> buscaPagina(int limite, int off) {

        List<PlanoBean> pagina = new ArrayList();
        String sql;
        sql = "select * from plano order by numero LIMIT " + limite + " offset " + off;;
        System.out.println(sql);
        try {
            pstm = bd.conectar().prepareStatement(sql);

            rs = pstm.executeQuery();
            PlanoBean li;
            while (rs.next()) {

                li = new PlanoBean();

                li.setNumero(rs.getString("numero"));
                li.setIdPLANO(rs.getInt("idplano"));
                li.setDescricao(rs.getString("descricao"));
                li.setSaldo_inic(rs.getFloat("saldo_inic"));
                li.setSaldo_iniv(rs.getFloat("saldo_iniv"));
                li.setDt_saldo(rs.getDate("dt_saldo"));
                li.setDt_saus(rs.getDate("dt_saus"));
                li.setC_saieus(rs.getFloat("c_saieus"));
                li.setC_saivus(rs.getFloat("c_saivus"));
                pagina.add(li);
            }
            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pagina;
    }

    public String DtoC(Date datad) {

        String datasai = null;
        try {

            Date data = datad;

            SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");

            datasai = formatar.format(data);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return datasai;
    }

}
