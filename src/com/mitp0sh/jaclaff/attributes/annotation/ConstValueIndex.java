package com.mitp0sh.jaclaff.attributes.annotation;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ConstValueIndex extends AbstractValue
{
	private int                       constValueIndex = 0;
	private AbstractConstantPoolType constValueObject = null;
	
	public int getConstValueIndex() 
	{
		return constValueIndex;
	}
	
	public void setConstValueIndex(int constValueIndex) 
	{
		this.constValueIndex = constValueIndex;
	}
	
	public AbstractConstantPoolType getConstValueObject() 
	{
		return constValueObject;
	}
	
	public void setConstValueObject(AbstractConstantPoolType object)
	{
		this.constValueObject = object;
		
		if(object != null)
		{
			this.setConstValueIndex(0);
			this.addReference(object);
		}
	}
	
	public static ConstValueIndex deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
	
		ConstValueIndex value = new ConstValueIndex();
		
		value.setConstValueIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(ctx, value);
		
		return value;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, ConstValueIndex value)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		AbstractConstantPoolType cpt = ConstantPool.getConstantPoolTypeByIndex(constantPool, value.getConstValueIndex());
    	value.setConstValueObject(cpt);
    	value.setConstValueIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, ConstValueIndex value)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int constValueIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, value.getConstValueObject());
    	value.setConstValueIndex(constValueIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstValueIndex value) throws IOException
	{
		coupleToIndices(ctx, value);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(value.getConstValueIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
