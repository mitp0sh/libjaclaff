package com.mitp0sh.jaclaff.attributes;

import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;

/* complete */
public class AttributeDeprecated extends AbstractAttribute
{
	public static AttributeDeprecated deserialize(DesCtx ctx) throws IOException
    {
		AttributeDeprecated attribute = new AttributeDeprecated();
		
		return attribute;
    }
}