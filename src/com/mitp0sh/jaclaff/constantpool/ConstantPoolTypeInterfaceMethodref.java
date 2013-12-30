package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeInterfaceMethodref extends AbstractConstantPoolType
{
	private short                           classIndex = 0;	
	private short                     nameAndTypeIndex = 0;	
	private ConstantPoolTypeClass             cptClass = null;
	private ConstantPoolTypeNameAndType cptNameAndType = null;
	
	public ConstantPoolTypeInterfaceMethodref()
	{
		super.setConstant_pool_string_representation("CONSTANT_InterfaceMethodref");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF);
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
	
	public static ConstantPoolTypeInterfaceMethodref deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeInterfaceMethodref cptInterfaceMethodref = new ConstantPoolTypeInterfaceMethodref();
		
		cptInterfaceMethodref.setClassIndex((short)dis.readUnsignedShort());
		cptInterfaceMethodref.setNameAndTypeIndex((short)dis.readUnsignedShort());
		
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
	
	public static ConstantPoolTypeInterfaceMethodref clone(ConstantPoolTypeInterfaceMethodref src)
	{
		/* create new empty instance */
		ConstantPoolTypeInterfaceMethodref clone = new ConstantPoolTypeInterfaceMethodref();
		
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
