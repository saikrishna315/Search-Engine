<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>

.smalllink
{
   color:grey;
}

.divtag
{
 display: inline-block;
  float: left;
  width:50%;
  height:100%;
}
</style>
</head>
<body>
<div class="divtag">

		<%
			ArrayList<ArrayList<String>> googleData = (ArrayList<ArrayList<String>>) request.getAttribute("googleresults");
			if (googleData != null) {%>
				<h2>Google Search</h2>
				   <% for(int i=0;i<googleData.size();i++)
				   {%>
				   <a href="<%=googleData.get(i).get(1)%>"><%=googleData.get(i).get(0)%></a><br/>
				   <a class="smalllink" href="<%=googleData.get(i).get(1)%>" style="font-size:8px;"><%=googleData.get(i).get(1)%></a><br/><br/>
				   
				  <%} %> 
					
					
			<% 	}
          %>


</div>
<div class="divtag">

	
		<%
			ArrayList<ArrayList<String>> bingData = (ArrayList<ArrayList<String>>) request.getAttribute("bingresults");
			if (bingData != null) {%>
				<h2>Bing Search</h2>
				  <% for(int i=0;i<8;i++)
				   {%>
				   <a href="<%=bingData.get(i).get(1)%>"><%=bingData.get(i).get(0)%></a><br/>
				   <a class="smalllink" href="<%=bingData.get(i).get(1)%>" style="font-size:8px;"><%=bingData.get(i).get(1)%><br/><br/>
				      
				  <%} %> 
					
					
			<% 	}
          %>




</div>




</body>
</html>
