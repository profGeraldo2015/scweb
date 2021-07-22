
<%--

 <%=getServletContext().getServletContextName()%>

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <body>
    <center>
        <%
       // ServletContext ctx = getServletContext();
	//String NomeAplicacao = ctx.getInitParameter("aplicacao");

        
        %>
     <h6><%=getServletContext().getInitParameter("aplicacao")%> - Todos os direitos reservados</h6>
    </center>
</body>
</html>
