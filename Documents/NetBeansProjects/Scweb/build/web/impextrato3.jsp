<%-- 
    Document   : impextrato3
    Created on : 14/05/2016, 21:53:29
    Author     : Geraldo
--%>


<%@page import="br.com.model.Tlinha"%>
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

        <form name="impextrato" action="ServletRel" method="GET">
            <input type="submit" value="Voltar" name="acao" />
        </form>
        <table border='2' width="80%" bgcolor="goldenrod">

            <%
                List<Tlinha> result = (List<Tlinha>) request.getAttribute("listaextrato");
                Iterator iterator = result.iterator();

                while (iterator.hasNext()) {
                    Tlinha lancto = (Tlinha) iterator.next();


            %>

            <tr>

                <%                        if (lancto.getTipo().equals("h1")) {
                        out.print("<td align='center' style='background-color:goldenrod'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                    } else if (lancto.getTipo().equals("h2")) {
                        out.print("<td align='right' style='background-color:#fffacd'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");

                    } else if (lancto.getTipo().equals("d1")) {
                        out.print("<td align='left'style='background-color:#fffacd'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                    } else if (lancto.getTipo().equals("d2")) {
                        out.print("<td align='right'style='background-color:goldenrod'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");

                    } else if (lancto.getTipo().equals("f1")) {
                        out.print("<td align='right'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");
                    } else if (lancto.getTipo().equals("f2")) {
                        out.print("<td align='right'><font COLOR='#333334' FACE='Courier new' SIZE=3>" + lancto.getContent() + "</font></td>");

                    }
                %>


            </tr>

            <%
                }
            %>

        </table>
        <form name="impextrato" action="ServletRel" method="GET">
            <input type="submit" value="Voltar" name="acao" />
        </form>
    </center>

</body>
<jsp:include page="footer.jsp" />
</html>




