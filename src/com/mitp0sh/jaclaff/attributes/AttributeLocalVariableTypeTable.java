package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.localvariabletypetable.LocalVariableTypeTable;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeLocalVariableTypeTable extends AbstractAttribute 
{
	private LocalVariableTypeTable localVariableTypeTable = null;
	
	public int getLocalVariableTypeTableLength() 
	{
		if(localVariableTypeTable != null)
		{
			return localVariableTypeTable.getLocalVariableTypeTableLength();
		}
		
		return 0;
	}
	
	public LocalVariableTypeTable getLocalVariableTypeTable()
	{
		return localVariableTypeTable;
	}
	
	public void setLocalVariableTypeTable(LocalVariableTypeTable localVariableTypeTable)
	{
		this.localVariableTypeTable = localVariableTypeTable;
	}
	
	public static AttributeLocalVariableTypeTable deserialize(DesCtx ctx, AttributeCode attributeCode) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeLocalVariableTypeTable attribute = new AttributeLocalVariableTypeTable();
		
		int localVariableTypeTableLength = dis.readUnsignedShort();
		attribute.setLocalVariableTypeTable(LocalVariableTypeTable.deserialize(ctx, localVariableTypeTableLength, attributeCode));
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeLocalVariableTypeTable attribute, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getLocalVariableTypeTableLength(), Short.class));
		baos.write(LocalVariableTypeTable.serialize(ctx, attribute.getLocalVariableTypeTable(), attributeCode));
		
		return baos.toByteArray();
	}
}
