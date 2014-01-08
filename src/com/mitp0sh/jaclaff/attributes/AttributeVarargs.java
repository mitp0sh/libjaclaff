package com.mitp0sh.jaclaff.attributes;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;

public class AttributeVarargs extends AbstractAttribute
{
	public static AttributeVarargs deserialize(DataInputStream dis, ConstantPool cp) throws IOException
    {
		AttributeVarargs attribute = new AttributeVarargs();
		
		return attribute;
    }
}