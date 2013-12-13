package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;


public class ExceptionTable 
{
	private ExceptionTableEntry[] exceptionTable = null;

	public ExceptionTable(short exceptionTableLength)
	{
		this.exceptionTable = new ExceptionTableEntry[exceptionTableLength];		
	}
	
	public short getExceptionTableLength()
	{
		return (short)this.exceptionTable.length;
	}
	
	public ExceptionTableEntry[] getExceptionTable()
	{
		return exceptionTable;
	}
	
	public static ExceptionTable deserialize(DataInputStream dis, short exceptionTableLength, ConstantPool constantPool) throws IOException
    {	
		ExceptionTable attribute = new ExceptionTable(exceptionTableLength);
		
		for(int i = 0; i < exceptionTableLength; i++)
		{
			attribute.getExceptionTable()[i] = ExceptionTableEntry.deserialize(dis, constantPool);
		}  
	    
		return attribute;
    }
	
	public static byte[] serialize(ExceptionTable exceptionTable) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < exceptionTable.getExceptionTableLength(); i++)
		{
			baos.write(ExceptionTableEntry.serialize(exceptionTable.getExceptionTable()[i]));
		} 
		
		return baos.toByteArray();
	}
}
