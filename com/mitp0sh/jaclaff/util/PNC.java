package com.mitp0sh.jaclaff.util;

public class PNC
{
	public static byte[] toByteArray(long value, Class<?> type)
	{
		byte[] byteArray = new byte[0];
	
		if(type == Short.class)
		{
			byteArray = new byte[2];
			
			byteArray[1] = (byte)(((value & 0x000000FF) >>> 0) & 0xFF);
			byteArray[0] = (byte)(((value & 0x0000FF00) >>> 8) & 0xFF);
		}
		else
		if(type == Integer.class)
		{
			byteArray = new byte[4];
			
			byteArray[3] = (byte)(((value & 0x000000FF) >>> 0)  & 0xFF);
			byteArray[2] = (byte)(((value & 0x0000FF00) >>> 8)  & 0xFF);
			byteArray[1] = (byte)(((value & 0x00FF0000) >>> 16) & 0xFF);
			byteArray[0] = (byte)(((value & 0xFF000000) >>> 24) & 0xFF);
		}
		else
		if(type == Long.class)
		{
			byteArray = new byte[8];
			
			byteArray[7] = (byte)(((value & 0x00000000000000FFL) >>> 0)  & 0xFF);
			byteArray[6] = (byte)(((value & 0x000000000000FF00L) >>> 8)  & 0xFF);
			byteArray[5] = (byte)(((value & 0x0000000000FF0000L) >>> 16) & 0xFF);
			byteArray[4] = (byte)(((value & 0x00000000FF000000L) >>> 24) & 0xFF);
			byteArray[3] = (byte)(((value & 0x000000FF00000000L) >>> 32) & 0xFF);
			byteArray[2] = (byte)(((value & 0x0000FF0000000000L) >>> 40) & 0xFF);
			byteArray[1] = (byte)(((value & 0x00FF000000000000L) >>> 48) & 0xFF);
			byteArray[0] = (byte)(((value & 0xFF00000000000000L) >>> 52) & 0xFF);
		}
		else
		{
			System.out.println("Type not supported!");
			byteArray = null;
		}
		
		return byteArray;
	}
	
	public static byte[] shortToByteArray(short value)
	{
		byte[] byteArray = new byte[2];
		
		byteArray[1] = (byte)(((value & 0x000000FF) >>> 0) & 0xFF);
		byteArray[0] = (byte)(((value & 0x0000FF00) >>> 8) & 0xFF);
		
		return byteArray;
	}
	
	public static byte[] shortToByteArray(int value)
	{
		byte[] byteArray = new byte[4];
		
		byteArray[3] = (byte)(((value & 0x000000FF) >>> 0)  & 0xFF);
		byteArray[2] = (byte)(((value & 0x0000FF00) >>> 8)  & 0xFF);
		byteArray[1] = (byte)(((value & 0x00FF0000) >>> 16) & 0xFF);
		byteArray[0] = (byte)(((value & 0xFF000000) >>> 24) & 0xFF);
		
		return byteArray;
	}
	
	public static byte[] shortToByteArray(long value)
	{
		byte[] byteArray = new byte[8];
		
		byteArray[7] = (byte)(((value & 0x00000000000000FFL) >>> 0)  & 0xFF);
		byteArray[6] = (byte)(((value & 0x000000000000FF00L) >>> 8)  & 0xFF);
		byteArray[5] = (byte)(((value & 0x0000000000FF0000L) >>> 16) & 0xFF);
		byteArray[4] = (byte)(((value & 0x00000000FF000000L) >>> 24) & 0xFF);
		byteArray[3] = (byte)(((value & 0x000000FF00000000L) >>> 32) & 0xFF);
		byteArray[2] = (byte)(((value & 0x0000FF0000000000L) >>> 40) & 0xFF);
		byteArray[1] = (byte)(((value & 0x00FF000000000000L) >>> 48) & 0xFF);
		byteArray[0] = (byte)(((value & 0xFF00000000000000L) >>> 52) & 0xFF);
		
		return byteArray;
	}
}
