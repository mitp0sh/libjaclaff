package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeFieldref extends AbstractConstantPoolType
{
	private short                           classIndex = 0;	
	private short                     nameAndTypeIndex = 0;	
	private ConstantPoolTypeClass             cptClass = null;
	private ConstantPoolTypeNameAndType cptNameAndType = null;

	public ConstantPoolTypeFieldref()
	{
		super.setConstant_pool_string_representation("CONSTANT_Fieldref");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF);
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
	
	public static ConstantPoolTypeFieldref deserialize(DataInputStream dis) throws IOException
	{
		ConstantPoolTypeFieldref cptFieldref = new ConstantPoolTypeFieldref();
		
		cptFieldref.setClassIndex((short)dis.readUnsignedShort());
		cptFieldref.setNameAndTypeIndex((short)dis.readUnsignedShort());
		
		return cptFieldref;
	}
	public static byte[] serialize(ConstantPoolTypeFieldref elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getClassIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getNameAndTypeIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public static ConstantPoolTypeFieldref clone(ConstantPoolTypeFieldref src)
	{
		/* create new empty instance */
		ConstantPoolTypeFieldref clone = new ConstantPoolTypeFieldref();
		
		/* fill instance with original data */
		clone.setConstant_pool_string_representation(src.getConstant_pool_string_representation());
		clone.setConstant_pool_tag(src.getConstant_pool_tag());
		clone.setCptClass(src.getCptClass());
		clone.setCptNameAndType(src.getCptNameAndType());		
		
		return clone;
	}
}
