package com.mitp0sh.jaclaff.attributes.localvariabletypetable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class LocalVariableTypeTable 
{	
	private LocalVariableTypeTableEntry[] localVariableTypeTable = new LocalVariableTypeTableEntry[0];
	
	public int getLocalVariableTypeTableLength()
	{
		return localVariableTypeTable.length;
	}
	
	public LocalVariableTypeTable(int localVariableTypeTableLength)
	{
		this.localVariableTypeTable = new LocalVariableTypeTableEntry[localVariableTypeTableLength];
	}

	public LocalVariableTypeTableEntry[] getLocalVariableTypeTable()
	{
		return localVariableTypeTable;
	}

	public void setLocalVariableTypeTable(LocalVariableTypeTableEntry[] localVariableTypeTable) 
	{
		this.localVariableTypeTable = localVariableTypeTable;
	}
	
	public static LocalVariableTypeTable deserialize(DesCtx ctx, int length, AttributeCode attributeCode) throws IOException
    {
		LocalVariableTypeTable localVariableTypeTable = new LocalVariableTypeTable(length);
		
		for(int i = 0; i < length; i++)
		{
			localVariableTypeTable.getLocalVariableTypeTable()[i] = LocalVariableTypeTableEntry.deserialize(ctx, attributeCode);
		}
	    
		return localVariableTypeTable;
    }
	
	public static byte[] serialize(SerCtx ctx, LocalVariableTypeTable table, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < table.getLocalVariableTypeTableLength(); i++)
		{
			LocalVariableTypeTableEntry current = table.getLocalVariableTypeTable()[i];
			baos.write(LocalVariableTypeTableEntry.serialize(ctx, current, attributeCode));
		}
		
		return baos.toByteArray();
	}
}
