package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeInteger extends AbstractConstantPoolType
{
	private int bytes = 0;
	
	public ConstantPoolTypeInteger()
	{
		super.setConstant_pool_string_representation("CONSTANT_Integer");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_INTEGER);
	}

	public int getBytes() 
	{
		return bytes;
	}

	public void setBytes(int bytes) 
	{
		this.bytes = bytes;
	}
	
	public static ConstantPoolTypeInteger deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeInteger cptInteger = new ConstantPoolTypeInteger();
		
		cptInteger.setBytes(dis.readInt());
		
		return cptInteger;
	}
	
	public static byte[] serialize(ConstantPoolTypeInteger elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getBytes(), Integer.class));
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeInteger clone(ConstantPoolTypeInteger src)
	{
		/* create new empty instance */
		ConstantPoolTypeInteger clone = new ConstantPoolTypeInteger();
		
		/* fill instance with original data */
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		clone.setBytes(src.getBytes());
		
		return clone;
	}
}