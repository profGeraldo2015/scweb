<%-- 
    Document   : sobre
    Created on : 14/05/2016, 07:52:47
    Author     : Geraldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         
          <link rel="stylesheet" type="text/css" href="estilo.css">
   
        <title>ScWeb v1.0</title>
    </head>
        <jsp:include page="menusus.jsp" />

    <jsp:include page="header.jsp" />
    <body>

    <center>
         <img src="figura2.jpg" width="800" height="320" alt="Figura2" align="center">
        <p>Versao 1.0 - Março/Abril 2016 - Desenvolvido utilizando JSP e ServLet (JAVA), banco</p>
        <p> de dados MySQL</p>
        <p> Versao 1.1 - Abril 2016 - Versao utilizando AJAX na pagina Movimento Contabil</p>
        <p> Versao 1.2 - Maio 2016 - Versao utilizando JavaScript na pagina Movimento Contabil</p>
        
         <form name="sobre" action="ServletSc" method="GET">
             <button value="Voltar" name="acao">Voltar</button>
    </form>
    </center>
  
</body>
<jsp:include page="footer.jsp" />
</html>
