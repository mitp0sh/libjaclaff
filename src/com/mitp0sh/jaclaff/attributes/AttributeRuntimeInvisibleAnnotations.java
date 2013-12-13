package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.generic.Annotation;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeRuntimeInvisibleAnnotations extends AbstractAttribute
{
	private short        numAnnotations = 0;
	private Annotation[]    annotations = new Annotation[0];
	
	public AttributeRuntimeInvisibleAnnotations(short numAnnotations)
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
	
	public short getNumAnnotations() 
	{
		return numAnnotations;
	}
	
	public static AttributeRuntimeInvisibleAnnotations deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{		
		AttributeRuntimeInvisibleAnnotations attribute = new AttributeRuntimeInvisibleAnnotations((short)dis.readUnsignedShort());
		
		for(int i = 0; i < attribute.getNumAnnotations(); i++)
		{
			attribute.getAnnotations()[i] = Annotation.deserialize(dis, constantPool);
		}
		
		return attribute;
	}
	
	public static byte[] serialize(AttributeRuntimeInvisibleAnnotations attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getNumAnnotations(), Short.class));
		
		for(int i = 0; i < attribute.getNumAnnotations(); i++)
		{
			baos.write(Annotation.serialize(attribute.getAnnotations()[i]));
		}
		
		return baos.toByteArray();
	}
}
