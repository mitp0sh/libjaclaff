package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class Attributes
{	
	private AbstractAttribute[] attributes = new AbstractAttribute[0];	

	public Attributes(int attributesCount)
	{
		this.attributes = new AbstractAttribute[attributesCount & 0xFFFF];
	}
	
	public int getAttributesCount()
	{
		return (short)attributes.length;
	}
	
	public AbstractAttribute[] getAttributes() 
	{
		return attributes;
	}
	
	public static Attributes deserialize(DesCtx ctx, int attributesCount, Object reference0) throws IOException
    {
		Attributes attributes = new Attributes(attributesCount);
		
		for(int i = 0; i < attributesCount; i++)
		{
			attributes.getAttributes()[i] = AbstractAttribute.deserialize(ctx, reference0);
		}
		
		return attributes;
    }
	
	public static byte[] serialize(SerCtx ctx, Attributes attributes) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < attributes.getAttributesCount(); i++)
		{
			baos.write(AbstractAttribute.serialize(ctx, attributes.getAttributes()[i]));
		}
		
		return baos.toByteArray();
	}
	
	public static AbstractAttribute getAttributeByName(Attributes attributes, ConstantPool constantPool, String attributeName)
	{
		AbstractAttribute abstractAttribute = null;
		
		/* search code attribute */
		for(int i = 0; i < attributes.getAttributesCount(); i++)
		{
			AbstractAttribute attribute = attributes.getAttributes()[i];
			
			int	     nameIndex = attribute.getAttributeNameIndex();
			String	lAttributeName = constantPool.getConstantTypeUtf8Bytes(nameIndex);
			
			if(attributeName.equals(lAttributeName))
			{
				abstractAttribute = attribute;
				break;
			}	
		}
		
		return abstractAttribute;
	}
}