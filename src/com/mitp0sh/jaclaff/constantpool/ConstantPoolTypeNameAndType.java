package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeNameAndType extends AbstractConstantPoolType
{
	private short                      nameIndex = 0;	
	private short                descriptorIndex = 0;	
	private ConstantPoolTypeUtf8         cptName = null;
	private ConstantPoolTypeUtf8   cptDescriptor = null;

	public ConstantPoolTypeNameAndType()
	{
		super.setConstant_pool_string_representation("CONSTANT_NameAndType");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE);
	}
	
	public short getNameIndex()
	{
		return this.nameIndex;
	}
	
	public void setNameIndex(short nameIndex) 
	{
		this.nameIndex = nameIndex;
	}
	
	public short getDescriptorIndex() 
	{
		return this.descriptorIndex;
	}
	
	public void setDescriptorIndex(short descriptorIndex)
	{
		this.descriptorIndex = descriptorIndex;
	}
	
	public ConstantPoolTypeUtf8 getCptName() 
	{
		return cptName;
	}

	public void setCptName(ConstantPoolTypeUtf8 cptName) 
	{
		this.cptName = cptName;
	}

	public ConstantPoolTypeUtf8 getCptDescriptor() 
	{
		return cptDescriptor;
	}

	public void setCptDescriptor(ConstantPoolTypeUtf8 cptDescriptor)
	{
		this.cptDescriptor = cptDescriptor;
	}
	
	public static ConstantPoolTypeNameAndType deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeNameAndType cptNameAndType = new ConstantPoolTypeNameAndType();
		
		cptNameAndType.setNameIndex((short)dis.readUnsignedShort());
		cptNameAndType.setDescriptorIndex((short)dis.readUnsignedShort());
		
		return cptNameAndType;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeNameAndType elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getNameIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getDescriptorIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeNameAndType clone(ConstantPoolTypeNameAndType src)
	{
		/* create new empty instance */
		ConstantPoolTypeNameAndType clone = new ConstantPoolTypeNameAndType();
		
		/* fill instance with original data */
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		clone.setCptName(src.getCptName());
		clone.setCptDescriptor(src.getCptDescriptor());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		ConstantPoolTypeNameAndType cptNameAndType = null;
		try
		{
			cptNameAndType = (ConstantPoolTypeNameAndType)obj;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		if(!cptNameAndType.cptName.equals(this.cptName))
		{
			return false;
		}
		
		return cptNameAndType.cptDescriptor.equals(this.cptDescriptor);
	}
}
