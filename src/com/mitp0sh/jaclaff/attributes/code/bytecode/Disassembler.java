package com.mitp0sh.jaclaff.attributes.code.bytecode;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;

public class Disassembler
{
	public static SingleInstruction disassemble(DesCtx ctx, int offset, Boolean hasWidePrefix) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		SingleInstruction instruction = new SingleInstruction();
		
		byte byteCode = (byte)(dis.readUnsignedByte() & 0xFF);
		instruction.setByteCode(Mnemonics.getJBC()[byteCode & 0xFF]);
		instruction.seOffset(offset);
		
		String format = "";
		
		if(hasWidePrefix)
		{
			format = instruction.getByteCode().getWideFormat();
		}
		else
		{
			format = instruction.getByteCode().getFormat();
		}
		
		if(format.equals("b"))
		{
			dis.skipBytes(format.length() - 1);
			instruction.getByteCode().setPhysicalLength((short)format.length());
		}
		else
		if(format.equals("bc"))
		{
			instruction.setOperand1(dis.readByte());
			instruction.getByteCode().setPhysicalLength((short)format.length());
			
			SingleInstruction.decoupleFromIndices(ctx, instruction);
		}
		else
		if(format.equals("bcc"))
		{
			instruction.setOperand1(dis.readShort());
			instruction.getByteCode().setPhysicalLength((short)format.length());
		}
		else
		if(format.equals("bi") || format.equals("wbii"))
		{
			if(format.startsWith("w"))
			{
				instruction.setOperand1(dis.readUnsignedShort());
				instruction.getByteCode().setPhysicalLength((short)(format.length() - 1));
				instruction.setWidePrefix(true);
			}
			else
			{
				instruction.setOperand1(dis.readUnsignedByte());
				instruction.getByteCode().setPhysicalLength((short)format.length());
			}
			
			SingleInstruction.decoupleFromIndices(ctx, instruction);
		}		
		else
		if(format.equals("bii"))
		{
			instruction.setOperand1(dis.readUnsignedShort());
			instruction.getByteCode().setPhysicalLength((short)format.length());
			
			SingleInstruction.decoupleFromIndices(ctx, instruction);
		}
		else
		if(format.equals("bic") || format.equals("wbiicc"))
		{			
			if(format.startsWith("w"))
			{	
				instruction.setOperand1(dis.readUnsignedShort());				
				instruction.setOperand2(dis.readShort());				
				instruction.getByteCode().setPhysicalLength((short)(format.length() - 1));
				instruction.setWidePrefix(true);
			}
			else
			{
				instruction.setOperand1(dis.readUnsignedByte());
				instruction.setOperand2(dis.readByte());
				instruction.getByteCode().setPhysicalLength((short)format.length());
			}
		}
		else
		if(format.equals("biic"))
		{
			instruction.setOperand1(dis.readUnsignedShort());
			instruction.setOperand2(dis.readByte());
			instruction.getByteCode().setPhysicalLength((short)format.length());
			
			SingleInstruction.decoupleFromIndices(ctx, instruction);
		}
		else
		if(format.equals("boo"))
		{
			instruction.setOperand1(dis.readShort());
			instruction.getByteCode().setPhysicalLength((short)format.length());
		}
		else
		if(format.equals("boooo"))
		{
			instruction.setOperand1(dis.readInt());
			instruction.getByteCode().setPhysicalLength((short)format.length());
		}
		else
		if(format.equals("bjj"))
		{
			instruction.setOperand1(dis.readUnsignedShort());
			instruction.getByteCode().setPhysicalLength((short)format.length());
			
			SingleInstruction.decoupleFromIndices(ctx, instruction);
		}
		else
		if(format.equals("bjj__"))
		{
			instruction.setOperand1(dis.readUnsignedShort());
			dis.skipBytes(2);
			instruction.getByteCode().setPhysicalLength((short)format.length());
			
			SingleInstruction.decoupleFromIndices(ctx, instruction);
		}
		else
		if(format.equals("bjjjj"))
		{
			instruction.setOperand1(dis.readInt());
			instruction.getByteCode().setPhysicalLength((short)format.length());
			
			SingleInstruction.decoupleFromIndices(ctx, instruction);
		}
		else
		if(format.equals(""))
		{
			if(byteCode == Mnemonics.BC_tableswitch ||
			   byteCode == Mnemonics.BC_lookupswitch)
			{
                int padding = 0;
				
				if(((offset + 1) % 4) == 0)
				{
					padding = 0;
				}
				else
				{
					padding = 4 - ((offset + 1) % 4);
				}
				dis.skipBytes(padding);	
					
				if(byteCode == Mnemonics.BC_lookupswitch)
				{
					int defaultByte = dis.readInt();
					int      npairs = dis.readInt();
					
					LookupSwitch lookupSwitch = new LookupSwitch(npairs);
					lookupSwitch.setDefaultByte(defaultByte);
					instruction.setLookupSwitch(lookupSwitch);
					
					for(int i = 0; i < npairs; i++)
					{
						lookupSwitch.getMatch()[i]  = dis.readInt();
						lookupSwitch.getOffset()[i] = dis.readInt();
					}
					
					instruction.getByteCode().setPhysicalLength((short)(1 + padding + 8 + npairs * 8));
				}
				else
				{
					int defaultByte     = dis.readInt();
					int lowbyte         = dis.readInt();
					int highbyte        = dis.readInt();
					int numberOfOffsets = highbyte - lowbyte + 1;
					TableSwitch tableSwitch = new TableSwitch(numberOfOffsets);
					instruction.setTableSwitch(tableSwitch);
					tableSwitch.setDefaultByte(defaultByte);
					
					for(int i = 0; i < numberOfOffsets; i++)
					{
						tableSwitch.getOffsets()[i] = dis.readInt();
					}
					
					instruction.getByteCode().setPhysicalLength((short)(1 + padding + 12 + numberOfOffsets * 4));
				}
			}
			else
			if(byteCode == Mnemonics.BC_wide)
			{
				instruction.getByteCode().setPhysicalLength((short)1);
			}
			else
			if(byteCode == Mnemonics.BC_breakpoint)
			{
				System.out.println("BREAKPOINT OPCODE DETECTED, NOT IMPLEMENTED!!!");
			}
			else
			{
				System.out.println("Opcode unknown!!!!");
			}
		}
		
		return instruction;
	}
	
	public static MethodInstructions disassemble(DesCtx ctx, long length) throws IOException
	{
		MethodInstructions instructions = new MethodInstructions();
		
		int disassembleLength = 0;
		Boolean hasWidePrefix = false;
		
		while(length > disassembleLength)
		{
			SingleInstruction instruction = disassemble(ctx, disassembleLength, hasWidePrefix);
			instructions.addInstruction(instruction);
			
			if(instruction.getByteCode().getByteCode() == Mnemonics.BC_wide)
			{
				hasWidePrefix = true;
			}
			else
			{
				hasWidePrefix = false;
			}
			
			disassembleLength += instruction.getByteCode().getPhysicalLength();
		}
		
		return instructions;
	}
	
}
