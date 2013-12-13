package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeExceptions extends AbstractAttribute
{
	private short                    numberOfExceptions = 0;
	private short[]                          exceptions = null;
	private ConstantPoolTypeClass[]   exceptionsObjects = null;

	public AttributeExceptions(short numberOfExceptions)
	{
		this.numberOfExceptions = numberOfExceptions;
		this.exceptions         = new short[numberOfExceptions];		
	}
	
	public short getNumberOfExceptions()
	{
		return numberOfExceptions;
	}

	public void setNumberOfExceptions(short numberOfExceptions)
	{
		this.numberOfExceptions = numberOfExceptions;
	}

	public short[] getExceptions()
	{
		return exceptions;
	}

	public void setExceptions(short[] exceptions) 
	{
		this.exceptions = exceptions;
	}
	
	public ConstantPoolTypeClass[] getExceptionsObjects() 
	{
		return exceptionsObjects;
	}

	public void setExceptionsObjects(ConstantPoolTypeClass[] exceptionsObjects) 
	{
		this.exceptionsObjects = exceptionsObjects;
	}
	
	public static void decoupleFromIndices(AttributeExceptions attribute, ConstantPool constantPool)
	{
		attribute.setExceptionsObjects(new ConstantPoolTypeClass[attribute.getExceptions().length]);
		
		for(int i = 0; i < attribute.getExceptions().length; i++)
		{
			attribute.getExceptionsObjects()[i] = (ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, attribute.getExceptions()[i]);
			attribute.getExceptions()[i]        = (short)0;
		}
	}

	public static AttributeExceptions deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {		
		AttributeExceptions attribute = new AttributeExceptions((short)(dis.readUnsignedShort()));
		
		for(int i = 0; i < attribute.getNumberOfExceptions(); i++)
		{
			attribute.getExceptions()[i] = (short)dis.readUnsignedShort();
		}
		
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeExceptions attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getNumberOfExceptions(), Short.class));
		
		for(int i = 0; i < attribute.getNumberOfExceptions(); i++)
		{
			baos.write(PNC.toByteArray(attribute.getExceptions()[i], Short.class));
		}
		
		return baos.toByteArray();
	}
}
