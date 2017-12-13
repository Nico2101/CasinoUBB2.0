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


							<c:if test="${not empty seleccion}">
								<script>
									toastr
											.error("Debe seleccionar todos los datos requeridos");
								</script>
							</c:if>


							<div id="formulario" align="center">
								<form class="form-horizontal" role="form"
									action="MostrarInformeAlmuerzos.htm" method="post">

									<div align="center" class="page-header">
										<h1>
											<strong>Generar Informe Total Almuerzos Vendidos</strong>
										</h1>
									</div>
									<div class="form-group">

										<br> <label
											class="col-sm-3 control-label no-padding-right"
											for="form-field-1"> Mes: </label>

										<div class="col-sm-9">


											<select name="mes" class="col-xs-10 col-sm-5"
												style="width: 200px">
												<option value="-1">---Seleccione un mes---</option>
												<option value="1">Enero</option>
												<option value="2">Febrero</option>
												<option value="3">Marzo</option>
												<option value="4">Abril</option>
												<option value="5">Mayo</option>
												<option value="6">Junio</option>
												<option value="7">Julio</option>
												<option value="8">Agosto</option>
												<option value="9">Septiembre</option>
												<option value="10">Octubre</option>
												<option value="11">Noviembre</option>
												<option value="12">Diciembre</option>

											</select> <label class="col-sm-3 control-label no-padding-right"
												for="form-field-1"> Año: </label> <select name="year"
												style="width: 200px" class="col-xs-10 col-sm-5">
												<option value="-1">---Seleccione un año---</option>
												<option value="2014">2014</option>
												<option value="2015">2015</option>
												<option value="2016">2016</option>
												<option value="2017">2017</option>

											</select>
										</div>
									</div>
									<input class="btn" value="Avanzar" type="submit">
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
