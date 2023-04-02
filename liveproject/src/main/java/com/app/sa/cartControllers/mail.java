package com.app.sa.cartControllers;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


public class mail 
{
	public static boolean sendEmail(String message, String subject, String to, String from) 
	{
		boolean res = false;
		
		String host="smtp.gmail.com";

		Properties properties = System.getProperties();
		System.out.println("PROPERTIES "+properties);

		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.port","465");
		properties.put("mail.smtp.ssl.enable","true");
		properties.put("mail.smtp.auth","true");
		
		Session session=Session.getInstance(properties, new Authenticator() 
		{
			@Override
			protected PasswordAuthentication getPasswordAuthentication() 
			{				
				return new PasswordAuthentication("cakeshop9566@gmail.com", "*****");
			}
			
			
		});
		
		session.setDebug(true);
		
		MimeMessage m = new MimeMessage(session);
		
		try 
		{
		m.setFrom(from);
		
		m.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		
		m.setSubject(subject);
		
	    m.setText(message);
		
		Transport.send(m);
		res = true;
		System.out.println("Sent success...................");
		
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
			
		return res;
	}
}

