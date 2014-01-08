package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.linenumbertable.LineNumberTable;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class AttributeLineNumberTable extends AbstractAttribute
{
	private int           lineNumberTableLength = 0;
	private LineNumberTable     lineNumberTable = null;
	
	public int getLineNumberTableLength()
	{
		return lineNumberTableLength;
	}
	
	public void setLineNumberTableLength(int lineNumberTableLength)
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
	
	public static AttributeLineNumberTable deserialize(DesCtx ctx, AttributeCode attributeCode) throws IOException
    {		
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeLineNumberTable attribute = new AttributeLineNumberTable();

		attribute.setLineNumberTableLength(dis.readUnsignedShort());
		attribute.setLineNumberTable(LineNumberTable.deserialize(ctx, attribute.getLineNumberTableLength()));
				
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
