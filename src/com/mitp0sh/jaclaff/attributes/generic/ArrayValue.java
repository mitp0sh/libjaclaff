package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.util.PNC;


public class ArrayValue 
{
	private short              numValues = 0;
	private ElementValue[] elementValues = new ElementValue[0];
	
	public ArrayValue(short numValues)
	{
		this.numValues     = numValues;
		this.elementValues = new ElementValue[numValues];
	}
	
	public short getNumValues()
	{
		return numValues;
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
		short numValues = (short)dis.readUnsignedShort();
		ArrayValue arrayValue = new ArrayValue(numValues);
		
		for(int i = 0; i < numValues; i++)
		{
			arrayValue.getElementValues()[i] = ElementValue.deserialize(dis, constantPool);
		}
		
		return arrayValue;
	}
	
	public static byte[] serialize(ArrayValue arrayValue) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(arrayValue.getNumValues(), Short.class));
		
		for(int i = 0; i < arrayValue.getNumValues(); i++)
		{
			baos.write(ElementValue.serialize(arrayValue.getElementValues()[i]));
		}
		
		return baos.toByteArray();
	}
}
