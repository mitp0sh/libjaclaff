package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class Disassembly
{
	private ArrayList<AbstractInstruction> instructions = new ArrayList<AbstractInstruction>();

	public ArrayList<AbstractInstruction> getInstructions() 
	{
		return instructions;
	}

	public void setInstructions(ArrayList<AbstractInstruction> instructions) 
	{
		this.instructions = instructions;
	}
	
	public int getNumInstructions()
	{
		return instructions.size();
	}

	public AbstractInstruction getFirstInstruction()
	{
		ArrayList<AbstractInstruction> instructions = getInstructions();
		if(instructions.size() <= 0)
		{
			return null;
		}
		
		return instructions.get(0);
	}
	
	public int getPhysicalDisassemblySize()
	{
		int size = 0;
		
		Iterator<AbstractInstruction> iter = instructions.iterator();
		while(iter.hasNext())
		{
			AbstractInstruction current = iter.next();
			size += current.getPhysicalSize();
		}
		
		return size;
	}
	
	public static Disassembly deserialize(DesCtx ctx, long length) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		Disassembly disassembly = new Disassembly();
		int offset = 0;
		boolean previousInstructionWide = false;
		AbstractInstruction previousInstruction = null;
		
		while(length > offset)
		{
			AbstractInstruction current = null;
			int byteCodeValue = dis.readUnsignedByte();
			
			if(InstructionTypeB.isOfType(byteCodeValue))
			{				
				current = InstructionTypeB.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBC.isOfType(byteCodeValue))
			{
				current = InstructionTypeBC.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBCC.isOfType(byteCodeValue))
			{
				current = InstructionTypeBCC.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBI.isOfType(byteCodeValue))
			{
				current = InstructionTypeBI.deserialize(ctx, byteCodeValue, previousInstructionWide, previousInstruction, disassembly);
				previousInstructionWide = false;
			}
			else
			if(InstructionTypeBIC.isOfType(byteCodeValue))
			{
				current = InstructionTypeBIC.deserialize(ctx, byteCodeValue, previousInstructionWide, previousInstruction, disassembly);
				previousInstructionWide = false;
			}
			else
			if(InstructionTypeBII.isOfType(byteCodeValue))
			{
				current = InstructionTypeBII.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBIIC.isOfType(byteCodeValue))
			{
				current = InstructionTypeBIIC.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBJJ__.isOfType(byteCodeValue))
			{
				current = InstructionTypeBJJ__.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBJJ.isOfType(byteCodeValue))
			{
				current = InstructionTypeBJJ.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBJJJJ.isOfType(byteCodeValue))
			{
				current = InstructionTypeBJJJJ.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBOO.isOfType(byteCodeValue))
			{
				current = InstructionTypeBOO.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeBOOOO.isOfType(byteCodeValue))
			{
				current = InstructionTypeBOOOO.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeLookupSwitch.isOfType(byteCodeValue))
			{
				current = InstructionTypeLookupSwitch.deserialize(ctx, byteCodeValue, offset, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeTableSwitch.isOfType(byteCodeValue))
			{
				current = InstructionTypeTableSwitch.deserialize(ctx, byteCodeValue, offset, previousInstruction, disassembly);
			}
			else
			if(InstructionTypeWide.isOfType(byteCodeValue))
			{
				previousInstructionWide = true;
				current = InstructionTypeWide.deserialize(ctx, byteCodeValue, previousInstruction, disassembly);
			}
			else
			{
				System.err.println("error - unable to disassemble instruction ( byteCodeValue == " + byteCodeValue + " )");
				System.exit(-1);
			}
			
			disassembly.getInstructions().add(current);
			previousInstruction = current;
			offset += current.getPhysicalSize();
		}
		
		decoupleFromOffsets(ctx, disassembly);
		
		System.out.println();
		System.out.println(disassembly);
		System.out.println();
		
		return disassembly;
	}
	
	public static void decoupleFromOffsets(DesCtx ctx, Disassembly disassembly)
	{
		Iterator<AbstractInstruction> iter = disassembly.getInstructions().iterator();
		while(iter.hasNext())
		{
			AbstractInstruction current = iter.next();
			int byteCodeValue = current.getByteCodeValue();
			
			if(InstructionTypeBOO.isOfType(byteCodeValue))
			{
				InstructionTypeBOO.decoupleFromOffsets(ctx, (InstructionTypeBOO) current);
			}
		}
	}
	
	public static byte[] serialize(SerCtx ctx, Disassembly disassembly) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Iterator<AbstractInstruction> iter = disassembly.getInstructions().iterator();
		while(iter.hasNext())
		{
			AbstractInstruction current = iter.next();
			int byteCodeValue = current.getByteCodeValue();
			if(InstructionTypeB.isOfType(byteCodeValue))
			{				
				baos.write(InstructionTypeB.serialize(ctx, (InstructionTypeB)current));
			}
			else
			if(InstructionTypeBC.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBC.serialize(ctx, (InstructionTypeBC)current));
			}
			else
			if(InstructionTypeBCC.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBCC.serialize(ctx, (InstructionTypeBCC)current));
			}
			else
			if(InstructionTypeBI.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBI.serialize(ctx, (InstructionTypeBI)current));
			}
			else
			if(InstructionTypeBIC.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBIC.serialize(ctx, (InstructionTypeBIC)current));
			}
			else
			if(InstructionTypeBII.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBII.serialize(ctx, (InstructionTypeBII)current));
			}
			else
			if(InstructionTypeBIIC.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBIIC.serialize(ctx, (InstructionTypeBIIC)current));
			}
			else
			if(InstructionTypeBJJ__.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBJJ__.serialize(ctx, (InstructionTypeBJJ__)current));
			}
			else
			if(InstructionTypeBJJ.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBJJ.serialize(ctx, (InstructionTypeBJJ)current));
			}
			else
			if(InstructionTypeBJJJJ.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBJJJJ.serialize(ctx, (InstructionTypeBJJJJ)current));	
			}
			else
			if(InstructionTypeBOO.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBOO.serialize(ctx, (InstructionTypeBOO)current));
			}
			else
			if(InstructionTypeBOOOO.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeBOOOO.serialize(ctx, (InstructionTypeBOOOO)current));
			}
			else
			if(InstructionTypeLookupSwitch.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeLookupSwitch.serialize(ctx, (InstructionTypeLookupSwitch)current, disassembly));
			}
			else
			if(InstructionTypeTableSwitch.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeTableSwitch.serialize(ctx, (InstructionTypeTableSwitch)current, disassembly));
			}
			else
			if(InstructionTypeWide.isOfType(byteCodeValue))
			{
				baos.write(InstructionTypeWide.serialize(ctx, (InstructionTypeWide)current));
			}
			else
			{
				System.err.println("error - unable to assemble instruction ( " + current.toString() + " )");
				System.exit(-1);
			}
		}
		
		return baos.toByteArray();
	}
	
	public int getInstructionOffset(AbstractInstruction instruction)
	{
		int offset = 0;
		
		Iterator<AbstractInstruction> iter = getInstructions().iterator();
		while(iter.hasNext())
		{
			AbstractInstruction current = iter.next();
			if(current == instruction)
			{
				break;
			}
			
			offset += current.getPhysicalSize();
		}

		return offset;
	}
	
	public AbstractInstruction getInstruction(int offset)
	{
		Iterator<AbstractInstruction> iter = getInstructions().iterator();
		while(iter.hasNext())
		{
			AbstractInstruction current = iter.next();
			int currentOffset = getInstructionOffset(current);
			if(currentOffset == offset)
			{
				return current;
			}
		}
		
		return null;
	}
	
	@Override
	public String toString() 
	{
		String string = "<index>  <offset>  <instruction>\n\n";
		int index = 0;
		
		Iterator<AbstractInstruction> iter = getInstructions().iterator();
		while(iter.hasNext())
		{
			String line = "";
			AbstractInstruction current = iter.next();
			
			String stringIndex = Integer.toString(index);
			int stringIndexLength = stringIndex.length();
			switch(stringIndexLength)
			{
				case 1:
				{
					stringIndexLength = "<index>".length() - 1;
					break;
				}
				case 2:
				{
					stringIndexLength = "<index>".length() - 2;
					break;
				}
				case 3:
				{
					stringIndexLength = "<index>".length() - 3;
					break;
				}
				case 4:
				{
					stringIndexLength = "<index>".length() - 4;
					break;
				}
				case 5:
				{
					stringIndexLength = "<index>".length() - 5;
					break;
				}
			}
			
			for(int i = 0; i < stringIndexLength; i++)
			{
				line += " ";
			}
			line += stringIndex;
			line += "  ";
			int offset = getInstructionOffset(current);
			String offsetString = Integer.toString(offset);
			int offsetStringLength = offsetString.length();
			
			switch(offsetStringLength)
			{
				case 1:
				{
					offsetStringLength = 7;
					break;
				}
				case 2:
				{
					offsetStringLength = 6;
					break;
				}
				case 3:
				{
					offsetStringLength = 5;
					break;
				}
				case 4:
				{
					offsetStringLength = 4;
					break;
				}
				case 5:
				{
					offsetStringLength = 3;
					break;
				}
			}
			for(int i = 0; i < offsetStringLength; i++)
			{
				line += " ";
			}
			
			line += offset;
			line += "  ";
			
			
			line += current.toString();
			
			string += line;
			string += "\n";
			
			index++;
		}
		
		return string;
	}
}
