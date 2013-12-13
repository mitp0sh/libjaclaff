package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;


public class Attributes
{	
	private AbstractAttribute[] attributes = new AbstractAttribute[0];	

	public Attributes(short attributesCount)
	{
		this.attributes = new AbstractAttribute[attributesCount];
	}
	
	public short getAttributesCount()
	{
		return (short)attributes.length;
	}
	
	public AbstractAttribute[] getAttributes() 
	{
		return attributes;
	}
	
	public static Attributes deserialize(DataInputStream dis, short attributesCount, ConstantPool constantPool) throws IOException
    {
		Attributes attributes = new Attributes(attributesCount);
		
		for(int i = 0; i < attributesCount; i++)
		{
			attributes.getAttributes()[i] = AbstractAttribute.deserialize(dis, constantPool);
		}
		
		return attributes;
    }
	
	public static byte[] serialize(Attributes attributes, ConstantPool constantPool) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < attributes.getAttributesCount(); i++)
		{
			baos.write(AbstractAttribute.serialize(attributes.getAttributes()[i], constantPool));
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
			
			short	     nameIndex = attribute.getAttributeNameIndex();
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