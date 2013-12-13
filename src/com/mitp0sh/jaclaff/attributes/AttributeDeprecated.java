package com.mitp0sh.jaclaff.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class AttributeDeprecated extends AbstractAttribute
{
	public static AttributeDeprecated deserialize(DataInputStream dis) throws IOException
    {
		AttributeDeprecated attribute = new AttributeDeprecated();
		
		return attribute;
    }
}