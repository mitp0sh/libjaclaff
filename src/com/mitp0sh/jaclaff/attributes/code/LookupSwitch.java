package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.util.PNC;


public class LookupSwitch
{
	private int           defaultByte = 0;
	private ArrayList<Integer>  match = new ArrayList<Integer>();
	private ArrayList<Integer> offset = new ArrayList<Integer>();
	
	private ArrayList<SingleInstruction> offsetInstructions = new ArrayList<SingleInstruction>();

	public int getDefaultByte() 
	{
		return defaultByte;
	}

	public void setDefaultByte(int defaultByte) 
	{
		this.defaultByte = defaultByte;
	}

	public ArrayList<Integer> getMatch() 
	{
		return match;
	}

	public ArrayList<Integer> getOffset() 
	{
		return offset;
	}
	
	public ArrayList<SingleInstruction> getOffsetInstructions()
	{
		return offsetInstructions;
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
		baos.write(PNC.toByteArray(lookupSwitch.getMatch().size(), Integer.class));
		
		for(int i = 0; i < lookupSwitch.getMatch().size(); i++)
		{
			baos.write(PNC.toByteArray(lookupSwitch.getMatch().get(i), Integer.class));
			baos.write(PNC.toByteArray(lookupSwitch.getOffset().get(i), Integer.class));
		}
		
		return baos.toByteArray();
	}
}
