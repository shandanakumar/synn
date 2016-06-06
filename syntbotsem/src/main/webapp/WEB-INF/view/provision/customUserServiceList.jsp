<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.syntel.isap.provisioning.bean.CustomVM"%>



<!DOCTYPE html>

<html lang="en-us">
	<head>
		<meta charset="utf-8">
		<title> SyntBots - Custom Service List</title>
		<meta name="description" content="">
		<meta http-equiv="refresh" content="10" > 
		<meta name="author" content="">	
		<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
		
		<!-- Basic Styles -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/bootstrap.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/font-awesome.min.css">
		
		<!-- SmartAdmin Styles : Caution! DO NOT change the order -->
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production-plugins.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-production.min.css">
		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-skins.min.css">

		<link rel="stylesheet" type="text/css" media="screen" href="css/smartadmin-rtl.min.css"> 

		<!-- FAVICONS -->
		<link rel="shortcut icon" href="img/favicon/favicon.ico" type="image/x-icon">
		<link rel="icon" href="img/favicon/favicon.ico" type="image/x-icon">

		<!-- GOOGLE FONT -->
		<link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Open+Sans:400italic,700italic,300,400,700">		
	
	<style>
	#delete_icon{
	  color:red;
	}
	</style>
	</head>
	
	
<%
       
      List<CustomVM> customList= null;
        if(request.getAttribute("list")!= null){
            customList= (List<CustomVM>)request.getAttribute("list");   
        }else{           
            customList = new ArrayList<CustomVM>();
        }
       
        CustomVM objCustomVM = null;
        String strCustomVMsToFetchIPAddresses = "";
        for(int i=0;i < customList.size(); i++){
            objCustomVM = (CustomVM) customList.get(i);
           
            if((objCustomVM.getIp_addr() == null) || (objCustomVM.getIp_addr().length() == 0) || (objCustomVM.getIp_addr().equals("-"))){
                if(strCustomVMsToFetchIPAddresses.length() > 0)
                {
                    strCustomVMsToFetchIPAddresses += ",";
                }
                strCustomVMsToFetchIPAddresses += objCustomVM.getVm_name();
            }
           
        }
       
    %>
	
	
	

	
	   <%@ include file="/SesssionFilter.jsp" %>
     <body class="menu-on-top">    
		<!-- #HEADER -->
		<header id="header">
		<!-- PAGE LOGO HEADER -->

		<%@ include file="/includefiles/logoheader.jsp"%>

		<!-- END PAGE LOGO HEADER -->

		<!-- pulled right: nav area -->
		<%-- <div class="pull-right">
             <div class="project-context hidden-xs">
                <span class="label">Tenant User: ( Project : STG Automtation Team )</span>
                 <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <i class="fa fa-user"></i><span> Welcome ,&nbsp;&nbsp;${userValue.usr_name}</span>
                 </a>
              </div>
               
          <!-- collapse menu button -->
		<div id="hide-menu" class="btn-header pull-right">
			<span> <a href="javascript:void(0);" data-action="toggleMenu" title="Collapse Menu"><i class="fa fa-reorder"></i></a> </span>
		</div>
		<!-- end collapse menu -->

         <span> <a style="border-radius: 2px;cursor: default !important;display: inline-block;font-weight: 700;height: 30px;line-height: 24px;min-width: 30px; padding: 2px;text-align: center;text-decoration: none !important;-moz-user-select: none;background-color: #F8F8F8;   
                          background-image: -moz-linear-gradient(center top , #F8F8F8, #F1F1F1);border: 1px solid #BFBFBF;color: #6D6A69;font-size: 17px;
                          margin: 10px 0px 0px;" href="./logOut.jsp" title="Sign Out" data-action="userLogout" data-logout-msg="You can improve your security further after logging out by closing this opened browser"><i class="fa fa-sign-out"></i>
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
					 <li>
						<a href="dashboardTenantUser" title="Dashboard"><i class="fa fa-lg fa-fw fa-home"></i> <span class="menu-item-parent">Dashboard</span></a>
					</li>
					<li>
						<a href="#"><i class="fa fa-lg fa-fw fa-windows"></i> <span class="menu-item-parent">Provision VM</span></a>
						<ul>
							<li>
								<a href="stackTenantUser">Stack Service </a> <!-- here actually stackTenantUser action should be called -->
							</li>
								<li>
								<a href="bespokeTenantUser">Bespoke Service </a> <!-- here actually bespokeTenantUser action should be called -->
							</li>
							<li>
								<a  href="customLaunch">Custom Service </a>
							</li>
						</ul>
					</li>					
						<li class="active">
						<a href="#"><i class="fa fa-lg fa-fw fa-filter"></i> <span class="menu-item-parent">Provision VM List</span></a>
						<ul>
							<li class="active">
								<a href="customUserServiceList" title="#"> Custom Service List</a> 
							</li>
							<li >
								<a  href="bespokeUserServiceList" title="#">Bespoke Service List </a> <!-- here actually BespokeServiceList action should be called -->
							</li>
						</ul>
					</li>					
					<li>
						<a href="userKeyPairList" title="KeyPair"><i class="fa fa-lg fa-fw fa-key"></i> <span class="menu-item-parent">KeyPair List</span></a>
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
				<span class="ribbon-button-alignment"> </span>
				<!-- breadcrumb -->
				<ol class="breadcrumb">
					<li>Home</li><li>Service List</li><li>Custom Service List</li>
				</ol>
			</div>
			<!-- END RIBBON -->
			
			<!-- MAIN CONTENT -->
			<div id="content">
			  
				<!-- widget grid -->
				<section id="widget-grid" class="">				
				<!-- row -->
				<div class="row">
				    <form name="instanceList" id="instanceList" method="POST"> 				
					<div class="pull-right">	
						<a  class="btn bg-color-redLight txt-color-white" id="anc_id"  onclick="terminateSelected();"   href="javascript:void(0);">  <i class="fa fa-trash-o">&nbsp;</i>Terminate Selected </a>
						<!-- <a class="btn bg-color-blueLight txt-color-white" href="javascript:void(0);"> Pause Selected </a>							 -->
						<br><br>						
					</div>        						   
				    <input type="hidden" name="instId" id="instId"/>
					<!-- NEW WIDGET START -->
					<article class="col-xs-12 col-sm-12 col-md-12 col-lg-12">			
						<!-- Widget ID (each widget will need unique ID)-->
						<div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-0" data-widget-colorbutton="false" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="true" data-widget-sortable="false">				
							<header>
								<span class="widget-icon"> <i class="fa fa-table"></i> </span>
								<h2>Custom Service List </h2>			
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
												   <th><input type="checkbox" id="ckbCheckAll"></th> 
													<th data-class="expand">VM Name</th>
													<th data-hide="phone" >Image Name</th>	
													<th>IP Address</th>
													<th data-hide="phone,tablet">Size</th>
													<!-- <th data-hide="phone,tablet">Security Group</th>
													<th data-hide="phone,tablet">KeyPair</th> -->
													<th data-hide="phone,tablet">Start</th>
													<th data-hide="phone,tablet">End</th>
													<th data-hide="phone,tablet">Provider</th>
													<th data-hide="phone,tablet">Status</th>						
									                <th data-hide="phone,tablet">Actions</th>	
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${list}" var="customList">
											<tr>
													       <td align="center"><input type="checkbox" id="${customList.vm_name}" value="${customList.vm_custom_id}" class="checkBoxClass"></td>
					        								<td><a data-toggle="modal" href="#myModal" id="${customList.vm_custom_id}" onclick="getRow(this);"> ${customList.vm_name} </a></td>
															<td>${customList.stackList.image}  </td>
															<td>${customList.ip_addr} <br><br> <b> ${customList.public_ip_addr}</b> </td>
															<td>${customList.stackList.flavor}  </td>
															<%-- <td>${customList.stackList.security}  </td>
															<td>${customList.stackList.keypair}  </td> --%>
															<td>${customList.startDate}   </td>
															<td>${customList.endDate}  </td>
															<td>${customList.vdc_provider}   </td>
														    	<c:set var="paramName" value="${customList.status}"/>
															<td>
																 <c:choose>
																    <c:when test="${ (paramName=='ReadyRole') || (paramName=='ACTIVE') }">
																		 <span class="label label-success">${customList.status}</span>
																		 <br><br>		
																   </c:when>
																   <c:when test="${ paramName=='StoppedVM'}">
																		 <span class="label label-danger">${customList.status}</span>
																		 <br><br>		
																   </c:when>
																  <c:otherwise>
																     <span class="label label-warning">InProgress</span>
																     <br><br>																     
																   </c:otherwise>
															    </c:choose>	 
															    
															  <c:set var="ipAdd" value="${customList.public_ip_addr}"/>
														<c:choose>												 
																 <c:when test="${ (ipAdd=='-') || (ipAdd=='0.0.0.0')}">														 
																	   <span class="label label-danger">Inaccessible</span>
																	   <br><br>			 														   
																  </c:when>
															<c:otherwise>
															<c:choose>	
															  	<c:when test="${ paramName=='ReadyRole'}">														 
																	<span class="label label-success">Accessible</span>	  
																	<br><br>		
																 </c:when>
																 <c:when test="${ paramName=='StoppedVM'}">														 
																	<span class="label label-danger">Inaccessible</span>	
																	<br><br>		  
																 </c:when>
																 <c:otherwise>
																     <span class="label label-danger">Inaccessible</span>
																     <br><br>																     
																 </c:otherwise>
																 </c:choose>
														   </c:otherwise>
													</c:choose>	 														    													   															    
													</td>
													
													<td width="15%">      
																											
														<a title="Terminate VM" id="${customList.vm_custom_id}" name="${customList.vm_name}" onclick="terminateInstance(this)" href="#"><i class="fa fa-trash-o" id="delete_icon"></i></a>															
														&nbsp;&nbsp;
														<div class="btn-group">		
															<button class="btn btn-primary btn-sm dropdown-toggle" data-toggle="dropdown">
																More <span class="caret"></span>
															</button>
															<ul class="dropdown-menu">	
													
													
													 <c:set var="cloudParam" value="${customList.vdc_provider}"/>
													<c:choose>	
													
														<c:when test="${(cloudParam=='Openstack')}">	
																																																							
															 <c:choose>													 																																	
							                                    <c:when test="${(ipAdd=='-') and (paramName=='Requested') }">					
							                                     <li>										
										                           <a  href="javascript:void(0)"> Allocate Floating IP</a>
																</li>																
								  	                          <li>		
										                       <a  href="javascript:void(0)"> Deallocate Floating IP</a>												
									                        </li>											
							                                </c:when>
														
							  	                          <c:when test="${(ipAdd=='-') and (paramName=='ACTIVE') }">				
							                               <li>												
										                   <a id="${customList.vm_custom_id}" name="${customList.vm_name}" href="#" onclick="addFloatingIP(this);">Allocate Floating IP</a>												
									                      </li>													
								  	                     <li>												
										                  <a class="btn disabled" href="javascript:void(0);" style="text-align:left">Deallocate Floating IP</a>
										                  </li>
														 </c:when>
														     <c:when test="${(ipAdd=='0.0.0.0') and (paramName=='ACTIVE') }">				
							                               <li>												
										                   <a id="${customList.vm_custom_id}" name="${customList.vm_name}" href="#" onclick="addFloatingIP(this);">Allocate Floating IP</a>												
									                      </li>													
								  	                     <li>												
										                  <a class="btn disabled" href="javascript:void(0);" style="text-align:left">Deallocate Floating IP</a>
										                  </li>
														 </c:when>																	        
														   <c:when test="${(paramName=='error')}">														 
														          <li>
																	<a class="btn disabled" href="javascript:void(0);" style="text-align:left">Allocate Floating IP</a>
																</li>
															  	<li>
																	<a class="btn disabled" href="javascript:void(0);" style="text-align:left">Deallocate Floating IP</a>
																</li>
														   </c:when>														        
														  <c:otherwise>
														        <li>
																	<a class="btn disabled" href="javascript:void(0);" style="text-align:left">Allocate Floating IP</a>
																</li>
														      
														   	     <li>
																	<a id="${customList.vm_custom_id}" name="${customList.vm_name}" href="#" onclick="removeFloatingIP(this);">Deallocate Floating IP</a>
																</li>
														 </c:otherwise>
													    </c:choose>	
																 <li>
																	<a href="javascript:void(0);">Pause Instance</a>
																</li>
																<li>
																	<a href="javascript:void(0);">Suspend Instance</a>
																</li>
																<li>
																	<a href="javascript:void(0);">Resize Instance</a>
																</li>
													</c:when>
																
													
													<c:when test="${(cloudParam=='Azure')}">	
														       <li>
																	<a href="javascript:void(0);">Restart</a>
																</li>
																<li>
																<a id="${customList.vm_custom_id}" name="${customList.vm_name}" href="#" onclick="shutdownAzureCustVM(this);">Shutdown</a>
																	<!-- <a href="javascript:void(0);">Shutdown</a> -->
																</li>
																<li>
																	<a href="javascript:void(0);">Attach empty disk</a>
																</li> 
																<li>
																	<a href="javascript:void(0);">Capture</a>
																</li>
																<li>
																	<a class="btn disabled" href="javascript:void(0);" style="text-align:left" >Dettach</a>
																</li>
													
													</c:when>			
																
															</c:choose>																																													
															</ul>
														</div>
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
	<!-- END MAIN PANEL -->
	
		
	<!-- ui-dialog -->
     <div id="dialog_simple" title=" ">
            <p>
                Are you sure you want to terminate the Instance : <span id="instance"> </span> ? .
            </p>
      </div>
      
      
      	<!-- ui-dialog -->
     <div id="floating_dialog" title=" ">
            <p>
                Are you sure you want to add FloatingIP to the Instance : <span id="instanceFloating"> </span> ? .
            </p>
      </div>
      
         	<!-- ui-dialog -->
     <div id="removeFloating_dialog" title=" ">
            <p>
                Are you sure you want to remove FloatingIP to the Instance : <span id="instanceRemoveFloating"> </span> ? .
            </p>
      </div>

	 
		<!-- Modal popup for  workflow -->
		<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<h4 class="modal-title">
							<b> VM Details</b>
						</h4>
					</div>
					
					
					<div class="modal-body">
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="category"></label> Instance Name <input type="text"
										name="roleInstanceName" id="roleInstanceName" class="form-control" disabled />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="category"></label> Status <input type="text"
										name="roleStatus" id="roleStatus" class="form-control" disabled />
								</div>
							</div>
							
						</div>
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<label for="category"></label> IP Address <input type="text"
										name="roleIPAddress" id="roleIPAddress" class="form-control" disabled />
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<label for="category"> Power State </label> <input type="text"
										name="rolePowerState" id="rolePowerState" class="form-control" disabled />
								</div>
							</div>
							
						</div>
						<table id="dt_basic1" class="table table-striped table-bordered table-hover" width="100%">
									    <thead>								
												<tr>
													
													<th>Name</th>	
													<th>Vip</th>	
													<th>publicPort</th>
													<th>LocalPort</th>
													<th>Protocol</th>
													
												</tr>
										</thead>
										<tbody>
											<%--  <c:forEach items="${vmDetails}" var="vmDetails"> 
											<tr>
												<td> ${vmDetails.instanceEndPointDetails.name}  </td>
												<td> ${vmDetails.instanceEndPointDetails.Vip} </td>
												<td> ${vmDetails.instanceEndPointDetails.publicPort}</td>
												<td> ${vmDetails.instanceEndPointDetails.localPort}  </td>
												<td> ${vmDetails.instanceEndPointDetails.protocol}  </td>
												
														    	
											</tr>
										 </c:forEach>  --%>
										</tbody>					
						</table>
						<div class="modal-footer">
							<footer>
								<!-- <button type="button" class="btn btn-primary" onclick="javascript:fnItOpsValidateWrkflwDtlsPopUp();">Save</button> -->
								<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
							</footer>
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
	<!-- modal popup end workflow -->



	<!-- PAGE FOOTER -->

	<%@ include file="/includefiles/footer.jsp"%>

	<!-- END PAGE FOOTER -->

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

		<!-- MAIN APP JS FILE -->
		<script src="js/app.min.js"></script>

		<!-- PAGE RELATED PLUGIN(S) -->
		<script src="js/plugin/datatables/jquery.dataTables.min.js"></script>
		<script src="js/plugin/datatables/dataTables.colVis.min.js"></script>
		<script src="js/plugin/datatables/dataTables.tableTools.min.js"></script>
		<script src="js/plugin/datatables/dataTables.bootstrap.min.js"></script>
		<script src="js/plugin/datatable-responsive/datatables.responsive.min.js"></script>

		<script type="text/javascript">
		function terminateInstance(instance){
      		 var id=instance.id;
       		 var name=instance.name;     
      		 $('#instId').val(id);
      		 $('#instance').text(name);
       		 $('#dialog_simple').dialog('open');
        	 return false;      
          }
		
		
		function addFloatingIP(instance){
     		 var id=instance.id;
      		 var name=instance.name;     
     		 $('#instId').val(id);
     		 $('#instanceFloating').text(name);
      		 $('#floating_dialog').dialog('open');
       	 return false;      
         }
		
		function removeFloatingIP(instance){
    		 var id=instance.id;
     		 var name=instance.name;     
    		 $('#instId').val(id);
    		 $('#instanceRemoveFloating').text(name);
     		 $('#removeFloating_dialog').dialog('open');
      	 return false;      
        }
		
	
		
		
		function terminateSelected(){
     		    $('#instance').text('Selected'); 
     		     var id="";
                 $.each($(".checkBoxClass:checked"), function(){   
                 if(id=="")	{
                     id=$(this).val();
                 } 
                 else{
                	 id=id+","+$(this).val();
                 }
                 });
                 
                 $('#instId').val(id);
     		 
      		 $('#dialog_simple').dialog('open');
       	     return false;      
         }	
		
		function getRow(value) {
			//alert("Id"+value.id);
			var varVmId=value.id;
			/*document.getElementById('VmName').value=value.id;
			alert("name"+document.getElementById('VmName').value); */
			$.getJSON("./getVMDetailsFromService/"+ varVmId,
					
			function(response) {
						//alert("hi"+response.powerState);
						
						$('#roleInstanceName').val(response.instanceName);
						$('#roleIPAddress').val(response.ipAddress);
						$('#rolePowerState').val(response.powerState);
						$('#roleStatus').val(response.status);
						$("#dt_basic1").html('');
						var myHeading = "<tr><th>" +'Name' + "</th><th>" + 'VIP' + "</th><th>" + 'public Port' + "</th><th>" + 'Local Port' + "</th><th>" + 'Protocol' + "</th></tr>";
						$(myHeading).appendTo(dt_basic1);
						$.each(response.instanceEndPointDetails, function(i){
							//alert("name"+response.instanceEndPointDetails[i].name);
							//alert("vip"+response.instanceEndPointDetails[i].vip);
							
							var mystring = "<tr><td>" + response.instanceEndPointDetails[i].name + "</td><td>" +response.instanceEndPointDetails[i].vip + "</td><td>" + response.instanceEndPointDetails[i].publicPort + "</td><td>" +response.instanceEndPointDetails[i].localPort + "</td><td>" +response.instanceEndPointDetails[i].protocol + "</td></tr>";
							$(mystring).appendTo(dt_basic1);
							});
						
						
			});
		}
		
		
       </script>

	    <script type="text/javascript">
	   // DO NOT REMOVE : GLOBAL FUNCTIONS!		
		$(document).ready(function() {	
			
			pageSetUp();			
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
           
      
            $('#dialog_simple').dialog({
                autoOpen : false,
                width : 600,
                resizable : false,
                modal : true,
                title : "<div class='widget-header'><h4><i class='fa fa-warning'></i> Delete Confirmation ?</h4></div>",
                buttons : [{
                    html : "<i class='fa fa-trash-o'></i>&nbsp; Terminate Instance",
                    "class" : "btn btn-danger",
                    click : function() {
                        document.instanceList.action = "./terminateInstance";
                           document.instanceList.submit();
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
            
            
            $('#floating_dialog').dialog({
                autoOpen : false,
                width : 600,
                resizable : false,
                modal : true,
                title : "<div class='widget-header'><h4> Add Confirmation ?:</h4></div>",
                buttons : [{
                    html : "<i class='fa fa-trash-o'></i>&nbsp; Add FloatingIP",
                    "class" : "btn btn-danger",
                    click : function() {
                        document.instanceList.action = "./addFloatingIP";
                        document.instanceList.submit();
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
            
            
            $('#removeFloating_dialog').dialog({
                autoOpen : false,
                width : 600,
                resizable : false,
                modal : true,
                title : "<div class='widget-header'><h4> Remove Confirmation ?:</h4></div>",
                buttons : [{
                    html : "<i class='fa fa-trash-o'></i>&nbsp; Remove FloatingIP",
                    "class" : "btn btn-danger",
                    click : function() {
                        document.instanceList.action = "./removeFloatingIP";
                        document.instanceList.submit();
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
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12  hidden-xs'l>r>"+
						"t"+
						"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
					"autoWidth" : true,					
					"bSort": true,
					 "oLanguage": {	"sEmptyTable": "No Instances available"},
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
			  
			  /* BASIC ;*/
				/* var responsiveHelper_dt_basic1 = undefined;
				var responsiveHelper_datatable_fixed_column = undefined;
				var responsiveHelper_datatable_col_reorder = undefined;
				var responsiveHelper_datatable_tabletools = undefined;
				
				var breakpointDefinition = {
					tablet : 1024,
					phone : 480
				};
	
				$('#dt_basic1').dataTable({
					"sDom": "<'dt-toolbar'<'col-xs-12 col-sm-6'f><'col-sm-6 col-xs-12  hidden-xs'l>r>"+
						"t"+
						"<'dt-toolbar-footer'<'col-sm-6 col-xs-12 hidden-xs'i><'col-xs-12 col-sm-6'p>>",
					"autoWidth" : true,					
					"bSort": true,
					 "sScrollY": "500px",
					 "oLanguage": {	"sEmptyTable": "No Instances available"},
					 "preDrawCallback" : function() {
						// Initialize the responsive datatables helper once.
						if (!responsiveHelper_dt_basic) {
							responsiveHelper_dt_basic = new ResponsiveDatatablesHelper($('#dt_basic1'), breakpointDefinition);
						}
					},
					"rowCallback" : function(nRow) {
						responsiveHelper_dt_basic.createExpandIcon(nRow);
					},
					"drawCallback" : function(oSettings) {
						responsiveHelper_dt_basic.respond();
					}
				});	 */
			  /* END BASIC */ 
			  
			  
			  
			  
		   var checkboxes = $(".checkBoxClass"),
		   submitButt = $("#anc_id");
			  
			$("#ckbCheckAll").click(function () {
				// var atLeastOneIsChecked = $('.checkBoxClass').is(':checked');
					 submitButt.attr("disabled", checkboxes.is(":checked")); 
			    $(".checkBoxClass").prop('checked', $(this).prop('checked'));
			});
				
		    $('.checkBoxClass').change(function () {
				   var check = ($('.checkBoxClass').filter(":checked").length == $('.checkBoxClass').length);
				   $('#ckbCheckAll').prop("checked", check);
			});
		    
	        checkboxes.click(function() {
		    submitButt.attr("disabled", !checkboxes.is(":checked"));
		     });
	        
	        submitButt.attr("disabled",true);
			$.root_.addClass("fixed-page-footer") 
			localStorage.setItem("sm-setmenu","top");
			
			setTimeout(function(){
				launchAzureInstanceDBUpdate();
		   		},1000);
			
		});

	</script>
	
	<script>
	function launchAzureInstanceDBUpdate(){
		var varCustomVMsToFetchIPAddresses = '<%= strCustomVMsToFetchIPAddresses %>';
		
		//alert(varCustomVMsToFetchIPAddresses);

    	 $.ajax({     
    		type: "POST",     
    		data: "strJsonObj="+varCustomVMsToFetchIPAddresses,
    		url: "./azurecustomlaunchdbupdate",		
    		success: function(data){
    			//alert('inside success block'+data);
    		},     
    		error:function(XMLHttpRequest){ 
    			//alert('Error Occured');
    			
    		}
    	});  
   	 
    	 
	}   
	
	function shutdownAzureCustVM(instance){
		 var id=instance.id;
		 var vmname=instance.name;
		// alert("id:"+id+"==name:"+vmname+":");
		 
		 $.ajax({     
	    		type: "POST",     
	    		data: "strShtdwnVmName="+vmname,
	    		url: "./azurecustomvmshutdown",		
	    		success: function(data){
	    			//alert('inside success block'+data);
	    		},     
	    		error:function(XMLHttpRequest){ 
	    			alert('Unable to Shutdown the Instance');
	    			
	    		}
	    	}); 
		 
	}
	</script> 
	

	</body>
</html>