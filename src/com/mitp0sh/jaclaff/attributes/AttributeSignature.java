package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeSignature extends AbstractAttribute
{
	private int                    signatureIndex = 0;
	private ConstantPoolTypeUtf8  signatureObject = null;
	
	public int getSignatureIndex()
	{
		return signatureIndex;
	}

	public void setSignatureIndex(int signatureIndex)
	{
		this.signatureIndex = signatureIndex;
	}
	
	public ConstantPoolTypeUtf8 getSignatureObject() 
	{
		return signatureObject;
	}

	public void setSignatureObject(ConstantPoolTypeUtf8 object) 
	{
		this.signatureObject = object;
		
		if(object != null)
		{
			this.setSignatureIndex(0);
			this.addReference(object);
		}
	}
	
	public static AttributeSignature deserialize(DesCtx ctx) throws IOException
    {		
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeSignature attribute = new AttributeSignature();
		
		attribute.setSignatureIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(ctx, attribute);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(DesCtx ctx, AttributeSignature attribute)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int signatureIndex = attribute.getSignatureIndex();
		AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, signatureIndex);
		ConstantPoolTypeUtf8 cpt = (ConstantPoolTypeUtf8)acpt;
		
		attribute.setSignatureObject(cpt);
	}
	
	public static void coupleToIndices(SerCtx ctx, AttributeSignature attribute)
	{
		int signatureIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), attribute.getSignatureObject());
		attribute.setSignatureIndex(signatureIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, AttributeSignature attribute) throws IOException
	{
		coupleToIndices(ctx, attribute);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getSignatureIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
