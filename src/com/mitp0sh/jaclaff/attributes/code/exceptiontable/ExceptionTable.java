package com.mitp0sh.jaclaff.attributes.code.exceptiontable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.attributes.code.Disassembly;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class ExceptionTable 
{
	private ArrayList<ExceptionTableEntry> exceptionTable = new ArrayList<ExceptionTableEntry>();
	
	public int getExceptionTableLength()
	{
		return this.exceptionTable.size();
	}
	
	public ArrayList<ExceptionTableEntry> getExceptionTable()
	{
		return exceptionTable;
	}
	
	public static ExceptionTable deserialize(DataInputStream dis, int exceptionTableLength, ConstantPool constantPool, Disassembly disassembly) throws IOException
    {	
		ExceptionTable attribute = new ExceptionTable();
		
		for(int i = 0; i < exceptionTableLength; i++)
		{
			attribute.getExceptionTable().add(ExceptionTableEntry.deserialize(dis, constantPool, disassembly));
		}  
	    
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, ExceptionTable exceptionTable, Disassembly disassembly) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Iterator<ExceptionTableEntry> iter = exceptionTable.getExceptionTable().iterator();
		while(iter.hasNext())
		{
			ExceptionTableEntry current = iter.next();
			baos.write(ExceptionTableEntry.serialize(ctx, current, disassembly));
		}
		
		return baos.toByteArray();
	}
}
