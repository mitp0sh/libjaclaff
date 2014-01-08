package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.code.bytecode.MethodInstructions;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class ExceptionTable 
{
	private ExceptionTableEntry[] exceptionTable = null;

	public ExceptionTable(int exceptionTableLength)
	{
		this.exceptionTable = new ExceptionTableEntry[exceptionTableLength];		
	}
	
	public int getExceptionTableLength()
	{
		return (short)this.exceptionTable.length;
	}
	
	public ExceptionTableEntry[] getExceptionTable()
	{
		return exceptionTable;
	}
	
	public static ExceptionTable deserialize(DataInputStream dis, int exceptionTableLength, ConstantPool constantPool, MethodInstructions disassembly) throws IOException
    {	
		ExceptionTable attribute = new ExceptionTable(exceptionTableLength);
		
		for(int i = 0; i < exceptionTableLength; i++)
		{
			attribute.getExceptionTable()[i] = ExceptionTableEntry.deserialize(dis, constantPool, disassembly);
		}  
	    
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, ExceptionTable exceptionTable, MethodInstructions disassembly) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < exceptionTable.getExceptionTableLength(); i++)
		{
			baos.write(ExceptionTableEntry.serialize(ctx, exceptionTable.getExceptionTable()[i], disassembly));
		} 
		
		return baos.toByteArray();
	}
}
