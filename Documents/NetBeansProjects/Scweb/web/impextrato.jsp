<%-- 
    Document   : impextrato
    Created on : 22/06/2014, 23:59:54
    Author     : impextrato
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
         <h1>Extrato Contabil</h1>
            
            <br/>
            <table border='2' width="100%">
         
            <%
                List<String> result =(List<String>) request.getAttribute("listaextrato");
                
                for (String linha: result){
           
            %>
            <tr<td>
                
                <%
            out.print("<br/>"+linha);
           
                }
            %>
                </td></tr>
            </table>
            <form name="impextrato" action="extrato.jsp" method="GET">
                <input type="submit" value="Voltar" name="acao" />
            </form>
            </center>
    
     </body>
     <jsp:include page="footer.jsp" />
     </html>
       
           
  
