package com.mitp0sh.jaclaff.attributes.linenumbertable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class LineNumberTable
{
	private LineNumberTableEntry[] lineNumberTable = new LineNumberTableEntry[0];

	public LineNumberTable(int lineNumberTableLength)
	{
		lineNumberTable = new LineNumberTableEntry[lineNumberTableLength];
	}
	
	public int getLineNumberTableLength()
	{
		return lineNumberTable.length;
	}
	
	public LineNumberTableEntry[] getLineNumberTable()
	{
		return lineNumberTable;
	}

	public void setLineNumberTable(LineNumberTableEntry[] lineNumberTable) 
	{
		this.lineNumberTable = lineNumberTable;
	}
	
	public static LineNumberTable deserialize(DesCtx ctx, int length, AttributeCode attributeCode) throws IOException
    {	
		LineNumberTable lineNumberTable = new LineNumberTable(length);
		for(int i = 0; i < length; i++)
		{
			lineNumberTable.getLineNumberTable()[i] = LineNumberTableEntry.deserialize(ctx, attributeCode);
		}
		
		return lineNumberTable;
    }
	
	public static byte[] serialize(SerCtx ctx, LineNumberTable lineNumberTable, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < lineNumberTable.getLineNumberTableLength(); i++)
		{
			LineNumberTableEntry current = lineNumberTable.getLineNumberTable()[i];
			baos.write(LineNumberTableEntry.serialize(ctx, current, attributeCode));
		}
		
		return baos.toByteArray();
	}
}
