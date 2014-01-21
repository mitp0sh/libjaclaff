package com.mitp0sh.jaclaff.attributes.linenumbertable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.attributes.code.MethodInstructions;
import com.mitp0sh.jaclaff.attributes.code.SingleInstruction;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class LineNumberTableEntry 
{
	private int           startPc = 0;
	private int        lineNumber = 0;
	
	private SingleInstruction startPcInstruction = null;
	
	public int getStartPc()
	{
		return startPc;
	}
	
	public void setStartPc(int startPc)
	{
		this.startPc = startPc;
	}
	
	public int getLineNumber() 
	{
		return lineNumber;
	}
	
	public void setLineNumber(int lineNumber) 
	{
		this.lineNumber = lineNumber;
	}
	
	public SingleInstruction getStartPcInstruction() 
	{
		return startPcInstruction;
	}

	public void setStartPcInstruction(SingleInstruction startPcInstruction) 
	{
		this.startPcInstruction = startPcInstruction;
	}

	public static LineNumberTableEntry deserialize(DesCtx ctx, AttributeCode attributeCode) throws IOException
    {	
		DataInputStream dis = ctx.getDataInputStream();
		
		LineNumberTableEntry lineNumberTableEntry = new LineNumberTableEntry();
		lineNumberTableEntry.setStartPc(dis.readUnsignedShort());
		lineNumberTableEntry.setLineNumber(dis.readUnsignedShort());
		
		/* decouple from offsets */
		decoupleFromOffsets(ctx, lineNumberTableEntry, attributeCode.getCode());
		
		return lineNumberTableEntry;
    }
	
	protected static void decoupleFromOffsets(DesCtx ctx, LineNumberTableEntry lineNumberTableEntry, MethodInstructions disassembly)
	{
		SingleInstruction startPcInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, lineNumberTableEntry.startPc);
		lineNumberTableEntry.setStartPcInstruction(startPcInstruction);
	}
	
	protected static void coupleToOffsets(SerCtx ctx, LineNumberTableEntry lineNumberTableEntry, MethodInstructions disassembly)
	{
		int startPc = MethodInstructions.getInstructionOffset(lineNumberTableEntry.getStartPcInstruction(), disassembly);
		lineNumberTableEntry.setStartPc(startPc);
	}
	
	public static byte[] serialize(SerCtx ctx, LineNumberTableEntry lineNumberTableEntry, AttributeCode attributeCode) throws IOException
	{
		/* couple to offset before serializing */
		coupleToOffsets(ctx, lineNumberTableEntry, attributeCode.getCode());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(lineNumberTableEntry.getStartPc(), Short.class));
		baos.write(PNC.toByteArray(lineNumberTableEntry.getLineNumber(), Short.class));
		
		return baos.toByteArray();
	}
}
