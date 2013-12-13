package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.util.PNC;


public class EnumConstValue
{
	private short                     typeNameIndex = 0;
	private ConstantPoolTypeUtf8     typeNameObject = null;
	private short                    constNameIndex = 0;
	private ConstantPoolTypeUtf8    constNameObject = null;
	
	public short getTypeNameIndex()
	{
		return typeNameIndex;
	}
	
	public void setTypeNameIndex(short typeNameIndex) 
	{
		this.typeNameIndex = typeNameIndex;
	}
	
	public short getConstNameIndex()
	{
		return constNameIndex;
	}
	
	public void setConstNameIndex(short constNameIndex)
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
	
	public static void decoupleFromIndices(EnumConstValue ecv, ConstantPool constantPool)
	{
		ecv.setTypeNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, ecv.typeNameIndex));
		ecv.setTypeNameIndex((short)0);
		ecv.setConstNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, ecv.constNameIndex));
		ecv.setConstNameIndex((short)0);
	}
	
	public static EnumConstValue deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		EnumConstValue enumConstValue = new EnumConstValue();
		
		enumConstValue.setTypeNameIndex((short)dis.readUnsignedShort());
		enumConstValue.setConstNameIndex((short)dis.readUnsignedShort());
		
		decoupleFromIndices(enumConstValue, constantPool);
		
		return enumConstValue;
	}
	
	public static byte[] serialize(EnumConstValue enumConstValue) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(enumConstValue.getTypeNameIndex(), Short.class));
		baos.write(PNC.toByteArray(enumConstValue.getConstNameIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
