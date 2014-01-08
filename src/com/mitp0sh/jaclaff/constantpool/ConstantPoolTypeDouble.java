package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeDouble extends AbstractConstantPoolType
{
	private int highBytes;
	private int lowBytes;
	
	public ConstantPoolTypeDouble()
	{
		super.setConstant_pool_string_representation("CONSTANT_Double");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_DOUBLE);
	}
	
	public int getHighBytes() 
	{
		return this.highBytes;
	}
	
	public void setHighBytes(int highBytes) 
	{
		this.highBytes = highBytes;
	}
	
	public int getLowBytes() 
	{
		return this.lowBytes;
	}
	
	public void setLowBytes(int lowBytes) 
	{
		this.lowBytes = lowBytes;
	}
	
	public static ConstantPoolTypeDouble deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeDouble cptDouble = new ConstantPoolTypeDouble();
		
		cptDouble.setHighBytes(dis.readInt());
		cptDouble.setLowBytes(dis.readInt());
		
		return cptDouble;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeDouble elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getHighBytes(), Integer.class));
		baos.write(PNC.toByteArray(elem.getLowBytes(), Integer.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeDouble clone()
	{
		/* create new empty instance */
		ConstantPoolTypeDouble clone = (ConstantPoolTypeDouble)super.clone();
		
		/* fill instance with original data */
		clone.setHighBytes(this.getHighBytes());
		clone.setLowBytes(this.getLowBytes());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		ConstantPoolTypeDouble cptDouble = null;
		try
		{
			cptDouble = (ConstantPoolTypeDouble)obj;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		if(cptDouble.highBytes != this.highBytes)
		{
			return false;
		}
		
		return cptDouble.lowBytes == this.lowBytes;
	}
}
