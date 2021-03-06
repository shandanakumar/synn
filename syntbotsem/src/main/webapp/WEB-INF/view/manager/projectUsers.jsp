<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-us">
<head>
<meta charset="utf-8">
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

<title>SyntBots Poject Admin - User List</title>
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

<!-- SmartAdmin RTL Support -->
<link rel="stylesheet" type="text/css" media="screen"
	href="css/smartadmin-rtl.min.css">

<!-- We recommend you use "your_style.css" to override SmartAdmin
             specific styles this will also ensure you retrain your customization with each SmartAdmin update.
        <link rel="stylesheet" type="text/css" media="screen" href="css/your_style.css"> -->

<!-- Demo purpose only: goes with demo.js, you can delete this css when designing your own WebApp -->
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
		<%-- <div class="pull-right">
			<div class="project-context hidden-xs">
				<span class="label">Corporate Admin: </span> <a href="#"
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


	<!-- #NAVIGATION -->
	<!-- Left panel : Navigation area -->
	<!-- Note: This width of the aside area can be adjusted through LESS variables -->
	<aside id="left-panel">
		<!-- NAVIGATION : This navigation is also responsive-->
		<nav>
			<ul>
				<li ><a href="dashboardProject" title="Dashboard"><i
						class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">Dashboard</span></a>
				</li>
				<li class="active"><a href="endUserList" title="endUserList"><i
						class="fa  fa-group"></i> <span class="menu-item-parent">Project Users</span></a>
				</li>
				<li><a href="endUserVMList"><i
						class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent">
							Project VM List</span></a></li>
				<li  ><a href="endUserProjectQuota"><i
						class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent">
							Quota</span></a></li>
		<li><a href="projectKeyPairList"><i
						class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent">
							KeyPair List</span></a></li>		
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
			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<li>Home</li>
				<li>Project Users</li>
			</ol>
			<!-- end breadcrumb -->
		</div>
		<!-- END RIBBON -->


		<div id="content">

			<!-- widget grid -->
			<section id="widget-grid" class="">
			
			<!-- row -->
					<div class="row">
                       
						 <div class="pull-right" style="padding-right: 2%;">
							 <a data-toggle="modal" onclick="getUserNameDetails(this);"  class="btn btn-success txt-color-white pull-right  ">
                                            <i class="fa fa-circle-arrow-up fa-lg"></i>
                                            Add User
                                        </a>   
							<br><br>
						</div>		
					
								</div>

					
	              				<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
								<div class="modal-dialog">
								<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
												&times;
											</button>
											<h4 class="modal-title">
												 Add User To Project
											</h4>
										</div>
										<div class="modal-body no-padding">
											<form action="addDPoolUser" id="pool-form" class="smart-form" method="POST">
											<fieldset>
											<section>
											
												<label class="label"> Select Pool Users </label>
												<label class="select select-multiple"> 
												<select class ="custom-scroll" multiple="" name="user_name" id=dpoolUser></select>
													</label>
												
									
	
										</section>
													 </fieldset>
										
												<footer>
													<button type="submit" id="user"  class="btn btn-primary">
														Add User
													</button>
													<button type="button" class="btn btn-default" data-dismiss="modal">
														Cancel
													</button>
					
												</footer>
									    </form>						
						      </div>
					</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
			</div><!-- /.modal -->
				<!-- row -->
				
			<!--warning for empty popup for add users-->	
			 <div  class="modal fade" id="noUsersModal"  tabindex="-1" role="dialog" >
						<div class="modal-dialog">	            
				            <div class="modal-content">
                                <div class="modal-header">
                                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                    </button>
                                    <h4 class="modal-title">
                                       Attention: There are no user in this Department pool!
                                    </h4>
                                </div>
                                <div class="modal-body no-padding">
                                 		&nbsp;&nbsp;&nbsp;<p>
						               	<br>
						     
						               	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please Add Users in Department
						                <br>
						                <br>
						            </p>
						        </div>
						        <div class="modal-footer">
						        	
                 	                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    	Cancel
                                    </button>               
                                 
						        </div>
						         
						     </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                   </div><!-- /.modal -->	
                <!--warning for empty popup for add users end-->   
            
				
				<div class="row">
					
										<!-- edit popup -->
					<div class="modal fade" id="myModal1" tabindex="-1" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">Edit Department</h4>
								</div>
								<div class="modal-body no-padding">
									<form action="editDepartment" id="edit-dept-form"
										class="smart-form" method="POST">

										<input type="hidden" id="editDeptId" name="dpt_id" value="">
										<fieldset>
											<section>
												<div class="row">
													<label class="label col col-2">Department Name</label>
													<div class="col col-10">
														<label class="input"> <input type="text"
															name="dpt_name" id="edit_dpt_name">
														</label>
													</div>
												</div>
											</section>
											<section>
												<div class="row">
													<label class="label col col-2">Department
														Description</label>
													<div class="col col-10">
														<label class="input"> <input type="text"
															name="dpt_desc" id="edit_dpt_desc">
														</label>

													</div>
												</div>
											</section>
											<section>
												<div class="row">
													<label class="label col col-2">Owner</label>
													<div class="col col-10">
														<label class="input"> <select class="form-control"
															id="edit_usr_id" name="usr_id">
														</select>
														</label>
													</div>
												</div>
											</section>

											<section>
												<div class="row">
													<label class="label col col-2">Status</label>
													<div class="col col-10">
														<label class="input"> <select class="form-control"
															id="status" name="status">

																<option value="Active">Active</option>

																<option value="InActive">InActive</option>



														</select>
														</label>
													</div>
												</div>
											</section>

										</fieldset>
										<footer>
											<button type="submit" class="btn btn-primary">Save
												Changes</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Cancel</button>
										</footer>
									</form>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->
					<!-- end of edit popup -->
				

					<div class="modal fade" id="noDeptUserModal" tabindex="-1"
						role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">Attention: No Users Available!</h4>
								</div>
								<div class="modal-body no-padding">
									&nbsp;&nbsp;&nbsp;
									<p>
										&nbsp;&nbsp;&nbsp;You do not have any users in Corporate Pool.<br>
										&nbsp;&nbsp;&nbsp;Please ask your admin to add corporate pool
										users,then proceed with adding new departments.
									</p>
								</div>
								<div class="modal-footer">

									<button type="button" class="btn btn-default"
										data-dismiss="modal">Cancel</button>

								</div>

							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->

					<div class="modal fade" id="myModal" tabindex="-1" role="dialog">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal"
										aria-hidden="true">&times;</button>
									<h4 class="modal-title">Create Department</h4>
								</div>
								<div class="modal-body no-padding">
									<form action="addDepartment" id="dept-form" class="smart-form"
										method="POST">
										<fieldset>
											<section>
												<div class="row">
													<label class="label col col-2">Department Name</label>
													<div class="col col-10">
														<label class="input"> <input type="text"
															name="dpt_name" id="dpt_name">
														</label>
													</div>
												</div>
											</section>
											<section>
												<div class="row">
													<label class="label col col-2">Department
														Description</label>
													<div class="col col-10">
														<label class="input"> <input type="text"
															name="dpt_desc" id="dpt_desc">
														</label>

													</div>
												</div>
											</section>
											<section>
												<div class="row">
													<label class="label col col-2">Owner</label>
													<div class="col col-10">
														<label class="input"> <select class="form-control"
															id="usr_id" name="usr_id">
																<option value="" disabled="" selected="selected">Select
																	Owner</option>

														</select>
														</label>
													</div>
												</div>
											</section>
										</fieldset>
										<footer>
											<button type="submit" class="btn btn-primary">Save</button>
											<button type="button" class="btn btn-default"
												data-dismiss="modal">Cancel</button>
										</footer>
									</form>
								</div>
							</div>
							<!-- /.modal-content -->
						</div>
						<!-- /.modal-dialog -->
					</div>
					<!-- /.modal -->


					<form name="projectusers" id="projectusers">
						<!-- NEW WIDGET START -->
						<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
							<div class="jarviswidget jarviswidget-color-darken" id="wid-id-1"
								data-widget-editbutton="false" data-widget-editbutton="false"
								data-widget-togglebutton="false"
								data-widget-deletebutton="false"
								data-widget-fullscreenbutton="false"
								data-widget-custombutton="true" data-widget-sortable="false"  data-widget-colorbutton="false">
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
									<span class="widget-icon"> <i class="fa fa-table"></i>
									</span>
									<h2>Project User Details</h2>
								</header>
								<div>
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
									</div>
									<!-- end widget edit box -->
									<!-- widget content -->
									<div class="widget-body no-padding">
										<input type="hidden" id="deptId" name="deptId" value="">

										<table id="dt_basic"
											class="table table-striped table-bordered table-hover"
											width="100%">
											<thead>
												<tr>
													<th data-class="expand">User Name</th>
													<th data-hide="phone,tablet">Role</th>
													<th data-hide="phone,tablet">Status</th>
													<th data-hide="phone,tablet"><i
														class="fa fa-fw fa-map-marker txt-color-blue hidden-md hidden-sm hidden-xs"></i>Actions</th>
												</tr>
											</thead>
											<tbody>
												<c:forEach items="${userList}" var="enduser">
													<tr>
														<td>${enduser.usr_name}</td>
														<td>${enduser.role_name}</td>
													
														<c:set var="paramName" value="${enduser.status}" />
														<td><c:choose>
																<c:when test="${ paramName=='Active'}">
																	<span class="label label-success">Active</span>
																</c:when>
																<c:otherwise>
																	<span class="label label-danger">Inactive</span>
																</c:otherwise>
															</c:choose></td>
								<td style="padding-left: 2%;">
								<a title="edit" data-toggle="modal565" id="${department.dpt_id}"name="${department.owner}" onclick="getRow(this);"href="#myModal123"><i class="fa fa-pencil"></i></a> 
								<a title="assignVdctoDpt" data-toggle="modal1223"id="${department.dpt_id}" name="${department.dpt_name}"onclick="assignVdctoDpt(this);"><i class="fa fa-sitemap"></i></a> 
								<a title="delete" href="#" id="${department.dpt_id}" name="${department.dpt_name}"onclick="deleteDepartment1(this)" class="fa fa-trash-o"></i></a> <%--       </c:when>
                                                           <c:otherwise>
                                                           
                                                           </c:otherwise>
                                                         </c:choose>   --%>
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
						</article>
						<!-- WIDGET END -->
					</form>
				</div>
				<!-- end row -->
			</section>
			<!-- end widget grid -->
		</div>
		<!-- END MAIN CONTENT -->
	</div>
	<!-- END MAIN PANEL -->

	<!-- ui-dialog -->
	<div id="dialog_simple" title="Dialog Simple Title">
		<p>
			Are you sure you want to delete the user : <span id="dept">
			</span> ? .
		</p>
	</div>


	<div class="modal fade" id="vdcAssignmentModal" tabindex="-1"role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" onclick="hideVdcDetails()"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">
						Allocate VDC to <span id="dptname"> DepartMent Group</span>
					</h4>
				</div>
				<div class="modal-body no-padding">
					<form action="allocateVdcToDepartmentGroup" id="allocateVdc-form"
						class="smart-form" method="GET">
						<input type="hidden" id=allocateVdctoDpt name="dpt_id" value="">
						<fieldset>
							<section>
								<div class="row">
									<label class="label col col-3">Available VDC</label>
									<div class="col col-9">
										<select class="form-control" id="vdc_id" name="vdc_id">
											<!-- <option value="" disabled="" selected="selected">Select VDC
                                                                        	</option> -->
										</select>
									</div>
								</div>

							</section>
							<section>
								<div class="row">
									<label class="label col col-3">Allocate Memory</label>
									<div class="col col-9" div id="total_mem">
										<label class="input"> <!-- <i class="icon-append fa fa-user"></i> -->
											<input type="text" name="total_mem" id="total_cg_mem">
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
										<label class="input"> <!--  <i class="icon-append fa fa-envelope-o"></i> -->
											<input type="text" name="total_vcpu" id="total_cg_vcpu">
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
									<label class="label col col-3">Allocate Disk Storage</label>
									<div class="col col-9">
										<label class="input"> <input class="form-control"
											name="total_disk_storage" id="total_cg_disk_storage">
										</label>
									</div>
								</div>
								<div class="row">
									<label class="label col col-15"><span id="message3"></span>
									</label>
								</div>
							</section>
						</fieldset>
						<div id="vdcDetailsFromService" style="display: none;">
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
						</div>

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
	<!-- /.modal -->

 
                   <div  class="modal fade" id="noVdcModal"  tabindex="-1" role="dialog" >
						<div class="modal-dialog">	            
				            <div class="modal-content">
                                <div class="modal-header">
                                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                    </button>
                                    <h4 class="modal-title">
                                       Attention: No VDC Available for Allocation!
                                    </h4>
                                </div>
                                <div class="modal-body no-padding">
                                 		&nbsp;&nbsp;&nbsp;<p>
						               	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Please Register New VDC, then allocate.<br>
						               	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;OR<br>
						               	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Go to Quota Page to Edit or Delete Previous Allocations.
						                
						            </p>
						        </div>
						        <div class="modal-footer">
						        	
                 	                <button type="button" class="btn btn-default" data-dismiss="modal">
                                    	Cancel
                                    </button>               
                                 
						        </div>
						         
						     </div><!-- /.modal-content -->
                        </div><!-- /.modal-dialog -->
                   </div><!-- /.modal -->	


	<!-- PAGE FOOTER -->

	<%@ include file="/includefiles/footer.jsp"%>

	<!-- END PAGE FOOTER -->

	<!-- PACE LOADER - turn this on if you want ajax loading to show (caution: uses lots of memory on iDevices)-->
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

	<!--[if IE 8]>

        <h1>Your browser is out of date, please update your browser by going to www.microsoft.com/download</h1>

        <![endif]-->


	<!-- MAIN APP JS FILE -->
	<script src="js/app.min.js"></script>

	<!-- PAGE RELATED PLUGIN(S) -->
	<script src="js/plugin/datatables/jquery.dataTables.min.js"></script>
	<script src="js/plugin/datatables/dataTables.colVis.min.js"></script>
	<script src="js/plugin/datatables/dataTables.tableTools.min.js"></script>
	<script src="js/plugin/datatables/dataTables.bootstrap.min.js"></script>
	<script
		src="js/plugin/datatable-responsive/datatables.responsive.min.js"></script>
	<script type="text/javascript">
		/* quota function  */

		function assignVdctoDpt(departmentGroup) {
			var dptId  = departmentGroup.id;
			var  dptname = departmentGroup.name;
			$('#dptname').text(dptname);
			var selectedVdcId = null;
			$('#allocateVdctoDpt').val(dptId);
			//$('#dptname').text(name);
			$.getJSON("./getAvailableVdcLists/"+dptId,function(response) {

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
									$('#vdcDetailsFromService').hide();
									$('#vdc_id')
											.change(
													function() {
														 selectedVdcId = $('#vdc_id option:selected').val();

														$.getJSON("./getVdcDetailsFromCorporateTable/"+ selectedVdcId,function(response) {
																$('#tot_mem').text(response.total_mem);
																			$('#tot_vcpu').text(response.total_vcpu);
																			$('#tot_disk_storage').text(response.total_disk_storage);
																			$('#free_mem').text(response.free_mem);
																			$('#free_vcpu').text(response.free_vcpu);
																			$('#free_disk_storage').text(response.free_disk_storage);

																		})

														$('#vdcDetailsFromService').show();

														$("#message").html("");
														// alert("aftershow");
														/* $.ajax({type : "post",
																	url : "./checkVdcAllocates",

																	//data: "{'selectedVdcId':'" + selectedVdcId + "', 'dptId': '" + dptId + "'}",

																	data : "selectedVdcId="
																			+ selectedVdcId
																			+ "&dptId="
																			+ dptId,

																	success : function(
																			data) {
																		if (data == 0) {
																			$(
																					"#message")
																					.html(
																							" <font color='green'> <b>This VDC not allocated for this Departement </b> </font> ");
																		} else {

																			$(
																					"#message")
																					.html(
																							"  <font color='red'>This VDC is Allocated for this Departement Choose different !  </font>  ");

																		}
																	}
																}); */

													});
								}
			
								
								
								
								$("#total_cg_mem")
								.change(
										function() {

											// $("#message").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');

											$("#message1").html('');
											var total_cg_mem = $(
													"#total_cg_mem").val()
													.trim();

											$
													.ajax({
														type : "post",
														url : "./checkallocatedmemory",
														data : "total_cg_mem="
																+ total_cg_mem
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
																		"#total_cg_mem")
																		.val(
																				'');
															}

														}
													});

										});

								

								//validation for availability VCPU

								$("#total_cg_vcpu")
										.change(
												function() {

													// $("#message").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');

													$("#message2").html('');
													var total_cg_vcpu = $(
															"#total_cg_vcpu").val()
															.trim();

													$
															.ajax({
																type : "post",
																url : "./checkallocatedvcpu",
																data : "total_cg_vcpu="
																		+ total_cg_vcpu
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
																				"#total_cg_vcpu")
																				.val(
																						'');
																	}

																}
															});

												});

								
								$("#total_cg_disk_storage")
								.change(
										function() {

											// $("#message").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');

											$("#message3").html('');
											var total_cg_disk_storage = $(
													"#total_cg_disk_storage")
													.val().trim();

											$
													.ajax({
														type : "post",
														url : "./checkallocateddiskstorage",
														data : "total_cg_disk_storage="
																+ total_cg_disk_storage
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
																		"#total_cg_disk_storage")
																		.val(
																				'');
															}

														}
													});

										});

								
								
								
			
			});
			
			
			
			
			
			
		}
		
		
		function getUserNameDetails(data){
			  $('#dpoolUser').html('');
			 $.getJSON("./getDPoolUsers",function(response){
				var options = '';   
				
				if (!response.length) {
					$('#noUsersModal').modal('show');

				}
				else{
		            for(i=0;i<response.length;i++){
		            	
		            	
		               options += '<option value="' + response[i].usr_id + '">' + response[i].usr_name + '</option>';
		            }
		           $('#dpoolUser').append(options);  
		           $('#myModal').modal('show');
				}
		         });
			
			
		}

		function deleteDepartment(department) {
			var id = department.id;
			var name = department.name;
			$('#dept').text(name);
			$('#deptId').val(id);
			$('#dialog_simple').dialog('open');
			return false;
		}

		function getRow(value) {
			var deptId = value.id;
			$('#editDeptId').val(deptId);
			var userName = value.name;

			$
					.getJSON(
							"./getDepartment/" + deptId,
							function(response) {
								$('#edit_dpt_name').val(response.dpt_name);
								$('#edit_dpt_desc').val(response.dpt_desc);
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
								if (userName.trim() !== "") {
									$
											.getJSON(
													"./getOwners/" + userName,
													function(response) {
														var options = '';
														options += '<option selected value="' + response.usr_id + '">'
																+ response.usr_name
																+ '</option>';
														$('#edit_usr_id')
																.append(options);

													});
								} else {
									var options = '';
									options += '<option selected value="0"> Select new Owner </option>';
									$('#edit_usr_id').append(options);
								}

								$
										.getJSON(
												"./getDeptUsers",
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
							"./getDeptUsers",
							function(response) {

								if (!response.length) {
									$('#noDeptUserModal').modal('show');

								} else {

									$('#myModal').modal('show');
									var options = '';
									for (i = 0; i < response.length; i++) {
										options += '<option value="' + response[i].usr_id + '">'
												+ response[i].usr_name
												+ '</option>';
									}

									$('#usr_id').append(options);

								}
							});
		}
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		$(document)
				.ready(
						function() {

							//validation for availability memory

						/* 	$("#total_cg_mem")
									.change(
											function() {

												// $("#message").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');

												$("#message1").html('');
												var total_cg_mem = $(
														"#total_cg_mem").val()
														.trim();

												$
														.ajax({
															type : "post",
															url : "./checkallocatedmemory",
															data : "total_cg_mem="
																	+ total_cg_mem,
															success : function(
																	data) {
																if (data == 0) {
																	$(
																			"#message1")
																			.html(
																					" <font color='red'> <b>Please Enter below or equal to avaliblity memory  </b> </font> ");
																	$(
																			"#total_cg_mem")
																			.val(
																					'');
																}

															}
														});

											});

							//validation for availability VCPU

							$("#total_cg_vcpu")
									.change(
											function() {

												// $("#message").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');

												$("#message2").html('');
												var total_cg_vcpu = $(
														"#total_cg_vcpu").val()
														.trim();

												$
														.ajax({
															type : "post",
															url : "./checkallocatedvcpu",
															data : "total_cg_vcpu="
																	+ total_cg_vcpu,
															success : function(
																	data) {
																if (data == 0) {
																	$(
																			"#message2")
																			.html(
																					" <font color='red'> <b>Please Enter below or equal to avalible VCPU  </b> </font> ");
																	$(
																			"#total_cg_vcpu")
																			.val(
																					'');
																}

															}
														});

											});

							//validation for availability Allocate Disk Storage

							$("#total_cg_disk_storage")
									.change(
											function() {

												// $("#message").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');

												$("#message3").html('');
												var total_cg_disk_storage = $(
														"#total_cg_disk_storage")
														.val().trim();

												$
														.ajax({
															type : "post",
															url : "./checkallocateddiskstorage",
															data : "total_cg_disk_storage="
																	+ total_cg_disk_storage,
															success : function(
																	data) {
																if (data == 0) {
																	$(
																			"#message3")
																			.html(
																					" <font color='red'> <b>Please Enter below or equal to avalible Disk Storage  </b> </font> ");
																	$(
																			"#total_cg_disk_storage")
																			.val(
																					'');
																}

															}
														});

											}); */

							pageSetUp();
							$.root_.addClass("fixed-page-footer");
							localStorage.setItem("sm-setmenu", "top");

							/* $.getJSON("./getDeptUsers",function(response){
							    var options = '';   
							    for(i=0;i<response.length;i++){
							       options += '<option value="' + response[i].usr_id + '">' + response[i].usr_name + '</option>';
							    }
							   $('#usr_id').append(options);     
							 });  
							 */
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

							/*
							 * DIALOG SIMPLE
							 */

							// Dialog click
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
															html : "<i class='fa fa-trash-o'></i>&nbsp; Delete department",
															"class" : "btn btn-danger",
															click : function() {
																document.department.action = "./deleteDepartment";
																document.department
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
						
							$("#pool-form").validate(
							{
								rules : {
									user_name : {
										required : true
									},
								},
								messages : {
									
									user_name : {
										required : 'Please select atleast one user'
									},
								},
								
							});		
							 
							
							
							
							
							
							
							
							$("#edit-dept-form").validate(
											{
												// Rules for form validation
												rules : {
													dpt_name : {
														required : true
													},
													dpt_desc : {
														required : true
													},
													usr_id : {
														required : true
													}
												},

												// Messages for form validation
												messages : {
													dpt_name : {
														required : 'Please enter your department name'
													},
													dpt_desc : {
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
																					"#edit-dept-form")
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

							// Validation
							$("#dept-form")
									.validate(
											{
												// Rules for form validation
												rules : {
													dpt_name : {
														required : true
													},
													dpt_desc : {
														required : true
													},
													usr_id : {
														required : true
													}
												},

												// Messages for form validation
												messages : {
													dpt_name : {
														required : 'Please enter your department name'
													},
													dpt_desc : {
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

							
							$("#allocateVdc-form").validate(
									{
										// Rules for form validation
										rules : {
											vdc_id : {
												required : true
											},
											total_mem : {
												required : true
											},
											total_vcpu : {
												required : true
											},
											total_disk_storage : {
												required : true
											}
										},

										// Messages for form validation
										messages : {
											vdc_id : {
												required : 'Please Select Vdcname'
											},
											total_mem : {
												required : 'Please Allocate Total memory'
											},
											total_vcpu : {
												required : 'Please Allocate Total vcpu'
											},
											total_disk_storage : {
												required : 'Please Allocate Total Disk Storage'
											}

										},

										// Ajax form submition
										submitHandler : function(form) {
											$(form).ajaxSubmit(
															{
																success : function() {
																	$("#allocateVdc-form").addClass('submited');
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
												"sScrollY" : "420px",
												"oLanguage" : {
													"sEmptyTable" : "No Departments available"
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
						});
	</script>
</body>
</html>