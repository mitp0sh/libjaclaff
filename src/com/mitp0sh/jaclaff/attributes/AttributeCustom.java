package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class AttributeCustom extends AbstractAttribute
{
private byte[] content = new byte[0];
	
	public byte[] getContent()
	{
		return content;
	}

	public void setContent(byte[] content)
	{
		this.content = content;
	}

	public static AttributeCustom deserialize(DataInputStream dis) throws IOException
    {
		AttributeCustom attribute = new AttributeCustom();			
		
		byte[] content = new byte[(int)attribute.getAttributeLength()];		
		dis.read(content, 0, (int)attribute.getAttributeLength());		
		attribute.setContent(content);
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeCustom attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(attribute.getContent());
		
		return baos.toByteArray();
	}
}
