/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import br.com.model.BLinha;
import br.com.model.Balancete;
import br.com.model.MovimentoBean;
import br.com.model.MovimentoDB;
import br.com.model.Pagina;
import br.com.model.Relatorio;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
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
public class ServletPag extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private int offset;
    private int qtd = 0;
    private int numPagina = 1;
    private int totPagina = 0;
    private int limite = 12;
    Pagina objpagina;
    List<MovimentoBean> pag;
    boolean primeiraVez = true;
    String opcao = "dt_vencto";
    String DB;
    String dataIrPara;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        DB = request.getServletContext().getInitParameter("DB");
        System.out.println("cheguei aqui ajax totPagina " + request.getParameter("totPagina"));
        totPagina = Integer.parseInt(request.getParameter("totPagina"));
        String acao = (String) request.getParameter("acao");
        numPagina = Integer.parseInt(request.getParameter("numPagina"));
        // if (primeiraVez) {
        // offset = Integer.parseInt(request.getParameter("offset"));
        //     primeiraVez = false;
        // }
        String opcaovigente = request.getParameter("opcaovigente");
        String opcao_de_pesquisa = request.getParameter("opcao_pesquisar");
        String pesquisa = request.getParameter("pesquisa");
        System.out.println("cheguei aqui ajax " + totPagina + " totPagina ");
        System.out.println("cheguei aqui ajax " + acao + " acao ");
        System.out.println("cheguei aqui ajax " + numPagina + " numPagina ");
        System.out.println("cheguei aqui ajax " + offset + " offset ");
        System.out.println("cheguei aqui ajax opcao " + opcao);

        switch (acao) {

            case "Voltar":

                request.getRequestDispatcher("menu2.jsp").forward(request, response);
                break;
            case "Cadastrar":
                request.getRequestDispatcher("incluirmovto2.jsp").forward(request, response);
                // request.getRequestDispatcher("menu2.jsp").forward(request, response);
                break;
            case "Pesquisar":

                MovimentoDB lanctodb = new MovimentoDB(DB);

                List<MovimentoBean> pag = lanctodb.listarMovtos(pesquisa, opcao_de_pesquisa);
                request.setAttribute("lista", pag);
                request.setAttribute("opcao", opcao_de_pesquisa);

                request.setAttribute("pagina", pag);//aqui pagina sao os registros
                request.setAttribute("numPagina", numPagina);//aqui e a pagina atual
                request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
                request.setAttribute("offset", offset);
                request.setAttribute("opcaovigente", opcao);
                request.setAttribute("dataIrPara", dataIrPara);
                request.getRequestDispatcher("pesquisamovto.jsp").forward(request, response);
                break;
            case "Ordenar":
                System.out.println("cheguei aqui ajax ordenar totPagina " + totPagina);
                System.out.println("cheguei aqui ajax  acao " + acao);

                System.out.println("cheguei aqui ajax numPagina " + numPagina);

                System.out.println("cheguei aqui ajax  offset " + offset);

                opcao = request.getParameter("opcao_ordenar");
                objpagina = new Pagina(DB);

                qtd = objpagina.qtdReg();
                totPagina = objpagina.numPag(limite);
                if (offset < 0) {
                    numPagina = 1;
                    offset = 0;
                }
                System.out.println("antes de exec list ordenar opcao_ordenar " + opcao);
                System.out.println("limite " + limite);

                System.out.println("numPagina " + numPagina);

                System.out.println(" offset " + offset);
                pag = objpagina.buscaPagina(opcao, limite, offset, "n");
                imprimirTela(out);
                break;
            case "Anterior":

                System.out.println("cheguei aqui ajax anterior " + totPagina + " totPagina ");
                System.out.println("cheguei aqui ajax " + acao + " acao ");

                System.out.println("cheguei aqui ajax " + numPagina + " numPagina ");
                offset = Integer.parseInt(request.getParameter("offset"));
                System.out.println("cheguei aqui ajax " + offset + " offset ");

                objpagina = new Pagina(DB);

                qtd = objpagina.qtdReg();
                totPagina = objpagina.numPag(limite);
                offset -= limite;
                numPagina--;
                if (offset < 0) {
                    numPagina = 1;
                    offset = 0;
                }
                System.out.println("antes de exec list anterior " + opcao + " opcao ");
                System.out.println("limite " + limite);

                System.out.println(numPagina + " numPagina ");

                System.out.println(" " + offset + " offset ");
                opcao = request.getParameter("opcao_ordenar");
                pag = objpagina.buscaPagina(opcao, limite, offset, "n");
                imprimirTela(out);
                break;

            case "Proximo":

                offset = Integer.parseInt(request.getParameter("offset"));
                objpagina = new Pagina(DB);

                qtd = objpagina.qtdReg();
                totPagina = objpagina.numPag(limite);

                offset += limite;
                numPagina++;
                if (offset > qtd) {
                    offset -= limite;
                    numPagina = (offset / limite) + 1;
                }

                System.out.println("antes de exec list prox opcao " + opcao);
                System.out.println("limite " + limite);

                System.out.println("numPagina  " + numPagina);

                System.out.println("  offset " + offset);
                opcao = request.getParameter("opcao_ordenar");
                pag = objpagina.buscaPagina(opcao, limite, offset, "n");
                imprimirTela(out);
                break;
            case "IrPara":
                //ocerto aqui é que quando for da primeira vez abrir uma tela para escolher se 
                // e ordenado  por emissao ou vencimento

                objpagina = new Pagina(DB);
                qtd = objpagina.qtdReg();
                totPagina = objpagina.numPag(limite);
                offset = 0;

                dataIrPara = request.getParameter("dataIrPara");

                Date d = objpagina.CtoD(dataIrPara);

                numPagina = objpagina.pagIrPara(opcao, limite, d);

                offset = (numPagina * limite) - limite;
                if (offset < 0) {
                    offset = 0;
                }
                pag = objpagina.buscaPagina(opcao, limite, offset - 1, "n");

                //List<MovimentoBean> pagina = objpagina.buscaPagina(opcao, 15, offset, "s");
                System.out.println("primeira vez paginada offset " + offset);
                //request.setAttribute("usuario_autenticado", usuario);
                request.setAttribute("pagina", pag);//aqui pagina sao os registros
                request.setAttribute("numPagina", numPagina);//aqui e a pagina atual
                request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
                request.setAttribute("offset", offset);
                request.setAttribute("opcaovigente", opcao);
                request.setAttribute("dataIrPara", dataIrPara);
                //request.getRequestDispatcher("paginada2.jsp").forward(request, response);
                imprimirTela(out);
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

        try {
            processRequest(request, response);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            Logger.getLogger(ServletSc.class.getName()).log(Level.SEVERE, null, ex);
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
        } catch (Exception ex) {
            Logger.getLogger(ServletSc.class.getName()).log(Level.SEVERE, null, ex);
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

    public void imprimirTela(PrintWriter out) {
        try {

            //List<Contrato> listaContrato = contrato.consultaContrato(valor);
            out.println("<tr>");

            out.println("<th width='6%'>Debito</th>");
            out.println("<th width='6%'>Credito</th>");
            out.println("<th width='6%'>Data</th>");
            out.println("<th width='30%'>Historico</th>");
            out.println("<th width='20%'>Observacao</th>");
            out.println("<th width='10%'>Valor</th>");
            out.println("<th width='6%'>Vencimento</th>");
            out.println("<th width='6%'>Alterar</th>");
            out.println("<th width='6%'>Excluir</th>");
            out.println("</tr>");

            for (MovimentoBean lancto : pag) {
                out.println("<tr>");

                out.println("<td>" + lancto.getDebito() + "</td>");
                out.println("<td>" + lancto.getCredito() + "</td>");
                out.println("<td>" + lancto.getEmissa() + "</td>");
                out.println("<td>" + lancto.getHistorico() + "</td>");
                out.println("<td>" + lancto.getObs() + "</td>");
                out.println("<td align='right'>" + lancto.getV() + "</td>");
                out.println("<td align='right'>" + lancto.getVencto() + "</td>");
                out.println("<td><a href='http://'" + getServletContext().getInitParameter("IP") + "/alteramovto.jsp?codigo=" + lancto.getIdmovimento() + "&opcao_debito=" + lancto.getDebito() + "&opcao_credito=" + lancto.getCredito() + "&dt_emissao=" + lancto.getEmissao() + "&hist=" + lancto.getHistorico() + "&obs=" + lancto.getObs() + "&valor=" + lancto.getValor() + "&dt_vencto=" + lancto.getVencimento() + "><img src=\"editar16.png\"></a></td>");
                out.println("<td><a href='http://'" + getServletContext().getInitParameter("IP") + "/excluimovto2.jsp?codigo=" + lancto.getIdmovimento() + "&opcao_debito=" + lancto.getDebito() + "&opcao_credito=" + lancto.getCredito() + "&dt_emissao=" + lancto.getEmissao() + "&hist=" + lancto.getHistorico() + "&obs=" + lancto.getObs() + "&valor=" + lancto.getValor() + "&dt_vencto=" + lancto.getVencimento() + "><img src=\"excluir16.png\"></a></td>");

                out.println("</tr>");
            }
//            out.println("<tr><td>  <p>Página : <%=request.getAttribute('numPagina')%>/<%=request.getAttribute('totPagina')%></p> </td></tr>");

        } catch (Exception e) {
            System.out.println("Falha ao Criar Tabela Movimento" + e.getMessage());
        } finally {

            out.close();
        }

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
