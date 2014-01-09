package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
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

	public static AttributeSourceDebugExtension deserialize(DesCtx ctx) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeSourceDebugExtension attribute = new AttributeSourceDebugExtension();

		byte[] debugExtension = new byte[(int)attribute.getAttributeLength()];
		dis.read(debugExtension, 0, (int)attribute.getAttributeLength());
		attribute.setDebugExtension(debugExtension);
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeSourceDebugExtension attribute) throws IOException
	{
		ByteArrayOutputStream baos = new  ByteArrayOutputStream();
		
		baos.write(attribute.getDebugExtension());
		
		return baos.toByteArray();
	}
}
