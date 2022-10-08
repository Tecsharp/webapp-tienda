<%@page contentType="text/html; charset=UTF-8" import="java.util.*, org.tecsharp.apiservlet.webapp.headers.models.*"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Cabeceras Http</title>
</head>
<body>

<div class="navbar navbar-inverse navbar-fixed-top">
	<div class="topNav">
		<div class="container">
			<div class="alignR">
			    <a href="<%=request.getContextPath()%>/index.html"> <span class="icon-home"></span> Inicio</a>
                <%if(username.isPresent()){%>
				<a href="<%=request.getContextPath()%>/mi-cuenta"><span class="icon-user"></span> Mi cuenta</a>
				<a href="<%=request.getContextPath()%>/ver/carrito"><span class="icon-shopping-cart"></span> <%=productosEnCarrito%> Articulo(s) - <span class="badge badge-warning"> $<%=precioTotal%></span></a>
				<%}else {%>
				<a href="<%=request.getContextPath()%>/registrarse"><span class="icon-edit"></span> Registrate </a>
				 <%}%>

			</div>
		</div>
	</div>
</div>


</body>
</html>