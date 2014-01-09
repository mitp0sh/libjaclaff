package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.localvariabletable.LocalVariableTable;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeLocalVariableTable extends AbstractAttribute 
{
	private LocalVariableTable localVariableTable = null;
	
	public int getLocalVariableTableLength() 
	{
		if(localVariableTable != null)
		{
			return localVariableTable.getLocalVariableTableLength();
		}
		return 0;
	}
	
	public LocalVariableTable getLocalVariableTable()
	{
		return localVariableTable;
	}
	
	public void setLocalVariableTable(LocalVariableTable localVariableTable)
	{
		this.localVariableTable = localVariableTable;
	}
	
	public static AttributeLocalVariableTable deserialize(DesCtx ctx, AttributeCode attributeCode) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeLocalVariableTable attribute = new AttributeLocalVariableTable();
		
		int localVariableTableLength = dis.readUnsignedShort();
		attribute.setLocalVariableTable(LocalVariableTable.deserialize(ctx, localVariableTableLength, attributeCode));
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeLocalVariableTable attribute, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getLocalVariableTableLength(), Short.class));
		baos.write(LocalVariableTable.serialize(ctx, attribute.getLocalVariableTable(), attributeCode));
		
		return baos.toByteArray();
	}
}
