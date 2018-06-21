<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
  <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<style>

.smalllink
{
   color:grey;
}

.rowTag{
text-wrap:word-wrap;
	
}
.left{
	float:left;
}
.right{
	float:right;
}

#cluster
{
height:800px;
overflow:auto;
}

#hits
{
height:800px;
overflow:auto;
}


#pagerank
{
height:800px;
overflow:auto;
}

#queryexp
{
height:800px;
overflow:auto;
}
</style>
</head>
<body>
<div class="container-fluid">
<div class="row rowTag">
	<div class="col-sm-3" id="hits">
	
		<%
			ArrayList<ArrayList<String>> mydata1 = (ArrayList<ArrayList<String>>) request.getAttribute("results1");
			if (mydata1 != null) {%>
				<h2>Hits</h2>
				  <%  for(int i=0;i<mydata1.size();i++)
				   {%>
				   <a href="<%=mydata1.get(i).get(1)%>"><%=mydata1.get(i).get(0)%></a><br/>
				   <a class="smalllink" href="<%=mydata1.get(i).get(1)%>" style="font-size:8px;"><%=mydata1.get(i).get(1)%></a><br/><br/>
				   
				  <%} %> 					
			<% 	}
          %>
	</div>
	<div class="col-sm-3" id="cluster">

	
		<%
			ArrayList<ArrayList<String>> mydata4 = (ArrayList<ArrayList<String>>) request.getAttribute("clustering");
			if (mydata4 != null) {%>
				<h2>Clustering</h2>
				   <% for(int i=0;i<mydata4.size();i++)
					 
				   {  //System.out.println("data:"+mydata4.get(i).get(0));
				         if(mydata4.get(i).get(0).isEmpty())
				        	 continue;
				   
				   %>
				   <a href="<%=mydata4.get(i).get(1)%>"><%=mydata4.get(i).get(0)%></a><br/>
				   <a class="smalllink" href="<%=mydata4.get(i).get(1)%>" style="font-size:8px;"><%=mydata4.get(i).get(1)%></a>
				   <p><%=mydata4.get(i).get(2) %></p>
				      
				  <%} %> 
					
					
			<% 	}
          %>




	</div>
	<div class="col-sm-3" id="pagerank">

	
		<%
			ArrayList<ArrayList<String>> mydata2 = (ArrayList<ArrayList<String>>) request.getAttribute("results2");
			if (mydata1 != null) {%>
				<h2>Page Ranking</h2>
				  <%  for(int i=0;i<mydata2.size();i++)
				   {%>
				   <a href="<%=mydata2.get(i).get(1)%>"><%=mydata2.get(i).get(0)%></a><br/>
				   <a class="smalllink" href="<%=mydata2.get(i).get(1)%>" style="font-size:8px;"><%=mydata2.get(i).get(1)%></a><br/><br/>
				      
				  <%} %> 
					
					
			<% 	}
          %>




</div>

<div class="col-sm-3" id="queryexp">
<% String exp=new String();%>
	<%
	    if(!(request.getAttribute("expand")==null))
	    	exp=request.getAttribute("expand").toString();
	    else
	    	exp=" ";
	%>
	
	
		<%
			ArrayList<ArrayList<String>> mydata3 = (ArrayList<ArrayList<String>>) request.getAttribute("queryexpansion");
			if (mydata1 != null) {%>
				<h2>After Query Expansion</h2>
				<h3>Expanded query is:<%=exp %></h3>
				
				   <%for(int i=0;i<mydata3.size();i++)
				   {%>
				   <a href="<%=mydata3.get(i).get(1)%>"><%=mydata3.get(i).get(0)%></a><br/>
				   <a class="smalllink" href="<%=mydata3.get(i).get(1)%>" style="font-size:8px;"><%=mydata3.get(i).get(1)%></a><br/><br/>
				   
				  <%} %> 
					
					
			<% 	}
          %>


</div>
	
</div>
</div>



</body>
</html>
