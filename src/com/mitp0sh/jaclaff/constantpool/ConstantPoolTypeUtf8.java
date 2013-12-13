package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeUtf8 extends AbstractConstantPoolType
{
	private short  length = 0;
	private String  bytes = "";
	
	public ConstantPoolTypeUtf8()
	{
		super.setConstant_pool_string_representation("CONSTANT_Utf8");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_UTF8);
	}
	
	public short getLength() 
	{
		return length;
	}
	
	public void setLength(short length) 
	{
		this.length = length;
	}
	
	public String getBytes() 
	{
		return bytes;
	}
	
	public void setBytes(String bytes) 
	{
		this.bytes = bytes;
	}
	
	public static ConstantPoolTypeUtf8 deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeUtf8 cptUtf8 = new ConstantPoolTypeUtf8();		
		
		cptUtf8.setLength((short)dis.readUnsignedShort());
		
		byte[] bUtf8 = new byte[cptUtf8.getLength()];					
		for(int y = 0; y < cptUtf8.getLength(); y++)
		{
			bUtf8[y] = (byte)dis.readUnsignedByte();
		}
		cptUtf8.setBytes(new String(bUtf8));
		
		return cptUtf8;
	}
	
	public static byte[] serialize(ConstantPoolTypeUtf8 elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getBytes().length(), Short.class));
		baos.write(elem.getBytes().getBytes());
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeUtf8 clone(ConstantPoolTypeUtf8 src)
	{
		/* create new empty instance */
		ConstantPoolTypeUtf8 clone = new ConstantPoolTypeUtf8();
		
		/* fill instance with original data */
		clone.setBytes(src.getBytes());
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setLength(src.getLength());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		
		return clone;
	}
}
