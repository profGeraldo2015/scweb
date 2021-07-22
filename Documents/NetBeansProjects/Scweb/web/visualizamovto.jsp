<%-- 
    Document   : visualizamovto
    Created on : 21/06/2014, 22:55:39
    Author     : User

url="<%=getServletContext().getInitParameter("DB")%>"
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="scripts.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="estilo.css">

    <jsp:include page="header.jsp" />
    <body>

        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver" url="jdbc:mysql://127.0.0.1/sc"
                           user="root"  password=""/>

        <sql:query dataSource="${snapshot}" var="result" >

            select idmovimento,ct_debito,ct_credito,dt_emissao,hist,obs,valor10,dt_vencto from movimento order by dt_vencto;
        </sql:query>

    <center>
        <h1>Movimento Contabil</h1>

        <form name="visualiza" action="ServletSc" method="GET">


            <input type="submit" name="acao" value="Cadastrar">

        </form>
        <div id="pesquisa"> 
            <form name="pesquisar" action="ServletSc" method="GET">
                <select name="opcao_pesquisar">
                    <option value="hist" selected="selected">Historico</option>
                    <option value="obs">Observacao</option>
                    <option value="dt_emissao">Data</option>
                    <option value="dt_vencto">Vencimento</option>

                </select>      
                <input type="text" name="pesquisa" value="" size="50" />

                <input type="submit" name="acao" value="Pesquisar">
                <input type="submit" name="acao" value="Voltar">

            </form>
        </div>

        <h2><%=request.getSession().getAttribute("msg")%></h2>

        <br/>

        <table border='2'>
            <tr>

                <th>Debito</th>
                <th>Credito</th>
                <th>Data</th>
                <th>Historico</th>
                <th>Observacao</th>
                <th>Valor</th>
                <th>Vencimento</th>
                <th>Alterar</th>
                <th>Excluir</th>

            </tr>
            <c:forEach var="row" items="${result.rows}">
                <tr>
                    <td><c:out value="${row.ct_debito}"/></td>
                    <td><c:out value="${row.ct_credito}"/></td>
                    <td><c:out value="${row.dt_emissao}"/></td>
                    <td><c:out value="${row.hist}"/></td>
                    <td><c:out value="${row.obs}"/></td>
                    <td><c:out value="${row.valorus}"/></td>
                    <%

                    %>
                    <td><c:out value="${row.dt_vencto}" /></td>
                    <td><a href="http://<%=getServletContext().getInitParameter("IP")%>/alteramovto.jsp?codigo=${row.idmovimento}&opcao_debito=${row.ct_debito}&opcao_credito=${row.ct_credito}&dt_emissao=${row.dt_emissao}&hist=${row.hist}&obs=${row.obs}&valor=${row.valor10}&dt_vencto=${row.dt_vencto}">Alterar</a></td>
                    <td><a href="http://<%=getServletContext().getInitParameter("IP")%>/excluimovto.jsp?codigo=${row.idmovimento}&opcao_debito=${row.ct_debito}&opcao_credito=${row.ct_credito}&dt_emissao=${row.dt_emissao}&hist=${row.hist}&obs=${row.obs}&valor=${row.valor10}&dt_vencto=${row.dt_vencto}">Excluir</a></td>


                </tr>
            </c:forEach>
        </table>
        <form name="voltar" action="ServletSc" method="GET">

            <input type="submit" name="acao" value="Voltar">

        </form>
    </div>
</center>

</body>
<jsp:include page="footer.jsp" />
</html>
