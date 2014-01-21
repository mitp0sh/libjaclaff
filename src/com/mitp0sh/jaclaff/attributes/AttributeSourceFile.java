package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeSourceFile extends AbstractAttribute
{
	private int                     sourceFileIndex = 0;
	private ConstantPoolTypeUtf8   sourceFileObject = null;

	public int getSourceFileIndex()
	{
		return sourceFileIndex;
	}

	public void setSourceFileIndex(int sourceFileIndex)
	{
		this.sourceFileIndex = sourceFileIndex;
	}
	
	public ConstantPoolTypeUtf8 getSourceFileObject() 
	{
		return sourceFileObject;
	}

	public void setSourceFileObject(ConstantPoolTypeUtf8 object) 
	{
		this.sourceFileObject = object;
	
		if(object != null)
		{
			this.setSourceFileIndex(0);
			this.addReference(object);
		}
	}
	
	public static AttributeSourceFile deserialize(DesCtx ctx) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeSourceFile attribute = new AttributeSourceFile();

		attribute.setSourceFileIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(ctx, attribute);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(DesCtx ctx, AttributeSourceFile attribute)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		attribute.setSourceFileObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(constantPool, attribute.sourceFileIndex));
		attribute.setSourceFileIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, AttributeSourceFile attribute)
	{
		int sourceFileIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), attribute.getSourceFileObject());
		attribute.setSourceFileIndex(sourceFileIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, AttributeSourceFile attribute) throws IOException
	{
		coupleToIndices(ctx, attribute);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getSourceFileIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
