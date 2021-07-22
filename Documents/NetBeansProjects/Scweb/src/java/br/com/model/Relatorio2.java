package br.com.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;

public class Relatorio2 {

    PreparedStatement pstm;
    ResultSet rs;
    String W_DT_SALDO;
    private Date datai;
    private Date w_dt_saldo;
    private Date ATUAL2;
    private String ATUAL, tipo;
    private String DIA_ANT;
    float SALDO = 0;
    float SAL_ANT = 0;
    float DEBITO = 0;
    float CREDITO = 0;
    float SALDO_CONTA = 0;
    float W_VALOR = 0;
    String W_CONTA, impConta;
    boolean flag = false;
    SaldoInicial saldo_inicial;
    String sql;
    AcessoDB bd;
    private DecimalFormat df;
    SimpleDateFormat data;
    String DB;

    public Relatorio2(String DB) {
        this.DB = DB;
        bd = new AcessoDB(this.DB);
        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        data = new SimpleDateFormat("dd/MM/yyyy");

    }

    public String formatar(String s) {
        String branco = "";
        String espaco = " ";

        for (int i = 1; i <= 50 - s.length(); i++) {
            branco += espaco;
        }
        return branco + s;
    }

    public List<String> extrato(String opcao, String conta, String DATAI, String DATAF) throws SQLException {

        List<String> extrato = new ArrayList();
        BLinha regLinha = new BLinha();

        saldo_inicial = new SaldoInicial(conta,this.DB);
        W_CONTA = conta;
        SALDO_CONTA = saldo_inicial.getSaldoAnterior();
        W_DT_SALDO = DtoC(saldo_inicial.getDataInicial());
        this.setDIA_ANT(DtoC(saldo_inicial.getDataInicial()));
        SAL_ANT = SALDO_CONTA;

        if (CtoD(DATAI).before(CtoD(W_DT_SALDO)) && CtoD(DATAI).equals(CtoD(W_DT_SALDO))) {
            DATAI = W_DT_SALDO;
            this.setDIA_ANT(W_DT_SALDO);
        }
        this.setATUAL(W_DT_SALDO);

        sql = "select * from movimento where " + opcao + " < '" + DATAI + "' and (ct_debito = " + conta + " or ct_credito=" + conta + ") order by " + opcao;
        try {

            pstm = bd.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();

            //        MovimentoBean li;
            //rs.first();
            while (rs.next()) {

                if (rs.getDate("dt_vencto").before(CtoD(DATAI))) {

                    if (!CtoD(this.getATUAL()).equals(rs.getDate("dt_vencto"))) {
                        SALDO = SAL_ANT;
                    }
                    if (rs.getString("ct_credito").equals(W_CONTA)) {
                        W_VALOR = rs.getFloat("valor10");
                        W_VALOR *= -1;
                        SAL_ANT += W_VALOR;
                        this.setATUAL(DtoC(rs.getDate("DT_VENCTO")));
                        W_VALOR = 0;
                    }
                    if (rs.getString("ct_debito").equals(W_CONTA)) {
                        SAL_ANT += rs.getFloat("valor10");
                        this.setATUAL(DtoC(rs.getDate("DT_VENCTO")));
                    }
                } else {

                    break;
                }
            }//while
            SALDO = SAL_ANT;
            if (CtoD(DATAI).after(CtoD(W_DT_SALDO))) {
                this.setDIA_ANT(getATUAL());
            }

            bd.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            pstm.close();
            rs.close();

        }

        extrato.add(" CONTA : " + conta + " - " + saldo_inicial.getDescricao() + " com vencimento de "
                + DATAI.substring(8, 10) + "/" + DATAI.substring(5, 7) + "/" + DATAI.substring(0, 4) + " a "
                + DATAF.substring(8, 10) + "/" + DATAF.substring(5, 7) + "/" + DATAF.substring(0, 4));
        extrato.add(" Saldo inicial em "
                + this.getDIA_ANT().substring(8, 10)
                + "/" + this.getDIA_ANT().substring(5, 7)
                + "/" + this.getDIA_ANT().substring(0, 4) + " de " + formatar(df.format(SAL_ANT)));
        W_VALOR = 0;
        DEBITO = 0;
        CREDITO = 0;
        setATUAL(DIA_ANT);

        sql = "select * from movimento where (" + opcao + " >= '" + DATAI + "' and " + opcao + " <= '" + DATAF + "') and (ct_debito = " + conta + " or ct_credito=" + conta + ") order by " + opcao;

        try {

            pstm = bd.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();

            MovimentoBean li;

            while (rs.next()) {

                li = new MovimentoBean();
                li.setDebito(rs.getString("ct_debito"));
                li.setCredito(rs.getString("ct_credito"));
                li.setEmissao(rs.getDate("dt_emissao"));
                li.setHistorico(rs.getString("hist"));
                li.setObs(rs.getString("obs"));
                li.setValor(rs.getFloat("valor10"));
                li.setVencimento(rs.getDate("dt_vencto"));

                if (!CtoD(getATUAL()).equals(li.getVencimento())) {
                    if (flag == true) {

                        extrato.add(formatar(" >>> SALDO >>> " + data.format(CtoD(getATUAL())) + " " + df.format(SALDO)));
                    }
                }
                if (li.getDebito().equals(W_CONTA)) {

                    W_VALOR = li.getValor();
                    DEBITO += W_VALOR;
                    impConta = li.getCredito();
                    tipo = "C";
                }

                if (li.getCredito().equals(W_CONTA)) {

                    W_VALOR = li.getValor();
                    W_VALOR *= -1;
                    CREDITO += W_VALOR;
                    impConta = li.getDebito();
                    tipo = "D";
                }

                extrato.add(li.linha1());
                extrato.add(li.linha2());
                System.out.println(li.linha2());
                if (li.getCredito().equals(W_CONTA)) {
                    SALDO += W_VALOR;
                }
                if (li.getDebito().equals(W_CONTA)) {
                    SALDO += W_VALOR;
                }
                flag = true;

                setATUAL(DtoC(rs.getDate("DT_VENCTO")));

            } //fim do while

            bd.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            pstm.close();
            rs.close();

        }

        extrato.add(">>>  Creditos " + df.format(DEBITO) + " <<<  >>>  Debitos " + df.format(CREDITO) + " <<< >>> Saldo final : " + df.format(SALDO));
        return extrato;
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

    public int comparaData(Calendar data1, Calendar data2) {
        int retorno = 0;
        int dia1 = data1.get(Calendar.DATE);
        int mes1 = data1.get(Calendar.MONTH);
        int ano1 = data1.get(Calendar.YEAR);
        int dia2 = data2.get(Calendar.DATE);
        int mes2 = data2.get(Calendar.MONTH);
        int ano2 = data2.get(Calendar.YEAR);

        if (ano1 < ano2) {
            retorno = -1;
        } else if (ano1 > ano2) {
            retorno = 1;
        } else {
            if (mes1 < mes2) {
                retorno = -1;
            } else if (mes1 > mes2) {
                retorno = 1;
            } else {
                if (dia1 < dia2) {
                    retorno = -1;
                } else if (dia1 > dia2) {
                    retorno = 1;
                }
            }
        }
        return retorno;
    }

    public String getATUAL() {
        return ATUAL;
    }

    public void setATUAL(String ATUAL) {
        this.ATUAL = ATUAL;
    }

    public void mostraSelecao(String sql, String msg) throws SQLException {
        System.out.println(sql);
        try {

            pstm = bd.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();

            MovimentoBean li = new MovimentoBean();
            rs.first();
            while (rs.next()) {
                li.setDebito(rs.getString("ct_debito"));
                li.setCredito(rs.getString("ct_credito"));
                li.setEmissao(rs.getDate("dt_emissao"));
                li.setHistorico(rs.getString("hist"));
                li.setObs(rs.getString("obs"));
                li.setValor(rs.getFloat("valor10"));
                li.setVencimento(rs.getDate("dt_vencto"));
                //JOptionPane.showMessageDialog(null,msg+"\n"+li.toString());
                System.out.println(msg + "\n" + li.toString());
            }

            bd.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            pstm.close();
            rs.close();

        }

    }

    public String getDIA_ANT() {
        return DIA_ANT;
    }

    public void setDIA_ANT(String DIA_ANT) {
        this.DIA_ANT = DIA_ANT;
    }
}
