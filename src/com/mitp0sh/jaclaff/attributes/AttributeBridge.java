package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class AttributeBridge extends AbstractAttribute
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

	public static AttributeBridge deserialize(DataInputStream dis) throws IOException
    {
		AttributeBridge attribute = new AttributeBridge();			
		
		byte[] content = new byte[attribute.getAttributeLength()];		
		dis.read(content, 0, attribute.getAttributeLength());		
		attribute.setContent(content);
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeBridge attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(attribute.getContent());
		
		return baos.toByteArray();
	}
}
