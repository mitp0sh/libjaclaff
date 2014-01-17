package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.exceptions.Exceptions;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeExceptions extends AbstractAttribute
{
	private Exceptions exceptions = null;
	
	public int getNumberOfExceptions() 
	{
		if(exceptions != null)
		{
			return exceptions.getNumExceptions();
		}
		return 0;
	}

	public Exceptions getExceptions() 
	{
		return exceptions;
	}

	public void setExceptions(Exceptions exceptions) 
	{
		this.exceptions = exceptions;
	}
	
	public static AttributeExceptions deserialize(DesCtx ctx) throws IOException
	{				
		DataInputStream dis = ctx.getDataInputStream();
		
		int num = dis.readUnsignedShort();
		AttributeExceptions attribute = new AttributeExceptions();
		attribute.setExceptions(Exceptions.deserialize(ctx, num));
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeExceptions attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		int num = attribute.getNumberOfExceptions();
		
		baos.write(PNC.toByteArray(num, Short.class));
		baos.write(Exceptions.serialize(ctx, attribute.getExceptions()));
		
		return baos.toByteArray();
	}
}
