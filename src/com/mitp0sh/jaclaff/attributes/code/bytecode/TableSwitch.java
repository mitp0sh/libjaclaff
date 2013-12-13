package com.mitp0sh.jaclaff.attributes.code.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class TableSwitch
{
	private int    defaultByte = 0;
	private int[]      offsets = new int[0];
	
	public TableSwitch(int numberOfOffsets)
	{
		offsets = new int[numberOfOffsets];
	}

	public int getDefaultByte() 
	{
		return defaultByte;
	}

	public void setDefaultByte(int defaultByte) 
	{
		this.defaultByte = defaultByte;
	}

	public int[] getOffsets() 
	{
		return offsets;
	}

	public void setOffsets(int[] offsets)
	{
		this.offsets = offsets;
	}
	
	public static byte[] serialize(TableSwitch tableSwitch, int offset) throws IOException
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
		
		int lowBytes  = 0;
		int highBytes = tableSwitch.getOffsets().length + lowBytes - 1;
		
		baos.write(new byte[padding]);
		baos.write(PNC.toByteArray(tableSwitch.getDefaultByte(), Integer.class));
		baos.write(PNC.toByteArray(lowBytes, Integer.class));
		baos.write(PNC.toByteArray(highBytes, Integer.class));
		
		for(int i = 0; i < tableSwitch.getOffsets().length; i++)
		{
			baos.write(PNC.toByteArray(tableSwitch.getOffsets()[i], Integer.class));
		}
		
		return baos.toByteArray();
	}
}
