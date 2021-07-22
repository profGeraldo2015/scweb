<%-- 
    Document   : implistagemgeral
    Created on : 13/05/2016, 20:15:01
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
   <STYLE TYPE="text/css">
<!--
td,th{font-family: Courier New; 
   font-size: 12pt;
   color: yellow;
}
-->
</STYLE>    
    <jsp:include page="header.jsp" />
    <body>
        
            <center>
            <h1>Diário Geral</h1>
            
            <br/>
            <table id="tbMovto" border='2' width="80%" bgcolor="navy">
            <tr>
           
                
                <th>Débito</th>
                <th>Crédito</th>
                <th>Emissão</th>
                <th>Histórico</th>
                <th>Observação</th>
                <th>Valor</th>
                <th>Vencimento</th>
             
            </tr>
            <%
                List<MovimentoBean> result =(List<MovimentoBean>) request.getAttribute("listaGeral");
                Iterator iterator = result.iterator();
                
                while (iterator.hasNext()){
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
       
           
  
