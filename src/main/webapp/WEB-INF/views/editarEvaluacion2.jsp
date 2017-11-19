<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<meta charset="utf-8" />
<%@ include file="cabecera.jsp"%>
</head>

<body class="no-skin" onload="rate('${editEva.valoracion}');">
	<div id="navbar" class="navbar navbar-default          ace-save-state">
		<%@ include file="barraSuperior.jsp"%>
	</div>

	<div class="main-container ace-save-state" id="main-container">
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
			<div class="main-content-inner">
				<div class="breadcrumbs ace-save-state" id="breadcrumbs"></div>

				<div class="page-content">





					<!-- /.page-header -->

					<div class="row">
						<div class="col-xs-12">
							<!-- PAGE CONTENT BEGINS -->



							<div align="center" class="page-header">
								<h1>
									<strong>Evaluar Servicio</strong>
								</h1>
							</div>
							<br>

							<form class="form-horizontal" role="form" method="get"
								action="actualizarEvaluacion.htm">
								<div align="center">
									<div class="form-group" style="width: 450px">
									    <input type="hidden" name="id" value="${editEva.id}">
										<label class="col-sm-5 control-label no-padding-right">
											Valoración </label>

										<div class="col-sm-5">
											<div class="rating inline"></div>

										</div>

										<br> <br> <label
											class="col-sm-5 control-label no-padding-right"
											for="form-field-1"> Comentario </label>

										<div class="col-sm-5">
											<input name="comentario" type="text" id="form-field-1"
												placeholder="" style="width: 200px"
												class="col-xs-10 col-sm-5" value="${editEva.comentario}" required>
										</div>
										<br> <br> <br> <input class="btn"
											value="Guardar" type="submit">
									</div>

								</div>
							</form>



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

	<!-- basic scripts -->

	<!--[if !IE]> -->
	<script src="assets/js/jquery-2.1.4.min.js"></script>

	<!-- <![endif]-->

	<!--[if IE]>
<script src="assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
	<script type="text/javascript">
		if ('ontouchstart' in document.documentElement)
			document
					.write("<script src='assets/js/jquery.mobile.custom.min.js'>"
							+ "<"+"/script>");
	</script>
	<script src="assets/js/bootstrap.min.js"></script>

	<!-- page specific plugin scripts -->
	<script src="assets/js/jquery.bootstrap-duallistbox.min.js"></script>
	<script src="assets/js/jquery.raty.min.js"></script>
	<script src="assets/js/bootstrap-multiselect.min.js"></script>
	<script src="assets/js/select2.min.js"></script>
	<script src="assets/js/jquery-typeahead.js"></script>

	<!-- ace scripts -->
	<script src="assets/js/ace-elements.min.js"></script>
	<script src="assets/js/ace.min.js"></script>

	<!-- inline scripts related to this page -->
	<script type="text/javascript">
		jQuery(function($) {
			var demo1 = $('select[name="duallistbox_demo1[]"]')
					.bootstrapDualListbox(
							{
								infoTextFiltered : '<span class="label label-purple label-lg">Filtered</span>'
							});
			var container1 = demo1.bootstrapDualListbox('getContainer');
			container1.find('.btn').addClass('btn-white btn-info btn-bold');

			/**var setRatingColors = function() {
				$(this).find('.star-on-png,.star-half-png').addClass('orange2').removeClass('grey');
				$(this).find('.star-off-png').removeClass('orange2').addClass('grey');
			}*/
			$('.rating').raty({
				'cancel' : true,
				'half' : true,
				'starType' : 'i'
			/**,
			
			'click': function() {
				setRatingColors.call(this);
			},
			'mouseover': function() {
				setRatingColors.call(this);
			},
			'mouseout': function() {
				setRatingColors.call(this);
			}*/
			})//.find('i:not(.star-raty)').addClass('grey');

			//////////////////
			//select2
			$('.select2').css('width', '200px').select2({
				allowClear : true
			})
			$('#select2-multiple-style .btn').on('click', function(e) {
				var target = $(this).find('input[type=radio]');
				var which = parseInt(target.val());
				if (which == 2)
					$('.select2').addClass('tag-input-style');
				else
					$('.select2').removeClass('tag-input-style');
			});

			//////////////////
			$('.multiselect')
					.multiselect(
							{
								enableFiltering : true,
								enableHTML : true,
								buttonClass : 'btn btn-white btn-primary',
								templates : {
									button : '<button type="button" class="multiselect dropdown-toggle" data-toggle="dropdown"><span class="multiselect-selected-text"></span> &nbsp;<b class="fa fa-caret-down"></b></button>',
									ul : '<ul class="multiselect-container dropdown-menu"></ul>',
									filter : '<li class="multiselect-item filter"><div class="input-group"><span class="input-group-addon"><i class="fa fa-search"></i></span><input class="form-control multiselect-search" type="text"></div></li>',
									filterClearBtn : '<span class="input-group-btn"><button class="btn btn-default btn-white btn-grey multiselect-clear-filter" type="button"><i class="fa fa-times-circle red2"></i></button></span>',
									li : '<li><a tabindex="0"><label></label></a></li>',
									divider : '<li class="multiselect-item divider"></li>',
									liGroup : '<li class="multiselect-item multiselect-group"><label></label></li>'
								}
							});

			///////////////////

			//typeahead.js
			//example taken from plugin's page at: https://twitter.github.io/typeahead.js/examples/
			var substringMatcher = function(strs) {
				return function findMatches(q, cb) {
					var matches, substringRegex;

					// an array that will be populated with substring matches
					matches = [];

					// regex used to determine if a string contains the substring `q`
					substrRegex = new RegExp(q, 'i');

					// iterate through the pool of strings and for any string that
					// contains the substring `q`, add it to the `matches` array
					$.each(strs, function(i, str) {
						if (substrRegex.test(str)) {
							// the typeahead jQuery plugin expects suggestions to a
							// JavaScript object, refer to typeahead docs for more info
							matches.push({
								value : str
							});
						}
					});

					cb(matches);
				}
			}

			$('input.typeahead').typeahead({
				hint : true,
				highlight : true,
				minLength : 1
			}, {
				name : 'states',
				displayKey : 'value',
				source : substringMatcher(ace.vars['US_STATES']),
				limit : 10
			});

			///////////////

			//in ajax mode, remove remaining elements before leaving page
			$(document).one(
					'ajaxloadstart.page',
					function(e) {
						$('[class*=select2]').remove();
						$('select[name="duallistbox_demo1[]"]')
								.bootstrapDualListbox('destroy');
						$('.rating').raty('destroy');
						$('.multiselect').multiselect('destroy');
					});

		});
	</script>
	<script>
	function rate(valoracion){
		console.log(valoracion);
		$('.rating').raty({
			'cancel' : true,
			'half' : true,
			'starType' : 'i',
			'score':valoracion,
			'halfShow':    true
		/**,
		
		'click': function() {
			setRatingColors.call(this);
		},
		'mouseover': function() {
			setRatingColors.call(this);
		},
		'mouseout': function() {
			setRatingColors.call(this);
		}*/
		})//.find('i:not(.star-raty)').addClass('grey');
	}
	</script>
</body>
</html>
