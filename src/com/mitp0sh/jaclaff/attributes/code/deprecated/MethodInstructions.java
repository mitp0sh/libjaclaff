package com.mitp0sh.jaclaff.attributes.code.deprecated;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.serialization.SerCtx;


public class MethodInstructions
{
	private ArrayList<SingleInstruction> instructions = new ArrayList<SingleInstruction>();
	
	public int getNumberOfInstructions()
	{
		return this.instructions.size();
	}
	
	public ArrayList<SingleInstruction> getInstructions()
	{
		return instructions;
	}
	
	public void setInstructions(ArrayList<SingleInstruction> instructions) 
	{
		this.instructions = instructions;
	}
	
	public int getPhysicalSizeOfMethodInstructions()
	{
		int size = 0;
		
		Iterator<SingleInstruction> iter = this.getInstructions().iterator();
		while(iter.hasNext())
		{
			SingleInstruction current = iter.next();
			size += current.getPhysicalInstructionLength(this);
		}
		
		return size;
	}
	
	public static byte[] serialize(SerCtx ctx, MethodInstructions methodInstructions) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int offset = 0;
		Iterator<SingleInstruction> iter = methodInstructions.getInstructions().iterator();
		while(iter.hasNext())
		{			
			SingleInstruction current = iter.next();
			byte[] instr = SingleInstruction.serialize(ctx, methodInstructions, current, offset);
			offset += instr.length;
			baos.write(instr);
		}
		
		return baos.toByteArray();
	}
	
	public static SingleInstruction lookupInstructionByOffset(MethodInstructions disassembly, int offset)
	{
		int currentOffset = 0;
		Iterator<SingleInstruction> iter = disassembly.getInstructions().iterator();
		while(iter.hasNext())
		{
			SingleInstruction current = iter.next();
			if(currentOffset == offset)
			{
				return current;
			}
			
			currentOffset += current.getPhysicalInstructionLength(disassembly);
		}
		
		return null;
	}
	
	public static int getInstructionOffset(SingleInstruction instruction, MethodInstructions disassembly)
	{
		boolean instructionFound = false;
		int      effectiveOffset = 0;
		
		Iterator<SingleInstruction> iter = disassembly.getInstructions().iterator();
		while(iter.hasNext())
		{
			SingleInstruction current = iter.next();
			
			/* job done !? */
			if(current == instruction)
			{
				/* instruction found - end criteria reached! */
				instructionFound = true;
				break;
			}
			
			/* add to effective offset */
			effectiveOffset += current.getPhysicalInstructionLength(disassembly);
		}
		
		if(instructionFound == false)
		{
			/* error - instruction not found in disassembly! */
			return -1;
		}
		
		return effectiveOffset;
	}
}
