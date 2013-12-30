package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeString extends AbstractConstantPoolType
{
	private short                  	stringIndex = 0;
	private ConstantPoolTypeUtf8      cptString = null;

	public ConstantPoolTypeString()
	{
		super.setConstant_pool_string_representation("CONSTANT_String");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING);
	}

	public short getStringIndex()
	{
		return this.stringIndex;
	}

	public void setStringIndex(short stringIndex) 
	{
		this.stringIndex = stringIndex;
	}
	
	public ConstantPoolTypeUtf8 getCptString() 
	{
		return cptString;
	}

	public void setCptString(ConstantPoolTypeUtf8 cptString)
	{
		this.cptString = cptString;
	}
	
	public static ConstantPoolTypeString deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeString cptString = new ConstantPoolTypeString();
		
		cptString.setStringIndex((short)dis.readUnsignedShort());
		
		return cptString;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeString elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getStringIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeString clone(ConstantPoolTypeString src)
	{
		/* create empty wrapper */
		ConstantPoolTypeString clone = new ConstantPoolTypeString();
		
		/* fill with data */		
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		clone.setCptString(ConstantPoolTypeUtf8.clone(src.cptString));
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		ConstantPoolTypeString cptString = null;
		try
		{
			cptString = (ConstantPoolTypeString)obj;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return cptString.cptString.equals(this.cptString);
	}
}
