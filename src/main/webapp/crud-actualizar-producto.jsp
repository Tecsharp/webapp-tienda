<%@page contentType="text/html; charset=UTF-8" import="java.util.*, org.tecsharp.apiservlet.webapp.headers.models.*, java.text.DecimalFormat"%>
<%
Optional<String> username = (Optional<String>) request.getAttribute("username");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
Producto producto = (Producto) request.getAttribute("producto");
List<TipoProducto> categorias = (List<TipoProducto>) request.getAttribute("categorias");
Integer categoriaId = (Integer) request.getAttribute("categoriaId");
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
			<link href="<%=request.getContextPath()%>/assets/font-awesome/css/font-awesome-ie7.min.css" rel="stylesheet">
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

    <h2>Agregar productos</h2>
    <h5>Selecciona o ingresa los datos</h5>

    <form action="<%=request.getContextPath()%>/crud/actualizar/producto" method="post">

    <label class="control-label" for="idProducto">ID del producto</label>
            <div class="controls">
            <input type="text" id="idProducto" name="idProducto" value="<%=producto.getId()%>" readonly>
        </div>

    <div>
        <label for="categoria">Categoria</label>
            <div>
                <select name="categoria" id="categoria">
                    <!-- METER UNA LISTA CON LAS CATEGORIAS -->
                    <option value="<%=producto.getTipo().getId()%>"><%=producto.getTipo().getNombre()%></option>
                    <%for (TipoProducto cat : categorias){%>
                    <option value="<%=cat.getId()%>"> <%=cat.getNombre()%> </option>
                    <%}%>
                    <!--
                    <option value="1"> Motocicletas</option>
                    <option value="2"> Telefonia</option>
                    <option value="3"> Refrigeracion</option>
                    <option value="4"> Videojuegos</option>
                    <option value="5"> Computo</option>
                    -->
                </select>
            </div>

    </div>


    <label class="control-label" for="name">Nombre del producto</label>
        <div class="controls">
        <input type="text" id="nombre" name="nombre" value="<%=producto.getNombre()%>">
    </div>

    <label class="control-label" for="precio">Precio del producto</label>
        <div class="controls">
        <input type="text" id="precio" name="precio" value="<%=producto.getPrecio()%>">
    </div>

    <label class="control-label" for="stock">Stock del producto</label>
        <div class="controls">
        <input type="text" id="stock" name="stock" value="<%=producto.getStock()%>">
    </div>

    <label class="control-label" for="shortdescription">Descripción corta</label>
        <div class="controls">
        <input type="text" id="shortdescription" name="shortdescription" value="<%=producto.getDescripcionCorta()%>">
    </div>

    <div class="full-width">
        <label for="largedescription">Descripcion larga/html</label>
        <textarea id="largedescription" name="largedescription"><%=producto.getDescripcion()%></textarea>
    </div>

    <div>
        <label>Estado</label>

        <%if(producto.getStatus() == 1){%>
        <div>
            <input type="radio" name="status" id="status" value="1" checked="checked">
            <label>Activo</label>
        </div>
        <div>
            <input type="radio" name="status" id="status" value="0">
            <label>Inactivo</label>
        </div>
        <%} else {%>
        <div>
            <input type="radio" name="status" id="status" value="1">
            <label>Activo</label>
        </div>
        <div>
            <input type="radio" name="status" id="status" value="0" checked="checked">
            <label>Inactivo</label>
        </div>
        <%}%>
    </div>
    <div>
        <div>
            <input type="submit" class="shopBtn" value="Actualizar">
        </div>
    </div>

    </form>
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
