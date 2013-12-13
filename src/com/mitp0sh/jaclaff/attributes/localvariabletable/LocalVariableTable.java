package com.mitp0sh.jaclaff.attributes.localvariabletable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class LocalVariableTable 
{
	private LocalVariableTableEntry[] localVariableTable = new LocalVariableTableEntry[0];
	
	public LocalVariableTable(short localVariableTableLength)
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
	
	public static LocalVariableTable deserialize(DataInputStream dis, short localVariableTableLength) throws IOException
    {
		LocalVariableTable localVariableTable = new LocalVariableTable(localVariableTableLength);
		
		for(int i = 0; i < localVariableTableLength; i++)
		{
			localVariableTable.getLocalVariableTable()[i] = LocalVariableTableEntry.deserialize(dis);
		}
	    
		return localVariableTable;
    }
	
	public static byte[] serialize(LocalVariableTable localVariableTable) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < localVariableTable.localVariableTable.length; i++)
		{
			baos.write(LocalVariableTableEntry.serialize(localVariableTable.getLocalVariableTable()[i]));
		}
		
		return baos.toByteArray();
	}
}
