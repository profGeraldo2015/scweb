<%-- 
    Document   : principal
    Created on : 13/03/2014, 21:46:04
    Author     : ProfGeraldo

--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1/jquery.min.js"></script>
    <script src="scripts.js" type="text/javascript"></script>
    <script src="js/valida.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="estilo.css">
    
     <STYLE TYPE="text/css">
        <!--
        td,th{font-family: Calibri; 
              font-size: 12pt;
              color: yellow;

        }

        -->
    </style>
    <head>  
        <script language="JavaScript">
            function isEmpty(str) {
                if (str == null || str == "")
                    return true;
                return false;
            }
            function validate(numero, dt_saldo) {
                if (isEmpty(numero)) {
                    alert("NUMERO obrigatorio...");
                    form_principal.numero.focus();
                    return false;
                } else {
                    if (isEmpty(dt_saldo)) {
                        alert("Data deve ser preenchida...");
                        form_principal.dt_saldo.focus();
                        return false;
                    }
                    document.forms[].submit();
                }

                return true;
            }
        </script>
        
    </head>

    <body >
        <%--
        onsubmit="return validate(this.isbn.value,this.titulo.value);
        --%>



        <div id="mensagem">
            <h2>Inclusão de Contas Contábeis</h2>
        </div>
        <div id="inclui-box">
            <div id="inclui-box-interno">

                <div id="inclui-box-label">
                    <h3>Preencha o formulário abaixo: </h3>
                </div>   
                <form name="form_principal" action="ServletPlano" method="POST"onsubmit="return validate(this.numero.value, this.dt_saldo.value);" >
                    <center> 
                        <table border="1" id="tbMovto" bgcolor="navy">
                            <tr>
                                <td>NÚMERO : </td>
                                <td><input type="text" name="numero" value="" size="8"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" /></td>
                            </tr>
                            <tr><td>DESCRIÇÃO : </td>
                                <td> <input type="text" name="descricao" value="" size="50"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)"/></td>
                            </tr>  
                            <tr><td>DATA :</td> 
                                <td><input type="date" name="dt_saldo" value=""size="8" /></td>
                            </tr>
                            <tr><td>SALDO INICIAL - EMISSÃO :</td>  
                                <td><input type="text" name="saldo_inic" value=""size="12"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" onkeyPress="return(MascaraMoeda(this, '.', ',', event))" /></td>
                            </tr>
                            <tr><td>SALDO INICIAL - VENCIMENTO :</td>  
                                <td><input type="text" name="saldo_iniv" value=""size="12"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" onkeyPress="return(MascaraMoeda(this, '.', ',', event))" /></td>
                            </tr>
                            <tr><td>DATA US$ :</td>  
                                <td><input type="date" name="dt_saus" value=""size="8" /></td>
                            </tr>
                            <tr><td>SALDO INICIAL - EMISSÃO US$:</td>  
                                <td><input type="text" name="c_saieus" value=""size="12"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" onkeyPress="return(MascaraMoeda(this, '.', ',', event))" /></td>
                            </tr>
                            <tr><td>SALDO INICIAL - VENCIMENTO US$:</td>  
                                <td><input type="text" name="c_saivus" value="0"size="12"onfocus="mudaCor(this, 1)" onblur="mudaCor(this, 2)" onkeyPress="return(MascaraMoeda(this, '.', ',', event))" /></td>
                            </tr>



                        </table>
                        <center>
                            <input type="submit" value="Gravar" name="acao" />
                            <input type="reset" value="Limpar" name="limpar" /> 
                            <input type="submit" value="Cancelar" name="acao" />
                        </center>
                </form>
            </div>
        </div> 
    </body>
    <jsp:include page="footer.jsp" />
</html>
