<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.syntel.isap.provisioning.bean.EnvironmentVM" %>
<%@ page import="com.syntel.isap.provisioning.bean.EnvironmentVMExt" %>
<%@ page import="com.syntel.isap.provisioning.constants.ISAPConstants" %>
<!DOCTYPE html>
<html lang="en-us">
	<head>
	
		<meta charset="utf-8">
		<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

		<title>SyntBots User Bespoke Service Page </title>
		<meta name="description" content="">
		<meta name="author" content="">	
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/font-awesome.min.css">

		<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production-plugins.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.min.css">

		<!-- SmartAdmin RTL Support -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> 

		<!-- #FAVICONS syntel logo -->
		<link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

		<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/demo.min.css">

		<style>

          .panel-white {border-color: #333; border-top-color: #333; border-right-color-value: rgb(51, 51, 51);border-bottom-color: #333;
             border-left-color-value: rgb(51, 51, 51);border-left-color-ltr-source: physical; border-left-color-rtl-source: physical;
             border-right-color-ltr-source: physical; border-right-color-rtl-source: physical;
            }
      
			.error-text-2 {
				text-align: center;
				font-size: 700%;
				font-weight: bold;
				font-weight: 100;
				color: #333;
				line-height: 1;
				letter-spacing: -.05em;
				background-image: -webkit-linear-gradient(92deg,#333,#ed1c24);
				-webkit-background-clip: text;
				-webkit-text-fill-color: transparent;
			}
			.particle {
				position: absolute;
				top: 50%;
				left: 50%;
				width: 1rem;
				height: 1rem;
				border-radius: 100%;
				background-color: #ed1c24;
				background-image: -webkit-linear-gradient(rgba(0,0,0,0),rgba(0,0,0,.3) 75%,rgba(0,0,0,0));
				box-shadow: inset 0 0 1px 1px rgba(0,0,0,.25);
			}
			.particle--a {
				-webkit-animation: particle-a 1.4s infinite linear;
				-moz-animation: particle-a 1.4s infinite linear;
				-o-animation: particle-a 1.4s infinite linear;
				animation: particle-a 1.4s infinite linear;
			}
			.particle--b {
				-webkit-animation: particle-b 1.3s infinite linear;
				-moz-animation: particle-b 1.3s infinite linear;
				-o-animation: particle-b 1.3s infinite linear;
				animation: particle-b 1.3s infinite linear;
				background-color: #00A300;
			}
			.particle--c {
				-webkit-animation: particle-c 1.5s infinite linear;
				-moz-animation: particle-c 1.5s infinite linear;
				-o-animation: particle-c 1.5s infinite linear;
				animation: particle-c 1.5s infinite linear;
				background-color: #57889C;
			}@-webkit-keyframes particle-a {
			0% {
			-webkit-transform: translate3D(-3rem,-3rem,0);
			z-index: 1;
			-webkit-animation-timing-function: ease-in-out;
			} 25% {
			width: 1.5rem;
			height: 1.5rem;
			}
		
			50% {
			-webkit-transform: translate3D(4rem, 3rem, 0);
			opacity: 1;
			z-index: 1;
			-webkit-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .75rem;
			height: .75rem;
			opacity: .5;
			}
		
			100% {
			-webkit-transform: translate3D(-3rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@-moz-keyframes particle-a {
			0% {
			-moz-transform: translate3D(-3rem,-3rem,0);
			z-index: 1;
			-moz-animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.5rem;
			height: 1.5rem;
			}
		
			50% {
			-moz-transform: translate3D(4rem, 3rem, 0);
			opacity: 1;
			z-index: 1;
			-moz-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .75rem;
			height: .75rem;
			opacity: .5;
			}
		
			100% {
			-moz-transform: translate3D(-3rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@-o-keyframes particle-a {
			0% {
			-o-transform: translate3D(-3rem,-3rem,0);
			z-index: 1;
			-o-animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.5rem;
			height: 1.5rem;
			}
		
			50% {
			-o-transform: translate3D(4rem, 3rem, 0);
			opacity: 1;
			z-index: 1;
			-o-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .75rem;
			height: .75rem;
			opacity: .5;
			}
		
			100% {
			-o-transform: translate3D(-3rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@keyframes particle-a {
			0% {
			transform: translate3D(-3rem,-3rem,0);
			z-index: 1;
			animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.5rem;
			height: 1.5rem;
			}
		
			50% {
			transform: translate3D(4rem, 3rem, 0);
			opacity: 1;
			z-index: 1;
			animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .75rem;
			height: .75rem;
			opacity: .5;
			}
		
			100% {
			transform: translate3D(-3rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@-webkit-keyframes particle-b {
			0% {
			-webkit-transform: translate3D(3rem,-3rem,0);
			z-index: 1;
			-webkit-animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.5rem;
			height: 1.5rem;
			}
		
			50% {
			-webkit-transform: translate3D(-3rem, 3.5rem, 0);
			opacity: 1;
			z-index: 1;
			-webkit-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .5rem;
			height: .5rem;
			opacity: .5;
			}
		
			100% {
			-webkit-transform: translate3D(3rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@-moz-keyframes particle-b {
			0% {
			-moz-transform: translate3D(3rem,-3rem,0);
			z-index: 1;
			-moz-animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.5rem;
			height: 1.5rem;
			}
		
			50% {
			-moz-transform: translate3D(-3rem, 3.5rem, 0);
			opacity: 1;
			z-index: 1;
			-moz-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .5rem;
			height: .5rem;
			opacity: .5;
			}
		
			100% {
			-moz-transform: translate3D(3rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@-o-keyframes particle-b {
			0% {
			-o-transform: translate3D(3rem,-3rem,0);
			z-index: 1;
			-o-animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.5rem;
			height: 1.5rem;
			}
		
			50% {
			-o-transform: translate3D(-3rem, 3.5rem, 0);
			opacity: 1;
			z-index: 1;
			-o-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .5rem;
			height: .5rem;
			opacity: .5;
			}
		
			100% {
			-o-transform: translate3D(3rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@keyframes particle-b {
			0% {
			transform: translate3D(3rem,-3rem,0);
			z-index: 1;
			animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.5rem;
			height: 1.5rem;
			}
		
			50% {
			transform: translate3D(-3rem, 3.5rem, 0);
			opacity: 1;
			z-index: 1;
			animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .5rem;
			height: .5rem;
			opacity: .5;
			}
		
			100% {
			transform: translate3D(3rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@-webkit-keyframes particle-c {
			0% {
			-webkit-transform: translate3D(-1rem,-3rem,0);
			z-index: 1;
			-webkit-animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.3rem;
			height: 1.3rem;
			}
		
			50% {
			-webkit-transform: translate3D(2rem, 2.5rem, 0);
			opacity: 1;
			z-index: 1;
			-webkit-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .5rem;
			height: .5rem;
			opacity: .5;
			}
		
			100% {
			-webkit-transform: translate3D(-1rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@-moz-keyframes particle-c {
			0% {
			-moz-transform: translate3D(-1rem,-3rem,0);
			z-index: 1;
			-moz-animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.3rem;
			height: 1.3rem;
			}
		
			50% {
			-moz-transform: translate3D(2rem, 2.5rem, 0);
			opacity: 1;
			z-index: 1;
			-moz-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .5rem;
			height: .5rem;
			opacity: .5;
			}
		
			100% {
			-moz-transform: translate3D(-1rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@-o-keyframes particle-c {
			0% {
			-o-transform: translate3D(-1rem,-3rem,0);
			z-index: 1;
			-o-animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.3rem;
			height: 1.3rem;
			}
		
			50% {
			-o-transform: translate3D(2rem, 2.5rem, 0);
			opacity: 1;
			z-index: 1;
			-o-animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .5rem;
			height: .5rem;
			opacity: .5;
			}
		
			100% {
			-o-transform: translate3D(-1rem,-3rem,0);
			z-index: -1;
			}
			}
		
			@keyframes particle-c {
			0% {
			transform: translate3D(-1rem,-3rem,0);
			z-index: 1;
			animation-timing-function: ease-in-out;
			}
		
			25% {
			width: 1.3rem;
			height: 1.3rem;
			}
		
			50% {
			transform: translate3D(2rem, 2.5rem, 0);
			opacity: 1;
			z-index: 1;
			animation-timing-function: ease-in-out;
			}
		
			55% {
			z-index: -1;
			}
		
			75% {
			width: .5rem;
			height: .5rem;
			opacity: .5;
			}
		
			100% {
			transform: translate3D(-1rem,-3rem,0);
			z-index: -1;
			}
			}
		</style>
       
		
		<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
	
	</head>
	

<%@ include file="/SesssionFilter.jsp" %>  
	   
    <body class="menu-on-top pace-done">
    
		<!-- #HEADER -->
		<header id="header">
          <!-- PAGE LOGO HEADER -->
          		<%@ include file="/includefiles/logoheader.jsp" %>
<!-- END PAGE LOGO HEADER -->

			
			<!-- #TOGGLE LAYOUT BUTTONS -->
			<!-- pulled right: nav area -->
			<%-- <div class="pull-right">

               <div class="project-context hidden-xs">
                <span class="label">Tenant User: ( Project : STG Automtation Team )</span>
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown"> 
                    <i class="fa fa-user"></i>
                     <span> Welcome ,&nbsp;&nbsp;${userValue.usr_name}</span>
                 </a>
               </div>
               
               	<!-- collapse menu button -->
				<div id="hide-menu" class="btn-header pull-right">
					<span> <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu"><i class="fa fa-reorder"></i></a> </span>
				</div>
				<!-- end collapse menu -->

              <span> <a style="border-radius: 2px;cursor: default !important;display: inline-block;font-weight: 700;height: 30px;line-height: 24px;min-width: 30px;
                                padding: 2px;text-align: center;text-decoration: none !important;-moz-user-select: none;background-color: #F8F8F8;
                                background-image: -moz-linear-gradient(center top , #F8F8F8, #F1F1F1);border: 1px solid #BFBFBF;color: #6D6A69;font-size: 17px;
                                margin: 10px 0px 0px;" href="./logout" title="Sign Out" data-action="userLogout" 
                                data-logout-msg="You can improve your security further after logging out by closing this opened browser">
                           <i class="fa fa-sign-out"></i>
                       </a> 
             </span> 

           </div> --%>
			<!-- end pulled right: nav area -->

		</header>
		<!-- END HEADER -->


		<!-- #NAVIGATION -->
		<!-- Left panel : Navigation area -->
		<!-- Note: This width of the aside area can be adjusted through LESS variables -->
		<aside id="left-panel">
		<!-- NAVIGATION : This navigation is also responsive-->
		<nav>
			<ul>
				<li><a href="departmentHome" title="Dashboard"><i
						class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">Dashboard</span></a>
				</li>
				<li><a href="departmentList" title="departments"><i
						class="fa  fa-group"></i> <span class="menu-item-parent">Departments</span></a>
				</li>
				<li><a href="departmentQuota"><i
						class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent">
							Quota</span></a></li>
				<li><a href="corporateUserList"><i class="fa fa-user"></i>
						<span class="menu-item-parent">Corporate Users</span></a></li>

				<li class="active"><a href="bluePrintDetails"><i
						class="fa fa-lg fa-fw fa-puzzle-piece"></i> <span
						class="menu-item-parent">Blue Print</span></a>
					<ul>
						<li><a href="blueprint">Bespoke </a></li>
					</ul></li>
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
				<%-- 	<span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true">
						<i class="fa fa-refresh"></i>
					</span>  --%>
				</span>
				<!-- breadcrumb -->
				<ol class="breadcrumb">
					<li>Home</li><li>Blue Print Details</li>
				</ol>
			</div>
			<!-- END RIBBON -->

					<!-- MAIN CONTENT -->
		  <div id="content">	
		  
		  <div class="pull-right">
							 <a href="bespokeBluePrint" class="btn btn-primary txt-color-white">Add Blue Print</a>   
                                        
							<br><br>
						</div>
		   <div class="row">
				       <!-- NEW WIDGET START -->
				<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<form name="dptgrp" id="dptgrp" method="GET">
						<input type="hidden" name="dpt_id" id="depID">
						<input type="hidden" name="vdc_id" id="vdc_ID">
						<!-- Widget ID (each widget will need unique ID)-->
						<div class="jarviswidget jarviswidget-color-blueLight"
							id="wid-id-0" data-widget-editbutton="false"
							data-widget-togglebutton="false" data-widget-deletebutton="false"
							data-widget-fullscreenbutton="false"
							data-widget-custombutton="true" data-widget-sortable="false">
							<header>
								<span class="widget-icon"> <i class="fa fa-table"></i>
								</span>
								<h2>Blue Print Details List</h2>

							</header>

							<!-- widget div-->
							<div>

								
								<!-- widget content -->
								<div class="widget-body no-padding">

									<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
										<thead>
											<tr>
												<th data-hide="phone,tablet">Name</th>
												<th data-hide="phone,tablet">Description</th>
												<th data-class="expand">Provider </th>
												<th data-hide="phone,tablet" style="width:50%">Components</th>
												<th data-hide="phone,tablet">Status</th>
												<th data-hide="phone,tablet">Actions</th> 
											</tr>
										</thead>
										<tbody>
											<c:forEach items="${envVMList}" var="envVM">
												<tr>
													<td>${envVM.vm_name}</td>
													<td  style="width:20%">
													<%-- <c:forEach items="${envVM.vdc}" var="envDesc"> --%>
													${envVM.os}
													<%-- </c:forEach> --%>
													</td>
													
													<td>${envVM.vdc_provider}</td>
													<td  style="width:20%">
													<c:forEach items="${envVM.pkgList}" var="envVMPkgList">
													${envVMPkgList.package_name},${envVMPkgList.package_version}
													</c:forEach>
													</td>
													<td><c:choose>

															<c:when test="${envVM.vdc_provider == 'MS-Azure'}">
																<span class="label label-success">${envVM.status}</span>
															</c:when>
															<c:otherwise>
																<span class="label label-success">${envVM.status}</span>
																<br>
																<br>
															</c:otherwise>
														</c:choose> <%-- <c:if test="${envVM.vdc_provider == 'MS-Azure'}">
															<strong> ${envVM.status}</strong>
														</c:if> <c:if test="${envVM.vdc_provider == 'OpenStack'}">
															<strong> ${envVM.status}</strong>
														</c:if> --%></td>
													<td><a title="edit" onclick="getRowforEdit1(this);"
														data-toggle="modal"><i class="fa fa-pencil"></i></a> <a
														title="delete" onclick="getQuotaByID1(this);"
														href="#myModal1" data-toggle="modal"><i
															class="fa fa-trash-o" style="color: red;"></i></a></td>
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
					
        </div>
        </div>
        </div>
   <!-- END MAIN PANEL -->

		<!-- PAGE FOOTER -->

	<%@ include file="/includefiles/footer.jsp"%>

	<!-- END PAGE FOOTER -->
	
    <!--================================================== -->
		<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
		<script data-pace-options='{ "restartOnRequestAfter": true }' src="js/plugin/pace/pace.min.js"></script>

		<!-- Link to Google CDN's jQuery + jQueryUI; fall back to local -->
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
		<script>
			if (!window.jQuery) {
				document.write('<script src="js/libs/jquery-2.1.1.min.js"><\/script>');
			}
		</script>

		<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.10.3/jquery-ui.min.js"></script>
		<script>
			if (!window.jQuery.ui) {
				document.write('<script src="js/libs/jquery-ui-1.10.3.min.js"><\/script>');
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

		<!--[if IE 8]>

		<h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

		<![endif]-->
		<!-- MAIN APP JS FILE -->
		<script src="js/app.min.js"></script>

		<!-- ENHANCEMENT PLUGINS : NOT A REQUIREMENT -->
		<!-- Voice command : plugin -->
		<script src="js/speech/voicecommand.min.js"></script>

		<!-- SmartChat UI : plugin -->
		<script src="js/smart-chat-ui/smart.chat.ui.min.js"></script>
		<script src="js/smart-chat-ui/smart.chat.manager.min.js"></script>

		<!-- PAGE RELATED PLUGIN(S) 
		<script src="..."></script>-->
		<!-- PAGE RELATED PLUGIN(S) -->
	<script src="js/plugin/datatables/jquery.dataTables.min.js"></script>
	<script src="js/plugin/datatables/dataTables.colVis.min.js"></script>
	<script src="js/plugin/datatables/dataTables.tableTools.min.js"></script>
	<script src="js/plugin/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

	<script type="text/javascript">
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
			pageSetUp();
			
			
			
			localStorage.setItem("sm-setmenu","top");
			$.root_.addClass("fixed-page-footer") ;
			
		    $( "#loading1" ).hide();
		    
		    /* BASIC ;*/
			var responsiveHelper_dt_basic = undefined;
			var breakpointDefinition = {
				tablet : 1024,
				phone : 480
			};

			$('#dt_basic')
					.dataTable(
							{
								"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12  hidden-xs'l>r>"
										+ "t"
										+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
								"autoWidth" : true,
								"bSort" : true,
								"sScrollY" : "350px",
								"oLanguage" : {
									"sEmptyTable" : "No Build Details available"
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
			/* END BASIC */
			
			$('#startDate').datepicker({
				dateFormat : 'yy-mm-dd', 
				prevText : '<i class="fa fa-chevron-left"></i>',
				nextText : '<i class="fa fa-chevron-right"></i>',
				onSelect : function(selectedDate) {
					$('#endDate').datepicker('option', 'minDate', selectedDate);
				}
			});
			
			$('#endDate').datepicker({
				dateFormat : 'yy-mm-dd',
				prevText : '<i class="fa fa-chevron-left"></i>',
				nextText : '<i class="fa fa-chevron-right"></i>',
				onSelect : function(selectedDate) {
					$('#startDate').datepicker('option', 'maxDate', selectedDate);
				}
			}); 
			
			
			  $.getJSON("./getKeyPairs",function(response){
                  var options = '';   
                  var select = $('#keypair');

                  for(i=0;i<response.length;i++){
                  	 $('<option>').val(response[i]).text(response[i]).appendTo(select);
                   }  	
            
               }); 
			  
			  
			  
			     
			  // Validation
					$("#addForm").validate({

						// Rules for form validation
						rules : {
							vm_name : {
								required : true
							},
							service_desc : {
								required : true
							},
							service_req_name : {
								required : true
							},
							image : {
								required : true
							},
							network : {
								required : true
							},
							startDate : {
								required : true
							},
							endDate : {
								required : true
							},
							flavor : {
								required : true
							},
							security : {
								required : true
							},
							keypair : {
								required : true
							}
							
						},

						// Messages for form validation
						messages : {
							vm_name : {
								required : 'Please enter your instance name'
							},	
							service_desc : {
								required : 'Please enter your service description'
							},
							service_req_name : {
								required : 'Please enter your service name'
							},
							security : {
								required : 'Please select security group'
							},
							image : {
								required : 'Please select image'
							},
							network : {
								required : 'Please select network'
							},
							flavor : {
								required : 'Please select flavor'
							},
							startDate : {
								required : 'You must select startdate'
							},
							endDate : {
								required : 'You must select enddate'
							},
							keypair : {
								required : 'You must select keypair'
							}
						},

						// Ajax form submition
						submitHandler : function(form) {
							$(form).ajaxSubmit({
								success : function() {
									$("#smart-form-register").addClass('submited');
								}
							});
						},
					// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
	  			    
			
		})
		function getMasterId(vmId,vdcProvider){	
			$("#vmId").val(vmId);
			$("#vdcProvider").val(vdcProvider);
			alert("vmId from get master"+vmId);
			alert("vdc provider from get master"+vdcProvider);
			if(vdcProvider=="OpenStack"){
				document.getElementById("azureBlock").style.display = 'none';
			    document.getElementById("openstackKeypairBlock").style.display = 'block';
			}else{
				document.getElementById("openstackKeypairBlock").style.display = 'none';
			    document.getElementById("azureBlock").style.display = 'block';
			}
			
			  $('#dynamicFormElements').html('');
			 $.getJSON("./getVMAttributes/"+vmId,function(response){	
				 alert("inside");
				 for(i=0;i<response.length;i++){
					  $('#dynamicFormElements').append('<section class="col col-10"><label class="label"><b>'+response[i].package_name+'</b><hr></section>'); 					 
					 for(j=0;j<response[i].packageAttributes.length;j++){
						 var attr=response[i].packageAttributes[j].attr_name;
						 alert("attr is"+attr);
						 
						 $('#dynamicFormElements').append(' <section class="col col-6"><label class="label">'+response[i].packageAttributes[j].attr_name+'</label><label class="input"><input type="text" name='+response[i].packageAttributes[j].attr_name+' id='+response[i].packageAttributes[j].attr_name+' value='+response[i].packageAttributes[j].default_val+' ></label></section>'); 
					 }
                  }  					 				 
			 });
			 $( "#loading1" ).hide();
		}

	</script>
	<script type="text/javascript">
		function getMasterId(vmId,vdcProvider){	
			$("#vmId").val(vmId);
			$("#vdcProvider").val(vdcProvider);
			//alert("vmId from get master"+vmId);
			alert("vdc provider from get master"+vdcProvider);
			if(vdcProvider=="OpenStack"){
				document.getElementById("azureBlock").style.display = 'none';
			    document.getElementById("openstackKeypairBlock").style.display = 'block';
			}else{
				document.getElementById("openstackKeypairBlock").style.display = 'none';
			    document.getElementById("azureBlock").style.display = 'block';
			}
			
			  $('#dynamicFormElements').html('');
			 $.getJSON("./getVMAttributes/"+vmId,function(response){				 
				 for(i=0;i<response.length;i++){
					  $('#dynamicFormElements').append('<section class="col col-10"><label class="label"><b>'+response[i].package_name+'</b><hr></section>'); 					 
					 for(j=0;j<response[i].packageAttributes.length;j++){
						 var attr=response[i].packageAttributes[j].attr_name;
						 alert("attr is"+attr);
						 
						 $('#dynamicFormElements').append(' <section class="col col-6"><label class="label">'+response[i].packageAttributes[j].attr_name+'</label><label class="input"><input type="text" name='+response[i].packageAttributes[j].attr_name+' id='+response[i].packageAttributes[j].attr_name+' value='+response[i].packageAttributes[j].default_val+' ></label></section>'); 
					 }
                  }  					 				 
			 });
			 $( "#loading1" ).hide();
		}
	</script>
	
	</body>
</html>