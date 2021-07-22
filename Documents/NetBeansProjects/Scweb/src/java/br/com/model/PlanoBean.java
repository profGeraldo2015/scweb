package br.com.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class PlanoBean {

    private Integer idPLANO;
    private String numero;
    private String descricao;
    private float saldo_inic;
    private Date dt_saldo;
    private float saldo_iniv;
    private float c_saivus;
    private float c_saieus;
    private Date dt_saus;
    private DecimalFormat df;
    private SimpleDateFormat data;

    public PlanoBean() {
        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        data = new SimpleDateFormat("dd/MM/yyyy");
    }

    @Override
    public String toString() {
        return "PlanoBean{" + "idPLANO=" + idPLANO + ", numero=" + numero + ", descricao=" + descricao + ", saldo_inic=" + saldo_inic + ", dt_saldo=" + dt_saldo + ", saldo_iniv=" + saldo_iniv + ", c_saivus=" + c_saivus + ", c_saieus=" + c_saieus + ", dt_saus=" + dt_saus + '}';
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public float getSaldo_inic() {
        return saldo_inic;
    }

    public String getSaldo_inic2() {
        return df.format(saldo_inic);
    }

    public void setSaldo_inic(float saldo_inic) {
        this.saldo_inic = saldo_inic;
    }

    public Date getDt_saldo() {
        return dt_saldo;
    }

    public String getDt_saldo2() {
        return data.format(dt_saldo);
    }

    public void setDt_saldo(Date dt_saldo) {
        this.dt_saldo = dt_saldo;
    }

    public float getSaldo_iniv() {
        return saldo_iniv;
    }

    public String getSaldo_iniv2() {
        return df.format(saldo_iniv);
    }

    public void setSaldo_iniv(float saldo_iniv) {
        this.saldo_iniv = saldo_iniv;
    }

    public float getC_saivus() {
        return c_saivus;
    }

    public String getC_saivus2() {
        return df.format(c_saivus);
    }

    public void setC_saivus(float c_saivus) {
        this.c_saivus = c_saivus;
    }

    public float getC_saieus() {
        return c_saieus;
    }

    public String getC_saieus2() {
        return df.format(c_saieus);
    }

    public void setC_saieus(float c_saieus) {
        this.c_saieus = c_saieus;
    }

    public Date getDt_saus() {
        return dt_saus;
    }

    public String getDt_saus2() {
        return data.format(dt_saus);
    }

    public void setDt_saus(Date dt_saus) {
        this.dt_saus = dt_saus;
    }

    public Integer getIdPLANO() {
        return idPLANO;
    }

    public void setIdPLANO(Integer idPLANO) {
        this.idPLANO = idPLANO;
    }

}
