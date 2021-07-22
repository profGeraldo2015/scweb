/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Geraldo
 */
public class sPesquisa extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String opcao_de_pesquisa = request.getParameter("campo");
        String acao = request.getParameter("acao");
        String conta = "";
        System.out.println("opcao de pesquisa = " + opcao_de_pesquisa + " acao " + acao);
        response.setContentType("text/html;charset=UTF-8");
        switch (acao) {

            case "pesq":

                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */

                    if (opcao_de_pesquisa.equals("dt_vencto") || opcao_de_pesquisa.equals("dt_emissao")) {

                        out.println("<input type='date' name='pesquisa' value='' size='50' />");
                        //    out.println("<button name='acao' value='Pesquisar'>Pesquisar</button>");
                    } else {
                        out.println("<input type='text' name='pesquisa' value='' size='50' />");
                    }
                    out.println("<button name='acao' value='Pesquisar'>Pesquisar</button>");

                }
                break;
            case "debito":
                conta = opcao_de_pesquisa.substring(0, 8);

                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */

                    out.println("<input type='text' name='txt_debito'value='" + conta + "' size='8' />");

                }
                break;
            case "credito":
                conta = opcao_de_pesquisa.substring(0, 8);

                try (PrintWriter out = response.getWriter()) {
                    /* TODO output your page here. You may use following sample code. */

                    out.println("<input type='text' name='txt_credito'value='" + conta + "' size='8' />");

                }
                break;

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
