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
<%@page import="java.util.LinkedList"%>
<%@page import="com.proyecto.transferObject.MenuTO"%>
<%@page import="com.proyecto.transferObject.HorarioTO"%>
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

							<c:if test="${not empty evaluacionAgregada}">
								<script>
									toastr
											.success("Evaluacion agregada correctamente!");
								</script>
							</c:if>

							<c:if test="${not empty errorAlValorar}">
								<script>
									toastr
											.error("Se ha producido un error al evaluar el menu");
								</script>
							</c:if>


							<div align="center" class="page-header">
								<h1>
									<strong>Ticket Almuerzo</strong>
								</h1>
							</div>


							<div>
								<table id="tablaMenusComprados" class="table table-condensed">
									<thead>
										<tr>

											<th>Nombre</th>
											<th>Tipo</th>
											<th>Precio</th>
											<th>Fecha</th>
											<th>Cantidad</th>
											<th>Hora Inicio</th>
											<th>Hora Fin</th>
											<th>Tikcet</th>



										</tr>
									</thead>


									<%
										LinkedList<MenuTO> list = (LinkedList<MenuTO>) request.getAttribute("menu");
										LinkedList<HorarioTO> list2 = (LinkedList<HorarioTO>) request.getAttribute("datosHorario");

										if (list != null && list2 != null)
											for (int i = 0; i < list.size(); i++) {
												MenuTO menu = list.get(i);
												HorarioTO horario = list2.get(i);
									%>

									<tr>


										<td><%=menu.getNombre()%></td>
										<td><%=menu.getTipo()%></td>
										<td><%=menu.getPrecio()%></td>
										<td><%=menu.getFecha()%></td>
										<td><%=horario.getCantMaxRaciones()%></td>
										<td><%=horario.getHoraInicio()%></td>
										<td><%=horario.getHoraFin()%></td>
										<td><a
											href="getTicket.htm?menu=<%=menu.getNombre()%>&tipo=<%=menu.getTipo()%>&precio=<%=menu.getPrecio()%>&fecha=<%=menu.getFecha()%>&cantRaciones=<%=horario.getCantMaxRaciones()%>&horaI=<%=horario.getHoraInicio()%>&horaF=<%=horario.getHoraFin()%>"><input
												class="btn btn-primary btn-sm" type="button"
												value="Generar Ticket" /></a></td>


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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js">
	
</script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>

<script>
	$(function() {
		$('#tablaMenusComprados')
				.DataTable(
						{
							'paging' : true,
							'lengthChange' : true,
							'searching' : false,
							'ordering' : true,
							'info' : true,
							'autoWidth' : true,
							'responsive' : true,
							"lengthMenu" : [ [ 5, 10, 20, -1 ],
									[ 5, 10, 20, "Todos" ] ],
							"language" : {
								"sProcessing" : "Procesando...",
								"sLengthMenu" : "Mostrar _MENU_ registros",
								"sZeroRecords" : "No se encontraron resultados",
								"sEmptyTable" : "Ningún dato disponible en esta tabla",
								"sInfo" : "Mostrando del _START_ al _END_",
								"sInfoEmpty" : "Mostrando del 0 al 0 de 0",
								"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
								"sInfoPostFix" : "",
								"sSearch" : "Buscar:",
								"sUrl" : "",
								"sInfoThousands" : ",",
								"sLoadingRecords" : "Cargando...",
								"oPaginate" : {
									"sFirst" : "Primero",
									"sLast" : "Último",
									"sNext" : "Siguiente",
									"sPrevious" : "Anterior"
								},
								"oAria" : {
									"sSortAscending" : ": Activar para ordenar la columna de manera ascendente",
									"sSortDescending" : ": Activar para ordenar la columna de manera descendente"
								}
							}
						})
	})
</script>

</html>
