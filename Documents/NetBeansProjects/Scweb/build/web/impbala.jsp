<%-- 
    Document   : impbala
    Created on : 26/04/2016, 22:28:54
    Author     : Prof Geraldo

  <STYLE TYPE="text/css">
        <!--
        td,th{font-family: Courier New; 
              font-size: 10pt;
              color: yellow;
        }
        body{
            background-color: lightblue;
        }
        -->
    </STYLE> 

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
  
    <jsp:include page="header.jsp" />
    <body>

    <center>
        <h1>Balancete Contábil</h1>

        <br/>
        <form name="impbala" action="ServletRel" method="GET">
            <input type="submit" value="Voltar" name="acao" />
        </form>
        <table id="tbMovto" border='2' width="90%" bgcolor="navy">
            <tr>
                <th>CONTA</th>
                <th>DESCRIÇÃO</th>
                <th>SALDO ANTERIOR</th>
                <th>DÉBITOS</th>
                <th>CRÉDITOS</th>
                <th>SALDO ATUAL</th>


            </tr>
            <%
                List<BLinha> result = (List<BLinha>) request.getAttribute("listabala");
                int linha2 = 1;
                for (BLinha linha : result) {
                    if(linha2%2 == 0){
                    out.println("<tr style='color:#333333;background-color:#FFFBD6;'>");
                    }else{
                    out.println("<tr style='color:#333333;background-color:white;'>");
                    
                    }
                    
            %>
            
            <%--<tr>--%>
            
            <%              
                out.println("<td>"+ linha.getConta() + 
                "</td><td>" + linha.getDescricao() + 
                "</td> <td align='right'> " + linha.getSA() + 
                "</td><td align='right'>" + linha.getD() + 
                "</td><td align='right'>" + linha.getC() + 
                "</td><td align='right'>" + linha.getS() + "</td>");
               linha2++;
             }
            %>
                </td></tr>
        </table>
        <form name="impbala" action="ServletRel" method="GET">
            <input type="submit" value="Voltar" name="acao" />
        </form>
    </center>

</body>
<jsp:include page="footer.jsp" />
</html>



