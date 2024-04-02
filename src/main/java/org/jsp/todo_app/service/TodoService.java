package org.jsp.todo_app.service;

import java.io.IOException;





import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.jsp.todo_app.dao.ToDoDao;
import org.jsp.todo_app.dto.Task;
import org.jsp.todo_app.dto.User;

public class TodoService {
	
	ToDoDao dao=new ToDoDao();
	public void signup(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
	{
		User user=new User();
		
		//for receiving from frontend
		String name=req.getParameter("name");
		String email=req.getParameter("email");
		String password=req.getParameter("password");
		long mobile=Long.parseLong(req.getParameter("mobile"));
		String dob=req.getParameter("dob");
		String gender=req.getParameter("gender");
		
		
		//signup Logic
		if(dao.findByEmail(email).isEmpty() &&dao.findByMobile(mobile).isEmpty())
		{
//			User user=new User();
			
			
			
			user.setDob(LocalDate.parse(dob));
			user.setEmail(email);
			user.setGender(gender);
			user.setMobile(mobile);
			user.setName(name);
			user.setPassword(password);
			
			dao.saveUser(user);
			
			resp.getWriter().print("<h1 align='center' style='color:green'>Account created Successfully: </h1>");
			req.getRequestDispatcher("login2.html").include(req, resp);
			
		}
		else 
		{
			if(dao.findByEmail(email) .isEmpty())
			{
				
			
			resp.getWriter().print("<h1 align='center' style='color:red'>Mobile Number " + mobile+ " is already present in our database</h1>");
			req.getRequestDispatcher("login2.html").include(req, resp);
			}
			
			
			
			else if(dao.findByMobile(mobile) .isEmpty() )
			{
				resp.getWriter().print("<h1 align='center' style='color:red'>Email " + email+ " is already present in our database</h1>");
				req.getRequestDispatcher("login2.html").include(req, resp);
				
			}
		
		
		
		
		else
		{
			resp.getWriter().print("<h1 align='center' style='color:red'>Email " + email+ " and mobile number "+ mobile+ " is already present in our database</h1>");
			req.getRequestDispatcher("login2.html").include(req, resp);
		}
	}
		
		
		
		
		
	}
	
	
	//for login logic
	public void login(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		String emph = req.getParameter("emph");
		String passsword = req.getParameter("pass");
		
		//To check duplicate we are using list
		List<User> list = null;
		
		//if mobile try will execute
		try {
			long mobile = Long.parseLong(emph);
			list = dao.findByMobile(mobile);
			if (list.isEmpty())
				resp.getWriter().print("<h1 align='center' style='color:red'>Incorrect Mobile Number</h1>");
		}
	
		
		//if email then catch will execute
		catch (NumberFormatException e) 
		{
			String email = emph;
			list = dao.findByEmail(email);
			if (list.isEmpty())
				resp.getWriter().print("<h1 align='center' style='color:red'>Incorrect Email</h1>");
		}

		if (!list.isEmpty()) 
		
		{
			User user=list.get(0);
			if (list.get(0).getPassword().equals(passsword)) 
			{
				
				
				req.getSession().setAttribute("user", user);
				resp.getWriter().print("<h1 align='center' style='color:green'>Login Success</h1>");
				
				List<Task>tasks=dao.fetchTasksByUserId(user.getId());
				req.setAttribute("tasks", tasks);
				
				
				req.getRequestDispatcher("home.jsp").include(req, resp);
				
				
				
			} 
			
			
			
			
			else 
			{
				resp.getWriter().print("<h1 align='center' style='color:red'>Incorrect Password</h1>");
				req.getRequestDispatcher("login2.html").include(req, resp);
			}
		} 
		else 
		{
			req.getRequestDispatcher("login2.html").include(req, resp);
		}
	}
	
	
	//for logout logic
	public void logout(HttpServletRequest req, HttpServletResponse resp) throws IOException ,ServletException
	{
		req.getSession().removeAttribute("user");
		resp.getWriter().print("<h1 align='center' style='color:green'>Logout Success</h1>");
		req.getRequestDispatcher("login2.html").include(req, resp);
		req.getRequestedSessionId();
		
		
	}

    //for Add task logic
	public void addTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
	{
		
		String name=req.getParameter("name");
		String description=req.getParameter("description");
		Part image=req.getPart("image");
		
		Task task=new Task();
		task.setName(name);
		task.setDescription(description);
		task.setStatus(false);
		task.setAddedTime(LocalDateTime.now());
		
		byte[] pic=new byte[image.getInputStream().available()];
		image.getInputStream().read(pic);
		
		task.setImage(pic);
		
		User user=(User) req.getSession().getAttribute("user");
		task.setUser(user);
		dao.saveTask(task);
		
		resp.getWriter().print("<h1 align='center' style='color:green'>Task added Success</h1>");
		

		List<Task>tasks=dao.fetchTasksByUserId(user.getId());
		req.setAttribute("tasks", tasks);
		
		
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
	
	public void completeTask(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		Task task=dao.findTaskById(id);
		task.setStatus(true);
		dao.updateTask(task);
		
		resp.getWriter().print("<h1>status changed Success</h1>");
		
		User user=(User)req.getSession().getAttribute("user");
		
		List<Task> tasks=dao.fetchTasksByUserId(user.getId());
		req.setAttribute("tasks",tasks);
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
		
	}
	
	
	public void deleteTask(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		int id=Integer.parseInt(req.getParameter("id"));
		Task task=dao.findTaskById(id);
		dao.delete(task);
		
		resp.getWriter().print("<h1 align='center' style='color:green'>Task Deleted Successfully</h1>");
		
		User user=(User)req.getSession().getAttribute("user");
		
		List<Task> tasks=dao.fetchTasksByUserId(user.getId());
		req.setAttribute("tasks",tasks);
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
		
	}
	
	
	public void updateTask(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
	{
		String name=req.getParameter("name");
		String description=req.getParameter("description");
		Part image=req.getPart("image");
		int id=Integer.parseInt(req.getParameter("id"));
		
		Task task=new Task();
		task.setId(id);
		task.setName(name);
		task.setDescription(description);
		task.setStatus(false);
		task.setAddedTime(LocalDateTime.now());
		
		byte[] pic=new byte[image.getInputStream().available()];
		image.getInputStream().read(pic);
		
		if(pic.length==0)
		{
			task.setImage(dao.findTaskById(id).getImage());
		}
		else
		{
			task.setImage(pic);
		}
		
		User user=(User)req.getSession().getAttribute("user");
		task.setUser(user);
		
		dao.updateTask(task);
		
		resp.getWriter().print("<h1 align='center' style='color:green'>Task updated Success</h1>");
		
		List<Task> tasks=dao.fetchTasksByUserId(user.getId());
		req.setAttribute("tasks",tasks);
		
		req.getRequestDispatcher("home.jsp").include(req, resp);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	public void completetask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
//	{
//		int id=Integer.parseInt(req.getParameter("id"));
//		Task task=dao.findTaskById(id);
//		task.setStatus(true);
//		dao.updateTask(task);
//		
//		resp.getWriter().print("<h1 align='center' style='color:green'>Status changed successfully:</h1>");
//		
//		User user=(User)req.getSession().getAttribute("user");
//		List<Task> tasks=dao.fetchTasksByUserId(user.getId());
//		req.setAttribute("task", tasks);
//		
//		req.getRequestDispatcher("home.jsp").include(req, resp);
//		
//	}
	
//	public void deletetask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException
//	{
//		int id=Integer.parseInt(req.getParameter("id"));
//		Task task=dao.findTaskById(id);
//		dao.delete(task);
//		
//		resp.getWriter().print("<h1 align='center' style='color:green'>Task deleted  successfully:</h1>");
//		
//		User user=(User)req.getSession().getAttribute("user");
//		List<Task> tasks=dao.fetchTasksByUserId(user.getId());
//		req.setAttribute("task", tasks);
//		
//		req.getRequestDispatcher("home.jsp").include(req, resp);
//		
//	}
	
//	
//	public void deleteTask(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException 
//	{
//		int id=Integer.parseInt(req.getParameter("id"));
//		Task task=dao.findTaskById(id);
//		dao.delete(task);
//		
//		resp.getWriter().print("<h1 align='center' style='color:green'>Task Deleted Successfully</h1>");
//		
//		User user=(User)req.getSession().getAttribute("user");
//		List<Task> tasks=dao.fetchTasksByUserId(user.getId());
//		req.setAttribute("task", tasks);
//		
//		req.getRequestDispatcher("home.jsp").include(req,resp);
//		
//		
//	}
	
	
//	
//	public void updateTask(HttpServletRequest req,HttpServletResponse resp) throws IOException, ServletException
//	{
//		String name=req.getParameter("taskname");
//		String description=req.getParameter("description");
//		Part image=req.getPart("taskimage");
//		int id=Integer.parseInt(req.getParameter("id"));
//		
//		Task task=new Task();
//		task.setName(name);
//		task.setDescription(description);
//		task.setStatus(false);
//		task.setAddedTime(LocalDateTime.now());
//		
//		byte[] pic=new byte[image.getInputStream().available()];
//		image.getInputStream().read(pic);
//		
//		if(pic.length==0)
//		{
//			task.setImage(dao.findTaskById(id).getImage());
//		}
//		else
//		{
//			task.setImage(pic);
//		}
//		
//		User user=(User)req.getSession().getAttribute("user");
//		task.setUser(user);
//		
//		dao.saveTask(task);
//		
//		resp.getWriter().print("<h1>Task updated Success</h1>");
//		
//		List<Task> tasks=dao.fetchTasksByUserId(user.getId());
//		req.setAttribute("tasks",tasks);
//		
//		req.getRequestDispatcher("home.jsp").include(req, resp);
//	}
//	
	
	
	
	

}
