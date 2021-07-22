<%-- 
    Document   : header
    Created on : 31/03/2014, 21:26:16
    Author     : Prof Geraldo
    <%
        Cookie[] cookies = request.getCookies();
        int length = cookies.length;
        for( int  i = 0 ; i<length; i++){
            Cookie cookie = cookies[i];
           if (cookie.getName().equals("usuario")){
            out.println("<B>Usuario : </B>" + cookie.getValue() + "<br>");
           }
        
        }

        
        <%
        
        out.println("<B>Usuario : </B>" + session.getAttribute("login") + "<br>");
        
        %>
        
    <%=getServletContext().getServletContextName()%>
        
        
        %>

  <%
        
        out.println("<B>Usuario : </B>" + session.getAttribute("usuario_autenticado") + "<br>");
        
        %>  
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <link rel="stylesheet" type="text/css" href="estilo.css">
        <title>ScWeb v1.0</title>
    </head>
    <body>
     
       <center>
        <p>Sistema Administrativo Contabil v1.0</p>
        </center>
    </body>
</html>
