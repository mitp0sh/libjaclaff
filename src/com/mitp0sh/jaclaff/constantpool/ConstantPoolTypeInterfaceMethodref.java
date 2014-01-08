package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeInterfaceMethodref extends AbstractConstantPoolType
{
	private int                             classIndex = 0;	
	private int                       nameAndTypeIndex = 0;	
	private ConstantPoolTypeClass             cptClass = null;
	private ConstantPoolTypeNameAndType cptNameAndType = null;
	
	public ConstantPoolTypeInterfaceMethodref()
	{
		super.setConstant_pool_string_representation("CONSTANT_InterfaceMethodref");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF);
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
	
	public static ConstantPoolTypeInterfaceMethodref deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeInterfaceMethodref cptInterfaceMethodref = new ConstantPoolTypeInterfaceMethodref();
		
		cptInterfaceMethodref.setClassIndex(dis.readUnsignedShort());
		cptInterfaceMethodref.setNameAndTypeIndex(dis.readUnsignedShort());
		
		return cptInterfaceMethodref;
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeInterfaceMethodref elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getClassIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getNameAndTypeIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeInterfaceMethodref clone()
	{
		/* create new empty instance */
		ConstantPoolTypeInterfaceMethodref clone = (ConstantPoolTypeInterfaceMethodref)super.clone();
		
		/* fill instance with original data */
		clone.setCptClass(this.getCptClass());
		clone.setCptNameAndType(this.getCptNameAndType());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		ConstantPoolTypeInterfaceMethodref cptInterfaceMethodref = null;
		try
		{
			cptInterfaceMethodref = (ConstantPoolTypeInterfaceMethodref)obj;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		if(!cptInterfaceMethodref.cptClass.equals(this.cptClass))
		{
			return false;
		}
		
		return cptInterfaceMethodref.cptNameAndType.equals(this.cptNameAndType);
	}
}
