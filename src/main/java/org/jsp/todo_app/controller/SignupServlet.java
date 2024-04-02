package org.jsp.todo_app.controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jsp.todo_app.dto.User;
import org.jsp.todo_app.service.TodoService;

@WebServlet("/signup2")
public class SignupServlet extends HttpServlet
{
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		
//		String name=req.getParameter("name");
//		String email=req.getParameter("email");
//		String password=req.getParameter("password");
//		String mobile=req.getParameter("mobile");
//		String dob=req.getParameter("dob");
//		String gender=req.getParameter("gender");
		
//		resp.getWriter().print("<h1>" + name +"</h1>");
//		resp.getWriter().print("<h1>" + email +"</h1>");
//		resp.getWriter().print("<h1>" + password +"</h1>");
//		resp.getWriter().print("<h1>" + mobile +"</h1>");
//		resp.getWriter().print("<h1>" + dob +"</h1>");
//		resp.getWriter().print("<h1>" + gender +"</h1>");
		
		TodoService service= new TodoService();
		service.signup(req, resp);
		
		
		
		
		
	
		
		
		
//		EntityManagerFactory factory=Persistence.createEntityManagerFactory("m7");
//		EntityManager manager=factory.createEntityManager();
//		EntityTransaction transaction=manager.getTransaction();
//		
//		transaction.begin();
//		manager.persist(user);
//		transaction.commit();
//		
//		resp.getWriter().print("<h1 style='color:blue'> Account Created Successfully: </h1>");
//		req.getRequestDispatcher("login2.html").include(req, resp);
//		System.out.println("Data has been inserted successfully:");
		
	}

	

}
