package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeFloatDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
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
	
	public static ConstantPoolTypeFloat deserialize(DataInputStream dis) throws InvalidConstantPoolTypeFloatDeserializationException, IOException
	{
		ConstantPoolTypeFloat cptFloat = new ConstantPoolTypeFloat();
		
		cptFloat.setBytes(dis.readInt());
		
		return cptFloat;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeFloat elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getBytes(), Integer.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeFloat clone()
	{
		/* create new empty instance */
		ConstantPoolTypeFloat clone = (ConstantPoolTypeFloat)super.clone();
		
		/* fill instance with original data */
		clone.setBytes(this.getBytes());
		
		return clone;
	}
	
	@Override
	public String toString()
	{	
		return "TODO - NOT YET IMPELEMENTED !!!";
	}
}
