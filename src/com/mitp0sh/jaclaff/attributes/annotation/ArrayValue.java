package com.mitp0sh.jaclaff.attributes.annotation;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class ArrayValue extends AbstractValue
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
	
	public static ArrayValue deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		int numValues = dis.readUnsignedShort();
		ArrayValue arrayValue = new ArrayValue(numValues);
		
		for(int i = 0; i < numValues; i++)
		{
			arrayValue.getElementValues()[i] = ElementValue.deserialize(ctx);
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
