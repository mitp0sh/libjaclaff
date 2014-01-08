package com.mitp0sh.jaclaff.deserialization;

import java.io.DataInputStream;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;

public class DesCtx
{
	private ConstantPool       constantPool = null;
	private DataInputStream dataInputStream = null;
	
	public ConstantPool getConstantPool()
	{
		return constantPool;
	}
	
	public void setConstantPool(ConstantPool constantPool)
	{
		this.constantPool = constantPool;
	}
	
	public DataInputStream getDataInputStream()
	{
		return dataInputStream;
	}
	
	public void setDataInputStream(DataInputStream dataInputStream)
	{
		this.dataInputStream = dataInputStream;
	}
}
