package com.app.sa.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.app.sa.userDAO.userDAO;
import com.app.sa.userDTO.userDTO;

public class displayUser extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("displayuser.jsp");
		String msg = "";
        userDAO dao = new userDAO();

			List<userDTO> user = dao.display();
			if(user.isEmpty())
			{
				msg="No Records Found";
				request.setAttribute("msg", msg);	
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("user", user);
				rd.forward(request, response);
			}
	}

}
