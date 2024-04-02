<%@page import="org.jsp.todo_app.dao.ToDoDao"%>
<%@page import="org.jsp.todo_app.dto.Task"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit Task To Do</title>
    <style>
        body {
            background-image: linear-gradient(#00dbde, #fc00ff); /* Neon Color */
            color: #fff;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        form {
            background-color: #000;
            padding: 20px;
            border-radius: 50px;
            box-shadow: 0 0 10px rgba(255, 255, 255, 0.2);
            width: 350px;
            text-align: center;
            height: 72vh;
        }

        label {
            display: block;
            margin-bottom: 8px;
        }

        input {
            width: 100%;
            padding: 8px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border-radius: 20px
        }

        button {
            background-color: #00ff00; /* Neon Green */
            color: #000;
            padding: 10px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            transition: background-color 0.3s ease;
        }

        button:hover {
            background-color: #00cc00; /* Darker Green on hover */
            animation: pulse 0.5s infinite;
        }

        @keyframes pulse {
            0% {
                transform: scale(1);
            }
            50% {
                transform: scale(1.1);
            }
            100% {
                transform: scale(1);
            }
        }
    </style>
</head>
<body>

<%
int id=Integer.parseInt(request.getParameter("id")); 
ToDoDao dao=new ToDoDao();
Task task=dao.findTaskById(id);
%>
    <form action="edit-task" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="<%=task.getId()%>">
        <h1>Edit Task To Do</h1>
        <label for="taskName">Task Name</label>
        <input   type="text" name="name" value="<%= task.getName()%>">

        <label for="taskDescription">Task Description</label>
        <input type="text" name="description" id="" value="<%= task.getDescription()%>">

        <label for="taskImage">Task Image</label>
        <input type="file" name="image" ><img height="70px" width="70px"
        src="data:image/png;base64,<%=task.getEncodedImage() %>"> <br> <br>

        <button >Add Task</button>
    </form>
</body>
</html>
