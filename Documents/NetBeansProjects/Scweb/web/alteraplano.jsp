<%-- 
    Document   : alteraplano
    Created on : 19/06/2014, 09:13:43
    Author     : ProfGeraldo
--%>

<%@page import="java.text.DecimalFormat"%>
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
    </style>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="scripts.js" type="text/javascript"></script>
     <script src="js/valida.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="estilo.css">
    <body>

        <div id="mensagem">
            <h2>Alteração de Contas Contábeis</h2>
        </div>
        <div id="inclui-box">
            <div id="inclui-box-interno">

                <div id="inclui-box-label">
                    <h3>Altere o formulário abaixo: </h3>
                </div>   


                <form name="alteraplano" action="ServletPlano" method="GET">
                    <center>
                        <%
                            DecimalFormat df = new DecimalFormat();
                            df.applyPattern("###,###,##0.00");
                           
                        %>
                        <table id="tbMovto" bgcolor="navy" border="1">
                            <tr>
                                <td>NÚMERO : </td>
                                <td><input type="text" name="numero2" value="<%=request.getParameter("numero")%>" size="8" disabled="disabled" /></td>
                            </tr>
                            <tr>
                                <td>DESCRIÇÃO : </td>
                                <td> <input type="text" name="descricao" value="<%=request.getParameter("descricao")%>" size="50"/></td>
                            </tr>
                            <tr>
                                <td>DATA :</td> 
                                <td><input type="date" name="dt_saldo" value="<%=request.getParameter("dt_saldo")%>"size="10" /></td>
                            </tr>

                            <tr>
                                <td>SALDO INICIAL - Emissao:</td>  
                                <td><input type="text" name="saldo_inic" value="<%=df.format(Float.parseFloat(request.getParameter("saldo_inic")))%>"size="12"onkeyPress="return(MascaraMoeda(this,'.',',',event))" /></td>
                            </tr>
                            <tr>
                                <td>SALDO INICIAL - Vencimento:</td>  
                                <td><input type="text" name="saldo_iniv" value="<%=df.format(Float.parseFloat(request.getParameter("saldo_iniv")))%>"size="12" onkeyPress="return(MascaraMoeda(this,'.',',',event))"/></td>
                            </tr>
                            <tr>
                                <td>DATA US$ :</td> 
                                <td><input type="date" name="dt_saus" value="<%=request.getParameter("dt_saus")%>"size="10" /></td>
                            </tr>

                            <tr>
                                <td>SALDO INICIAL - Emissao US$ :</td>  
                                <td><input type="text" name="c_saieus" value="<%=df.format(Float.parseFloat(request.getParameter("c_saieus")))%>"size="12"onkeyPress="return(MascaraMoeda(this,'.',',',event))" /></td>
                            </tr>
                            <tr>
                                <td>SALDO INICIAL - Vencimento US$ :</td>  
                                <td><input type="text" name="c_saivus" value="<%=df.format(Float.parseFloat(request.getParameter("c_saivus")))%>"size="12"onkeyPress="return(MascaraMoeda(this,'.',',',event))" /></td>
                            </tr>

                        </table>
                        <input type="submit" value="Alterar" name="acao" />
                        <input type="hidden" name="numero" value="<%=request.getParameter("numero")%>" size="8"  />
                        <input type="hidden" name="idplano" value="<%=request.getParameter("idplano")%>" size="8"  />
                        <input type="submit" value="Cancelar" name="acao" />
                    </center>
                </form>
            </div>
        </div> 
    </body>
    <jsp:include page="footer.jsp" />
</html>
