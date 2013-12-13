package com.mitp0sh.jaclaff.attributes.localvariabletypetable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class LocalVariableTypeTable 
{	
	private LocalVariableTypeTableEntry[] localVariableTypeTable = new LocalVariableTypeTableEntry[0];
	
	public LocalVariableTypeTable(short localVariableTypeTableLength)
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
	
	public static LocalVariableTypeTable deserialize(DataInputStream dis, short localVariableTypeTableLength) throws IOException
    {
		LocalVariableTypeTable localVariableTypeTable = new LocalVariableTypeTable(localVariableTypeTableLength);
		
		for(int i = 0; i < localVariableTypeTableLength; i++)
		{
			localVariableTypeTable.getLocalVariableTypeTable()[i] = LocalVariableTypeTableEntry.deserialize(dis);
		}
	    
		return localVariableTypeTable;
    }
	
	public static byte[] serialize(LocalVariableTypeTable localVariableTypeTable) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < localVariableTypeTable.localVariableTypeTable.length; i++)
		{
			baos.write(LocalVariableTypeTableEntry.serialize(localVariableTypeTable.getLocalVariableTypeTable()[i]));
		}
		
		return baos.toByteArray();
	}
}
