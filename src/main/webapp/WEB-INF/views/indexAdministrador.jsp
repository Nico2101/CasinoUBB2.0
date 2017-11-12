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

							<c:if test="${not empty actualizado }">
								<script type="text/javascript">
									toastr
											.success("Menú actualizado correctamente!");
								</script>
							</c:if>

							<c:if test="${not empty NoHayValoraciones }">
								<script type="text/javascript">
									toastr
											.warning("No hay evaluaciones de usuarios");
								</script>
							</c:if>

							<c:if test="${not empty CuposActualizados }">
								<script type="text/javascript">
									toastr
											.success("Cupos actualizados correctamente!");
								</script>
							</c:if>

							<c:if test="${not empty CuposNoActualizados}">
								<script type="text/javascript">
									toastr
											.error("Error al actualizar los Cupos");
								</script>
							</c:if>


							<!-- /.row -->
							<!-- PAGE CONTENT ENDS -->

							<div id="modal-table" class="modal fade" tabindex="-1">
								<div class="modal-dialog">
									<div class="modal-content">
										<div class="modal-header no-padding">
											<div class="table-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true" onclick="borrarDatos();">
													<span class="white">&times;</span>
												</button>
												Aumentar Saldo Usuario
											</div>
										</div>

										<div class="modal-body">
											<form class="form-horizontal" role="form">
												<br>
												<div class="form-group" style="width: 850px">

													<label class="col-sm-3 control-label no-padding-right"
														for="form-field-1"> Rut Usuario </label>

													<div class="col-sm-9">
														<input id="rutUsuario" name="rutUsuario" type="text"
															id="form-field-1" placeholder="" style="width: 300px"
															class="col-xs-10 col-sm-5" required
															oninput="checkRut(this)">
													</div>

													<br> <br> <label
														class="col-sm-3 control-label no-padding-right"
														for="form-field-1"> Saldo </label>

													<div class="col-sm-9">
														<input id="saldo" name="saldo" type="number"
															id="form-field-1" placeholder="" style="width: 300px"
															class="col-xs-10 col-sm-5" required>
													</div>
												</div>


												<div align="center">
													<input class="btn" value="agregar" type="button"
														onclick="aumentarSaldo();">
												</div>

											</form>
										</div>

										<div class="modal-footer no-margin-top">
											<button class="btn btn-sm btn-danger pull-left"
												data-dismiss="modal" onclick="borrarDatos();">
												<i class="ace-icon fa fa-times"></i> Cerrar
											</button>


										</div>
									</div>
									<!-- /.modal-content -->
								</div>
								<!-- /.modal-dialog -->
							</div>
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
	function borrarDatos() {
		$('#rutUsuario').val("");
		$('#saldo').val("");
	}

	function aumentarSaldo() {

		var rut = $('#rutUsuario').val();
		var saldo = $('#saldo').val();

		$
				.ajax({
					type : 'GET',
					url : "agregarSaldo.htm",
					data : {
						rutUsuario : rut,
						saldo : saldo
					},
					dataType : 'json',
					success : function(data) {
						if (data.rut == null) {
							toastr
									.error("El rut indicado no se encuentra en la base de datos");
						} else {
							$('#modal-table').modal('hide');
							toastr.success("Se ha cargado el monto de " + saldo
									+ " pesos a la cuenta");
							$('#rutUsuario').val("");
							$('#saldo').val("");
						}
					},
					error : function(jqXHR, errorThrown) {
						alert("Error");
					}
				});

	}
</script>
<script>
	function checkRut(rut) {
		// Despejar Puntos
		var valor = rut.value.replace('.', '');
		// Despejar Guión
		valor = valor.replace('-', '');

		// Aislar Cuerpo y Dígito Verificador
		cuerpo = valor.slice(0, -1);
		dv = valor.slice(-1).toUpperCase();

		// Formatear RUN
		rut.value = cuerpo + '-' + dv

		// Si no cumple con el mínimo ej. (n.nnn.nnn)
		if (cuerpo.length < 7) {
			rut.setCustomValidity("RUT Incompleto");
			return false;
		}

		// Calcular Dígito Verificador
		suma = 0;
		multiplo = 2;

		// Para cada dígito del Cuerpo
		for (i = 1; i <= cuerpo.length; i++) {

			// Obtener su Producto con el Múltiplo Correspondiente
			index = multiplo * valor.charAt(cuerpo.length - i);

			// Sumar al Contador General
			suma = suma + index;

			// Consolidar Múltiplo dentro del rango [2,7]
			if (multiplo < 7) {
				multiplo = multiplo + 1;
			} else {
				multiplo = 2;
			}

		}

		// Calcular Dígito Verificador en base al Módulo 11
		dvEsperado = 11 - (suma % 11);

		// Casos Especiales (0 y K)
		dv = (dv == 'K') ? 10 : dv;
		dv = (dv == 0) ? 11 : dv;

		// Validar que el Cuerpo coincide con su Dígito Verificador
		if (dvEsperado != dv) {
			rut.setCustomValidity("RUT Inválido");
			return false;
		}

		// Si todo sale bien, eliminar errores (decretar que es válido)
		rut.setCustomValidity('');
	}
</script>

</html>
