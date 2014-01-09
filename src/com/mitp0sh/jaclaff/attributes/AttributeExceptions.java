package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeExceptions extends AbstractAttribute
{
	private int[]                          exceptions = null;
	private ConstantPoolTypeClass[] exceptionsObjects = new ConstantPoolTypeClass[0];

	public AttributeExceptions(int numberOfExceptions)
	{
		this.exceptions         = new int[numberOfExceptions];		
	}
	
	public int getNumberOfExceptions()
	{
		return exceptionsObjects.length;
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

	public static AttributeExceptions deserialize(DesCtx ctx) throws IOException
    {				
		DataInputStream dis = ctx.getDataInputStream();
		
		int num = dis.readUnsignedShort();
		AttributeExceptions attribute = new AttributeExceptions(num);
		
		for(int i = 0; i < num; i++)
		{
			attribute.getExceptions()[i] = dis.readUnsignedShort();
		}
		
		decoupleFromIndices(ctx, attribute);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(DesCtx ctx, AttributeExceptions attribute)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		int num = attribute.getNumberOfExceptions();
		attribute.setExceptionsObjects(new ConstantPoolTypeClass[num]);
		
		for(int i = 0; i < num; i++)
		{
			attribute.getExceptionsObjects()[i] = (ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, attribute.getExceptions()[i]);
			attribute.getExceptions()[i]        = 0;
		}
	}
	
	public static void coupleToIndices(SerCtx ctx, AttributeExceptions attribute)
	{
		int num = attribute.getNumberOfExceptions();
		for(int i = 0; i < num; i++)
		{
			short currentIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), attribute.getExceptionsObjects()[i]);
			attribute.getExceptions()[i] = currentIndex;
		}
	}
	
	public static byte[] serialize(SerCtx ctx, AttributeExceptions attribute) throws IOException
	{
		coupleToIndices(ctx, attribute);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int num = attribute.getNumberOfExceptions();
		baos.write(PNC.toByteArray(num, Short.class));
		
		for(int i = 0; i < num; i++)
		{
			baos.write(PNC.toByteArray(attribute.getExceptions()[i], Short.class));
		}
		
		return baos.toByteArray();
	}
}
