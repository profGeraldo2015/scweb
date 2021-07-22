<%-- 
    Document   : pesquisamovto
    Created on : 21/06/2014, 23:17:22
    Author     : User
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
  
        <%
            String opcao = null;
            if (request.getAttribute("opcao").equals("dt_vencto")) {
                opcao = "Vencimento";
            }
            if (request.getAttribute("opcao").equals("dt_emissao")) {
                opcao = "Emissão";
            }
            if (request.getAttribute("opcao").equals("hist")) {
                opcao = "Histórico";
            }
            if (request.getAttribute("opcao").equals("obs")) {
                opcao = "Observação";
            }


        %>

              <h2>Resultado da pesquisa por : <%=opcao%></h2>

        <br/>


        <table id="tbMovto" border='2' width="90%" bgcolor="navy">
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
            <%
                List<MovimentoBean> result = (List<MovimentoBean>) request.getAttribute("lista");
                Iterator iterator = result.iterator();
                int linha = 1;
                while (iterator.hasNext()) {
                    MovimentoBean lancto = (MovimentoBean) iterator.next();
                    if ((linha % 2) == 0) {
                        out.println("<tr style='color:yellow;background-color: navy;'>");
                    } else {
                        out.println("<tr style='color:navy;background-color: white;'>");
                    }
                    linha++;


            %>

          
                <td><%=lancto.getDebito()%></td>
                <td><%=lancto.getCredito()%></td>
                <td><%=lancto.getEmissa()%></td>
                <td><%=lancto.getHistorico()%></td>
                <td><%=lancto.getObs()%></td>
                <td align="right"><%=lancto.getV()%></td>
                <td align="right"><%=lancto.getVencto()%></td>

                <td><a href="http://<%=request.getServletContext().getInitParameter("IP")%>/alteramovto.jsp?codigo=<%=lancto.getIdmovimento()%>&opcao_debito=<%=lancto.getDebito()%>&opcao_credito=<%=lancto.getCredito()%>&dt_emissao=<%=lancto.getEmissao()%>&hist=<%=lancto.getHistorico()%>&obs=<%=lancto.getObs()%>&valor=<%=lancto.getValor()%>&dt_vencto=<%=lancto.getVencimento()%>&opcaovigente=<%=request.getParameter("opcaovigente")%>&opcaoradio=<%=request.getParameter("opcaoradio")%>">Alterar</a></td>
                <td><a href="http://<%=request.getServletContext().getInitParameter("IP")%>/excluimovto2.jsp?codigo=<%=lancto.getIdmovimento()%>&opcao_debito=<%=lancto.getDebito()%>&opcao_credito=<%=lancto.getCredito()%>&dt_emissao=<%=lancto.getEmissao()%>&hist=<%=lancto.getHistorico()%>&obs=<%=lancto.getObs()%>&valor=<%=lancto.getValor()%>&dt_vencto=<%=lancto.getVencimento()%>&opcaovigente=<%=request.getParameter("opcaovigente")%>&opcaoradio=<%=request.getParameter("opcaoradio")%>">Excluir</a></td>

            </tr>

            <%
                }
            %>

        </table>
        <form name="pesquisamovto" action="ServletSc" method="GET">
            <input type="hidden" name="opcao_pesquisar" value="<%=request.getParameter("opcao_pesquisar")%>"size="8" />
            <input type="hidden" name="opcaovigente" value="<%=request.getParameter("opcaovigente")%>"size="8" />
            <input type="hidden" name="opcaoradio" value="<%=request.getParameter("opcaoradio")%>"size="8" />
            <input type="hidden" name="dataIrPara" value="<%=request.getParameter("dataIrPara")%>"size="8" />
            <input type="submit" value="Movimento Contabil" name="acao" />
        </form>
    </center>

</body>
<jsp:include page="footer.jsp" />
</html>



