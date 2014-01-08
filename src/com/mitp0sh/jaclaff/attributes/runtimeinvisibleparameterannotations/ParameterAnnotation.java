package com.mitp0sh.jaclaff.attributes.runtimeinvisibleparameterannotations;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.generic.Annotation;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ParameterAnnotation
{	
	private Annotation[] annotations = new Annotation[0];

	public ParameterAnnotation(int numAnnotations)
	{
		annotations = new Annotation[numAnnotations];
	}
	
	public int getNumAnnotations()
	{
		return annotations.length;
	}
	
	public Annotation[] getAnnotations()
	{
		return annotations;
	}

	public void setAnnotations(Annotation[] annotations)
	{
		this.annotations = annotations;
	}
	
	public static ParameterAnnotation deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		ParameterAnnotation parameterAnnotation = new ParameterAnnotation(dis.readUnsignedShort());
		
		for(short i = 0; i < parameterAnnotation.getNumAnnotations(); i++)
		{
			parameterAnnotation.getAnnotations()[i] = Annotation.deserialize(dis, constantPool);
		}
		
		return parameterAnnotation;
    }
	
	public static byte[] serialize(SerCtx ctx, ParameterAnnotation parameterAnnotation) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(parameterAnnotation.getNumAnnotations(), Short.class));
		
		for(short i = 0; i < parameterAnnotation.getNumAnnotations(); i++)
		{
			baos.write(Annotation.serialize(ctx, parameterAnnotation.getAnnotations()[i]));
		}
		
		return baos.toByteArray();
	}
}
