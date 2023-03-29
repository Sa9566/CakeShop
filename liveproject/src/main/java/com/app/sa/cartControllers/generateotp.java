package com.app.sa.cartControllers;

import java.util.Random;

public class generateotp 
{
	public String generateotp(int otplength)
	{
		Random rd1 = new Random();
		
		StringBuilder sb1 = new StringBuilder();
		
		for(int i=0;i<otplength;i++)
		{
			sb1.append(rd1.nextInt(10));
		}
		String s = sb1.toString();
		
		return s;
	}
}
