/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Geraldo
 */
public class Datas {

    private Date dataHoje;
    private Date dataInicial;
    private Date dataFinal;

    private DecimalFormat df;
    private SimpleDateFormat data;

    public Datas() {
        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        data = new SimpleDateFormat("dd/MM/yyyy");
        Date datahoje = new Date();
        this.dataHoje = datahoje;
        this.dataInicial = datahoje;
        this.dataFinal = datahoje;

    }

    @Override
    public String toString() {
        return "Datas{" + "dataHoje=" + dataHoje + ", dataInicial=" + dataInicial + ", dataFinal=" + dataFinal + '}';
    }

    public Date getDataHoje() {
        return dataHoje;
    }

    public String getDataH() {
        return data.format(dataHoje);
    }

    public void setDataHoje(Date dataHoje) {
        this.dataHoje = dataHoje;
    }

    public Date getDataInicial() {
        return dataInicial;
    }

    public String getDataF() {
        return data.format(dataFinal);
    }

    public String getDataI() {
        return data.format(dataInicial);
    }

    public void setDataInicial(Date dataInicial) {
        this.dataInicial = dataInicial;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
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
