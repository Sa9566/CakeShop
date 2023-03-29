package com.app.sa.Controllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

import com.app.sa.purchaseDAO.purchaseDAO;

public class deletepurchase extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("deletepurchase.jsp");
	    String msg = "";
	    
	    int orderid = Integer.parseInt(request.getParameter("delete"));
	    purchaseDAO dao = new purchaseDAO();
	    
	    try
	    {
			if(dao.delete(orderid))
			{
				msg="Deleted Successfully";
				request.setAttribute("msg", msg);
			}
			else
			{
				msg="Invalid Id";
				request.setAttribute("msg", msg);
			}
		}
	    catch (SQLException e) 
	    {
	    	msg="Server Error";
			request.setAttribute("msg", msg);
			e.printStackTrace();
		}
	    rd.forward(request, response);
	}

}
