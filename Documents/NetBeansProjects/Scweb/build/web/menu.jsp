<%-- 
    Document   : menu
    Created on : 21/06/2014, 23:57:21
    Author     : User

  <form action="visualizamovto.jsp" method="post">
                <input type="submit" value="Movimento Contabil" name="acao"  />
            </form>
trecho acima retirado em 09/05/2016 quando da implementacao da paginacao

--%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Sistema Administrativo Contabil</title>
        <link rel="stylesheet" type="text/css" href="estilo.css">

    </head>
    <body>
        <center>
            <h1>Cadastros</h1>
            </br>

          
            <form action="ServletSc" method="post">
                <input type="submit" value="Movimento Contabil" name="acao"  />
            </form>

            <form action="visualizaplano.jsp" method="post">
                <input type="submit" value="Plano de Contas" name="acao"  />
            </form>
            <h2>Relatorios</h2>
            </br>

            <form action="ServletRel" method="post">
                <input type="submit" value="Extrato Contabil" name="acao"  />
            </form>
            <form Action="ServletRel" method="post">
                <input type="submit" value="Diario por Conta" name="acao"  />
            </form>
            <form Action="ServletRel" method="post">
                <input type="submit" value="Diario Geral" name="acao"  />
            </form>
            <form Action="ServletRel" method="post">
                <input type="submit" value="Balancete Contabil" name="acao"  />
            </form>
            <form Action="ServletRel" method="post">
                <input type="submit" value="Demonstrativo Contabil" name="acao"  />
            </form>

        </center>
    </body>
</html>
