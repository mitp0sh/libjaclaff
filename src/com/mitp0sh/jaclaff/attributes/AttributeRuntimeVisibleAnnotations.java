package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.generic.Annotation;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeRuntimeVisibleAnnotations extends AbstractAttribute
{
	private int        numAnnotations = 0;
	private Annotation[]  annotations = new Annotation[0];
	
	public AttributeRuntimeVisibleAnnotations(int numAnnotations)
	{
		this.numAnnotations = numAnnotations;
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
		return numAnnotations;
	}
	
	public static AttributeRuntimeVisibleAnnotations deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {		
		AttributeRuntimeVisibleAnnotations attribute = new AttributeRuntimeVisibleAnnotations(dis.readUnsignedShort());
		
		for(int i = 0; i < attribute.getNumAnnotations(); i++)
		{
			attribute.getAnnotations()[i] = Annotation.deserialize(dis, constantPool);
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
