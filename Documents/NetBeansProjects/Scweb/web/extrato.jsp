<%-- 
    Document   : extrato
    Created on : 22/06/2014, 21:53:20
    Author     : extrato
--%>

<%@page import="br.com.model.PlanoBean"%>
<%@page import="java.util.List"%>
<%@page import="br.com.model.PlanoDB"%>
<%@page import="br.com.model.Pagina"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
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
     
    <center>
        <h1>Extrato Contábil</h1> 
        <h2>Preencha os dados abaixo: </h2>
        <c:set var="now" value="<%=new Date() %>"/>
        
            <form name="form_extrato" action="ServletRel" method="GET">
         
                <table id="tbMovto" border="1" bgcolor="navy">
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
                      <tr><td>Número : </td>
                          <td> <input type="text" name="txt_conta" value="" size="8"/></td>
                    </tr>  
                    <tr><td>Conta :</td> 
                        <td>
                              <%
                                        PlanoDB contaD = new PlanoDB(request.getServletContext().getInitParameter("DB"));
                                 
                                        List<PlanoBean> lista = contaD.carregaComboPlano();
                                  
                                        
                                    %>  
                            <select name="opcao_conta" id="opcao_conta" onchange="pegaConta(txt_conta)">
                                <option value="">Escolha a conta...</option>
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
                </table>
                <input type="submit" value="Imprimir Extrato" name="acao" />
                <input type="reset" value="Limpar" name="limpar" /> 
                <input type="submit" value="Voltar" name="acao" />
            </form>
    </center>   
</body>
<jsp:include page="footer.jsp" />

<script type="text/javascript">
    function pegaConta(obj) {
       var conta = document.getElementById("opcao_conta");
            obj.value = conta.value;
     
       
    }
</script>
</html>

