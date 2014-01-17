package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.annotation.Annotation;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeRuntimeVisibleAnnotations extends AbstractAttribute
{
	private Annotation[]  annotations = new Annotation[0];
	
	public AttributeRuntimeVisibleAnnotations(int numAnnotations)
	{
		this.annotations    = new Annotation[numAnnotations];
	}
	
	public Annotation[] getAnnotations() 
	{
		return annotations;
	}
	
	public void setAnnotations(Annotation[] annotations)
	{
		this.annotations = annotations;
	}
	
	public int getNumAnnotations() 
	{
		return annotations.length;
	}
	
	public static AttributeRuntimeVisibleAnnotations deserialize(DesCtx ctx) throws IOException
    {		
		DataInputStream dis = ctx.getDataInputStream();
		
		int num = dis.readUnsignedShort();
		AttributeRuntimeVisibleAnnotations attribute = new AttributeRuntimeVisibleAnnotations(num);
		
		for(int i = 0; i < attribute.getNumAnnotations(); i++)
		{
			attribute.getAnnotations()[i] = Annotation.deserialize(ctx);
		}
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeRuntimeVisibleAnnotations attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getNumAnnotations(), Short.class));
		
		for(int i = 0; i < attribute.getNumAnnotations(); i++)
		{
			baos.write(Annotation.serialize(ctx, attribute.getAnnotations()[i]));
		}
		
		return baos.toByteArray();
	}
}
