package br.com.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geraldo
 */
public class Pagina {

    PreparedStatement pstm;
    ResultSet rs;
    SimpleDateFormat data;
    private DecimalFormat df;
    AcessoDB bd;

    public Pagina(String DB) {

        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        data = new SimpleDateFormat("dd/MM/yyyy");
        bd = new AcessoDB(DB);
    }

    //AcessoDB bd = new AcessoDB();
    public int qtdReg() {

        int qtd = 0;

        String sql = "SELECT COUNT(*) AS c FROM movimento";

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

        String sql = "SELECT COUNT(*) AS c FROM movimento";

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

    public int pagAtual(String opcao, int limite) {

        int qtdReg = 0;
        int numPagina = 0;
        Date date = new Date();
        //String datahoje = data.format(date);//pegar a data do sistema
        String datahoje = DtoC(date);//pegar a data do sistema
        System.out.println("date " + datahoje);
        String sql = "SELECT * FROM movimento ORDER BY " + opcao + ",IDMOVIMENTO";

        try {
            pstm = bd.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
              //  System.out.println("dt_vencto " + rs.getDate(opcao) + " datahoje " + datahoje);
                try {
                    if (rs.getDate(opcao).before(date)) {
                      //  System.out.println(" dt_vencto < " + rs.getDate(opcao) + " datahoje " + datahoje);

                        qtdReg++;
                    }
                } catch (NullPointerException npe) {
                   // System.out.println(npe.getMessage());
                }
            }
            if (qtdReg % limite == 0) {
                numPagina = (qtdReg / limite);
            } else {
                numPagina = (qtdReg / limite) + 1;
            }

            bd.desconectar();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("numPagina " + numPagina);

        return numPagina;
    }

    public int pagIrPara(String opcao, int limite, Date datai) {

        int qtdReg = 0;
        int numPagina = 0;
        //Date date = new Date();
        //String datahoje = data.format(date);//pegar a data do Date
        //String datahoje = DtoC(datai);//pegar a data do sistema

        String sql = "SELECT * FROM movimento ORDER BY " + opcao + ",IDMOVIMENTO";

        try {
            pstm = bd.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            while (rs.next()) {
                //System.out.println(datai);
                try {
                    if (rs.getDate(opcao).before(datai) || rs.getDate(opcao).equals(datai)) {
                        qtdReg++;
                    }
                } catch (NullPointerException npe) {
                    System.out.println(npe.getMessage());
                }
            }
            if (qtdReg % limite == 0) {
                numPagina = (qtdReg / limite);
            } else {
                numPagina = (qtdReg / limite) + 1;
            }

            bd.desconectar();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return numPagina;
    }

    public List<MovimentoBean> buscaPagina(String opcao, int limite, int off, String d) {

        List<MovimentoBean> pagina = new ArrayList();
        String sql;
        Date date = new Date();
        //String datahoje = data.format(date);//pegar a data do sistema
        String datahoje = DtoC(date);//pegar a data do sistema

        System.out.println(datahoje + " opcao " + opcao);
        //sql = "select * from movimento order by " + opcao ;
        if (d != "s") {
            sql = "SELECT * FROM movimento order by " + opcao + ",IDMOVIMENTO LIMIT " + limite + " offset " + off;
        } else {
            sql = "SELECT * FROM movimento where " + opcao + ">= '" + datahoje + "' order by " + opcao + ",IDMOVIMENTO LIMIT " + limite + " offset " + off;
        }

        System.out.println(sql);
        try {
            pstm = bd.conectar().prepareStatement(sql);

            rs = pstm.executeQuery();
            MovimentoBean li;
            while (rs.next()) {
                li = new MovimentoBean();
                li.setIdmovimento(rs.getInt("idmovimento"));
                li.setDebito(rs.getString("ct_debito"));
                li.setCredito(rs.getString("ct_credito"));
                li.setEmissao(rs.getDate("dt_emissao"));
                li.setHistorico(rs.getString("hist"));
                li.setObs(rs.getString("obs"));
                li.setValor(rs.getFloat("valorus"));
                li.setVencimento(rs.getDate("dt_vencto"));
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

    public Date CtoD(String datas) {

        Date datasai = null;
        try {
            //    SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat formatar = new SimpleDateFormat("yyyy-MM-dd");

            String dataString = datas;
            datasai = formatar.parse(dataString);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return datasai;
    }
}
