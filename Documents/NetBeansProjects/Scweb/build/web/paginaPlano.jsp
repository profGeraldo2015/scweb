<%-- 
    Document   : paginaPlano
    Created on : 11/05/2016, 07:18:02
    Author     : Geraldo

 <td><a href="http://<%=getServletContext().getInitParameter("IP")%>/alteraplano.jsp?idplano=<%=lancto.getIdPLANO()%>&numero=<%=lancto.getNumero()%>&descricao=<%=lancto.getDescricao()%>&saldo_inic=<%=lancto.getSaldo_iniv()%>&dt_saldo=<%=lancto.getDt_saldo()%>&dt_saus=<%=lancto.getDt_saus()%>&c_saieus=<%=lancto.getC_saieus%>&c_saivus=<%=lancto.getC_saivus()%>">Alterar<a/></td>

--%>

<%@page import="br.com.model.PlanoBean"%>
<%@page import="java.text.DecimalFormat"%>
<%@ page import="java.io.*,java.util.*,java.sql.*"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>  
        <link rel="stylesheet" type="text/css" href="estilo.css">
    </head>

    <STYLE TYPE="text/css">
        <!--
        td,th{font-family: Arial; 
              font-size: 10pt;

        }
        -->
    </STYLE> 

    <jsp:include page="menusus.jsp" />

    <script type="text/javascript" src="js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="js/jquery.corner.js"></script>
    <body>

    <center>
        <h3>Plano de Contas</h3>
        <form name="frm_plano" action="ServletPlano" method="GET">
            <div id="operacao">
                <div id="pesq" class="box">
                    <fieldset align="left"><legend>Escolha opção de pesquisa</legend> 
                        <div id="pesquisa" class="box">      
                            <select name="opcao_pesquisar">
                                <option value="numero" selected="selected">CONTA</option>
                                <option value="descricao">DESCRIÇÃO</option>
                            </select>    
                        </div>
                        <input type="text" name="pesquisa" value="" size="50" />

                        <button name="acao" value="Pesquisar">Pesquisar</button>
                    </fieldset>
                </div>
            </div>

            <%--
            <button name="acao" value="Voltar">Voltar</button>

            

        </form>--%>

            <p>Página : <%=request.getAttribute("numPagina")%>/<%=request.getAttribute("totPagina")%></p>
            <table id="tbPlano" border='2' width="95%" >
                <tr>

                    <th width="6%">Número</th>

                    <th>Descrição</th>
                    <th width="9%">Data R$</th>
                    <th width="9%">Saldo Inicial - Emissao R$ </th>
                    <th width="9%">Saldo Inicial - Vencimento R$ </th>
                    <th width="9%">Data US$</th>
                    <th width="9%">Saldo Inicial - Emissao US$ </th>
                    <th width="9%">Saldo Inicial - Vencimento US$ </th>
                    <th>Opção</th>

                </tr>
                <%
                    List<PlanoBean> result = (List<PlanoBean>) request.getAttribute("pagina");
                    Iterator iterator = result.iterator();
                    int linha = 1;
                    while (iterator.hasNext()) {
                        PlanoBean lancto = (PlanoBean) iterator.next();
                        if ((linha % 2) == 0) {
                            out.println("<tr style='color:#333334;background-color:goldenrod;'>");
                        } else {
                            out.println("<tr style='color:#333334;background-color:#fffacd;'>");
                        }
                        linha++;


                %>




                <td><%=lancto.getNumero()%></td>
                <td><%=lancto.getDescricao()%></td>
                <td><%=lancto.getDt_saldo2()%></td>
                <td align="right"><%=lancto.getSaldo_inic2()%></td>
                <td align="right"><%=lancto.getSaldo_iniv2()%></td>
                <td><%=lancto.getDt_saus2()%> </td>
                <td align="right"><%=lancto.getC_saieus2()%></td>
                <td align="right"><%=lancto.getC_saivus2()%></td>
                <td><a href="http://<%=getServletContext().getInitParameter("IP")%>/alteraplano.jsp?idplano=<%=lancto.getIdPLANO()%>&numero=<%=lancto.getNumero()%>&descricao=<%=lancto.getDescricao()%>&saldo_inic=<%=lancto.getSaldo_inic()%>&saldo_iniv=<%=lancto.getSaldo_iniv()%>&dt_saldo=<%=lancto.getDt_saldo()%>&dt_saus=<%=lancto.getDt_saus()%>&c_saieus=<%=lancto.getC_saieus()%>&c_saivus=<%=lancto.getC_saivus()%>"><img src="editar16.png"><a/></td>


                </tr>

                <%
                    }
                %>

            </table>
            <%--<form name="frm_paginaPlano" action="ServletPlano" method="GET">--%>

            <input type="hidden" name="numPagina" value="<%=request.getParameter("numPagina")%>" size="2"  />

            <button name="acao" value=" < "><img src="seta_voltar.png"></button>
            <button name="acao" value="Cadastrar"><img src="adicionar16.png"></button>

            <%--<button name="acao" value="Voltar">Voltar</button>--%>
            <button name="acao" value=" > "><img src="seta_avancar.png"></button>
        </form>
    </center>

</body>
<jsp:include page="footer.jsp" />
</html>
