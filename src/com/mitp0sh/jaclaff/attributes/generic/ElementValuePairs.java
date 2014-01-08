package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class ElementValuePairs 
{
	private ElementValuePairsEntry[]     elementValuePairs = new ElementValuePairsEntry[0];
	
	private ElementValuePairs(int numElementValuePairs)
	{
		this.elementValuePairs = new ElementValuePairsEntry[numElementValuePairs];
	}

	public ElementValuePairsEntry[] getElementValuePairs() 
	{
		return elementValuePairs;
	}

	public void setElementValuePairs(ElementValuePairsEntry[] elementValuePairs)
	{
		this.elementValuePairs = elementValuePairs;
	}

	public int getNumElementValuePairs()
	{
		return elementValuePairs.length;
	}
	
	public static ElementValuePairs deserialize(DataInputStream dis, int numElementValuePairs, ConstantPool constantPool) throws IOException
	{
		ElementValuePairs elementValuePairs = new ElementValuePairs(numElementValuePairs);
		
		for(int i = 0; i < numElementValuePairs; i++)
		{
			elementValuePairs.getElementValuePairs()[i] = ElementValuePairsEntry.deserialize(dis, constantPool);	
		}
		
		return elementValuePairs;
	}
	
	public static byte[] serialize(SerCtx ctx, ElementValuePairs elementValuePairs) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < elementValuePairs.getNumElementValuePairs(); i++)
		{
			baos.write(ElementValuePairsEntry.serialize(ctx, elementValuePairs.getElementValuePairs()[i]));
		}
		
		return baos.toByteArray();
	}
}
