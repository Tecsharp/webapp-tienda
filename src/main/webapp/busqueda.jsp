<%@page contentType="text/html; charset=UTF-8" import="java.util.*, org.tecsharp.apiservlet.webapp.headers.models.*"%>
<%
List<Producto> todosLosProductos = (List<Producto>) request.getAttribute("todosLosProductos");
List<TipoProducto> categorias = (List<TipoProducto>) request.getAttribute("categorias");
List<Producto> productosFiltrados = (List<Producto>) request.getAttribute("productosFiltrados");
Optional<String> username = (Optional<String>) request.getAttribute("username");
String precioTotal = (String) request.getAttribute("precioTotal");
Integer productosEnCarrito = (Integer) request.getAttribute("productosEnCarrito");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Tecstore | Buscar </title>
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
				<a href="<%=request.getContextPath()%>/mi-cuenta"><span class="icon-user"></span> Mi cuenta</a>
				<a href="<%=request.getContextPath()%>/ver/carrito"><span class="icon-shopping-cart"></span> <%=productosEnCarrito%> Articulo(s) - <span class="badge badge-warning"> $<%=precioTotal%></span></a>
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
	<a class="logo" href="index.html"><span>Tecstore</span>
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
			  <li class=""><a href="<%=request.getContextPath()%>/index.html">Inicio	</a></li>
			  <li class=""><a href="<%=request.getContextPath()%>/productos/todos">Productos</a></li>

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
            		<a href="<%=request.getContextPath()%>/logout"> <button type="button" class="shopBtn btn-block">Cerrar sesi??n</button></a>
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

              <!--
			  <div class="well well-small alert alert-warning cntr">
				  <h2>50% Discount</h2>
				  <p> 
					 only valid for online order. <br><br><a class="defaultBtn" href="#">Click here </a>
				  </p>
			  </div>
			  <div class="well well-small" ><a href="#"><img src="<%=request.getContextPath()%>/assets/img/paypal.jpg" alt="payment method paypal"></a></div>
			
			<a class="shopBtn btn-block" href="#">Upcoming products <br><small>Click to view</small></a>
			<br>
			<br>
			<ul class="nav nav-list promowrapper">
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="<%=request.getContextPath()%>/assets/img/bootstrap-ecommerce-templates.png" alt="bootstrap ecommerce templates">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
			<li style="border:0"> &nbsp;</li>
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="<%=request.getContextPath()%>/assets/img/shopping-cart-template.png" alt="shopping cart template">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
			<li style="border:0"> &nbsp;</li>
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="<%=request.getContextPath()%>/assets/img/bootstrap-template.png" alt="bootstrap template">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VIEW</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
		  </ul>
            -->
          <div class="well well-small" ><a href="#"><img src="<%=request.getContextPath()%>/assets/img/paypal.jpg" alt="payment method paypal"></a></div>

	</div>

<div class="span9">
<div class="well well-small">
	<%for (Producto tp : productosFiltrados){%>
	<%if(tp.getStatus() == 1){%>
	<hr class="soften">
	<div class="row-fluid">	  
		<div class="span2">
			<a href="<%=request.getContextPath()%>/ver/producto?id=<%=tp.getId()%>&idTipo=<%=tp.getTipo().getId()%>"><img src="<%=tp.getImgLink()%>" alt="<%=tp.getNombre()%>"></a>
		</div>
		<div class="span6">
			<h5><a href="<%=request.getContextPath()%>/ver/producto?id=<%=tp.getId()%>&idTipo=<%=tp.getTipo().getId()%>"><%=tp.getNombre()%></a></h5>
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
		  <%if(username.isPresent()){%>
		  <a href="<%=request.getContextPath()%>/agregar/carro?productoID=<%=tp.getId()%>&idUser=${usuario.idUser}" class="defaultBtn"><span class=" icon-shopping-cart"></span> Agregar al carro</a>
		  <%}else{%>
		  <h6 style="color:#E69537";> Inicia sesi??n para agregar al carrito.</h6>
		  <%}%>
		  <a href="<%=request.getContextPath()%>/ver/producto?id=<%=tp.getId()%>&idTipo=<%=tp.getTipo().getId()%>" class="shopBtn">VER</a>
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
<section class="our_client">
	<hr class="soften"/>
	<h4 class="title cntr"><span class="text">Mejores marcas</span></h4>
	<hr class="soften"/>
	<div class="row">
		<div class="span2">
			<a href="#"><img alt="" src="<%=request.getContextPath()%>/assets/img/1.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="<%=request.getContextPath()%>/assets/img/2.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="<%=request.getContextPath()%>/assets/img/3.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="<%=request.getContextPath()%>/assets/img/4.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="<%=request.getContextPath()%>/assets/img/5.png"></a>
		</div>
		<div class="span2">
			<a href="#"><img alt="" src="<%=request.getContextPath()%>/assets/img/6.png"></a>
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
		<a href="#"><img src="<%=request.getContextPath()%>/assets/img/maestro.png" alt="payment"></a>
		<a href="#"><img src="<%=request.getContextPath()%>/assets/img/mc.png" alt="payment"></a>
		<a href="#"><img src="<%=request.getContextPath()%>/assets/img/pp.png" alt="payment"></a>
		<a href="#"><img src="<%=request.getContextPath()%>/assets/img/visa.png" alt="payment"></a>
		<a href="#"><img src="<%=request.getContextPath()%>/assets/img/disc.png" alt="payment"></a>
	</p>
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