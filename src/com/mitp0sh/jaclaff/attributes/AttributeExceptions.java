package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeExceptions extends AbstractAttribute
{
	private int                    numberOfExceptions = 0;
	private int[]                          exceptions = null;
	private ConstantPoolTypeClass[]   exceptionsObjects = null;

	public AttributeExceptions(int numberOfExceptions)
	{
		this.numberOfExceptions = numberOfExceptions;
		this.exceptions         = new int[numberOfExceptions];		
	}
	
	public int getNumberOfExceptions()
	{
		return numberOfExceptions;
	}

	public void setNumberOfExceptions(int numberOfExceptions)
	{
		this.numberOfExceptions = numberOfExceptions;
	}

	public int[] getExceptions()
	{
		return exceptions;
	}

	public void setExceptions(int[] exceptions) 
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

	public static AttributeExceptions deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {				
		AttributeExceptions attribute = new AttributeExceptions(dis.readUnsignedShort());
		
		for(int i = 0; i < attribute.getNumberOfExceptions(); i++)
		{
			attribute.getExceptions()[i] = dis.readUnsignedShort();
		}
		
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(AttributeExceptions attribute, ConstantPool constantPool)
	{
		attribute.setExceptionsObjects(new ConstantPoolTypeClass[attribute.getExceptions().length]);
		
		for(int i = 0; i < attribute.getExceptions().length; i++)
		{
			attribute.getExceptionsObjects()[i] = (ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, attribute.getExceptions()[i]);
			attribute.getExceptions()[i]        = 0;
		}
	}
	
	public static void coupleToIndices(SerCtx ctx, AttributeExceptions attribute)
	{
		for(int i = 0; i < attribute.getExceptions().length; i++)
		{
			short currentIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), attribute.getExceptionsObjects()[i]);
			attribute.getExceptions()[i] = currentIndex;
		}
	}
	
	public static byte[] serialize(SerCtx ctx, AttributeExceptions attribute) throws IOException
	{
		coupleToIndices(ctx, attribute);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getNumberOfExceptions(), Short.class));
		
		for(int i = 0; i < attribute.getNumberOfExceptions(); i++)
		{
			baos.write(PNC.toByteArray(attribute.getExceptions()[i], Short.class));
		}
		
		return baos.toByteArray();
	}
}
