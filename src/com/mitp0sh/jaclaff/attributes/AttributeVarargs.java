package com.mitp0sh.jaclaff.attributes;

import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;

/* complete */
public class AttributeVarargs extends AbstractAttribute
{
	public static AttributeVarargs deserialize(DesCtx ctx) throws IOException
    {
		AttributeVarargs attribute = new AttributeVarargs();
		
		return attribute;
    }
}