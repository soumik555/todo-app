
<%@page import="org.jsp.todo_app.dto.Task"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Home Page</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 0;
      padding: 0;
      background-color: #f2f2f2;
    }

    header {
      background-color: #333;
      color: white;
      text-align: center;
      padding: 15px;
    }

    table {
      width: 80%;
      margin: 20px auto;
      border-collapse: collapse;
      background-color: white;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    th, td {
      padding: 15px;
      text-align: left;
      border-bottom: 1px solid #ddd;
    }

    th {
      background-color: #4CAF50;
      color: white;
    }

    button {
      background-color: #4CAF50;
      color: white;
      padding: 10px 15px;
      border: none;
      border-radius: 5px;
      cursor: pointer;
      transition: background-color 0.3s, transform 0.3s;
    }

    button:hover {
      background-color: #ff3131; /* Neon red */
      transform: scale(1.05);
    }
    #edit:hover
    {
    background-color: #cfff04; /* Neon yellow */
      transform: scale(1.05);
    }
    #complete:hover 
	{
	 background-color:  #4D4DFF;/* Neon blue */
      transform: scale(1.05);
	
	}
	#tb
	{
	background-color: yellow;
	}
	#delete
	{
	background-color: #ff3131;
	}
	#edit
	{
	background-color: 	#9ACD32;
	}
	#addtsk
	{
	   background-color: #B332CD;
	}
	#lgout
	{
	background-color: #FE0000;
	}

    
  </style>
</head>
<body>

  <header>
    <h1>Home Page</h1>
    <%
	List<Task>tasks=(List<Task>)request.getAttribute("tasks");
	%>
	
	<%
	if(!tasks.isEmpty())
    {
	%>
  </header>

  <table border="6px" cellspacing="8px" cellpadding="8px">
    <thead >
      <tr id="tb">
        <th>Task Image</th>
        <th>Task Name</th>
        <th>Task Description</th>
        <th>Created Time</th>
        <th>Status</th>
        <th>Delete</th>
        <th>Edit</th>
      </tr>



      <%
      for(Task task:tasks){
      %>
      <tr>
          <th><img height="70px" width="70px" alt="" src="data:image/png;base64,<%= task.getEncodedImage()%>"/></th>
          <th><%=task.getName()%></th>
          <th><%=task.getDescription()%></th>
          <th><%=task.getAddedTime()%></th>
          <th><%if(task.isStatus()) {%>
          Completed<%} else{%><a href="complete-task?id=<%=task.getId()%>"><button id="complete">Complete</button></a><%} %></th>
          <th><a href="delete?id=<%=task.getId()%>"><button id="delete">Delete</button></a></th>
          <th><a href="edit-task.jsp?id=<%=task.getId()%>"><button id="edit">Edit</button></a></th>
      </tr>
      <%
      }
      %>
    </thead>
    <tbody>
      <!-- Add your table rows here -->
    </tbody>
  </table>

  <%
}
%>

  <div style="text-align: center;">
    <a href="add-task.html"> <button id="addtsk">Add Task</button></a>
    <a href="logout"><button id="lgout">Logout</button></a>
  </div>

 

</body>
</html>
