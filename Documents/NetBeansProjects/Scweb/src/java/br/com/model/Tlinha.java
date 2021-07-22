/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.model;

/**
 *
 * @author Geraldo
 */
public class Tlinha {

    private String tipo;
    private String content;

    public Tlinha() {
    }

    public Tlinha(String tipo, String content) {
        this.tipo = tipo;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return "Tlinha{" + "tipo=" + tipo + ", content=" + content + '}';
    }

}
