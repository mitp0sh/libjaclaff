package com.mitp0sh.jaclaff.serialization;

import com.mitp0sh.jaclaff.VirtualClassFile;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;

public class SerCtx
{
	private ConstantPool         constantPool = null;
	private VirtualClassFile virtualClassFile = null;

	public SerCtx()
	{
		super();
	}
	
	public SerCtx(VirtualClassFile virtualClassFile, ConstantPool constantPool)
	{
		super();
		this.constantPool     = constantPool;
		this.virtualClassFile = virtualClassFile;
	}
	
	public ConstantPool getConstantPool() 
	{
		return constantPool;
	}

	public void setConstantPool(ConstantPool constantPool) 
	{
		this.constantPool = constantPool;
	}

	public VirtualClassFile getVirtualClassFile() 
	{
		return virtualClassFile;
	}

	public void setVirtualClassFile(VirtualClassFile virtualClassFile) 
	{
		this.virtualClassFile = virtualClassFile;
	}
}
