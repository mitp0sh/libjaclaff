package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class EnumConstValue
{
	private int                    typeNameIndex = 0;
	private ConstantPoolTypeUtf8  typeNameObject = null;
	private int                   constNameIndex = 0;
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

	public void setTypeNameObject(ConstantPoolTypeUtf8 typeNameObject) 
	{
		this.typeNameObject = typeNameObject;
	}

	public ConstantPoolTypeUtf8 getConstNameObject()
	{
		return constNameObject;
	}

	public void setConstNameObject(ConstantPoolTypeUtf8 constNameObject)
	{
		this.constNameObject = constNameObject;
	}
	
	public static EnumConstValue deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{		
		EnumConstValue enumConstValue = new EnumConstValue();
		
		enumConstValue.setTypeNameIndex(dis.readUnsignedShort());
		enumConstValue.setConstNameIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(enumConstValue, constantPool);
		
		return enumConstValue;
	}
	
	public static void decoupleFromIndices(EnumConstValue enumConstValue, ConstantPool constantPool)
	{
		enumConstValue.setTypeNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, enumConstValue.typeNameIndex));
		enumConstValue.setTypeNameIndex(0);
		enumConstValue.setConstNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, enumConstValue.constNameIndex));
		enumConstValue.setConstNameIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, EnumConstValue enumConstValue)
	{
		ConstantPool cp = ctx.getConstantPool();
		short typeNameIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, enumConstValue.getTypeNameObject());
		enumConstValue.setTypeNameIndex(typeNameIndex);
		short constNameIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, enumConstValue.getConstNameObject());
		enumConstValue.setConstNameIndex(constNameIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, EnumConstValue enumConstValue) throws IOException
	{
		coupleToIndices(ctx, enumConstValue);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(enumConstValue.getTypeNameIndex(), Short.class));
		baos.write(PNC.toByteArray(enumConstValue.getConstNameIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
