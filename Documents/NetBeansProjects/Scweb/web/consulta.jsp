<%-- 
    Document   : consulta
    Created on : 28/05/2016, 22:53:06
    Author     : Geraldo
--%>

<%@page import="br.com.model.PlanoBean"%>
<%@page import="java.util.List"%>
<%@page import="br.com.model.PlanoDB"%>
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
    <body>
      
    <center><h1>Consulta movimento</h1> 
        <h2>Preencha os dados abaixo: </h2>

        <form name="form_balancete" action="ServletSc" method="GET">
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
                <tr><td>Número : </td>
                    <td> <input type="text" id="txt_conta" name="txt_conta" value="" size="8"/></td>

                </tr>  
                <tr><td>Conta :</td> 
                     <%
                                        PlanoDB contaD = new PlanoDB(request.getServletContext().getInitParameter("DB"));
                                 
                                        List<PlanoBean> lista = contaD.carregaComboPlano();
                                  
                                        
                                    %>  
                    <td>
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

            <button value="Consulta Movimento" name="acao">Consultar</button>
            <input type="reset" value="Limpar" name="limpar" /> 
            <input type="hidden" name="opcao_pesquisar" value="<%=request.getParameter("opcao_pesquisar")%>"size="8" />
            <input type="hidden" name="opcaovigente" value="<%=request.getParameter("opcaovigente")%>"size="8" />
            <input type="hidden" name="opcaoradio" value="<%=request.getParameter("opcaoradio")%>"size="8" />
            <input type="hidden" name="dataIrPara" value="<%=request.getParameter("dataIrPara")%>"size="8" />
            <button value="Voltar" name="acao">Voltar</button>
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



