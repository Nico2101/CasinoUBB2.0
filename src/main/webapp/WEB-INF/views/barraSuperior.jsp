<%-- 
    Document   : barraSuperior
    Created on : 15-sep-2017, 22:41:01
    Author     : Nicolas
--%>
<%@ include file="/WEB-INF/views/include.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div class="navbar-container ace-save-state" id="navbar-container">
	<button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
		<span class="sr-only">Toggle sidebar</span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>

		<span class="icon-bar"></span>
	</button>

	<div class="navbar-header pull-left">
		<a href="irIndex.html" class="navbar-brand">
			<small>
				
				Casino UBB
			</small>
		</a>
	</div>

	<div class="navbar-buttons navbar-header pull-right" role="navigation">
		<ul class="nav ace-nav">


			<li class="light-blue dropdown-modal">
				<a data-toggle="dropdown" href="#" class="dropdown-toggle">
					<span class="user-info">
						<small>Bienvenido,</small>
						<c:if test="${not empty nombre}">
							<small>${nombre}</small>
							
						</c:if>
					</span>

					<i class="ace-icon fa fa-caret-down"></i>
				</a>

				<ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
					<li>
						<a href="salir.htm">
							<i class="ace-icon fa fa-power-off"></i>
							Cerrar Sesión
						</a>
					</li>
				</ul>
			</li>
		</ul>
	</div>
</div><!-- /.navbar-container -->
