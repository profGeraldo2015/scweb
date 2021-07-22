<%-- 
    Document   : paginada2
    Created on : 17/05/2016, 08:29:47
    Author     : Geraldo
--%>


<%@page import="br.com.model.PlanoBean"%>
<%@page import="br.com.model.PlanoDB"%>
<%@page import="java.util.Iterator"%>
<%@page import="br.com.model.MovimentoBean"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="br.com.model.AcessoDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <jsp:include page="menusus.jsp" />


    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/jquery.corner.js"></script>
    <body>

    <center>
        <h3>Movimento Contábil</h3>

        <form id="frm_pagina2" action="ServletPag" method="GET">

            <div id="operacao">
                <div id="pesq" class="box">
                    <fieldset align="left"><legend>Escolha opção de pesquisa</legend> 
                        <div id="pesquisa" class="box"> 
                            <select size="1" name="opcao_pesquisar" id="opcao_pesquisar" onchange="buscaCampo();">
                                <option value="dt_vencto" selected>Vencimento</option>
                                <option value="hist" >Histórico</option>
                                <option value="obs">Observação</option>
                                <option value="dt_emissao">Emissão</option>
                            </select>   

                        </div>
                        <div id="pesquisaText" class="box">    
                            <input type="date" name="pesquisa" id="pesquisa" value="" size="50" />
                            <button name="acao" value="Pesquisar">Pesquisar</button>
                        </div>

                    </fieldset>
                </div>

                <div id="ordena" class="">
                    <fieldset align="left"><legend>Escolha opção de ordenação</legend>  
                        <input type="hidden" name="opcaovigente" value="<%=request.getAttribute("opcaovigente")%>"/>
                        <select size="1" name="opcao_ordenar" id="opcao_ordenar" onchange="setaOrdenacao();">
                            <option value="dt_vencto" selected>Vencimento</option>
                            <option value="hist" >Histórico</option>
                            <option value="obs">Observação</option>
                            <option value="dt_emissao">Emissão</option>
                        </select>   

                    </fieldset>
                </div>
                <div id="irPara" class="">
                    <fieldset align="left"><legend>Escolha a data do 1º movimento</legend> 
                        <%--<button name="acao" value="Ir Para" >Ir Para</button>--%>
                        <input  type="date" name="dataIrPara" id="dataIrPara" value="" size="8" onchange="buscaData();" />
                    </fieldset>     
                </div>


            </div>




            <input type="hidden" id="txt_numPagina" name="numPagina" value="<%=request.getAttribute("numPagina")%>" size="5" />
            <input type="hidden" id="txt_totPagina" name="totPagina" value="<%=request.getAttribute("totPagina")%>" size="5" />
            <input type="text"   id="txt_offset" name="offset" value="<%=request.getAttribute("offset")%>"/>

            <table id="tbMovto" border='2' width="90%">
                <tr>


                  <th width="6">Débito</th>
                    <th width="6%">Crédito</th>
                    <th width="6%">Emissão</th>
                    <th width="30%">Histórico</th>
                    <th width="20%">Observação</th>
                    <th width="10%">Valor</th>
                    <th width="6%">Vencimento</th>
                    <th width="6%">Alterar</th>
                    <th width="6%">Excluir</th>
                </tr>
                <%
                    List<MovimentoBean> result = (List<MovimentoBean>) request.getAttribute("pagina");
                    Iterator iterator = result.iterator();

                    while (iterator.hasNext()) {
                        MovimentoBean lancto = (MovimentoBean) iterator.next();


                %>

                <tr>
                    <td><%=lancto.getDebito()%></td>
                    <td><%=lancto.getCredito()%></td>
                    <td><%=lancto.getEmissa()%></td>
                    <td><%=lancto.getHistorico()%></td>
                    <td><%=lancto.getObs()%></td>
                    <td align="right"><%=lancto.getV()%> </td> 
                    <td align="right"><%=lancto.getVencto()%></td>
                    <td><a href="http://<%=request.getServletContext().getInitParameter("IP")%>/alteramovto.jsp?codigo=<%=lancto.getIdmovimento()%>&opcao_debito=<%=lancto.getDebito()%>&opcao_credito=<%=lancto.getCredito()%>&dt_emissao=<%=lancto.getEmissao()%>&hist=<%=lancto.getHistorico()%>&obs=<%=lancto.getObs()%>&valor=<%=lancto.getValor()%>&dt_vencto=<%=lancto.getVencimento()%>&opcaovigente=<%=request.getParameter("opcaovigente")%>&opcaoradio=<%=request.getParameter("opcaoradio")%>"><img src="editar16.png"></a></td>
                    <td><a href="http://<%=request.getServletContext().getInitParameter("IP")%>/excluimovto2.jsp?codigo=<%=lancto.getIdmovimento()%>&opcao_debito=<%=lancto.getDebito()%>&opcao_credito=<%=lancto.getCredito()%>&dt_emissao=<%=lancto.getEmissao()%>&hist=<%=lancto.getHistorico()%>&obs=<%=lancto.getObs()%>&valor=<%=lancto.getValor()%>&dt_vencto=<%=lancto.getVencimento()%>&opcaovigente=<%=request.getParameter("opcaovigente")%>&opcaoradio=<%=request.getParameter("opcaoradio")%>"><img src="excluir16.png"></a></td>

                </tr>

                <%
                    }
                %>
                <%--<tr><td>  <p>Página : <%=request.getAttribute("numPagina")%>/<%=request.getAttribute("totPagina")%></p> </td></tr>--%>
            </table>
        </form>
        <p></p><p></p><p></p><p></p>
        <button id="btn_anterior"  value="Anterior" onclick="buscaAnterior();">Anterior</button>
        <button name="acao" value="Cadastrar">Cadastrar</button>
        <button id="btn_proximo"   value="Proximo"  onclick="buscaProximo();">Próximo</button>
        <p></p><p></p><p></p><p></p>    <p></p><p></p><p></p><p></p>
        <fieldset align="center"><legend>Relatórios - Preencha os dados abaixo: </legend>  


            <form name="form_extrato" action="ServletRel" method="GET">
                <table id="tbExtrato" border="1" bgcolor="navy" align="center">
                    <td>Data de: </td>
                    <td>
                        <select name="opcao_data" id="opcao_data">
                            <option value="dt_vencto" selected="selected">Vencimento</option>
                            <option value="dt_emissao">Emissao</option>
                        </select>      
                    </td>
                    <tr>
                        <td>Data Inicial : </td>
                        <td><input type="date" name="inicio" id="inicio" value="${now}" size="8" /></td>
                    </tr>
                    <tr><td>Data Final : </td>
                        <td> <input type="date" name="fim" id="fim" value="" size="8"/></td>
                    </tr>  
                    <tr><td>Número : </td>
                        <td> <input type="text" name="txt_conta" id="txt_conta" value="" size="8"/></td>
                    </tr>  
                    <tr><td>Conta :</td> 
                        <td>
                            <%
                                PlanoDB contaD = new PlanoDB(request.getServletContext().getInitParameter("DB"));

                                List<PlanoBean> lista = contaD.carregaComboPlano();


                            %>  
                            <select name="opcao_conta" id="opcao_conta" onchange="pegaConta(txt_conta)">
                                <option value="">Escolha a conta...</option>
                                <%                                        for (int i = 0; i < lista.size(); i++) {
                                        if (lista.get(i).getNumero().substring(4, 8).equals("0000")) {

                                        } else {
                                %>

                                <option value="<%=lista.get(i).getNumero()%>"><%=lista.get(i).getNumero() + " - " + lista.get(i).getDescricao()%> </option>

                                <%}
                                    }
                                %>

                            </select>      
                        </td>
                    </tr>
                </table>

                <br>
                <input type="reset" value="Limpar" name="limpar" /> 

            </form>
            <br>
            <div id="zbotoes">
                <div id="botao">
                    <button id="btn_extrato"     value="Extrato"       onclick="buscaExtrato();">Extrato</button>
                </div>
                <div id="botao">
                    <button id="btn_diario"      value="Diario"        onclick="buscaDiario();">Diario</button>
                </div>
                <div id="botao">
                    <button id="btn_diariogeral" value="Diario Geral"  onclick="buscaDiarioGeral();">Diario Geral</button>
                </div>
                <div id="botao">
                    <button id="btn_balancete"   value="Balancete"     onclick="buscaBalancete();">Balancete</button>
                </div>
                <div id="botao">
                    <button id="btn_demo"        value="Demonstrativo" onclick="buscaDemo();">Demonstrativo</button>
                </div>
            </div>
            <br>
            <br>
            <table id="tbsMovto" align="center" border='2' width="80%" bgcolor="goldenrod">
                <tr><th>Aguarde o resultado aqui...</th></tr>

            </table>

        </fieldset>
    </center>

    <script type="text/javascript">
        function initXHR() {
            if (window.XMLHttpRequest) {
                xhr = new XMLHttpRequest();
            } else if (window.ActiveXObject) {
                xhr = new ActiveXObject("Microsoft.XMLHttp");
            }
        }

        function sendRequest(url, handler) {
            initXHR();
            xhr.onreadystatechange = handler;
            xhr.open("GET", url, true);
            xhr.send(null);
        }


        function buscaProximo() {
            var acao = window.document.getElementById("btn_proximo");

            var url = "ServletPag?acao=" + acao.value + "&offset=" + $('#txt_offset').val() + "&totPagina=" + $('#txt_totPagina').val() + "&numPagina=" + $('#txt_numPagina').val() + "&opcao_ordenar=" + $('#opcao_ordenar').val();

            sendRequest(url, ajaxBuscaPagina);

        }
        function buscaAnterior() {
            var acao = window.document.getElementById("btn_anterior");
            var url = "ServletPag?acao=" + acao.value + "&offset=" + $('#txt_offset').val() + "&totPagina=" + $('#txt_totPagina').val() + "&numPagina=" + $('#txt_numPagina').val() + "&opcao_ordenar=" + $('#opcao_ordenar').val();

            sendRequest(url, ajaxBuscaPagina);
        }
        function ajaxBuscaPagina() {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    var valor = window.document.getElementById("tbMovto");
                    var retorno = xhr.responseText;
                    valor.innerHTML = retorno;
                }
            }
        }
        function buscaExtrato() {
            var acao = window.document.getElementById("btn_extrato");

            var url = "sExtrato?acao=" + acao.value + "&opcao_data=" + $('#opcao_data').val() + "&inicio=" + $('#inicio').val() + "&fim=" + $('#fim').val() + "&opcao_conta=" + $('#opcao_conta').val() + "&txt_conta=" + $('#txt_conta').val();

            sendRequest(url, ajaxBuscaExtrato);

        }
        function buscaDiario() {
            var acao = window.document.getElementById("btn_diario");

            var url = "sExtrato?acao=" + acao.value + "&opcao_data=" + $('#opcao_data').val() + "&inicio=" + $('#inicio').val() + "&fim=" + $('#fim').val() + "&opcao_conta=" + $('#opcao_conta').val() + "&txt_conta=" + $('#txt_conta').val();

            sendRequest(url, ajaxBuscaExtrato);

        }
        function buscaDiarioGeral() {
            var acao = window.document.getElementById("btn_diariogeral");

            var url = "sExtrato?acao=" + acao.value + "&opcao_data=" + $('#opcao_data').val() + "&inicio=" + $('#inicio').val() + "&fim=" + $('#fim').val() + "&opcao_conta=" + $('#opcao_conta').val() + "&txt_conta=" + $('#txt_conta').val();

            sendRequest(url, ajaxBuscaExtrato);

        }
        function buscaBalancete() {
            var acao = window.document.getElementById("btn_balancete");

            var url = "sExtrato?acao=" + acao.value + "&opcao_data=" + $('#opcao_data').val() + "&inicio=" + $('#inicio').val() + "&fim=" + $('#fim').val();

            sendRequest(url, ajaxBuscaExtrato);

        }

        function buscaDemo() {
            var acao = window.document.getElementById("btn_demo");

            var url = "sExtrato?acao=" + acao.value + "&opcao_data=" + $('#opcao_data').val() + "&inicio=" + $('#inicio').val() + "&fim=" + $('#fim').val();

            sendRequest(url, ajaxBuscaExtrato);

        }
        function ajaxBuscaExtrato() {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    var valor = window.document.getElementById("tbsMovto");
                    var retorno = xhr.responseText;
                    valor.innerHTML = retorno;
                }
            }
        }
        function setaOrdenacao() {
            var campo = window.document.getElementById("opcao_ordenar");
            var url = "ServletPag?acao=Ordenar" + "&offset=" + $('#txt_offset').val() + "&totPagina=" + $('#txt_totPagina').val() + "&numPagina=" + $('#txt_numPagina').val() + "&opcao_ordenar=" + $('#opcao_ordenar').val();
            sendRequest(url, ajaxBuscaPagina);
        }
        function buscaData() {
            var campo = window.document.getElementById("dataIrPara");
            var url = "ServletPag?acao=IrPara" + "&offset=" + $('#txt_offset').val() + "&totPagina=" + $('#txt_totPagina').val() + "&numPagina=" + $('#txt_numPagina').val() + "&dataIrPara=" + $('#dataIrPara').val();
            sendRequest(url, ajaxBuscaPagina);
        }

        function buscaCampo() {
            var campo = window.document.getElementById("opcao_pesquisar");
            var url = "sPesquisa?acao=pesq&campo=" + campo.value;
            sendRequest(url, ajaxBuscaPesquisa);
        }
        function ajaxBuscaPesquisa() {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    var valor = window.document.getElementById("pesquisaText");
                    var retorno = xhr.responseText;
                    valor.innerHTML = retorno;
                }
            }

        }
        function pegaConta(obj) {
            var conta = document.getElementById("opcao_conta");
            obj.value = conta.value;


        }
    </script>
</body>
<jsp:include page="footer.jsp" />

</html>



