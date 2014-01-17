package com.mitp0sh.jaclaff.attributes.annotation;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ClassInfoIndex extends AbstractValue
{
	private int                   classInfoIndex = 0;
	private ConstantPoolTypeUtf8 classInfoObject = null;
	
	public int getClassInfoIndex() 
	{
		return classInfoIndex;
	}

	public void setClassInfoIndex(int classInfoIndex) 
	{
		this.classInfoIndex = classInfoIndex;
	}

	public ConstantPoolTypeUtf8 getClassInfoObject() 
	{
		return classInfoObject;
	}

	public void setClassInfoObject(ConstantPoolTypeUtf8 object)
	{
		this.classInfoObject = object;
	
		if(object != null)
		{
			this.setClassInfoIndex(0);
			this.addReference(object);
		}
	}
	
	public static ClassInfoIndex deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
	
		ClassInfoIndex value = new ClassInfoIndex();
		
		value.setClassInfoIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(ctx, value);
		
		return value;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, ClassInfoIndex value)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		AbstractConstantPoolType acpt = ConstantPool.getConstantPoolTypeByIndex(constantPool, value.getClassInfoIndex());
		ConstantPoolTypeUtf8 cpt = (ConstantPoolTypeUtf8)acpt;
		value.setClassInfoObject(cpt);
    	value.setClassInfoIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, ClassInfoIndex value)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int classInfoIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, value.getClassInfoObject());
    	value.setClassInfoIndex(classInfoIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ClassInfoIndex value) throws IOException
	{
		coupleToIndices(ctx, value);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(value.getClassInfoIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
