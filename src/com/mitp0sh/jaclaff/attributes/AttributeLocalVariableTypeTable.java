package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.localvariabletypetable.LocalVariableTypeTable;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeLocalVariableTypeTable extends AbstractAttribute 
{
	private short                    localVariableTypeTableLength = 0;
	private LocalVariableTypeTable         localVariableTypeTable = null;
	
	public short getLocalVariableTypeTableLength() 
	{
		return localVariableTypeTableLength;
	}
	
	public void setLocalVariableTypeTableLength(short localVariableTypeTableLength)
	{
		this.localVariableTypeTableLength = localVariableTypeTableLength;
	}
	
	public LocalVariableTypeTable getLocalVariableTypeTable()
	{
		return localVariableTypeTable;
	}
	
	public void setLocalVariableTypeTable(LocalVariableTypeTable localVariableTypeTable)
	{
		this.localVariableTypeTable = localVariableTypeTable;
	}
	
	public static AttributeLocalVariableTypeTable deserialize(DataInputStream dis) throws IOException
    {
		AttributeLocalVariableTypeTable attribute = new AttributeLocalVariableTypeTable();
		
		attribute.setLocalVariableTypeTableLength((short)dis.readUnsignedShort());
		attribute.setLocalVariableTypeTable(LocalVariableTypeTable.deserialize(dis, attribute.getLocalVariableTypeTableLength()));
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeLocalVariableTypeTable attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getLocalVariableTypeTableLength(), Short.class));
		baos.write(LocalVariableTypeTable.serialize(attribute.getLocalVariableTypeTable()));
		
		return baos.toByteArray();
	}
}
