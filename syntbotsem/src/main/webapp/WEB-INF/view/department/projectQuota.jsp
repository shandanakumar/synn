<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-us">
<head>
<meta charset="utf-8">
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

<title>SyntBots Admin:Project Groups Quota</title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<!-- Basic Styles -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/font-awesome.min.css">

<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/smartadmin-production-plugins.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/smartadmin-production.min.css">
<link rel="stylesheet" type="text/css" media="screen"
	href="css/smartadmin-skins.min.css">
<!--  <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

<!-- SmartAdmin RTL Support -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/smartadmin-rtl.min.css">


<link rel="stylesheet" type="text/css" media="screen"
	href="css/demo.min.css">

<!-- FAVICONS -->
<link rel="shortcut icon" href="img/favicon/favicon.ico"
	type="image/x-icon">
<link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

<!-- GOOGLE FONT -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
</head>
<%@ include file="/SesssionFilter.jsp"%>
<body class=" ">
	<!-- #HEADER -->
	<header id="header">
		<!-- PAGE LOGO HEADER -->

		<%@ include file="/includefiles/logoheader.jsp"%>

		<!-- END PAGE LOGO HEADER -->

		<!-- pulled right: nav area -->
	<%-- 	<div class="pull-right">
			<div class="project-context hidden-xs">
				<span class="label">ISAP Admin: </span> <a href="#"
					class="dropdown-toggle" data-toggle="dropdown"> <i
					class="fa fa-user"></i> <span> Welcome
						,&nbsp;&nbsp;${userValue.usr_name}</span>
				</a>
			</div>

			<!-- collapse menu button -->
			<div id="hide-menu" class="btn-header pull-right">
				<span> <a href="javascript:void(0);" data-action="toggleMenu"
					title="Collapse Menu"><i class="fa fa-reorder"></i></a>
				</span>
			</div>
			<!-- end collapse menu -->

			<span> <a
				style="border-radius: 2px; cursor: default !important; display: inline-block; font-weight: 700; height: 30px; line-height: 24px; min-width: 30px; padding: 2px; text-align: center; text-decoration: none !important; -moz-user-select: none; background-color: #F8F8F8; background-image: -moz-linear-gradient(center top, #F8F8F8, #F1F1F1); border: 1px solid #BFBFBF; color: #6D6A69; font-size: 17px; margin: 10px 0px 0px;"
				href="./logOut.jsp" title="Sign Out" data-action="userLogout"
				data-logout-msg="You can improve your security further after logging out by closing this opened browser">
					<i class="fa fa-sign-out"></i>
			</a>
			</span>

		</div> --%>
		<!-- end pulled right: nav area -->
	</header>
	<!-- END HEADER -->



	<aside id="left-panel">
		<!-- NAVIGATION : This navigation is also responsive-->
		<nav>
			<ul>

				<li><a href="dashboardDeparment"
					title="DashBoard"><i class="fa fa-lg fa-fw fa-home"></i> <span
						class="menu-item-parent">DashBoard</span></a></li>



				<li><a href="projects"><i class="fa fa-stack-overflow"></i>
						<span class="menu-item-parent">Projects</span></a></li>

				<li><a href="templateList" title="templateList"><i
						class="fa  fa-group"></i> <span class="menu-item-parent">Template
							List</span></a></li>
				<!-- 	<li >
						<a href="#"><i class="fa fa-lg fa-fw fa-filter"></i> <span class="menu-item-parent">Map Users</span></a>
					</li> -->
				<!-- <li>
						<a href="#"><i class="fa fa-lg fa-fw fa-calendar"></i> <span class="menu-item-parent">Service Catalog creation</span></a>
					</li> 
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-filter"></i> <span class="menu-item-parent">Stack List</span></a>
					</li> -->

				<!-- <li>
						<a href="#"><i class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent">Manage Services</span></a>
					</li>  -->

				<!-- <li>
						<a href="#"><i class="fa fa-stack-overflow"></i> <span class="menu-item-parent">Reports</span></a>
					</li> -->
				<!-- 	
					<li>
					<a href="#"><i class="fa fa-fw fa-medkit"></i> <span class="menu-item-parent"> Monitor</span></a>			
					</li> -->
				<li class="active"><a href="projectQuota"><i
						class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent">
							Quota</span></a></li>

				<!-- 	
					<li>
						<a href="#" ><i class="fa fa-lg fa-fw fa-retweet"></i> <span class="menu-item-parent">Migration</span></a>
					
					    <ul>
						   <li>
								<a href="#">Discover</a>
							</li>
							<li class="active"> 
								<a href="#">MigrationPlans</a>
							</li>
							<li>
								<a href="#">RegisterEnv</a>
							</li>
						</ul>
					
					
					</li> -->

                     <li>
						<a href="departmentUserList"><i class="fa fa-user"></i> <span class="menu-item-parent">Department Users</span></a>
					</li>


			</ul>
		</nav>
		<span class="minifyme" data-action="minifyMenu"> <i
			class="fa fa-arrow-circle-left hit"></i>
		</span>

	</aside>
	<!-- END NAVIGATION -->

	<!-- MAIN PANEL -->
	<div id="main" role="main">

		<!-- RIBBON -->
		<div id="ribbon">
			<span class="ribbon-button-alignment">  	
					
			</span>
			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<li>Home</li>
				<li>Project Group Quota</li>
			</ol>
		</div>
		<!-- END RIBBON -->

		<!-- MAIN CONTENT -->
		<div id="content">



			<!-- widget grid -->
			<section id="widget-grid" class="">



				<div class="modal fade" id="myModalEdit" tabindex="-1" role="dialog">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" onclick="hideVdcDetails()"
									data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title">
									Edit Allocated VDC to <span id="dptname">Project</span>
								</h4>
							</div>
							<div class="modal-body no-padding">
								<form action="editVdcToProject"
									id="editVdcToProject" class="smart-form" method="GET">
									<input type="hidden" id=editprojId name="proj_id" value="">
									<input type="hidden" id=vdcID name="vdc_id" value="">
									<fieldset>
										<section>
											<div class="row">
												<label class="label col col-3">Selected VDC</label>
												<div class="col col-9" id="total_mem">
													<label class="input"> <!-- <i class="icon-append fa fa-user"></i> -->
														<input type="text" disabled="disabled" id="vdc_name">
													</label>
												</div>
											</div>

										</section>
										<section>
											<div class="row">
												<label class="label col col-3">Allocated Memory</label>
												<div class="col col-9" div id="total_mem">
													<label class="input"> <!-- <i class="icon-append fa fa-user"></i> -->
														<input type="text" name="total_mem" id="total_dpt_mem">
													</label>
												</div>
											</div>
											<div class="row">
												<label class="label col col-15"><span id="message1"></span>
												</label>
											</div>
										</section>
										<section>
											<div class="row">
												<label class="label col col-3">Allocated VCPU</label>
												<div class="col col-9">
													<label class="input"> <!--  <i class="icon-append fa fa-envelope-o"></i> -->
														<input type="text" name="total_vcpu" id="total_dpt_vcpu">
													</label>

												</div>
											</div>
											<div class="row">
												<label class="label col col-15"><span id="message2"></span>
												</label>
											</div>

										</section>
										<section>
											<div class="row">
												<label class="label col col-3">Allocated Disk
													Storage</label>
												<div class="col col-9">
													<label class="input"> <input class="form-control"
														name="total_disk_storage" id="total_dpt_disk_storage">
													</label>
												</div>
											</div>
											<div class="row">
												<label class="label col col-15"><span id="message3"></span>
												</label>
											</div>
										</section>
									</fieldset>

									<fieldset>
										<table id="dt_basic1"
											class="table table-striped table-bordered table-hover"
											width="100%">
											<thead>
												<tr>
													<th>Total Memory(MB)</th>
													<th>Total VCPU</th>
													<th>Total Disk Storage(GB)</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><span id="tot_mem"> </span></td>
													<td><span id="tot_vcpu"> </span></td>
													<td><span id="tot_disk_storage"> </span></td>
												</tr>
											</tbody>
										</table>
									</fieldset>

									<fieldset>
										<table id="dt_basic2"
											class="table table-striped table-bordered table-hover"
											width="100%">
											<thead>
												<tr>
													<th>Available Memory(MB)</th>
													<th>Available VCPU</th>
													<th>Available Disk Storage(GB)</th>
												</tr>
											</thead>
											<tbody>
												<tr>
													<td><span id="free_mem"></span></td>
													<td><span id="free_vcpu"></span></td>
													<td><span id="free_disk_storage"></span></td>
												</tr>
											</tbody>
										</table>
									</fieldset>


									<footer>
										<button type="submit" class="btn btn-primary">Save</button>
										<button type="button" class="btn btn-default"
											onclick="hideVdcDetails()" data-dismiss="modal">Cancel
										</button>

									</footer>
								</form>
							</div>
						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal-dialog -->
				</div>

				<!-- NEW WIDGET START -->
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<form name="projectgrp" id="projectgrp" method="GET">
						<input type="hidden" name="proj_id" id="projId"> <input
							type="hidden" name="vdc_id" id="vdc_ID">
						<!-- Widget ID (each widget will need unique ID)-->
						<div class="jarviswidget jarviswidget-color-blueLight"
							id="wid-id-0" data-widget-editbutton="false"
							data-widget-togglebutton="false" data-widget-deletebutton="false"
							data-widget-fullscreenbutton="false"
							data-widget-custombutton="true" data-widget-sortable="false">
							
							<header>
								<span class="widget-icon"> <i class="fa fa-table"></i>
								</span>
								<h2>Quota Details Of Projects</h2>

							</header>

							<!-- widget div-->
							<div>

								<!-- widget edit box -->
								<div class="jarviswidget-editbox">
									<!-- This area used as dropdown edit box -->

								</div>
								<!-- end widget edit box -->

								<!-- widget content -->
								<div class="widget-body no-padding">
									<input type="hidden" id="projId" name="projId" value="">
									<table id="dt_basic"
										class="table table-striped table-bordered table-hover"
										width="100%">
										<thead>
											<tr>

												<th data-class="expand">Project Name</th>
												<th data-hide="phone,tablet">VDC Name</th>
												<th data-hide="phone,tablet">Total Memory(MB)</th>
												<th data-hide="phone,tablet">Total VCPUs</th>
												<th data-hide="phone,tablet">Total Disk Storage</th>
												<th data-hide="phone,tablet">Free Memory(MB)</th>
												<th data-hide="phone,tablet">Free VCPUs</th>
												<th data-hide="phone,tablet">Free Disk Storage</th>
												<th data-hide="phone,tablet">Actions</th>
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${vdcprojQuotaMapBeanList}" var="pjquota">

												<tr>

													<td>${pjquota.projectbean.proj_name}</td>
													<td>${pjquota.vdcmasterbean.vdc_name}</td>
													<td>${pjquota.total_mem}</td>
													<td>${pjquota.total_vcpu}</td>
													<td>${pjquota.total_disk_storage}</td>
													<td>${pjquota.free_mem}</td>
													<td>${pjquota.free_vcpu}</td>
													<td>${pjquota.free_disk_storage}</td>
													<td style="padding-left: 3%;">
													  <a title="edit"
														data-toggle="modal" id="${pjquota.proj_id}"
														name="${pjquota.vdc_id}" onclick="getRowforEdit1(this);"
														data-toggle="modal"><i
															class="fa fa-pencil txt-color-grey"></i></a>
													  <a title="delete" id="${pjquota.proj_id}"
														name="${pjquota.vdc_id}" onclick="getQuotaByID1(this);"
														data-toggle="modal"><i class="fa fa-trash-o"></i></a> </td>
												</tr>

											</c:forEach>
										</tbody>
									</table>
								</div>
								<!-- end widget content -->

							</div>
							<!-- end widget div -->

						</div>
						<!-- end widget -->
					</form>
				</article>
				<!-- WIDGET END -->
			</section>

		</div>
	</div>

	<!-- end row -->

	<!-- end row -->


	<!-- end widget grid -->


	<!-- END MAIN CONTENT -->


	<!-- END MAIN PANEL -->
	<div id="delete_quotaDialog" title="Dialog Simple Title">
		<p>
			Are you sure you want to delete the quota for Project: "<span
				id="proj_name"> </span>" ?
		</p>
	</div>
	<div id="dialog_simple" title="Dialog Simple Title">
		<p>
			Are you sure you want to delete the Project: "<span id="projects">
			</span>" ?
		</p>
	</div>



	<!-- PAGE FOOTER -->

	<%@ include file="/includefiles/footer.jsp"%>

	<!-- END PAGE FOOTER -->
	
	<script data-pace-options='{ "restartOnRequestAfter": true }'
		src="js/plugin/pace/pace.min.js"></script>

	<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script>
		if (!window.jQuery) {
			document
					.write('<script src="js/libs/jquery-2.1.1.min.js"><\/script>');
		}
	</script>

	<script
		src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
	<script>
		if (!window.jQuery.ui) {
			document
					.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');
		}
	</script>

	<!-- IMPORTANT: APP CONFIG -->
	<script src="js/app.config.js"></script>

	<!-- JS TOUCH : include this plugin for mobile drag / drop touch events-->
	<script src="js/plugin/jquery-touch/jquery.ui.touch-punch.min.js"></script>

	<!-- BOOTSTRAP JS -->
	<script src="js/bootstrap/bootstrap.min.js"></script>

	<!-- CUSTOM NOTIFICATION -->
	<script src="js/notification/SmartNotification.min.js"></script>

	<!-- JARVIS WIDGETS -->
	<script src="js/smartwidgets/jarvis.widget.min.js"></script>

	<!-- EASY PIE CHARTS -->
	<script src="js/plugin/easy-pie-chart/jquery.easy-pie-chart.min.js"></script>

	<!-- SPARKLINES -->
	<script src="js/plugin/sparkline/jquery.sparkline.min.js"></script>

	<!-- JQUERY VALIDATE -->
	<script src="js/plugin/jquery-validate/jquery.validate.min.js"></script>

	<!-- JQUERY MASKED INPUT -->
	<script src="js/plugin/masked-input/jquery.maskedinput.min.js"></script>

	<!-- JQUERY SELECT2 INPUT -->
	<script src="js/plugin/select2/select2.min.js"></script>

	<!-- JQUERY UI + Bootstrap Slider -->
	<script src="js/plugin/bootstrap-slider/bootstrap-slider.min.js"></script>

	<!-- browser msie issue fix -->
	<script src="js/plugin/msie-fix/jquery.mb.browser.min.js"></script>

	<!-- FastClick: For mobile devices -->
	<script src="js/plugin/fastclick/fastclick.min.js"></script>

	

	<!-- MAIN APP JS FILE -->
	<script src="js/app.min.js"></script>

	<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
	<!-- Voice command : plugin -->
	<script src="js/speech/voicecommand.min.js"></script>

	<!-- SmartChat UI : plugin -->
	<script src="js/smart-chat-ui/smart.chat.ui.min.js"></script>
	<script src="js/smart-chat-ui/smart.chat.manager.min.js"></script>

	<!-- PAGE RELATED PLUGIN(S) -->
	<script src="js/plugin/datatables/jquery.dataTables.min.js"></script>
	<script src="js/plugin/datatables/dataTables.colVis.min.js"></script>
	<script src="js/plugin/datatables/dataTables.tableTools.min.js"></script>
	<script src="js/plugin/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
	<script type="text/javascript">


	function getQuotaByID(quota) {
		var projid = quota.id;
		var vdcId = quota.name;
		$('#vdc_ID').val(vdcId);
		$('#projId').val(projid);

		$.getJSON("./getProjectName/" + projid, function(response) {

			var proj_name = response.proj_name;

			$('#proj_name').text(response.proj_name);

			$('#delete_quotaDialog').dialog('open');
		})

		return false;
	}
	
</script>
	<script type="text/javascript">
	
	function getRowforEdit(editQuota){
        var projid=editQuota.id;
     var VdcId = editQuota.name;
        $('#editprojId').val(projid);
        
        
        $("#message1").html('');
        $("#message2").html('');
        $("#message3").html('');
      
     $.getJSON("./getAllocatedDetailspj/"+projid+ "/"+ VdcId,function(response){
    	 
    	
				var total_mem = response.total_mem;
				var total_vcpu = response.total_vcpu;
				var total_disk_storage = response.total_disk_storage;
				
				
				var dptId= response.dpt_id;
			
				var selectedVdcId = response.vdc_id;
				$('#vdcID').val(selectedVdcId);
			   $('#total_dpt_mem').val(total_mem); 
				 $('#total_dpt_vcpu').val(total_vcpu); 
				 $('#total_dpt_disk_storage').val(total_disk_storage); 
			        $.getJSON("./getVdcNames/"+selectedVdcId,function(response) {
			        	 
			        	 var vdcname = response.vdc_name;
			        	 $('#vdc_name').val(vdcname); 
			    	}) 
			    		$.getJSON("./getVdcDetailsFromDepartmentTable/"+ selectedVdcId+ "/"+ dptId,
																		function(response) {

																			$('#tot_mem').text(response.total_mem);
																			$('#tot_vcpu').text(response.total_vcpu);
																			$('#tot_disk_storage').text(response.total_disk_storage);
																			$('#free_mem').text(response.free_mem);
																			$('#free_vcpu').text(response.free_vcpu);
																			$('#free_disk_storage').text(response.free_disk_storage);

																		})
	  
			    							$("#total_dpt_mem").change(
											function() {

												$("#message1").html('');
												var total_dpt_mem = $(
														"#total_dpt_mem").val()
														.trim();

												$.ajax({
															type : "post",
															url : "./checkallocatedmemoryforprojects",
															data : "total_dpt_mem="
																	+ total_dpt_mem
																	+ "&selectedVdcId="
																	+ selectedVdcId,
															success : function(
																	data) {
																if (data == 0) {
																	$(
																			"#message1")
																			.html(
																					" <font color='red'> <b>Please Enter below or equal to avaliblity memory  </b> </font> ");
																	$(
																			"#total_dpt_mem")
																			.val(
																					'');
																}

															}
														});

											});

			        
			        

					//validation for availability VCPU

					$("#total_dpt_vcpu")
							.change(
									function() {

										// $("#message").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');

										$("#message2").html('');
										var total_dpt_vcpu = $(
												"#total_dpt_vcpu").val()
												.trim();

										$
												.ajax({
													type : "post",
													url : "./checkallocatedvcpuforprojects",
													data : "total_dpt_vcpu="
															+ total_dpt_vcpu
															+ "&selectedVdcId="
															+ selectedVdcId,
													success : function(
															data) {
														if (data == 0) {
															$(
																	"#message2")
																	.html(
																			" <font color='red'> <b>Please Enter below or equal to avalible VCPU  </b> </font> ");
															$(
																	"#total_dpt_vcpu")
																	.val(
																			'');
														}

													}
												});

									});

					
					$("#total_dpt_disk_storage")
					.change(
							function() {

								// $("#message").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');

								$("#message3").html('');
								var total_dpt_disk_storage = $(
										"#total_dpt_disk_storage")
										.val().trim();

								$
										.ajax({
											type : "post",
											url : "./checkallocateddiskstorageforprojects",
											data : "total_dpt_disk_storage="
													+ total_dpt_disk_storage
													+ "&selectedVdcId="
													+ selectedVdcId,
											success : function(
													data) {
												if (data == 0) {
													$(
															"#message3")
															.html(
																	" <font color='red'> <b>Please Enter below or equal to avalible Disk Storage  </b> </font> ");
													$(
															"#total_dpt_disk_storage")
															.val(
																	'');
												}

											}
										});

							});

				
			        
									
			    	
			})
			 		
       
        $('#myModalEdit').modal('show');
         return false;      
    }
		
		$(document)
				.ready(
						function() {
							pageSetUp();
							/* BASIC ;*/
							var responsiveHelper_dt_basic = undefined;
							var responsiveHelper_datatable_fixed_column = undefined;
							var responsiveHelper_datatable_col_reorder = undefined;
							var responsiveHelper_datatable_tabletools = undefined;

							var breakpointDefinition = {
								tablet : 1024,
								phone : 480
							};

							$('#dt_basic')
									.dataTable(
											{
												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,
												"bSort" : true,
												"iDisplayLength" : 5,
												"sScrollY" : "460px",
												"oLanguage" : {
													"sEmptyTable" : "No Data available"
												},
												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_dt_basic) {
														responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
																$('#dt_basic'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_dt_basic
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_dt_basic
															.respond();
												}
											});

							$('#delete_quotaDialog')
									.dialog(
											{
												autoOpen : false,
												width : 600,
												resizable : false,
												modal : true,
												title : " Delete Confirmation ?",
												buttons : [
														{
															html : "<i class='fa fa-trash-o'></i>&nbsp; Delete ProjectQuota",
															"class" : "btn btn-danger",
															click : function() {
																document.projectgrp.action = "./deleteProjectQuota";
																document.projectgrp
																		.submit();
																$(this)
																		.dialog(
																				"close");
															}
														},
														{
															html : "<i class='fa fa-times'></i>&nbsp; Cancel",
															"class" : "btn btn-default",
															click : function() {
																$(this)
																		.dialog(
																				"close");
															}
														} ]
											});

							/* END BASIC */
							/*
							 * CONVERT DIALOG TITLE TO HTML
							 * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
							 */

							$.widget("ui.dialog", $.extend({},
									$.ui.dialog.prototype, {
										_title : function(title) {
											if (!this.options.title) {
												title.html("&#160;");
											} else {
												title.html(this.options.title);
											}
										}
									}));

							$('#dialog_link').click(function() {
								$('#dialog_simple').dialog('open');
								return false;
							});

							$('#dialog_simple')
									.dialog(
											{
												autoOpen : false,
												width : 600,
												resizable : false,
												modal : true,
												title : "<div class='widget-header'><h4><i class='fa fa-warning'></i> Delete Confirmation ?</h4></div>",
												buttons : [
														{
															html : "<i class='fa fa-trash-o'></i>&nbsp; Delete Corporate Group",
															"class" : "btn btn-danger",
															click : function() {
																document.projectgrp.action = "./deleteprojectgrp1";
																document.projectgrp
																		.submit();
																$(this)
																		.dialog(
																				"close");
															}
														},
														{
															html : "<i class='fa fa-times'></i>&nbsp; Cancel",
															"class" : "btn btn-default",
															click : function() {
																$(this)
																		.dialog(
																				"close");
															}
														} ]
											});

							// Validation
							$("#corp-form")
									.validate(
											{
												// Rules for form validation
												rules : {
													corporate_name : {
														required : true
													},
													corporate_description : {
														required : true
													},
													usr_id : {
														required : true
													}
												},

												// Messages for form validation
												messages : {
													corporate_name : {
														required : 'Please enter your corporate group name'
													},
													corporate_description : {
														required : 'Please select your department description'
													},
													usr_id : {
														required : 'Please select owner'
													}

												},

												// Ajax form submition
												submitHandler : function(form) {
													$(form)
															.ajaxSubmit(
																	{
																		success : function() {
																			$(
																					"#dept-form")
																					.addClass(
																							'submited');
																		}
																	});
												},
												// Do not change code below
												errorPlacement : function(
														error, element) {
													error.insertAfter(element
															.parent());
												}
											});

							$('#vdc_id')
									.change(
											function() {
												alert("kalyan");
												$('#vdcDetailsFromService')
														.show();
												var selectedVdcId = $(
														'#vdc_id option:selected')
														.val();
												var cgId = $('#allocateVdcCgId')
														.val();
												$
														.getJSON(
																"./getVdcDetailsFromService/"
																		+ selectedVdcId,
																function(
																		response) {
																	var totalMem = response.maxTotalRAMSize;
																	var totalVcpu = response.maxTotalInstances;
																	var totalDiskStorage = response.maxTotalCores;
																	$(
																			'#tot_mem')
																			.text(
																					response.maxTotalRAMSize);
																	$(
																			'#tot_vcpu')
																			.text(
																					response.maxTotalInstances);
																	$(
																			'#tot_disk_storage')
																			.text(
																					response.maxTotalCores);
																	$
																			.getJSON(
																					"./getVdcDetailsFromCgQuotaTable/"
																							+ cgId
																							+ "/"
																							+ selectedVdcId
																							+ "/"
																							+ totalMem
																							+ "/"
																							+ totalVcpu
																							+ "/"
																							+ totalDiskStorage,
																					function(
																							cgResponse) {
																						$(
																								'#free_mem')
																								.val(
																										cgResponse.availableMem);
																						$(
																								'#free_vcpu')
																								.val(
																										cgResponse.availableVcpu);
																						$(
																								'#free_disk_storage')
																								.val(
																										cgResponse.availableDiskStorage);
																					})
																})

											});

						});
	</script>


	<script>
		function deleteprojectgrp(projectgrp) {
			var id = projectgrp.id;
			var name = projectgrp.name;
			$('#projects').text(name);
			$('#proj_id').val(id);
			$('#dialog_simple').dialog('open');
			return false;
		}
		
		
		
		
		function assignVdc(projectgrp) {
			var projId = projectgrp.id;
			var name = projectgrp.name;
			$('#projname').text(name);
			$
					.getJSON(
							"./getAvailableVdcList",
							function(response) {

								if (!response.length) {
									$('#noVdcModal').modal('show');

								} else {
									$('#vdc_id').html('');
									$('#vdcAssignmentModal').modal('show');
									var options = '';
									options += '<option selected="" disabled="" value="0"> Select VDC </option>';
									for (i = 0; i < response.length; i++) {
										options += '<option value="' + response[i].vdc_id + '">'
												+ response[i].vdc_name
												+ '</option>';
									}

									$('#vdc_id').append(options);

								}
							});

		}

		function getRow(value) {
			var corpId = value.id;
			$('#editcgId').val(corpId);
			var userName = value.name;
			$
					.getJSON(
							"./getcorporate/" + corpId,
							function(response) {
								$('#edit_corporate_name').val(
										response.corporate_name);
								$('#edit_corporate_description').val(
										response.corporate_description);
								$('#status').html('');
								if (response.status == "Active") {
									var options = '';
									options += '<option selected value="Active"> Active </option>';
									options += '<option  value="InActive"> InActive </option>';
									$('#status').append(options);
								} else {
									var options = '';
									options += '<option  value="Active"> Active </option>';
									options += '<option selected value="InActive"> InActive </option>';
									$('#status').append(options);
								}
								$('#edit_usr_id').html('');
								$
										.getJSON(
												"./getOwner/" + userName,
												function(response) {
													var options = '';
													options += '<option selected value="' + response.usr_id + '">'
															+ response.usr_name
															+ '</option>';
													$('#edit_usr_id').append(
															options);

												});

								$
										.getJSON(
												"./getCorpUsers",
												function(response) {
													var options = '';
													for (i = 0; i < response.length; i++) {
														options += '<option value="' + response[i].usr_id + '">'
																+ response[i].usr_name
																+ '</option>';
													}
													$('#edit_usr_id').append(
															options);
												});
							});
		}

		function getAdd() {

			$
					.getJSON(
							"./getCorpUsers",
							function(response) {

								if (!response.length) {
									$('#noCorpUserModal').modal('show');

								} else {
									$('#usr_id').html('');
									$('#myModal').modal('show');
									var options = '';
									options += '<option selected="" disabled="" value="0"> Select Corporate Owner </option>';
									for (i = 0; i < response.length; i++) {
										options += '<option value="' + response[i].usr_id + '">'
												+ response[i].usr_name
												+ '</option>';
									}

									$('#usr_id').append(options);
								}
							});
		}

		function hideVdcDetails() {
			$('#vdcDetailsFromService').hide();
		}
		
		
		
	</script>

</body>

</html>