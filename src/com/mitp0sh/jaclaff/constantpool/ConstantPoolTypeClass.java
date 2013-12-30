package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeClass extends AbstractConstantPoolType
{
	private short                  nameIndex = 0;
	private ConstantPoolTypeUtf8     cptName = null;	

	public ConstantPoolTypeClass()
	{
		super.setConstant_pool_string_representation("CONSTANT_Class");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS);	
	}	
	
	public short getNameIndex()
	{
		return this.nameIndex;
	}

	public void setNameIndex(short nameIndex) 
	{
		this.nameIndex = nameIndex;
	}
	
	public ConstantPoolTypeUtf8 getCptName()
	{
		return cptName;
	}

	public void setCptName(ConstantPoolTypeUtf8 cptName)
	{
		this.cptName = cptName;
	}
	
	public static ConstantPoolTypeClass deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeClass cptClass = new ConstantPoolTypeClass();
		
		cptClass.setNameIndex((short)dis.readUnsignedShort());
		
		return cptClass;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeClass elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getNameIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeClass clone(ConstantPoolTypeClass src)
	{
		/* create new empty instance */
		ConstantPoolTypeClass clone = new ConstantPoolTypeClass();
		
		/* fill instance with original data */
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		clone.setCptName(src.getCptName());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		ConstantPoolTypeClass cptClass = null;
		try
		{
			cptClass = (ConstantPoolTypeClass)obj;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return cptClass.cptName.equals(this.cptName);
	}
}
