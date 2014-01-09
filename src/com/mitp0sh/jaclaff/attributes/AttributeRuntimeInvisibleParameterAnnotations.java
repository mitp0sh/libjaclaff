package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.runtimeinvisibleparameterannotations.ParameterAnnotation;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class AttributeRuntimeInvisibleParameterAnnotations extends AbstractAttribute
{	
	private ParameterAnnotation[] parameterAnnotations = new ParameterAnnotation[0];
	
	private AttributeRuntimeInvisibleParameterAnnotations(int numParameters)
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
	
	public static AttributeRuntimeInvisibleParameterAnnotations deserialize(DesCtx ctx) throws IOException
    {		
		DataInputStream dis = ctx.getDataInputStream();
		
		int num =  dis.readUnsignedByte();
		AttributeRuntimeInvisibleParameterAnnotations attribute = new AttributeRuntimeInvisibleParameterAnnotations(num);
		
		for(short i = 0; i < num; i++)
		{
			attribute.getParameterAnnotations()[i] = ParameterAnnotation.deserialize(ctx);
		}
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeRuntimeInvisibleParameterAnnotations attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int num = attribute.getNumParameters();
		baos.write(new byte[]{(byte)num});
		
		for(byte i = 0; i < num; i++)
		{
			baos.write(ParameterAnnotation.serialize(ctx, attribute.getParameterAnnotations()[i]));
		}
		
		return baos.toByteArray();
	}
}
