<%-- 
    Document   : menu2
    Created on : 10/05/2016, 08:22:46
    Author     : Geraldo
           body {
                        padding:0px;
                        margin:0px;
                        background-color: lightblue;
                    }

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
    "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html  xml:lang="pt" lang="pt">

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

            <link rel="stylesheet" type="text/css" href="estilo.css">

                <style type="text/css">
                    <!--
         
                    #menu ul {
                        padding:0px;
                        margin:0px;
                        float: left;
                        width: 100%;
                        background-color:#EDEDED;
                        list-style:none;
                        font:100% Tahoma;
                    }

                    #menu ul li { display: inline; }

                    #menu ul li a {
                        background-color:#EDEDED;
                        color: #333;
                        text-decoration: none;
                        border-bottom:3px solid #EDEDED;
                        padding: 2px 10px;
                        float:left;
                    }

                    #menu ul li a:hover {
                        background-color:#D6D6D6;
                        color: #6D6D6D;
                        border-bottom:3px solid #EA0000;
                    }
                    -->
                </style>
                </head>

                <body>

                    <div id="menu">
                        <ul>
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/ServletSc?acao=Movimento&offset=0&opcaovigente=dt_vencto&opcao=dt_vencto&opcao_pesquisar=dt_vencto&opcaoradio=dt_vencto">Movimento Contábil</a></li>
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/ServletPlano?acao=Plano">Plano de Contas</a></li>
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/extrato.jsp">Extrato Contábil</a></li>
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/listagem.jsp">Diário por Conta</a></li>
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/listagemGeral.jsp">Diário Geral</a></li>
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/balancete.jsp">Balancete</a></li>
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/demonstrativo.jsp">Demonstrativo</a></li>

                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/ServletSc?acao=Paginada2&offset=0&opcaoradio=dt_vencto&opcao=dt_vencto">Paginada AJAX</a></li>
                           
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/ServletSc?acao=Consulta&offset=0&opcaovigente=dt_vencto&opcao=dt_vencto&opcao_pesquisar=dt_vencto&opcaoradio=dt_vencto">Consulta Movimento</a></li>
                            <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/sobre.jsp">Sobre</a></li>
                             <li><a href="http://<%=getServletContext().getInitParameter("IP")%>/pagjquery.jsp">Pagina Jquery</a></li>
                            
                        </ul>
                    </div>  
                    <jsp:include page="header.jsp" />
                      <%
        
        out.println("<B>Usuario : </B>" + session.getAttribute("usuario_autenticado") + "<br>");
        
        %>  
                    <center>
                        <img src="figura2.jpg" width="720" height="290" alt="Figura2" align="center">
                    </center>
                    <jsp:include page="footer.jsp" />
                </body>
                </html>