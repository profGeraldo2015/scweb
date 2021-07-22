<%-- 
    Document   : impdemo
    Created on : 03/05/2016, 22:12:01
    Author     : Geraldo
--%>

<%@page import="br.com.model.BLinha"%>
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
    
     <head>  
          <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
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
         <h1>Demonstrativo Contabil</h1>
            
            <br/>
             <form name="impdemo" action="ServletRel" method="GET">
                <input type="submit" value="Voltar" name="acao" />
            </form>
            <table id="tbMovto" border='2' width=90% bgcolor="navy">
           <tr>
                <th>CONTA</th>
                <th>DESCRICAO</th>
                <th>SALDO ANTERIOR</th>
                <th>DEBITOS</th>
                <th>CREDITOS</th>
                <th>SALDO ATUAL</th>
               
                
             </tr>
            <%
                List<BLinha> result =(List<BLinha>) request.getAttribute("listademo");
                
                for ( BLinha linha : result){
              if( linha.getConta().substring(4,8).equals("0000")){
            %>
            <tr><td>
                
                <%
                     out.print("<br/>"+linha.getConta()+"</td><td>"+linha.getDescricao()+" </td> <td align='right'> "+linha.getSA()+"</td><td align='right'>"+linha.getD()+"</td><td align='right'>"+linha.getC()+"</td><td align='right'>"+linha.getS()+"</td>");
              
              } 
                }
            %>
                </td>
            </tr>
            </table>
            <form name="impdemo" action="ServletRel" method="GET">
                <input type="submit" value="Voltar" name="acao" />
            </form>
            </center>
    
     </body>
     <jsp:include page="footer.jsp" />
     </html>
       
           
  

