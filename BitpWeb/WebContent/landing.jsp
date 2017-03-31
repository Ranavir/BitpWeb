<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Welcome</title>
		<meta charset="utf-8">
		<meta name = "format-detection" content = "telephone=no" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
		<meta name="theme-color" content="#042048" />

	<!-- 	<link rel="shortcut icon" href="assets/images/favicon.png" type="image/x-icon">
		<link rel="icon" href="assets/images/favicon.png" type="image/x-icon"> -->
		
		<!-- Bootstrap -->
		<link href="plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="https://cdn.datatables.net/1.10.13/css/dataTables.bootstrap.min.css" rel="stylesheet" media="screen">
		<link href="plugins/chosen/chosen.css" rel="stylesheet" media="screen">
		<link href="plugins/chosen/prism.css" rel="stylesheet" media="screen">
		<link href="plugins/datepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet" media="screen">
		<link href="https://cdn.datatables.net/rowreorder/1.2.0/css/rowReorder.dataTables.min.css" rel="stylesheet" media="screen">
		<link href="https://cdn.datatables.net/responsive/2.1.1/css/responsive.dataTables.min.css" rel="stylesheet" media="screen">
		<link href="assets/css/custom.css" rel="stylesheet" media="screen">

		<!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
		<!--[if lt IE 9]>
			<script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
		<![endif]-->

		<script src="plugins/jquery/jquery.min.js"></script>


</head>
<body>
	<nav class="navbar navbar-default base cusNav">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="user/index"><img src="assets/images/National-Health-Mission.png"/></a>
			</div>
			<div id="navbar" class="navbar-collapse collapse">
				<ul class="nav navbar-nav navbar-right">
											<li class=""><a href="#">Home</a></li> 
					
					<li class="dropdown">
						<a href="#" role="button" class="dropdown-toggle userIcon" data-toggle="dropdown"><i class="fa fa-user"></i>Profile</a>
						<ul class="dropdown-menu">
							<li><a href="#"><i class="ci key"></i> Change password</a></li>
							<li><a href="index.jsp"><i class="ci logout"></i> Logout</a></li>
						</ul>
					</li>
				</ul>
			</div><!--/.nav-collapse -->
		</div>
    </nav>
	<div class="container-fluid entry-header">
	<h1 class="entry-title">Online Exam Result Reconciliation</h1>
</div>

<div class="container-fluid vh100">
	<div class = "row">
	<div class="col-md-5">
	</div>	
		<div class="col-md-2">	
			<div class="form-group">
				<label style="margin-left: 22%;">Online Exam List</label>
				<select name="blood_group" class="form-control" id="examCodeList">
					
				</select>
			</div>
		</div>
	</div>
	


	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-12">
			<div class="panel panel-default">
				<div class="panel-heading"><div class="panel-title">Detail Results</div></div>
				<div class="panel-body">
					<table class="table table-condensed table-striped table-bordered data-table display nowrap" id="studentList" width="100%">
					<thead>
						<tr>
						<th>User Id</th>
						<th>Student Name</th>
						<th>Exam Code</th>
						<th>Exam Title</th>
						<th>Online ExamMark Tot.</th>
						<th>Interview Mark Tot.</th>
						<th>Tot.Cutoff Mark</th>
						<th>Exam StartTime</th>
						<th>Exam EndTime</th>
						<th>Exam SessionId</th>
						<th>Marks secured Online Exam</th>
						<th>Marks Secured Interview</th>
						<th>Selection Status</th>
						<th>Edit</th>
					</tr>
					</thead>
					<tbody>
					</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>	
	<footer class="container-fluid site-footer text-center">
			<div class="row">
				<div class="col-sm-2"><img src="./assets/images/odisha-sasan.png" class="ft-img"></div>
				<div class="col-sm-8">
					 <div class="row">
						
					</div> 
					<hr>
					<h3>Biotech Industrial Training Programme</h3> 
					&copy; All Rights Reserved. Bitp.com
				</div>
				<div class="col-sm-2"><img src="./assets/images/Emblem_of_India.png" class="ft-img"></div>
			</div>
    </footer>
	<div class="modal fade" id="myModalTxtArea" role="dialog">
    <div class="modal-dialog">
    
      <!-- Modal content-->
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">Enter Mark</h4>
        </div>
        <div class="modal-body">
           <div class="form-group">
               <label for="inputComment">Enter Mark Secured Online Exam</label>
               <input type="text" class="form-control" id="marktextfield" style="width: 100px;"  >
               <input type="hidden" class="form-control" id="totalMark" style="width: 100px;"  >
               <input type="hidden" class="form-control" id="uid" style="width: 100px;"  >
               <input type="hidden" class="form-control" id="examcode" style="width: 100px;"  >
        	</div>
        <div class="modal-footer">
          <button type="button" class="btn btn-default"  id="mdalSendBtnForAll">Update</button>
        </div>
      </div>
      
    </div>
    
</div>
</div>
	
	
	<script src="plugins/jquery/jquery.validate.min.js"></script>
	<script src="plugins/bootstrap/js/bootstrap.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.13/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/1.10.13/js/dataTables.bootstrap.min.js"> </script>
	<script src="https://cdn.datatables.net/rowreorder/1.2.0/js/dataTables.rowReorder.min.js"> </script>
	<script src="https://cdn.datatables.net/responsive/2.1.1/js/dataTables.responsive.min.js"> </script>
	<script src="plugins/chosen/prism.js"></script>
	<script src="plugins/chosen/chosen.jquery.min.js"></script>
	<script src="plugins/datepicker/js/moment.js"></script>
    <script src="plugins/datepicker/js/bootstrap-datetimepicker.min.js"></script>
    
	<script src="assets/js/custom-form-validator.js"></script>
	<script src="assets/js/custom.js"></script>
	<script type="text/javascript" src="myJs/landing.js"></script>
   
</body>
</html>


    