/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author User
 */
public class MovimentoBean {

    private String debito;
    private String credito;
    private String historico;
    private Date emissao;
    private Date vencimento;
    private String obs;
    private float valor;
    private int idmovimento;
    private DecimalFormat df;
    private SimpleDateFormat data;

    public MovimentoBean() {
        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00");
        data = new SimpleDateFormat("dd/MM/yyyy");
    }

    /**
     * @return the debito
     */
    public String getDebito() {
        return debito;
    }

    /**
     * @param debito the debito to set
     */
    public void setDebito(String debito) {
        this.debito = debito;
    }

    @Override
    public String toString() {
        return "MovimentoBean{" + "debito=" + debito + ", credito=" + credito + ", historico=" + historico + ", emissao=" + emissao + ", vencimento=" + vencimento + ", obs=" + obs + ", valor=" + valor + '}';
    }

    public String linha1() {
        return "| " + data.format(emissao) + " | " + debito + " | " + credito + " | " + data.format(vencimento) + " | " + formatar(historico, "d") + " |" + formatar(obs, "d") + " | " + formatar(df.format(valor), "e") + " |";
    }

    public String linha2() {
        return formatar(obs.trim(), "d") + " | " + formatar(df.format(valor), "e") + " |";
    }

    public String formatar(String s, String tipo) {
        String branco = "";
        String espaco = " ";

        for (int i = 1; i <= 35 - s.length(); i++) {
            branco += espaco;
        }
        if (tipo.equals("d")) {
            return s + branco;
        } else {
            return branco + s;
        }
    }

    /**
     * @return the credito
     */
    public String getCredito() {
        return credito;
    }

    /**
     * @param credito the credito to set
     */
    public void setCredito(String credito) {
        this.credito = credito;
    }

    /**
     * @return the historico
     */
    public String getHistorico() {
        return historico;
    }

    /**
     * @param historico the historico to set
     */
    public void setHistorico(String historico) {
        this.historico = historico;
    }

    /**
     * @return the emissao
     */
    public Date getEmissao() {
        return emissao;
    }

    public String getEmissa() {
        return data.format(emissao);
    }

    /**
     * @return the vencimento
     */
    public Date getVencimento() {
        return vencimento;
    }

    public String getVencto() {
        return data.format(vencimento);
    }

    /**
     *
     * /
     *
     **
     * @param emissao the emissao to set
     */
    public void setEmissao(Date emissao) {
        this.emissao = emissao;
    }

    /**
     * @param vencimento the vencimento to set
     */
    public void setVencimento(Date vencimento) {
        this.vencimento = vencimento;
    }

    /**
     * @return the obs
     */
    public String getObs() {
        return obs;
    }

    /**
     * @param obs the obs to set
     */
    public void setObs(String obs) {
        this.obs = obs;
    }

    /**
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

    public String getV() {
        return df.format(valor);
    }

    /**
     * @param valor the valor to set
     */
    public void setValor(float valor) {
        this.valor = valor;
    }

    public void cadastrarMovimento() {
        String sql = "insert into movimento (dt_emissao,ct_debito,ct_credito,hist,valorus,obs,dt_vencto,) values('"
                + getEmissao() + "','" + getDebito() + "','" + getCredito() + "','" + getHistorico() + "','"
                + getValor() + "','" + getObs() + "','" + getVencimento() + "')";

        AcessoDB conexao = new AcessoDB();
        conexao.executaSqlUpdate(sql);
        conexao.desconectar();
    }

    public int getIdmovimento() {
        return idmovimento;
    }

    public void setIdmovimento(int idmovimento) {
        this.idmovimento = idmovimento;
    }
}
