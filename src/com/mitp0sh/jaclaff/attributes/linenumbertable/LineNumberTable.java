package com.mitp0sh.jaclaff.attributes.linenumbertable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class LineNumberTable
{
	private LineNumberTableEntry[] lineNumberTable = new LineNumberTableEntry[0];

	public LineNumberTable(short lineNumberTableLength)
	{
		lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
	}
	
	public short getLineNumberTableLength()
	{
		return (short)lineNumberTable.length;
	}
	
	public LineNumberTableEntry[] getLineNumberTable()
	{
		return lineNumberTable;
	}

	public void setLineNumberTable(LineNumberTableEntry[] lineNumberTable) 
	{
		this.lineNumberTable = lineNumberTable;
	}
	
	public static LineNumberTable deserialize(DataInputStream dis, short lineNumberTableLength) throws IOException
    {	
		LineNumberTable lineNumberTable = new LineNumberTable(lineNumberTableLength);
		for(int i = 0; i < lineNumberTableLength; i++)
		{
			lineNumberTable.getLineNumberTable()[i] = LineNumberTableEntry.deserialize(dis);
		}
		
		return lineNumberTable;
    }
	
	public static byte[] serialize(LineNumberTable lineNumberTable) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < lineNumberTable.getLineNumberTableLength(); i++)
		{
			baos.write(LineNumberTableEntry.serialize(lineNumberTable.getLineNumberTable()[i]));
		}
		
		return baos.toByteArray();
	}
}
