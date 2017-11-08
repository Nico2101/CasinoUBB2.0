<%-- 
    Document   : index
    Created on : 15-sep-2017, 22:45:00
    Author     : Nicolas
--%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="cabecera.jsp"%>
<!-- HTML meta refresh URL redirection -->

</head>

<body class="no-skin">
	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<%@ include file="barraSuperior.jsp"%>
	</div>

	<div class="main-container ace-save-state" id="main-container">

		<div id="sidebar"
			class="sidebar                  responsive                    ace-save-state">
			<script type="javascript">
                    try{ace.settings.loadState('sidebar')}catch(e){}
                </script>
			<%@ include file="barraLateralUsuario.jsp"%>

			<div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
				<i id="sidebar-toggle-icon"
					class="ace-icon fa fa-angle-double-left ace-save-state"
					data-icon1="ace-icon fa fa-angle-double-left"
					data-icon2="ace-icon fa fa-angle-double-right"></i>
			</div>
		</div>

		<div class="main-content">
			<!--inicio contenido-->
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs"></div>

				<div class="page-content">

					<div class="page-header"></div>
					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->


							<c:if test="${not empty NoHayAlmuerzosEnHorario}">
								<script>
									toastr
											.warning("No quedan almuerzos en el horario indicado");
								</script>
							</c:if>
							
							<c:if test="${not empty HorarioCambiado}">
								<script>
									toastr
											.success("Horario Cambiado Correctamente!");
								</script>
							</c:if>
							


							<div align="center" class="page-header">
								<h1>
									<strong>Horario Disponible</strong>
								</h1>
							</div>
							<br>

							<div align="center">
								<table class="table table-condensed" style="width: 1000px">
									<thead>
										<tr>
											<th>ID Reserva</th>
											<th>ID Menú</th>
											<th>Menú</th>
											<th>Hora Inicio</th>
											<th>Hora Fin</th>
											<th>Raciones Disponibles</th>
											<th></th>
										</tr>
									</thead>

									<c:forEach var="horario" items="${horarioDisponible}">

										<c:if test="${idHorario==horario.id}">
											<tr bgcolor="#00FFFF">
												<td>${idReserva}</td>
												<td>${menu.id}</td>
												<td><c:out value="${menu.nombre}"></c:out></td>
												<td><c:out value="${horario.horaInicio}"></c:out></td>
												<td><c:out value="${horario.horaFin}"></c:out></td>
												<td><c:out value="${horario.cantMaxRaciones}"></c:out></td>
												<td><strong>Horario Actualmente Reservado</strong></td>
											</tr>
										</c:if>
										<c:if test="${idHorario!=horario.id}">
											<tr>
												<td>${idReserva}</td>
												<td>${menu.id}</td>
												<td><c:out value="${menu.nombre}"></c:out></td>
												<td><c:out value="${horario.horaInicio}"></c:out></td>
												<td><c:out value="${horario.horaFin}"></c:out></td>
												<td><c:out value="${horario.cantMaxRaciones}"></c:out></td>
												<td><a
													href="actualizarHorarioMenu.htm?idReserva=${idReserva}&id_menu=${menu.id}&idHorarioSeleccionado=${idHorario}&idHorarioNuevo=${horario.id}&cantRaciones=${horario.cantMaxRaciones}"><input
														class="btn btn-primary btn-sm" type="button"
														value="Seleccionar nuevo horario" /></a></td>
											</tr>
										</c:if>

									</c:forEach>

								</table>
							</div>


							<!-- /.row -->
							<!-- PAGE CONTENT ENDS -->
							<!-- PAGE CONTENT ENDS -->
						</div>
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.page-content -->
			</div>
		</div>
		<!-- /.main-content -->

		<div class="footer">
			<%@ include file="pieDePagina.jsp"%>
		</div>

		<a href="#" id="btn-scroll-up"
			class="btn-scroll-up btn btn-sm btn-inverse"> <i
			class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
		</a>
	</div>
	<!-- /.main-container -->



	<%@ include file="scripts.jsp"%>
</body>


</html>
