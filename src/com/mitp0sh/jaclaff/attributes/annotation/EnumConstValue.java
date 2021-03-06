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
public class EnumConstValue extends AbstractValue
{
	private int                    typeNameIndex = 0;
	private int                   constNameIndex = 0;
	
	private ConstantPoolTypeUtf8  typeNameObject = null;
	private ConstantPoolTypeUtf8 constNameObject = null;
	
	public int getTypeNameIndex()
	{
		return typeNameIndex;
	}
	
	public void setTypeNameIndex(int typeNameIndex) 
	{
		this.typeNameIndex = typeNameIndex;
	}
	
	public int getConstNameIndex()
	{
		return constNameIndex;
	}
	
	public void setConstNameIndex(int constNameIndex)
	{
		this.constNameIndex = constNameIndex;
	}
	
	public ConstantPoolTypeUtf8 getTypeNameObject() 
	{
		return typeNameObject;
	}

	public void setTypeNameObject(ConstantPoolTypeUtf8 object) 
	{
		this.typeNameObject = object;
		
		if(object != null)
		{
			this.setTypeNameIndex(0);
			this.addReference(object);
		}
	}

	public ConstantPoolTypeUtf8 getConstNameObject()
	{
		return constNameObject;
	}

	public void setConstNameObject(ConstantPoolTypeUtf8 object)
	{
		this.constNameObject = object;
		
		if(object != null)
		{
			this.setConstNameIndex(0);
			this.addReference(object);
		}
	}
	
	public static EnumConstValue deserialize(DesCtx ctx) throws IOException
	{		
		DataInputStream dis = ctx.getDataInputStream();
		
		EnumConstValue enumConstValue = new EnumConstValue();
		
		enumConstValue.setTypeNameIndex(dis.readUnsignedShort());
		enumConstValue.setConstNameIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(ctx, enumConstValue);
		
		return enumConstValue;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, EnumConstValue enumConstValue)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		enumConstValue.setTypeNameObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(constantPool, enumConstValue.typeNameIndex));
		enumConstValue.setTypeNameIndex(0);
		
		enumConstValue.setConstNameObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(constantPool, enumConstValue.constNameIndex));
		enumConstValue.setConstNameIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, EnumConstValue enumConstValue)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int typeNameIndex = ConstantPool.indexByCPE(cp, enumConstValue.getTypeNameObject());
		enumConstValue.setTypeNameIndex(typeNameIndex);
		
		int constNameIndex = ConstantPool.indexByCPE(cp, enumConstValue.getConstNameObject());
		enumConstValue.setConstNameIndex(constNameIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, EnumConstValue enumConstValue) throws IOException
	{
		coupleToIndices(ctx, enumConstValue);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(enumConstValue.getTypeNameIndex(),  Short.class));
		baos.write(PNC.toByteArray(enumConstValue.getConstNameIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
