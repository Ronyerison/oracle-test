<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base href="/oracle-test/">
<title>Oracle Test</title>

<!-- ######################### LIBRARIES #########################-->
<script type="text/javascript" src="resources/lib/jquery/jquery.min.js"></script>
<script type="text/javascript" src="resources/lib/angular/angular.js"></script>
<script type="text/javascript" src="resources/lib/angular/angular-ui-router.min.js"></script>
<script type="text/javascript" src="resources/lib/angular/angular-resource.min.js"></script>
<script type="text/javascript" src="resources/lib/angular/angular-cookies.min.js"></script>
<script type="text/javascript" src="resources/lib/sb-admin-2/sb-admin-2.js"></script>
<script type="text/javascript" src="resources/lib/metisMenu/metisMenu.min.js"></script>
<script type="text/javascript" src="resources/lib/bootstrap/bootstrap.js"></script>

<script type="text/javascript" src="resources/js/app.js"></script>

<link rel="stylesheet" type="text/css" href="resources/lib/bootstrap/bootstrap.css">
<link rel="stylesheet" type="text/css" href="resources/css/app.css">
<link rel="stylesheet" type="text/css" href="resources/lib/sb-admin-2/sb-admin-2.css">
<link rel="stylesheet" type="text/css" href="resources/lib/metisMenu/metisMenu.min.css" >
<link rel="stylesheet" type="text/css" href="resources/lib/font-awesome/css/font-awesome.min.css" >



<!-- ######################### CONTROLLERS #########################-->
<script type="text/javascript" src="resources/js/controllers/TodoController.js"></script>
<script type="text/javascript" src="resources/js/controllers/ApplicationController.js"></script>
<script type="text/javascript" src="resources/js/controllers/UserController.js"></script>


<!-- ########################### SERVICES ##########################-->
<script type="text/javascript" src="resources/js/services/TodoService.js"></script>
<script type="text/javascript" src="resources/js/services/ApplicationService.js"></script>
<script type="text/javascript" src="resources/js/services/UserService.js"></script>


</head>

<body ng-app="oracle-test">
	<div ui-view></div>
</body>
</html>