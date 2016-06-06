<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en-us">
    <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

        <title>SyntBots</title>
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
					<li >
						<a href="dashboardAdmin" title="DashBoard"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">DashBoard</span></a>
					</li>
					<li>
						<a href="ListUsers"><i class="fa fa-user"></i> <span class="menu-item-parent">Users</span></a>
					</li>
					
					<li class="active">
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
					
					<li>
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
					<li>Home</li><li>Corporate Group</li>
				</ol>
			</div>
			<!-- END RIBBON -->

			<!-- MAIN CONTENT -->
			<div id="content">

     
		
				<!-- widget grid -->
				<section id="widget-grid" class="">
				
					<!-- row -->
					<div class="row">
							<div class="pull-right" style="padding-right: 2%;">
							 <a data-toggle="modal" onclick="getAdd()"  class="btn btn-success txt-color-white pull-right  ">
                                            <i class="fa fa-circle-arrow-up fa-lg"></i>
                                            Add Corporate Group
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
												Create Corporate
											</h4>
										</div>
										<div class="modal-body no-padding">
											<form action="addcorporategroup" id="corp-form" class="smart-form" method="POST">
													<fieldset>
															<section>
																<div class="row">
																	<label class="label col col-2">Corporate Name</label>
																	<div class="col col-10">
																		<label class="input"> <!-- <i class="icon-append fa fa-user"></i> -->
																			<input type="text" name="corporate_name" id="corpo_name">
																		</label>
																	</div>
																</div>
																<div class="row">
									<label class="label col col-13"><span id="messagename"></span>
									</label>
								</div>
														</section>
														  <section>
															<div class="row">
																<label class="label col col-2">Corporate Description</label>
																<div class="col col-10">
																	<label class="input"> <!-- <i class="icon-append fa fa-envelope-o"></i> -->
																		<input type="text" name="corporate_description" id="corporate_description">
																	</label>
																	
																</div>
															</div>
														</section>
														<section>
															   <div class="row">
															<label class="label col col-2">Owner</label>
                                                                <div class="col col-10">
                                                                    <label class="input">
                                                                        <select class="form-control" id="usr_id" name="usr_id">
                                                                        	<!-- <option value="0" disabled="" selected="selected">Select Owner
                                                                        	</option> -->
                                                                        	</select>
                                                                     </label>
                                                                     </div>
                                                                  </div>
																<%-- <c:forEach items="${usersList}" var="owners">
																<option >${owners}</option>
																</c:forEach> --%>
														
														</section>
														
													
														
													 </fieldset>
										
												<footer>
													<button type="submit" class="btn btn-primary">
														Save
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
					
					<div class="modal fade" id="myModalEdit" tabindex="-1" role="dialog">
								<div class="modal-dialog">
								<div class="modal-content">
										<div class="modal-header">
											<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
												&times;
											</button>
											<h4 class="modal-title">
												Edit Corporate Group
											</h4>
										</div>
										<div class="modal-body no-padding">
											<form action="editcorporategroup" id="corp-edit-form" class="smart-form" method="POST">
													<input type="hidden" id="editcgId" name="cg_id" value="">
													<fieldset>
															<section>
																<div class="row">
																	<label class="label col col-2">Corporate Name</label>
																	<div class="col col-10">
																		<label class="input"> <!-- <i class="icon-append fa fa-user"></i> -->
																			<input type="text" name = "corporate_name" id = "edit_corporate_name">
																		</label>
																	</div>
																</div>
														</section>
														  <section>
															<div class="row">
																<label class="label col col-2">Corporate Description</label>
																<div class="col col-10">
																	<label class="input"> <!-- <i class="icon-append fa fa-envelope-o"></i> -->
																		<input type="text" name="corporate_description" id = "edit_corporate_description">
																	</label>
																	
																</div>
															</div>
														</section>
														<section>
															<div class="row">
																<label class="label col col-2">Owner</label>
																<div class="col col-10">
																	<select class = "form-control" id ="edit_usr_id" name = "usr_id" >
																	</select>
																</div>
															</div>
														</section>
														<section>
															<div class="row">
																<label class="label col col-2">Status</label>
																<div class="col col-10">
                                                                    <label class="input">
                                                                        <select class="form-control" id="status" name="status">
                                                                        
                                                                        <option value="Active">Active</option>
                                                                        
                                                                        <option value="InActive">InActive</option>
                                                                        
                                                                        
                                                                        
                                                                    </select> 
                                                                    </label>                                                               
                                                                </div>
															</div>
														</section>
													</fieldset>
										
												<footer>
													<button type="submit" class="btn btn-primary">
														Save
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
			<div  class="modal fade" id="noCorpUserModal"  tabindex="-1" role="dialog" >
						<div class="modal-dialog">	            
				            <div class="modal-content">
                                <div class="modal-header">
                                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                    </button>
                                    <h4 class="modal-title">
                                       Attention: No Users Available!
                                    </h4>
                                </div>
                                <div class="modal-body no-padding">
                                 	&nbsp;&nbsp;&nbsp;<p>
						               	&nbsp;&nbsp;&nbsp;You do not have any users in Common Pool.<br>
						                &nbsp;&nbsp;&nbsp;Add new pool users,then proceed with adding new corporate groups. 
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
						
					<div class="modal fade" id="vdcAssignmentModal" tabindex="-1" role="dialog">
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
											<form  action ="allocateVdcToCorporateGroup" id="allocateVdcform" class="smart-form" method="GET">
													<input type="hidden" id="allocateVdcCgId" name="cg_id" value="">
															<fieldset>
																<section>
																	<div class="row">
																		<label class="label col col-3">Available VDC</label>
																		<div class="col col-9">
																			<select class = "form-control" id ="vdc_id" name = "vdc_id" >
																				<!-- <option value="" disabled="" selected="selected">Select VDC
                                                                        	</option> -->
																			</select>
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
																				<input type="text" class="form-control" disabled = "disabled" name = "total_mem" id = "total_cg_mem">
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
																				<input type="text" class="form-control" disabled = "disabled" name="total_vcpu" id = "total_cg_vcpu">
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
		                                                                        <input type="text" class="form-control" disabled = "disabled" name="total_disk_storage" id="total_cg_disk_storage" >
		                                                                    </label>                                                               
		                                                                </div>
																	</div>
																	<div class="row">
																		<label class="label col col-15"><span id="message3"></span>
																		</label>
																	</div>
																</section> 
															</fieldset>
															<div  id="vdcDetailsFromService" style="display: none;">
															<fieldset>
																<table id="dt_basic1" class="table table-striped table-bordered table-hover" width="100%">
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
																<table id="dt_basic2" class="table table-striped table-bordered table-hover" width="100%">
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
													<button type="submit"  class="btn btn-primary">
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
							<form name = "corporateGroup" id =  "corporateGroup" method="POST" >
							<input type = "hidden" name = "cg_id" id = "cg_id">
							<!-- Widget ID (each widget will need unique ID)-->
							<div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-0" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="true" data-widget-sortable="false">
									<header>
									<span class="widget-icon"> <i class="fa fa-table"></i> </span>
									<h2>Corporate List </h2>
				
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
													
													<th data-class="expand"><i class="fa fa-fw fa-user text-muted hidden-md hidden-sm hidden-xs"></i> Corporate Name</th>
													<th data-hide="phone,tablet"><i class="txt-color-blue hidden-md hidden-sm hidden-xs"></i> Corporate Description</th>
													<th data-hide="phone,tablet"><i class="txt-color-blue hidden-md hidden-sm hidden-xs"></i> Corporate Owner</th>
													<th data-hide="phone,tablet"><i class="fa fa-fw fa-map-marker txt-color-blue hidden-md hidden-sm hidden-xs"></i> Status</th>
													<th data-hide="phone,tablet"><i class="fa fa-fw fa-map-marker txt-color-blue hidden-md hidden-sm hidden-xs"></i>Actions</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${corpgrplist}" var="corpgrp">
												<tr>
													<td>${corpgrp.corporate_name}</td>
													<td>${corpgrp.corporate_description}</td>
													<td>${corpgrp.corporate_owner}</td>
													<c:set var="status" value="${corpgrp.status}"/>
													<td>
														<c:choose>
															<c:when test="${ status=='Active'}">
																<span class="label label-success">${corpgrp.status}</span>
															</c:when>
															<c:otherwise>
																<span class="label label-danger">${corpgrp.status}</span>
															</c:otherwise>
														</c:choose>
													</td>
													<td  style="padding-left: 3%;">
														<a title="edit" data-toggle="modal" id = "${corpgrp.cg_id}" name = "${corpgrp.corporate_owner}" onclick="getRow(this);" href="#myModalEdit"><i class="fa fa-pencil"></i></a>
														<a title="assignVdc" data-toggle="modal" id = "${corpgrp.cg_id}" name = "${corpgrp.corporate_name}" onclick="assignVdc(this);" ><i class="fa fa-sitemap"></i></a>
														<a title="delete" href="#" id = "${corpgrp.cg_id}" name = "${corpgrp.corporate_name}" onclick="deleteCorporateGroup(this)" role="button" ><i class="fa fa-trash-o"></i></a>
														
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

		
		<!-- END MAIN PANEL -->
		
		<div id="dialog_simple" title="Dialog Simple Title">
            <p>
                Are you sure you want to delete the Corporate Group: "<span id="corps"> </span>" ?
            </p>
        </div>
        
         <div  class="modal fade" id="CorpDeptModal"  tabindex="-1" role="dialog" >
						<div class="modal-dialog">	            
				            <div class="modal-content">
                                <div class="modal-header">
                                   <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                                    &times;
                                    </button>
                                     <h3 class="modal-title">
                                       Warning:<br>
                                         </h3>
                                    
                                        
                                       
                                
                                <div class="modal-body no-padding" >
                                  <p >	
						            
						            </p>
                                <p>
                                Departments  are allocated for this "<span id="corps1"> </span>" Corporate group.Ask Corporate Admin to release the following deapartments .
                                       
						            </p> 
						              <p >	
						            
						            </p>
						              <p >	
						            
						            </p>
						            <p id="dynspan">	
						            
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
				
				 // Validation
                $("#corp-edit-form").validate({
                    // Rules for form validation
                    rules : {
                    	corporate_name : {
                            required : true
                        },
                        corporate_description : {
                            required : true
                        }
                    },

                    // Messages for form validation
                    messages : {
                    	corporate_name : {
                            required : 'Please enter some corporate group name'
                        },   
                        corporate_description : {
                            required : 'Please select some corporate description'
                        }                       
                    },

                    // Ajax form submition
                    submitHandler : function(form) {
                        $(form).ajaxSubmit({
                            success : function() {
                                $("#corp-form").addClass('submited');
                            }
                        });
                    },
                // Do not change code below
                    errorPlacement : function(error, element) {
                        error.insertAfter(element.parent());
                    }
                });	
				
				//validation for allocate vdc
				$("#allocateVdcform").validate({
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
                            required : 'Please select one vdc'
                        },   
                     	total_mem : {
                            required : 'Please allocate some memory'
                        },                       
                       total_vcpu : {
                            required : 'Please allocate some vcpu'
                        },
                        total_disk_storage : {
                        	required : 'Please allocate some disk storage'
                        }
                       
                    },

                    // Ajax form submition
                    submitHandler : function(form) {
                        $(form).ajaxSubmit({
                            success : function() {
                                $("#allocateVdcform").addClass('submited');
                            }
                        });
                    },
                // Do not change code below
                    errorPlacement : function(error, element) {
                        error.insertAfter(element.parent());
                    }
                });	
				
				 // Validation
                $("#corp-form").validate({
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
                            required : 'Please enter some corporate group name'
                        },   
                        corporate_description : {
                            required : 'Please select some corporate description'
                        },                       
                        usr_id : {
                            required : 'Please select corporate owner'
                        }
                       
                    },

                    // Ajax form submition
                    submitHandler : function(form) {
                        $(form).ajaxSubmit({
                            success : function() {
                                $("#corp-form").addClass('submited');
                            }
                        });
                    },
                // Do not change code below
                    errorPlacement : function(error, element) {
                        error.insertAfter(element.parent());
                    }
                });	
		
		
			// Validation for not exceeding values in VDC Allocation Modal Box
				// Available Memory Validation
			$("#total_cg_mem").change(function() {
				$("#message1").html('');
				$("#message2").html('');
				$("#message3").html(''); 
				var total_cg_mem = $("#total_cg_mem").val().trim();
				var available_mem = $('#free_mem').text().trim();
				available_mem=parseInt(available_mem); 
				if (total_cg_mem>available_mem) {
					$("#message1").html(" <font color='red'>Please Enter Value Within Available Memory. </font> ");
					$("#total_cg_mem").val('');
				} 
												
			});
		 		// Available VCPU Validation			
			$("#total_cg_vcpu").change(function() {
				$("#message1").html('');
				$("#message2").html('');
				$("#message3").html('');
				var total_cg_vcpu = $("#total_cg_vcpu").val().trim();
				var available_vcpu = $('#free_vcpu').text();
				available_vcpu=parseInt(available_vcpu); 
				if(total_cg_vcpu>available_vcpu)
				{
					$("#message2").html(" <font color='red'>Please Enter Value Within Available VCPU.</font> ");
					$("#total_cg_vcpu").val('');
				}
						
			});
				// Available Disk Storage Validation
			$("#total_cg_disk_storage").change(function() {
				$("#message1").html('');
				$("#message2").html('');
				$("#message3").html('');
				var total_disk_storage = $("#total_cg_disk_storage").val().trim();
				var available_disk_storage = $('#free_disk_storage').text();
				available_disk_storage=parseInt(available_disk_storage); 
				if (total_disk_storage > available_disk_storage) {
					$("#message3").html(" <font color='red'>Please Enter Value Within Available Disk Storage.</font> ");
					$("#total_cg_disk_storage").val('');
				} 
			}); 
													
			
	});
		
		
		
		
		
		//checking project availability
		
		
		$("#corpo_name").change(function() {
			     
			 $("#messagename").html('<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
			 
			      var corpo_name = $("#corpo_name").val().trim();
			    
			     $.ajax({
		                    type:"post",
		                    url:"./checkCorpoNameAvailability",
		                    data:"corpo_name="+corpo_name,
		                        success:function(data){
		                        if(data==0){
		                            $("#messagename").html(" <font color='green'> <b>corporate available </b> </font> ");
		                        }
		                        else{

		                          

		                            $("#messagename").html("  <font color='red'>corporate already Exist!  </font>  ");

		                            $("#corpo_name").val('');
		                        }
		                    }
		                 });
			    
			    });

		</script>

		
		<script>
		
		function deleteCorporateGroup(corporateGroup){
	           var cgId=corporateGroup.id;
	           var name=corporateGroup.name;
	       	$("#dynspan").html("");
	      //  $('#dynspan').append('<span>'+'&nbsp'+':'+'</span>');
	       	$.getJSON("./getAvailableCorpdept/"+cgId,function(response){
	       		if(!response.length){
	       			   $('#corps').text(name);
		 	           $('#cg_id').val(cgId);
		 	           $('#dialog_simple').dialog('open');	             		
             		}
	       		else
	       			{
	       		 $('#corps1').text(name);
	       		
	       		var j=1;
	       		for (i = 0; i < response.length; i++) {
	       		
	       		     
	       			if(j<=6)
	       				{
	       			 $('#dynspan').append('<span>'+response[i].dpt_name+'&nbsp;'+','+'&nbsp;'+'</span>');
	       				}
	       			else
	       				{
	       				j=1;
	       			 $('#dynspan').append('<span>'+response[i].dpt_name+'&nbsp;'+','+'&nbsp;'+'</span>'+'<br>');	
	       				}
		       		j++;
		       		
		       		    }
	       		 
	 	           $('#CorpDeptModal').modal('show');	
	 	        
	       			}
	       	});
	       	
	       
	           
	       }
		
		function assignVdc(corporateGroup){
			var cgId = corporateGroup.id;
			var name = corporateGroup.name;
			$('#allocateVdcCgId').val(cgId);
			$('#corpname').text(name);
			$.getJSON("./getAvailableVdcList/"+cgId,function(response){
				 	if(!response.length){
	            		$('#noVdcModal').modal('show');		             		
	             		}
	                else{
	                	 $('#vdc_id').html('');  
		                	$('#vdcAssignmentModal').modal('show');
		                    var options = '';  
		                    options += '<option selected="" disabled="" value="0"> Select VDC </option>';
		              		for(i=0;i<response.length;i++){
		                		options += '<option value="' + response[i].vdc_id + '">' + response[i].vdc_name + '</option>';
		              		}
		              		$("#message").html("");
		             		$('#vdc_id').append(options);
		             		$('#vdcDetailsFromService').hide();
		             		 $('#vdc_id').change(function () {
		             			 
		             			$("#total_cg_mem").prop('disabled', false);
		             			$("#total_cg_vcpu").prop('disabled', false);
		             			$("#total_cg_disk_storage").prop('disabled', false);
		             			var selectedVdcId=$('#vdc_id option:selected').val();
		             			if(selectedVdcId==40){
			                     	$.getJSON("./getVdcDetailsFromService/"+selectedVdcId,function(response){
			            				
			                     		var totalMem = response.memory_mb;
			            				var totalVcpu = response.vcpus;
			            				var totalDiskStorage = response.local_gb;
			            				 $('#tot_mem').text(totalMem);
			                             $('#tot_vcpu').text(totalVcpu ); 
			                             $('#tot_disk_storage').text(totalDiskStorage); 
			                             $.getJSON("./getVdcDetailsFromCgQuotaTable/"+cgId+"/"+selectedVdcId+"/"+totalMem+"/"+totalVcpu+"/"+totalDiskStorage,function(cgResponse){
			                   			 		$('#free_mem').text(cgResponse.availableMem);
			                                    $('#free_vcpu').text(cgResponse.availableVcpu); 
			                                    $('#free_disk_storage').text(cgResponse.availableDiskStorage); 
			                   			}) 
			                   			$('#vdcDetailsFromService').show();	
			            			})
		             			}
		             			else{
		             				$.getJSON("./getAzureVdcDtlsFrmDB/"+selectedVdcId,function(response){
		             					var totalMem = response.total_mem;
			            				var totalVcpu = response.total_vcpu;
			            				var totalDiskStorage = response.total_disk_storage;
			            				$('#tot_mem').text(totalMem);
			                            $('#tot_vcpu').text(totalVcpu ); 
			                            $('#tot_disk_storage').text(totalDiskStorage); 
			                            $.getJSON("./getVdcDetailsFromCgQuotaTable/"+cgId+"/"+selectedVdcId+"/"+totalMem+"/"+totalVcpu+"/"+totalDiskStorage,function(cgResponse){
		                   			 		$('#free_mem').text(cgResponse.availableMem);
		                                    $('#free_vcpu').text(cgResponse.availableVcpu); 
		                                    $('#free_disk_storage').text(cgResponse.availableDiskStorage); 
		                   				}) 
		                   				$('#vdcDetailsFromService').show();	
		             					
		             				})
		             				
		             			}
		            			
		                             });
		             		 } 
	            	});
			 }
		
		function getRow(value){
	          var corpId=value.id;
	          $('#editcgId').val(corpId);
	          var userName=value.name;
	          $.getJSON("./getcorporate/"+corpId,function(response){
	                 $('#edit_corporate_name').val(response.corporate_name);
	                 $('#edit_corporate_description').val(response.corporate_description);
	                 $('#status').html('');  
	                 if(response.status=="Active"){
	                	 var options = '';  
	                	  options += '<option selected value="Active"> Active </option>';
	                	  options += '<option  value="InActive"> InActive </option>';
	                      $('#status').append(options); 
	                 }
	                 else{
	                  var options = '';  
	               	  options += '<option  value="Active"> Active </option>';
	               	  options += '<option selected value="InActive"> InActive </option>';
	                   $('#status').append(options); 
	                 }
	                 $('#edit_usr_id').html('');    
	                 $.getJSON("./getOwner/"+userName,function(response){
	                             var options = '';                 
	                                options += '<option selected value="' + response.usr_id + '">' + response.usr_name + '</option>';
	                                $('#edit_usr_id').append(options); 
	                             
	           
	                          });
	                 
	                
	                  $.getJSON("./getCorpUsers",function(response){
	                        var options = '';   
	                        for(i=0;i<response.length;i++){
	                           options += '<option value="' + response[i].usr_id + '">' + response[i].usr_name + '</option>';
	                        }
	                       $('#edit_usr_id').append(options);     
	                     });  
	               });         
	         }
		function getAdd(){
            $.getJSON("./getCorpUsers",function(response){
				if(!response.length){
            		$('#noCorpUserModal').modal('show');
	             		
             	}
                else{
                	$('#usr_id').html('');
                	$('#myModal').modal('show');
                    var options = ''; 
                    options += '<option selected="" disabled="" value="0"> Select Corporate Owner </option>';
              		for(i=0;i<response.length;i++){
                		options += '<option value="' + response[i].usr_id + '">' + response[i].usr_name + '</option>';
              		}
              		
             		$('#usr_id').append(options);
                }
                });
        }
		function hideVdcDetails(){
			$('#vdcDetailsFromService').hide();
			$("#message1").html('');
			$("#message2").html('');
			$("#message3").html('');
		}
		
		
		</script>

	</body>

</html>