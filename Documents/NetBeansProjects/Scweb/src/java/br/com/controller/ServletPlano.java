/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.MovimentoBean;
import br.com.model.Pagina;
import br.com.model.PaginaPlano;
import br.com.model.PlanoBean;
import br.com.model.PlanoDB;
import java.io.IOException;
import static java.lang.System.out;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import oracle.jrockit.jfr.parser.ParseException;

/**
 *
 * @author User
 */
public class ServletPlano extends HttpServlet {

    private PlanoBean plano;
    private int offset = 15;
    private int qtd = 0;
    private int numPagina = 1;
    private int limite = 12;
    private int totPagina = 1;
    PaginaPlano objpagina = null;
    String DB;

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
            throws ServletException, IOException, java.text.ParseException, Exception {

        DB = request.getServletContext().getInitParameter("DB");
        String acao = request.getParameter("acao");
        String numero = request.getParameter("numero");
        String opcao_de_pesquisa = request.getParameter("opcao_pesquisar");
        String pesquisa = request.getParameter("pesquisa");
        String Pagina = request.getParameter("numPagina");
        if (Pagina == null) {
            Pagina = "1";
        }
        if (acao.equals("Gravar")) {

            plano = new PlanoBean();
            /*
             plano.setNumero(request.getParameter("numero"));
             plano.setDescricao(request.getParameter("descricao"));
             plano.setSaldo_inic(Float.parseFloat(request.getParameter("saldo_inic")));
             plano.setDt_saldo(formataData(request.getParameter("dt_saldo")));
             //plano.setDt_saldo(request.getParameter("dt_saldo"));
             plano.setSaldo_iniv(Float.parseFloat(request.getParameter("saldo_iniv")));
             plano.setDt_saus(formataData(request.getParameter("dt_saus")));
             String valor2 = limpaValor(request.getParameter("valor"));
             plano.setC_saivus(Float.parseFloat(request.getParameter("c_saivus")));
             String valor2 = limpaValor(request.getParameter("valor"));
             plano.setC_saieus(Float.parseFloat(request.getParameter("c_saieus")));
             */
            plano.setNumero(request.getParameter("numero"));
            plano.setDescricao(request.getParameter("descricao"));
            String valor2 = limpaValor(request.getParameter("saldo_inic"));
            plano.setSaldo_inic(Float.parseFloat(valor2));
            plano.setDt_saldo(formataData(request.getParameter("dt_saldo")));
            String valor3 = limpaValor(request.getParameter("saldo_iniv"));
            plano.setSaldo_iniv(Float.parseFloat(valor3));
            plano.setDt_saus(formataData(request.getParameter("dt_saus")));
            String valor4 = limpaValor(request.getParameter("c_saivus"));
            plano.setC_saivus(Float.parseFloat(valor4));
            String valor5 = limpaValor(request.getParameter("c_saieus"));
            plano.setC_saieus(Float.parseFloat(valor5));

            PlanoDB planodb = new PlanoDB(DB);
            planodb.cadastrarPlano(plano);
            String msg = "Dados gravados com sucesso!!!";
            request.getSession().setAttribute("msg", msg);
            request.getRequestDispatcher("visualizaplano.jsp").forward(request, response);

        } else if (acao.equals("Alterar")) {

            plano = new PlanoBean();
            System.out.println("cheqguei aqui..alterar.");
            System.out.println("idplano " + request.getParameter("idplano"));

            plano.setIdPLANO(Integer.parseInt(request.getParameter("idplano")));

            plano.setNumero(request.getParameter("numero"));
            plano.setDescricao(request.getParameter("descricao"));
            String valor2 = limpaValor(request.getParameter("saldo_inic"));
            plano.setSaldo_inic(Float.parseFloat(valor2));
            plano.setDt_saldo(formataData(request.getParameter("dt_saldo")));
            String valor3 = limpaValor(request.getParameter("saldo_iniv"));
            plano.setSaldo_iniv(Float.parseFloat(valor3));
            plano.setDt_saus(formataData(request.getParameter("dt_saus")));
            String valor4 = limpaValor(request.getParameter("c_saivus"));
            plano.setC_saivus(Float.parseFloat(valor4));
            String valor5 = limpaValor(request.getParameter("c_saieus"));
            plano.setC_saieus(Float.parseFloat(valor5));

            System.out.println(plano.toString());
            PlanoDB planodb = new PlanoDB(DB);
            planodb.alterarPlano(plano);
            List<PlanoBean> pag = objpagina.buscaPagina(limite, offset);

            //List<MovimentoBean> pagina = objpagina.buscaPagina(opcao, 15, offset, "s");
            System.out.println("usuario " + request.getSession().getAttribute("usuario_autenticado"));
            request.setAttribute("pagina", pag);//aqui pagina sao os registros
            request.setAttribute("numPagina", numPagina);//aqui e a quantidade de paginas totais

            request.getRequestDispatcher("paginaPlano.jsp").forward(request, response);
            //request.getRequestDispatcher("visualizaplano.jsp").forward(request, response);

        } else if (acao.equals("Excluir")) {
            /* 
             plano = new PlanoBean();
                        
             plano.setIsbn(Integer.parseInt(request.getParameter("isbn")));
            
             PlanoDB planodb = new PlanoDB();
            
             planodb.excluirPlano(plano);
             //planodb.listarplanos("");
             //alterar para visualiza3
             request.getRequestDispatcher("visualiza3.jsp").forward(request, response);
             */
        } else if (acao.equals("Cancelar")) {
            List<PlanoBean> pag = objpagina.buscaPagina(limite, offset);

            //System.out.println("primeira vez paginada offset " + offset);
            request.setAttribute("pagina", pag);//aqui pagina sao os registros
            request.setAttribute("numPagina", numPagina);//aqui e a quantidade de paginas totais

            request.getRequestDispatcher("paginaPlano.jsp").forward(request, response);
            //request.getRequestDispatcher("visualizaplano.jsp").forward(request, response);

        } else if (acao.equals("Cadastrar")) {

            request.getRequestDispatcher("incluirplano.jsp").forward(request, response);

        } else if (acao.equals("Pesquisar")) {

            PlanoDB planodb = new PlanoDB(DB);

            //    List<PlanoBean> lista = planodb.listarPlano(pesquisa,opcao_de_pesquisa);
            //    request.setAttribute("lista", lista);
            request.setAttribute("opcao", opcao_de_pesquisa);
            request.getRequestDispatcher("pesquisaplano.jsp").forward(request, response);

        } else if (acao.equals("Voltar")) {
            request.getRequestDispatcher("menu2.jsp").forward(request, response);

            // inclusao da paginacao
        } else if (acao.equals("Plano de Contas") || acao.equals("Plano")) {

            objpagina = new PaginaPlano(DB);
            qtd = objpagina.qtdReg();
            numPagina = objpagina.numPag(limite);
            totPagina = numPagina;
            offset = 0;
            numPagina = 1;//objpagina.pagAtual(limite);

            //offset = (numPagina * limite) - limite;
            List<PlanoBean> pag = objpagina.buscaPagina(limite, offset);

            //List<MovimentoBean> pagina = objpagina.buscaPagina(opcao, 15, offset, "s");
            System.out.println("primeira vez paginada offset " + offset);
            request.setAttribute("pagina", pag);//aqui pagina sao os registros
            request.setAttribute("numPagina", numPagina);
            request.setAttribute("totPagina", totPagina);//aqui e a quantidade de paginas totais

            request.getRequestDispatcher("paginaPlano.jsp").forward(request, response);
        } else if (acao.equals(
                " > ")) {
            offset += limite;
            numPagina++;
            if (offset > qtd) {
                offset -= limite;
                numPagina = (offset / limite) + 1;
            }

            objpagina = new PaginaPlano(DB);
            List<PlanoBean> pag = objpagina.buscaPagina(limite, offset);
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);
            request.getRequestDispatcher("paginaPlano.jsp").forward(request, response);

        } else if (acao.equals(
                " < ")) {
            offset -= limite;
            numPagina--;
            if (offset < 0) {
                numPagina = 1;
                offset = 0;
            }
            objpagina = new PaginaPlano(DB);
            List<PlanoBean> pag = objpagina.buscaPagina(limite, offset);
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);
            request.getRequestDispatcher("paginaPlano.jsp").forward(request, response);

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
        try {
            processRequest(request, response);

        } catch (java.text.ParseException ex) {
            Logger.getLogger(ServletPlano.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletPlano.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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
        try {
            processRequest(request, response);

        } catch (java.text.ParseException ex) {
            Logger.getLogger(ServletPlano.class
                    .getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ServletPlano.class
                    .getName()).log(Level.SEVERE, null, ex);
        }
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

    public static java.sql.Date formataData(String data) throws Exception {
        if (data == null || data.equals("")) {
            return null;
        }

        java.sql.Date date = null;
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date = new java.sql.Date(((java.util.Date) formatter.parse(data)).getTime());
        return date;
    }

    public String limpaValor(String v) {
        String vv = "";
        String caracter = "";
        int off = 1;
        for (int i = 0; i < v.length(); i++) {
            caracter = v.substring(i, off);
            System.out.println(" vv = " + vv + " i = " + i + " tam v = " + v.length());
            System.out.println(" v subs= " + caracter);
            if (caracter.equals(",")) {
                vv += ".";
            } else if (!caracter.equals(".")) {
                vv += caracter;
            }
            off++;
            System.out.println(" vv = " + vv);
        }

        return vv;
    }
}
