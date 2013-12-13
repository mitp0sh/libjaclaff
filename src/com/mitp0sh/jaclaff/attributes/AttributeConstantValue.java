package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeConstantValue extends AbstractAttribute
{	 
	private int                             attributeLength = 0;
	private short                        constantValueIndex = 0;	
	private AbstractConstantPoolType    constantValueObject = null;	

	public String getAttributeName()
	{
		return AbstractAttribute.attributeConstantValue;
	}

	public short getConstantValueIndex() 
	{
		return constantValueIndex;
	}

	public void setConstantValueIndex(short constantValueIndex) 
	{
		this.constantValueIndex = constantValueIndex;
	}

	public int getAttributeLength() 
	{
		return this.attributeLength;
	}
	
	public void setAttributeLength(int attributeLength)
	{
		this.attributeLength = attributeLength;
	}
	
	public AbstractConstantPoolType getConstantValueObject() 
	{
		return constantValueObject;
	}

	public void setConstantValueObject(AbstractConstantPoolType constantValueObject) 
	{
		this.constantValueObject = constantValueObject;
	}
	
	public static void decoupleFromIndices(AttributeConstantValue attribute, ConstantPool constantPool)
	{
		attribute.setConstantValueObject(ConstantPool.getConstantPoolTypeByIndex(constantPool, attribute.constantValueIndex));
		attribute.setConstantValueIndex((short)0);
	}
	
	public static AttributeConstantValue deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
	    AttributeConstantValue attribute = new AttributeConstantValue();
	    
	    attribute.setConstantValueIndex((short)(dis.readUnsignedShort()));	
	    
	    decoupleFromIndices(attribute, constantPool);
		
	    return attribute;
    }
	
	public static byte[] serialize(AttributeConstantValue attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getConstantValueIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
