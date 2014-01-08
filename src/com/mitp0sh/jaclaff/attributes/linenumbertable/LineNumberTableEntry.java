package com.mitp0sh.jaclaff.attributes.linenumbertable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class LineNumberTableEntry 
{
	private int           startPc = 0;
	private int        lineNumber = 0;
	
	/* additional info */
	private int    instructionIndexStartPc = 0;
	
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
	
	public int getInstructionIndexStartPc() 
	{
		return instructionIndexStartPc;
	}

	public void setInstructionIndexStartPc(int instructionIndex) 
	{
		this.instructionIndexStartPc = instructionIndex;
	}
	
	public static LineNumberTableEntry deserialize(DesCtx ctx) throws IOException
    {	
		DataInputStream dis = ctx.getDataInputStream();
		
		LineNumberTableEntry lineNumberTableEntry = new LineNumberTableEntry();
		lineNumberTableEntry.setStartPc(dis.readUnsignedShort());
		lineNumberTableEntry.setLineNumber(dis.readUnsignedShort());
		
		return lineNumberTableEntry;
    }
	
	public static byte[] serialize(LineNumberTableEntry lineNumberTableEntry) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(lineNumberTableEntry.getStartPc(), Short.class));
		baos.write(PNC.toByteArray(lineNumberTableEntry.getLineNumber(), Short.class));
		
		return baos.toByteArray();
	}
}
