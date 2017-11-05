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

										if (list != null && list2!=null)
											for (int i = 0; i < list.size(); i++) {
												MenuTO menu = list.get(i);
												HorarioTO horario = list2.get(i);
									%>
									<tr>
										<td><%=menu.getNombre()%></td>
										<td><%=menu.getTipo()%></td>
										<td><%=menu.getPrecio()%></td>
										<td><%=menu.getFecha()%></td>
										<td><%=horario.getHoraInicio()%></td>
										<td><%=horario.getHoraFin()%></td>
										<td><a class="blue" href="#"> <i
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
