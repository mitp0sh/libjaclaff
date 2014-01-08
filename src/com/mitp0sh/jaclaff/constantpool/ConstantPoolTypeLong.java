package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeLong extends AbstractConstantPoolType
{
	private int highBytes;
	private int lowBytes;
	
	public ConstantPoolTypeLong()
	{
		super.setConstant_pool_string_representation("CONSTANT_Long");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_LONG);
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
	
	public static ConstantPoolTypeLong deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeLong cptLong = new ConstantPoolTypeLong();
		
		cptLong.setHighBytes(dis.readInt());
		cptLong.setLowBytes(dis.readInt());
		
		return cptLong;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeLong elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getHighBytes(), Integer.class));
		baos.write(PNC.toByteArray(elem.getLowBytes(), Integer.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeLong clone()
	{
		/* create new empty instance */
		ConstantPoolTypeLong clone = (ConstantPoolTypeLong)super.clone();
		
		/* fill instance with original data */
		clone.setHighBytes(this.getHighBytes());
		clone.setLowBytes(this.getLowBytes());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		ConstantPoolTypeLong cptLong = null;
		try
		{
			cptLong = (ConstantPoolTypeLong)obj;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		if(cptLong.highBytes != this.highBytes)
		{
			return false;
		}
		
		return cptLong.lowBytes == this.lowBytes;
	}
}