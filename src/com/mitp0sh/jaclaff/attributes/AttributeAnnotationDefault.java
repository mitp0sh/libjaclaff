package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.generic.ElementValue;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
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
	
	public static AttributeAnnotationDefault deserialize(DesCtx ctx) throws IOException
    {		
		AttributeAnnotationDefault attribute = new AttributeAnnotationDefault();

		attribute.setElementValue(ElementValue.deserialize(ctx));
	    
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeAnnotationDefault attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(ElementValue.serialize(ctx, attribute.getElementValue()));
		
		return baos.toByteArray();
	}
}
