/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controller;

import static br.com.controller.ServletPlano.formataData;
import br.com.model.Datas;
import br.com.model.MovimentoBean;
import br.com.model.MovimentoDB;
import br.com.model.Pagina;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
 * @author User
 */
public class ServletSc extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private MovimentoBean lancto;
    private int offset = 12;
    private int qtd = 0;
    private int numPagina = 1;
    private int totPagina = 0;
    private int limite = 12;
    Pagina objpagina = null;
    String opcao = "dt_vencto";
    String dataIrPara;
    String[] listaOpcoes = {"dt_vencto", "hist", "obs", "dt_emissao"};
    String DB;
    DecimalFormat df;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {

        DB = request.getServletContext().getInitParameter("DB");

        SimpleDateFormat data;
        data = new SimpleDateFormat("dd/MM/yyyy");
        Date datahoje = new Date();
        Pagina pegaDataHoje = new Pagina(DB);
        String datafh = data.format(datahoje);
        Date dataih = pegaDataHoje.CtoD(data.format(datahoje));
        Datas dtHoje = new Datas();
        System.out.println("data hoje " + dtHoje.toString());
        System.out.println("data hojeh " + dtHoje.getDataH());

        String acao = request.getParameter("acao");
        String codigo = request.getParameter("codigo");
        String opcao_de_pesquisa = request.getParameter("opcao_pesquisar");
        String opcaoradio = request.getParameter("opcaoradio");
        String opcaovigente = request.getParameter("opcaovigente");
        String pesquisa = request.getParameter("pesquisa");
        String pagina = request.getParameter("pagina");
        String usuario = (String) request.getSession().getAttribute("usuario_autenticado");
        System.out.println("--------------------------------------");
        System.out.println(DB);
        System.out.println("IP " + request.getServletContext().getInitParameter("IP"));
        System.out.println(" entrei no servelet acao = " + acao);
        System.out.println(" entrei no servelet opcao_pesquisar = " + opcao_de_pesquisa);
        System.out.println(" entrei no servelet opcaoradio = " + opcaoradio);
        System.out.println(" entrei no servelet opcaovigente = " + opcaovigente);
        System.out.println(" entrei no servelet dataIrPara = " + this.dataIrPara + " parametro " + dataIrPara);
        System.out.println(" entrei no servelet opcao global = " + opcao);

        //int offset = Integer.parseInt(request.getParameter("offset"));
        //opcao = opcaotela;
        System.out.println(" opcao servlet " + opcao + " opcaoradio =  " + opcaoradio);

        if (opcao == null || opcao == "") {
            System.out.println("opcao do servlet nulo ou branco " + opcao + " opcaoradio " + opcaoradio);
            opcao = "dt_vencto";
        } else {
            System.out.println("opcao do servlet " + opcao + " opcaovigente " + opcaovigente);
            //opcao = request.getParameter("opcaovigente");
            opcao = request.getParameter("opcaoradio");

        }
        System.out.println(" depois do if opcao = " + opcao + " opcaoradio > " + opcaoradio);
        if (pagina == null) {
            pagina = "1";
        }

        if (acao.equals("Gravar")) {

            lancto = new MovimentoBean();
            System.out.println("cheqguei aqui..1.Gravar");
            if (request.getParameter("txt_debito").equals("")) {
                lancto.setDebito(request.getParameter("opcao_debito"));
            } else {
                lancto.setDebito(request.getParameter("txt_debito"));
            }
            if (request.getParameter("txt_credito").equals("")) {
                lancto.setCredito(request.getParameter("opcao_credito"));
            } else {
                lancto.setCredito(request.getParameter("txt_credito"));
            }

            lancto.setEmissao(formataData(request.getParameter("dt_emissao")));
            lancto.setHistorico(request.getParameter("hist"));
            lancto.setObs(request.getParameter("obs"));

            System.out.println(request.getParameter("valor"));
            String valor2 = limpaValor(request.getParameter("valor"));
            lancto.setValor(Float.parseFloat(valor2));
            // lancto.setValor(Float.parseFloat(request.getParameter("valor")));
            lancto.setVencimento(formataData(request.getParameter("dt_vencto")));
            MovimentoDB lanctodb = new MovimentoDB(DB);
            lanctodb.cadastrarMovto(lancto);

            //request.getRequestDispatcher("incluimovto2.jsp").forward(request, response);
            //request.getRequestDispatcher("visualizamovto.jsp").forward(request, response);
            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset, "n");
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);

        } else if (acao.equals("Alterar")) {

            lancto = new MovimentoBean();
            //JOptionPane.showMessageDialog( null,"heguei alterar");
            System.out.println("cheqguei aqui..alterar.");
            System.out.println("idmovimento " + request.getParameter("idmovimento"));

            lancto.setIdmovimento(Integer.parseInt(request.getParameter("idmovimento")));

            if (request.getParameter("txt_debito").equals("")) {
                lancto.setDebito(request.getParameter("opcao_debito"));
            } else {
                lancto.setDebito(request.getParameter("txt_debito"));
            }
            if (request.getParameter("txt_credito").equals("")) {
                lancto.setCredito(request.getParameter("opcao_credito"));
            } else {
                lancto.setCredito(request.getParameter("txt_credito"));
            }

            lancto.setEmissao(formataData(request.getParameter("dt_emissao")));
            lancto.setHistorico(request.getParameter("hist"));
            lancto.setObs(request.getParameter("obs"));
            String valor2 = limpaValor(request.getParameter("valor"));
            lancto.setValor(Float.parseFloat(valor2));
            //lancto.setValor(Float.parseFloat(request.getParameter("valor")));
            lancto.setVencimento(formataData(request.getParameter("dt_vencto")));
            MovimentoDB lanctodb = new MovimentoDB(DB);
            System.out.println("cheguei aqui..to gravando....");
            lanctodb.alterarMovto(lancto);
            System.out.println("chamando pagina...");

            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset, "n");
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.setAttribute("datahoje", datafh);
            System.out.println("chamando datahoje " + datafh);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);

        } else if (acao.equals("Duplicar")) {

            lancto = new MovimentoBean();
            //JOptionPane.showMessageDialog( null,"heguei alterar");
            System.out.println("cheqguei aqui..duplicar.");

            if (request.getParameter("txt_debito").equals("")) {
                lancto.setDebito(request.getParameter("opcao_debito"));
            } else {
                lancto.setDebito(request.getParameter("txt_debito"));
            }
            if (request.getParameter("txt_credito").equals("")) {
                lancto.setCredito(request.getParameter("opcao_credito"));
            } else {
                lancto.setCredito(request.getParameter("txt_credito"));
            }

            lancto.setEmissao(formataData(request.getParameter("dt_emissao")));
            lancto.setHistorico(request.getParameter("hist"));
            lancto.setObs(request.getParameter("obs"));
            String valor2 = limpaValor(request.getParameter("valor"));
            lancto.setValor(Float.parseFloat(valor2));
            //lancto.setValor(Float.parseFloat(request.getParameter("valor")));

            lancto.setVencimento(formataData(request.getParameter("dt_vencto")));
            MovimentoDB lanctodb = new MovimentoDB(DB);
            System.out.println("cheqguei aqui..to grravando....");
            lanctodb.cadastrarMovto(lancto);
            System.out.println("chamando pagina...");

            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset, "n");
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);

        } else if (acao.equals("Excluir")) {

            lancto = new MovimentoBean();
            System.out.println(" Vai excluir");
            lancto.setIdmovimento(Integer.parseInt(request.getParameter("idmovimento")));
            MovimentoDB mov = new MovimentoDB(DB);
            mov.excluirMovto(lancto);
            objpagina = new Pagina(DB);
            qtd = objpagina.qtdReg();
            totPagina = objpagina.numPag(limite);
            //offset = 0;
            //numPagina = objpagina.pagAtual(opcao, limite);
            //offset = (numPagina * limite) - limite;
            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset, "n");
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);
            //request.getRequestDispatcher("visualizamovto.jsp").forward(request, response);

        } else if (acao.equals("Cancelar")) {

            objpagina = new Pagina(DB);
            System.out.println("Cancelar servlet offset " + offset);
            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset, "n");
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);
            //request.getRequestDispatcher("visualizamovto.jsp").forward(request, response);

        } else if (acao.equals("Ordenar")) {

            objpagina = new Pagina(DB);
            System.out.println("ordenar servlet opcao servelt " + opcao + " opcaoradio " + opcaoradio);
            List<MovimentoBean> pag = objpagina.buscaPagina(opcaoradio, limite, offset, "n");
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);

            request.setAttribute("opcaovigente", opcao);
            System.out.println("antes de entrar na pagina opcaoradio " + opcaoradio + " opcao " + opcao + " opcaovigente " + opcaovigente);
            opcao = opcaoradio;
            request.getRequestDispatcher("paginada.jsp").forward(request, response);
            //request.getRequestDispatcher("visualizamovto.jsp").forward(request, response);

        } else if (acao.equals("Cadastrar")) {
            Date dataAtual = new Date();
            request.setAttribute("dataAtual", dataAtual);
            System.out.println("dataAtual" + dataAtual);
            request.setAttribute("numPagina", numPagina);//aqui e a pagina atual
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("incluirmovto2.jsp").forward(request, response);

        } else if (acao.equals("Pesquisar")) {

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
            //request.getRequestDispatcher("paginada.jsp").forward(request, response);
            request.getRequestDispatcher("pesquisamovto.jsp").forward(request, response);

        } else if (acao.equals("Consulta")) {
            //abrir pagina para escolher periodo
            request.setAttribute("numPagina", numPagina);//aqui e a pagina atual
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("consulta.jsp").forward(request, response);

        } else if (acao.equals("Consulta Movimento")) {

            String opcaoConsulta = request.getParameter("opcao_data");
            String inicio = request.getParameter("inicio");
            String fim = request.getParameter("fim");

            String conta;

            if (request.getParameter("txt_conta").equals("") || request.getParameter("txt_conta").equals(null)) {
                conta = "";//request.getParameter("opcao_conta");
            } else {
                conta = request.getParameter("txt_conta");
            }
            MovimentoDB lanctodb = new MovimentoDB(DB);

            List<MovimentoBean> pag = lanctodb.consultaMovto(opcaoConsulta, inicio, fim, conta);
            request.setAttribute("lista", pag);
            request.setAttribute("opcao", opcaoConsulta);

            request.setAttribute("pagina", pag);//aqui pagina sao os registros
            request.setAttribute("numPagina", numPagina);//aqui e a pagina atual
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            //request.getRequestDispatcher("paginada.jsp").forward(request, response);
            request.getRequestDispatcher("consultamovto.jsp").forward(request, response);

        } else if (acao.equals("Voltar")) {

            request.getRequestDispatcher("menu2.jsp").forward(request, response);

        } else if (acao.equals("Movimento Contabil") || acao.equals("Movimento")) {
            //ocerto aqui é que quando for da primeira vez abrir uma tela para escolher se 
            // e ordenado  por emissao ou vencimento

            objpagina = new Pagina(DB);
            qtd = objpagina.qtdReg();
            System.out.println("qtd "+qtd);
            totPagina = objpagina.numPag(limite);
            offset = 0;
            numPagina = objpagina.pagAtual(opcao, limite);

            offset = (numPagina * limite) - limite;

            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset - 1, "n");

            //List<MovimentoBean> pagina = objpagina.buscaPagina(opcao, 15, offset, "s");
            System.out.println("primeira vez paginada offset " + offset);
            request.setAttribute("usuario_autenticado", usuario);
            //session.setAttribute("usuario_autenticado", usuario);
            request.setAttribute("pagina", pag);//aqui pagina sao os registros
            request.setAttribute("numPagina", numPagina);//aqui e a pagina atual
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("objData", dtHoje);

            //request.setAttribute("dataIrPara", datafh);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);

        } else if (acao.equals("Ir Para")) {
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
            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset - 1, "n");

            //List<MovimentoBean> pagina = objpagina.buscaPagina(opcao, 15, offset, "s");
            System.out.println("primeira vez paginada offset " + offset);
            request.setAttribute("usuario_autenticado", usuario);
            request.setAttribute("pagina", pag);//aqui pagina sao os registros
            request.setAttribute("numPagina", numPagina);//aqui e a pagina atual
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);

        } else if (acao.equals(" > ")) {
            System.out.println("recebi no > offset " + offset + " opcao " + opcao);

            offset += limite;
            numPagina++;
            if (offset > qtd) {
                offset -= limite;
                numPagina = (offset / limite) + 1;
            }

            objpagina = new Pagina(DB);
            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset - 1, "n");
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            System.out.println("enviando offset " + offset);
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);

        } else if (acao.equals(" < ")) {
            offset -= limite;
            numPagina--;
            if (offset < 0) {
                numPagina = 1;
                offset = 0;
            }
            objpagina = new Pagina(DB);
            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset - 1, "n");
            request.setAttribute("pagina", pag);
            request.setAttribute("numPagina", numPagina);//aqui e o numero da pagina
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            request.setAttribute("dataIrPara", dataIrPara);
            request.getRequestDispatcher("paginada.jsp").forward(request, response);

        } else if (acao.equals("Paginada2")) {
            //ocerto aqui é que quando for da primeira vez abrir uma tela para escolher se 
            // e ordenado  por emissao ou vencimento
            opcao = request.getParameter("opcao");
            System.out.println("cheguei aqui paginada2 opcao global " + opcao);

            objpagina = new Pagina(DB);
            qtd = objpagina.qtdReg();
            totPagina = objpagina.numPag(limite);
            offset = 0;
            numPagina = objpagina.pagAtual(opcao, limite);

            offset = (numPagina * limite) - limite;

            List<MovimentoBean> pag = objpagina.buscaPagina(opcao, limite, offset, "n");

            System.out.println("primeira vez paginada offset " + offset);
            request.setAttribute("usuario_autenticado", usuario);
            request.setAttribute("pagina", pag);//aqui pagina sao os registros
            request.setAttribute("numPagina", numPagina);//aqui e a pagina atual
            request.setAttribute("totPagina", totPagina);//aqui e o total de paginas
            request.setAttribute("offset", offset);
            request.setAttribute("opcaovigente", opcao);
            System.out.println("primeira vez paginada numPagina " + numPagina);
            System.out.println("primeira vez paginada totPagina " + totPagina);
            request.getRequestDispatcher("paginada2.jsp").forward(request, response);

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
