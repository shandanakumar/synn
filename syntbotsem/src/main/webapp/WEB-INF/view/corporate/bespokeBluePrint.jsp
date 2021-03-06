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

				<li  class="active"><a href="#"><i
						class="fa fa-lg fa-fw fa-puzzle-piece"></i> <span
						class="menu-item-parent">Blue Print</span></a>
				<ul>
						<li><a href="bespokeBluePrint">Bespoke</a></li>
					</ul></li>
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
					<li>Home</li><li>Provision VM</li><li>Bespoke Service</li>
				</ol>
			</div>
			<!-- END RIBBON -->

					<!-- MAIN CONTENT -->
					
						
					
		  <div id="content">
		   <div class="pull-right">
							 <a href="blueprint" class="btn btn-primary txt-color-white">Create Blueprint</a>   
                                        
							<br><br>
						</div>		
		 <div class="row">
		 <div class="col-sm-12">
			  <div class="well well-light">	
			  <div class="row">				
					<div class="well well-light">
				       <div class="row">
						<c:forEach items="${envVMList}" var="envVM">
   								<c:set var="enVmid" value="${envVM.vm_master_id}"/>
							      <div class="col-xs-12 col-sm4 col-md-2">
						            <div class="panel panel-white">
						                <div class="panel-heading"  style="border-bottom-color: #333;">
						                  <h3 class="panel-title">						                			 
												<c:forEach items="${envVMExtList}" var="enVMExt">														
														 <c:set var="enVmExid" value="${enVMExt.vm_master_id}"/>
														 <c:set var="paramname" value="${enVMExt.param_name}"/>
														 <c:set var="paramval" value="${enVMExt.param_val}"/>									
															<c:if test="${ (enVmid == enVmExid) && (paramname=='imgpath') }">
																  		 <img src="${enVMExt.param_val}" style="padding-left: 0%" width="110px" height="50px;"  alt="Logo"/>  
														    </c:if> 																  
															<c:if test="${ (enVmid == enVmExid) && (paramname=='populartag') && (paramval=='Active') }">
																 		 <img src="img/ribbon.png" class="ribbon" alt=""> 
															</c:if>															
												</c:forEach>		 		 
			                             </h3>
						               </div>
						               <div class="panel-body no-padding text-align-center">
						                   <div class="the-price">
						                        <h1>
						                           ${envVM.vm_name} <!--  Tomcat Version <strong> : 7.0</strong> -->
						                         </h1>
						                    </div>
						                    <table class="table">
						                        <tr>
						                            <td>
						                              OS <strong> :  ${envVM.os}</strong>
						                            </td>
						                        </tr>
						                        <tr class="active">
						                            <td>
						                            <strong>VCPU </strong> :${envVM.cpu}
						                            </td>
						                        </tr>
						                        <tr>
						                            <td>
						                               RAM <strong> :   ${envVM.mem}</strong>
						                            </td>
						                        </tr>
						                       	<c:if test="${envVM.vdc_provider == 'OpenStack'}">
													<tr class="active">
							                            <td>
							                             Disk Size  <strong> :${envVM.hdd}</strong>
							                            </td>
							                        </tr>
												</c:if>
												<c:if test="${envVM.vdc_provider == 'MS-Azure'}">
													<tr class="active">
							                            <td>
							                             Status  <strong> :${envVM.status}</strong>
							                            </td>
							                        </tr>
												</c:if> 																  
												<tr>
						                            <td>
						                             Type  <strong> : ${envVM.os_type}</strong>
						                            </td>
						                        </tr>
						                        <tr>
						                            <td id="vdcProvider">
						                             Provider <strong> : ${envVM.vdc_provider}</strong>
						                            </td>
						                        </tr>				                      
						                    </table>
						                </div>						               					                
						               <div class="panel-footer no-padding">						            						                    						                      
						                     <a href="javascript:void(0);" data-toggle="modal" id="${envVM.vm_master_id}" name="${envVM.vdc_provider}" onClick="getMasterId(this.id,this.name)" data-target="#myModal" class="btn bg-color-darken txt-color-white btn-block" role="button">
						                    <i class="fa fa-shopping-cart"></i>
						                      Launch <span> now!</span></a>						                      
						               </div> 	       
						            </div>
						        </div>	
	                           </c:forEach>		        
							</div>							
						  </div> 							
						 </div>     		
					</div>
				</div>

                   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
									&times;
								</button>
								<h3 class="modal-title" id="myModalLabel"><span id="componentName"></span> Configuration Details</h3>
							</div>							
							<div class="modal-body">			
									<!-- row -->
									<div class="row">				
										<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">									
											<div class="row">
												<div class="col-sm-12">
													<div class="text-center error-box">															
														<!-- NEW COL START -->
														<article class="col-sm-12 col-md-12 col-lg-12">			
															<!-- Widget ID (each widget will need unique ID)-->
															<div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-5" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="true" data-widget-sortable="false">
																<header>
																	<span class="widget-icon"> <i class="fa fa-edit"></i> </span>
																	<h2>Configuration Details </h2>	
																	 <span class="pull-right" id="loading1" > <img src="img/ajaxLoader.gif"> </span>								
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
																		<form name="v" id="addForm" class="smart-form">																										
						   													 <input type="hidden" name="vmId" id="vmId">					
																			<fieldset>
																	
						                    					              	<div class="row">
						                    					              	<!-- Important code keypair with dropdown values -->
						                    					              	<div id="openstackKeypairBlock" style="display:none">
							                    					              	<section class="col col-10">
																						<label class="label">KeyPair</label>
																								<label class="select">
																									<select name="keypair" id="keypair">
																									<option value="0" selected="" disabled="">select keyPair</option>
																						</select>  <i></i> </label>
																					</section> 
																					<!--   <section class="col col-6">
																								<label class="label">Instance Name </label>
																								<label class="input">
																									<input type="text" name="vm_name" id="vm_name" >
																								</label>
																							</section> -->
						                    					              	</div>
																	            <div id="azureBlock" style="display:none">
																                      <section class="col col-10">
																						<label class="label">UserName</label> 
																						<label class="input">
																							<input type="text" name="azureUserName" id="azureUserName" placeholder="Username">
																						</label>
																						</section>
																						
																						<section class="col col-6">
																						<label class="label">Password</label> 
																						<label class="input">
																							<input type="password" name="azurePassword" id="azurePassword" placeholder="Password">
																						</label>
																						</section>
																						<section class="col col-6">
																						<label class="label">Confirm Password</label> 
																						<label class="input">
																							<input type="password" name="azureCnfPassword" id="azureCnfPassword" placeholder="Confirm Password">
																						</label>
																						</section>
																						
																						<section class="col col-10">
																						<label class="label">EndPoints<i class="fa fa-question-circle"></i></label> 
																						<table class="table table table-hover">
																									<thead>
																										<tr>
																											<th style="width:50%" >Name</th>
																											<th>Protocol</th>
																											<th>Public Port</th>
																											<th>Private Port</th>
																										</tr>
																									</thead>
																									<tbody>
																										<tr class="info">
																											<td style="text-align:left">Remote Desktop</td>
																											<td>TCP</td>
																											<td>Auto</td>
																											<td>3389</td>
																										</tr>
																										<tr class="info">
																											<td style="text-align:left">PowerShell</td>
																											<td>TCP</td>
																											<td>5986</td>
																											<td>5986</td>
																										</tr>
																										<!-- <tr class="info">
																											<td style="text-align:left">SSH</td>
																											<td>TCP</td>
																											<td>22</td>
																											<td>22</td>
																										</tr>
																										<tr class="info">
																											<td style="text-align:left">IMAP</td>
																											<td>TCP</td>
																											<td>143</td>
																											<td>143</td>
																										</tr>
																										<tr class="info">
																											<td style="text-align:left">LDAP</td>
																											<td>TCP</td>
																											<td>389</td>
																											<td>389</td>
																										</tr> -->
																									</tbody>
																								</table>
																								<!-- <label class="input">
																									<input type="text" name="inputendpoint" id="inputendpoint" placeholder="ENTER or SELECT A VALUE">
																								</label>
																								<label class="select">
																									<select name="endpointSelectBox" id="endpointSelectBox">
																										<option value="0" selected="" disabled="">Select Endpoint</option>
																										<option value="1" selected="" disabled="">test1</option>
																										<option value="2" selected="" disabled="">test2</option>
																									</select>  <i></i> </label> -->
																						</section>
																			 
								                                                   </div>
																				</div>
																				<div class="row">
						                    					              	
																                         <section class="col col-6">
																                                 <label class="label">Start Date </label>
																										<label class="input"> <i class="icon-append fa fa-calendar"></i>
																											<input type="text" name="startDate" id="startDate" placeholder="Expected start date">
																										</label>
																							</section>
																							<section class="col col-6">
																									   <label class="label">End Date </label>
																										<label class="input"> <i class="icon-append fa fa-calendar"></i>
																											<input type="text" name="endDate" id="endDate" placeholder="Expected finish date">
																										</label>
																							</section>
								                                                   </div>
																			
																				<div class="row" id="dynamicFormElements">								
																				</div> 																					
																			</fieldset>
												
																			<footer>
																				<button type="submit" name="submit" class="btn btn-primary" onclick="check()">
																					Configure & Launch
																				</button>
																			</footer>				
																		</form>
																		
																	</div>
																	<!-- end widget content -->	
																</div>
																<!-- end widget div -->
																
															</div>
															<!-- end widget -->	
														</article>
														<!-- END COL -->
														<br>					
													</div>				
												</div>				
												</div>				
											</div>
										<!-- end row -->					
									</div>					
							      </div>
		                     </div>
		               </div><!-- /.modal-dialog -->
                     </div><!-- /.modal -->
	            </div>
	      <!-- END MAIN CONTENT -->
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

	<script type="text/javascript">
		
		// DO NOT REMOVE : GLOBAL FUNCTIONS!
		
		$(document).ready(function() {
			
			pageSetUp();
			localStorage.setItem("sm-setmenu","top");
			$.root_.addClass("fixed-page-footer") ;
			
		    $( "#loading1" ).hide();
			
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

	</script>
	<script type="text/javascript">
		function getMasterId(vmId,vdcProvider){	
			$("#vmId").val(vmId);
			$("#vdcProvider").val(vdcProvider);
			//alert("vmId from get master"+vmId);
			//alert("vdc provider from get master"+vdcProvider);
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
						 $('#dynamicFormElements').append(' <section class="col col-6"><label class="label">'+response[i].packageAttributes[j].attr_name+'</label><label class="input"><input type="text" name='+response[i].packageAttributes[j].attr_name+' id='+response[i].packageAttributes[j].attr_name+' value='+response[i].packageAttributes[j].default_val+' ></label></section>'); 
					 }
                  }  					 				 
			 });
			 $( "#loading1" ).hide();
		}
	</script>
	
       <script type="text/javascript">
       function check() { 
    	$( "#loading1").show();
    	var varVmId = document.getElementById("vmId").value;
    	var vdcProvider = document.getElementById("vdcProvider").value;
    	if(vdcProvider == "MS-Azure"){
    		launchAzureBespokeInstance();
    		$( "#loading1" ).hide();
    	/* document.bespokeLaunch.action="bespokeAzureLaunch"; */
    	}else if(vdcProvider == "OpenStack"){
    		document.bespokeLaunch.action="bespokeLaunch";	
    		$( "#loading1" ).hide();
    	}
     }
       
       function launchAzureBespokeInstance(){
    		// alert("inside launchAzureInstance");
    		 var varAzureLaunchBespokeJsonObj = {};
    		  
    		varAzureLaunchBespokeJsonObj['azureUserName'] = document.getElementById("azureUserName").value;
    	  	//alert("azureUserName "+varAzureLaunchBespokeJsonObj['azureUserName']);
    	  	varAzureLaunchBespokeJsonObj['azurePassword'] = document.getElementById("azurePassword").value;
    	  	//alert("azurePassword "+varAzureLaunchBespokeJsonObj['azurePassword']);
    	  	varAzureLaunchBespokeJsonObj['azureCnfPassword'] = document.getElementById("azureCnfPassword").value;
    	  	//alert("azureCnfPassword "+varAzureLaunchBespokeJsonObj['azureCnfPassword']);
    	  	/* varAzureLaunchBespokeJsonObj['azureEndPoint'] = document.getElementById("azureEndPoint").value;
    	  	alert("azureEndPoint "+varAzureLaunchBespokeJsonObj['azureEndPoint']); 
    	  	varAzureLaunchBespokeJsonObj['azureVmName'] = document.getElementById("azureVmName").value;
    	  	alert("azureVmName "+varAzureLaunchBespokeJsonObj['azureVmName']);*/
    	  	varAzureLaunchBespokeJsonObj['azureStartDate'] = document.getElementById("startDate").value;
    	  	//alert("azureStartDate "+varAzureLaunchBespokeJsonObj['azureStartDate']);
    	  	varAzureLaunchBespokeJsonObj['azureEndDate'] = document.getElementById("endDate").value;
    	  	//alert("azureEndDate"+varAzureLaunchBespokeJsonObj['azureEndDate']);
    	  	varAzureLaunchBespokeJsonObj['azureVmId'] = document.getElementById("vmId").value;  
    		var strJSON = JSON.stringify(varAzureLaunchBespokeJsonObj); 
    		//alert(strJSON);    		
    		  
    			/* $.ajax({     
    				type:"GET",     
    				data:"azureBespokeDetails="+strJSON,
    				url: "./azurecustomlaunch", 
    				dataType: 'json',
    				success:function(data){
    					alert('inside  success block'+data);
    				},     
    				error:function(XMLHttpRequest){ 
    					alert('inside  error block');
    				}
    			}); 
    			$.ajax({     
    				data:strJSON,
    				data:"azureBespokeDetails="+strJSON,
    				url: "./bespokeAzureLaunch", 
    				dataType: 'json',
    				success:function(data){
    					alert('inside  success block'+data);
    				},     
    				error:function(XMLHttpRequest){ 
    					alert('inside  error block');
    				}
    			});
    			
    			$.ajax({     
    				type:"POST",     
    				//data:strJSON,
    				data:"azureBespokeDetails="+strJSON,
    				url: "./bespokeAzureLaunch", 
    				dataType: 'json',
    				success:function(data){
    					alert('inside  success block'+data);
    				},     
    				error:function(XMLHttpRequest){ 
    					alert('inside  error block');
    				}
    			});*/
    			
    		$.ajax({     
    			type:"POST",     
    			data:"azureBespokeDetails="+strJSON,
    			url: "./bespokeAzureLaunch", 
    			dataType: 'json',
    			success:function(data){
    				alert('inside  success block'+data);
    				//getCustomServiceList();
    			},     
    			error:function(XMLHttpRequest){ 
    				alert('Error Occured');
    				//getCustomServiceList();
    			}
    		});
       }
      </script>
      
      
	
	</body>
</html>