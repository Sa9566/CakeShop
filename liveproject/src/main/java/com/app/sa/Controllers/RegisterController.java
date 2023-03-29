package com.app.sa.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import com.app.sa.userDAO.userDAO;
import com.app.sa.userDTO.userDTO;


public class RegisterController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("register.jsp");
		String msg = " ";
		userDAO dao = new userDAO();

		String user = request.getParameter("email");
		String Pass = request.getParameter("password");
		String cont = request.getParameter("contact");
		String name = request.getParameter("name");

		userDTO dto = new userDTO(user, Pass, cont, name);

		try
		{
			if(dao.register(dto))
			{
				msg="Register Succesfully....";
				request.setAttribute("msg", msg);
			}
			else
			{
				msg = "Something Wrong...";
				request.setAttribute("msg", msg);
			}
		} 
		catch (SQLException e) 
		{
			msg="Server Error, Try Again Later...";
			request.setAttribute("msg", msg);
			e.printStackTrace();
		}
     rd.forward(request, response);
	}
}

