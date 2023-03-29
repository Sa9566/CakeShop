package com.app.sa.cartControllers;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.app.sa.cakeDAO.cakeDAO;
import com.app.sa.cakeDTO.cakeDTO;
import com.app.sa.cartDAO.cartDAO;
import com.app.sa.cartDTO.cartDTO;

public class displaycartController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd = request.getRequestDispatcher("viewcart.jsp");
		String msg = "";
		cartDAO dao = new cartDAO();
		 
		List<cartDTO> cart;
		try 
		{
			cart = dao.displayall();
			if(cart.isEmpty())
			{
				msg="No Data Found";
				request.setAttribute("msg", msg);
			}
			else
			{
				request.setAttribute("cart", cart);
				rd.forward(request, response);
			}

		}
		catch (SQLException e)
		{
			msg="Server Error!!!";
			request.setAttribute("msg", msg);
			e.printStackTrace();
		}
		rd.forward(request, response);
	 }
}