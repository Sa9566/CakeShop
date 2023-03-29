package com.app.sa.Controllers;

import jakarta.servlet.ServletException;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.app.sa.purchaseDAO.purchaseDAO;

public class LogoutController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		purchaseDAO dao = new purchaseDAO();
		HttpSession ses = request.getSession(false);
		if(ses!=null)
		{
			String username = (String)ses.getAttribute("un");
			try
			{
				if(dao.logout(username))
				{
					ses.invalidate();
				}
			}
			catch (SQLException e) 
			{
				e.printStackTrace();
			}
		}
		response.sendRedirect("index.html");
	}

}
