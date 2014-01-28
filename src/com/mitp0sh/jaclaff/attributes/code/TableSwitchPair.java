package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class TableSwitchPair
{
	private int index                             = 0;
	private int offset                            = 0;
	private AbstractInstruction offsetInstruction = null;
	private Disassembly disassembly               = null;

	private TableSwitchPair(Disassembly disassembly)
	{
		setDisassembly(disassembly);
	}
	
	public int getIndex() 
	{
		return index;
	}

	public void setIndex(int index) 
	{
		this.index = index;
	}

	public int getOffset() 
	{
		return offset;
	}
	
	public void setOffset(int offset) 
	{
		this.offset = offset;
	}
	
	public AbstractInstruction getOffsetInstruction() 
	{
		return offsetInstruction;
	}
	
	public void setOffsetInstruction(AbstractInstruction offsetInstruction) 
	{
		this.offsetInstruction = offsetInstruction;
	}
	
	public Disassembly getDisassembly() 
	{
		return disassembly;
	}

	public void setDisassembly(Disassembly disassembly) 
	{
		this.disassembly = disassembly;
	}
	
	public static TableSwitchPair deserialize(DesCtx ctx, int offset, Disassembly disassembly) throws IOException
	{
		TableSwitchPair pair = new TableSwitchPair(disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		pair.setOffset(dis.readInt());
		
		return pair;
	}
	
	public static void decoupleFromOffsets(DesCtx ctx, TableSwitchPair pair, int offset, Disassembly disassembly)
	{
		int targetOffset = offset;
		targetOffset += pair.getOffset();
		AbstractInstruction offsetInstruction = disassembly.getInstruction(targetOffset);
		pair.setOffsetInstruction(offsetInstruction);
	}
	
	public static void coupleWithOffsets(SerCtx ctx, TableSwitchPair pair, int offset, Disassembly disassembly)
	{
		AbstractInstruction operandInstruction = pair.getOffsetInstruction();
		int instructionOffset = disassembly.getInstructionOffset(operandInstruction);
		instructionOffset -= offset;
		pair.setOffset(instructionOffset);
	}
	
	public static byte[] serialize(SerCtx ctx, TableSwitchPair pair, int offset, Disassembly disassembly) throws IOException
	{
		coupleWithOffsets(ctx, pair, offset, disassembly);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		baos.write(PNC.toByteArray(pair.getOffset(), Integer.class));
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		Disassembly disassembly = getDisassembly();
		int offset = disassembly.getInstructionOffset(getOffsetInstruction());
		
		String text = "";
		text += ": jump @ offset " + offset + " -> "  + getOffsetInstruction().getLiteral();
		
		return text;
	}
	
	public static int getPhysicalSize() 
	{
		return 4;
	}
}
