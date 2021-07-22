/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 $(
     
    function (){
        $('#login').click(
                function(){
                    if( $(this).val('') == 'Digite o usuario'){
                       $(this).val(''); 
                    };
                }
        );
    
        $('#senha').click(
                function(){
                    if( $(this).val('') == 'Senha'){
                       $(this).val(''); 
                    };
                }
        );
        $('#botao').click(
        function initXHR(){
            if(window.XMLHttpRequest){ 
                xhr = new XMLHttpRequest();
            }else if (window.ActiveXObject){
                xhr = new ActiveXObject("Microsoft.XMLHttp");
            }
        }
 /*
        function sendRequest(url, handler){
            initXHR();
            xhr.onreadystatechange = handler;
            xhr.open("GET", url, true);
            xhr.send(null);
        }
        
        function pesquisaFatura(){
            var valor = window.document.getElementById("txt_buscaFatura");
            var url = "sConsultaFatura?valor=" + valor.value;
            sendRequest(url, ajaxRelatorioFatura);
        }
        
        function ajaxRelatorioFatura(){
            if(xhr.readyState == 4) {
              if(xhr.status == 200){  
                 var valor = window.document.getElementById("tbl_fatura");
                 var retorno = xhr.responseText;
                 valor.innerHTML = retorno;
                }
             }
        }
   
        function baixarFatura(){
            var idContrato = window.document.getElementById("txt_buscaFatura");
            var url = "sBaixarFatura?idContrato=" + idContrato.value;
            sendRequest(url, ajaxBaixarFatura());
        }
        
        function ajaxBaixarFatura(){
            if(xhr.readyState == 4) {
              if(xhr.status == 200){  
                 location.href="index.jsp";
                }
             }
        }
                */
        );
        }
);
        

