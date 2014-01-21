package com.mitp0sh.jaclaff.attributes.localvariabletable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class LocalVariableTable 
{
	private ArrayList<LocalVariableTableEntry> localVariableTable = new ArrayList<LocalVariableTableEntry>();
	
	public int getLocalVariableTableLength()
	{
		return localVariableTable.size();
	}

	public ArrayList<LocalVariableTableEntry> getLocalVariableTable()
	{
		return localVariableTable;
	}

	public void setLocalVariableTable(ArrayList<LocalVariableTableEntry> localVariableTable) 
	{
		this.localVariableTable = localVariableTable;
	}
	
	public static LocalVariableTable deserialize(DesCtx ctx, int length, AttributeCode attributeCode) throws IOException
    {		
		LocalVariableTable localVariableTable = new LocalVariableTable();
		
		for(int i = 0; i < length; i++)
		{
			localVariableTable.getLocalVariableTable().add(LocalVariableTableEntry.deserialize(ctx, attributeCode));
		}
	    
		return localVariableTable;
    }
	
	public static byte[] serialize(SerCtx ctx, LocalVariableTable table, AttributeCode attributeCode) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Iterator<LocalVariableTableEntry> iter = table.getLocalVariableTable().iterator();
		while(iter.hasNext())
		{
			LocalVariableTableEntry current = iter.next();
			baos.write(LocalVariableTableEntry.serialize(ctx, current, attributeCode));
		}
		
		return baos.toByteArray();
	}
}
