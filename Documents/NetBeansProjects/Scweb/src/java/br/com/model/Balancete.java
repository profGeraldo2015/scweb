/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Geraldo balancete web
 */
public class Balancete {

    PreparedStatement pstm;
    ResultSet rs;
    List<BLinha> balancete;
    boolean FLAG = true;
    //     T         = 0 ;
    //     Registro  = 0 ;
    //     P_Proc    = 0 ;
    //     TEMPO     = .t.;
    //     TEMP_1R   = 0;
    //     TEMP_QT_P = 0;
    //     TEMP_RES  = 0;
    //     TEMP_TOT  = 0;
    //     TEMP_INI  = 0;
    //     TEMP_FIN  = 0;
    float debito_ana = 0;
    float credito_ana = 0;
    float sal_ant = 0;
    float w_saldo = 0;
    float W_VALOR = 0;
    String BANDEIRA = "  ";
    String MENSA1 = "Calculando Saldo anterior...      ";
    String mensa2 = "Calculando D‚bitos...             ";
    String mensa3 = "Calculando Cr‚ditos...            ";
    String mensa4 = "Verificando Movimento no per¡odo  ";
    String mensa5 = "Gravando no Arquivo Tempor rio    ";
    String W_CONTA, CT_DEB, CT_CRED = "        ";
    int posicao = 1;
    //VAL1 := VAL2 := 0,
    //String DATA = CTOD(SPACE(8));
    //flot W_VAL = NUM_CON = 0;
    String sql;
    AcessoDB bd;
    private DecimalFormat df;
    SimpleDateFormat data;
    BLinha linha;

    public List<BLinha> getBalancete() {
        return balancete;
    }

    public void setBalancete(List<BLinha> balancete) {
        this.balancete = balancete;
    }

//memvar x,SALDO,CREDITO,DEBITO,aCONTA,aSALANT,aDEBITO,aCREDITO,CONTA
//PUBLIC X,CONTA := ARRAY(200,4,4)
    //SaldoInicial saldo_inicial;
    public Balancete(String DB) {
        this.balancete = new ArrayList();

        bd = new AcessoDB(DB);
        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        data = new SimpleDateFormat("dd/MM/yyyy");

    }

    public List<BLinha> GeraBalancete(String opcao, String DATAI, String DATAF, String DB) throws SQLException {

        //List<BLinha> balancete = new ArrayList();
        System.out.println(opcao + "DI " + DATAI + "DF" + DATAF);
        PlanoDB plano = new PlanoDB(DB);
        List<PlanoBean> objPlano = plano.carregaComboPlano();

        for (PlanoBean objPlano1 : objPlano) {
            linha = new BLinha();
            linha.setConta(objPlano1.getNumero());
            linha.setDescricao(linha.formatDesc(objPlano1.getDescricao()));
            linha.setSaldoAnterior(objPlano1.getSaldo_iniv());
            linha.setDebito(0);
            linha.setCredito(0);
            linha.setSaldo(0);
            //if( !liCbxPlano.get(i).getNumero().substring(4, 4).equals("0000")){
            //System.out.println(liCbxPlano.get(i).getNumero().substring(4, 4));
            this.balancete.add(linha);
            //}
            //cbCredito.addItem(liCbxPlano.get(i).getNumero()+ " - "+liCbxPlano.get(i).getDescricao());
        }

        //for (BLinha balancete1 : balancete) {
        //    System.out.println("1" + balancete1.toString());
        //}
        //sql="select * from movimento where "+opcao+" < '"+DATAI+"' order by "+opcao;
        sql = "select * from movimento order by " + opcao;

        try {

            pstm = bd.conectar().prepareStatement(sql);
            rs = pstm.executeQuery();
            //System.out.println("Conta "+Conta + " valor "+valor+" posicao "+ posicao);
            //System.out.println(sql);
            //System.out.println("datai "+DATAI);
            //System.out.println("dataF "+DATAF);

            while (rs.next()) {
                //System.out.println("1 "+rs.getString("ct_debito")+" "
                // + rs.getString("ct_credito")+ " "+ rs.getFloat("valor10")+" "+rs.getDate("dt_vencto"));
                try {
                    if (rs.getDate("dt_vencto").before(CtoD(DATAI))) {
                        W_VALOR = rs.getFloat("valorus");
                       //System.out.println(" w _ valor "+W_VALOR);

                        //MOST()
                        CT_DEB = rs.getString("ct_debito");
                        ACUMULA(CT_DEB, W_VALOR, 2);
                        //@ 24,13 SAY MENSA1
                        DESCARREGA(CT_DEB, W_VALOR, 2);
                        //@ 24,13 SAY MENSA2
                        CT_CRED = rs.getString("ct_credito");
                        SUBTRAI(CT_CRED, W_VALOR, 2);
                        //@ 24,13 SAY MENSA3
                        DESCARREGA(CT_CRED, -1 * W_VALOR, 2);
                        //@ 24,13 SAY MENSA1
                    }//else{
                    //System.out.println(rs.getDate("dt_vencto")+ " maior que "+DATAI);

                    //}
                    W_VALOR = 0;

                    //processa periodo
                    if ((rs.getDate("dt_vencto").after(CtoD(DATAI)) || rs.getDate("dt_vencto").equals(CtoD(DATAI))) && (rs.getDate("dt_vencto").before(CtoD(DATAF)) || rs.getDate("dt_vencto").equals(CtoD(DATAF)))) {

                    //System.out.println("2 "+rs.getString("ct_debito")+" "
                        //+ rs.getString("ct_credito")+ " "+ rs.getFloat("valor10")+" "+rs.getDate("dt_vencto"));
                        W_VALOR = rs.getFloat("valorus");

                    //          MOST()
                        //          @ 24,13 SAY MENSA4
                        CT_DEB = rs.getString("ct_debito");
                        ACUMULA(CT_DEB, W_VALOR, 3);
                        //        @ 24,13 SAY MENSA2
                        DESCARREGA(CT_DEB, W_VALOR, 3);
                        CT_CRED = rs.getString("ct_credito");
                        //          @ 24,13 SAY MENSA3
                        SUBTRAI(CT_CRED, W_VALOR, 4);
                        //         @ 24,13 SAY MENSA3
                        DESCARREGA(CT_CRED, -1 * W_VALOR, 4);
                        W_VALOR = 0;

                    }//fim do if
                } catch (NullPointerException npe) {
                    System.out.println(npe.getMessage());
                }
            }//while
            //

            bd.desconectar();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

            pstm.close();
            rs.close();

        }
        for (int x = 0; x < balancete.size(); x++) {
            balancete.get(x).setSaldo(balancete.get(x).getSaldoAnterior()
                    + balancete.get(x).getDebito()
                    + balancete.get(x).getCredito());
        }
        // this.mostraBala();
        return balancete;
    }

    public void mostraBala() {
        for (int i = 0; i <= balancete.size(); i++) {
            System.out.println(balancete.get(i).toString());
        }
    }

    public void DESCARREGA(String conta, float valor, int posicao) {

        //if ( null != conta.substring(0,1) )
        switch (conta.substring(0, 1)) {

            case "1": //100

                ACUMULA("10000000", valor, posicao);

                switch (conta.substring(1, 2)) {
                    case "1"://110

                        ACUMULA("11000000", valor, posicao);

                        switch (conta.substring(2, 3)) {
                            case "1":   // 111
                                ACUMULA("11100000", valor, posicao);
                                break;
                            case "2":   // 112
                                ACUMULA("11200000", valor, posicao);
                                switch (conta.substring(3, 4)) {
                                    case "1":    // 1121
                                        ACUMULA("11210000", valor, posicao);
                                        break;
                                }
                                break;
                            case "3":         // 113
                                ACUMULA("11300000", valor, posicao);
                                break;
                        }
                        break;
                    case "2"://120
                        ACUMULA("12000000", valor, posicao);
                        break;

                }
                break;
            case "2":
                ACUMULA("20000000", valor, posicao);

                switch (conta.substring(1, 2)) {//DO CASE

                    case "1":            // 210

                        ACUMULA("21000000", valor, posicao);

                        switch (conta.substring(2, 3)) {

                            case "1":    // 211
                                ACUMULA("21100000", valor, posicao);
                                break;
                            case "2":    // 212
                                ACUMULA("21200000", valor, posicao);
                                break;
                            case "3":    // 213
                                ACUMULA("21300000", valor, posicao);
                                break;
                            case "4":    // 214
                                ACUMULA("21400000", valor, posicao);
                                break;
                            case "5":    // 215
                                ACUMULA("21500000", valor, posicao);
                                break;

                        }//END CASE
                        break;

                    case "2":            // 220

                        ACUMULA("22000000", valor, posicao);
                        break;

                }//END CASE
                break;

            case "3":

                ACUMULA("30000000", valor, posicao);

                switch (conta.substring(1, 2)) {//DO CASE

                    case "1":// CASE SUBSTR(w_conta,2,1) = '1'           // 310

                        ACUMULA("31000000", valor, posicao);

                        switch (conta.substring(2, 3)) {//DO CASE

                            case "1":   // 311
                                ACUMULA("31100000", valor, posicao);
                                break;
                        }//END CASE
                        break;
                    case "2": //CASE SUBSTR(w_conta,2,1) = '2'           // 320

                        ACUMULA("32000000", valor, posicao);

                        switch (conta.substring(2, 3)) {//DO CASE
                            case "1":   // 321
                                ACUMULA("32100000", valor, posicao);
                                break;
                        }//END CASE
                        break;

                    case "3":           // 330

                    // SALDO[23] := SALDO[19] + SALDO[24] + SALDO[21]
                    //                             SUM := 0
                    //                           SUM := SOMA(CONTA,"31000000",POS)+;
                    //                                SOMA(CONTA,"32000000",POS)+;
                    //                              SOMA(CONTA,"34000000",POS)
                    //                     ACUMULA(CONTA,"33000000",SUM,POS)
                    case "4":           // 340

                        ACUMULA("34000000", valor, posicao);

                        switch (conta.substring(2, 3)) {//DO CASE

                            case "1":   // 341
                                ACUMULA("34100000", valor, posicao);
                                break;
                            case "2":   // 342
                                ACUMULA("34200000", valor, posicao);
                                break;
                        }//END CASE
                        break;

                    case "5":           // 350

                        ACUMULA("35000000", valor, posicao);

                        switch (conta.substring(2, 3)) {//DO CASE
                            case "1":   // 351
                                ACUMULA("35100000", valor, posicao);
                                break;
                            case "2":   // 352
                                ACUMULA("35200000", valor, posicao);
                                break;
                        }//END CASE
                        break;

                }//END CASE

                //  saldo[23] := saldo[19] + saldo[24] + SALDO[21] // 330
                float SUM = 0;
                SUM = SOMA("31000000", posicao)
                        + SOMA("32000000", posicao)
                        + SOMA("34000000", posicao);

                ACUMULA("33000000", SUM, posicao);

                break;
        }
    }

    public void SUBTRAI(String Conta, float valor, int posicao) {
        //System.out.println("Conta "+Conta + " valor "+valor+" posicao "+ posicao);
        for (BLinha balancete1 : balancete) {
            if (balancete1.getConta().equals(Conta)) {
                if (posicao == 2) {
                    balancete1.setSaldoAnterior(balancete1.getSaldoAnterior() - valor);
                    //break;
                }
                if (posicao == 3) {
                    balancete1.setDebito(balancete1.getDebito() - valor);
                    //  break;
                }
                if (posicao == 4) {
                    balancete1.setCredito(balancete1.getCredito() - valor);
                    //    break;
                }
                balancete1.setSaldo(balancete1.getSaldoAnterior()
                        + balancete1.getDebito()
                        + balancete1.getCredito());
                break;
            }
        }

    }

    public void ACUMULA(String Conta, float valor, int posicao) {
        //System.out.println("Conta "+Conta + " valor "+valor+" posicao "+ posicao);
        int ind = 0;
        int tam;
        tam = this.balancete.size();

        //System.out.println("tam "+ tam);
        for (ind = 0; ind < tam; ind++) {
            //if( balancete.contains(Conta)){}
//            System.out.println(" bala conta " + balancete.get(ind).getConta() + " Conta "+Conta);
            if (balancete.get(ind).getConta().equals(Conta)) {
                if (posicao == 2) {
                    //valor +=  balancete.get(ind).getSaldoAnterior();
                    //balancete.get(ind).setSaldoAnterior(valor);break;

                    balancete.get(ind).setSaldoAnterior((balancete.get(ind).getSaldoAnterior() + valor));//break;
                }
                if (posicao == 3) {
                    balancete.get(ind).setDebito(balancete.get(ind).getDebito() + valor);//break;
                    //valor +=  balancete.get(ind).getDebito();
                    //balancete.get(ind).setDebito(valor);break;

                }
                if (posicao == 4) {
                    balancete.get(ind).setCredito(balancete.get(ind).getCredito() + valor);//break;
                    //valor +=  balancete.get(ind).getCredito();
                    //balancete.get(ind).setCredito(valor);break;

                }
                balancete.get(ind).setSaldo(balancete.get(ind).getSaldoAnterior()
                        + balancete.get(ind).getDebito()
                        + balancete.get(ind).getCredito());
                break;
            }
            //System.out.println(balancete.get(ind).getCredito());

        }

    }

    public float SOMA(String Conta, int posicao) {
        float soma = 0;
        for (int i = 0; i < balancete.size(); i++) {

            if (balancete.get(i).getConta().equals(Conta)) {
                if (posicao == 2) {
                    soma = balancete.get(i).getSaldoAnterior();//break;

                }
                if (posicao == 3) {
                    soma = balancete.get(i).getDebito();//break;
                }
                if (posicao == 4) {
                    soma = balancete.get(i).getCredito();//break;
                }
                break;
            }

        }
        return soma;
    }

    public String DtoC(Date datad) {

        String datasai = null;
        try {

            Date data = datad;
            //arqui vamos configurar o simple date formate
            // d = dia, M (maiusculo) = mes e y = ano (obs: m = minutos)
            //SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
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
