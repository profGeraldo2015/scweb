<%-- 
    Document   : balancete
    Created on : 26/04/2016, 22:31:00
    Author     : Geraldo
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
      
        -->
    </STYLE> 
    <head>  
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>
    <jsp:include page="header.jsp"/>
    <body >
    <center><h1>Geração do Balancete Contábil</h1> 
        <h2>Preencha os dados abaixo: </h2>
       
            <form name="form_balancete" action="ServletRel" method="GET">
                <table border="1" bgcolor="navy" id="tbMovto">
                    <td>Data de: </td>
                    <td>
                        <select name="opcao_data">
                            <option value="dt_vencto" selected="selected">Vencimento</option>
                            <option value="dt_emissao">Emissão</option>
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
                   
                   <button value="Imprimir Balancete" name="acao">Imprimir</button>
                   <input type="reset" value="Limpar" name="limpar" /> 
                   <button value="Voltar" name="acao">Voltar</button>
              </form>
        
    </center>   
</body>
<jsp:include page="footer.jsp" />
</html>


