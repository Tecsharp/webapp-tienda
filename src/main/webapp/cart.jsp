<%@page contentType="text/html; charset=UTF-8" import="java.util.*, org.tecsharp.apiservlet.webapp.headers.models.*, java.text.DecimalFormat"%>
<%
Optional<String> username = (Optional<String>) request.getAttribute("username");
List<Producto> proca = (List<Producto>) request.getAttribute("proca");
String mensajeApp = (String) getServletContext().getAttribute("mensaje");
Integer numeroProductosEnCarrito = (Integer) request.getAttribute("numeroProductosEnCarrito");
String precioTotal = (String) request.getAttribute("precioTotal");
%>



<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Tecstore | Carrito</title>
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
				<a href="<%=request.getContextPath()%>/ver/carrito"><span class="icon-shopping-cart"></span> <%=numeroProductosEnCarrito%> Articulo(s) - <span class="badge badge-warning"> $<%=precioTotal%></span></a>
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
	<a href="<%=request.getContextPath()%>/ver/carrito"><span class="btn btn-mini"><%=numeroProductosEnCarrito%> <span class="icon-shopping-cart"></span></span></a>
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
			  <li class=""><a href="<%=request.getContextPath()%>/inicio">Inicio</a></li>
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
	<div class="span12">
    <ul class="breadcrumb">
		<li><a href="<%=request.getContextPath()%>/">Inicio</a> <span class="divider">/</span></li>
		<li class="active">Carrito</li>
    </ul>
	<div class="well well-small">
		<h1>Carrito <small class="pull-right"> <%=numeroProductosEnCarrito%> art??culo(s) en el carrito </small></h1>
	<hr class="soften"/>	

	<table class="table table-bordered table-condensed">
              <thead>
                <tr>
                  <th>Producto</th>
                  <th>Descripcion</th>
				  <th>	Ref. </th>
                  <th>Disponible</th>
                  <th>Precio</th>
                  <th>Cantidad</th>
                  <th>Total</th>
				</tr>
              </thead>
              <tbody>

                <%for (Producto pr : proca){%>
                <%
                Integer precio = pr.getPrecio();
                Integer items = pr.getNumItems();
                Integer precioF = precio * items;
                DecimalFormat formatea = new DecimalFormat("###,###,###");
                String precioFinal = formatea.format(precioF);
                %>


                <tr>
                  <td><img width="100" src="<%=pr.getImgLink()%>" alt=""></td>
                  <td><a href="<%=request.getContextPath()%>/ver/producto?id=<%=pr.getId()%>&idTipo=<%=pr.getTipo().getId()%>"><%=pr.getNombre()%></a><br>Disponibles: <%=pr.getStock()%><br><br><%=pr.getDescripcionCorta()%></td>
                  <td> - </td>
                  <td><span class="shopBtn"><span class="icon-ok"></span></span> </td>
                  <td>$ <%=pr.getPrecioFormateado()%></td>
                  <td>
                  <%=pr.getNumItems()%>
				  <div class="input-append">
				  <a href="<%=request.getContextPath()%>/eliminar/producto?idProducto=<%=pr.getId()%>&userId=${usuario.idUser}"><button class="btn btn-mini btn-danger" type="button"><span class="icon-remove"></span></button></a>
				  </div>
				</td>
                  <td>$ <%=precioFinal%></td>
                </tr>

                <%}%>


				 <tr>
                  <td colspan="6" class="alignR">Total a pagar</td>
                  <td class="label label-primary"> $ <%=precioTotal%></td>
                </tr>
				</tbody>
            </table><br/>
		
		
            <table class="table table-bordered">
			<tbody>
				 <tr>
                  <td> 
				<form class="form-inline">
				  <label style="min-width:159px"> VOUCHERS Code: </label> 
				<input type="text" class="input-medium" placeholder="CODE">
				<button type="submit" class="shopBtn"> ADD</button>
				</form>
				</td>
                </tr>
				
			</tbody>
				</table>
			<table class="table table-bordered">

            </table>
    <form action="<%=request.getContextPath()%>/comprar/carrito" method="post">
	<a href="<%=request.getContextPath()%>/inicio" class="shopBtn btn-large"><span class="icon-arrow-left"></span> Continuar comprando </a>


      <input type="submit" class="shopBtn btn-large pull-right" value="Comprar">
      <input type="hidden" name="iduser" value="${usuario.idUser}">

    </form>
    <!--
	<a href="<%=request.getContextPath()%>/comprar/carrito?userId=${usuario.idUser}" method="POST" class="shopBtn btn-large pull-right">Comprar carrito <span class="icon-arrow-right"></span></a>
    -->
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
