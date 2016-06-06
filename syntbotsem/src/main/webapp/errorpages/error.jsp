
<!DOCTYPE html>
<html lang="en-us" id="extr-page">
<%@ page isErrorPage="true"%>
<%-- <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> --%>
<jsp:useBean id="cexception" class="com.syntel.isap.provisioning.exceptions.UserNotFoundException"/>  

 <head>
        <meta charset="utf-8">
        <!--<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">-->

        <title>SyntBots Admin Dashboard</title>
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
  <body class=" ">
  
  <header id="header">
   
  	 <!-- PAGE LOGO HEADER -->
          		<%@ include file="/includefiles/logoheader.jsp" %>
	<!-- END PAGE LOGO HEADER -->
         
   <!--  <!-- pulled right: nav area -->
      <!--  <div class="pull-right">
           
              
           collapse menu button
           
            end collapse menu

            <span>
                   <a style="border-radius: 2px;cursor: default !important;display: inline-block;font-weight: 700;height: 30px;line-height: 24px;min-width: 30px;
                             padding: 2px;text-align: center;text-decoration: none !important;-moz-user-select: none;background-color: #F8F8F8;
                             background-image: -moz-linear-gradient(center top , #F8F8F8, #F1F1F1);border: 1px solid #BFBFBF;color: #6D6A69;font-size: 17px;
                             margin: 10px 0px 0px;" href="./logOut.jsp" title="Sign Out" data-action="userLogout"
                             data-logout-msg="You can improve your security further after logging out by closing this opened browser">
                           <i class="fa fa-sign-out"></i>
                    </a>
             </span>

         </div>
        end pulled right: nav area  -->
    </header>
			<!-- MAIN CONTENT -->
			
				
						<div class="jarviswidget jarviswidget-color-blueLight" id="wid-id-0" data-widget-editbutton="false" data-widget-togglebutton="false" data-widget-deletebutton="false" data-widget-fullscreenbutton="false" data-widget-custombutton="true" data-widget-sortable="false">
								
								<header>
									 
									<h2>Error Message</h2>
				
								</header>
				
								<!-- widget div-->
								<div>
				
									<!-- widget edit box -->
									<div class="jarviswidget-editbox">
										<!-- This area used as dropdown edit box -->
				
									</div>
									<!-- end widget edit box -->
				
									<!-- widget content -->
									<div class="widget-body no-padding" class="custom-scroll table-responsive" >
										<!-- content goes here -->
			      	       <span class ="danger"> Exception Message: <%=cexception.getMessage()%></span>
			      	       
			      	 <!--       <h2>OOPS! Something went wrong please check the Administrator</h2> -->
										
									</div>
									<!-- end widget content -->
				
								</div>
								<!-- end widget div -->
				
							</div>
			
			
			

  <div class="page-footer" style="padding: 15px 13px 0px;">
			<div class="row">
				<div class="col-xs-12 col-sm-6">
					<span class="txt-color-white">ISAP - Syntel, Inc. &copy; 2014-2015</span>
				</div>
			</div>
		</div>

</body>


</html>





