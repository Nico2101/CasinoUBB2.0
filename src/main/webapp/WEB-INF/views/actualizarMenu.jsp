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




							<div id="formulario">
								<form class="form-horizontal" role="form"
									action="formActualizarMenu.htm" method="get">


									<c:if test="${not empty noUpdated }">
										<script type="text/javascript">
											toastr
													.error("Error al actualizar el menú");
										</script>
									</c:if>

									<c:if test="${not empty fechaAnterior }">
										<script type="text/javascript">
											toastr
													.error("Error, la fecha seleccionada es anterior a la actual");
										</script>
									</c:if>

									<div align="center" class="page-header">
										<h1>
											<strong>Editar Menú</strong>
										</h1>
									</div>

									<br> <br>
									<div class="form-group" style="width: 1850px">
										<input type="hidden" name="id" value="${editMenu.id }" /> <label
											class="col-sm-3 control-label no-padding-right"
											for="form-field-1"> Nombre </label>

										<div class="col-sm-9">
											<input name="nombreMenu" type="text" id="form-field-1"
												placeholder="" style="width: 300px"
												class="col-xs-10 col-sm-5" value="${editMenu.nombre}"
												required>
										</div>

										<br> <br> <label
											class="col-sm-3 control-label no-padding-right"
											for="form-field-1"> Tipo </label>

										<c:if test="${editMenu.tipo=='Extra' }">
											<div class="col-sm-9">
												<select name="tipoMenu" class="col-xs-10 col-sm-5"
													style="width: 300px">
													<option>Normal</option>
													<option selected>Extra</option>
												</select>

											</div>
										</c:if>

										<c:if test="${editMenu.tipo=='Normal' }">
											<div class="col-sm-9">
												<select name="tipoMenu" class="col-xs-10 col-sm-5"
													style="width: 300px">
													<option selected>Normal</option>
													<option>Extra</option>
												</select>

											</div>
										</c:if>
										<br> <br> <label
											class="col-sm-3 control-label no-padding-right"
											for="form-field-1"> Precio </label>

										<div class="col-sm-9">
											<input name="precioMenu" type="number" id="form-field-1"
												placeholder="" style="width: 300px"
												class="col-xs-10 col-sm-5" value="${editMenu.precio }"
												required>
										</div>

										<br> <br> <label
											class="col-sm-3 control-label no-padding-right"
											for="form-field-1"> Cantidad de Raciones </label>

										<div class="col-sm-9">
											<input name="cantidadRaciones" type="number"
												id="form-field-1" placeholder="" style="width: 300px"
												class="col-xs-10 col-sm-5" required
												value="${editMenu.cantRaciones }">
										</div>

										<br> <br> <label
											class="col-sm-3 control-label no-padding-right"
											for="form-field-1"> Fecha Menú</label>

										<div class="col-sm-9">
											<input name="dateSelected" type="date" id="form-field-1"
												placeholder="" style="width: 300px"
												class="col-xs-10 col-sm-5" value="${editMenu.fecha }"
												required>
										</div>
									</div>
									<br> <br> <input class="btn" value="Guardar"
										type="submit">


								</form>


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