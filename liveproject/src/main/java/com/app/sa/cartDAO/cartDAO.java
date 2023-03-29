package com.app.sa.cartDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sa.cartDTO.cartDTO;
import com.app.sa.util.Dbutil;
import com.mysql.cj.jdbc.CallableStatement;

public class cartDAO 
{
   public boolean insert(cartDTO dto) throws SQLException
   {
	   boolean r = true;
      Connection c = Dbutil.getConnection();
      
      PreparedStatement pst = c.prepareStatement("insert into viewcart values(?,?,?,?,?,?)");
      pst.setInt(1, dto.getOrderid());   
      pst.setInt(2, dto.getCakeid());
      pst.setString(3, dto.getUsername());
      pst.setString(4, dto.getCakename());
      pst.setDouble(5, dto.getPrice());
      pst.setString(6, dto.getKilogram());

      int nor = pst.executeUpdate();
      
      if(nor!=1)
      {
    	  r = false;
      }
      Dbutil.Close(c);
      return r;
   }
   public double updatequan(String username) throws SQLException
   {
	   double db=0.0;
	   
	   Connection c = Dbutil.getConnection();
	   
	   PreparedStatement ps = c.prepareStatement("SELECT sum(viewcart.price)\r\n"
	   		+ "from cake.viewcart where username=?;");
	   ps.setString(1, username);
	   
	   ResultSet t = ps.executeQuery();
	   
	   if(t.next())
	   {
		   db = t.getDouble(1);
	   }
	   Dbutil.Close(c);
	   return db;
   }
   public List<cartDTO> displayall() throws SQLException
   {
	   List<cartDTO> cart = new ArrayList<cartDTO>();
	   
	   Connection c = Dbutil.getConnection();

		PreparedStatement ps = c.prepareStatement("select * from viewcart");

		ResultSet r = ps.executeQuery();

		while(r.next())
		{
			cartDTO dto = new cartDTO();
			dto.setOrderid(r.getInt(1));
			dto.setCakeid(r.getInt(2));
			dto.setUsername(r.getString(3));
			dto.setCakename(r.getString(4));
			dto.setPrice(r.getDouble(5));
			dto.setKilogram(r.getString(6));
			cart.add(dto);
		}
		Dbutil.Close(c);
		return cart;
   }
   public boolean delete(int orderid) throws SQLException
   {
	   Connection c = Dbutil.getConnection();
	
	   java.sql.CallableStatement cs = c.prepareCall("{call deletecart(?)}");
	   cs.setInt(1, orderid);
		
	   boolean r = cs.execute();
		Dbutil.Close(c);
		return r;
   }
   public boolean update(int orderid) throws SQLException
   {
	   
	   Connection c = Dbutil.getConnection();
	   
	   PreparedStatement ps = c.prepareStatement("update cake.cakepage set quantity=quantity-1\r\n"
	   		+ "where cakepage.quantity>=1 and cakepage.cakeid = (select viewcart.cakeid\r\n"
	   		+ "from cake.viewcart\r\n"
	   		+ "where viewcart.orderid=?);");
	   ps.setInt(1, orderid);
	   
	   boolean r = ps.execute();
	   
	   Dbutil.Close(c);
	   return r;
   }
}
