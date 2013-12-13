package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.util.PNC;


public class Annotation 
{
	private short                              typeIndex = 0;
	private ConstantPoolTypeUtf8              typeObject = null;
	private short               	numElementValuePairs = 0;
	private ElementValuePairs      	   elementValuePairs = null;
	
	public short getTypeIndex() 
	{
		return typeIndex;
	}
	
	public void setTypeIndex(short typeIndex)
	{
		this.typeIndex = typeIndex;
	}
	
	public short getNumElementValuePairs()
	{
		return numElementValuePairs;
	}
	
	public void setNumElementValuePairs(short numElementValuePairs)
	{
		this.numElementValuePairs = numElementValuePairs;
	}
	
	public ElementValuePairs getElementValuePairs()
	{
		return elementValuePairs;
	}
	
	public void setElementValuePairs(ElementValuePairs elementValuePairs)
	{
		this.elementValuePairs = elementValuePairs;
	}
	
	public ConstantPoolTypeUtf8 getTypeObject() 
	{
		return typeObject;
	}

	public void setTypeObject(ConstantPoolTypeUtf8 typeObject) 
	{
		this.typeObject = typeObject;
	}
	
	public static void decoupleFromIndices(Annotation annotation, ConstantPool constantPool)
	{
		annotation.setTypeObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, annotation.typeIndex));
		annotation.setTypeIndex((short)0);
	}

	public static Annotation deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		Annotation annotation = new Annotation();
		
		annotation.setTypeIndex((short)dis.readUnsignedShort());
		annotation.setNumElementValuePairs((short)dis.readUnsignedShort());
		annotation.setElementValuePairs(ElementValuePairs.deserialize(dis, annotation.getNumElementValuePairs(), constantPool));
		
		decoupleFromIndices(annotation, constantPool);
		
		return annotation;
	}
	
	public static byte[] serialize(Annotation annotation) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(annotation.getTypeIndex(), Short.class));
		baos.write(PNC.toByteArray(annotation.getNumElementValuePairs(), Short.class));
		baos.write(ElementValuePairs.serialize(annotation.getElementValuePairs()));
		
		return baos.toByteArray();
	}
}
