package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class Annotation 
{
	private int                            typeIndex = 0;
	private ConstantPoolTypeUtf8          typeObject = null;
	private ElementValuePairs      elementValuePairs = null;
	
	public int getTypeIndex() 
	{
		return typeIndex;
	}
	
	public void setTypeIndex(int typeIndex)
	{
		this.typeIndex = typeIndex;
	}
	
	public int getNumElementValuePairs()
	{
		return elementValuePairs.getNumElementValuePairs();
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

	public static Annotation deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		Annotation annotation = new Annotation();
		
		annotation.setTypeIndex(dis.readUnsignedShort());
		int numElementValuePairs = dis.readUnsignedShort();
		annotation.setElementValuePairs(ElementValuePairs.deserialize(dis, numElementValuePairs, constantPool));
		
		decoupleFromIndices(annotation, constantPool);
		
		return annotation;
	}
	
	public static void decoupleFromIndices(Annotation annotation, ConstantPool constantPool)
	{
		annotation.setTypeObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, annotation.typeIndex));
		annotation.setTypeIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, Annotation annotation)
	{
		ConstantPool cp = ctx.getConstantPool();
		short typeIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, annotation.getTypeObject());
		annotation.setTypeIndex(typeIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, Annotation annotation) throws IOException
	{
		coupleToIndices(ctx, annotation);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(annotation.getTypeIndex(), Short.class));
		baos.write(PNC.toByteArray(annotation.getNumElementValuePairs(), Short.class));
		baos.write(ElementValuePairs.serialize(ctx, annotation.getElementValuePairs()));
		
		return baos.toByteArray();
	}
}
