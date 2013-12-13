package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeFloat extends AbstractConstantPoolType
{
	private int bytes = 0;

	public ConstantPoolTypeFloat()
	{
		super.setConstant_pool_string_representation("CONSTANT_Float");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_FLOAT);
	}
	
	public int getBytes() 
	{
		return this.bytes;
	}

	public void setBytes(int bytes) 
	{
		this.bytes = bytes;
	}
	
	public static ConstantPoolTypeFloat deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeFloat cptFloat = new ConstantPoolTypeFloat();
		
		cptFloat.setBytes(dis.readInt());
		
		return cptFloat;
	}
	
	public static byte[] serialize(ConstantPoolTypeFloat elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getBytes(), Integer.class));
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeFloat clone(ConstantPoolTypeFloat src)
	{
		/* create new empty instance */
		ConstantPoolTypeFloat clone = new ConstantPoolTypeFloat();
		
		/* fill instance with original data */
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		clone.setBytes(src.getBytes());
		
		return clone;
	}
}
