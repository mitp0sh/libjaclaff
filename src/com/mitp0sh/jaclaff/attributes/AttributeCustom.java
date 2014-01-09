package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
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

	public static AttributeCustom deserialize(DesCtx ctx) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeCustom attribute = new AttributeCustom();			
		
		byte[] content = new byte[(int)attribute.getAttributeLength()];		
		dis.read(content, 0, (int)attribute.getAttributeLength());		
		attribute.setContent(content);
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeCustom attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(attribute.getContent());
		
		return baos.toByteArray();
	}
}
