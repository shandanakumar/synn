<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en-us">
<head>
<meta charset="utf-8">
<!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

<title>SyntBots Register VDC</title>
<meta name="description" content="">
<meta name="author" content="">

<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

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

		
		<!-- iOS web-app metas : hides Safari UI Components and Changes Status Bar Appearance -->
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
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
<link rel="stylesheet" type="text/css" media="screen" href="css/demo.min.css">
<style>
#logo img {
	width: 60%;
	height: 123%;
	padding-left: -2px;
	margin-left: -13px;
	margin-top: -12px;
}

.btn-primary {
	background-color: none;
	border-color: #2C699D;
}

.selected {
	color: red;
}
</style>
<!-- GOOGLE FONT -->
<link rel="stylesheet"
	href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">
</head>

<%@ include file="/SesssionFilter.jsp"%>
<body class="menu-on-top">
	<!-- #HEADER -->
	<header id="header">
		<!-- PAGE LOGO HEADER -->
		<%@ include file="/includefiles/logoheader.jsp"%>
		<!-- END PAGE LOGO HEADER -->

		<!-- pulled right: nav area -->
		<%--  <div class="pull-right">
           <div class="project-context hidden-xs">
                <span class="label">Technical Admin: ( Project : STG Automtation Team )</span>
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
				<li><a href="dashboardAdmin" title="DashBoard"><i
						class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">DashBoard</span></a>
				</li>
				<li><a href="ListUsers"><i class="fa fa-user"></i> <span
						class="menu-item-parent">Users</span></a></li>

				<li><a href="corporategroups" title="corporateAdmin"><i
						class="fa  fa-group"></i> <span class="menu-item-parent">Corporate
							Group</span></a></li>
				<li class="active"><a href="#"><i
						class="fa fa-lg fa-fw fa-puzzle-piece"></i> <span
						class="menu-item-parent">VDC Management</span></a>
					<ul>
						<li class="active"><a href="register">Register VDC</a></li>
					</ul></li>
				<li><a href="listCgQuotaDetails"><i
						class="fa fa-lg fa-fw fa-cogs"></i> <span class="menu-item-parent">
							Quota</span></a></li>
				

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
			<span class="ribbon-button-alignment"> <%-- 	<span id="refresh" class="btn btn-ribbon" data-action="resetWidgets" data-title="refresh"  rel="tooltip" data-placement="bottom" data-original-title="<i class='text-warning fa fa-warning'></i> Warning! This will reset all your widget settings." data-html="true">
						<i class="fa fa-refresh"></i>
					</span>  --%>
			</span>
			<!-- breadcrumb -->
			<ol class="breadcrumb">
				<li>Home</li>
				<li>VDC Management</li>
				<li>Register VDC</li>
			</ol>
		</div>
		<!-- END RIBBON -->

		<!-- MAIN CONTENT -->
		<div id="content">

			<!-- widget grid -->
			<section id="widget-grid" class="">
				<div class="row">
					<div class="pull-right"></div>
				</div>
				<!-- row -->
				<div class="row">

					<!-- NEW WIDGET START -->
					<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">


						<!-- Widget ID (each widget will need unique ID)-->
						<div class="jarviswidget" id="wid-id-11"
							data-widget-colorbutton="false" data-widget-colorbutton="false"
							data-widget-editbutton="false" data-widget-togglebutton="false"
							data-widget-deletebutton="false"
							data-widget-fullscreenbutton="false"
							data-widget-custombutton="false">


							<header>

								<ul id="widget-tab-1" class="nav nav-tabs pull-left">

									<li class="active"><a data-toggle="tab" href="#hr1"><span
											class="hidden-mobile hidden-tablet">VDC</span> </a></li>
									<!-- <li><a data-toggle="tab" href="#hr2"><span
											class="hidden-mobile hidden-tablet"> SCM</span> </a></li>
									<li><a data-toggle="tab" href="#hr3"><span
											class="hidden-mobile hidden-tablet">MONITOR</span></a></li> -->


								</ul>

							</header>


							<!-- widget div-->
							<div>

								<!-- widget content -->
								<div class="widget-body no-padding">

									<!-- widget body text-->

									<div class="tab-content padding-10">
										<div class="tab-pane fade in active" id="hr1">

											<!-- <div id="main" role="main"> -->
											<!-- Main content -->
											<section class="content">

													<div class="row">
														<div class="col-xs-12" style="margin-left: 0;">
															<div class="box">
																<div class="box-body table-responsive">
																	<div>Use this service to register an installed
																		compute POD(Point of Delivery) and select the cloud
																		infrastructure platform that manage its resources.</div>
																	<br>
																	<div>
																		<p style="color: blue">Click images to register to
																			VDC</p>
																	</div>
																	<br>
																	<ul id="external-events" class="list-unstyled">
																	
																	  	<li><span> <a href="javascript:void(0);"
																				data-toggle="modal" data-target="#Azure">
																				<img width="100" height="40" alt="azurelogo"
																				src="img/isapimg/azure-logo.png"></img></a>
																		</span></li>
																		<li><span> <a href="javascript:void(0);"
																				data-toggle="modal" data-target="#openstack">
																			 	<img width="100" height="40" alt="openStacklogo"
																				src="img/isapimg/openstacklogo.png"></img></a>
																		</span></li>

																		<li><span> <a href="javascript:void(0);"
																				data-toggle="modal" data-target="#AWS1">
																			<img width="100" height="40" alt="awslogo"
																				src="img/isapimg/awslogo.png"></img></a>
																		</span></li>
																		<li><span> <a href="javascript:void(0);"
																				data-toggle="modal" data-target="#VMWare1">
																				<img width="100" height="40" alt="vmwarelogo"
																				src="img/isapimg/vmwarelogo.png"></img></a>
																		</span></li>

                                                                      
																		<li>
																			<div>

																				<div class="easy-pie-chart txt-color-orangeDark"
																					data-percent="0" data-pie-size="30" id="serverLoad">
																					<span class="percent percent-sign">35</span>
																				</div>
																				<span class="easy-pie-title "> Server Load <span
																					id="server"></span></span>

																			</div>

																		</li>
																		<li>
																			<div>

																				<div class="easy-pie-chart txt-color-greenLight "
																					data-percent="0" data-pie-size="30" id="diskSpace">
																					<span class="percent percent-sign">78.9 </span>
																				</div>
																				<span class="easy-pie-title "> Disk Space<span
																					id="disk"></span></span>
																			</div>

																		</li>
																		<li>
																			<div>

																				<div class="easy-pie-chart txt-color-blue "
																					data-percent="0" data-pie-size="30" id="Memory">
																					<span class="percent percent-sign">23 </span>
																				</div>
																				<span class="easy-pie-title "> Memory <span
																					id="mem"></span></span>
																			</div>

																		</li>


																	</ul>
																</div>
																<!-- /.box-body -->


															</div>
															<!-- /.box -->


															<!-- widget grid -->
															<section id="widget-grid" class="">
																<!-- row -->
																<div class="row">
																	
																			<article
																		class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

																		<!-- 	Widget ID (each widget will need unique ID) -->
																		<div class="jarviswidget jarviswidget-color-blueLight"
																			id="wid-id-1" data-widget-colorbutton="false"
																			data-widget-editbutton="false"
																			data-widget-togglebutton="false"
																			data-widget-deletebutton="false"
																			data-widget-fullscreenbutton="false"
																			data-widget-custombutton="true"
																			data-widget-sortable="false">
																			<header>
																				<span class="widget-icon"> <i
																					class="fa fa-table"></i>
																				</span>
																				<h2>Azure VDC</h2>

																			</header>

																			<!-- 	widget div -->
																			<div>

																				<!-- widget edit box -->
																				<div class="jarviswidget-editbox">
																					<!-- This area used as dropdown edit box  -->

																				</div>
																				<!-- end widget edit box -->

																				<!-- widget content -->
																				<div class="widget-body no-padding">

																					<table id="AzureVdc"
																						class="table table-striped table-bordered"
																						height="100%" width="100%">
																						<thead>

																							<tr>
																								<th>Vdc Name</th>
																								<th data-hide="phone,tablet">Location</th>
																								
																								<th data-hide="phone">Registered At</th>
																								<th>Description</th>
																								<th data-hide="phone,tablet">Status</th>
																								<th>Action</th>


																							</tr>
																							 
																						</thead>

																						
																						<tbody>
																									<c:forEach items="${azureVdcList}" var="user">
																										<tr>
																											<td><c:out value="${user.vdc_name}" />&nbsp;&nbsp;&nbsp;<a
																												style="color: #358;" id="${user.vdc_id}"
																												href="#" onClick="test(this);">usage</a></td>
																											<td><c:out value="${user.vdc_location}" /></td>
																											
																											<td><c:out value="${user.created_at}" /></td>
																											<td><c:out
																													value="${user.azureVdcList.description}" /></td>
																											<c:choose>
																												<c:when test="${user.vdc_status=='Active'}">
																													<td><span class="label label-success">Active</span></td>
																												</c:when>
																												<c:when
																													test="${user.vdc_status=='Inactive'}">
																													<td><span class="label label-danger">Inactive</span></td>
																												</c:when>
																											</c:choose>
																											<td><a title="edit" id="${user.vdc_id}"
																												name="${user.vdc_name}"
																											onclick="getRowOpenstack1(this);"
																											href="#myModalEditOpenstack1"
																												data-toggle="modal"><i
																													class="fa fa-pencil"></i></a> 
																													
																													<a title="delete"
																												href="#myModalDeleteOpenstack1${user.vdc_id}"
																												data-toggle="modal"><i
																													class="fa fa-trash-o" onClick="del1()"
																													role="button"></i></a>

																												<div class="modal small fade"
																													id="myModalDeleteOpenstack${user.vdc_id}"
																													tabindex="-1" role="dialog"
																													aria-labelledby="myModalLabel"
																													aria-hidden="true">
																													<div class="modal-dialog">
																														<div class="modal-content">
																															<div class="modal-header">

																																<h4 class="modal-title">Delete
																																	Project confirmations !</h4>
																															</div>
																															<div class="modal-body">
																																<p>
																																	<i class="fa fa-warning"></i>Are you
																																	sure you want to delete the VDC
																																	:${user.vdc_name} ? <br>This
																																	cannot be undone.
																																</p>
																															</div>
																															<div class="modal-footer">
																																<button class="btn btn-default"
																																	data-dismiss="modal">Cancel</button>
																																<button class="btn btn-danger"
																																	onclick="deleteOpenstack1(this);"
																																	id="${user.vdc_id}">Delete</button>
																															</div>
																														</div>
																													</div>
																												</div></td>
																										</tr>
																									</c:forEach>
																								</tbody>
																								
																							<%--  
                                          <c:forEach  items="${list3}" var="user">
                                                      <tr>
                                                      <td><c:out value="${user.vdc_name}"/></td>
                                                      
                                                      <td><c:out value="${user.vdc_location}" /></td>
                                                      <td><c:out value="${user.tenant}" /></td>
                                                      <td><c:out value="${user.created_at}" /></td>
                                                      <td><c:out value="${user.description}" /></td>
                                                     <!--  <td>
														  <div class="btn-group">
																<button class="btn btn-primary btn-sm dropdown-toggle"
																	    data-toggle="dropdown">
																	    Select <span class="caret"></span>
																</button>
																<ul class="dropdown-menu">
																	<li><a href="javascript:void(0);"
																		data-toggle="modal" data-target="#myModalO">Active</a>
																	</li>
																	<li><a href="javascript:void(0);"
																		data-toggle="modal" data-target="#myModalO">InActive</a>
																	</li>
																</ul>
															</div>
														 </td> -->
														 <c:choose>
                                                        <c:when test="${user.vdc_status=='Active'}">
                                                        <td> <span class="label label-success">Active</span></td>
                                                         </c:when>
                                                         <c:when test="${user.vdc_status=='Inactive'}">
                                                         <td> <span class="label label-danger">Inactive</span></td>
                                                         </c:when>
                                                         </c:choose>
														  <td>
														 <a title="edit"  href="#myModal1" data-toggle="modal"><i class="fa fa-pencil"></i></a>
                                                         <a title="delete" href="#myModal${user.vdc_name}" data-toggle="modal"><i class="fa fa-trash-o" onClick="del()" role="button"></i></a>
														
														
      									   <div class="modal small fade" id="myModal${user.vdc_name}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      												<div class="modal-dialog">
       											  <div class="modal-content">
          											 <div class="modal-header">
					
															<h4 class="modal-title">
															Delete Project confirmations !
															</h4>
														</div>
          										<div class="modal-body">
           											 <p><i class="fa fa-warning"></i>Are you sure you want to delete the VDC :${user.vdc_name} ?  <br>This cannot be undone.</p>
         											 </div>
            									<div class="modal-footer">
            									<button class="btn btn-default" data-dismiss="modal" >Cancel</button>
           										<button class="btn btn-danger" onclick="deleteProject(this);" id="${user.vdc_name}">Delete</button>
           										 </div>
         									 </div>
       										 </div>
     									</div>
														 </td>
													 </tr>
                                                 </c:forEach>  --%>
																						
																					</table>

																				</div>
																				<!-- 	end widget content -->
																			</div>
																			<!-- end widget div -->
																		</div>
																		<!-- WIDGET END -->

																	</article>

																	<article
																		class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

																		<!-- NEW WIDGET START -->

																		<!-- Widget ID (each widget will need unique ID)-->
																		<div class="jarviswidget jarviswidget-color-blueLight"
																			id="wid-id-1" data-widget-colorbutton="false"
																			data-widget-editbutton="false"
																			data-widget-togglebutton="false"
																			data-widget-deletebutton="false"
																			data-widget-fullscreenbutton="false"
																			data-widget-custombutton="true"
																			data-widget-sortable="false">

																			<header>
																				<span class="widget-icon"> <i
																					class="fa fa-table"></i>
																				</span>
																				<h2>OpenStack VDC</h2>
																			</header>
																			<!-- widget div-->
																			<div>

																				<form name="editForm1">

																					<input type="hidden" name="vdc_id" id="vdc_id" />

																					<!-- widget edit box -->
																					<div class="jarviswidget-editbox">
																						<!-- This area used as dropdown edit box -->
																					</div>
																					<!-- end widget edit box -->

																					<!-- widget content -->
																					<div class="widget-body no-padding">

																						<div class="table-responsive">

																							<table id="Openstack"
																								class="table table-striped table-bordered"
																								width="100%">
																								<thead>
																									<tr>
																										<th>Vdc Name</th>
																										<th>Version</th>
																										<th data-hide="phone,tablet">Location</th>
																										<th data-class="expand">Tenant</th>
																										<th data-hide="phone">Registered At</th>
																										<th>Description</th>
																										<th data-hide="phone,tablet">Status</th>
																										<th>Action</th>

																									</tr>
																								</thead>

																								<tbody>
																									<c:forEach items="${vdcList}" var="user">
																										<tr>
																											<td><c:out value="${user.vdc_name}" />&nbsp;&nbsp;&nbsp;<a
																												style="color: #358;" id="${user.vdc_id}"
																												href="#" onClick="test(this);">usage</a></td>
																											<td><c:out
																													value="${user.vdclist.version}" /></td>
																											<td><c:out value="${user.vdc_location}" /></td>
																											<td><c:out
																													value="${user.vdclist.adminTenant}" /></td>
																											<td><c:out value="${user.created_at}" /></td>
																											<td><c:out
																													value="${user.vdclist.description}" /></td>
																											<!--  <td>
														  <div class="btn-group">
																<button class="btn btn-primary btn-sm dropdown-toggle"
																	    data-toggle="dropdown">
																	    Select <span class="caret"></span>
																</button>
																<ul class="dropdown-menu">
																	<li><a href="javascript:void(0);"
																		data-toggle="modal" data-target="#myModalO">Active</a>
																	</li>
																	<li><a href="javascript:void(0);"
																		data-toggle="modal" data-target="#myModalO">InActive</a>
																	</li>
																</ul>
															</div>
														 </td> -->
																											<c:choose>
																												<c:when test="${user.vdc_status=='Active'}">
																													<td><span class="label label-success">Active</span></td>
																												</c:when>
																												<c:when
																													test="${user.vdc_status=='Inactive'}">
																													<td><span class="label label-danger">Inactive</span></td>
																												</c:when>
																											</c:choose>
																											<td><a title="edit" id="${user.vdc_id}"
																												name="${user.vdc_name}"
																											onclick="getRowOpenstack1(this);"
																											href="#myModalEditOpenstack1"
																												data-toggle="modal"><i
																													class="fa fa-pencil"></i></a> 
																													
																													<a title="delete"
																												href="#myModalDeleteOpenstack1${user.vdc_id}"
																												data-toggle="modal"><i
																													class="fa fa-trash-o" onClick="del1()"
																													role="button"></i></a>

																												<div class="modal small fade"
																													id="myModalDeleteOpenstack${user.vdc_id}"
																													tabindex="-1" role="dialog"
																													aria-labelledby="myModalLabel"
																													aria-hidden="true">
																													<div class="modal-dialog">
																														<div class="modal-content">
																															<div class="modal-header">

																																<h4 class="modal-title">Delete
																																	Project confirmations !</h4>
																															</div>
																															<div class="modal-body">
																																<p>
																																	<i class="fa fa-warning"></i>Are you
																																	sure you want to delete the VDC
																																	:${user.vdc_name} ? <br>This
																																	cannot be undone.
																																</p>
																															</div>
																															<div class="modal-footer">
																																<button class="btn btn-default"
																																	data-dismiss="modal">Cancel</button>
																																<button class="btn btn-danger"
																																	onclick="deleteOpenstack1(this);"
																																	id="${user.vdc_id}">Delete</button>
																															</div>
																														</div>
																													</div>
																												</div></td>
																										</tr>
																									</c:forEach>
																								</tbody>
																							</table>
																						</div>
																						<!-- -- end table responsive -->
																					</div>
																					<!--end  widget content -->
																				</form>
																			</div>
																			<!-- end widget div-->
																		</div>
																	</article>




																	<article
																		class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
																		<!-- Widget ID (each widget will need unique ID)-->
																		<div class="jarviswidget jarviswidget-color-blueLight"
																			id="wid-id-1" data-widget-colorbutton="false"
																			data-widget-editbutton="false"
																			data-widget-togglebutton="false"
																			data-widget-deletebutton="false"
																			data-widget-fullscreenbutton="false"
																			data-widget-custombutton="true"
																			data-widget-sortable="false">
																			<header>
																				<span class="widget-icon"> <i
																					class="fa fa-table"></i>
																				</span>
																				<h2>Amazon VDC</h2>
																			</header>
                                                                         		
																			<!-- widget div -->
																			<div>
																				<!-- 	widget edit box -->
																				<div class="jarviswidget-editbox">
																					<!-- This area used as dropdown edit box -->
																				</div>
																				<!-- end widget edit box -->

																				<!-- widget content -->
																				<div class="widget-body no-padding">

																					<div class="table-responsive">
																						<table id="Amazon"
																							class="table table-striped table-bordered"
																							width="100%">
																							<thead>

																								<tr>
																									<th>Vdc Name</th>
																									<th data-hide="phone,tablet">Location</th>
																									<th data-class="expand">Tenant</th>
																									<th data-hide="phone">Registered At</th>
																									<th>Description</th>
																									<th data-hide="phone,tablet">Status</th>
																									<th>Action</th>


																								</tr>
																							</thead>

																							<tbody>
																								<%-- 
                                           <c:forEach  items="${list2}" var="user">
                                                      <tr>
                                                      <td><c:out value="${user.vdc_name}"/></td>
                                                      <c:choose>
                                                        <c:when test="${user.vdc_status=='Active'}">
                                                        <td> <span class="label label-success">Active</span></td>
                                                         </c:when>
                                                         <c:when test="${user.vdc_status=='Inactive'}">
                                                         <td> <span class="label label-danger">Inactive</span></td>
                                                         </c:when>
                                                         </c:choose>
                                                      <td><c:out value="${user.vdc_location}" /></td>
                                                      <td><c:out value="${user.tenant}" /></td>
                                                      <td><c:out value="${user.created_at}" /></td>
                                                      <td><c:out value="${user.description}" /></td>
                                                     <!--  <td>
														  <div class="btn-group">
																<button class="btn btn-primary btn-sm dropdown-toggle"
																	    data-toggle="dropdown">
																	    Select <span class="caret"></span>
																</button>
																<ul class="dropdown-menu">
																	<li><a href="javascript:void(0);"
																		data-toggle="modal" data-target="#myModalO">Active</a>
																	</li>
																	<li><a href="javascript:void(0);"
																		data-toggle="modal" data-target="#myModalO">InActive</a>
																	</li>
																</ul>
															</div>
														 </td> -->
														  <td>
														 <a title="edit"  href="#myModal1" data-toggle="modal"><i class="fa fa-pencil"></i></a>
                                                         <a title="delete" href="#myModal${user.vdc_name}" data-toggle="modal"><i class="fa fa-trash-o" onClick="del()" role="button"></i></a>
														
														
      									   <div class="modal small fade" id="myModal${user.vdc_name}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      												<div class="modal-dialog">
       											  <div class="modal-content">
          											 <div class="modal-header">
					
															<h4 class="modal-title">
															Delete Project confirmations !
															</h4>
														</div>
          										<div class="modal-body">
           											 <p><i class="fa fa-warning"></i>Are you sure you want to delete the VDC :${user.vdc_name} ?  <br>This cannot be undone.</p>
         											 </div>
            									<div class="modal-footer">
            									<button class="btn btn-default" data-dismiss="modal" >Cancel</button>
           										<button class="btn btn-danger" onclick="deleteProject(this.id);" id="${user.vdc_name}">Delete</button>
           										 </div>
         									 </div>
       										 </div>
     									</div>
														 </td>
													 </tr>
                                                 </c:forEach>  --%>

																							</tbody>
																						</table>
																					</div>
																				</div>
																				<!-- 	end widget content -->

																			</div>
																			<!-- 	end widget div -->

																		</div>
																		<!-- 	end widget -->
																	</article>


																	<article
																		class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

																		<!-- 	Widget ID (each widget will need unique ID) -->
																		<div class="jarviswidget jarviswidget-color-blueLight"
																			id="wid-id-1" data-widget-colorbutton="false"
																			data-widget-editbutton="false"
																			data-widget-togglebutton="false"
																			data-widget-deletebutton="false"
																			data-widget-fullscreenbutton="false"
																			data-widget-custombutton="true"
																			data-widget-sortable="false">
																			<header>
																				<span class="widget-icon"> <i
																					class="fa fa-table"></i>
																				</span>
																				<h2>VM Ware VDC</h2>

																			</header>

																			<!-- 	widget div -->
																			<div>

																				<!-- widget edit box -->
																				<div class="jarviswidget-editbox">
																					<!-- This area used as dropdown edit box  -->

																				</div>
																				<!-- end widget edit box -->

																				<!-- widget content -->
																				<div class="widget-body no-padding">

																					<table id="VMware"
																						class="table table-striped table-bordered"
																						height="100%" width="100%">
																						<thead>

																							<tr>
																								<th>Vdc Name</th>
																								<th data-hide="phone,tablet">Location</th>
																								<th data-class="expand">Tenant</th>
																								<th data-hide="phone">Registered At</th>
																								<th>Description</th>
																								<th data-hide="phone,tablet">Status</th>
																								<th>Action</th>


																							</tr>
																						</thead>

																						<tbody>
																							<%--  
                                          <c:forEach  items="${list3}" var="user">
                                                      <tr>
                                                      <td><c:out value="${user.vdc_name}"/></td>
                                                      
                                                      <td><c:out value="${user.vdc_location}" /></td>
                                                      <td><c:out value="${user.tenant}" /></td>
                                                      <td><c:out value="${user.created_at}" /></td>
                                                      <td><c:out value="${user.description}" /></td>
                                                     <!--  <td>
														  <div class="btn-group">
																<button class="btn btn-primary btn-sm dropdown-toggle"
																	    data-toggle="dropdown">
																	    Select <span class="caret"></span>
																</button>
																<ul class="dropdown-menu">
																	<li><a href="javascript:void(0);"
																		data-toggle="modal" data-target="#myModalO">Active</a>
																	</li>
																	<li><a href="javascript:void(0);"
																		data-toggle="modal" data-target="#myModalO">InActive</a>
																	</li>
																</ul>
															</div>
														 </td> -->
														 <c:choose>
                                                        <c:when test="${user.vdc_status=='Active'}">
                                                        <td> <span class="label label-success">Active</span></td>
                                                         </c:when>
                                                         <c:when test="${user.vdc_status=='Inactive'}">
                                                         <td> <span class="label label-danger">Inactive</span></td>
                                                         </c:when>
                                                         </c:choose>
														  <td>
														 <a title="edit"  href="#myModal1" data-toggle="modal"><i class="fa fa-pencil"></i></a>
                                                         <a title="delete" href="#myModal${user.vdc_name}" data-toggle="modal"><i class="fa fa-trash-o" onClick="del()" role="button"></i></a>
														
														
      									   <div class="modal small fade" id="myModal${user.vdc_name}" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
      												<div class="modal-dialog">
       											  <div class="modal-content">
          											 <div class="modal-header">
					
															<h4 class="modal-title">
															Delete Project confirmations !
															</h4>
														</div>
          										<div class="modal-body">
           											 <p><i class="fa fa-warning"></i>Are you sure you want to delete the VDC :${user.vdc_name} ?  <br>This cannot be undone.</p>
         											 </div>
            									<div class="modal-footer">
            									<button class="btn btn-default" data-dismiss="modal" >Cancel</button>
           										<button class="btn btn-danger" onclick="deleteProject(this);" id="${user.vdc_name}">Delete</button>
           										 </div>
         									 </div>
       										 </div>
     									</div>
														 </td>
													 </tr>
                                                 </c:forEach>  --%>
																						</tbody>
																					</table>

																				</div>
																				<!-- 	end widget content -->
																			</div>
																			<!-- end widget div -->
																		</div>
																		<!-- WIDGET END -->

																	</article>
																	
																	
															

																</div>
																<!-- end row -->

															</section>
															<!-- end widget grid -->
														</div>

													</div>
												</section>
												<!-- /.content -->

												<!-- Modal  POP Up Form Start -->
												<div class="modal fade" id="myModalO" tabindex="-1"
													role="dialog" aria-labelledby="myModalLabel"
													aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-hidden="true">&times;</button>
																<h4 class="modal-title" id="myModalLabel">
																	<b>Assigining Virtual Datacenter to Organization</b>
																</h4>
															</div>
															<div class="modal-body">
																<div class="row">
																	<div class="col-md-12">
																		<div class="form-group">
																			<label for="category"> Assign to Organization</label>
																			<select class="form-control" id="category">
																				<option>Organization1</option>
																				<option>Organization2</option>
																				<option>Organization3</option>
																			</select>
																		</div>
																	</div>
																</div>

																<div class="row">
																	<div class="col-md-12">
																		<div class="form-group">
																			<label for="category"> Tenant Admin</label> <select
																				class="form-control" id="category">
																				<option>Admin1</option>
																				<option>Admin2</option>
																				<option>Admin3</option>
																			</select>
																		</div>
																	</div>
																</div>

																<div class="row">
																	<div class="col-md-12">
																		<div class="form-group">
																			<label for="category"> Bussiness Admin</label> <select
																				class="form-control" id="category">
																				<option>Admin1</option>
																				<option>Admin2</option>
																				<option>Admin3</option>
																			</select>
																		</div>
																	</div>
																</div>

															</div>

															<div class="modal-footer">

																<button type="button" class="btn btn-primary">
																	Submit</button>
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Cancel</button>
															</div>
														</div>
														<!-- /.modal-content -->
													</div>
													<!-- /.modal-dialog -->
												</div>
												<!-- /.modal -->

												<!-- Modal  POP Up 1 Form Start for OPENSTACK-->
												<form action="insertVdc" id="openstack-insert-form"
													method="POST">
													<div class="modal fade" id="openstack" tabindex="-1"
														role="dialog" aria-labelledby="myModalLabel"
														aria-hidden="true">

														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-hidden="true">
																		&times;</button>
																	<h4 class="modal-title" id="myModalLabel">
																		<b> Register Open Stack </b>
																	</h4>
																</div>

																<div class="modal-body">
																	<!-- <form action="insert.html" class="smart-form" >	 -->
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> VDC Name</label> <input
																					type="text" name="vdc_name" id="vdc_name"
																					class="form-control" placeholder="VDC Name"
																					required /> <span id="statusvdcname"></span>

																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> VDC Location</label> <select
																					class="form-control" id="openstackLocation"
																					name="vdc_location" required>
																					<option value="0" selected>--Select
																						Location--</option>
																					<option value="1">Chennai</option>
																					<option value="2">Pune</option>
																					<option value="3">Mumbai</option>
																				</select>
																			</div>
																		</div>
																	</div>

																	<div class="row">
																		<div class="col-md-12">
																			<div class="form-group">
																				<label for="category"> VDC Description</label> <input
																					type="text" name="description" id="description"  class="form-control"
																					placeholder="Description" required>
																			</div>
																		</div>
																	</div>


																	<%-- <div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category">SCM Name</label> <select
																					class="form-control" id="scm_name" name="scm_name"
																					required>
																					<option selected=" " disabled=" ">--Select
																						SCM--</option>
																					<c:forEach items="${scmList}" var="scm">
																						<option>${scm.scm_name}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>

																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category">MONITOR Name</label> <select
																					class="form-control" id="mon_name" name="mon_name"
																					required>
																					<option selected=" " disabled=" ">--Select
																						MONITOR--</option>
																					<c:forEach items="${monitorList}" var="monitor">
																						<option>${monitor.mon_name}</option>
																					</c:forEach>
																				</select>
																			</div>
																		</div>
																	</div> --%>

																	<!-- <div class="row">
																		<div class="col-md-12">
																			<div class="form-group">
																				<label for="category">Subnet Address</label> <input
																					type="text" name="subnet_address"
																					id="subnet_address" class="form-control"
																					placeholder="Subnet Address" required /> <span
																					id="statussubnetaddress"></span>
																			</div>
																		</div>
																	</div> -->

																	<!-- <div class="row">
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category">Controller Endpoint</label> <input
																					type="text" name="controller_endpoint"
																					id="controller_endpoint" class="form-control"
																					placeholder="Controller Endpoint" required /> <span
																					id="constatus"></span>

																			</div>
																		</div> -->
																		<div class="row">
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category">Region</label> <input
																					type="text" name="region"
																					id="region" class="form-control"
																					placeholder="Region" required /> <span
																					id="constatus"></span>

																			</div>
																		</div>
																		
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category">Keystone Endpoint</label> <input
																					type="text" name="keystone_endpoint"
																					id="keystone_endpoint" class="form-control"
																					placeholder="Keystone Endpoint" required="true" />
																				<span id="status"></span>

																			</div>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category">Admin Tenant</label> <input
																					type="text" name="tenant" id="tenant"
																					class="form-control" placeholder="Admin Tenant"
																					required />
																			</div>
																		</div>
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category">Version</label> <select
																					class="form-control" id="openstackversion" name="version"
																					required>
																					<option  value="0">--Select
																						version--</option>
																					<option value="1">Havana</option>
																				</select>
																			</div>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category">Admin Username</label> <input
																					type="text" name="user" id="user"
																					class="form-control" placeholder="Username"
																					required />
																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category">Admin Password</label> <input
																					type="password" name="password" id="password"
																					class="form-control" placeholder="Password"
																					required />
																			</div>

																		</div>
																	</div>

																	<div class="modal-footer">

																		<button type="button" id="testConnection"
																			class="btn btn-primary">Test Connection</button>
																		<button type="submit" id="openStackRegister"
																			class="btn btn-primary" disabled="disabled">
																			Register</button>
																		<button type="button" id="openStackReset"
																			class="btn btn-primary">Reset</button>
																			<a class="btn btn-primary" href="register">Cancel</a>
																		<!-- <button  class="btn btn-primary" type="button" id="openstackClear" data-dismiss="modal">Cancel</button> -->
																		<!-- <button type="button" class="btn btn-default" data-dismiss="modal">
									Cancel
								</button> -->
																	</div>
																	<!-- </form>	 -->

																</div>
																<!-- -/.modal-body -->
															</div>
															<!-- /.modal-content -->
														</div>
														<!-- /.modal-dialog -->

													</div>
													<!-- /.modal -->
												</form>
												<!-- Modal  POP Up1 Form End for OPENSTACK -->

												<!-- Modal  POP Up 1 Edit Form Start for OPENSTACK-->
												<form action="editVdc" id="insert-form" method="POST">
													<div class="modal fade" id="myModalEditOpenstack"
														tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
														aria-hidden="true">

														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-hidden="true">
																		&times;</button>
																	<h4 class="modal-title" id="myModalLabel">
																		<b> Edit Open Stack </b>
																	</h4>
																</div>

																<div class="modal-body">


																	<input type="hidden" id="gvdc_id" name="vdc_id"
																		value=""> <input type="hidden" name="vdc_id"
																		id="vdc_id" />


																	<div class="row">
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category"> VDC Name</label> <input
																					type="text" name="vdc_name" id="vdcedit_name"
																					class="form-control" required /> <span
																					id="statusvdceditname"></span>

																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> VDC Description</label> <input
																					type="text" name="description" id="description"
																					class="form-control" />
																			</div>
																		</div>
																	</div>

																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> Location</label> <select
																					class="form-control" id="vdc_location"
																					name="vdc_location" selected="">
																					<option>Chennai</option>
																					<option>Pune</option>
																					<option>Mumbai</option>
																				</select>
																			</div>
																		</div>

																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> Status</label> <select
																					class="form-control" id="vdc_status"
																					name="vdc_status" selected="">
																					<option>Active</option>
																					<option>Inactive</option>
																				</select>
																			</div>
																		</div>
																	</div>
																	<!-- 	
							<div class="row">
									<div class="col-md-12">
									<label for="AdminTenant">Admin Tenant</label>
										<div class="form-group">
											<input type="text" name="tenant" id="tenant" class="form-control" required />
										</div>
									</div>
								 <div class="col-md-6">
								   <label for="AdminUser">Username</label>
										<div class="form-group">
											<input type="text" name="username" id="user_name" class="form-control"  required />
										</div>
									</div>
									<div class="col-md-6">
									<label for="AdminPassword">Password</label>
										<div class="form-group">
											<input type="password" name="password" class="form-control" placeholder="*****" required />
										</div> 
								</div>
							</div>	 -->

																	<div class="modal-footer">

																		<!-- <button type="button" class="btn btn-primary">
									Test Connection
								</button> -->
																		<button type="submit" class="btn btn-primary">
																			Save Changes</button>
																		<button type="button" class="btn btn-default"
																			data-dismiss="modal">Cancel</button>
																	</div>


																</div>
																<!-- -/.modal-body -->
															</div>
															<!-- /.modal-content -->
														</div>
														<!-- /.modal-dialog -->

													</div>
													<!-- /.modal -->
												</form>
												<!-- Modal  POP Up1 Edit Form End for OPENSTACK -->

												<!-- Modal  POP Up 2 Form Start for AWS -->
												<div class="modal fade" id="AWS" tabindex="-1" role="dialog"
													aria-labelledby="myModalLabel" aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-hidden="true">&times;</button>
																<h4 class="modal-title" id="myModalLabel">
																	<b> Register Amazon </b>
																</h4>
															</div>
															<div class="modal-body">
																<div class="row">
																	<div class="col-md-12">
																		<label for="category">VDC Name</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="VDC Name" required />
																		</div>
																		<label for="category">VDC Description</label>
																		<div class="form-group">
																			<textarea class="form-control"
																				placeholder="Description" rows="5" required></textarea>
																		</div>
																	</div>
																</div>

																<div class="row">
																	<div class="col-md-12">
																		<div class="form-group">
																			<label for="category"> Location</label> <select
																				class="form-control" id="category">
																				<option>Chennai</option>
																				<option>Pune</option>
																				<option>Mumbai</option>
																			</select>
																		</div>
																	</div>
																</div>

																<div class="row">
																	<div class="col-md-12">
																		<label for="SubnetAddress">Subnet Address</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="0.0.0.0/0" required />
																		</div>
																		<label for="AccessKey">Access Key</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="Enter ..." required />
																		</div>

																		<label for="Secretkey">Secret key</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="Enter ..." required />
																		</div>
																		<label for="Region">Region</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="Enter ..." required />
																		</div>

																	</div>
																</div>
															</div>


															<div class="modal-footer">
																<button type="button" class="btn btn-primary">
																	Test Connection</button>
																<button type="button" class="btn btn-primary">
																	Register</button>

																<button type="button" class="btn btn-default"
																	data-dismiss="modal">cancel</button>
															</div>

														</div>
														<!-- /.modal-content -->
													</div>
													<!-- /.modal-dialog -->
												</div>
												<!-- /.modal -->

												<!-- Modal  POP Up2 Form End for AWS -->



												<!-- Modal  POP Up 2 Form Start for VM Ware -->

												<div class="modal fade" id="VMWare" tabindex="-1"
													role="dialog" aria-labelledby="myModalLabel"
													aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-hidden="true">&times;</button>
																<h4 class="modal-title" id="myModalLabel">
																	<b> Register VMware </b>
																</h4>
															</div>
															<div class="modal-body">
																<div class="row">
																	<div class="col-md-12">
																		<label for="category">VDC Name</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="VDC Name" required />
																		</div>
																		<label for="category">VDC Description</label>
																		<div class="form-group">
																			<textarea class="form-control"
																				placeholder="Description" rows="5" required></textarea>
																		</div>
																	</div>
																</div>

																<div class="row">
																	<div class="col-md-12">
																		<div class="form-group">
																			<label for="category"> Location</label> <select
																				class="form-control" id="category">
																				<option>Chennai</option>
																				<option>Pune</option>
																				<option>Mumbai</option>
																			</select>
																		</div>
																	</div>
																</div>

																<div class="row">
																	<div class="col-md-12">
																		<label for="SubnetAddress">Subnet Address</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="0.0.0.0/0" required />
																		</div>
																		<label for="VcenterserverIP">Vcenter server
																			IP/fqdn</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="Enter ..." required />
																		</div>

																		<label for="Username ">Username</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="Enter ..." required />
																		</div>
																		<label for="Password">Password</label>
																		<div class="form-group">
																			<input type="text" class="form-control"
																				placeholder="Enter ..." required />
																		</div>
																	</div>
																</div>
															</div>

															<div class="modal-footer">
																<button type="button" class="btn btn-primary">
																	Test Connection</button>
																<button type="button" class="btn btn-primary">
																	Register</button>
																<button type="button" class="btn btn-default"
																	data-dismiss="modal">Cancel</button>
															</div>
														</div>
														<!-- /.modal-content -->
													</div>
													<!-- /.modal-dialog -->
												</div>
												<!-- /.modal -->

												<!-- Modal  POP Up3Form End for VM Ware -->
												
												<!-- Modal  POP Up 2 Form Start for VM Ware -->
                                            <div class="modal fade" id="Azure" tabindex="-1"
													role="dialog" aria-labelledby="myModalLabel"
													aria-hidden="true">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-hidden="true">&times;</button>
																<h4 class="modal-title" id="myModalLabel">
																	<b> Register Azure </b>
																</h4>
															</div>
													<div class="modal-body">
																	<!-- <form action="insert.html" class="smart-form" >	 -->
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> VDC Name</label> <input
																					type="text" name="azureVdcName" id="azureVdcName"
																					class="form-control" placeholder="Vdc Name"
																					required /> <span id="statusAzureVdcName"></span>

																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> VDC Location</label> <select
																					class="form-control" id="azureLocation"
																					name="vdc_location" required>
																					<option value="0" selected>--Select
																						Location--</option>
																					<option value="3">NA</option>
																					<option value="1">Chennai</option>
																					<option value="2">Pune</option>
																					<option value="3">Mumbai</option>
																					
																				</select>
																			</div>
																		</div>
																	</div>

																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> VDC Description</label> <input
																					type="text" name="descriptionInAzure" id="descriptionInAzure"  class="form-control"
																					placeholder="Description" required>
																			</div>
																		</div>
																	
																		
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category">Subscription Id</label> <input
																					type="text" name="subscriptionId"
																					id="subscriptionId" class="form-control"
																					placeholder="subscription Id" required /> <span
																					id="azureStatus"></span>

																			</div>
																		</div>
																		</div>
																		<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category">Account Username</label> <input
																					type="text" name="acoountUserName" id="acoountUserName"  class="form-control"
																					placeholder="Account Username" required>
																			</div>
																		</div>
																	
																		
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category">Account Password</label> <input
																					type="text" name="AccountPassword"
																					id="AccountPassword" class="form-control"
																					placeholder="Account Password" required /> 
																			</div>
																		</div>
																		</div>
																		<div class="row">
																				<div class="col-md-6">
																						<div class="widget-body no-padding smart-form">
																						<fieldset>
																			
																			<label for="file" class="input input-file">Public Setting File </label>
																			<div class="input input-file">
																				<span class="button"><input type="file" id="keystoneInAzure" name="file" >Browse</span>
																				<input type="text" placeholder="Select public setting file" readonly=""/>
																			</div>
																			
																			</fieldset>
																			</div>
																			
																		</div>
																	</div>
																	<div class="modal-footer">

																		<button type="button" id="testConnection"
																			class="btn btn-primary">Test Connection</button>
																		<button type="submit" id="azureRegister"
																			class="btn btn-primary" disabled="disabled">
																			Register</button>
																		<button type="button" id="azureReset"
																			class="btn btn-primary">Reset</button>
																			<a class="btn btn-primary" href="register">Cancel</a>
																		<!-- <button  class="btn btn-primary" type="button" id="openstackClear" data-dismiss="modal">Cancel</button> -->
																		<!-- <button type="button" class="btn btn-default" data-dismiss="modal">
									Cancel
								</button> -->
																	</div>
																	<!-- </form>	 -->
																	
																		<!-- <div class="widget-body no-padding smart-form">

											<fieldset>
												<div class="row">
													<section class="col col-4">
														<label class="label">BluePrint Name</label> <label
															class="input"> <input type="text"
															name="azureVmName" id="azureVmName">
														</label>
													</section>
													<section class="col col-4">
														<label class="label">BluePrint Description</label> <label
															class="input"> <input type="text"
															name="azureVmNameDesc" id="azureVmNameDesc">
														</label>
													</section>

														<section class="col col-4">
													<label class="label">Logo</label>
													<div class="input input-file">
														<span class="button"><input type="file" id="file" name="file">Browse</span><input type="text" placeholder="add Logo" readonly="">
													</div>
												</section>
												
												</div>
												<div class="row">
													<section class="col col-4">
														<label class="label">Public</label> <input type="checkbox"
															name="publicCheckbox" id="publicCheckbox"
															onclick="visiblity()">

													</section>
													<section class="col col-4" id="enable1">
														<label class="label">Visible To</label> <select multiple
															style="width: 100%" class="select2">
															<optgroup label="Public Users">
																<option value="User1">Public User2</option>
																<option value="User2">Public User3</option>
															</optgroup>
															<optgroup label="Private Users">
																<option value="User3">Private User4</option>
																<option value="User4" selected="selected">Public
																	User1</option>
																<option value="User5">Private User5</option>
															</optgroup>


														</select>
													</section>
												</div>

											</fieldset>

											<footer>
												<button type="submit" class="btn btn-primary">Save Template</button>
											</footer>

										</div> -->

																</div>
																<!-- -/.modal-body -->
												<!-- /.modal -->
												<!-- Modal  POP Up 1 Form Start for OPENSTACK-->
												<!-- <form action="insertAzureVdc" id="azure-insert-form"
													method="POST">
													<div class="modal fade" id="azure" tabindex="-1"
														role="dialog" aria-labelledby="myModalLabel"
														aria-hidden="true">

														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-hidden="true">
																		&times;</button>
																	<h4 class="modal-title" id="myModalLabel">
																		<b> Register Azure </b>
																	</h4>
																</div>

																<div class="modal-body">
																	<form action="insert.html" class="smart-form" >	
																	<div class="row">
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> VDC Name</label> <input
																					type="text" name="azureVdcName" id="azureVdcName"
																					class="form-control" placeholder="VdcName"
																					required /> <span id="statusAzureVdcName"></span>

																			</div>
																		</div>
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category"> VDC Location</label> <select
																					class="form-control" id="azureLocation"
																					name="vdc_location" required>
																					<option value="0" selected>--Select
																						Location--</option>
																					<option value="1">Chennai</option>
																					<option value="2">Pune</option>
																					<option value="3">Mumbai</option>
																				</select>
																			</div>
																		</div>
																	</div>

																	<div class="row">
																		<div class="col-md-12">
																			<div class="form-group">
																				<label for="category"> VDC Description</label> <input
																					type="text" name="descriptionInAzure" id="descriptionInAzure"  class="form-control"
																					placeholder="Description" required>
																			</div>
																		</div>
																	</div>
																		<div class="row">
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category">Subscription</label> <input
																					type="text" name="subscription"
																					id="subscription" class="form-control"
																					placeholder="Region" required /> <span
																					id="azureStatus"></span>

																			</div>
																		</div>
																		
																		<div class="col-md-6">
																			<div class="form-group">
																				<label for="category">Keystone </label> <input
																					type="file" name="keystoneInAzure"
																					id="keystoneInAzure" class="form-control"
																					placeholder="keystoneInAzure" required="true" />
																				<span id="keystoneStatus"></span>

																			</div>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-6">

																			<div class="form-group">
																				<label for="category">Keystone Password</label> <input
																					type="text" name="keystonePassword" id="keystonePassword"
																					class="form-control" placeholder="keystonePassword"
																					required />
																			</div>
																		</div>
																		
																	</div>
																	<div class="modal-footer">

																		<button type="button" id="testConnection"
																			class="btn btn-primary">Test Connection</button>
																		<button type="submit" id="azureRegister"
																			class="btn btn-primary" disabled="disabled">
																			Register</button>
																		<button type="button" id="azureReset"
																			class="btn btn-primary">Reset</button>
																			<a class="btn btn-primary" href="register">Cancel</a>
																		<button  class="btn btn-primary" type="button" id="openstackClear" data-dismiss="modal">Cancel</button>
																		<button type="button" class="btn btn-default" data-dismiss="modal">
									Cancel
								</button>
																	</div>
																	</form>	

																</div>
																-/.modal-body
															</div>
															/.modal-content
														</div>
														/.modal-dialog

													</div>
													/.modal
												</form> -->
												
											</div>
										</div>

										<div class="tab-pane fade " id="hr2">

											<div id="main" role="main">


												<!-- Main content -->
												<section class="content">

													<div class="row">
														<div class="col-xs-12" style="margin-left: 0;">
															<div class="box">
																<div class="box-body table-responsive">

																	<div>
																		<p style="color: blue">Click images to register to
																			SCM</p>
																	</div>
																	<br>

																	<ul id="external-events" class="list-unstyled">
																		<li><span> <a href="javascript:void(0);"
																				data-toggle="modal" data-target="#puppetModal"
																				class="btn btn-primary btn-xs txt-color-white"><i
																					class="fa fa-gear fa-2x fa-spin"></i></a> <img
																				width="100" height="40" alt="Puppetlogo"
																				src="img/isapimg/puppet.png"></img>
																		</span></li>

																		<li><span> <a href="javascript:void(0);"
																				data-toggle="modal" data-target="#chefModal"
																				class="btn btn-primary btn-xs txt-color-white"><i
																					class="fa fa-gear fa-2x fa-spin"></i></a> <img
																				width="100" height="40" alt="Cheflogo"
																				src="img/isapimg/chef.png"></img>
																		</span></li>


																	</ul>
																</div>
																<!-- /.box-body -->
															</div>
															<!-- /.box -->

															<!-- widget grid -->
															<section id="widget-grid" class="">
																<!-- row -->
																<div class="row">
																	<!-- NEW WIDGET START -->
																	<article
																		class="col-xs-12 col-sm-12 col-md-12 col-lg-12">

																		<form name="editForm">

																			<input type="hidden" name="scm_id" id="scm_id" />
																			<!-- Widget ID (each widget will need unique ID)-->
																			<div
																				class="jarviswidget jarviswidget-color-blueLight"
																				id="wid-id-1" data-widget-colorbutton="false"
																				data-widget-editbutton="false"
																				data-widget-togglebutton="false"
																				data-widget-deletebutton="false"
																				data-widget-fullscreenbutton="false"
																				data-widget-custombutton="true"
																				data-widget-sortable="false">

																				<header>
																					<span class="widget-icon"> <i
																						class="fa fa-table"></i>
																					</span>
																					<h2>SCM</h2>
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

																						<div class="table-responsive">

																							<table id="scmbasic"
																								class="table table-striped table-bordered table-hover"
																								width="100%">
																								<thead>
																									<tr>

																										<th>SCM Name</th>
																										<th>Type</th>
																										<th>HostName(FQDN)</th>
																										<th>VDC</th>

																										<th>Status</th>
																										<th>Action</th>

																									</tr>
																								</thead>

																								<tbody>
																									<c:forEach items="${scmList}" var="scm">
																										<tr>

																											<td><c:out value="${scm.scm_name}" /></td>
																											<td><c:out value="${scm.scm_type}" /></td>
																											<td><c:out value="${scm.host_name}" /></td>
																											<td><c:choose>
																													<c:when test='${empty scm.vdc}'>
																														<c:out value="-">
																														</c:out>
																													</c:when>
																													<c:otherwise>
																														<c:forEach items="${scm.vdc}" var="vdc">
                                                       ${vdc.vdc_name}<br>
																														</c:forEach>
																													</c:otherwise>

																												</c:choose></td>


																											<td><c:out value="Registered" /></td>

																											<td style="padding-left: 3%;"><a
																												title="edit" id="${scm.scm_id}"
																												onclick="getRow(this);" href="#editscm"
																												data-toggle="modal"><i
																													class="fa fa-pencil"></i></a> <a title="delete"
																												href="#myModal${scm.scm_id}" role="button"
																												data-toggle="modal"><i
																													class="fa fa-trash-o"></i></a> <!-- <a
																												class="btn btn-info btn-xs" href="view.jsp">Info</a> -->

																												<div class="modal small fade"
																													id="myModal${scm.scm_id}" tabindex="-1"
																													role="dialog"
																													aria-labelledby="myModalLabel"
																													aria-hidden="true">
																													<div class="modal-dialog">
																														<div class="modal-content">
																															<div class="modal-header">

																																<h4 class="modal-title">Delete scm
																																	confirmations !</h4>
																															</div>
																															<div class="modal-body">
																																<p>
																																	<i class="fa fa-warning"></i>Are you
																																	sure you want to delete the SCM :
																																	${scm.scm_name} ? <br>This cannot
																																	be undone.
																																</p>
																															</div>
																															<div class="modal-footer">
																																<button class="btn btn-default"
																																	data-dismiss="modal">Cancel</button>
																																<button class="btn btn-danger"
																																	onclick="deleteScm(this.id)"
																																	id="${scm.scm_id}">Delete</button>
																															</div>
																														</div>
																													</div>
																												</div></td>

																										</tr>
																									</c:forEach>
																								</tbody>
																							</table>
																						</div>
																						<!-- -- end table responsive -->
																					</div>
																					<!--end  widget content -->
																				</div>
																				<!-- end widget div-->

																			</div>
																			<!-- end widget ID -->





																		</form>
																	</article>
																	<!-- WIDGET END -->
																</div>
																<!-- end row -->
															</section>
															<!-- end widget grid -->
														</div>
													</div>
												</section>
												<!-- /.content -->


												<!-- Modal  POP Up 1 Form Start for scmedit-->
												<div class="modal fade" id="editscm" tabindex="-1"
													role="dialog">
													<div class="modal-dialog">
														<div class="modal-content">
															<div class="modal-header">
																<button type="button" class="close" data-dismiss="modal"
																	aria-hidden="true">&times;</button>
																<h4 class="modal-title">Edit SCM</h4>
															</div>
															<div class="modal-body no-padding">
																<form action="updateScm" id="edit-proj-form"
																	class="smart-form" method="POST">

																	<input type="hidden" id="edit_scm_id" name="scm_id"
																		value="">
																	<fieldset>
																		<section>
																			<div class="row">
																				<label class="label col col-2">SCM Name</label>
																				<div class="col col-10">
																					<label class="input"> <input type="text"
																						name="scm_name" id="edit_scm_name" required>
																						<span id="editstatusscmname"></span>

																					</label>
																				</div>
																			</div>
																		</section>
																		<section>
																			<div class="row">
																				<label class="label col col-2">End Point</label>
																				<div class="col col-10">
																					<label class="input"> <input type="text"
																						name="scm_endpoint" id="edit_scm_endpoint"
																						required> <span id="editstatusscm"></span>

																					</label>

																				</div>
																			</div>
																		</section>
																		<section>
																			<div class="row">
																				<label class="label col col-2">HostName</label>
																				<div class="col col-10">
																					<label class="input"> <input type="text"
																						id="edit_host_name" name="host_name" required>
																					</label>
																				</div>
																			</div>
																		</section>

																		<section>
																			<div class="row">
																				<label class="label col col-2">Admin
																					username</label>
																				<div class="col col-10">
																					<label class="input"> <input type="text"
																						id="edit_admin_user" name="admin_user" required>
																					</label>
																				</div>
																			</div>
																		</section>

																		<section>
																			<div class="row">
																				<label class="label col col-2">Admin
																					password</label>
																				<div class="col col-10">
																					<label class="input"> <input
																						type="password" id="edit_admin_pass"
																						name="admin_pass" required>
																					</label>
																				</div>
																			</div>
																		</section>



																	</fieldset>
																	<footer>
																		<button type="submit" class="btn btn-primary">
																			Save Changes</button>
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
												<!-- Modal  POP Up Form End for scmedit-->

												<!-- Modal  POP Up 1 Form Start for Puppet-->
												<form action="addpuppet" id="insert-form" method="POST">
													<div class="modal fade" id="puppetModal" tabindex="-1"
														role="dialog" aria-labelledby="myModalLabel"
														aria-hidden="true">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-hidden="true">
																		&times;</button>
																	<h4 class="modal-title" id="myModalLabel">
																		<b> Register Puppet </b>
																	</h4>
																</div>
																<div class="modal-body">

																	<div class="row">
																		<div class="col-md-12">
																			<label for="category">Name</label>
																			<div class="form-group">
																				<input type="text" name="scm_name"
																					id="puppetscm_name" class="form-control"
																					placeholder="Name" required /> <span
																					id="statuspuppetscmname"></span>
																			</div>
																		</div>
																	</div>
																	<div class="row">
																		<div class="col-md-12">
																			<label for="ControllerEndPoint">EndPoint</label>
																			<div class="form-group">
																				<input type="text" name="scm_endpoint"
																					id="scm_endpoint" class="form-control"
																					placeholder="0.0.0.0" required="true" /> <span
																					id="statusscm"></span>
																				<div id="suggestscmname"></div>
																			</div>

																			<label for="HostName">HostName</label>
																			<div class="form-group">
																				<input type="text" name="host_name"
																					class="form-control"
																					placeholder="Fully Qualified Domain Name" required />
																			</div>
																			<label for="AdminUser">Admin User Name</label>
																			<div class="form-group">
																				<input type="text" name="admin_user"
																					class="form-control" placeholder="username"
																					required />
																			</div>
																			<label for="AdminPassword">Admin Password</label>
																			<div class="form-group">
																				<input type="password" name="admin_pass"
																					class="form-control" placeholder="password"
																					required />
																			</div>
																		</div>
																	</div>

																	<div class="modal-footer">
																		<button type="button" class="btn btn-primary">
																			Test Connection</button>
																		<button type="submit" class="btn btn-primary">
																			Register</button>
																		<button type="button" class="btn btn-default"
																			data-dismiss="modal">Cancel</button>
																	</div>

																</div>
																<!-- -/.modal-body -->
															</div>
															<!-- /.modal-content -->
														</div>
														<!-- /.modal-dialog -->

													</div>
													<!-- /.modal -->
												</form>
												<!-- Modal  POP Up Form End for puppet-->

												<!-- Modal  POP Up  Form Start for Chef-->
												<form action="addchef" id="insert-form" method="POST">
													<div class="modal fade" id="chefModal" tabindex="-1"
														role="dialog" aria-labelledby="myModalLabel"
														aria-hidden="true">
														<div class="modal-dialog">
															<div class="modal-content">
																<div class="modal-header">
																	<button type="button" class="close"
																		data-dismiss="modal" aria-hidden="true">
																		&times;</button>
																	<h4 class="modal-title" id="myModalLabel">
																		<b> Register Chef </b>
																	</h4>
																</div>

																<div class="modal-body">

																	<div class="row">
																		<div class="col-md-12">
																			<label for="category">Name</label>
																			<div class="form-group">
																				<input type="text" name="scm_name" id="chef_name"
																					class="form-control" placeholder="Name" required />
																				<span id="statuschefscmname"></span>
																			</div>
																		</div>
																	</div>

																	<div class="row">
																		<div class="col-md-12">

																			<label for="ControllerEndPoint">EndPoint</label>
																			<div class="form-group">
																				<input type="text" name="scm_endpoint"
																					id="chef_scm_endpoint" class="form-control"
																					placeholder="0.0.0.0" required /> <span
																					id="chefstatus"></span>

																			</div>

																			<label for="HostName">HostName</label>
																			<div class="form-group">
																				<input type="text" name="host_name"
																					class="form-control"
																					placeholder="Fully Qualified Domain Name" required />
																			</div>

																			<label for="AdminUser">Admin User Name</label>
																			<div class="form-group">
																				<input type="text" name="admin_user"
																					class="form-control" placeholder="username"
																					required />
																			</div>
																			<label for="AdminPassword">Admin Password</label>
																			<div class="form-group">
																				<input type="password" name="password"
																					class="form-control" placeholder="password"
																					required />
																			</div>
																		</div>
																	</div>
																	<div class="modal-footer">
																		<button type="button" class="btn btn-primary">
																			Test Connection</button>
																		<button type="submit" class="btn btn-primary">
																			Register</button>
																		<button type="button" class="btn btn-default"
																			data-dismiss="modal">Cancel</button>
																	</div>
												</form>
											</div>
											<!-- -/.modal-body -->
										</div>
										<!-- /.modal-content -->
									</div>
									<!-- /.modal-dialog -->

								<!-- </div> -->
								<!-- /.modal -->

								<!-- Modal  POP Up1 Form End for puppet-->
							</div>

						</div>

						<div class="tab-pane fade" id="hr3">
							<div id="main" role="main">


								<!-- Main content -->
								<section class="content">

									<div class="row">
										<div class="col-xs-12" style="margin-left: 0;">
											<div class="box">
												<div class="box-body table-responsive">

													<div>
														<p style="color: blue">Click images to register to
															MONITOR</p>
													</div>
													<br>

													<ul id="external-events" class="list-unstyled">
														<li><span> <a href="javascript:void(0);"
																data-toggle="modal" data-target="#NagiosModal"
																class="btn btn-primary btn-xs txt-color-white"><i
																	class="fa fa-gear fa-2x fa-spin"></i></a> <img width="100"
																height="40" alt="Nagioslogo"
																src="img/isapimg/nagioslogo.png"></img>
														</span></li>

														<li><span> <a href="javascript:void(0);"
																data-toggle="modal" data-target="#ZabixModal"
																class="btn btn-primary btn-xs txt-color-white"><i
																	class="fa fa-gear fa-2x fa-spin"></i></a> <img width="100"
																height="40" alt="Zabbixlogo"
																src="img/isapimg/zabbixlogo.png"></img>
														</span></li>
													</ul>
												</div>
												<!-- /.box-body -->
											</div>
											<!-- /.box -->

											<!-- widget grid -->
											<section id="widget-grid" class="">
												<!-- row -->
												<div class="row">
													<!-- NEW WIDGET START -->
													<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
														<form name="monitoreditForm">
															<input type="hidden" name="mon_id" id="mon_id" />
															<!-- Widget ID (each widget will need unique ID)-->
															<div class="jarviswidget jarviswidget-color-blueLight"
																id="wid-id-1" data-widget-colorbutton="false"
																data-widget-editbutton="false"
																data-widget-togglebutton="false"
																data-widget-deletebutton="false"
																data-widget-fullscreenbutton="false"
																data-widget-custombutton="true"
																data-widget-sortable="false">
																<header>
																	<span class="widget-icon"> <i
																		class="fa fa-table"></i>
																	</span>
																	<h2>MONITOR</h2>
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
																		<div class="table-responsive">
																			<table id="monbasic"
																				class="table table-striped table-bordered table-hover"
																				width="100%">
																				<thead>
																					<tr>
																						<th>Monitor Name</th>
																						<th>Type</th>
																						<th>HostName(FQDM)</th>
																						<th>VDC</th>
																						<th>Status</th>
																						<th>Action</th>
																					</tr>
																				</thead>
																				<tbody>
																					<c:forEach items="${monitorList}" var="monitor">
																						<tr>
																							<td><c:out value="${monitor.mon_name}" /></td>
																							<td><c:out value="${monitor.mon_type}" /></td>
																							<td><c:out value="${monitor.host_name}" /></td>
																							<td><c:choose>
																									<c:when test='${empty monitor.vdc}'>
																										<c:out value="-">
																										</c:out>
																									</c:when>
																									<c:otherwise>
																										<c:forEach items="${monitor.vdc}"
																											var="monitorvdc">
                                                       ${monitorvdc.vdc_name}<br>
																										</c:forEach>
																									</c:otherwise>
																								</c:choose></td>
																							<td><c:out value="Registered" /></td>
																							<td style="padding-left: 3%;"><a
																								title="edit" id="${monitor.mon_id}"
																								onclick="getMonitorRow(this);"
																								href="#editmonitor" data-toggle="modal"><i
																									class="fa fa-pencil"></i></a> <a title="delete"
																								href="#myMonitorModal${monitor.mon_id}"
																								role="button" data-toggle="modal"><i
																									class="fa fa-trash-o"></i></a>
																								<div class="modal small fade"
																									id="myMonitorModal${monitor.mon_id}"
																									tabindex="-1" role="dialog"
																									aria-labelledby="myModalLabel"
																									aria-hidden="true">
																									<div class="modal-dialog">
																										<div class="modal-content">
																											<div class="modal-header">
																												<h4 class="modal-title">Delete Monitor
																													confirmations !</h4>
																											</div>
																											<div class="modal-body">
																												<p>
																													<i class="fa fa-warning"></i>Are you sure
																													you want to delete the MONITOR :
																													${monitor.mon_name} ? <br>This cannot
																													be undone.
																												</p>
																											</div>
																											<div class="modal-footer">
																												<button class="btn btn-default"
																													data-dismiss="modal">Cancel</button>
																												<button class="btn btn-danger"
																													onclick="deleteMonitor(this.id)"
																													id="${monitor.mon_id}">Delete</button>
																											</div>
																										</div>
																									</div>
																								</div></td>
																						</tr>
																					</c:forEach>
																				</tbody>
																			</table>
																		</div>
																		<!-- -- end table responsive -->
																	</div>
																	<!--end  widget content -->
																</div>
																<!-- end widget div-->

															</div>
															<!-- end widget ID -->
														</form>
													</article>
													<!-- WIDGET END -->
												</div>
												<!-- end row -->
											</section>
											<!-- end widget grid -->
										</div>
									</div>
								</section>
								<!-- /.content -->


								<!-- Modal  POP Up 1 Form Start for monitoredit-->
								<div class="modal fade" id="editmonitor" tabindex="-1"
									role="dialog">
									<div class="modal-dialog">
										<div class="modal-content">
											<div class="modal-header">
												<button type="button" class="close" data-dismiss="modal"
													aria-hidden="true">&times;</button>
												<h4 class="modal-title">Edit MONITOR</h4>
											</div>
											<div class="modal-body no-padding">
												<form action="updateMonitor" id="edit-mon-form"
													class="smart-form" method="POST">
													<input type="hidden" id="cmon_id" name="mon_id" value="">
													<fieldset>
														<section>
															<div class="row">
																<label class="label col col-2">Monitor Name</label>
																<div class="col col-10">
																	<label class="input"> <input type="text"
																		name="mon_name" id="edit_mon_name" required> <span
																		id="editnamestatus"></span>

																	</label>
																</div>
															</div>
														</section>
														<section>
															<div class="row">
																<label class="label col col-2">End Point</label>
																<div class="col col-10">
																	<label class="input"> <input type="text"
																		name="mon_endpoint" id="edit_mon_endpoint" required>
																		<span id="editstatus"></span>

																	</label>

																</div>
															</div>
														</section>
														<section>
															<div class="row">
																<label class="label col col-2">HostName</label>
																<div class="col col-10">
																	<label class="input"> <input type="text"
																		id="host_name" name="host_name" required>
																	</label>
																</div>
															</div>
														</section>

														<section>
															<div class="row">
																<label class="label col col-2">Admin username</label>
																<div class="col col-10">
																	<label class="input"> <input type="text"
																		id="admin_user" name="admin_user" required>
																	</label>
																</div>
															</div>
														</section>

														<section>
															<div class="row">
																<label class="label col col-2">Admin password</label>
																<div class="col col-10">
																	<label class="input"> <input type="password"
																		id="admin_pass" name="admin_pass" required>
																	</label>
																</div>
															</div>
														</section>
													</fieldset>
													<footer>
														<button type="submit" class="btn btn-primary">
															Save Changes</button>
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
								<!-- Modal  POP Up Form End for monitor edit-->

								<!-- Modal  POP Up 1 Form Start for Nagios--->
								<form action="addnagios" id="insert-form" method="POST">
									<div class="modal fade" id="NagiosModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">

										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">
														<b> Register Nagios </b>
													</h4>
												</div>

												<div class="modal-body">

													<div class="row">
														<div class="col-md-12">
															<label for="category">Name</label>
															<div class="form-group">
																<input type="text" name="mon_name" id="nag_name"
																	class="form-control" placeholder="Name" required /> <span
																	id="nagnamestatus"></span>
															</div>
														</div>
													</div>

													<div class="row">
														<div class="col-md-12">

															<label for="ControllerEndPoint">EndPoint</label>
															<div class="form-group">
																<input type="text" name="mon_endpoint"
																	id="nag_mon_endpoint" class="form-control"
																	placeholder="0.0.0.0" required /> <span id="nagstatus"></span>
															</div>
															<label for="HostName">HostName</label>
															<div class="form-group">
																<input type="text" name="host_name" class="form-control"
																	placeholder="Fully Qualified Domai Name" required />
															</div>

															<label for="AdminUser">Admin User Name</label>
															<div class="form-group">
																<input type="text" name="admin_user"
																	class="form-control" placeholder="username" required />
															</div>
															<label for="AdminPassword">Admin Password</label>
															<div class="form-group">
																<input type="password" name="admin_pass"
																	class="form-control" placeholder="password" required />
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-primary">
															Test Connection</button>
														<button type="submit" class="btn btn-primary">
															Register</button>
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Cancel</button>
													</div>


												</div>
												<!-- -/.modal-body -->
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->

									</div>
									<!-- /.modal -->
								</form>
								<!-- Modal  POP Up Form End for Nagios-->


								<!-- Modal  POP Up  Form Start for  Zabbix-->
								<form action="addzabbix" id="insert-form" method="POST">
									<div class="modal fade" id="ZabixModal" tabindex="-1"
										role="dialog" aria-labelledby="myModalLabel"
										aria-hidden="true">

										<div class="modal-dialog">
											<div class="modal-content">
												<div class="modal-header">
													<button type="button" class="close" data-dismiss="modal"
														aria-hidden="true">&times;</button>
													<h4 class="modal-title" id="myModalLabel">
														<b> Register Zabbix</b>
													</h4>
												</div>

												<div class="modal-body">

													<div class="row">
														<div class="col-md-12">
															<label for="category">Name</label>
															<div class="form-group">
																<input type="text" name="mon_name" id="zab_name"
																	class="form-control" placeholder="Name" required /> <span
																	id="zabnamestatus"></span>
															</div>
														</div>
													</div>
													<div class="row">
														<div class="col-md-12">

															<label for="ControllerEndPoint">EndPoint</label>
															<div class="form-group">
																<input type="text" name="mon_endpoint"
																	id="zab_mon_endpoint" class="form-control"
																	placeholder="0.0.0.0" required /> <span id="zabstatus"></span>

															</div>

															<label for="HostName">HostName</label>
															<div class="form-group">
																<input type="text" name="host_name" class="form-control"
																	placeholder="Fully Qualified Domain Name" required />
															</div>


															<label for="AdminUser">Admin User Name</label>
															<div class="form-group">
																<input type="text" name="admin_user"
																	class="form-control" placeholder="username" required />
															</div>
															<label for="AdminPassword">Admin Password</label>
															<div class="form-group">
																<input type="password" name="password"
																	class="form-control" placeholder="password" required />
															</div>
														</div>
													</div>
													<div class="modal-footer">
														<button type="button" class="btn btn-primary">
															Test Connection</button>
														<button type="submit" class="btn btn-primary">
															Register</button>
														<button type="button" class="btn btn-default"
															data-dismiss="modal">Cancel</button>
													</div>


												</div>
												<!-- -/.modal-body -->
											</div>
											<!-- /.modal-content -->
										</div>
										<!-- /.modal-dialog -->

									</div>
									<!-- /.modal -->
								</form>
								<!-- Modal  POP Up1 Form End for zabbix-->
							</div>
						</div>
				</div>
				<!-- end widget content-->
		</div>
		<!-- end widget boby-->
	</div>
	<!-- end widget-->
	</div>
	</article>
	<!-- WIDGET END-->
	</div>
	<!-- end row-->
	</section>
	<!-- end widget grid-->
	</div>
	<!-- END MAIN CONTENT-->
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

	<script>
		$(document)
				.ready(
						function() {
							pageSetUp();
							$.root_.addClass("fixed-page-footer")
							localStorage.setItem("sm-setmenu", "top");

							/* BASIC ;*/
							var responsiveHelper_dt_basic = undefined;
							var responsiveHelper_datatable_fixed_column = undefined;
							var responsiveHelper_datatable_fixed_column1 = undefined;
							var responsiveHelper_datatable_col_reorder = undefined;
							var responsiveHelper_datatable_tabletools = undefined;

							var breakpointDefinition = {
								tablet : 1024,
								phone : 480
							};

							$('#Openstack')
									.dataTable(
											{
												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,
												"bSort" : true,
												"sScrollY" : "100px",
												"oLanguage" : {
													"sEmptyTable" : "No Openstack VDC's available"
												},
												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_dt_basic) {
														responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
																$('#Openstack'),
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
                            					// Validation
					/* $("#openstack-insert-form").validate({

						// Rules for form validation
						rules : {
							vdc_name : {
								required : true
							},
							vdc_location : {
								required : true
							},
							description : {
								required : true
							},
							region : {
								required : true
							},
							keystone_endpoint : {
								required : true
							},
							tenant : {
								required : true
							},
							version : {
								required : true
							},
							user : {
								required : true
							},
							password : {
								required : true
							}
				
							
						},

						// Messages for form validation
						messages : {
							vdc_name : {
								required : 'Please enter your VDC name'
							},	
							vdc_location : {
								required : 'Please select VDC Location'
							},
							description : {
								required : 'Please enter your VDC description'
							},
							region : {
								required : 'Please enter your region'
							},
							keystone_endpoint : {
								required : 'Please enter your keystone endpoint'
							},
							tenant : {
								required : 'Please enter your Admin tenant'
							},
							version : {
								required : 'Please enter your version'
							},
							user : {
								required : 'Please  enter your Admin Username'
							},
							password : {
								required : 'Please enter your Admin Password'
							}
						},

						// Ajax form submition
						submitHandler : function(form) {
							$(form).ajaxSubmit({
								success : function() {
									$("#openstack-insert-form").addClass('submited');
								}
							});
						},
					// Do not change code below
						errorPlacement : function(error, element) {
							error.insertAfter(element.parent());
						}
					});
							 */
							/* COLUMN FILTER  */
							var otable = $('#Amazon')
									.DataTable(
											{

												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,

												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_datatable_fixed_column) {
														responsiveHelper_datatable_fixed_column = new ResponsiveDatatablesHelper(
																$('#Amazon'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_datatable_fixed_column
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_datatable_fixed_column
															.respond();
												}

											});

							// custom toolbar
							$("div.toolbar")
									.html('<div class="center" ></div>');

							// Apply the filter
							$(
									"#datatable_fixed_column1 thead th input[type=text]")
									.on(
											'keyup change',
											function() {

												otable.column(
														$(this).parent()
																.index()
																+ ':visible')
														.search(this.value)
														.draw();

											});

							/* COLUMN FILTER  */
							var otable = $('#VMware')
									.DataTable(
											{

												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,

												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_datatable_fixed_column1) {
														responsiveHelper_datatable_fixed_column1 = new ResponsiveDatatablesHelper(
																$('#VMware'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_datatable_fixed_column1
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_datatable_fixed_column1
															.respond();
												}

											});

							/* END COLUMN FILTER */
							/* COLUMN FILTER  */
							var otable = $('#AzureVdc')
									.DataTable(
											{


												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6 hidden-xs'f><'col-sm-6 col-xs-12 hidden-xs'<'toolbar'>>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,
												"bSort" : true,
												"sScrollY" : "100px",
												"oLanguage" : {
													"sEmptyTable" : "No Azure VDC's available"
												},

												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_datatable_fixed_column1) {
														responsiveHelper_datatable_fixed_column1 = new ResponsiveDatatablesHelper(
																$('#AzureVdc'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_datatable_fixed_column1
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_datatable_fixed_column1
															.respond();
												}

											});

							/* END COLUMN FILTER */

							/* COLUMN SHOW - HIDE */
							$('#scmbasic')
									.dataTable(
											{
												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,
												"bSort" : true,
												"sScrollY" : "500px",
												"oLanguage" : {
													"sEmptyTable" : "No Instances available"
												},
												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_datatable_col_reorder) {
														responsiveHelper_datatable_col_reorder = new ResponsiveDatatablesHelper(
																$('#scmbasic'),
																breakpointDefinition);
													}
												},
												"rowCallback" : function(nRow) {
													responsiveHelper_datatable_col_reorder
															.createExpandIcon(nRow);
												},
												"drawCallback" : function(
														oSettings) {
													responsiveHelper_datatable_col_reorder
															.respond();
												}
											});

							/* END COLUMN SHOW - HIDE */

							/* TABLETOOLS */
							$('#monbasic')
									.dataTable(
											{
												"sDom" : "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12 hidden-xs'l>r>"
														+ "t"
														+ "<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
												"autoWidth" : true,
												"bSort" : true,
												"sScrollY" : "500px",
												"oLanguage" : {
													"sEmptyTable" : "No Instances available"
												},
												"preDrawCallback" : function() {
													// Initialize the responsive datatables helper once.
													if (!responsiveHelper_dt_basic) {
														responsiveHelper_dt_basic = new ResponsiveDatatablesHelper(
																$('#monbasic'),
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

							/* END TABLETOOLS */
							/* For Hiding easy-pie-chart */
							
							
		
   
							
							
							$("#vdc_name")
									.change(
											function() {
												var vdc_name = $("#vdc_name")
														.val().trim();
												$("#statusvdcname")
														.html(
																'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
												if (vdc_name.length > 0) {
													$
															.ajax({
																type : "GET",
																url : "checkVdcNameAvailability",
																data : "vdc_name="
																		+ vdc_name,
																success : function(
																		response) {
																	if (response == 0) {
																		$(
																				"#statusvdcname")
																				.html(
																						" <font color='green'> <b>VDC Name available </b> </font> ");
																	} else {
																		$(
																				"#statusvdcname")
																				.html(
																						"  <font color='red'> <b style=''> Name already taken <b> </font>  ");
																		$(
																				"#vdc_name")
																				.val(
																						"");
																	}
																}
															});

												} else {
													$("#statusvdcname")
															.html("");
												}
											});

							$("#vdcedit_name")
									.change(
											function() {
												var vdcedit_name = $(
														"#vdcedit_name").val()
														.trim();
												$("#statusvdceditname")
														.html(
																'<img src="images/loader.gif" align="absmiddle">&nbsp;Checking availability...');
												if (vdcedit_name.length > 0) {
													$
															.ajax({
																type : "GET",
																url : "checkVdcNameAvailability",
																data : "vdc_name="
																		+ vdcedit_name,
																success : function(
																		response) {
																	if (response == 0) {
																		$(
																				"#statusvdceditname")
																				.html(
																						" <font color='green'> <b>VDC Name available </b> </font> ");
																	} else {
																		$(
																				"#statusvdceditname")
																				.html(
																						"  <font color='red'> <b style=''> Name already taken <b> </font>  ");
																		$(
																				"#vdc_name")
																				.val(
																						"");
																	}
																}
															});

												} else {
													$("#statusvdceditname")
															.html("");
												}
											});

							$("#subnet_address")
									.change(
											function() {

												var subnet_address = $(
														"#subnet_address")
														.val().trim();
												if ((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
														.test(subnet_address))) {

													/*  $("#statussubnetaddress").html('<img src="images/loader.gif" align="absmiddle">&nbsp;Checking availability...');
													    if((subnet_address.length>0) )
													    {
													     $.ajax({  
													     type: "GET",  
													     url: "checkSubnetAddressAvailability",  
													       data: "subnet_address="+subnet_address, 
													     success: function(response){ 
													  	  
													  	   if((response == 0) ){ 								              							                 
													  		   $("#statussubnetaddress").html(" <font color='green'> <b>Subnet Address available </b> </font> ");
													              }  
													              else{  					
													                 $("#statussubnetaddress").html("  <font color='red'> <b style=''>Subnet Address already taken <b> </font>  ");
													                 $("#subnet_address").val("");
													             }  
													     }
													     });
													
													}
													else{
													    $("#statussubnetaddress").html("");         	  
													}    */
												} else {
													$("#statussubnetaddress")
															.html(
																	"  <font color='red'> <b style=''>Invalid Subnet Address  <b> </font>  ");

												}
											});

							$("#keystone_endpoint")
									.change(
											function() {
												var keystone_endpoint = $(
														"#keystone_endpoint")
														.val().trim();
												/* var port=str.keystone_endpoint(":");
												alert(port); */
												var expression=/^http[s]?:(?:\/\/)(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?):([1-9]|[1-9][0-9]|[1-9][0-9][0-9]|[1-9][0-9][0-9][0-9]|[1-5][0-9][0-9][0-9][0-9]|6[0-5][0-5][0-3][0-5])$/;
												if ((expression.test(keystone_endpoint))) {
													$("#status")
															.html(
																	'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
													if (keystone_endpoint.length > 0) {
														$
																.ajax({
																	type : "GET",
																	url : "checkKeystoneEndpointAvailability",
																	data : "keystone_endpoint="
																			+ keystone_endpoint,
																	success : function(
																			response) {
																		if (response == 0) {
																			$(
																					"#status")
																					.html(
																							" <font color='green'> <b>Keystone Endpoint available </b> </font> ");
																		} else {
																			$(
																					"#status")
																					.html(
																							"  <font color='red'> <b style=''> Keystone Endpoint already taken <b> </font>  ");
																			$(
																					"#keystone_endpoint")
																					.val(
																							"");
																		}
																	}
																});

													} else {
														$("#status").html("");
													}

												} else {
													$("#status")
															.html(
																	"  <font color='red'> <b style=''>Invalid Keystone Endpoint  <b> </font>  ");
												}

											});

							$("#controller_endpoint")
									.change(
											function() {
												var controller_endpoint = $(
														"#controller_endpoint")
														.val().trim();
												if ((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
														.test(controller_endpoint))) {
													$("#constatus")
															.html(
																	'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
													if (controller_endpoint.length > 0) {
														$
																.ajax({
																	type : "GET",
																	url : "checkControllerEndpointAvailability",
																	data : "controller_endpoint="
																			+ controller_endpoint,
																	success : function(
																			response) {
																		if (response == 0) {
																			$(
																					"#constatus")
																					.html(
																							" <font color='green'> <b>Controller Endpoint available </b> </font> ");
																		} else {
																			$(
																					"#constatus")
																					.html(
																							"  <font color='red'> <b style=''> Controller Endpoint already taken <b> </font>  ");
																			$(
																					"#controller_endpoint")
																					.val(
																							"");
																		}
																	}
																});

													} else {
														$("#constatus")
																.html("");
													}
												} else {
													$("#constatus")
															.html(
																	"  <font color='red'> <b style=''>Invalid Subnet Address  <b> </font>  ");
												}

											});

							$("#puppetscm_name")
									.change(
											function() {
												var puppetscm_name = $(
														"#puppetscm_name")
														.val().trim();
												$("#statuspuppetscmname")
														.html(
																'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
												if (puppetscm_name.length > 0) {
													$
															.ajax({
																type : "GET",
																url : "checkScmNameAvailability",
																data : "scm_name="
																		+ puppetscm_name,
																success : function(
																		response) {
																	if (response == 0) {
																		$(
																				"#statuspuppetscmname")
																				.html(
																						" <font color='green'> <b>SCM Name available </b> </font> ");
																	} else {
																		$(
																				"#statuspuppetscmname")
																				.html(
																						"  <font color='red'> <b style=''> Name already taken <b> </font>  ");
																		$(
																				"#puppetscm_name")
																				.val(
																						"");
																	}
																}
															});

												} else {
													$("#statuspuppetscmname")
															.html("");
												}
											});

							$("#chef_name")
									.change(
											function() {
												var chef_name = $("#chef_name")
														.val().trim();
												$("#statuschefscmname")
														.html(
																'<img src="images/loader.gif" align="absmiddle">&nbsp;Checking availability...');
												if (chef_name.length > 0) {
													$
															.ajax({
																type : "GET",
																url : "checkScmNameAvailability",
																data : "scm_name="
																		+ chef_name,
																success : function(
																		response) {
																	if (response == 0) {
																		$(
																				"#statuschefscmname")
																				.html(
																						" <font color='green'> <b>SCM Name available </b> </font> ");
																	} else {
																		$(
																				"#statuschefscmname")
																				.html(
																						"  <font color='red'> <b style=''> Name already taken <b> </font>  ");
																		$(
																				"#chef_name")
																				.val(
																						"");
																	}
																}
															});

												} else {
													$("#statuschefscmname")
															.html("");
												}
											});

							$("#edit_scm_name")
									.change(
											function() {
												var edit_scm_name = $(
														"#edit_scm_name").val()
														.trim();
												$("#editstatusscmname")
														.html(
																'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
												if (edit_scm_name.length > 0) {
													$
															.ajax({
																type : "GET",
																url : "checkScmNameAvailability",
																data : "scm_name="
																		+ edit_scm_name,
																success : function(
																		response) {
																	if (response == 0) {
																		$(
																				"#editstatusscmname")
																				.html(
																						" <font color='green'> <b>SCM Name available </b> </font> ");
																	} else {
																		$(
																				"#editstatusscmname")
																				.html(
																						"  <font color='red'> <b style=''> Name already taken <b> </font>  ");
																		$(
																				"#edit_scm_name")
																				.val(
																						"");
																	}
																}
															});

												} else {
													$("#editstatusscmname")
															.html("");
												}
											});

							$("#scm_endpoint")
									.change(
											function() {
												var scm_endpoint = $(
														"#scm_endpoint").val()
														.trim();
												if ((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
														.test(scm_endpoint))) {
													$("#statusscm")
															.html(
																	'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
													if (scm_endpoint.length > 0) {
														$
																.ajax({
																	type : "GET",
																	url : "checkEndpointAvailabilty",
																	data : "scm_endpoint="
																			+ scm_endpoint,
																	success : function(
																			response) {
																		if (response == 0) {
																			$(
																					"#statusscm")
																					.html(
																							" <font color='green'> <b>SCM Endpoint available </b> </font> ");
																		} else {
																			$(
																					"#statusscm")
																					.html(
																							"  <font color='red'> <b style=''> SCM Endpoint already taken <b> </font>  ");
																			$(
																					"#scm_endpoint")
																					.val(
																							"");
																		}
																	}
																});

													} else {
														$("#statusscm")
																.html("");
													}
												} else {
													$("#statusscm")
															.html(
																	"  <font color='red'> <b style=''>Invalid Endpoint  <b> </font>  ");
												}
											});

							$("#edit_scm_endpoint")
									.change(
											function() {
												var edit_scm_endpoint = $(
														"#edit_scm_endpoint")
														.val().trim();
												if ((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
														.test(edit_scm_endpoint))) {
													$("#editstatusscm")
															.html(
																	'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
													if (edit_scm_endpoint.length > 0) {
														$
																.ajax({
																	type : "GET",
																	url : "checkEndpointAvailabilty",
																	data : "scm_endpoint="
																			+ edit_scm_endpoint,
																	success : function(
																			response) {
																		if (response == 0) {
																			$(
																					"#editstatusscm")
																					.html(
																							" <font color='green'> <b>SCM Endpoint available </b> </font> ");
																		} else {
																			$(
																					"#editstatusscm")
																					.html(
																							"  <font color='red'> <b style=''> SCM Endpoint already taken <b> </font>  ");
																			$(
																					"#edit_scm_endpoint")
																					.val(
																							"");
																		}
																	}
																});

													} else {
														$("#editstatusscm")
																.html("");
													}
												} else {
													$("#editstatusscm")
															.html(
																	"  <font color='red'> <b style=''>Invalid Endpoint  <b> </font>  ");
												}
											});

							$("#chef_scm_endpoint")
									.change(
											function() {
												var chef_scm_endpoint = $(
														"#chef_scm_endpoint")
														.val().trim();
												if ((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
														.test(chef_scm_endpoint))) {
													$("#chefstatus")
															.html(
																	'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
													if (chef_scm_endpoint.length > 0) {
														$
																.ajax({
																	type : "GET",
																	url : "checkEndpointAvailabilty",
																	data : "scm_endpoint="
																			+ chef_scm_endpoint,
																	success : function(
																			response) {
																		if (response == 0) {
																			$(
																					"#chefstatus")
																					.html(
																							" <font color='green'> <b>SCM Endpoint available </b> </font> ");
																		} else {
																			$(
																					"#chefstatus")
																					.html(
																							"  <font color='red'> <b style=''> SCM Endpoint already taken <b> </font>  ");
																			$(
																					"#chef_scm_endpoint")
																					.val(
																							"");
																		}
																	}
																});

													} else {
														$("#chefstatus").html(
																"");
													}
												} else {
													$("#chefstatus")
															.html(
																	"  <font color='red'> <b style=''>Invalid Endpoint  <b> </font>  ");
												}
											});

							$("#nag_name")
									.change(
											function() {
												var nag_name = $("#nag_name")
														.val().trim();
												$("#nagnamestatus")
														.html(
																'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
												if (nag_name.length > 0) {
													$
															.ajax({
																type : "GET",
																url : "checkMonNameAvailability",
																data : "mon_name="
																		+ nag_name,
																success : function(
																		response) {
																	if (response == 0) {
																		$(
																				"#nagnamestatus")
																				.html(
																						" <font color='green'> <b>MON Name available </b> </font> ");
																	} else {
																		$(
																				"#nagnamestatus")
																				.html(
																						"  <font color='red'> <b style=''>Name already taken <b> </font>  ");
																		$(
																				"#nag_name")
																				.val(
																						"");
																	}
																}
															});

												} else {
													$("#nagnamestatus")
															.html("");
												}
											});

							$("#zab_name")
									.change(
											function() {
												var zab_name = $("#zab_name")
														.val().trim();
												$("#zabnamestatus")
														.html(
																'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
												if (zab_name.length > 0) {
													$
															.ajax({
																type : "GET",
																url : "checkMonNameAvailability",
																data : "mon_name="
																		+ zab_name,
																success : function(
																		response) {
																	if (response == 0) {
																		$(
																				"#zabnamestatus")
																				.html(
																						" <font color='green'> <b>MON Name available </b> </font> ");
																	} else {
																		$(
																				"#zabnamestatus")
																				.html(
																						"  <font color='red'> <b style=''> Name already taken <b> </font>  ");
																		$(
																				"#zab_name")
																				.val(
																						"");
																	}
																}
															});

												} else {
													$("#zabnamestatus")
															.html("");
												}
											});

							$("#edit_mon_name")
									.change(
											function() {
												var edit_mon_name = $(
														"#edit_mon_name").val()
														.trim();
												$("#editnamestatus")
														.html(
																'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
												if (edit_mon_name.length > 0) {
													$
															.ajax({
																type : "GET",
																url : "checkMonNameAvailability",
																data : "mon_name="
																		+ edit_mon_name,
																success : function(
																		response) {
																	if (response == 0) {
																		$(
																				"#editnamestatus")
																				.html(
																						" <font color='green'> <b>MON Name available </b> </font> ");
																	} else {
																		$(
																				"#editnamestatus")
																				.html(
																						"  <font color='red'> <b style=''> Name already taken <b> </font>  ");
																		$(
																				"#edit_mon_name")
																				.val(
																						"");
																	}
																}
															});

												} else {
													$("#editnamestatus").html(
															"");
												}
											});

							$("#edit_mon_endpoint")
									.change(
											function() {
												var edit_mon_endpoint = $(
														"#edit_mon_endpoint")
														.val().trim();
												if ((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
														.test(edit_mon_endpoint))) {
													$("#editstatus")
															.html(
																	'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
													if (edit_mon_endpoint.length > 0) {
														$
																.ajax({
																	type : "GET",
																	url : "checkMonitorEndpointAvailabilty",
																	data : "mon_endpoint="
																			+ edit_mon_endpoint,
																	success : function(
																			response) {
																		if (response == 0) {
																			$(
																					"#editstatus")
																					.html(
																							" <font color='green'> <b>MON Endpoint available </b> </font> ");
																		} else {
																			$(
																					"#editstatus")
																					.html(
																							"  <font color='red'> <b style=''> MON Endpoint already taken <b> </font>  ");
																			$(
																					"#edit_mon_endpoint")
																					.val(
																							"");
																		}
																	}

																});

													} else {
														$("#editstatus").html(
																"");
													}
												} else {
													$("#editstatus")
															.html(
																	"  <font color='red'> <b style=''>Invalid Endpoint  <b> </font>  ");
												}
											});

							$("#nag_mon_endpoint")
									.change(
											function() {
												var nag_mon_endpoint = $(
														"#nag_mon_endpoint")
														.val().trim();
												if ((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
														.test(nag_mon_endpoint))) {
													$("#nagstatus")
															.html(
																	'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
													if (nag_mon_endpoint.length > 0) {
														$
																.ajax({
																	type : "GET",
																	url : "checkMonitorEndpointAvailabilty",
																	data : "mon_endpoint="
																			+ nag_mon_endpoint,
																	success : function(
																			response) {
																		if (response == 0) {
																			$(
																					"#nagstatus")
																					.html(
																							" <font color='green'> <b>MON Endpoint available </b> </font> ");
																		} else {
																			$(
																					"#nagstatus")
																					.html(
																							'</font></strong><font color=red>'
																									+ '&nbsp;Already Assigned EndPoint ');
																			$(
																					"#nag_mon_endpoint")
																					.val(
																							"");
																		}
																	}
																});

													} else {
														$("#nagstatus")
																.html("");
													}
												} else {
													$("#nagstatus")
															.html(
																	"  <font color='red'> <b style=''>Invalid Endpoint  <b> </font>  ");
												}
											});

							$("#zab_mon_endpoint")
									.change(
											function() {
												var zab_mon_endpoint = $(
														"#zab_mon_endpoint")
														.val().trim();
												if ((/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/
														.test(zab_mon_endpoint))) {
													$("#zabstatus")
															.html(
																	'<img src="img/discoverImage.GIF" align="absmiddle">&nbsp;Checking availability...');
													if (zab_mon_endpoint.length > 0) {
														$
																.ajax({
																	type : "GET",
																	url : "checkMonitorEndpointAvailabilty",
																	data : "mon_endpoint="
																			+ zab_mon_endpoint,
																	success : function(
																			response) {
																		if (response == 0) {
																			$(
																					"#zabstatus")
																					.html(
																							" <font color='green'> <b>MON Endpoint available </b> </font> ");
																		} else {
																			$(
																					"#zabstatus")
																					.html(
																							"  <font color='red'> <b style=''> MON Endpoint already taken <b> </font>  ");
																			$(
																					"#zab_mon_endpoint")
																					.val(
																							"");
																		}
																	}
																});

													} else {
														$("#zabstatus")
																.html("");
													}
												} else {
													$("#zabstatus")
															.html(
																	"  <font color='red'> <b style=''>Invalid Endpoint  <b> </font>  ");
												}
											});

						
							$("#testConnection")
									.click(
											function() {
												
												var keystone_endpoint = $(
														'#keystone_endpoint')
														.val().trim();
												var region = $(
														'#region')
														.val().trim();
												var tenant = $('#tenant').val()
														.trim();
												var user = $('#user').val()
														.trim();
												var password = $('#password')
														.val().trim();
												
												
												// Validation
											f1=	$("#openstack-insert-form").valid({

													// Rules for form validation
													rules : {
														vdc_name : {
															required : true
														},
														vdc_location : {
															 required: {
															        depends: function(element) {
															            return $("#openstackLocation").val() == " ";
															        }
															      }
														
															
														},
														description : {
															required : true
														},
														region : {
															required : true
														},
														keystone_endpoint : {
															required : true
														},
														tenant : {
															required : true
														},
														version : {
															required : true
														},
														user : {
															required : true
														},
														password : {
															required : true
														}
											
														
													},

													// Messages for form validation
													messages : {
														vdc_name : {
															required : 'Please enter your VDC name'
														},	
														vdc_location : {
															required : 'Please select VDC Location'
														},
														description : {
															required : 'Please enter your VDC description'
														},
														region : {
															required : 'Please enter your region'
														},
														keystone_endpoint : {
															required : 'Please enter your keystone endpoint'
														},
														tenant : {
															required : 'Please enter your Admin tenant'
														},
														version : {
															required : 'Please enter your version'
														},
														user : {
															required : 'Please  enter your Admin Username'
														},
														password : {
															required : 'Please enter your Admin Password'
														}
													},
													
													

													// Ajax form submition
												/* 	submitHandler : function(form) {
														$(form).ajaxSubmit({
															success : function() {
																$("#openstack-insert-form").addClass('submited');
															}
														});
													},
												// Do not change code below
													errorPlacement : function(error, element) {
														error.insertAfter(element.parent());
													} */
												});
												
												
												 var v1=validate();
												 if(v1)
													{
														$.ajax({
														//actual ajax call
														url : "./testOpenstakConnection",
														data : "&keystone_endpoint="
																+ keystone_endpoint
																+"&region="
																+region
																+ "&user="
																+ user
																+ "&password="
																+ password
																+ "&tenant="
																+ tenant,
														type : "POST",
														success : function(
																response) {
												
															if (response == 1) {
																$("#testConnection")
																		.prop(
																				"disabled",
																				true);
																$("#openStackRegister")
																		.prop(
																				"disabled",
																				false);
																$('#password')
																		.prop(
																				"disabled",
																				true);
																$('#user')
																		.prop(
																				"disabled",
																				true);
																$('#tenant')
																		.prop(
																				"disabled",
																				true);
																$('#subnet_address')
																		.prop(
																				"disabled",
																				true);
																
																alert("Test Connection success");
															}

															else
																alert("Test Connection failed Fill correct credentials to register");
														},
														error : function(
																xhr,
																textStatus,
																error) {
															alert("Failure:"+error);
														}
													});
													
												}
												else
													{
													
													}
												
											});

							$("#openStackReset").click(function() {
								$("#openstack-insert-form").trigger("reset");
								$("#testConnection").prop("disabled", false);
								$("#openStackRegister").prop("disabled", true);
								$("#status").text("");
								$("#constatus").text("");
								$("#statusvdcname").text("");
								$("#statussubnetaddress").text("");
								$('#password').prop("disabled", false);
								$('#user').prop("disabled", false);
								$('#tenant').prop("disabled", false);
								$('#subnet_address').prop("disabled", false);

							});

							$("#openStackRegister").click(function() {

								$('#password').prop("disabled", false);
								$('#user').prop("disabled", false);
								$('#tenant').prop("disabled", false);
								$('#subnet_address').prop("disabled", false);
								$("#openstack-insert-form").submit();

							});
                             
							$("#openstackClear").click(function() {
								  alert("inside function");
								  /* var fields =["vdc_name","openstackLocation","description","region","keystone_endpoint","tenant","openstackversion","user","password"];
								  var i, l = fields.length;
								  var fieldname;
								  $("#vdc_name").text("");
							      for (i = 0; i < l; i++) {
							        fieldname = fields[i];
							        $("#"+fieldname).text("");
							      } */
								  $("#openstack-insert-form").trigger("reset");
								  $("#status").text("");
								  $("#constatus").text("");
								  $("#statusvdcname").text("");
									$("#statussubnetaddress").text("");
									//$("#statusvdcname").message()
									$('#password').prop("disabled", false);
									$('#user').prop("disabled", false);
									$('#tenant').prop("disabled", false);
									$('#subnet_address').prop("disabled", false);
									
									openstack-insert-form.resetForm();
									
								    $("#openstack-insert-form").trigger("cancel");
								    
							  });
						});
	</script>



	<script type="text/javascript">
		var currentLink = null;

		function deleteOpenstack(value) {
			var vdc_id = value.id;
			document.getElementById('vdc_id').value = vdc_id;
			document.editForm1.action = "./deleteOpenstack";
			document.editForm1.submit();
		}
		function getRowOpenstack(value) {
			var vdc_id = value.id;
			$('#gvdc_id').val(vdc_id);
			$.getJSON("./getOpenstack/" + vdc_id, function(response) {
				$('#vdcedit_name').val(response.vdc_name);
				$('#description').val(response.description);
				$('#tenant').val(response.tenant);
				$('#vdc_location').val(response.vdc_location);
				$('#vdc_status').val(response.vdc_status);
				$('#user_name').val(response.username)
			});
		}

		function getRow(value) {
			var scmid = value.id;
			$('#scmid').val(scmid);
			$.getJSON("./editscm/" + scmid, function(response) {
				$('#edit_scm_id').val(scmid);
				$('#edit_scm_name').val(response.scm_name);
				$('#edit_scm_endpoint').val(response.scm_endpoint);
				$('#edit_host_name').val(response.host_name);
				$('#edit_admin_user').val(response.admin_user);
				$('#edit_admin_pass').val(response.admin_pass);
			});
		}

		function deleteScm(scm_id) {
			document.getElementById('scm_id').value = scm_id;
			document.editForm.action = "./deleteScm";
			document.editForm.submit();
		}

		function getMonitorRow(value) {
			var mon_id = value.id;
			$('#cmon_id').val(mon_id);
			$.getJSON("./editmon/" + mon_id, function(response) {
				$('#edit_mon_name').val(response.mon_name);
				$('#edit_mon_endpoint').val(response.mon_endpoint);
				$('#host_name').val(response.host_name);
				$('#admin_user').val(response.admin_user);
				$('#admin_pass').val(response.admin_pass);
			});
		}

		function deleteMonitor(mon_id) {
			document.getElementById('mon_id').value = mon_id;
			document.monitoreditForm.action = "./deleteMonitor";
			document.monitoreditForm.submit();
		}

		function test(hrefId) {
			$('#serverLoad').easyPieChart({
				animate : 5000
			});

			if (currentLink != null) {
				currentLink.style.color = hrefId.style.color; //You may put any color you want
			}
			hrefId.style.color = 'red';
			currentLink = hrefId;

			/* 
			   				 $.ajax({
			   				    	//actual ajax call
			   				        url: "./getUsage",
			   				        data : "vdcId=" + value ,
			   				        type: "POST",
			   				        success: function(response) {
			   				    		                
			   				        },
			   				        error : function(xhr, textStatus, error) {        	
			   		                 alert("error retrieving values");	        	  
			   				        }
			   				    });			 */

			$('#serverLoad').data('easyPieChart').update(Math.random() * 100);
			$('#server')
					.html('<i class="fa fa-caret-up icon-color-good"></i> ');
			$('#diskSpace').easyPieChart({
				animate : 5000
			});

			$('#disk')
					.html(' <i class="fa fa-caret-down icon-color-good"></i>');
			$('#diskSpace').data('easyPieChart').update(Math.random() * 100);

			$('#Memory').easyPieChart({
				animate : 5000
			});

			$('#Memory').data('easyPieChart').update(Math.random() * 100);

			$('#mem').html('<i class="fa fa-caret-up icon-color-good"></i>');
			/* $('.nextPage a:visited{font-family:Calibri;color:#005BAC;font-weight:bold}') ; */
			$('.nextPage').siblings().removeClass('selected');
			$('.nextPage').addClass('selected');
		}
		
		function validate()
	       {
	    	  
	         var fields = ["vdc_name","vdc_location","description","region","keystone_endpoint","tenant","version","user","password"]

	         var i, l = fields.length;
	       
	         var fieldname;
	         
	         for (i = 0; i < l; i++) {
	           fieldname = fields[i];
	           var result1 = document.getElementById('openstackLocation').value;
	 		    var result2 = document.getElementById('openstackversion').value;
	         	
	           if (document.forms["openstack-insert-form"][fieldname].value == " " || result1=="0" || result2=="0" ) {
	             
	          		 return false;
	           }
	           
	         }
	         return true;
	       }

 

	</script>
	
	
	
    <!-- <script type="text/javascript">
function getdepartment(){
       
            var table1 = $("#AzureVdc > tbody:last");

            var dropdown = $("#cb").clone();

            var p = $("span").html(dropdown);

            for(i = 1; i <= 10; i++){

  /*  table1.append("<tr><td>"+ i +"</td><td>${user.vdc_name}"+ i +
    "</td><td><span class='abc' id='sp_"+ i +"'></span></td><tr>");

            } */
            $('#cb').append(
            		'<tr>' +
            		    '<td>' + index + '</td>' +
            		    '<td>' + $(this).val() + '</td>' +
            		    '<td></td>' +
            		    '<td></td>' +
            		    '<td></td>' +
            		    '<td></td>' +
            		    '<td></td>' +
            		    '<td></td>' +
            		    '<td></td>' +
            		'</tr>');
            

            $(".abc").html(dropdown);
}

        };

</script> -->
</body>

</html>