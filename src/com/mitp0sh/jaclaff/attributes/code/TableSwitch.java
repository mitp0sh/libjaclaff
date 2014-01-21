package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.util.PNC;


public class TableSwitch
{
	private int    defaultByte = 0;
	private ArrayList<Integer> offsets = new ArrayList<Integer>();
	
	private ArrayList<SingleInstruction> offsetsInstructions = new ArrayList<SingleInstruction>();

	public int getDefaultByte() 
	{
		return defaultByte;
	}

	public void setDefaultByte(int defaultByte) 
	{
		this.defaultByte = defaultByte;
	}

	public ArrayList<Integer> getOffsets() 
	{
		return offsets;
	}

	public void setOffsets(ArrayList<Integer> offsets)
	{
		this.offsets = offsets;
	}
	
	public ArrayList<SingleInstruction> getOffsetsInstructions()
	{
		return offsetsInstructions;
	}

	public void setOffsetsInstructions(ArrayList<SingleInstruction> offsetsInstructions) 
	{
		this.offsetsInstructions = offsetsInstructions;
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
		int highBytes = tableSwitch.getOffsets().size() + lowBytes - 1;
		
		baos.write(new byte[padding]);
		baos.write(PNC.toByteArray(tableSwitch.getDefaultByte(), Integer.class));
		baos.write(PNC.toByteArray(lowBytes,                     Integer.class));
		baos.write(PNC.toByteArray(highBytes,                    Integer.class));
		
		for(int i = 0; i < tableSwitch.getOffsets().size(); i++)
		{
			baos.write(PNC.toByteArray(tableSwitch.getOffsets().get(i), Integer.class));
		}
		
		return baos.toByteArray();
	}
}
