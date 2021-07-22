
<%-- 
    Document   : login.jsp
    Created on : 13/03/2014, 21:44:48
    Author     : ProfGEraldo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
        <script src="scripts.js" type="text/javascript"></script>
        <link rel="stylesheet" type="text/css" href="estilo.css">

        <title>Login - Sistema Administrativo Contabil</title>

    </head>
    <body> 
        <div id="mensagem">
            Esta mensagem e de exemplo
        </div>
        <div id="login-box">
            <div id="login-box-interno">

                <div id="login-box-label">
                    <h1>Autenticacao SC</h1>    
                </div>   

                <form name="formulario" id="formulario" action="ServletLogin" method="POST" >

                    <div class="input-div" id="input-usuario">
                        <input type="text" id="login" name="login" value="Digite o usuario" size="10" />            
                    </div>
                    <div class="input-div" id="input-senha">    
                        <input type="password" id="senha" name="senha" value="Senha" size="10" />
                    </div>
                    <div id="botoes">
                        <div id="botao">
                            <input type="submit" name="acao" value="Enviar"/>
                        </div>
                    </div>
                </form>
            </div>    
        </div>
    </body>
</html>
