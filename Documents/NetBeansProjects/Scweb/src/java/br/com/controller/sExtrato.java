/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.BLinha;
import br.com.model.Balancete;
import br.com.model.MovimentoBean;
import br.com.model.Relatorio;
import br.com.model.Relatorio3;
import br.com.model.Tlinha;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geraldo
 */
public class sExtrato extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    String DB;
    String opcao, conta, inicio, fim;
    float saldo;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            DB = request.getServletContext().getInitParameter("DB");

            String acao = request.getParameter("acao");

            opcao = request.getParameter("opcao_data");

            System.out.println("txt_conta" + request.getParameter("txt_conta"));
            System.out.println("opcao_conta" + request.getParameter("opcao_conta"));

            if (request.getParameter("txt_conta") == null || request.getParameter("opcao_conta") == null) {

            } else {
                if (request.getParameter("txt_conta").equals("")) {
                    conta = request.getParameter("opcao_conta");
                } else {
                    conta = request.getParameter("txt_conta");
                }
            }
            inicio = request.getParameter("inicio");
            fim = request.getParameter("fim");

            switch (acao) {

                case ("Extrato"):
                    //recebe parametros de escolha do periodo e conta
                    //monta uma lista e devolve  para tele de impressao

                    Relatorio3 report = new Relatorio3(DB);

                    List<Tlinha> listaextrato = new ArrayList();

                    try {

                        listaextrato = report.extrato(opcao, conta, inicio, fim);

                    } catch (SQLException ex) {
                        Logger.getLogger(ServletRel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    List< Tlinha> result = (List<Tlinha>) listaextrato;
                    Iterator iterator = result.iterator();
                    while (iterator.hasNext()) {

                        Tlinha lancto = (Tlinha) iterator.next();

                        out.println("<tr>");

                        if (lancto.getTipo().equals("h1")) {
                            out.print("<td align='center' style='background-color:goldenrod'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                        } else if (lancto.getTipo().equals("h2")) {
                            out.print("<td align='right' style='background-color:#fffacd'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                        } else if (lancto.getTipo().equals("d1")) {
                            out.print("<td align='left'style='background-color:#fffacd'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                        } else if (lancto.getTipo().equals("d2")) {
                            out.print("<td align='right'style='background-color:goldenrod'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                        } else if (lancto.getTipo().equals("f1")) {
                            out.print("<td align='right'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                        } else if (lancto.getTipo().equals("f2")) {
                            out.print("<td align='right'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                        }
                        out.println("</tr>");
                    }
                    break;

                case "Diario":

                    Relatorio listagem = new Relatorio(DB);

                    List<MovimentoBean> listadiario = new ArrayList();

                    listadiario = listagem.listagem(opcao, conta, inicio, fim);

                    out.println("<tr>");
                    out.println("<th>Débito </th>");
                    out.println("<th>Crédito</th>");
                    out.println("<th>Data</th>");
                    out.println("<th>Histórico</th>");
                    out.println("<th>Observação</th>");
                    out.println("<th>Valor</th>");
                    out.println("<th>Vencimento</th>");
                    out.println("</tr>");

                    int linha = 0;
                    while (linha <= listadiario.size()) {

                        out.println("<tr>");
                        out.println("<td>" + listadiario.get(linha).getDebito() + "</td>");
                        out.println("<td>" + listadiario.get(linha).getCredito() + "</td>");
                        out.println("<td>" + listadiario.get(linha).getEmissa() + "</td>");
                        out.println("<td>" + listadiario.get(linha).getHistorico() + "</td>");
                        out.println("<td>" + listadiario.get(linha).getObs() + "</td>");
                        out.println("<td align = 'right'>" + listadiario.get(linha).getV() + "</td>");
                        out.println("<td align = 'right'>" + listadiario.get(linha).getVencto() + "</td>");
                        out.println("</tr>");
                        linha++;

                    }

                    break;

                case "Diario Geral":

                    Relatorio listagemGeral = new Relatorio(DB);
                    List<MovimentoBean> listaGeral = listagemGeral.listagem(opcao, inicio, fim);
                    out.println("<tr>");
                    out.println("<th>Débito </th>");
                    out.println("<th>Crédito</th>");
                    out.println("<th>Data</th>");
                    out.println("<th>Histórico</th>");
                    out.println("<th>Observação</th>");
                    out.println("<th>Valor</th>");
                    out.println("<th>Vencimento</th>");
                    out.println("</tr>");
                    int linha3 = 0;
                    while (linha3 <= listaGeral.size()) {
                        out.println("<tr>");
                        out.println("<td>" + listaGeral.get(linha3).getDebito() + "</td>");
                        out.println("<td>" + listaGeral.get(linha3).getCredito() + "</td>");
                        out.println("<td>" + listaGeral.get(linha3).getEmissa() + "</td>");
                        out.println("<td>" + listaGeral.get(linha3).getHistorico() + "</td>");
                        out.println("<td>" + listaGeral.get(linha3).getObs() + "</td>");
                        out.println("<td align = 'right'>" + listaGeral.get(linha3).getV() + "</td>");
                        out.println("<td align = 'right'>" + listaGeral.get(linha3).getVencto() + "</td>");
                        out.println("</tr>");
                        linha3++;
                    }
                    break;
                case "Balancete":

                    Balancete bala = new Balancete(DB);
                    //report.extrato(conta, inicio, fim);

                    List<BLinha> listabala = null;

                    try {

                        listabala = bala.GeraBalancete(opcao, inicio, fim, DB);

                    } catch (SQLException ex) {
                        Logger.getLogger(ServletRel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    out.println("<tr>");
                    out.println("<th>CONTA</th>");
                    out.println("<th>DESCRIÇÃO</th>");
                    out.println("<th>SALDO ANTERIOR</th>");
                    out.println("<th>DÉBITOS</th>");
                    out.println("<th>CRÉDITOS</th>");
                    out.println("<th>SALDO ATUAL</th>");

                    out.println("</tr>");

                    List<BLinha> resulta = (List<BLinha>) listabala;
                    int linha2 = 1;
                    for (BLinha lin : resulta) {
                        if (linha2 % 2 == 0) {
                            out.println("<tr style='color:#333333;background-color:#FFFBD6;'>");
                        } else {
                            out.println("<tr style='color:#333333;background-color:white;'>");

                        }

                        out.println("<td>" + lin.getConta()
                                + "</td><td>" + lin.getDescricao()
                                + "</td><td align='right'> " + lin.getSA()
                                + "</td><td align='right'>" + lin.getD()
                                + "</td><td align='right'>" + lin.getC()
                                + "</td><td align='right'>" + lin.getS() + "</td>");
                        linha2++;

                    }

                    out.println("</tr>");

                    break;
                case "Demonstrativo":

                    Balancete demo = new Balancete(DB);
                    //report.extrato(conta, inicio, fim);

                    List<BLinha> listademo = null;

                    try {

                        listademo = demo.GeraBalancete(opcao, inicio, fim, DB);

                    } catch (SQLException ex) {
                        Logger.getLogger(ServletRel.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    out.println("<tr>");
                    out.println("<th>CONTA</th>");
                    out.println("<th>DESCRIÇÃO</th>");
                    out.println("<th>SALDO ANTERIOR</th>");
                    out.println("<th>DÉBITOS</th>");
                    out.println("<th>CRÉDITOS</th>");
                    out.println("<th>SALDO ATUAL</th>");

                    out.println("</tr>");

                    List<BLinha> res = (List<BLinha>) listademo;
                    int linha22 = 1;
                    for (BLinha lin : res) {
                        if (linha22 % 2 == 0) {
                            out.println("<tr style='color:#333333;background-color:#FFFBD6;'>");
                        } else {
                            out.println("<tr style='color:#333333;background-color:white;'>");
                        }
                        //out.println("<td>linha de teste</td>");
                        if (lin.getConta().substring(4, 8).equals("0000")) {
                            out.println("<td>" + lin.getConta() + "</td><td>" + lin.getDescricao()
                                      + "</td><td align='right'>" + lin.getSA()
                                      + "</td><td align='right'>" + lin.getD()
                                      + "</td><td align='right'>" + lin.getC()
                                      + "</td><td align='right'>" + lin.getS() + "</td>");
                            linha22++;
                        }
                    }

                    out.println("</tr>");

                    break;

            }

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
