<%-- 
    Document   : paginada
    Created on : 07/05/2016, 22:08:48
    Author     : Geraldo

 
--%>


<%@page import="br.com.model.Datas"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Iterator"%>
<%@page import="br.com.model.MovimentoBean"%>
<%@page import="java.util.List"%>
<%@page import="java.sql.SQLException"%>
<%@page import="br.com.model.AcessoDB"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>

<!DOCTYPE html>
<html lang="pt-br">
    <meta charset="utf-8">
    <jsp:include page="menusus.jsp" />
    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/jquery.maskedinput.min.js"></script>

    <script type="text/javascript" src="js/jquery.corner.js"></script>

    <script>
        jQuery(function ($) {
            $("#dataIrPara").mask("99/99/9999");
        });

    </script>
    <%--<body onload="pegaData();">--%>
    <body>
    <center>
        <h3>Movimento Contábil</h3>
        <form name="frm_paginada" action="ServletSc" method="GET" >
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
                        <button name="acao" value="Ordenar">Ordenar por</button>  
                        <%

                            if (request.getAttribute("opcaovigente").equals("dt_vencto")) {
                        %>
                        <input type="radio" name="opcaoradio" value="dt_vencto" checked/>Vencimento
                        <%
                        } else {
                        %>
                        <input type="radio" name="opcaoradio" value="dt_vencto" />Vencimento
                        <%
                            }
                            if (request.getAttribute("opcaovigente").equals("dt_emissao")) {
                        %>
                        <input type="radio" name="opcaoradio" value="dt_emissao" checked/>Emissão
                        <%
                        } else {
                        %>
                        <input type="radio" name="opcaoradio" value="dt_emissao"/>Emissão
                        <%
                            }
                            if (request.getAttribute("opcaovigente").equals("hist")) {
                        %>
                        <input type="radio" name="opcaoradio" value="hist" checked/>Histórico
                        <%
                        } else {
                        %>
                        <input type="radio" name="opcaoradio" value="hist"/>Histórico
                        <%
                            }
                            if (request.getAttribute("opcaovigente").equals("obs")) {
                        %>
                        <input type="radio" name="opcaoradio" value="obs" checked/>Observação
                        <%
                        } else {
                        %>
                        <input type="radio" name="opcaoradio" value="obs"/>Observação
                        <%
                            }
                        %>
                    </fieldset>
                </div>
                <div id="irPara" class="">
                    <fieldset align="left"><legend>Escolha a data do 1º movimento</legend> 
                        <button name="acao" value="Ir Para">Ir Para</button>
                        <input  type="date" name="dataIrPara" id="dataIrPara" value="" size="8" />
                  </fieldset>     
                </div>


            </div>
            <%--
                        <button name="acao" value="Cadastrar">Cadastrar</button>
                        <button name="acao" value="Voltar">Voltar</button>
            --%>
            Página : <%=request.getAttribute("numPagina")%>/<%=request.getAttribute("totPagina")%>

            <input type="hidden" name="numPagina" value="<%=request.getAttribute("numPagina")%>" size="5" />

            <input type="hidden" name="totPagina" value="<%=request.getAttribute("totPagina")%>" size="5" />

            <input type="hidden" name="offset" value="<%=request.getAttribute("offset")%>" size="9"  />

            <input type="hidden" name="datahoje" value="<%=request.getParameter("datahoje")%>" size="8"  />



            <table id="tbMovto" border="2" width="90%" >
                <tr>
                    <th width="6%">Débito</th>
                    <th width="6%">Crédito</th>
                    <th width="6%">Emissão</th>
                    <th width="30%">Histórico</th>
                    <th width="23%">Observação</th>
                    <th width="13%">Valor</th>
                    <th width="6%">Vencimento</th>
                    <th width="3%"></th>
                    <th width="3%"></th>
                </tr>
                <%
                    List<MovimentoBean> result = (List<MovimentoBean>) request.getAttribute("pagina");
                    Iterator iterator = result.iterator();
                    int linha = 1;
                    while (iterator.hasNext()) {
                        MovimentoBean lancto = (MovimentoBean) iterator.next();
                        if ((linha % 2) == 0) {
                            //out.println("<tr style='color:yellow;background-color: navy;'>");
                            out.println("<tr style='color:#333334;background-color:goldenrod;'>");
                        } else {
                            //out.println("<tr style='color:navy;background-color: white;'>");
                            out.println("<tr style='color:#333334;background-color:#fffacd;'>");
                        }
                        linha++;

                %>

                <%--<tr style="color:#333333;background-color: #FFFBD6;">--%>


                <td><%=lancto.getDebito()%></td>
                <td><%=lancto.getCredito()%></td>
                <td><%=lancto.getEmissa()%></td>
                <td><%=lancto.getHistorico()%></td>
                <td><%=lancto.getObs()%></td>
                <td text-align="right"><%=lancto.getV()%> </td> 
                <td text-align="right"><%=lancto.getVencto()%></td>
                <td><a href="http://<%=request.getServletContext().getInitParameter("IP")%>/alteramovto.jsp?codigo=<%=lancto.getIdmovimento()%>&opcao_debito=<%=lancto.getDebito()%>&opcao_credito=<%=lancto.getCredito()%>&dt_emissao=<%=lancto.getEmissao()%>&hist=<%=lancto.getHistorico()%>&obs=<%=lancto.getObs()%>&valor=<%=lancto.getValor()%>&dt_vencto=<%=lancto.getVencimento()%>&opcaovigente=<%=request.getParameter("opcaovigente")%>&opcaoradio=<%=request.getParameter("opcaoradio")%>"><img src="editar16.png"></a></td>
                <td><a href="http://<%=request.getServletContext().getInitParameter("IP")%>/excluimovto2.jsp?codigo=<%=lancto.getIdmovimento()%>&opcao_debito=<%=lancto.getDebito()%>&opcao_credito=<%=lancto.getCredito()%>&dt_emissao=<%=lancto.getEmissao()%>&hist=<%=lancto.getHistorico()%>&obs=<%=lancto.getObs()%>&valor=<%=lancto.getValor()%>&dt_vencto=<%=lancto.getVencimento()%>&opcaovigente=<%=request.getParameter("opcaovigente")%>&opcaoradio=<%=request.getParameter("opcaoradio")%>"><img src="excluir16.png"></a></td>

                </tr>

                <%
                    }
                %>

            </table>

            <button name="acao" value=" < "><img src="seta_voltar.png"></button>
            <button name="acao" value="Cadastrar"><img src="adicionar16.png"></button>
            <%--<button name="acao" value="Voltar">Voltar</button>--%>
            <button name="acao" value=" > "><img src="seta_avancar.png"></button>
        </form>
    </center>

</body>
<jsp:include page="footer.jsp" />

<script type="text/javascript">

    function pegaData() {
        var texto = document.getElementById('dataIrPara');
        var texto2 = document.getElementById('pesquisa');
        var data = new Date();
        var d = '' + data.getDay();
        if (d.length < 2) {
            d = '0' + d;
        }
        var m = '' + data.getMonth();
        if (m.length < 2) {
            m = '0' + m;
        }
        var a = data.getFullYear();
        alert(d + "/" + m + "/" + a);
        texto.value = d + "/" + m + "/" + a;
        texto2.value = d + "/" + m + "/" + a;
    }

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
</script>

</html>



