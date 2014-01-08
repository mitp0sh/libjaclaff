package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeString extends AbstractConstantPoolType
{
	private int                  	stringIndex = 0;
	private ConstantPoolTypeUtf8      cptString = null;

	public ConstantPoolTypeString()
	{
		super.setConstant_pool_string_representation("CONSTANT_String");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING);
	}

	public int getStringIndex()
	{
		return this.stringIndex;
	}

	public void setStringIndex(int stringIndex) 
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
		
		cptString.setStringIndex(dis.readUnsignedShort());
		
		return cptString;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeString elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getStringIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeString clone()
	{
		/* create new empty instance */
		ConstantPoolTypeString clone = (ConstantPoolTypeString)super.clone();
		
		/* fill with data */		
		clone.setCptString(this.getCptString());
		
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
