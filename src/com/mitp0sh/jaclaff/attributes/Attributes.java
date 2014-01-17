package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class Attributes
{	
	private ArrayList<AbstractAttribute> attributes = new ArrayList<AbstractAttribute>();

	public Attributes()
	{
		this.attributes = new ArrayList<AbstractAttribute>();
	}
	
	public int getAttributesCount()
	{
		return attributes.size();
	}
	
	public ArrayList<AbstractAttribute> getAttributes() 
	{
		return attributes;
	}
	
	public static Attributes deserialize(DesCtx ctx, int attributesCount, Object reference0) throws IOException
    {
		Attributes attributes = new Attributes();
		
		for(int i = 0; i < attributesCount; i++)
		{
			attributes.getAttributes().add(AbstractAttribute.deserialize(ctx, reference0));
		}
		
		return attributes;
    }
	
	public static byte[] serialize(SerCtx ctx, Attributes attributes, Object reference0) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < attributes.getAttributesCount(); i++)
		{
			baos.write(AbstractAttribute.serialize(ctx, attributes.getAttributes().get(i), reference0));
		}
		
		return baos.toByteArray();
	}
	
	public static AbstractAttribute getAttributeByName(Attributes attributes, ConstantPool constantPool, String attributeName)
	{
		AbstractAttribute abstractAttribute = null;
		
		/* search code attribute */
		for(int i = 0; i < attributes.getAttributesCount(); i++)
		{
			AbstractAttribute attribute = attributes.getAttributes().get(i);
			
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