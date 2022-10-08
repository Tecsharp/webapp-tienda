<%@page contentType="text/html; charset=UTF-8" import="java.util.*, org.tecsharp.apiservlet.webapp.headers.models.*"%>
<%
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
List<TipoProducto> categorias = (List<TipoProducto>) request.getAttribute("categorias");
Optional<String> username = (Optional<String>) request.getAttribute("username");
Integer productosEnCarrito = (Integer) request.getAttribute("productosEnCarrito");
String precioTotal = (String) request.getAttribute("precioTotal");
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Inicia sesion</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <!-- Bootstrap styles -->
    <link href="assets/css/bootstrap.css" rel="stylesheet"/>
    <!-- Customize styles -->
    <link href="style.css" rel="stylesheet"/>
    <!-- font awesome styles -->
	<link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet">
		<!--[if IE 7]>
			<link href="css/font-awesome-ie7.min.css" rel="stylesheet">
		<![endif]-->

		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

	<!-- Favicons -->
    <link rel="shortcut icon" href="assets/ico/favicon.ico">
  </head>
<body>
<!-- 
	Upper Header Section 
-->
<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="topNav">
		<div class="container">
			<div class="alignR">
			    <a href="<%=request.getContextPath()%>/index.html"> <span class="icon-home"></span>Inicio</a>
                <%if(username.isPresent()){%>
				<a href="<%=request.getContextPath()%>/mi-cuenta"><span class="icon-user"></span> Mi cuenta</a>
				<a href="cart.html"><span class="icon-shopping-cart"></span> 2 Item(s) - <span class="badge badge-warning"> $448.42</span></a>
				<%}else {%>
				<a href="<%=request.getContextPath()%>/registro"><span class="icon-edit"></span> Registrate </a>
				 <%}%>

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
	<a class="logo" href="index.html"><span>Twitter Bootstrap ecommerce template</span> 
		<img src="<%=request.getContextPath()%>/assets/img/logo-bootstrap-shoping-cart.png" alt="bootstrap sexy shop">
	</a>
	</h1>
	</div>

    <div class="span8 alignR">
	<p><br> <strong> Soporte (24/7) :  0800 1234 678 </strong><br><br></p>
    <%if(username.isPresent()){%>
	<a href="<%=request.getContextPath()%>/ver/carrito"><span class="btn btn-mini"><%=productosEnCarrito%> <span class="icon-shopping-cart"></span></span></a>
    <%}%>
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
              <li class=""><a href="<%=request.getContextPath()%>/index.html">Inicio&nbsp; </a></li>
			  <li class=""></li>
			  <li class=""><a href="<%=request.getContextPath()%>/productos/todos">Productos</a></li>
			  <li class=""></li>
			  <li class=""></li>
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
       <%for (TipoProducto c : categorias){%>
        <li><a href="<%=request.getContextPath()%>/productos?idCat=<%=c.getId()%>"><span class="icon-chevron-right"></span><%=c.getNombre()%></a></li>
       <%}%>
		<li style="border:0"> &nbsp;</li>
		<%if(username.isPresent()){%>
	    <li> <a class="totalInCart" href="<%=request.getContextPath()%>/ver/carrito"><strong>Monto total  <span class="badge badge-warning pull-right" style="line-height:18px;">$<%=precioTotal%></span></strong></a></li>
	    <%}%>
	</ul>
</div>


			  <div class="well well-small" ><a href="#"><img src="assets/img/paypal.jpg" alt="payment method paypal"></a></div>
			

			<br>
			<br>


	</div>
	<div class="span9">
    <ul class="breadcrumb">
		<li><a href="index.html">Inicio</a> <span class="divider">/</span></li>
		<li class="active">Iniciar</li>
    </ul>
	<h3> Iniciar sesion</h3>
	<hr class="soft"/>
	
	<div class="row">
		<div class="span4">
			<div class="well">
			<h5>CREA UNA CUENTA</h5>
			<h3>Si no tienes cuenta, registrate.</h3>
			  <div class="controls">
			  <a href="<%=request.getContextPath()%>/registro">
			  <button type="submit" class="defaultBtn">CREAR CUENTA</button>
			  </a>
			  </div>

		</div>
		</div>
		<div class="span1"> &nbsp;</div>
		<div class="span4">
			<div class="well">
			<h5>YA ESTAS REGISTRADO?</h5>
			<form action="/webapp-tienda/inicio" method="post">
			  <div class="control-group">
				<label class="control-label" for="inputEmail">Usuario</label>
				<div class="controls">
				  <input class="span3"  type="text" placeholder="Usuario" name="username" id="username">
				</div>
			  </div>
			  <div class="control-group">
				<label class="control-label" for="inputPassword">Contraseña</label>
				<div class="controls">
				  <input type="password" class="span3" placeholder="Contraseña" name="password" id="password">
				</div>
			  </div>
			  <div class="control-group">
				<div class="controls">
				  <button type="submit" class="defaultBtn">INICIAR SESION</button> <a href="#"></a>
				</div>
			  </div>
			</form>
		</div>
		</div>
	</div>	
	
</div>
</div>
<!-- 
Clients 
-->
<section class="our_client">
	<hr class="soften"/>
	<h4 class="title cntr"><span class="text">Mejores marcas</span></h4>
	<hr class="soften"/>
	<div class="row">
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/1.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/2.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/3.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/4.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/5.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="assets/img/6.png"></a>
		</div>
	</div>
</section>

<!--
Footer
-->
<jsp:include page="footer.jsp" />
</div><!-- /container -->

<div class="copyright">
<div class="container">
	<p class="pull-right">
		<a href="#"><img src="assets/img/maestro.png" alt="payment"></a>
		<a href="#"><img src="assets/img/mc.png" alt="payment"></a>
		<a href="#"><img src="assets/img/pp.png" alt="payment"></a>
		<a href="#"><img src="assets/img/visa.png" alt="payment"></a>
		<a href="#"><img src="assets/img/disc.png" alt="payment"></a>
	</p>
	<span><%=mensajeApp%></span>
</div>
</div>
<a href="#" class="gotop"><i class="icon-double-angle-up"></i></a>
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="assets/js/jquery.js"></script>
	<script src="assets/js/bootstrap.min.js"></script>
	<script src="assets/js/jquery.easing-1.3.min.js"></script>
    <script src="assets/js/jquery.scrollTo-1.4.3.1-min.js"></script>
    <script src="assets/js/shop.js"></script>
  </body>
</html>
