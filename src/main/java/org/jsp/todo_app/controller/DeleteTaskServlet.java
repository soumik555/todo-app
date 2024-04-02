package org.jsp.todo_app.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsp.todo_app.service.TodoService;

@SuppressWarnings("serial")
@WebServlet("/delete")
public class DeleteTaskServlet extends HttpServlet

{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		TodoService service=new TodoService();
		service.deleteTask(req, resp);
		
	}

}
