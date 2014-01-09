package com.mitp0sh.jaclaff.attributes.localvariabletable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class LocalVariableTable 
{
	private LocalVariableTableEntry[] localVariableTable = new LocalVariableTableEntry[0];
	
	public int getLocalVariableTableLength()
	{
		return localVariableTable.length;
	}
	
	public LocalVariableTable(int localVariableTableLength)
	{
		this.localVariableTable = new LocalVariableTableEntry[localVariableTableLength];
	}

	public LocalVariableTableEntry[] getLocalVariableTable()
	{
		return localVariableTable;
	}

	public void setLocalVariableTable(LocalVariableTableEntry[] localVariableTable) 
	{
		this.localVariableTable = localVariableTable;
	}
	
	public static LocalVariableTable deserialize(DesCtx ctx, int length, AttributeCode attributeCode) throws IOException
    {		
		LocalVariableTable localVariableTable = new LocalVariableTable(length);
		
		for(int i = 0; i < length; i++)
		{
			localVariableTable.getLocalVariableTable()[i] = LocalVariableTableEntry.deserialize(ctx, attributeCode);
		}
	    
		return localVariableTable;
    }
	
	public static byte[] serialize(SerCtx ctx, LocalVariableTable table, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < table.getLocalVariableTableLength(); i++)
		{
			LocalVariableTableEntry current = table.getLocalVariableTable()[i];
			baos.write(LocalVariableTableEntry.serialize(ctx, current, attributeCode));
		}
		
		return baos.toByteArray();
	}
}
