<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-us">
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

        <title>SyntBots: Corporate Groups Quota</title>
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
        <!--  <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

        <!-- SmartAdmin RTL Support -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css">

        <!-- We recommend you use "your_style.css" to override SmartAdmin
             specific styles this will also ensure you retrain your customization with each SmartAdmin update.
        <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

        <!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
        <link rel="stylesheet" type="text/css" media="screen" href="css/demo.min.css">

        <!-- FAVICONS -->
        <link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
        <link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

        <!-- GOOGLE FONT -->
        <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
    </head>
   <%@ include file="/SesssionFilter.jsp" %>  
    <body class="menu-on-top">
    <!-- #HEADER -->
    <header id="header">

		<!-- PAGE LOGO HEADER -->
		<%@ include file="/includefiles/logoheader.jsp"%>
		<!-- END PAGE LOGO HEADER -->

		<!-- pulled right: nav area -->
      <%--  <div class="pull-right">
           <div class="project-context hidden-xs">
                <span class="label">SyntBots Admin: 
                </span>
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

            <span>
                   <a style="border-radius: 2px;cursor: default !important;display: inline-block;font-weight: 700;height: 30px;line-height: 24px;min-width: 30px;
                             padding: 2px;text-align: center;text-decoration: none !important;-moz-user-select: none;background-color: #F8F8F8;
                             background-image: -moz-linear-gradient(center top , #F8F8F8, #F1F1F1);border: 1px solid #BFBFBF;color: #6D6A69;font-size: 17px;
                             margin: 10px 0px 0px;" href="./logOut.jsp" title="Sign Out" data-action="userLogout"
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
					<li >
						<a href="dashboardAdmin" title="DashBoard"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">DashBoard</span></a>
					</li>
					<li>
						<a href="ListUsers"><i class="fa fa-user"></i> <span class="menu-item-parent">Users</span></a>
					</li>
					
					<li>
						<a href="corporategroups" title="corporateAdmin"><i class="fa  fa-group"></i> <span class="menu-item-parent">Corporate Group</span></a>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-puzzle-piece"></i> <span class="menu-item-parent">VDC Management</span></a>
						<ul>
							<li>
								<a href="register">Register VDC</a>
							</li>
						</ul>		
					</li>
					
					<li  class="active">
					<a href="listCgQuotaDetails"><i class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent"> Quota</span></a>			
					</li>
					
				</ul>
			</nav>
			<span class="minifyme" data-action="minifyMenu"> 
				<i class="fa fa-arrow-circle-left hit"></i> 
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
					<li>Home</li><li>Corporate Group Quota</li>
				</ol>
			</div>
			<!-- END RIBBON -->

			<!-- MAIN CONTENT -->
			<div id="content">

     
		
				<!-- widget grid -->
				<section id="widget-grid" class="">
					
									
					<div class="modal fade" id="vdcAssignmentModalEdit" tabindex="-1" role="dialog">
								<div class="modal-dialog">
								<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" onclick="hideVdcDetails()" data-dismiss="modal" aria-hidden="true">
												&times;
											</button>
											<h4 class="modal-title">
												Allocate VDC to <span id="corpname">Corporate Group</span>
											</h4>
										</div>
										<div class="modal-body no-padding">
									<!-- 	action ="editAllocatedVdcToCorporateGroup" -->
											<form  action ="editAllocatedVdcToCorporateGroup" name="allocateVdcform" id="allocateVdcform" class="smart-form" method="POST">
													<input type="hidden" id="allocateCgId" name="cg_id" value="">
													<input type="hidden" id="allocateVdcId" name="vdc_id" value="">
													<input type="hidden" id="usedMem" name="usedMem" value="">
													<input type="hidden" id="usedVcpu" name="usedVcpu" value="">
													<input type="hidden" id="usedDisk" name="usedDisk" value="">
													<fieldset>
																<section>
																	<div class="row">
																		<label class="label col col-3">Available VDC</label>
																		<div class="col col-9">
																			<!-- <select class = "form-control" id ="editVdcId" name = "vdc_id" >
																				<option value="" disabled="" selected="selected">Select VDC
                                                                        	</option>
																			</select> -->
																			<label class="input" > <!-- <i class="icon-append fa fa-user"></i> -->
																				<input type="text" disabled= "disabled" class="form-control"  name = "vdc_name" id = "vdcname">
																			</label>
																		</div>
																	</div>
																	<div class="row">
                                                                    	<label class="label col col-13"><span id="message"></span> </label>
                                                                    </div> 
																</section>
																<section>
																	<div class="row">
																		<label class="label col col-3">Allocate Memory(MB)</label>
																		<div class="col col-9">
																			<label class="input" > <!-- <i class="icon-append fa fa-user"></i> -->
																				<input type="text" class="form-control"  name = "total_mem" id = "total_cg_mem">
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
																		<label class="label col col-3">Allocate VCPU</label>
																		<div class="col col-9">
																			<label class="input"> <!-- <i class="icon-append fa fa-envelope-o"></i> -->
																				<input type="text" class="form-control" name="total_vcpu" id = "total_cg_vcpu">
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
																		<label class="label col col-3">Allocate Disk Storage(GB)</label>
																		<div class="col col-9">
		                                                                    <label class="input">
		                                                                        <input type="text" class="form-control"  name="total_disk_storage" id="total_cg_disk_storage" >
		                                                                    </label>                                                               
		                                                                </div>
																	</div>
																	<div class="row">
																		<label class="label col col-15"><span id="message3"></span>
																		</label>
																	</div>
																</section> 
															</fieldset>
															<div >
															<fieldset>
																<table  class="table table-striped table-bordered table-hover" width="100%">
									    							<thead>			                
																		<tr>
																			<th>Total Memory(MB)</th>
																			<th>Total VCPU</th>
																			<th>Total Disk Storage(GB)</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>
																			<span id="tot_mem"> </span>
																			</td>
																			<td >
																			<span id="tot_vcpu"> </span>
																			</td>
																			<td >
																			<span id="tot_disk_storage"> </span>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</fieldset>
															
															<fieldset>
																<table  class="table table-striped table-bordered table-hover" width="100%">
									    							<thead>			                
																		<tr>
																			<th>Available Memory(MB)</th>
																			<th>Available VCPU</th>
																			<th>Available Disk Storage(GB)</th>
																		</tr>
																	</thead>
																	<tbody>
																		<tr>
																			<td>
																			<span id="free_mem"></span>
																			</td>
																			<td>
																			<span id="free_vcpu"></span>
																			</td>
																			<td>
																			<span id="free_disk_storage"></span>
																			</td>
																		</tr>
																	</tbody>
																</table>
															</fieldset>
															</div>
										
												<footer>
												<!-- onclick="check()" -->
													<button type="submit"  class="btn btn-primary" >
														Save
													</button>
													<button type="button" class="btn btn-default" onclick="hideVdcDetails()" data-dismiss="modal">
														Cancel
													</button>
					
												</footer>
									    </form>						
						      </div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
			
						<!-- NEW WIDGET START -->
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<form name = "corporateGroupQuota" id =  "corporateGroupQuota" method="POST" >
							<input type = "hidden" name = "cg_id" id = "cg_id">
							<input type = "hidden" name = "vdc_id" id = "vdc_id">
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-0" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="true" data-widget-sortable="false">
								<!-- widget options:
								usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
				
								data-widget-colorbutton="false"
								data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="false"
								data-widget-collapsed="true"
								data-widget-sortable="false"
				
								-->
								<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Quota Details Of Corporate Groups </h2>
				
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
				
										<table id="dt_basic" class="table table-striped table-bordered table-hover" width="100%">
									    <thead>			                
												<tr>
													
													<th data-class="expand">Corporate Name</th>
													<th data-hide="phone,tablet">Vdc Name</th>
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
											<c:forEach items="${vdcCgQuotaMapBeanList}" var="cgquota">
												<tr>
													<td>${cgquota.corpbean.corporate_name}</td>
													<td>${cgquota.vdcMasterBean.vdc_name}</td>
													<td>${cgquota.total_mem}</td>
													<td>${cgquota.total_vcpu}</td>
													<td>${cgquota.total_disk_storage}</td>
													<td>${cgquota.free_mem}</td>
													<td>${cgquota.free_vcpu}</td>
													<td>${cgquota.free_disk_storage}</td>
													<td  style="padding-left: 3%;">
														<a title="edit" data-toggle="modal" id = "${cgquota.cg_id}" name = "${cgquota.vdc_id}" onclick="getRowForEditingVdcAllocation1(this);" href="#myModalEdit1"><i class="fa fa-pencil"></i></a>
														<a title="edit" data-toggle="modal" id = "${cgquota.cg_id}" name = "${cgquota.vdc_id}" onclick="deleteVDCforThisCG1(this);" href="#myModalEdit1"><i class="fa fa-trash-o"></i></a>
														<%-- <a title="delete" href="#" id = "${cgquota.cg_id}" name = "${cgquota.corporate_name}" onclick="deleteCorporateGroup(this)" role="button" ><i class="fa fa-trash-o"></i></a> --%>
														<%-- <a title="edit" style="color:#92A2A8" data-toggle="modal" id="${cgquota.cg_id}" name="${cgquota.created_by}"   href="#"><i class="fa fa-pencil txt-color-grey"></i></a> --%>
                                                		<!-- <a title="delete" style="color:#92A2A8" href="#" role="button" data-toggle="modal" ><i class="fa fa-trash-o txt-color-grey"></i></a> -->
													</td> 
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
	<div id="dialog_simple" title="Dialog Simple Title">
            <p>
                Are you sure you want to delete the Corporate Group: "<span id="corps"> </span>" ?
            </p>
        </div>
		
		<!-- END MAIN PANEL -->
		
		

	<!-- PAGE FOOTER -->
	<%@ include file="/includefiles/footer.jsp"%>
	<!-- END PAGE FOOTER -->

	<!-- SHORTCUT AREA : With large tiles (activated via clicking user name tag)
		Note: These tiles are completely responsive,
		you can add as many as you like
		-->

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

		<!-- PAGE RELATED PLUGIN(S) -->
		<script src="js/plugin/datatables/jquery.dataTables.min.js"></script>
		<script src="js/plugin/datatables/dataTables.colVis.min.js"></script>
		<script src="js/plugin/datatables/dataTables.tableTools.min.js"></script>
		<script src="js/plugin/datatables/dataTables.bootstrap.min.js"></script>
		<script src="js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

		<script type="text/javascript">
		$(document).ready(function() {
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
	
				$('#dt_basic').dataTable({
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"+
						"t"+
						"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
					"autoWidth" : true,
					 "bSort": true,
					    "iDisplayLength": 5,
					    "sScrollY": "460px",
					    "oLanguage": {
				            "sEmptyTable": "No Corporates available"
				          },
					"preDrawCallback" : function() {
						// Initialize the responsive datatables helper once.
						if (!responsiveHelper_dt_basic) {
							responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic'), breakpointDefinition);
						}
					},
					"rowCallback" : function(nRow) {
						responsiveHelper_dt_basic.createExpandIcon(nRow);
					},
					"drawCallback" : function(oSettings) {
						responsiveHelper_dt_basic.respond();
					}
				});
				
				$('#dialog_link').click(function() {
	                $('#dialog_simple').dialog('open');
	                return false;
	       			});
				
					$('#dialog_simple').dialog({
		                autoOpen : false,
		                width : 600,
		                resizable : false,
		                modal : true,
		                title : "<div class='widget-header'><h4><i class='fa fa-warning'></i> Delete Confirmation ?</h4></div>",
		                buttons : [{
		                    html : "<i class='fa fa-trash-o'></i>&nbsp; Delete Corporate Group",
		                    "class" : "btn btn-danger",
		                    click : function() {
		                        document.corporateGroup.action = "./deletecorporategroup1";
		                           document.corporateGroup.submit();
		                        $(this).dialog("close");
		                    }
		                }, {
		                    html : "<i class='fa fa-times'></i>&nbsp; Cancel",
		                    "class" : "btn btn-default",
		                    click : function() {
		                        $(this).dialog("close");
		                    }
		                }]
		            });
					
	
				$("#total_cg_mem").change(function() {
					$("#message1").html('');
					$("#message2").html('');
					$("#message3").html(''); 
					var total_cg_mem = $("#total_cg_mem").val().trim();
					var usedMem =$("#usedMem").val().trim(); 
					var available_mem = $('#free_mem').text().trim();
					available_mem=parseInt(available_mem); 
					usedMem = parseInt(usedMem);
					total_cg_mem = parseInt(total_cg_mem);
					if (total_cg_mem >available_mem) {
						$("#message1").html(" <font color='red'>Please Enter Value Within Available Memory. </font> ");
						$("#total_cg_mem").val('');
					}else if(usedMem >= total_cg_mem){
						$("#message1").html(" <font color='red'>This Much Memory is Already in Use by Departments. Enter a greater Value. </font> ");
						$("#total_cg_mem").val('');
					} 
					
												
				});
			 		// Available VCPU Validation			
				$("#total_cg_vcpu").change(function() {
					$("#message1").html('');
					$("#message2").html('');
					$("#message3").html('');
					var total_cg_vcpu = $("#total_cg_vcpu").val().trim();
					var usedVcpu =$("#usedVcpu").val().trim();
					alert("usedVcpu"+usedVcpu+"total_cg_vcpu"+total_cg_vcpu);
					var available_vcpu = $('#free_vcpu').text();
					available_vcpu = parseInt(available_vcpu);
					usedVcpu = parseInt(usedVcpu);
					total_cg_vcpu = parseInt(total_cg_vcpu);
					if(total_cg_vcpu > available_vcpu)
					{
						$("#message2").html(" <font color='red'>Please Enter Value Within Available VCPU.</font> ");
						$("#total_cg_vcpu").val('');
					}else if(usedVcpu >= total_cg_vcpu) {
						alert("usedVcpu"+usedVcpu);
						$("#message2").html(" <font color='red'>This Much VCPU is Already in Use by Departments. Enter a greater Value. </font> ");
						$("#total_cg_vcpu").val('');
					}
					
				});
					// Available Disk Storage Validation
				$("#total_cg_disk_storage").change(function() {
					$("#message1").html('');
					$("#message2").html('');
					$("#message3").html('');
					var total_disk_storage = $("#total_cg_disk_storage").val().trim();
					var usedDisk =$("#usedDisk").val().trim();
					var available_disk_storage = $('#free_disk_storage').text();
					available_disk_storage = parseInt(available_disk_storage); 
					usedDisk = parseInt(usedDisk);
					total_disk_storage = parseInt(total_disk_storage);
					if (total_disk_storage > available_disk_storage) {
						$("#message3").html(" <font color='red'>Please Enter Value Within Available Disk Storage.</font> ");
						$("#total_cg_disk_storage").val('');
					}else if(usedDisk >= total_disk_storage) {
						$("#message3").html(" <font color='red'>This Much Disk Storage is Already in Use by Departments. Enter a greater Value.</font> ");
						$("#total_cg_disk_storage").val('');						
					}
				}); 
			/* END BASIC */
				 /*
             * CONVERT DIALOG TITLE TO HTML
             * REF: http://stackoverflow.com/questions/14488774/using-html-in-a-dialogs-title-in-jquery-ui-1-10
             */
          
			
            $.widget("ui.dialog", $.extend({}, $.ui.dialog.prototype, {
                _title : function(title) {
                    if (!this.options.title) {
                        title.html("&#160;");
                    } else {
                        title.html(this.options.title);
                    }
                }
            }));
			
         });
		
		function getRowForEditingVdcAllocation(corporateGroup){
			alert("Alerting");
			var cgId = corporateGroup.id;
			var vdcId = corporateGroup.name;
			$('#allocateCgId').val(cgId);
			$('#allocateVdcId').val(vdcId);
			
			$.getJSON("./getVdcNameByVdcId/"+vdcId,function(response){
				alert("Alerting1"); 	
			  
				var  vdcname = response.vdc_name
			    $('#vdcname').val(vdcname);
	    		  
        	});
			
			$.getJSON("./getSelectedRowFromCgQuota/"+cgId+"/"+vdcId,function(response){
				var totMemDb = response.total_mem;
				var totVcpuDb = response.total_vcpu;
				var totDiskDB = response.total_disk_storage;
				$("#total_cg_mem").val(response.total_mem);
				$("#total_cg_vcpu").val(response.total_vcpu);
				$("#total_cg_disk_storage").val(response.total_disk_storage);
				$('#usedMem').val(response.total_mem - response.free_mem);
				$('#usedVcpu').val(response.total_vcpu - response.free_vcpu);
				$('#usedDisk').val(response.total_disk_storage - response.free_disk_storage);
				$.getJSON("./getVdcDetailsFromService/"+vdcId,function(response){
    				var totalMem = response.memoryMb;
    				var totalVcpu = response.vcpus;
    				var totalDiskStorage = response.localGb;
    				 $('#tot_mem').text(response.memoryMb);
                     $('#tot_vcpu').text(response.vcpus); 
                     $('#tot_disk_storage').text(response.localGb); 
                     $.getJSON("./getVdcDetailsFromCgQuotaTable/"+cgId+"/"+vdcId+"/"+totalMem+"/"+totalVcpu+"/"+totalDiskStorage,function(cgResponse){
           			 		$('#free_mem').text(cgResponse.availableMem+totMemDb);
                            $('#free_vcpu').text(cgResponse.availableVcpu+totVcpuDb); 
                            $('#free_disk_storage').text(cgResponse.availableDiskStorage+totDiskDB); 
           			}) 
           			$('#vdcDetailsFromService').show();	
    			})
    			$('#vdcAssignmentModalEdit').modal('show');                  
 
});
			
			
			
		   /*  $.getJSON("./getVdcDetailsFromService/"+vdcId,function(response){
		            				var totalMem = response.memoryMb;
		            				var totalVcpu = response.vcpus;
		            				var totalDiskStorage = response.localGb;
		            				 $('#tot_mem').text(response.memoryMb);
		                             $('#tot_vcpu').text(response.vcpus); 
		                             $('#tot_disk_storage').text(response.localGb); 
		                             $.getJSON("./getVdcDetailsFromCgQuotaTable/"+cgId+"/"+vdcId+"/"+totalMem+"/"+totalVcpu+"/"+totalDiskStorage,function(cgResponse){
		                   			 		$('#free_mem').text(cgResponse.availableMem);
		                                    $('#free_vcpu').text(cgResponse.availableVcpu); 
		                                    $('#free_disk_storage').text(cgResponse.availableDiskStorage); 
		                   			}) 
		                   			$('#vdcDetailsFromService').show();	
		            			})
		            			$('#vdcAssignmentModalEdit').modal('show');                  
		         
			 } */
			 function deleteVDCforThisCG(corporateGroup){
		           var id=corporateGroup.id;
		           var name=corporateGroup.name;
		           $('#corps').text(name);
		           $('#cg_id').val(id);
		           $('#dialog_simple').dialog('open');
		            return false;      
		       }
		function hideVdcDetails(){
			$('#vdcDetailsFromService').hide();
			$("#message1").html('');
			$("#message2").html('');
			$("#message3").html('');
		}
			 }
		/* function check() {   
			 var totalMem = document.getElementById("total_cg_mem").value;
			var freeMem = document.getElementById("allocatedFreeMem").value;
			alert("totalMem"+totalMem+"freeMem"+freeMem);
			totalMem=parseInt(totalMem);
			freeMem=parseInt(freeMem);
			var usedMem = totalMem - freeMem;
			usedMem=parseInt(usedMem);  
			
			
			
			
			
			var totalMem = document.getElementById("total_cg_mem").value;
			var usedMem = document.getElementById("usedMem").value;
			alert("usedMem"+usedMem+"totalMem"+totalMem);
			if(usedMem > totalMem){
				$("#message1").html(" <font color='red'>This Much Value is Already Used. Enter a greater Value. </font> ");
				
	     	}else{
	     		
	     		document.allocateVdcform.action="editAllocatedVdcToCorporateGroup";
	     	}
			
		} */
		
		
		</script>

		
		

	</body>

</html>