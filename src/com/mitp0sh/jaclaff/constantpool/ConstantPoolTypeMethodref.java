package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeMethodref extends AbstractConstantPoolType
{
	private short                           classIndex = 0;	
	private short                     nameAndTypeIndex = 0;	
	private ConstantPoolTypeClass             cptClass = null;
	private ConstantPoolTypeNameAndType cptNameAndType = null;
	
	public ConstantPoolTypeMethodref()
	{
		super.setConstant_pool_string_representation("CONSTANT_Methodref");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF);
	}
	
	public short getClassIndex()
	{
		return this.classIndex;
	}
	
	public void setClassIndex(short classIndex) 
	{
		this.classIndex = classIndex;
	}
	
	public short getNameAndTypeIndex() 
	{
		return this.nameAndTypeIndex;
	}
	
	public void setNameAndTypeIndex(short nameAndTypeIndex)
	{
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	public ConstantPoolTypeClass getCptClass() 
	{
		return cptClass;
	}

	public void setCptClass(ConstantPoolTypeClass cptClass) 
	{
		this.cptClass = cptClass;
	}

	public ConstantPoolTypeNameAndType getCptNameAndType() 
	{
		return cptNameAndType;
	}

	public void setCptNameAndType(ConstantPoolTypeNameAndType cptNameAndType)
	{
		this.cptNameAndType = cptNameAndType;
	}
	
	public static ConstantPoolTypeMethodref deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeMethodref cptMethodref = new ConstantPoolTypeMethodref();
		
		cptMethodref.setClassIndex((short)dis.readUnsignedShort());
		cptMethodref.setNameAndTypeIndex((short)dis.readUnsignedShort());
		
		return cptMethodref;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeMethodref elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getClassIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getNameAndTypeIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeMethodref clone(ConstantPoolTypeMethodref src)
	{
		/* create new empty instance */
		ConstantPoolTypeMethodref clone = new ConstantPoolTypeMethodref();
		
		/* fill instance with original data */
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		clone.setCptClass(src.getCptClass());
		clone.setCptNameAndType(src.getCptNameAndType());		
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		ConstantPoolTypeMethodref cptMethodref = null;
		try
		{
			cptMethodref = (ConstantPoolTypeMethodref)obj;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		if(!cptMethodref.cptClass.equals(this.cptClass))
		{
			return false;
		}
		
		return cptMethodref.cptNameAndType.equals(this.cptNameAndType);
	}
}
