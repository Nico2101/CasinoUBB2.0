<%-- 
    Document   : barraLateral
    Created on : 15-sep-2017, 22:40:52
    Author     : Nicolas
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<ul class="nav nav-list">
	<li class="active"><a href="indexAdministrador.htm"> <i
			class="menu-icon fa fa-home"></i> <span class="menu-text">
				Inicio </span>
	</a> <b class="arrow"></b></li>

	<li class=""><a href="#" class="dropdown-toggle"> <i
			class="menu-icon glyphicon glyphicon-cutlery"></i> <span
			class="menu-text"> Menú </span> <b class="arrow fa fa-angle-down"></b>
	</a> <b class="arrow"></b>

		<ul class="submenu">
			<li class=""><a href="ingresarMenu.htm"> <i
					class="menu-icon fa fa-caret-right"></i> Ingresar Menú
			</a> <b class="arrow"></b></li>

			<li class=""><a href="buscarMenu2.htm"> <i
					class="menu-icon fa fa-caret-right"></i> Modificar Menú
			</a> <b class="arrow"></b></li>
		</ul></li>

	<li class=""><a href="#" class="dropdown-toggle"> <i
			class="menu-icon glyphicon glyphicon-star"></i> <span
			class="menu-text"> Valoraciones </span> <b
			class="arrow fa fa-angle-down"></b>
	</a> <b class="arrow"></b>

		<ul class="submenu">
			<li class=""><a href="#"> <i
					class="menu-icon fa fa-caret-right"></i> Ver Valoraciones
			</a> <b class="arrow"></b></li>

			<li class=""><a href="verPromedioValoraciones.htm"> <i
					class="menu-icon fa fa-caret-right"></i> Promedio Valoraciones
			</a> <b class="arrow"></b></li>

		</ul></li>

	<li class=""><a href="#" class="dropdown-toggle"> <i
			class="menu-icon fa fa-users"></i> <span class="menu-text">
				Usuarios </span> <b class="arrow fa fa-angle-down"></b>
	</a> <b class="arrow"></b>

		<ul class="submenu">

			<li class=""><a href="" data-toggle="modal"
				data-target="#modal-table"><i
					class="menu-icon fa fa-caret-right"></i> Aumentar Saldo</a> <b
				class="arrow"></b></li>

			<li class=""><a href="listarUsuarios.htm"> <i
					class="menu-icon fa fa-caret-right"></i> Ver Total Usuarios
			</a> <b class="arrow"></b></li>

			<li class=""><a href="listaPersonasMes.htm"> <i
					class="menu-icon fa fa-caret-right"></i> Listar Usuarios con
					Reserva
			</a> <b class="arrow"></b></li>
		</ul></li>

	<li class=""><a href="#" class="dropdown-toggle"> <i
			class="menu-icon glyphicon glyphicon-file"></i> <span
			class="menu-text"> Informes </span> <b class="arrow fa fa-angle-down"></b>
	</a> <b class="arrow"></b>

		<ul class="submenu">
			<li class=""><a href="informeAlmuerzos.htm"> <i
					class="menu-icon fa fa-caret-right"></i> Total Almuerzos Vendidos
			</a> <b class="arrow"></b></li>

		</ul></li>

	<li class=""><a href="liberarCupos.htm" > <i
			class="menu-icon glyphicon glyphicon-ok"></i> <span class="menu-text">
				Liberar Cupos </span>


	</a> <b class="arrow"></b></li>




</ul>
<!-- /.nav-list -->
