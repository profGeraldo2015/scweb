<%-- 
    Document   : impextrato2
    Created on : 06/05/2016, 15:54:32
    Author     : Geraldo
--%>


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

    <jsp:include page="header.jsp" />
    <body>

    <center>
        <h1> Extrato </h1>

        <br/>
         <form name="impextrato" action="extrato.jsp" method="GET">
            <input type="submit" value="Voltar" name="acao" />
        </form>
        <table border='2' width="100%" bgcolor="navy">

            <%
                List<String> result = (List<String>) request.getAttribute("listaextrato");
                Iterator iterator = result.iterator();

                while (iterator.hasNext()) {
                    String lancto = (String) iterator.next();


            %>

            <tr>
            <td><FONT COLOR="yellow" FACE="Courier new" SIZE=3><%=lancto%></font></td>

            </tr>

            <%
                }
            %>

        </table>
        <form name="impextrato" action="extrato.jsp" method="GET">
            <input type="submit" value="Voltar" name="acao" />
        </form>
    </center>

</body>
<jsp:include page="footer.jsp" />
</html>



