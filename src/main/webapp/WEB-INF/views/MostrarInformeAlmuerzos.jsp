<%@ include file="/WEB-INF/views/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>

<%@ include file="cabecera.jsp"%>
<!-- HTML meta refresh URL redirection -->

<style type="text/css">
#formulario {
	text-align: center
}
</style>
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


							<div id="formulario" align="center">
								<form class="form-horizontal" role="form"
									action="generarInforme.htm" method="post">

									<div align="center" class="page-header">
										<h1>
											<strong>Almuerzos Vendidos en el mes seleccionado</strong>
										</h1>
									</div>
									<div align="center">
										<table class="table table-condensed" style="width: 600px">
											<c:if test="${not empty listaMenuVendidos }">
												<thead>
													<tr>

														<th>Nombre</th>
														<th>Precio</th>
														<th>Tipo</th>
														<th>Total Vendidos</th>

													</tr>
												</thead>



												<c:forEach var="menu" items="${listaMenuVendidos}">
													<tr>
														<td><c:out value="${menu.nombre}"></c:out></td>
														<td><c:out value="${menu.precio}"></c:out></td>
														<td><c:out value="${menu.tipo}"></c:out></td>
														<td><c:out value="${menu.cantRaciones}"></c:out></td>

													</tr>
												</c:forEach>
												<tr>
													<th>Total</th>
													<th></th>
													<th></th>
													<th><c:out value="${total}"></c:out></th>
												</tr>
												<br>
												<input class="btn pull-right" value="Generar Informe" type="submit">
											</c:if>
											<c:if test="${empty listaMenuVendidos }">

												<h4>No hay Menus Vendidos en el mes seleccionado</h4>
											</c:if>

										</table>
									</div>

								</form>
							</div>


						</div>



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

	<!-- /.main-content -->

	<div class="footer">
		<%@ include file="pieDePagina.jsp"%>
	</div>

	<a href="#" id="btn-scroll-up"
		class="btn-scroll-up btn btn-sm btn-inverse"> <i
		class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
	</a>

	<!-- /.main-container -->



	<%@ include file="scripts.jsp"%>
</body>


</html>
