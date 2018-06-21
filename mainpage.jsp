<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
  <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
<title>Display Page</title>


<script>
$(document).ready(function(){
    $("#tab2").hide();
    $("#tab1").show();

	
    $("#tab1link").click(function(){
        $("#tab2").hide();
        $("#tab1").show();
    });
    $("#tab2link").click(function(){
    	$("#tab1").hide();
        $("#tab2").show();
    });
});
</script>


</head>
<body>
<center><h1>Search Engine for Tennis</h1></center>
  <div align="center">
  <br/><br/>
    <form class="form-inline" method="get" action="controller">
          <input type="text" class="form-control" size="90" name="searchquery"/>
          <button type="submit" class="btn btn-primary">submit</button>
    </form>
  </div>
  
  <br/>
  <br/>
  <div class="container-fluid">
  <ul class="nav nav-tabs">
    <li  class="active"><a id="tab1link" href="#">Tab 1</a></li>
    <li><a id="tab2link" href="#">Tab 2</a></li> 
  </ul>
    <div id="tab1" >
       <%@include file="tabpage2.jsp" %>  
   </div>
  <div id="tab2" >
     <%@include file="tabpage1.jsp" %>  
   </div>
  <br>
</div>

</body>
</html>
