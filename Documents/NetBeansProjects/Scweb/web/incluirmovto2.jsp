<%-- 
    Document   : incluirmovto2
    Created on : 23/06/2014, 08:24:33
    Author     : User
--%>

<%@page import="br.com.model.PlanoBean"%>
<%@page import="br.com.model.PlanoDB"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <STYLE TYPE="text/css">
        <!--
        td,th{font-family: Calibri; 
              font-size: 12pt;
              color: yellow;

        }

        -->
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="scripts.js" type="text/javascript"></script>
    <script src="js/valida.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="estilo.css">
    <body>


        <div id="mensagem">
            <h2>Inclusão de movimento contábil</h2>
        </div>
        <div id="inclui-box">
            <div id="inclui-box-interno">

                <div id="inclui-box-label">
                    <p>Preencha o formulário abaixo: </p>
                </div>   

                <%--<form name="form" action="ServletSc" method="GET" onsubmit="return validaForm('hist','obs');">--%>
                <form name="form" action="ServletSc" method="GET" onsubmit="">


                    <center>  
                        <table id="tbMovto" border="1" bgcolor="navy">
                            <tr><td>Tipo de lançamento</td><td>

                                    <select name="tipo" id="tipo" onchange="buscaTipo();">
                                        <%
                                            ArrayList<String> tipos = new ArrayList();
                                            tipos.add("Selecione");
                                            tipos.add("SUPER");
                                            tipos.add("ALIMENTACAO");
                                            tipos.add("GASOLINA");
                                            tipos.add("EXTRAS");
                                            tipos.add("ACOUGUE");
                                            for (int x = 0; x < tipos.size(); x++) {

                                        %>

                                        <option value="<%=tipos.get(x)%>"><%=tipos.get(x)%> </option>

                                        <%
                                            }
                                        %>
                                    </select>      
                                </td>
                            </tr>
                            <tr>
                                <td>DÉBITO : </td>
                                <td><div id="debito"><input type="text" name="txt_debito" id="txt_debito" size="8"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" /><span>Só numeros</span></div></td>
                            </tr>
                            <tr>
                                <td></td>
                                <%
                                    PlanoDB contaD = new PlanoDB(request.getServletContext().getInitParameter("DB"));

                                    List<PlanoBean> lista = contaD.carregaComboPlano();


                                %>  
                                <td>
                                    <select name="opcao_debito" id="opcao_debito" onchange="pegaDebito(txt_debito);">
                                        <option value="Escolha a conta...">Escolha a conta...</option>
                                        <%                                         for (int i = 0; i < lista.size(); i++) {
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
                            <tr>
                                <td>CRÉDITO : </td>
                                <td><div id="credito"><input type="text" name="txt_credito" id="txt_credito" size="8" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></div></td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <select name="opcao_credito" id="opcao_credito" onchange="pegaCredito(txt_credito);">
                                        <option value="Escolha a conta...">Escolha a conta...</option>
                                        <%
                                            for (int i = 0; i < lista.size(); i++) {
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
                            <tr><td>EMISSÃO :</td> 
                                <td><input type="date" name="dt_emissao" value=""size="8" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></td>
                            </tr>
                            <tr><td>VENCIMENTO :</td>  
                                <td><input type="date" name="dt_vencto" value="" size="8"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" /></td>
                            </tr>
                            <tr><td>HISTÓRICO:</td>  
                                <td><input type="text" name="hist" id="hist" size="50" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></td>
                            </tr>
                            <tr><td>OBSERVAÇÃO:</td>  
                                <td><input type="text" name="obs" id="obs" size="50" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></td>
                            </tr>
                            <tr><td>VALOR :</td>  
                                <td><input type="text" name="valor" id="valor" size="12" value="0" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" onkeyPress="return(MascaraMoeda(this, '.', ',', event))"/></td>
                            </tr>

                        </table>
                        <input type="hidden" name="opcao_pesquisar" value="<%=request.getParameter("opcao_pesquisar")%>"size="" />
                        <input type="hidden" name="opcaovigente"    value="<%=request.getParameter("opcaovigente")%>"size="" />
                        <input type="hidden" name="opcaoradio"      value="<%=request.getParameter("opcaoradio")%>"size="" />
                        <input type="hidden" name="dataIrPara"      value="<%=request.getParameter("dataIrPara")%>"size="" />                        
                        <%--<input type="submit" value="Gravar" name="acao" onclick="validaForm('hist','obs');"/>--%>
                        <input type="submit" value="Gravar" name="acao" />

                        <input type="reset" value="Limpar" name="limpar" /> 
                        <input type="submit" value="Cancelar" name="acao">

                    </center>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
    <script type="text/javascript">
        function buscaTipo() {

            var tipo = document.getElementById("tipo");


            if (tipo.value == "SUPER") {

                document.getElementById("txt_debito").value = '21400018';
                document.getElementById("txt_credito").value = "11100020";
                document.getElementById("hist").value = "PAG SUPER";
            }
            if (tipo.value == "GASOLINA") {
                document.getElementById("txt_debito").value = "21400005";
                document.getElementById("txt_credito").value = "11100020";
                document.getElementById("hist").value = "PAG POSTO";
            }
            if (tipo.value == "EXTRAS") {
                document.getElementById("txt_debito").value = "21400002";
                document.getElementById("txt_credito").value = "11100020";
                document.getElementById("hist").value = "PAG ";
            }
            if (tipo.value == "ALIMENTACAO") {
                document.getElementById("txt_debito").value = "21400004";
                document.getElementById("txt_credito").value = "11100020";
                document.getElementById("hist").value = "PAG ";
            }
            if (tipo.value == "ACOUGUE") {
                document.getElementById("txt_debito").value = "21400011";
                document.getElementById("txt_credito").value = "11100020";
                document.getElementById("hist").value = "PAG ";
            }
        }

        function pegaCredito(obj) {
            var conta = document.getElementById("opcao_credito");
            obj.value = conta.value;


        }
        function pegaDebito(obj) {
            var conta = document.getElementById("opcao_debito");
            obj.value = conta.value;


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
        function buscaDebito() {
            var campo = window.document.getElementById("opcao_debito");
            var url = "sPesquisa?acao=debito&campo=" + campo.value;
            sendRequest(url, ajaxBuscaDebito);
        }
        function ajaxBuscaDebito() {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    var valor = window.document.getElementById("txt_debito");
                    var retorno = xhr.responseText;
                    valor.innerHTML = retorno;
                }
            }

        }
        function buscaCredito() {
            var campo = window.document.getElementById("opcao_credito");
            var url = "sPesquisa?acao=credito&campo=" + campo.value;

            sendRequest(url, ajaxBuscaCredito);
        }
        function ajaxBuscaCredito() {
            if (xhr.readyState == 4) {
                if (xhr.status == 200) {
                    var valor = window.document.getElementById("txt_credito");
                    var retorno = xhr.responseText;
                    valor.innerHTML = retorno;
                }
            }
        }
        function checkform() {
            if (window.document.form.hist.value == null || window.document.form.hist.value == "") {
                alert("Histórico em branco!!!");
                window.document.form.hist.focus();
                window.document.form.hist.selected();
                return false;
            }
            if (document.form.dt_vencto.value == null || document.form.dt_vencto == "") {
                alert("Vencimento em branco!!!");
                document.form.dt_vencto.focus();
                document.form.dt_vencto.selected();
                return false;
            }
            if (document.form.dt_emissao.value == null || document.form.dt_emissao == "") {
                alert("Emissão em branco!!!");
                document.form.dt_emissao.focus();
                document.form.dt_emissao.selected();
                return false;
            }

        }


    </script>
</html>
