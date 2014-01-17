package com.mitp0sh.jaclaff.attributes.annotation;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class ElementValuePairs 
{
	private ArrayList<ElementValuePairsEntry> elementValuePairs = new ArrayList<ElementValuePairsEntry>();

	public ArrayList<ElementValuePairsEntry> getElementValuePairs() 
	{
		return elementValuePairs;
	}

	public void setElementValuePairs(ArrayList<ElementValuePairsEntry> elementValuePairs)
	{
		this.elementValuePairs = elementValuePairs;
	}

	public int getNumElementValuePairs()
	{
		return elementValuePairs.size();
	}
	
	public static ElementValuePairs deserialize(DesCtx ctx, int numElementValuePairs) throws IOException
	{		
		ElementValuePairs elementValuePairs = new ElementValuePairs();
		
		for(int i = 0; i < numElementValuePairs; i++)
		{
			ElementValuePairsEntry current = ElementValuePairsEntry.deserialize(ctx);
			elementValuePairs.getElementValuePairs().add(current);
		}
		
		return elementValuePairs;
	}
	
	public static byte[] serialize(SerCtx ctx, ElementValuePairs elementValuePairs) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < elementValuePairs.getNumElementValuePairs(); i++)
		{
			baos.write(ElementValuePairsEntry.serialize(ctx, elementValuePairs.getElementValuePairs().get(i)));
		}
		
		return baos.toByteArray();
	}
}
