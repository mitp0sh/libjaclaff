package com.mitp0sh.jaclaff.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class AttributeSynthetic extends AbstractAttribute
{
	public static AttributeSynthetic deserialize(DataInputStream dis) throws IOException
    {
		AttributeSynthetic attribute = new AttributeSynthetic();
		
		return attribute;
    }
}
