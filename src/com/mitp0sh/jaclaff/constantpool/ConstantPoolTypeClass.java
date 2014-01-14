package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeClass extends AbstractConstantPoolType
{
	private int                   nameIndex = 0;
	private ConstantPoolTypeUtf8 nameObject = null;	

	public ConstantPoolTypeClass()
	{
		super.setConstant_pool_string_representation("CONSTANT_Class");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS);	
	}	
	
	public int getNameIndex()
	{
		return this.nameIndex;
	}

	public void setNameIndex(int nameIndex) 
	{
		this.nameIndex = nameIndex;
	}
	
	public ConstantPoolTypeUtf8 getNameObject()
	{
		return nameObject;
	}

	public void setNameObject(ConstantPoolTypeUtf8 nameObject)
	{
		this.nameObject = nameObject;
	}
	
	public static ConstantPoolTypeClass deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeClass cptClass = new ConstantPoolTypeClass();
		
		cptClass.setNameIndex(dis.readUnsignedShort());
		
		return cptClass;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeClass elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getNameIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeClass clone()
	{
		/* create new empty instance */
		ConstantPoolTypeClass clone = (ConstantPoolTypeClass)super.clone();
	
		/* fill instance with original data */
		clone.setNameObject(this.getNameObject());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if(obj == null)
		{
			return false;
		}
		
		ConstantPoolTypeClass cptClass = null;
		try
		{
			cptClass = (ConstantPoolTypeClass)obj;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return cptClass.nameObject.equals(this.nameObject);
	}
	
	public String getNonQualifiedClassName()
	{
		if(getNameObject() == null)
		{
			return "0";
		}
		
		String bytes = getNameObject().getBytes();
		if(bytes == null)
		{
			return "1";
		}
		
		String[] splitedBytes = bytes.split("/");
		return splitedBytes[splitedBytes.length - 1];
	}
	
	public String getFullQualifiedClassName()
	{
		if(getNameObject() == null)
		{
			return "0";
		}
		
		String bytes = getNameObject().getBytes();
		if(bytes == null)
		{
			return "1";
		}
		
		bytes = bytes.replaceAll("/", ".");
		return bytes;
	}

	@Override
	public String toString()
	{	
		return getFullQualifiedClassName();
	}
}
