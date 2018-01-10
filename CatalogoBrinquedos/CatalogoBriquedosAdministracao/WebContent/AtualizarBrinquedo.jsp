<%@page import="java.sql.*" %>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.io.PrintWriter"%>
<%@page import="br.MagicToys.model.Brinquedo" %>
<%@page import="br.MagicToys.dao.BrinquedoDAO" %>
<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
  int codigo       = 0;
  String descricao ="";
  String marca     ="";
  String categoria ="";
  String imagem    ="";
  double valor     = 0;
  String detalhes  ="";
  
  try{
  codigo = Integer.parseInt(request.getParameter("codigo"));
  }
  catch(NumberFormatException e){}
  
  BrinquedoDAO dao = new BrinquedoDAO(codigo); 
  Brinquedo brinquedo;
  brinquedo = dao.AcharBrinquedo();
  
  if (brinquedo != null)
  {
    codigo    = brinquedo.getCodigo();
    descricao = brinquedo.getDescricao();
    marca     = brinquedo.getMarca();
    categoria = brinquedo.getCategoria();
    imagem    = brinquedo.getImagem();
    valor     = brinquedo.getValor();
    detalhes  = brinquedo.getDetalhes();
  }
%>

<!DOCTYPE html>

	<html>
	
	  <head>
	  
	    <meta charset="utf-8">
	    
	      <meta http-equiv="X-UA-Compatible" content="IE=edge">
	      
	        <meta name="viewport" content="width=device-width, initial-scale=1">
	        
	    	  <title>Cadastrar Brinquedo</title>
	    	  
			    <link href="css/bootstrap.css" rel="stylesheet">
			    
	      		  </head>
      
    <body style="min-height: 768px; min-width: 1360px">
    
      <div class="container">
    
        <div class="row">
      
          <div class="span12" style="text-align:left">
        
    		<form id="FormAtualizar" class="form-horizontal" action="AtualizarBrinquedo" method="post" enctype="multipart/form-data" >
    
      		  <legend><h2>Catálogo de Brinquedos::.Atualizar Brinquedo:</h2></legend>

      			<table cellspacing="10">

  <div class="form-group">
      <label class="control-label col-md-1">Código:</label>
        <div class="input-group col-md-2">
          <input class="form-control" type="text" name="codigo" value ="<%=codigo%>" style="width: 50px" readonly="readonly">
            </div>
              </div>

  <div class="form-group">
      <label class="control-label col-md-1">Descrição:</label>
        <div class="input-group col-md-2">
          <input class="form-control" type="text" name="descricao" value ="<%=descricao%>">
            </div>
              </div>

  <fieldset>
	<div class="form-group">
      <label class="col-md-1 control-label" for="categoria">Categoria:</label>
        <div class=" row col-md-2">
          <select id="categoria" name="categoria" class="form-control">
            <option><%=categoria%></option>
              <option disabled="disabled">_________________</option>
                <option value="Meninos">Meninos</option>
                   <option value="Meninas">Meninas</option>
                      <option value="Bebês">Bebês</option>
                        </select>
  				          </div>
					        </div>
						      </fieldset>
						      
  <div class="form-group">
      <label class="control-label col-md-1">Marca:</label>
        <div class="input-group col-md-2">
          <input class="form-control" type="text" name="marca" value ="<%=marca%>">
            </div>
              </div>

  <div class="form-group">
      <label class="control-label col-md-1">Imagem:</label>
        <div class="input-group col-md-4">
          <input class="form-control" type="file" name="imagem" value ="<%=imagem%>">
            </div>
              </div>

  <div class="form-group">
      <label class="control-label col-md-1">Valor:</label>
        <div class="input-group col-md-2">
          <input class="form-control" type="text" name="valor" value ="<%=valor%>">
            </div>
              </div>
    
  <div class="form-group">
      <label class="control-label col-md-1">Detalhes:</label>
        <div class="input-group col-md-2">
          <input class="form-control" type="text" name="detalhes" value ="<%=detalhes%>" style="width: 400px">
            </div>
              </div>

  <!-- Button (Double) -->
  
  <label class="control-label" for=""></label>
  	<div class="controls">
	  <button type="submit" class="btn btn-success" style="margin-right: 10px; margin-top: -10px">Atualizar</button>
	    <button type="reset"  class="btn btn-danger" style="margin-top: -10px">Limpar</button>
    	  </div>
 	 		</div>
   			  </fieldset>
  				</form>
        <%
          PrintWriter m = response.getWriter();
          String retorno = request.getParameter("msg");
          if(retorno!= null)
          {
            m.println(retorno);
          }
        %>
	    	</div>
	      </div>
	    </div>
	  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
  </body>
</html>