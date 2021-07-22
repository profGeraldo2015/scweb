<%-- 
    Document   : alteramovto
    Created on : 23/06/2014, 08:24:33
    Author     : User
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
    <script src="js/valida.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="estilo.css">
    <body>

        <div id="mensagem">
            <h2>Alteração de movimento contábil</h2>
        </div>
        <div id="inclui-box">
            <div id="inclui-box-interno">

                <div id="inclui-box-label">
                    <p>Altere o movimento abaixo: </p>
                </div>   

                <form name="form_principal" action="ServletSc" method="GET">
                    <center>    
                        <table id="tbMovto" border="1" bgcolor="navy">
                            <tr>
                                <td>DÉBITO : </td>
                                <td><input type="text" name="txt_debito" value="<%=request.getParameter("opcao_debito")%>"size="8" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/>
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
                                        List<PlanoBean> lista = contaD.carregaComboPlano();
                                    
                                        out.print(descContaD);
                                        
                                    %>  
                                </td>
                            </tr>
                            <tr>
                                <td>

                                </td>
                                <td>
                                    <select name="opcao_debito">
                                        <%
                                            for( int i = 0; i < lista.size(); i++){
                                                if(lista.get(i).getNumero().substring(4,8).equals("0000")){
                                                    
                                                }else{
                                                %>
                                                
                                                <option value="<%=lista.get(i).getNumero()%>"><%=lista.get(i).getNumero()+ " - " + lista.get(i).getDescricao()%> </option>
                                            
                                                <%}
                                           }
                                        %>
                                    </select>      
                                </td>

                            </tr>
                            <tr>
                                <td>CRÉDITO : </td>
                                <td><input type="text" name="txt_credito" value="<%=request.getParameter("opcao_credito")%>"size="8"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" />   <%out.print(descContaC);%>     </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <select name="opcao_credito">
                                        <%
                                            for( int i = 0; i < lista.size(); i++){
                                                if(lista.get(i).getNumero().substring(4,8).equals("0000")){
                                                    
                                                }else{
                                                %>
                                                
                                                <option value="<%=lista.get(i).getNumero()%>"><%=lista.get(i).getNumero()+ " - " + lista.get(i).getDescricao()%> </option>
                                            
                                                <%}
                                           }
                                        %>
                                    </select>      
                                </td>
                            </tr>
                            <tr><td>EMISSÃO :</td> 
                                <td><input type="date" name="dt_emissao" value="<%=request.getParameter("dt_emissao")%>"size="8" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></td>
                            </tr>
                            <tr><td>VENCIMENTO :</td>  
                                
                                
                                <td><input type="date" name="dt_vencto" value="<%=request.getParameter("dt_vencto")%>"size="8" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></td>
                            </tr>
                            <tr><td>HISTÓRICO:</td>  
                                <td><input type="text" name="hist" value="<%=request.getParameter("hist")%>"size="50" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></td>
                            </tr>
                            <tr><td>OBSERVAÇÃO:</td>  
                                <td><input type="text" name="obs" value="<%=request.getParameter("obs")%>"size="50" onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></td>
                            </tr>
                            <tr><td>VALOR :</td>  
                                <td><input type="text" name="valor" value="<%=valor3%>"size="12"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"onkeyPress="return(MascaraMoeda(this,'.',',',event))" /></td>
                            </tr>

                        </table>

                        <input type="submit" value="Alterar" name="acao" />
                        <input type="hidden" name="idmovimento" value="<%=request.getParameter("codigo")%>" size="11"  />
                        <input type="hidden" value="<%=request.getParameter("opcaovigente")%>" name="opcaovigente" />
                        <input type="hidden" name="opcaoradio" value="<%=request.getParameter("opcaoradio")%>"size="" />
                        <input type="hidden" name="dataIrPara" value="<%=request.getParameter("dataIrPara")%>"size="" />  
                        <input type="submit" value="Duplicar" name="acao" />
                        <input type="reset" value="Limpar" name="limpar" /> 
                        <input type="submit" value="Cancelar" name="acao" />

                    </center>
                </form>
            </div>
        </div>
    </body>

    <jsp:include page="footer.jsp" />

</html>
