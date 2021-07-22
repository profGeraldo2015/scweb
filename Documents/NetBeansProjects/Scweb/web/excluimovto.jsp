%<-- 
    Document   : excluimovto
    Created on : 12/04/2016, 21:40:43
    Author     : Geraldo
   url="jdbc:mysql://127.0.0.1/sc"
--%>

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
    <body>
        <c:set var="url" value='<%=request.getServletContext().getInitParameter("DB")%>'/>
        <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
                           url="${url}"

                           user="root"  password=""/>

        <sql:query dataSource="${snapshot}" var="result">
            SELECT numero,descricao from plano order by numero;
        </sql:query>

        <div id="mensagem">
            Esta mensagem e de exemplo
        </div>
        <div id="inclui-box">
            <div id="inclui-box-interno">

                <div id="inclui-box-label">
                    <h1>Confirme a exclusão </h1>
                </div>   

                <form name="form_principal" action="ServletSc" method="GET">
                    <table>
                        <tr>
                            <td>DEBITO : </td>
                            <td><input type="text" name="opcao_debito"disabled="disabled" value="<%=request.getParameter("opcao_debito")%>"size="8" /></td>
                            <td>
                                <select name="opcao_debito">
                                    <c:forEach var="row" items="${result.rows}">
                                        <option c:out value="${row.numero}">${row.numero} - ${row.descricao}</option>>/>
                                    </c:forEach>
                                </select>      
                            </td>
                        </tr>
                        <tr>
                            <td>CREDITO : </td>
                            <td><input type="text" name="opcao_credito"disabled="disabled" value="<%=request.getParameter("opcao_credito")%>"size="8" /></td>
                            <td>
                                <select name="opcao_credito">
                                    <c:forEach var="row" items="${result.rows}">
                                        <option c:out value="${row.numero}">${row.numero} - ${row.descricao}</option>>/>
                                    </c:forEach>
                                </select>      
                            </td>
                        </tr>
                        <tr><td>DATA :</td> 
                            <td><input type="date" name="dt_emissao"disabled="disabled" value="<%=request.getParameter("dt_emissao")%>"size="8" /></td>
                        </tr>
                        <tr><td>HISTORICO :</td>  
                            <td><input type="text" name="hist" disabled="disabled"value="<%=request.getParameter("hist")%>"size="50" /></td>
                        </tr>
                        <tr><td>OBSERVACAO :</td>  
                            <td><input type="text" name="obs" disabled="disabled"value="<%=request.getParameter("obs")%>"size="50" /></td>
                        </tr>
                        <tr><td>VALOR :</td>  
                            <td><input type="text" name="valor"disabled="disabled" value="<%=request.getParameter("valor")%>"size="12" /></td>
                        </tr>
                        <tr><td>VENCIMENTO :</td>  
                            <td><input type="date" name="dt_vencto"disabled="disabled" value="<%=request.getParameter("dt_vencto")%>"size="8" /></td>
                        </tr>
                    </table>
                    <center>
                        <input type="submit" value="Excluir" name="acao" />
                        <input type="hidden" name="idmovimento" value="<%=request.getParameter("codigo")%>" size="11"  />
                        <input type="text" value="<%=request.getParameter("opcaovigente")%>" name="opcaovigente" />
                        <input type="submit" value="Cancelar" name="acao" />
                    </center>
                </form>
            </div>
        </div>
    </body>
    <jsp:include page="footer.jsp" />
</html>
