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
			<%@ include file="barraLateralAdministrador.jsp"%>

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


							<br>


							<div align="center" class="page-header">
								<h1>
									<strong>Total Reservas </strong>
								</h1>
							</div>
							<br>

							<div align="center">

								<table id="totalReservasMes" class="table table-condensed">
									<c:if test="${not empty totalReservas }">
										<thead>
											<tr>

												<th>RUT</th>
												<th>NOMBRE</th>
												<th>CANTIDAD</th>

											</tr>
										</thead>

										<c:forEach var="reserva" items="${totalReservas}">
											<tr>


												<td><c:out value="${reserva.rut}"></c:out></td>
												<td><c:out value="${reserva.nombre}"></c:out></td>
												<td><c:out value="${reserva.id}"></c:out></td>


											</tr>
										</c:forEach>
									</c:if>
									<c:if test="${empty totalReservas }">
										<h4>No hay Datos en el mes seleccionado</h4>

									</c:if>
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
		$('#totalReservasMes')
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
								"sSearch" : "Buscar :",
								"sLengthMenu" : "Mostrar _MENU_ registros",
								"sZeroRecords" : "No se encontraron resultados",
								"sEmptyTable" : "Ningún dato disponible en esta tabla",
								"sInfo" : "Mostrando del _START_ al _END_ de _TOTAL_",
								"sInfoEmpty" : "Mostrando del 0 al 0 de 0",
								"sInfoFiltered" : "(filtrado de un total de _MAX_ registros)",
								"sInfoPostFix" : "",
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
<script>
	
</script>
</html>
