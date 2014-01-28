package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeLookupSwitch extends AbstractInstruction
{
	private int defaultByte                            = 0;
	private ArrayList<LookupSwitchPair> pairs          = new ArrayList<LookupSwitchPair>();
	private AbstractInstruction defaultByteInstruction = null;

	public InstructionTypeLookupSwitch(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly)
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	public void initInstruction(int byteCodeValue)
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.LUPS);
		
		/* init instruction */
		switch(byteCodeValue)
		{
    	    case Mnemonics.BC_lookupswitch:
    	    {
    	    	setLiteral("lookupswitch");
    	    	setOperandStackDelta(-1);
    	    	break;
    	    }
		}
	}
	
	public int getDefaultByte() 
	{
		return defaultByte;
	}

	public void setDefaultByte(int defaultByte) 
	{
		this.defaultByte = defaultByte;
	}

	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
    	    case Mnemonics.BC_lookupswitch:
    	    {
        		return true;
        	}
		}
		
		return false;
	}
	
	public ArrayList<LookupSwitchPair> getPairs() 
	{
		return pairs;
	}

	public void setPairs(ArrayList<LookupSwitchPair> pairs) 
	{
		this.pairs = pairs;
	}
	
	public int getNumPairs()
	{
		return this.pairs.size();
	}
	
	public AbstractInstruction getDefaultByteInstruction() 
	{
		return defaultByteInstruction;
	}

	public void setDefaultByteInstruction(AbstractInstruction defaultByteInstruction) 
	{
		this.defaultByteInstruction = defaultByteInstruction;
	}
	
	public static InstructionTypeLookupSwitch deserialize(DesCtx ctx, int byteCodeValue, int offset, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeLookupSwitch instruction = new InstructionTypeLookupSwitch(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
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
		
		int defaultByte = dis.readInt();
		instruction.setDefaultByte(defaultByte);
		
		int npairs = dis.readInt();
		for(int i = 0; i< npairs; i++)
		{
			instruction.getPairs().add(LookupSwitchPair.deserialize(ctx, offset, disassembly));
		}
		
		return instruction;
	}
	
	public static void decoupleFromOffsets(DesCtx ctx, InstructionTypeLookupSwitch instruction)
	{
		Disassembly disassembly = instruction.getDisassembly();
		int offset = disassembly.getInstructionOffset(instruction);
		
		int targetOffset = instruction.getDefaultByte() + offset;
		AbstractInstruction offsetInstruction = disassembly.getInstruction(targetOffset);
		instruction.setDefaultByteInstruction(offsetInstruction);
		
		Iterator<LookupSwitchPair> iter = instruction.getPairs().iterator();
		while(iter.hasNext())
		{
			LookupSwitchPair pair = iter.next();
			LookupSwitchPair.decoupleFromOffsets(ctx, pair, offset, disassembly);
		}
	}
	
	public static void coupleWithOffsets(SerCtx ctx, InstructionTypeLookupSwitch instruction)
	{
		int offset = instruction.getDisassembly().getInstructionOffset(instruction);
		
		AbstractInstruction operandInstruction = instruction.getDefaultByteInstruction();
		int instructionOffset = instruction.getDisassembly().getInstructionOffset(operandInstruction);
		instructionOffset -= offset;
		instruction.setOffset(instructionOffset);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeLookupSwitch instruction, Disassembly disassembly) throws IOException
	{
		coupleWithOffsets(ctx, instruction);
		
		int offset = disassembly.getInstructionOffset(instruction);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		
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
		
		baos.write(PNC.toByteArray(instruction.getDefaultByte(), Integer.class));
		baos.write(PNC.toByteArray(instruction.getNumPairs(),    Integer.class));
		
		Iterator<LookupSwitchPair> iter = instruction.getPairs().iterator();
		while(iter.hasNext())
		{
			LookupSwitchPair pair = iter.next();
			LookupSwitchPair.serialize(ctx, pair, offset, disassembly);
		}
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		String text = super.toString() + "\n";
		
		Iterator<LookupSwitchPair> iter = getPairs().iterator();
		while(iter.hasNext())
		{
			LookupSwitchPair pair = iter.next();
			text += "                     " + pair.toString() + "\n";
		}
		
		AbstractInstruction defaultInstruction = getDefaultByteInstruction();
		int defOffset = getDisassembly().getInstructionOffset(defaultInstruction);
		
		text += "                     default: jump @ offset " + defOffset + " -> " + defaultInstruction.getLiteral();
		
		return text;
	}

	@Override
	public int getPhysicalSize()
	{
		int physicalSize = 0;
		int offset       = 0;
		
		offset = getDisassembly().getInstructionOffset(this);
		
		physicalSize += 1;
		
		int padding = 0;
		if(((offset + 1) % 4) == 0)
		{
			padding = 0;
		}
		else
		{
			padding = 4 - ((offset + 1) % 4);
		}
		
		physicalSize += padding;
		physicalSize += 4; 
		physicalSize += 4; 
		physicalSize += (getNumPairs() * LookupSwitchPair.getPhysicalSize());
		
		return physicalSize;
	}
}