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
import java.sql.SQLException;
import java.util.ArrayList;
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
public class ServletRel extends HttpServlet {

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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        DB = request.getServletContext().getInitParameter("DB");
        String acao = request.getParameter("acao");
        String opcao, conta, inicio, fim;

        switch (acao) {
            case ("Diario por Conta"):
                // chama tela de extrato
                request.getRequestDispatcher("listagem.jsp").forward(request, response);
                break;
            case ("Demonstrativo Contabil"):
                // chama tela de extrato
                request.getRequestDispatcher("demonstrativo.jsp").forward(request, response);
                break;

            case ("Cancelar"):
                //volta ao menu principal
                request.getRequestDispatcher("menu2.jsp").forward(request, response);
                break;
            case ("Imprimir Diario"):
                //recebe parametros de escolha do periodo e conta
                //monta uma lista e devolve  para tele de impressao
                opcao = request.getParameter("opcao_data");
                //conta = request.getParameter("opcao_conta");
                if (request.getParameter("txt_conta").equals("")) {
                    conta = request.getParameter("opcao_conta");
                } else {
                    conta = request.getParameter("txt_conta");
                }
                inicio = request.getParameter("inicio");
                fim = request.getParameter("fim");
                Relatorio listagem = new Relatorio(DB);
                List<MovimentoBean> lista = listagem.listagem(opcao, conta, inicio, fim);
                request.setAttribute("lista", lista);
                request.setAttribute("inicio", inicio);
                request.setAttribute("fim", fim);

                request.getRequestDispatcher("implistagem.jsp").forward(request, response);
                break;
            case ("Imprimir Diario Geral"):
                System.out.println("entrei no servelet");
                //recebe parametros de escolha do periodo e conta
                //monta uma lista e devolve  para tele de impressao
                opcao = request.getParameter("opcao_data");
                conta = request.getParameter("opcao_conta");
                inicio = request.getParameter("inicio");
                fim = request.getParameter("fim");
                Relatorio listagemGeral = new Relatorio(DB);
                List<MovimentoBean> listaGeral = listagemGeral.listagem(opcao, inicio, fim);

                request.setAttribute("listaGeral", listaGeral);
                request.getRequestDispatcher("implistagemgeral.jsp").forward(request, response);
                break;

            case ("Voltar"):
                request.getRequestDispatcher("menu2.jsp").forward(request, response);
                break;
            case ("Extrato Contabil"):
                // chama tela de extrato
                request.getRequestDispatcher("extrato.jsp").forward(request, response);
                break;
            case ("Balancete Contabil"):
                // chama tela de extrato
                request.getRequestDispatcher("balancete.jsp").forward(request, response);
                break;
            case ("Imprimir Extrato"):
                //recebe parametros de escolha do periodo e conta
                //monta uma lista e devolve  para tele de impressao

                float saldo;
                opcao = request.getParameter("opcao_data");

                if (request.getParameter("txt_conta").equals("")) {
                    conta = request.getParameter("opcao_conta");
                } else {
                    conta = request.getParameter("txt_conta");
                }
                inicio = request.getParameter("inicio");
                fim = request.getParameter("fim");

                Relatorio3 report = new Relatorio3(DB);

                List<Tlinha> listaextrato = new ArrayList();

                try {

                    listaextrato = report.extrato(opcao, conta, inicio, fim);

                } catch (SQLException ex) {
                    Logger.getLogger(ServletRel.class.getName()).log(Level.SEVERE, null, ex);
                }

                request.setAttribute("listaextrato", listaextrato);
                request.getRequestDispatcher("impextrato3.jsp").forward(request, response);
                //este de baixo e o novo extrato...
                //request.getRequestDispatcher("impextrato3.jsp").forward(request, response);

                break;
            case ("Imprimir Balancete"):
                //recebe parametros de escolha do periodo 
                //monta uma lista e devolve  para tele de impressao

                opcao = request.getParameter("opcao_data");
                //conta  = request.getParameter("opcao_conta");
                inicio = request.getParameter("inicio");
                fim = request.getParameter("fim");
                Balancete bala = new Balancete(DB);
                //report.extrato(conta, inicio, fim);

                List<BLinha> listabala = null;

                try {

                    listabala = bala.GeraBalancete(opcao, inicio, fim,DB);

                } catch (SQLException ex) {
                    Logger.getLogger(ServletRel.class.getName()).log(Level.SEVERE, null, ex);
                }
                //for(int index = 0 ; index < listabala.size() ; index++){
                //System.out.println(listabala.get(index).toString());
                //}
                request.setAttribute("listabala", listabala);
                request.getRequestDispatcher("impbala.jsp").forward(request, response);
                break;
            case ("Imprimir Demonstrativo"):
                //recebe parametros de escolha do periodo 
                //monta uma lista e devolve  para tele de impressao

                opcao = request.getParameter("opcao_data");
                //conta  = request.getParameter("opcao_conta");
                inicio = request.getParameter("inicio");
                fim = request.getParameter("fim");
                Balancete demo = new Balancete(DB);
                //report.extrato(conta, inicio, fim);

                List<BLinha> listademo = null;

                try {

                    listademo = demo.GeraBalancete(opcao, inicio, fim,DB);

                } catch (SQLException ex) {
                    Logger.getLogger(ServletRel.class.getName()).log(Level.SEVERE, null, ex);
                }
                //for(int index = 0 ; index < listabala.size() ; index++){
                //System.out.println(listabala.get(index).toString());
                //}
                request.setAttribute("listademo", listademo);
                request.getRequestDispatcher("impdemo.jsp").forward(request, response);
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
