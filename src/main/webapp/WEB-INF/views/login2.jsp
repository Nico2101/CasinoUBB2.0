<%-- 
    Document   : login
    Created on : Oct 13, 2017, 1:31:14 PM
    Author     : Juanita
--%>
<%@ include file="/WEB-INF/views/include.jsp"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
<head>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<link href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/css/toastr.css" rel="stylesheet"/>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/2.0.1/js/toastr.js"></script>
<title>Login CasinoUBB</title>

<meta name="description" content="User login page" />
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

<!-- bootstrap & fontawesome -->
<link rel="stylesheet" href="assets/css/bootstrap.min.css" />
<link rel="stylesheet"
	href="assets/font-awesome/4.5.0/css/font-awesome.min.css" />

<!-- text fonts -->
<link rel="stylesheet" href="assets/css/fonts.googleapis.com.css" />

<!-- ace styles -->
<link rel="stylesheet" href="assets/css/ace.min.css" />

<!--[if lte IE 9]>
                <link rel="stylesheet" href="assets/css/ace-part2.min.css" />
        <![endif]-->
<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />

<!--[if lte IE 9]>
          <link rel="stylesheet" href="assets/css/ace-ie.min.css" />
        <![endif]-->

<!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

<!--[if lte IE 8]>
        <script src="assets/js/html5shiv.min.js"></script>
        <script src="assets/js/respond.min.js"></script>
        <![endif]-->
</head>

<body class="login-layout">
	<div class="main-container">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-10 col-sm-offset-1">
					<div class="login-container">
						<div class="center">
							<h1>

								<i class="ace-icon fa fa-cutlery white"></i> <span class="white"
									id="id-text2">Casino UBB</span>
							</h1>

							<c:if test="${not empty registrado}">
								<script>
									toastr.success("Registrado correctamente!");
								</script>
							</c:if>

							<c:if test="${not empty sinregistro}">
								<script>
									toastr.error("Error en el registro");
								</script>
							</c:if>
							
							
							<c:if test="${not empty clavesDistintas}">
								<script>
									toastr.error("Las contraseñas ingresadas son distintas");
								</script>
							</c:if>
							
							

							<div class="">
								
								<div id="signup-box" class="signup-box visible widget-box no-border">
									<div class="widget-body">
										<div class="widget-main">
											<h4 class="header green lighter bigger">
												<i class="ace-icon fa fa-users blue"></i> Registro de nuevo
												usuario
											</h4>



											<form action="registroUsuario.htm" method="post">
												<fieldset>
													<label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="text" class="form-control" required
															oninput="checkRut(this)" placeholder="Rut" name="rut" value="${rut }" />
															<i class="ace-icon fa fa-male"></i>

													</span>
													</label> <label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="text" class="form-control" placeholder="Nombre"
															name="nombre" required value="${nombre }"  /> <i class="ace-icon fa fa-user"></i>
													</span>
													</label> <label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="text" class="form-control"
															placeholder="Apellido Paterno" name="appat" required value="${apellidoP}"  />
															<i class="ace-icon fa fa-user"></i>
													</span>
													</label> <label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="text" class="form-control"
															placeholder="Apellido Materno" name="apmat" required value="${apellidoM }"  />
															<i class="ace-icon fa fa-user"></i>
													</span>
													</label> <label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="password" class="form-control"
															placeholder="Contraseña" name="clave" required
															pattern=".{8}"
															title="La contraseña debe tener 8 caracteres" /> <i
															class="ace-icon fa fa-lock"></i>
													</span>
													</label> <label class="block clearfix"> <span
														class="block input-icon input-icon-right"> <input
															type="password" class="form-control"
															placeholder="Repetir  contraseña" name="clave2" required
															pattern=".{8}"
															title="La contraseña debe tener 8 caracteres" /> <i
															class="ace-icon fa fa-retweet"></i>
													</span>
													</label>

													<button type="Submit"
														class="width-65 pull-right btn btn-sm btn-success">
														<span class="bigger-110">Registrarme</span>

													</button>
												</fieldset>
											</form>
										</div>

									</div>

									<div class="toolbar center">
										<a href="login.htm" 
											class="back-to-login-link"> <i
											class="ace-icon fa fa-arrow-left"></i> Volver a login
										</a>

									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="assets/js/jquery-2.1.4.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
                                <script src="assets/js/jquery-1.11.3.min.js"></script>
                                <![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<" + "/script>");
	</script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			$(document).on('click', '.toolbar a[data-target]', function(e) {
				e.preventDefault();
				var target = $(this).data('target');
				$('.widget-box.visible').removeClass('visible');//hide others
				$(target).addClass('visible');//show target
			});
		});
	</script>
</body>
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

