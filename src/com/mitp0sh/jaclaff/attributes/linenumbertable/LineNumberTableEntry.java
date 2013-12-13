package com.mitp0sh.jaclaff.attributes.linenumbertable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class LineNumberTableEntry 
{
	private short           startPc = 0;
	private short        lineNumber = 0;
	
	/* additional info */
	private int    instructionIndexStartPc = 0;
	
	public short getStartPc()
	{
		return startPc;
	}
	
	public void setStartPc(short startPc)
	{
		this.startPc = startPc;
	}
	
	public short getLineNumber() 
	{
		return lineNumber;
	}
	
	public void setLineNumber(short lineNumber) 
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
	
	public static LineNumberTableEntry deserialize(DataInputStream dis) throws IOException
    {	
		LineNumberTableEntry lineNumberTableEntry = new LineNumberTableEntry();
		lineNumberTableEntry.setStartPc((short)dis.readUnsignedShort());
		lineNumberTableEntry.setLineNumber((short)dis.readUnsignedShort());
		
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
