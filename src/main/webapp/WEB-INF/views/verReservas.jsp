<%@ include file="/WEB-INF/views/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@page import="java.util.LinkedList"%>
<%@page import="com.proyecto.transferObject.MenuTO"%>
<%@page import="com.proyecto.transferObject.HorarioTO"%>
<%@page import="com.proyecto.transferObject.ReservaTO"%>
<%@ include file="cabecera.jsp"%>
<!-- HTML meta refresh URL redirection -->

</head>

<body class="no-skin">
	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<%@ include file="barraSuperior.jsp"%>
	</div>

	<div class="main-container ace-save-state" id="main-container">
		<script type="text/javascript">
			try {
				ace.settings.loadState('main-container')
			} catch (e) {
			}
		</script>

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

							<div align="center" class="page-header">
								<h1>
									<strong>Reservas</strong>
								</h1>
							</div>

							<c:if test="${not empty NoHayAlmuerzos}">
								<script>
									toastr.warning("No quedan almuerzos");
								</script>
							</c:if>

							<div align="center">
								<table class="table table-condensed" style="width: 800px">
									<thead>
										<tr>
											<th>ID Reserva</th>
											<th>Nombre Menú</th>
											<th>Tipo</th>
											<th>Precio</th>
											<th>Fecha</th>
											<th>Hora Inicio</th>
											<th>Hora Fin</th>
											<th>Editar/Eliminar</th>



										</tr>
									</thead>

									<%
										LinkedList<MenuTO> list = (LinkedList<MenuTO>) request.getAttribute("datosMenuReservado");
										LinkedList<HorarioTO> list2 = (LinkedList<HorarioTO>) request.getAttribute("datosHorarioReservado");
										LinkedList<ReservaTO> list3 = (LinkedList<ReservaTO>) request.getAttribute("datosReserva");

										if (list != null && list2 != null)
											for (int i = 0; i < list.size(); i++) {
												MenuTO menu = list.get(i);
												HorarioTO horario = list2.get(i);
												ReservaTO reserva = list3.get(i);
									%>
									<tr>

										<td><%=reserva.getId()%></td>
										<td><%=menu.getNombre()%></td>
										<td><%=menu.getTipo()%></td>
										<td><%=menu.getPrecio()%></td>
										<td><%=menu.getFecha()%></td>
										<td><%=horario.getHoraInicio()%></td>
										<td><%=horario.getHoraFin()%></td>
										<td><a class="blue" href="#"
											onclick="datos('<%=reserva.getId()%>','<%=menu.getId()%>','<%=horario.getId()%>','<%=menu.getFecha()%>');"
											data-toggle="modal" data-target="#modal-table"> <i
												class="ace-icon fa fa-pencil bigger"> </i>
										</a> &nbsp; <a class="red" href="#"> <i
												class="ace-icon fa fa-trash bigger"> </i>
										</a></td>


									</tr>
									<%
										}
										else {
									%>
									<h1>No hay datos</h1>
									<%
										}
									%>

								</table>
							</div>

							<!-- /.row -->

							<div id="modal-table" class="modal fade" tabindex="-1">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header no-padding">
											<div class="table-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true" onclick="borrarDatos();">
													<span class="white">&times;</span>
												</button>
												Editar Reserva
											</div>
										</div>

										<div class="modal-body">
											<form class="form-horizontal" role="form">
												<br>
												<div align="center" class="page-header">
													<h1>
														<strong>¿Qué desea cambiar?</strong>
													</h1>
												</div>
												<div align="center">
													<a onclick="botonCambiarMenu();"><input class="btn"
														value="Cambiar Menú" type="button"></a> <a
														onclick="botonCambiarHorario();"><input class="btn"
														value="Cambiar Horario" type="button"></a> <a
														onclick="botonCambiarAmbos();"><input class="btn"
														value="Ambos" type="button"></a>
												</div>

											</form>
										</div>

										<div class="modal-footer no-margin-top">
											<button class="btn btn-sm btn-danger pull-left"
												data-dismiss="modal">
												<i class="ace-icon fa fa-times"></i> Cerrar
											</button>


										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
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
<script>
	function datos(idReserva, idMenu, idHorario, fecha) {
		console.log(idReserva);
		localStorage.setItem('idReserva', idReserva);
		localStorage.setItem('idMenu', idMenu);
		localStorage.setItem('idHorario', idHorario);
		localStorage.setItem('fecha', fecha);
	}

	function botonCambiarMenu() {
		var idMenu = localStorage.getItem('idMenu');
		var idHorario = localStorage.getItem('idHorario');
		var fecha = localStorage.getItem('fecha');
		var idReserva = localStorage.getItem('idReserva');
		console.log(idReserva);

		$.ajax({
			type : 'GET',
			url : "cambiarMenu.htm",
			data : {
				idMenu : idMenu,
				idHorario : idHorario,
				fecha : fecha
			},
			dataType : 'json',
			success : function(data) {
				console.log(data);
				window.location = "obtenerMenu.htm?dateSelected=" + fecha
						+ "&idMenu=" + idMenu+"&idReserva="+idReserva;
			},
			error : function(jqXHR, errorThrown) {
				alert("Error");
			}
		});
	}
</script>

</html>
