<%@page contentType="text/html; charset=UTF-8" import="java.util.*, org.tecsharp.apiservlet.webapp.headers.models.*, java.text.DecimalFormat"%>
<%
Optional<String> username = (Optional<String>) request.getAttribute("username");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
Producto producto = (Producto) request.getAttribute("producto");

%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Tecstore | Admin</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap styles -->
    <link href="<%=request.getContextPath()%>/assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="<%=request.getContextPath()%>/style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="<%=request.getContextPath()%>/assets/font-awesome/css/font-awesome.css" rel="stylesheet">
		<!--[if IE 7]>
			<link href="<%=request.getContextPath()%>/css/font-awesome-ie7.min.css" rel="stylesheet">
		<![endif]-->

		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

	<!-- Favicons -->
    <link rel="shortcut icon" href="<%=request.getContextPath()%>/assets/ico/favicon.ico">
  </head>
<body>
<!-- 
	Upper Header Section 
-->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="topNav">
		<div class="container">
			<div class="alignR">
				<a href="index.html"> <span class="icon-home"></span> Inicio</a>
				<a href="<%=request.getContextPath()%>/mi-cuenta"><span class="icon-user"></span> Mi cuenta</a>
			</div>
		</div>
	</div>
</div>

<!--
Lower Header Section 
-->
<div class="container">
<div id="gototop"> </div>
<header id="header">
<div class="row">
	<div class="span4">
	<h1>
	<a class="logo" href="<%=request.getContextPath()%>/index.html"><span>Tecstore</span>
		<img src="<%=request.getContextPath()%>/assets/img/logo-bootstrap-shoping-cart.png" alt="bootstrap sexy shop">
	</a>
	</h1>
	</div>

	<div class="span4 alignR">

	<!--
	<p><br> <strong> Support (24/7) :  0800 1234 678 </strong><br><br></p>
	<span class="btn btn-mini">[ 2 ] <span class="icon-shopping-cart"></span></span>
	<span class="btn btn-warning btn-mini">$</span>
	<span class="btn btn-mini">&pound;</span>
	<span class="btn btn-mini">&euro;</span>
	-->

	</div>
</div>
</header>

<!--
Navigation Bar Section 
-->
<div class="navbar">
	  <div class="navbar-inner">
		<div class="container">
		  <a data-target=".nav-collapse" data-toggle="collapse" class="btn btn-navbar">
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
			<span class="icon-bar"></span>
		  </a>
		  <div class="nav-collapse">
			<ul class="nav">
			  <li class=""><a href="<%=request.getContextPath()%>/index.html">Inicio</a></li>

			  <!--
			  <li class=""><a href="list-view.html">List View</a></li>
			  <li class=""><a href="grid-view.html">Grid View</a></li>
			  <li class=""><a href="three-col.html">Three Column</a></li>
			  <li class=""><a href="four-col.html">Four Column</a></li>
			  <li class="active"><a href="general.html">General Content</a></li>
			  -->

			</ul>
			<form action="<%=request.getContextPath()%>/buscar/productos" class="navbar-search pull-left" method="post">
			  <input type="text" placeholder="Buscar" name="buscar" id="buscar" class="search-query span2">
			</form>

            <ul class="nav pull-right">
            <li class="dropdown">
            <%if(username.isPresent()){%>
                         <a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="icon-unlock"></span> ${usuario.nameUser} <b class="caret"></b></a>
                <div class="dropdown-menu">
                     	    <form class="form-horizontal loginFrm">
                     		<a href="<%=request.getContextPath()%>/logout"> <button type="button" class="shopBtn btn-block">Cerrar sesión</button></a>
                     		</form>
                     		</div>
            <%}else{%>
                         <a href="<%=request.getContextPath()%>/inicio"><span class="icon-lock"></span> Iniciar</b></a>
            <%}%>
            </li>
            </ul>

		  </div>
		</div>
	  </div>
	</div>
<!-- 
Body Section 
-->
	<div class="row">
		<div id="sidebar" class="span3">
		<div class="well well-small">
			<ul class="nav nav-list">
				<li><span class=""></span><b>Herramientas admin</b></li>
				<br>
				<li><a href="<%=request.getContextPath()%>/crud/agregar"><span class="icon-chevron-right"></span>Agregar productos</a></li>
				<li><a href="<%=request.getContextPath()%>/crud/actualizar"><span class="icon-chevron-right"></span>Actualizar productos</a></li>
				<li><a href="<%=request.getContextPath()%>/crud/eliminar"><span class="icon-chevron-right"></span>Eliminar productos</a></li>
				<li style="border:0"> &nbsp;</li>
			</ul>
		</div>
	</div>
	<div class="span9">
<div class="well well-small">
    <br>

    <h2>Se eliminó con éxito: <%=producto.getNombre()%></h2>
    <table class="table table-striped">
	<tbody>
	<tr class="techSpecRow"><td class="techSpecTD1">Nombre:</td><td class="techSpecTD2"><%=producto.getNombre()%></td></tr>
	<tr class="techSpecRow"><td class="techSpecTD1">Categoria:</td><td class="techSpecTD2"><%=producto.getTipo().getNombre()%></td></tr>
	<tr class="techSpecRow"><td class="techSpecTD1">Precio:</td><td class="techSpecTD2"><%=producto.getPrecioFormateado()%></td></tr>
	<tr class="techSpecRow"><td class="techSpecTD1">Stock:</td><td class="techSpecTD2"><%=producto.getStock()%></td></tr>
	<tr class="techSpecRow"><td class="techSpecTD1">Status:</td><td class="techSpecTD2"><%=producto.getStatus()%></td></tr>
	<tr class="techSpecRow"><td class="techSpecTD1">Descripcion corta:</td><td class="techSpecTD2"><%=producto.getDescripcionCorta()%></td></tr>
	<tr class="techSpecRow"><td class="techSpecTD1">Descripcion larga:</td><td class="techSpecTD2"><%=producto.getDescripcion()%></td></tr>
	</tbody>
	</table>

    <div>
        <div>
            <a href="<%=request.getContextPath()%>/crud/eliminar">
            <input class="shopBtn" type="button" value="Regresar" />
            </a>
        </div>
    </div>

    <br>
	<br>


	<hr/>
	<!-- SECCION CERRADA
	<h2>Form with validation states</h2><br/>



	<form class="bs-docs-example form-horizontal">
            <div class="control-group warning">
              <label class="control-label" for="inputWarning">Input with warning</label>
              <div class="controls">
                <input type="text" id="inputWarning">
                <span class="help-inline">Something may have gone wrong</span>
              </div>
            </div>
            <div class="control-group error">
              <label class="control-label" for="inputError">Input with error</label>
              <div class="controls">
                <input type="text" id="inputError">
                <span class="help-inline">Please correct the error</span>
              </div>
            </div>
            <div class="control-group info">
              <label class="control-label" for="inputInfo">Input with info</label>
              <div class="controls">
                <input type="text" id="inputInfo">
                <span class="help-inline">Username is taken</span>
              </div>
            </div>
            <div class="control-group success">
              <label class="control-label" for="inputSuccess">Input with success</label>
              <div class="controls">
                <input type="text" id="inputSuccess">
                <span class="help-inline">Woohoo!</span>
              </div>
            </div>
          </form>


	<h5>Lorem ipsum dolor sit amet</h5><br/>
	<p>
	Aliquam interdum, ipsum a posuere dictum, tellus risus blandit dolor, at tristique sapien urna vel purus. Pellentesque in dictum urna. Sed feugiat libero sit amet arcu malesuada eu convallis dui convallis. Donec facilisis massa a ipsum aliquam lobortis. Praesent ac lectus sed leo aliquam egestas. Sed ante neque, volutpat ac tempor et, bibendum at ligula. Nunc porta vestibulum sodales.
	</p>
	FINAL DE CIERRE-->

</div>
</div>
</div>
<!-- 
Clients 
-->

<!--
Footer
-->
<jsp:include page="footer.jsp" />
</div><!-- /container -->

<div class="copyright">
<div class="container">
	<span><%=mensajeApp%></span>
</div>
</div>
<a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="<%=request.getContextPath()%>/assets/js/jquery.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath()%>/assets/js/jquery.easing-1.3.min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
    <script src="<%=request.getContextPath()%>/assets/js/shop.js"></script>
  </body>
</html>
