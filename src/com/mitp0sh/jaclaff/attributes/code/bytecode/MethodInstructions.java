package com.mitp0sh.jaclaff.attributes.code.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class MethodInstructions
{
	private short               numberOfInstructions = 0;
	private SingleInstruction[]         instructions = new SingleInstruction[0];
	
	public short getNumberOfInstructions()
	{
		return (short)this.instructions.length;
	}
	
	public void setNumberOfInstructions(short numberOfInstructions) 
	{
		this.numberOfInstructions = numberOfInstructions;
	}
	
	public SingleInstruction[] getInstructions()
	{
		return instructions;
	}
	
	public void setInstructions(SingleInstruction[] instructions) 
	{
		this.instructions         = instructions;
	}
	
	public void addInstruction(SingleInstruction singleInstruction)
	{
		SingleInstruction[] newSingleInstructions  = new SingleInstruction[instructions.length + 1];
		System.arraycopy(this.instructions, 0, newSingleInstructions, 0, this.instructions.length);
		newSingleInstructions[this.instructions.length] = singleInstruction;
		
		this.numberOfInstructions++;
		this.instructions = newSingleInstructions;
	}
	
	public int getPhysicalSizeOfMethodInstructions()
	{
		int size = 0;
		
		for(int i = 0; i < this.getNumberOfInstructions(); i++)
		{
			size += this.getInstructions()[i].getPhysicalInstructionLength();
		}
		
		return size;
	}
	
	public static byte[] serialize(MethodInstructions methodInstructions) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int offset = 0;
		
		for(int i = 0; i < methodInstructions.numberOfInstructions; i++)
		{
			byte[] instr = SingleInstruction.serialize(methodInstructions.getInstructions()[i], offset);
			offset += instr.length;
			baos.write(instr);
		}
		
		return baos.toByteArray();
	}
	
	public static SingleInstruction lookupInstructionByOffset(MethodInstructions disassembly, int offset)
	{
		int currentOffset = 0;
		
		for(int i = 0; i < disassembly.getInstructions().length; i++)
		{
			if(currentOffset == offset)
			{
				return disassembly.getInstructions()[i];
			}
			
			currentOffset += disassembly.getInstructions()[i].getPhysicalInstructionLength();
		}
		
		return null;
	}
	
	public static int getInstructionOffset(SingleInstruction instruction, MethodInstructions disassembly)
	{
		boolean instructionFound = false;
		int      effectiveOffset = 0;
		
		/* iterate over all instructions */
		for(int i = 0; i < disassembly.getInstructions().length; i++)
		{
			/* retrieve current instruction */
			SingleInstruction current = disassembly.getInstructions()[i];
		
			/* job done !? */
			if(current == instruction)
			{
				/* instruction found - end criteria reached! */
				instructionFound = true;
				break;
			}
			
			/* add to effective offset */
			effectiveOffset += current.getPhysicalInstructionLength();
		}
		
		if(instructionFound == false)
		{
			/* error - instruction not found in disassembly! */
			return -1;
		}
		
		return effectiveOffset;
	}
}
