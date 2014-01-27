package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeUtf8DeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeUtf8 extends AbstractConstantPoolType
{
	private String  bytes = "";
	
	public ConstantPoolTypeUtf8()
	{
		super.setConstant_pool_string_representation("CONSTANT_Utf8");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_UTF8);
	}
	
	public int getLength() 
	{
		return bytes.length();
	}

	public String getBytes() 
	{
		return bytes;
	}
	
	public void setBytes(String bytes) 
	{
		this.bytes = bytes;
	}
	
	public static ConstantPoolTypeUtf8 deserialize(DataInputStream dis) throws IOException,
	                                                                           InvalidConstantPoolTypeUtf8DeserializationException
	{
		ConstantPoolTypeUtf8 cptUtf8 = new ConstantPoolTypeUtf8();		
		
		int length = dis.readUnsignedShort();
		byte[] bUtf8 = new byte[length];					
		for(int y = 0; y < length; y++)
		{
			bUtf8[y] = (byte)dis.readUnsignedByte();
		}
		cptUtf8.setBytes(new String(bUtf8));
		
		return cptUtf8;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeUtf8 elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getBytes().length(), Short.class));
		baos.write(elem.getBytes().getBytes());
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeUtf8 clone()
	{
		/* create new empty instance */
		ConstantPoolTypeUtf8 clone = (ConstantPoolTypeUtf8)super.clone();
		
		/* fill instance with original data */
		clone.setBytes(this.getBytes());
		
		return clone;
	}
	
	@Override
	public String toString()
	{	
		return getBytes();
	}
}
