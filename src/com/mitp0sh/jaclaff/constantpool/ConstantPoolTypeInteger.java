package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeIntegerDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
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
	
	public static ConstantPoolTypeInteger deserialize(DataInputStream dis) throws InvalidConstantPoolTypeIntegerDeserializationException, IOException
	{
		ConstantPoolTypeInteger cptInteger = new ConstantPoolTypeInteger();
		
		cptInteger.setBytes(dis.readInt());
		
		return cptInteger;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeInteger elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getBytes(), Integer.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeInteger clone()
	{
		/* create new empty instance */
		ConstantPoolTypeInteger clone = (ConstantPoolTypeInteger)super.clone();
		
		/* fill instance with original data */
		clone.setBytes(this.getBytes());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		try
		{
			ConstantPoolTypeInteger cpt = (ConstantPoolTypeInteger)obj;
			return cpt.bytes == this.bytes;
		}
		catch(NullPointerException e){}
		catch(ClassCastException e){}
		
		return false;
	}
}