package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeMethodref extends AbstractConstantPoolType
{
	private int                           classIndex = 0;	
	private int                     nameAndTypeIndex = 0;	
	private ConstantPoolTypeClass             cptClass = null;
	private ConstantPoolTypeNameAndType cptNameAndType = null;
	
	public ConstantPoolTypeMethodref()
	{
		super.setConstant_pool_string_representation("CONSTANT_Methodref");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF);
	}
	
	public int getClassIndex()
	{
		return this.classIndex;
	}
	
	public void setClassIndex(int classIndex) 
	{
		this.classIndex = classIndex;
	}
	
	public int getNameAndTypeIndex() 
	{
		return this.nameAndTypeIndex;
	}
	
	public void setNameAndTypeIndex(int nameAndTypeIndex)
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
		
		cptMethodref.setClassIndex(dis.readUnsignedShort());
		cptMethodref.setNameAndTypeIndex(dis.readUnsignedShort());
		
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
	
	public ConstantPoolTypeMethodref clone()
	{
		/* create new empty instance */
		ConstantPoolTypeMethodref clone = (ConstantPoolTypeMethodref)super.clone();
		
		/* fill instance with original data */
		clone.setCptClass(this.getCptClass());
		clone.setCptNameAndType(this.getCptNameAndType());	
		
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
