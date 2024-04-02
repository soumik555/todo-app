package project;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/log")
public class Login extends HttpServlet
{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String mobile=req.getParameter("mobile");
		String password=req.getParameter("password");
		
		
		resp.getWriter().println("<h1> Login Done successfully"+ "</h1>");
		resp.getWriter().println("<h1> The mobile number entered is "+mobile+ "</h1>");
		resp.getWriter().println("<h1> The password  entered is "+password + "</h1>");
		
	}

}
