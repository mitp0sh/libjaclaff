package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

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
	
	public static byte[] serialize(ConstantPoolTypeLong elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getHighBytes(), Integer.class));
		baos.write(PNC.toByteArray(elem.getLowBytes(), Integer.class));
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeLong clone(ConstantPoolTypeLong src)
	{
		/* create new empty instance */
		ConstantPoolTypeLong clone = new ConstantPoolTypeLong();
		
		/* fill instance with original data */
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		clone.setHighBytes(src.getHighBytes());
		clone.setLowBytes(src.getLowBytes());
		
		return clone;
	}
}