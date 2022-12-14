<%@page contentType="text/html; charset=UTF-8" import="java.util.*, org.tecsharp.apiservlet.webapp.headers.models.*"%>
<%
Optional<String> username = (Optional<String>) request.getAttribute("username");
Usuario usuario = (Usuario) request.getAttribute("usuario");
Optional<Integer> userAdminOptional = (Optional<Integer>) request.getAttribute("userAdminOptional");
List<TipoProducto> categorias = (List<TipoProducto>) request.getAttribute("categorias");
List<Producto> carruselUno = (List<Producto>) request.getAttribute("carruselUno");
List<Producto> carruselDos = (List<Producto>) request.getAttribute("carruselDos");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
String precioTotal = (String) request.getAttribute("precioTotal");
Integer productosEnCarrito = (Integer) request.getAttribute("productosEnCarrito");
List<Producto> productosRandom = (List<Producto>) request.getAttribute("productosRandom");
List<Producto> productosPopulares = (List<Producto>) request.getAttribute("productosPopulares");

%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Tecstore | Mas es menos</title>
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
	<a class="logo" href="index.html"><span>Twitter Bootstrap ecommerce template</span> 
		<img src="assets/img/logo-bootstrap-shoping-cart.png" alt="bootstrap sexy shop">
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
			  <li class="active"><a href="<%=request.getContextPath()%>/index.html">Inicio</a></li>
			  <li class=""></li>
			  <li class=""><a href="<%=request.getContextPath()%>/productos/todos">Productos</a></li>
			  <li class=""></li>
			  <li class=""></li>
			  <%if(usuario != null){%>
			  <%}%>
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
<div class="well well-small" ><a href="#"><img src="assets/img/paypal.jpg" alt="payment method paypal"></a></div>
<br>
			<br>

			<!--
			<ul class="nav nav-list promowrapper">
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/bootstrap-ecommerce-templates.png" alt="bootstrap ecommerce templates">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VER</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
			<li style="border:0"> &nbsp;</li>
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/shopping-cart-template.png" alt="shopping cart template">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VER</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
			<li style="border:0"> &nbsp;</li>
			<li>
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<img src="assets/img/bootstrap-template.png" alt="bootstrap template">
				<div class="caption">
				  <h4><a class="defaultBtn" href="product_details.html">VER&nbsp;</a> <span class="pull-right">$22.00</span></h4>
				</div>
			  </div>
			</li>
		  </ul>
           -->
	</div>
	<div class="span9">
	<div class="well np">
		<div id="myCarousel" class="carousel slide homCar">
            <div class="carousel-inner">
			  <div class="item">
                <img style="width:100%" src="assets/img/bootstrap_free-ecommerce.png" alt="bootstrap ecommerce templates">
                <div class="carousel-caption">
                      <h4>MacBook PRO 13</h4>
                      <p><span>Elegancia en abundancia</span></p>
                </div>
              </div>
			  <div class="item">
                <img style="width:100%" src="assets/img/carousel1.png" alt="bootstrap ecommerce templates">
                <div class="carousel-caption">
                      <h4>Italika FT125 TS</h4>
                      <p><span>La guerrera del trabajo</span></p>
                </div>
              </div>
			  <div class="item active">
                <img style="width:100%" src="assets/img/carousel3.png" alt="bootstrap ecommerce templates">
                <div class="carousel-caption">
                      <h4>Huawei P30 PRO</h4>
                      <p><span>Bueno, bonito y barato</span></p>
                </div>
              </div>
              <div class="item">
                <img style="width:100%" src="assets/img/bootstrap-templates.png" alt="bootstrap templates">
                <div class="carousel-caption">
                      <h4>Pulsar NS200</h4>
                      <p><span>De lo mejor de Bajaj</span></p>
                </div>
              </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">&rsaquo;</a>
          </div>
        </div>
<!--
New Products
-->
	<div class="well well-small">
	<h3>Nuevos productos </h3>
	<hr class="soften"/>
		<div class="row-fluid">
		<div id="newProductCar" class="carousel slide">
            <div class="carousel-inner">
			<div class="item active">
			  <ul class="thumbnails">

				<!== OPCION UNO -->

                <%for (Producto c : carruselUno){%>
				<li class="span3">
				  <div class="thumbnail">
					<a class="zoomTool" href="<%=request.getContextPath()%>/ver/producto?id=<%=c.getId()%>&idTipo=<%=c.getTipo().getId()%>" title="Ver detalles"><span class="icon-search"></span> VER DETALLES</a>
					<a  href="<%=request.getContextPath()%>/ver/producto?id=<%=c.getId()%>&idTipo=<%=c.getTipo().getId()%>"><img src="<%=c.getImgLink()%>" alt=""></a>
				  </div>
				</li>
                <%}%>

			  </ul>
			  </div>


            <!== OPCION DOS -->

		   <div class="item">
		  <ul class="thumbnails">

		  <%for (Producto d : carruselDos){%>
			<li class="span3">
			  <div class="thumbnail">
				<a class="zoomTool" href="<%=request.getContextPath()%>/ver/producto?id=<%=d.getId()%>&idTipo=<%=d.getTipo().getId()%>" title="Ver detalles"><span class="icon-search"></span>VER DETALLES</a>
				<a  href="<%=request.getContextPath()%>/ver/producto?id=<%=d.getId()%>"><img src="<%=d.getImgLink()%>" alt=""></a>
			  </div>
			</li>
            <%}%>

		  </ul>
		  </div>
		   </div>
		  <a class="left carousel-control" href="#newProductCar" data-slide="prev">&lsaquo;</a>
            <a class="right carousel-control" href="#newProductCar" data-slide="next">&rsaquo;</a>
		  </div>
		  </div>

		<div class="row-fluid">
		  <ul class="thumbnails">


		  <%for(Producto pr : productosRandom){%>
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="<%=request.getContextPath()%>/ver/producto?id=<%=pr.getId()%>&idTipo=<%=pr.getTipo().getId()%>" title="add to cart"><span class="icon-search"></span> VER DETALLES</a>
				<a href="<%=request.getContextPath()%>/ver/producto?id=<%=pr.getId()%>&idTipo=<%=pr.getTipo().getId()%>"><img src="<%=pr.getImgLink()%>" alt=""></a>
				<div class="caption cntr">
					<p><%=pr.getNombre()%></p>
					<p><strong>$ <%=pr.getPrecioFormateado()%></strong></p>

					<%if(username.isPresent()){%>
					<h4><a class="shopBtn" href="<%=request.getContextPath()%>/agregar/carro?productoID=<%=pr.getId()%>&idUser=${usuario.idUser}" title="add to cart"> Agregar </a></h4>
					<%}%>
					<br class="clr">
				</div>
			  </div>
			</li>
			<%}%>

			<!--
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="assets/img/c.jpg" alt=""></a>
				<div class="caption cntr">
					<p>Manicure & Pedicure</p>
					<p><strong></strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> Agregar&nbsp; </a></h4>
					<div class="actionList">
						<a class="pull-left" href="#">Add to Wish List </a> 
						<a class="pull-left" href="#"> Add to Compare </a>
					</div> 
					<br class="clr">
				</div>
			  </div>
			</li>
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a href="product_details.html"><img src="assets/img/a.jpg" alt=""></a>
				<div class="caption cntr">
					<p>Manicure & Pedicure</p>
					<p><strong> $22.00</strong></p>
					<h4><a class="shopBtn" href="#" title="add to cart"> Agregar </a></h4>
					<div class="actionList">
						<a class="pull-left" href="#">Add to Wish List </a> 
						<a class="pull-left" href="#"> Add to Compare </a>
					</div> 
					<br class="clr">
				</div>
			  </div>
			</li>
			-->

		  </ul>
		</div>


	</div>
	<!--
	Featured Products
	-->
		<div class="well well-small">
		  <h3><a class="btn btn-mini pull-right" href="<%=request.getContextPath()%>/productos/todos" title="Ver mas">Ver m??s<span class="icon-plus"></span></a> M??s poulares  </h3>
		  <hr class="soften"/>
		  <div class="row-fluid">
		  <ul class="thumbnails">

		    <%for(Producto pp : productosPopulares){%>
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="<%=request.getContextPath()%>/ver/producto?id=<%=pp.getId()%>&idTipo=<%=pp.getTipo().getId()%>" title="add to cart"><span class="icon-search"></span> VER DETALLES</a>
				<a  href="<%=request.getContextPath()%>/ver/producto?id=<%=pp.getId()%>&idTipo=<%=pp.getTipo().getId()%>"><img src="<%=pp.getImgLink()%>" alt=""></a>
				<div class="caption">
				  <h5><%=pp.getNombre()%></h5>
				  <h4>
					  <a class="defaultBtn" href="<%=request.getContextPath()%>/ver/producto?id=<%=pp.getId()%>&idTipo=<%=pp.getTipo().getId()%>" title="Ver detalles"><span class="icon-zoom-in"></span></a>
					  <%if(username.isPresent()){%>
					  <a class="shopBtn" href="<%=request.getContextPath()%>/agregar/carro?productoID=<%=pp.getId()%>&idUser=${usuario.idUser}" title="Agregar al carro"><span class="icon-plus"></span></a>
					  <%}%>
					  <span class="pull-right">$ <%=pp.getPrecioFormateado()%></span>
				  </h4>
				</div>
			  </div>
			</li>
			<%}%>

			<!--
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a  href="product_details.html"><img src="assets/img/e.jpg" alt=""></a>
				<div class="caption">
				  <h5>Manicure & Pedicure</h5>
				  <h4>
					  <a class="defaultBtn" href="product_details.html" title="Click to view"><span class="icon-zoom-in"></span></a>
					  <a class="shopBtn" href="#" title="add to cart"><span class="icon-plus"></span></a>
					  <span class="pull-right">$22.00</span>
				  </h4>
				</div>
			  </div>
			</li>
			<li class="span4">
			  <div class="thumbnail">
				<a class="zoomTool" href="product_details.html" title="add to cart"><span class="icon-search"></span> QUICK VIEW</a>
				<a  href="product_details.html"><img src="assets/img/f.jpg" alt=""/></a>
				<div class="caption">
				  <h5>Manicure & Pedicure</h5>
				  <h4>
					  <a class="defaultBtn" href="product_details.html" title="Click to view"><span class="icon-zoom-in"></span></a>
					  <a class="shopBtn" href="#" title="add to cart"><span class="icon-plus"></span></a>
					  <span class="pull-right">$22.00</span>
				  </h4>
				</div>
			  </div>
			</li>
			-->

		  </ul>	
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