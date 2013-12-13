package com.mitp0sh.jaclaff.attributes.code.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class LookupSwitch
{
	private int      defaultByte = 0;
	private int[]          match = new int[0];
	private int[]         offset = new int[0];
	
	public LookupSwitch(int npairs)
	{
		 match = new int[npairs];
		offset = new int[npairs];
	}

	public int getDefaultByte() 
	{
		return defaultByte;
	}

	public void setDefaultByte(int defaultByte) 
	{
		this.defaultByte = defaultByte;
	}

	public int[] getMatch() 
	{
		return match;
	}

	public void setMatch(int[] match) 
	{
		this.match = match;
	}

	public int[] getOffset() 
	{
		return offset;
	}

	public void setOffset(int[] offset) 
	{
		this.offset = offset;
	}	
	
	public static byte[] serialize(LookupSwitch lookupSwitch, int offset) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int padding = 0;
		
		if(((offset + 1) % 4) == 0)
		{
			padding = 0;
		}
		else
		{
			padding = 4 - ((offset + 1) % 4);
		}
		
		baos.write(new byte[padding]);
		baos.write(PNC.toByteArray(lookupSwitch.getDefaultByte(), Integer.class));
		baos.write(PNC.toByteArray(lookupSwitch.getMatch().length, Integer.class));
		
		for(int i = 0; i < lookupSwitch.getMatch().length; i++)
		{
			baos.write(PNC.toByteArray(lookupSwitch.getMatch()[i], Integer.class));
			baos.write(PNC.toByteArray(lookupSwitch.getOffset()[i], Integer.class));
		}
		
		return baos.toByteArray();
	}
}
