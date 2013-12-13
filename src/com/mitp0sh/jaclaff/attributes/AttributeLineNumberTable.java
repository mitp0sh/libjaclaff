package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.linenumbertable.LineNumberTable;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeLineNumberTable extends AbstractAttribute
{
	private short           lineNumberTableLength = 0;
	private LineNumberTable       lineNumberTable = null;
	
	public short getLineNumberTableLength()
	{
		return lineNumberTableLength;
	}
	
	public void setLineNumberTableLength(short lineNumberTableLength)
	{
		this.lineNumberTableLength = lineNumberTableLength;
	}
	
	public LineNumberTable getLineNumberTable() 
	{
		return lineNumberTable;
	}
	
	public void setLineNumberTable(LineNumberTable lineNumberTable)
	{
		this.lineNumberTable = lineNumberTable;
	}
	
	public static AttributeLineNumberTable deserialize(DataInputStream dis) throws IOException
    {		
		AttributeLineNumberTable attribute = new AttributeLineNumberTable();

		attribute.setLineNumberTableLength((short)(dis.readUnsignedShort()));
		attribute.setLineNumberTable(LineNumberTable.deserialize(dis, attribute.getLineNumberTableLength()));
				
		return attribute;
    }
	
	public static byte[] serialize(AttributeLineNumberTable attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getLineNumberTableLength(), Short.class));
		baos.write(LineNumberTable.serialize(attribute.getLineNumberTable()));
		
		return baos.toByteArray();
	}
}
