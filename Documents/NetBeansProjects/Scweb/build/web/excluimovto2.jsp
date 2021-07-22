<%-- 
    Document   : excluimovto2
    Created on : 28/05/2016, 16:19:43
    Author     : Geraldo
--%>

<%@page import="br.com.model.PlanoBean"%>
<%@page import="br.com.model.PlanoDB"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
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
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="scripts.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="estilo.css">
    <body>

        <div id="mensagem">
            <h2>Exclusão de movimento contábil</h2>
        </div>
        <div id="inclui-box">
            <div id="inclui-box-interno">

                <div id="inclui-box-label">
                    <p>Confirme a exclusão </p>
                </div>   

                <form name="form_principal" action="ServletSc" method="GET">
                    <table id="tbMovto" border="1" bgcolor="navy">
                        <tr>
                            <td>DEBITO : </td>
                            <td><input type="text" name="opcao_debito"disabled="disabled" value="<%=request.getParameter("opcao_debito")%>"size="8" /></td>
                            <td>
                                <%
                                    DecimalFormat df = new DecimalFormat();
                                    df.applyPattern("###,###,##0.00");
                                    String valor2 = request.getParameter("valor");
                                    String valor3 = df.format(Float.parseFloat(valor2));

                                    PlanoDB contaD = new PlanoDB(request.getServletContext().getInitParameter("DB"));
                                    String debito = request.getParameter("opcao_debito");
                                    String credito = request.getParameter("opcao_credito");
                                    String descContaD = contaD.pegaDesc(debito);
                                    String descContaC = contaD.pegaDesc(credito);

                                    out.print(descContaD);
                                %>      
                            </td>
                        </tr>
                        <tr>
                            <td>CREDITO : </td>
                            <td><input type="text" name="opcao_credito"disabled="disabled" value="<%=request.getParameter("opcao_credito")%>"size="8" /></td>
                            <td>
                                <%out.print(descContaC);%>      
                            </td>
                        </tr>
                        <tr><td>DATA :</td> 
                            <td><input type="date" name="dt_emissao"disabled="disabled" value="<%=request.getParameter("dt_emissao")%>"size="8" /></td>
                        </tr>
                        <tr><td>HISTORICO :</td>  
                            <td><input type="text" name="hist" disabled="disabled"value="<%=request.getParameter("hist")%>"size="50" /></td>
                        </tr>
                        <tr><td>OBSERVACAO :</td>  
                            <td><input type="text" name="obs" disabled="disabled"value="<%=request.getParameter("obs")%>"size="50" /></td>
                        </tr>
                        <tr><td>VENCIMENTO :</td>  
                            <td><input type="date" name="dt_vencto"disabled="disabled" value="<%=request.getParameter("dt_vencto")%>"size="8" /></td>
                        </tr>
                        <tr><td>VALOR :</td>  
                            <td><input type="text" name="valor" disabled="disabled" value="<%=valor3%>"size="12" /></td>
                        </tr>

                    </table>
                    <center>
                        <input type="submit" value="Excluir" name="acao" />
                        <input type="hidden" name="idmovimento" value="<%=request.getParameter("codigo")%>" size="11"  />
                        <input type="hidden" value="<%=request.getParameter("opcaovigente")%>" name="opcaovigente" />
                        <input type="hidden" name="opcaoradio" value="<%=request.getParameter("opcaoradio")%>"size="" />
                        <input type="hidden" name="dataIrPara" value="<%=request.getParameter("dataIrPara")%>"size="" />  
                        <input type="submit" value="Cancelar" name="acao" />
                    </center>
                </form>
            </div>
        </div>
    </body>
    <%--<jsp:include page="footer.jsp" />--%>
</html>
