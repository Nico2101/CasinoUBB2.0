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
									<strong>Menú</strong>
								</h1>
							</div>

							<c:if test="${not empty NoHayAlmuerzos}">
								<script>
									toastr.warning("No quedan almuerzos");
								</script>
							</c:if>
							
							<c:if test="${not empty nohorario}">
								<script>
									toastr
											.warning("No hay horarios disponibles");
								</script>
							</c:if>

							<div align="center">
								<table id="example" class="table table-condensed" >
									<thead>
										<tr>
											<th>ID</th>
											<th>Nombre</th>
											<th>Tipo</th>
											<th>Precio</th>
											<th>Raciones Restantes</th>
											<th>Fecha</th>
											<th>Horario Disponible</th>

										</tr>
									</thead>

									<c:forEach var="menu" items="${listaMenu}">
										<tr>
											<td><c:out value="${menu.id}"></c:out></td>
											<td><c:out value="${menu.nombre}"></c:out></td>
											<td><c:out value="${menu.tipo}"></c:out></td>
											<td><c:out value="${menu.precio}"></c:out></td>
											<td><c:out value="${menu.cantRaciones}"></c:out></td>
											<td><c:out value="${menu.fecha}"></c:out></td>
											<td><a
												href="verHorarioDisponible.htm?id=${menu.id}&menu=${menu.nombre}&cantRaciones=${menu.cantRaciones}&tipo=${menu.tipo}&precio=${menu.precio}&fecha=${menu.fecha}"><input
													class="btn btn-primary btn-sm" type="button"
													value="Ver Horario Disponible" /></a></td>
										</tr>
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
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript"
	src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js">
	
</script>
<script
	src="https://cdn.datatables.net/1.10.16/js/dataTables.bootstrap.min.js"></script>

<script>
	$(function() {
		$('#example')
				.DataTable(
						{
							'paging' : true,
							'lengthChange' : true,
							'searching' : true,
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
</body>


</html>
