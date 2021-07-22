<%-- 
    Document   : listagemGeral
    Created on : 13/05/2016, 20:06:16
    Author     : Geraldo
   <sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
            url="jdbc:mysql://127.0.0.1/sc"
            user="root"  password=""/>

        <sql:query dataSource="${snapshot}" var="result">
            SELECT numero,descricao from plano where substring(numero,5,8) <> "0000" order by numero;
        </sql:query>
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
  <STYLE TYPE="text/css">
        <!--
        td,th{font-family: Calibri; 
              font-size: 12pt;
              color: yellow;

        }
        body{
            background-color: lightblue;
        }
        -->
    </STYLE> 
    <head>  
        <link rel="stylesheet" type="text/css" href="estilo.css">

    </head>
    <jsp:include page="header.jsp"/>
    <body >


    <center>
        <h1>Diário Geral</h1>
        <h2>Preencha os dados abaixo: </h2>

        <form name="form_list_geral" action="ServletRel" method="GET">
            <table border="1" bgcolor="navy" id="tbMovto">
                <td>Data de: </td>
                <td>
                    <select name="opcao_data">
                        <option value="dt_vencto" selected="selected">Vencimento</option>
                        <option value="dt_emissao">Emissao</option>
                    </select>      
                </td>
                <tr>
                    <td>Data Inicial : </td>
                    <td><input type="date" name="inicio" value="" size="8" /></td>
                </tr>
                <tr><td>Data Final : </td>
                    <td> <input type="date" name="fim" value="" size="8"/></td>
                </tr>  

            </table>

            <input type="submit" value="Imprimir Diario Geral" name="acao" />
            <input type="reset" value="Limpar" name="limpar" /> 
            <input type="submit" value="Voltar" name="acao" />

        </form>

    </center>   
</body>
<jsp:include page="footer.jsp" />
</html>

