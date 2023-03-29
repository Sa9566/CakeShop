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

import com.app.sa.purchaseDAO.purchaseDAO;
import com.app.sa.purchaseDTO.purchaseDTO;

public class purchaseUser extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("purchase.jsp");
		String msg = "";
		
		purchaseDAO dao = new purchaseDAO();
		
		try 
		{
			List<purchaseDTO> purchase = dao.display();
			if(purchase.isEmpty())
			{
				msg="No Data Found";
				request.setAttribute("msg", msg);
			}
			else
			{
				request.setAttribute("purchase", purchase);
				rd.forward(request, response);
			}
		}
		catch (SQLException e)
		{
			msg="Server Error!!";
			request.setAttribute("msg", msg);
			e.printStackTrace();
		}
		rd.forward(request, response);
	}

}
