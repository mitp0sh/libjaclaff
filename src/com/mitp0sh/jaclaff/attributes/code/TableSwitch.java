package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.util.PNC;


public class TableSwitch
{
	private int            defaultByte = 0;
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
		TableSwitch tableSwitch = instruction.getTableSwitch();
		
		Iterator<Integer> iterOffsets = tableSwitch.getOffsets().iterator();
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
			
			tableSwitch.offsetsInstructions.add(targetInstruction);
		}
		
		int targetOffset = instruction.getOffset() + tableSwitch.getDefaultByte();
		SingleInstruction targetInstruction = null;
		targetInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, targetOffset);
		if(targetInstruction == null)
		{
			System.err.println("error - unable to lookup instruction by offset !!!");
			System.exit(-1);
		}
		
		tableSwitch.setDefaultByteInstruction(targetInstruction);
	}
	
	public static void killOffsets(SingleInstruction instruction)
	{
		TableSwitch tableSwitch = instruction.getTableSwitch();
	
		tableSwitch.setOffsets(new ArrayList<Integer>());
		tableSwitch.setDefaultByte(0);
	}
	
	public static byte[] serialize(MethodInstructions disassembly, TableSwitch tableSwitch, int offset) throws IOException
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
		
		SingleInstruction defaultByteInstruction = tableSwitch.getDefaultByteInstruction();
		int defaultByteOffset = MethodInstructions.getInstructionOffset(defaultByteInstruction, disassembly) - offset;
		
		int lowBytes  = 0;
		int highBytes = tableSwitch.getOffsetsInstructions().size() + lowBytes - 1;
		
		baos.write(new byte[padding]);
		baos.write(PNC.toByteArray(defaultByteOffset, Integer.class));
		baos.write(PNC.toByteArray(lowBytes,          Integer.class));
		baos.write(PNC.toByteArray(highBytes,         Integer.class));
		
		for(int i = 0; i < tableSwitch.getOffsetsInstructions().size(); i++)
		{
			SingleInstruction currentOffsetInstruction = null;
			currentOffsetInstruction = tableSwitch.getOffsetsInstructions().get(i);
			int currentOffset = MethodInstructions.getInstructionOffset(currentOffsetInstruction, disassembly) - offset;
			
			baos.write(PNC.toByteArray(currentOffset, Integer.class));
		}
		
		return baos.toByteArray();
	}
	
	public static int getPhysicalInstructionLength(TableSwitch tableSwitch, int offset)
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
		length += 4; // low bytes
		length += 4; // high bytes
		length += (tableSwitch.getOffsetsInstructions().size() * 4); // all offsets
		
		return length;
	}
}
