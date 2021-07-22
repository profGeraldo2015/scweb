<%-- 
    Document   : implistagem
    Created on : 23/06/2014, 08:06:05
    Author     : User
--%>


<%@page import="br.com.model.Relatorio3"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="br.com.model.Pagina"%>
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
    <STYLE TYPE="text/css">
        <!--
        td,th{font-family: Courier New; 
              font-size: 12pt;
              color: yellow;
        }
        .direita{
            text-align: right;
        }
        
        -->
    </STYLE>    
    <jsp:include page="header.jsp" />
    <body>
<%

//Relatorio3 p = new Relatorio3();



%>
    <center>
        <h1>Diário por Conta de <%=request.getAttribute("inicio")%> até <%=request.getAttribute("fim")%></h1>

        <br/>
        <table id="tbMovto" border='2' width="80%" bgcolor="navy">
            <tr>


                <th>Débito</th>
                <th>Crédito</th>
                <th>Data</th>
                <th>Histórico</th>
                <th>Observação</th>
                <th>Valor</th>
                <th>Vencimento</th>

            </tr>
            <%
                List<MovimentoBean> result = (List<MovimentoBean>) request.getAttribute("lista");
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
                <td class="direita"><%=lancto.getV()%> </td>
                <td align="right"><%=lancto.getVencto()%></td>

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



