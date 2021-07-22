<%-- 
    Document   : incluirmovto
    Created on : 21/06/2014, 23:04:35
    Author     : User
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>  
          <script language="JavaScript">
            function isEmpty(str){
                if(str==null || str=="") return true;
                return false;
            }
            function validate(numero,dt_saldo){
                if(isEmpty(numero)){
                    alert("NUMERO obrigatorio...");
                    form_principal.numero.focus();
                    return false;
                }else{if(isEmpty(dt_saldo)){
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
    <jsp:include page="header.jsp"/>
    <body >
      <%--
      onsubmit="return validate(this.isbn.value,this.titulo.value);
      --%>
        <div id="mensagem">
                    Esta mensagem e de exemplo
        </div>
                  
    <center> 
       <h1>Preencha o formulario abaixo: </h1>
      
        <form name="form_principal" action="ServletSc" method="GET">
            <table>
                <tr>
                    
                    <td><div class="input-div" id="input-debito">
                            <input type="text" id="debito" name="debito" value="Digite a conta debito" size="8" />
                        </div>
                    </td>
                </tr>
                <tr><td>CREDITO : </td>
                    <td> <input type="text" name="credito" value="" size="8"/></td>
                </tr>  
                <tr><td>DATA :</td> 
                     <td><input type="text" name="dt_emissao" value=""size="10" /></td>
                </tr>
                <tr><td>HISTORICO :</td>  
                    <td><input type="text" name="hist" value=""size="50" /></td>
                </tr>
                <tr><td>OBSERVACAO :</td>  
                    <td><input type="text" name="obs" value=""size="50" /></td>
                </tr>
                <tr><td>VALOR :</td>  
                    <td><input type="text" name="valor" value=""size="12" /></td>
                </tr>
                <tr><td>VENCIMENTO :</td>  
                    <td><input type="text" name="dt_vencto" value=""size="12" /></td>
                </tr>
            </table>
          
            <input type="submit" value="Gravar" name="acao" />
             <input type="reset" value="Limpar" name="limpar" /> 
             <input type="submit" value="Cancelar" name="acao" />
            
        </form>
        </center>   
    </body>
    <jsp:include page="footer.jsp" />
</html>

