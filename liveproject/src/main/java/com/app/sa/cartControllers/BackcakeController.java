package com.app.sa.cartControllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.app.sa.cakeDAO.cakeDAO;
import com.app.sa.cakeDTO.cakeDTO;
import com.app.sa.userDAO.userDAO;
import com.app.sa.userDTO.userDTO;

public class BackcakeController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd1 = request.getRequestDispatcher("cake.jsp");
		HttpSession ses = null;
		String msg ="";
		cakeDAO dao1 = new cakeDAO();
		List<cakeDTO> cak;	
		try 
		{

			cak = dao1.displayall();
			ses = request.getSession(false);
			ses.getAttribute("un");
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
			request.setAttribute("msg", msg);
			e.printStackTrace();
		}
      rd1.forward(request, response);
	}
}


