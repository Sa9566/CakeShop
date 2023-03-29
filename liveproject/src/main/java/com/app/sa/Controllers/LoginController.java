package com.app.sa.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.app.sa.cakeDAO.cakeDAO;
import com.app.sa.cakeDTO.cakeDTO;
import com.app.sa.userDAO.userDAO;
import com.app.sa.userDTO.userDTO;

public class LoginController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
		RequestDispatcher rd1 = request.getRequestDispatcher("cake.jsp");
		RequestDispatcher rd2 = request.getRequestDispatcher("admin.jsp");
		HttpSession ses = null;
		String msg ="";
		userDAO dao = new userDAO();
		userDTO dto = new userDTO();
		cakeDAO dao1 = new cakeDAO();
		List<cakeDTO> cak;
		String username = null;
 		dto.setUsername(request.getParameter("email"));
	    dto.setPassword(request.getParameter("password"));
	    try
	    {
			if(dao.login(dto))
			{		
				try 
				{		
					cak = dao1.displayall();
					ses = request.getSession(true);
					username = dto.getUsername();
					ses.setAttribute("un", username);
					ses.setMaxInactiveInterval(500);
					if(cak.isEmpty())
					{
						msg="No Records Found";
						request.setAttribute("msg", msg);
					}
					else
					{
					  request.setAttribute("cak", cak);
					  rd1.forward(request, response);
					}
				}
				catch (SQLException e) 
				{
					msg="Server Error!!!";
					e.printStackTrace();
				}
				
			}
			else if(dao.login1(dto))
			{
		        HttpSession ses1 = request.getSession(true);
		        ses1.setAttribute("user", dto.getUsername());
		 		rd2.forward(request, response);
			}
			else
			{
				msg="Invalid Credentials...";
				request.setAttribute("msg", msg);
			}
		
		} catch (SQLException e) 
	    {
			msg="Server Error!!!";
			request.setAttribute("msg", msg);
			e.printStackTrace();
		}
	    
	    rd.forward(request, response);
	}

	
}
