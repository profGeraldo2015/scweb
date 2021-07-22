<%-- 
    Document   : visualizamovto2
    Created on : 13/04/2016, 07:48:58
    Author     : Geraldo
--%>

<%@page import="br.com.model.Relatorio2"%>
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

    <jsp:include page="header.jsp" />
    
    <body>
        
         <form name="voltar" action="ServletSc" method="GET">
            <input type="submit" name="acao" value="Voltar">
        </form>

        <%
          // Este exemplo mostra como paginar os resultados de uma
            // tabela MySQL
            // o nome da base de dados é "test"

            String url = "jdbc:mysql://localhost/sc";
            String usuario = "root";
            String senha = "";
            Relatorio2 data = new Relatorio2();
            Connection conn = null;

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                conn = DriverManager.getConnection(url, usuario, senha);
            } catch (SQLException ex) {
                out.println("SQLException: " + ex.getMessage() + "<br>");
                out.println("SQLState: " + ex.getSQLState() + "<br>");
                out.println("VendorError: " + ex.getErrorCode() + "<br>");
            } catch (Exception e) {
                out.println("Problemas ao tentar conectar com o banco de dados");
            }

            // conn é a conexão com o banco de dados
            int limit = 12; // quantidade de resultados por página                

            // obtém a quantidade de registros
            PreparedStatement pstmt = conn.prepareStatement(
                    "SELECT COUNT(*) AS c FROM movimento");
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int total_rows = Integer.parseInt(rs.getString("c"));

            String pagina = request.getParameter("pagina"); // página atual
            if (pagina == null) {
                pagina = "1";
            }

            int limitValue = (Integer.parseInt(pagina) * limit) - limit;

            PreparedStatement pstmt2 = conn.prepareStatement(
                    "SELECT * FROM movimento order by dt_vencto LIMIT " + limitValue + ", " + limit);
            ResultSet rs2 = pstmt2.executeQuery();

            out.print("<table border='2'><tr>");
            out.print("<th>Debito</th>");
            out.print("<th>Credito</th>");
            out.print("<th>Data</th>");
            out.print("<th>Historico</th>");
            out.print("<th>Observacao</th>");
            out.print("<th>Valor</th>");
            out.print("<th>Vencimento</th></tr>");

            while (rs2.next()) {

                out.println("<tr>");
            //int id = rs2.getInt("idmovimento");
                //out.println("<td>"  id + "</td>");

                String DEBITO = rs2.getString("CT_DEBITO");
                out.println("<td>" + DEBITO + "</td>");

                String CREDITO = rs2.getString("CT_CREDITO");
                out.println("<td>" + CREDITO + "</td>");

                String EMISSAO = data.DtoC(rs2.getDate("dt_emissao"));
                out.println("<td>" + EMISSAO + "</td>");

                String HIST = rs2.getString("HIST");
                out.println("<td>" + HIST + "</td>");
                
                String OBS = rs2.getString("OBS");
                out.println("<td>" + OBS + "</td>");
                
                float valor10 = rs2.getInt("valor10");
                out.println("<td>" + valor10 + "</td><br>");
                
                String vencto = data.DtoC(rs2.getDate("dt_vencto"));
                out.println("<td>" + vencto + "</td>");
                
               
                
                out.println("</tr>");
            }

            int anterior;

            if (Integer.parseInt(pagina) != 1) {
                anterior = Integer.parseInt(pagina) - 1;
                out.println("</table><a href=?pagina=" + anterior + ">" + limit + " Anteriores</a>");
            } else {
                out.println(limit + " Anteriores ");
            }

            int numOfPages = total_rows / limit;
            int i;

            for (i = 1; i <= numOfPages; i++) {
                if (i == Integer.parseInt(pagina)) {
                    out.println("<b>" + i + "</b> ");
                } else {
                    out.println("<a href=?pagina=" + i + ">" + i + "</a> ");
                }
            }

            if ((total_rows % limit) != 0) {
                if (i == Integer.parseInt(pagina)) {
                    out.println(i + " ");
                } else {
                    out.println("<a href=?pagina=" + i + ">" + i + "</a> ");
                }
            }
            int proxima;
            if ((total_rows - (limit * Integer.parseInt(pagina))) > 0) {
                proxima = Integer.parseInt(pagina) + 1;

                out.println("<a href=?pagina=" + proxima + ">Próximos " + limit + "</a>");
            } else {
                out.println("Próximos " + limit);
            }
            out.println("<a href="http://<%=getServletContext().getInitParameter("IP")%>/menu.jsp">Voltar</a>");
        %>
       
    </body>
    <jsp:include page="footer.jsp" />
</html>
