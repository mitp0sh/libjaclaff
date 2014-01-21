package com.mitp0sh.jaclaff.attributes.localvariabletypetable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class LocalVariableTypeTable 
{	
	private ArrayList<LocalVariableTypeTableEntry> localVariableTypeTable = new ArrayList<LocalVariableTypeTableEntry>();
	
	public int getLocalVariableTypeTableLength()
	{
		return localVariableTypeTable.size();
	}

	public ArrayList<LocalVariableTypeTableEntry> getLocalVariableTypeTable()
	{
		return localVariableTypeTable;
	}

	public void setLocalVariableTypeTable(ArrayList<LocalVariableTypeTableEntry> localVariableTypeTable) 
	{
		this.localVariableTypeTable = localVariableTypeTable;
	}
	
	public static LocalVariableTypeTable deserialize(DesCtx ctx, int length, AttributeCode attributeCode) throws IOException
    {
		LocalVariableTypeTable localVariableTypeTable = new LocalVariableTypeTable();
		
		for(int i = 0; i < length; i++)
		{
			localVariableTypeTable.getLocalVariableTypeTable().add(LocalVariableTypeTableEntry.deserialize(ctx, attributeCode));
		}
	    
		return localVariableTypeTable;
    }
	
	public static byte[] serialize(SerCtx ctx, LocalVariableTypeTable table, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Iterator<LocalVariableTypeTableEntry> iter = table.getLocalVariableTypeTable().iterator();
		while(iter.hasNext())
		{
			LocalVariableTypeTableEntry current = iter.next();
			baos.write(LocalVariableTypeTableEntry.serialize(ctx, current, attributeCode));
		}
		
		return baos.toByteArray();
	}
}
