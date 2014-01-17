package com.mitp0sh.jaclaff.attributes.runtimeinvisibleparameterannotations;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.annotation.Annotation;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
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
	
	public static ParameterAnnotation deserialize(DesCtx ctx) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		int num = dis.readUnsignedShort();
		ParameterAnnotation parameterAnnotation = new ParameterAnnotation(num);
		
		for(short i = 0; i < num; i++)
		{
			parameterAnnotation.getAnnotations()[i] = Annotation.deserialize(ctx);
		}
		
		return parameterAnnotation;
    }
	
	public static byte[] serialize(SerCtx ctx, ParameterAnnotation parameterAnnotation) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int num = parameterAnnotation.getNumAnnotations();
		baos.write(PNC.toByteArray(num, Short.class));
		
		for(short i = 0; i < num; i++)
		{
			baos.write(Annotation.serialize(ctx, parameterAnnotation.getAnnotations()[i]));
		}
		
		return baos.toByteArray();
	}
}
