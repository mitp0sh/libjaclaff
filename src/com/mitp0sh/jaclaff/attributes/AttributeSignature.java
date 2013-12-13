package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeSignature extends AbstractAttribute
{
	private short                    signatureIndex = 0;
	private ConstantPoolTypeUtf8    signatureObject = null;
	
	public short getSignatureIndex()
	{
		return signatureIndex;
	}

	public void setSignatureIndex(short signatureIndex)
	{
		this.signatureIndex = signatureIndex;
	}
	
	public ConstantPoolTypeUtf8 getSignatureObject() 
	{
		return signatureObject;
	}

	public void setSignatureObject(ConstantPoolTypeUtf8 signatureObject) 
	{
		this.signatureObject = signatureObject;
	}
	
	public static void decoupleFromIndices(AttributeSignature attribute, ConstantPool constantPool)
	{
		attribute.setSignatureObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, attribute.signatureIndex));
		attribute.setSignatureIndex((short)0);
	}
	
	public static AttributeSignature deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {		
		AttributeSignature attribute = new AttributeSignature();
		
		attribute.setSignatureIndex((short)dis.readUnsignedShort());
		
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeSignature attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getSignatureIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
