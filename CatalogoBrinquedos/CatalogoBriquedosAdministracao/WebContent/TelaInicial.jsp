<%@page import="java.io.PrintWriter"%>
<%@page import="java.util.ArrayList"%>
<%@page import="br.MagicToys.model.Brinquedo" %>
<%@page import="br.MagicToys.dao.BrinquedoDAO" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<%@page import ="java.util.List" %>


<%@ page language="java"  contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%
  List<Brinquedo> brinquedos = new ArrayList<Brinquedo>();
  BrinquedoDAO bd = new BrinquedoDAO();
  brinquedos = bd.ListaDeBrinquedos();
  PrintWriter out1 = response.getWriter();
  //out1.println(brinquedos.size());

  session.setAttribute("brinquedos", brinquedos);
%>

<html>
  <head>
	    <meta charset="UTF-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
	    	<title>Administração</title>
		      <script>
		        function excluir(brinquedo)
		        {
		          if (confirm("Deseja realmente excluir " + brinquedo + "?"))
		            {
		              return true;
		            }
		          else
		            {
		              return false;
		            }
		        }
		      </script>
    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
  </head>
  <body>
  
  	<div class="page-header" align="center">
  	
  		<span class="label label-danger" align="center" style="color: #00A1F1; font-size:46px; font-family: cursive">MAGICTOYS</span><span class="label label-info" style="color: red; font-size: xx-large;">BRINQUEDOS</span>
	  		<span align="center">
		  		<address style="margin-top: 50px">
				  <strong>Magictoys, Inc.</strong><br>
				  Cesario Galero, Unicid<br>
				  Tatuapé, São Paulo<br>
				  <abbr title="Phone">Phone:</abbr> (123) 0800-####
				</address>
				
				<address>
				  <strong>E-mail</strong><br>
				  <a href="mailto:#">Magictoys@magic.com</a>
				</address>
			</span>
	</div>
			
		  	   <nav class="navbar navbar-inverse">
		  		  <div class="container-fluid">
  
    <!-- Brand and toggle get grouped for better mobile display -->
    
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
    </div>
    
    <!-- Collect the nav links, forms, and other content for toggling -->
    
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="TelaInicial.jsp">Administração<span class="sr-only">(current)</span></a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Opções<span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="" onclick="window.open ('InserirBrinquedo.jsp');">Novo Brinquedo</a></li>
          </ul>
        </li>
      </ul>
  </div><!-- /.container-fluid -->
</nav>
<div class="panel panel-default">
  <!-- Default panel contents -->
  <div class="panel-heading" align="center" style="font-size: 20px;font-style: bold">PRODUTOS</div>
  <div class="panel panel-default">
  
  </div>

  <!-- Table -->
  <fieldset>
			<h2>Catálogo de Brinquedos::.Administração:</h2>
				
				<table class="table" >
				 <div class="panel panel-default">
				  <div class="panel panel-default">
				   <div class="panel panel-default">
		   		    <th style="background: #00A1F1">Codigo</th>
			   	     <th style="background: #00A1F1">Descrição</th>
			   	      <th style="background: #00A1F1">Categoria</th>
			   	       <th style="background: #00A1F1">Marca</th>
			   	        <th style="background: #00A1F1">Valor</th>
			   	         <th style="background: #00A1F1">Detalhes</th>
			   	          <th>Controles</th>
				   		   <c:forEach var="brinquedo" varStatus="rowCounter" items="${brinquedos}">
						      <tr>
						      	<td align="center">${brinquedo.getCodigo()}</td>
								<td>${brinquedo.getDescricao()}</td>
								<td>${brinquedo.getCategoria()}</td>
								<td>${brinquedo.getMarca()}</td>
								<td>R$ ${brinquedo.getValor()}</td>
								<td>${brinquedo.getDetalhes()}</td>
								<td>
									<a href="" onclick="window.open ('AtualizarBrinquedo.jsp?codigo=${brinquedo.getCodigo()}',
									'Pagina de cadastro', 
									'STATUS=NO,TOOLBAR=NO,LOCATION=NO,DIRECTORIES=NO,RESISABLE=NO,SCROLLBARS=YES,TOP=10,LEFT=10,WIDTH=1360,HEIGHT=768');"
					 				> Editar</a> -
									<a href="ExcluirBrinquedo?codigo=${brinquedo.getCodigo()}" onclick="return excluir('${brinquedo.getDescricao()}')">Excluir</a>
								</td>
							  </tr>
				</c:forEach>
			</table>
	</fieldset>
</div>
		<%
          PrintWriter m = response.getWriter();
          String retorno = request.getParameter("msg");
          if(retorno!= null)
          {
            m.println(retorno);
          }
        %>
    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="js/bootstrap.min.js"></script>
  </body>
</html>