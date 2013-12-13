package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.localvariabletable.LocalVariableTable;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeLocalVariableTable extends AbstractAttribute 
{
	private short                    localVariableTableLength = 0;
	private LocalVariableTable             localVariableTable = null;
	
	public short getLocalVariableTableLength() 
	{
		return localVariableTableLength;
	}
	
	public void setLocalVariableTableLength(short localVariableTableLength)
	{
		this.localVariableTableLength = localVariableTableLength;
	}
	
	public LocalVariableTable getLocalVariableTable()
	{
		return localVariableTable;
	}
	
	public void setLocalVariableTable(LocalVariableTable localVariableTable)
	{
		this.localVariableTable = localVariableTable;
	}
	
	public static AttributeLocalVariableTable deserialize(DataInputStream dis) throws IOException
    {
		AttributeLocalVariableTable attribute = new AttributeLocalVariableTable();
		
		attribute.setLocalVariableTableLength((short)dis.readUnsignedShort());
		attribute.setLocalVariableTable(LocalVariableTable.deserialize(dis, attribute.getLocalVariableTableLength()));
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeLocalVariableTable attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getLocalVariableTableLength(), Short.class));
		baos.write(LocalVariableTable.serialize(attribute.getLocalVariableTable()));
		
		return baos.toByteArray();
	}
}
