package com.app.sa.cartControllers;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.app.sa.cartDAO.cartDAO;
import com.app.sa.purchaseDAO.purchaseDAO;
import com.app.sa.purchaseDTO.purchaseDTO;

public class buycakecartController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		RequestDispatcher rd =  request.getRequestDispatcher("final.jsp");
		HttpSession ses = request.getSession(false);
		String username =(String)ses.getAttribute("un");
		purchaseDAO dao = new purchaseDAO();
		String msg="";
		String otp = request.getParameter("otp");
		String otp1 = request.getParameter("otp1");


		if(otp1.equals(otp))
		{
			try 
			{
				if(dao.update(username))
				{
					if(dao.delete(username))
					{
						msg="Purchased Succesfully";
						request.setAttribute("msg", msg);    	
					}
					else
					{
						msg="something wrong";
						request.setAttribute("msg", msg); 
					}
				}
				else
				{
					msg="Something Wrong1";
					request.setAttribute("msg", msg);
				}
			} 
			catch (SQLException e) 
			{
				msg="Server Error!!";
				request.setAttribute("msg", msg);
				e.printStackTrace();
			}
		}
		else
		{
			msg="Wrong Otp";
			request.setAttribute("msg", msg);
		}

		rd.forward(request, response);
	}
}
