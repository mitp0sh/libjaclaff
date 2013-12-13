package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeSourceFile extends AbstractAttribute
{
	private short                     sourceFileIndex = 0;
	private ConstantPoolTypeUtf8     sourceFileObject = null;

	public short getSourceFileIndex()
	{
		return sourceFileIndex;
	}

	public void setSourceFileIndex(short sourceFileIndex)
	{
		this.sourceFileIndex = sourceFileIndex;
	}
	
	public ConstantPoolTypeUtf8 getSourceFileObject() 
	{
		return sourceFileObject;
	}

	public void setSourceFileObject(ConstantPoolTypeUtf8 sourceFileObject) 
	{
		this.sourceFileObject = sourceFileObject;
	}

	public static void decoupleFromIndices(AttributeSourceFile attribute, ConstantPool constantPool)
	{
		attribute.setSourceFileObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, attribute.sourceFileIndex));
		attribute.setSourceFileIndex((short)0);
	}
	
	public static AttributeSourceFile deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		AttributeSourceFile attribute = new AttributeSourceFile();

		attribute.setSourceFileIndex((short)dis.readUnsignedShort());
		
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeSourceFile attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getSourceFileIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
