package br.com.model;

import java.awt.Font;
import java.text.DecimalFormat;

/**
 *
 * @author Geraldo
 */
public class BLinha {
    private String conta;
    private String descricao;
    private float saldoAnterior;
    private float debito;
    private float credito;
    private float saldo;
    private DecimalFormat df; 
    private String h1;
    private String h2;
    private String d1;
    private String d2;
    private String f1;
    private String f2;
  
    public BLinha(String conta, float saldoAnterior, float debito, float credito, float saldo) {
        this.conta = conta;
        this.saldoAnterior = saldoAnterior;
        this.debito = debito;
        this.credito = credito;
        this.saldo = saldo;
        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00"); 
        Font fonte = new Font("Arial", Font.ITALIC, 20);
    }

    public BLinha() {
        df = new DecimalFormat();
        df.applyPattern("R$ ###,###,##0.00"); 
         Font fonte = new Font("Arial", Font.ITALIC, 20);
         
    }

    public String getConta() {
        return conta;
    }

    public void setConta(String conta) {
        this.conta = conta;
    }

    public float getSaldoAnterior() {
        return saldoAnterior;
    }
    public String getSA() {
        return formatar(df.format(saldoAnterior));
    }

    public void setSaldoAnterior(float saldoAnterior) {
        this.saldoAnterior = saldoAnterior;
    }

    public float getDebito() {
        return debito;
    }
    public String getD() {
        return formatar(df.format(debito));
    }

    public void setDebito(float debito) {
        this.debito = debito;
    }

    public float getCredito() {
        return credito;
    }
    public String getC() {
        return formatar(df.format(credito));
    }
    public void setCredito(float credito) {
        this.credito = credito;
    }

    public float getSaldo() {
        return saldo;
    }
    public String getS() {
        return formatar(df.format(saldo));
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        
        return  "| "+conta + " | " + descricao + " | "
                + formatar(df.format(saldoAnterior)) + " | " 
                + formatar(df.format(debito)) + " | "
                + formatar(df.format(credito)) + " | "
                + formatar(df.format(saldo)) + '|';
    }
     public String formatar(String s){
        String branco = "";
        String espaco = " ";
        
        for(int i = 1 ; i <= 15-s.length(); i++){
            branco += espaco;
        }
        return branco+s;
    }    
     
     public String formatDesc(String s){
        String branco = "";
        String espaco = " ";
        
        for(int i = 1 ; i <= 35-s.length(); i++){
            branco += espaco;
        }
        return s+branco;
    }    

    public String getH1() {
        return h1;
    }

    public void setH1(String h1) {
        this.h1 = h1;
    }

    public String getH2() {
        return h2;
    }

    public void setH2(String h2) {
        this.h2 = h2;
    }

    public String getD1() {
        return d1;
    }

    public void setD1(String d1) {
        this.d1 = d1;
    }

    public String getD2() {
        return d2;
    }

    public void setD2(String d2) {
        this.d2 = d2;
    }

    public String getF1() {
        return f1;
    }

    public void setF1(String f1) {
        this.f1 = f1;
    }

    public String getF2() {
        return f2;
    }

    public void setF2(String f2) {
        this.f2 = f2;
    }
}
