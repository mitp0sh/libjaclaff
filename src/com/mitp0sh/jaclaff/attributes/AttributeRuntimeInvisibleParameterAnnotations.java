package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.runtimeinvisibleparameterannotations.ParameterAnnotation;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;


public class AttributeRuntimeInvisibleParameterAnnotations extends AbstractAttribute
{	
	private ParameterAnnotation[] parameterAnnotations = new ParameterAnnotation[0];
	
	private AttributeRuntimeInvisibleParameterAnnotations(byte numParameters)
	{
		this.parameterAnnotations = new ParameterAnnotation[numParameters];
	}
	
	public byte getNumParameters()
	{
		return (byte)this.parameterAnnotations.length;
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
		AttributeRuntimeInvisibleParameterAnnotations attribute 
		    = new AttributeRuntimeInvisibleParameterAnnotations(dis.readByte());
		
		for(byte i = 0; i < attribute.getNumParameters(); i++)
		{
			attribute.getParameterAnnotations()[i]
                = ParameterAnnotation.deserialize(dis, constantPool);
		}
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeRuntimeInvisibleParameterAnnotations attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{attribute.getNumParameters()});
		
		for(byte i = 0; i < attribute.getNumParameters(); i++)
		{
			baos.write(ParameterAnnotation.serialize(attribute.getParameterAnnotations()[i]));
		}
		
		return baos.toByteArray();
	}
}