package com.mitp0sh.jaclaff.attributes.linenumbertable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class LineNumberTable
{
	private ArrayList<LineNumberTableEntry> lineNumberTable = new ArrayList<LineNumberTableEntry>();
	
	public int getLineNumberTableLength()
	{
		return lineNumberTable.size();
	}
	
	public ArrayList<LineNumberTableEntry> getLineNumberTable()
	{
		return lineNumberTable;
	}
	
	public static LineNumberTable deserialize(DesCtx ctx, int length, AttributeCode attributeCode) throws IOException
    {	
		LineNumberTable lineNumberTable = new LineNumberTable();
		for(int i = 0; i < length; i++)
		{
			lineNumberTable.getLineNumberTable().add(LineNumberTableEntry.deserialize(ctx, attributeCode));
		}
		
		return lineNumberTable;
    }
	
	public static byte[] serialize(SerCtx ctx, LineNumberTable lineNumberTable, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Iterator<LineNumberTableEntry> iter = lineNumberTable.getLineNumberTable().iterator();
		while(iter.hasNext())
		{
			LineNumberTableEntry current = iter.next();
			baos.write(LineNumberTableEntry.serialize(ctx, current, attributeCode));
		}
		
		return baos.toByteArray();
	}
}
