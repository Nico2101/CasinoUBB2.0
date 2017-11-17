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

							<c:if test="${not empty reservado}">
								<script type="text/javascript">
									toastr
											.success("Menú reservado correctamente en el horario indicado!");
								</script>
							</c:if>

							<c:if test="${not empty ReservaEliminada}">
								<script type="text/javascript">
									toastr
											.success("Reserva Eliminada Correctamente!");
								</script>
							</c:if>
							
							<c:if test="${not empty NoTieneMasReservas}">
								<script type="text/javascript">
									toastr
											.warning("No tiene mas reservas");
								</script>
							</c:if>

							<c:if test="${not empty evaluacionAgregada}">
								<script type="text/javascript">
									toastr
											.success("Evaluación agregada correctamente!");
								</script>
							</c:if>

							<c:if test="${not empty noQuedanMenusParaEvaluar}">
								<script type="text/javascript">
									toastr
											.warning("No quedan menus para evaluar");
								</script>
							</c:if>



							<c:if test="${not empty NoHayMenuParaEvaluar}">
								<script type="text/javascript">
									toastr
											.error("No tiene menús disponibles para evaluar");
								</script>
							</c:if>

							<c:if test="${not empty noreservado}">
								<script type="text/javascript">
									toastr.error("Error al reservar menú");
								</script>
							</c:if>

							<c:if test="${not empty NoTieneReservas}">
								<script type="text/javascript">
									toastr.error("No tiene reservas");
								</script>
							</c:if>
                            
                            
                            
                            <form class="form-horizontal" role="form"
									action="formActualizarEva.htm" method="get">


									<c:if test="${not empty noUpdated }">
										<script type="text/javascript">
											toastr
													.error("Error al actualizar el menú");
										</script>
									</c:if>


									<div align="center" class="page-header">
										<h1>
											<strong>Editar Evaluación</strong>
										</h1>
									</div>

									<br> <br>
									<div class="form-group" style="width: 1850px">
										<input type="hidden" name="id" value="${editEva.id }" /> 
										<label
											class="col-sm-3 control-label no-padding-right"
											for="form-field-1"> Comentario </label>

										<div class="col-sm-9">
											<input name="Comentario" type="text" id="form-field-1"
												placeholder="" style="width: 300px"
												class="col-xs-10 col-sm-5" value="${editEva.comentario}"
												required>
										</div>
									    <label class="col-sm-5 control-label no-padding-right">
											Valoración </label>

										<div class="col-sm-5">
											<div class="rating inline"></div>

										</div>
										
									</div>
									<br> <br> <input class="btn" value="Guardar"
										type="submit">


								</form>
                            
                            
                            
                            
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
