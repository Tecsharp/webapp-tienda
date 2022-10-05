<%@page contentType="text/html; charset=UTF-8" import="java.util.*, org.tecsharp.apiservlet.webapp.headers.models.*"%>
<%
Optional<String> username = (Optional<String>) request.getAttribute("username");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
List<Producto> todosLosProductos = (List<Producto>) request.getAttribute("todosLosProductos");
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
			    <a href="<%=request.getContextPath()%>/index.html"> <span class="icon-home"></span> Inicio</a>
                <%if(username.isPresent()){%>
				<a href="<%=request.getContextPath()%>/mi-perfil"><span class="icon-user"></span> My Account</a>
				<a href="<%=request.getContextPath()%>/ver/carrito"><span class="icon-shopping-cart"></span> Articulo(s) - <span class="badge badge-warning"> </span></a>
				<%}else {%>
				<a href="<%=request.getContextPath()%>/registrarse"><span class="icon-edit"></span> Registrate </a>
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
	<a href="<%=request.getContextPath()%>/ver/carrito"><span class="btn btn-mini"> <span class="icon-shopping-cart"></span></span></a>
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
			  <li class=""><a href="<%=request.getContextPath()%>/index.html">Inicio	</a></li>
			  <li class=""><a href="list-view.html">Productos</a></li>

			</ul>
			<form action="#" class="navbar-search pull-left">
			  <input type="text" placeholder="Search" class="search-query span2">
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
    <h2>Actualizar productos</h2>
    <h4>Filtrar por categoria</h4>
    <form action="<%=request.getContextPath()%>/crud/actualizar" method="post">
        <div>
                <div>
                    <select name="categoria" id="categoria">
                        <option value="0"> Todos</option>
                        <option value="1"> Motocicletas</option>
                        <option value="2"> Telefonia</option>
                        <option value="3"> Refrigeracion</option>
                        <option value="4"> Videojuegos</option>
                        <option value="5"> Computo</option>
                    </select>
                    <div>
                        <input type="submit" class="shopBtn" value="Filtrar">
                    </div>
                </div>
        </div>
    </form>

	<%for (Producto tp : todosLosProductos){%>
	<%if(tp.getStatus() == 1){%>
	<hr class="soften">
	<div class="row-fluid">	  
		<div class="span2">
			<a href="<%=request.getContextPath()%>/ver/producto?id=<%=tp.getId()%>"><img src="<%=tp.getImgLink()%>" alt="<%=tp.getNombre()%>"></a>
		</div>
		<div class="span6">
			<h5><a href="<%=request.getContextPath()%>/ver/producto?id=<%=tp.getId()%>"><%=tp.getNombre()%></a></h5>
			<p>
			<b>Disponibles: <%=tp.getStock()%></b>
			<br>
			<br>
			<%=tp.getDescripcionCorta()%>
			</p>
		</div>
		<div class="span4 alignR">
		<form class="form-horizontal qtyFrm">
		<h3> $<%=tp.getPrecioFormateado()%></h3>
		<br>
		<div class="btn-group">
		  <a href="<%=request.getContextPath()%>/crud/actualizar/producto?id=<%=tp.getId()%>" class="defaultBtn"><span class="btn btn-mini btn-danger"></span> Actualizar producto</a>
		  <a href="<%=request.getContextPath()%>/ver/producto?id=<%=tp.getId()%>" class="shopBtn">VER</a>
		 </div>
			</form>
		</div>
	</div>
	<%}%>
	<%}%>
</div>
</div>
</div>
<!-- 
Clients 
-->


<!--
Footer
-->
<footer class="footer">
<div class="row-fluid">
<div class="span2">
<h5>Your Account</h5>
<a href="#">YOUR ACCOUNT</a><br>
<a href="#">PERSONAL INFORMATION</a><br>
<a href="#">ADDRESSES</a><br>
<a href="#">DISCOUNT</a><br>
<a href="#">ORDER HISTORY</a><br>
 </div>
<div class="span2">
<h5>Iinformation</h5>
<a href="contact.html">CONTACT</a><br>
<a href="#">SITEMAP</a><br>
<a href="#">LEGAL NOTICE</a><br>
<a href="#">TERMS AND CONDITIONS</a><br>
<a href="#">ABOUT US</a><br>
 </div>
<div class="span2">
<h5>Our Offer</h5>
<a href="#">NEW PRODUCTS</a> <br>
<a href="#">TOP SELLERS</a><br>
<a href="#">SPECIALS</a><br>
<a href="#">MANUFACTURERS</a><br>
<a href="#">SUPPLIERS</a> <br/>
 </div>
 <div class="span6">
<h5>The standard chunk of Lorem</h5>
The standard chunk of Lorem Ipsum used since the 1500s is reproduced below for
 those interested. Sections 1.10.32 and 1.10.33 from "de Finibus Bonorum et 
 Malorum" by Cicero are also reproduced in their exact original form, 
accompanied by English versions from the 1914 translation by H. Rackham.
 </div>
 </div>
</footer>
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