package com.mitp0sh.jaclaff.attributes.annotation;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class Annotation extends AbstractValue
{
	private int                       typeIndex = 0;
	private ConstantPoolTypeUtf8     typeObject = null;
	private ElementValuePairs elementValuePairs = null;
	
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

	public void setTypeObject(ConstantPoolTypeUtf8 object) 
	{
		this.typeObject = object;
		
		if(object != null)
		{
			this.setTypeIndex(0);
			this.addReference(object);
		}
	}

	public static Annotation deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		Annotation annotation = new Annotation();
		
		annotation.setTypeIndex(dis.readUnsignedShort());
		int numElementValuePairs = dis.readUnsignedShort();
		annotation.setElementValuePairs(ElementValuePairs.deserialize(ctx, numElementValuePairs));
		
		decoupleFromIndices(ctx, annotation);
		
		return annotation;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, Annotation annotation)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		annotation.setTypeObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(constantPool, annotation.typeIndex));
		annotation.setTypeIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, Annotation annotation)
	{
		ConstantPool cp = ctx.getConstantPool();
		int typeIndex = ConstantPool.indexByCPE(cp, annotation.getTypeObject());
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
