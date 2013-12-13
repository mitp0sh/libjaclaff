package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.generic.ElementValue;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;


public class AttributeAnnotationDefault extends AbstractAttribute 
{
	private ElementValue elementValue = null;

	public ElementValue getElementValue() 
	{
		return elementValue;
	}

	public void setElementValue(ElementValue elementValue)
	{
		this.elementValue = elementValue;
	}
	
	public static AttributeAnnotationDefault deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		AttributeAnnotationDefault attribute = new AttributeAnnotationDefault();

		attribute.setElementValue(ElementValue.deserialize(dis, constantPool));
	    
		return attribute;
    }
	
	public static byte[] serialize(AttributeAnnotationDefault attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(ElementValue.serialize(attribute.getElementValue()));
		
		return baos.toByteArray();
	}
}
