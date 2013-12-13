package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class AttributeSourceDebugExtension extends AbstractAttribute
{
	private byte[] debugExtension = new byte[0];	
	
	public byte[] getDebugExtension()
	{
		return debugExtension;
	}

	public void setDebugExtension(byte[] debugExtension) 
	{
		this.debugExtension = debugExtension;
	}

	public static AttributeSourceDebugExtension deserialize(DataInputStream dis) throws IOException
    {
		AttributeSourceDebugExtension attribute = new AttributeSourceDebugExtension();

		byte[] debugExtension = new byte[attribute.getAttributeLength()];
		dis.read(debugExtension, 0, attribute.getAttributeLength());
		attribute.setDebugExtension(debugExtension);
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeSourceDebugExtension attribute) throws IOException
	{
		ByteArrayOutputStream baos = new  ByteArrayOutputStream();
		
		baos.write(attribute.getDebugExtension());
		
		return baos.toByteArray();
	}
}
