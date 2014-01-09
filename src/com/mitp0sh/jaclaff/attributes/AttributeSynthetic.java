package com.mitp0sh.jaclaff.attributes;

import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;

/* complete */
public class AttributeSynthetic extends AbstractAttribute
{
	public static AttributeSynthetic deserialize(DesCtx ctx) throws IOException
    {
		AttributeSynthetic attribute = new AttributeSynthetic();
		
		return attribute;
    }
}
