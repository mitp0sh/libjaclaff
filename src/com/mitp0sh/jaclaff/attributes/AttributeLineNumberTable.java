package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.linenumbertable.LineNumberTable;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeLineNumberTable extends AbstractAttribute
{
	private LineNumberTable lineNumberTable = null;
	
	public int getLineNumberTableLength()
	{
		if(lineNumberTable != null)
		{
			return lineNumberTable.getLineNumberTableLength();
		}
		
		return 0;
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

		int lineNumberTableLength = dis.readUnsignedShort();
		attribute.setLineNumberTable(LineNumberTable.deserialize(ctx, lineNumberTableLength, attributeCode));
				
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeLineNumberTable attribute, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getLineNumberTableLength(), Short.class));
		baos.write(LineNumberTable.serialize(ctx, attribute.getLineNumberTable(), attributeCode));
		
		return baos.toByteArray();
	}
}
