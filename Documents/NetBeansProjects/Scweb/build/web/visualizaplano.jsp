
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <jsp:include page="header.jsp" />
    <body>
        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="jdbc:mysql://127.0.0.1/sc"
                           user="root"  password=""/>

        <sql:query dataSource="${snapshot}" var="result">
            SELECT * from plano order by numero;
        </sql:query>

    <center>
        <h1>Plano de Contas</h1>

        <form name="visualiza" action="ServletPlano" method="GET">
            <input type="submit" name="acao" value="Cadastrar">

        </form>

        <form name="pesquisar" action="ServletPlano" method="GET">
            <select name="opcao_pesquisar">
                <option value="numero" selected="selected">NUMERO</option>
                <option value="descricao">Descricao</option>
            </select>      
            <input type="text" name="pesquisa" value="" size="50" />
            <input type="submit" name="acao" value="Pesquisar">
            <input type="submit" name="acao" value="Voltar">

        </form>

        <h2><%=request.getSession().getAttribute("msg")%></h2>

        <br/>

        <table border='2' width="100%">
            <tr>

                <th>Numero</th>
                <th>Descricao</th>
                <th>Data</th>
                <th>Saldo Inicial - Emissao R$ </th>
                <th>Saldo Inicial - Vencimento R$ </th>
                <th>Data $</th>
                <th>Saldo Inicial - Emissao $ </th>
                <th>Saldo Inicial - Vencimento $ </th>
                <th>Opcao</th>

            </tr>
            <c:forEach var="row" items="${result.rows}">
                <tr>

                    <td><c:out value="${row.numero}"/></td>
                    <td><c:out value="${row.descricao}"/></td>
                    <td><c:out value="${row.dt_saldo}"/></td>
                    <td><c:out value="${row.saldo_inic}"/></td>
                    <td><c:out value="${row.saldo_iniv}"/></td>
                    <td><c:out value="${row.dt_saus}"/></td>
                    <td><c:out value="${row.c_saieus}"/></td>
                    <td><c:out value="${row.c_saivus}"/></td>
                    <td><a href="http://<%=getServletContext().getInitParameter("IP")%>/alteraplano.jsp?idplano=${row.idplano}&numero=${row.numero}&descricao=${row.descricao}&saldo_inic=${row.saldo_inic}&saldo_iniv=${row.saldo_iniv}&dt_saldo=${row.dt_saldo}&dt_saus=${row.dt_saus}&c_saieus=${row.c_saieus}&c_saivus=${row.c_saivus}">Alterar<a/></td>

                </tr>
            </c:forEach>
        </table>

    </center>

</body>
<jsp:include page="footer.jsp" />
</html>
