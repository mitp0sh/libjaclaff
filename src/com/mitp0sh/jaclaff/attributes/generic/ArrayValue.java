package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ArrayValue 
{
	private ElementValue[] elementValues = new ElementValue[0];
	
	public ArrayValue(int numValues)
	{
		this.elementValues = new ElementValue[numValues];
	}
	
	public int getNumValues()
	{
		return elementValues.length;
	}

	public ElementValue[] getElementValues() 
	{
		return elementValues;
	}
	
	public void setElementValues(ElementValue[] elementValues)
	{
		this.elementValues = elementValues;
	}
	
	public static ArrayValue deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		int numValues = dis.readUnsignedShort();
		ArrayValue arrayValue = new ArrayValue(numValues);
		
		for(int i = 0; i < numValues; i++)
		{
			arrayValue.getElementValues()[i] = ElementValue.deserialize(dis, constantPool);
		}
		
		return arrayValue;
	}
	
	public static byte[] serialize(SerCtx ctx, ArrayValue arrayValue) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(arrayValue.getNumValues(), Short.class));
		
		for(int i = 0; i < arrayValue.getNumValues(); i++)
		{
			baos.write(ElementValue.serialize(ctx, arrayValue.getElementValues()[i]));
		}
		
		return baos.toByteArray();
	}
}
