package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.runtimeinvisibleparameterannotations.ParameterAnnotation;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class AttributeRuntimeInvisibleParameterAnnotations extends AbstractAttribute
{	
	private ParameterAnnotation[] parameterAnnotations = new ParameterAnnotation[0];
	
	private AttributeRuntimeInvisibleParameterAnnotations(short numParameters)
	{
		this.parameterAnnotations = new ParameterAnnotation[numParameters];
	}
	
	public int getNumParameters()
	{
		return this.parameterAnnotations.length;
	}
	
	public ParameterAnnotation[] getParameterAnnotations()
	{
		return parameterAnnotations;
	}
	
	public void setParameterAnnotations(ParameterAnnotation[] parameterAnnotations)
	{
		this.parameterAnnotations = parameterAnnotations;
	}
	
	public static AttributeRuntimeInvisibleParameterAnnotations deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {		
		AttributeRuntimeInvisibleParameterAnnotations attribute = new AttributeRuntimeInvisibleParameterAnnotations(dis.readByte());
		
		for(short i = 0; i < attribute.getNumParameters(); i++)
		{
			attribute.getParameterAnnotations()[i] = ParameterAnnotation.deserialize(dis, constantPool);
		}
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeRuntimeInvisibleParameterAnnotations attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)attribute.getNumParameters()});
		
		for(byte i = 0; i < attribute.getNumParameters(); i++)
		{
			baos.write(ParameterAnnotation.serialize(ctx, attribute.getParameterAnnotations()[i]));
		}
		
		return baos.toByteArray();
	}
}
