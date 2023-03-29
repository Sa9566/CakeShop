package com.app.sa.purchaseDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.app.sa.purchaseDTO.purchaseDTO;
import com.app.sa.util.Dbutil;


public class purchaseDAO 
{
	public boolean insert(purchaseDTO dto) throws SQLException
	   {
		   boolean r = true;
	      Connection c = Dbutil.getConnection();
	      
	      PreparedStatement pst = c.prepareStatement("insert into purchasedetail values(?,?,?,?,?,?,?)");
	      pst.setInt(1, dto.getOrderid());   
	      pst.setInt(2, dto.getCakeid());
	      pst.setString(3, dto.getUsername());
	      pst.setString(4, dto.getCakename());
	      pst.setDouble(5, dto.getPrice());
	      pst.setString(6, dto.getKilogram());
          pst.setString(7, dto.getPurchasestatus());
	      int nor = pst.executeUpdate();
	      
	      if(nor!=1)
	      {
	    	  r = false;
	      }
	      Dbutil.Close(c);
	      return r;
	   }

    public boolean delete(String username) throws SQLException
    {
    	boolean re = true;
    	
    	Connection c = Dbutil.getConnection();
    	
    	PreparedStatement ps = c.prepareStatement("delete from viewcart where username=?");		
         ps.setString(1, username);
         
         ps.executeUpdate();
         
        Dbutil.Close(c);
    	return re;
    }
    public boolean delete(int orderid) throws SQLException
    {
    	boolean re = true;
    	
    	Connection c = Dbutil.getConnection();
    	
    	PreparedStatement ps = c.prepareStatement("delete from purchasedetail where orderid=?");		
         ps.setInt(1, orderid);
         
         int nord = ps.executeUpdate();
         
         if(nord!=1)
         {
        	 re = false;
         }
    	Dbutil.Close(c);
    	return re;
    }
 public boolean update(String username) throws SQLException
 {
	 boolean  re= true;
	 
	 Connection c = Dbutil.getConnection();
	 
	 PreparedStatement ps = c.prepareStatement("update purchasedetail set purchasestatus='success' where username=?");
	 
	 for(int i=0;i<=5;i++)
	 {
		 ps.setString(1, username);
		 ps.addBatch();
	 }
	 ps.executeBatch();
	 Dbutil.Close(c);
	 return re;
 }
  public boolean logout(String username) throws SQLException
  {
	  
	  Connection c = Dbutil.getConnection();
	  
	  java.sql.CallableStatement cs = c.prepareCall("{call purchasedetail(?)}");
	  cs.setString(1, username);
	  
	  boolean res = cs.execute();
	  
	  Dbutil.Close(c);
	  return res;
  }
  public List<purchaseDTO> display() throws SQLException
  {
	  List<purchaseDTO> purchase = new ArrayList<purchaseDTO>();
	  
	  Connection c = Dbutil.getConnection();
	  
	  PreparedStatement ps = c.prepareStatement("select * from purchasedetail");
	  
	  ResultSet r = ps.executeQuery();
	  while(r.next())
	  {
		  purchaseDTO dto = new purchaseDTO();
		  dto.setOrderid(r.getInt(1));
		  dto.setCakeid(r.getInt(2));
		  dto.setUsername(r.getString(3));
		  dto.setCakename(r.getString(4));
		  dto.setPrice(r.getDouble(5));
		  dto.setKilogram(r.getString(6));
		  dto.setPurchasestatus(r.getString(7));
	     purchase.add(dto);
	  }
	  Dbutil.Close(c);
	  return purchase;
  }
}
