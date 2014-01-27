package com.mitp0sh.jaclaff.attributes.code.deprecated;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.util.PNC;


public class LookupSwitch
{
	private int            defaultByte = 0;
	private ArrayList<Integer>   match = new ArrayList<Integer>();
	private ArrayList<Integer> offsets = new ArrayList<Integer>();
	
	private SingleInstruction         defaultByteInstruction = null;
	private ArrayList<SingleInstruction> offsetsInstructions = new ArrayList<SingleInstruction>();

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
	
	public SingleInstruction getDefaultByteInstruction() 
	{
		return defaultByteInstruction;
	}

	public void setDefaultByteInstruction(SingleInstruction defaultByteInstruction) 
	{
		this.defaultByteInstruction = defaultByteInstruction;
	}
	
	public static void decoupleFromOffsets(MethodInstructions disassembly, SingleInstruction instruction)
	{
		LookupSwitch lookupSwitch = instruction.getLookupSwitch();
		
		Iterator<Integer> iterOffsets = lookupSwitch.getOffsets().iterator();
		while(iterOffsets.hasNext())
		{
			/* retrieve current offset */
			Integer currentOffset = iterOffsets.next();
			int targetOffset = instruction.getOffset() + currentOffset;
			SingleInstruction targetInstruction = null;
			targetInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, targetOffset);
			if(targetInstruction == null)
			{
				System.err.println("error - unable to lookup instruction by offset !!!");
				System.exit(-1);
			}
			
			lookupSwitch.offsetsInstructions.add(targetInstruction);
		}
		
		int targetOffset = instruction.getOffset() + lookupSwitch.getDefaultByte();
		SingleInstruction targetInstruction = null;
		targetInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, targetOffset);
		if(targetInstruction == null)
		{
			System.err.println("error - unable to lookup instruction by offset !!!");
			System.exit(-1);
		}
		
		lookupSwitch.setDefaultByteInstruction(targetInstruction);
	}
	
	public static void killOffsets(SingleInstruction instruction)
	{
		LookupSwitch lookupSwitch = instruction.getLookupSwitch();
		
		lookupSwitch.setOffsets(new ArrayList<Integer>());
		lookupSwitch.setDefaultByte(0);
	}
	
	public static byte[] serialize(MethodInstructions disassembly, LookupSwitch lookupSwitch, int offset) throws IOException
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
		
		SingleInstruction defaultByteInstruction = lookupSwitch.getDefaultByteInstruction();
		int defaultByteOffset = MethodInstructions.getInstructionOffset(defaultByteInstruction, disassembly) - offset;
		
		baos.write(PNC.toByteArray(defaultByteOffset, Integer.class));
		baos.write(PNC.toByteArray(lookupSwitch.getMatch().size(), Integer.class));
		
		for(int i = 0; i < lookupSwitch.getMatch().size(); i++)
		{
			SingleInstruction currentOffsetInstruction = null;
			currentOffsetInstruction = lookupSwitch.getOffsetsInstructions().get(i);
			int currentOffset = MethodInstructions.getInstructionOffset(currentOffsetInstruction, disassembly) - offset;
			
			baos.write(PNC.toByteArray(lookupSwitch.getMatch().get(i),   Integer.class));
			baos.write(PNC.toByteArray(currentOffset, Integer.class));
		}
		
		return baos.toByteArray();
	}
	
	public static int getPhysicalInstructionLength(LookupSwitch lookupSwitch, int offset)
	{
		int length = 0;
		
		int padding = 0;
		if(((offset + 1) % 4) == 0)
		{
			padding = 0;
		}
		else
		{
			padding = 4 - ((offset + 1) % 4);
		}
		
		length += padding;
		length += 4; // default byte
		length += 4; // num matches
		length += (lookupSwitch.getMatch().size() * 8); // all matches and offsets
		
		return length;
	}
}
