package com.mitp0sh.jaclaff.attributes.innerclasses;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.util.PNC;


public class ClassEntry
{
	private short                     innerClassInfoIndex    = 0;
	private ConstantPoolTypeClass       innerClassInfoObject = null;
	private short                        outerClassInfoIndex = 0;
	private ConstantPoolTypeClass       outerClassInfoObject = null;
	private short                             innerNameIndex = 0;
	private ConstantPoolTypeUtf8             innerNameObject = null;
	private short                      innerClassAccessFlags = 0;
	
	public short getInnerClassInfoIndex() 
	{
		return innerClassInfoIndex;
	}

	public void setInnerClassInfoIndex(short innerClassInfoIndex)
	{
		this.innerClassInfoIndex = innerClassInfoIndex;
	}
	
	public short getOuterClassInfoIndex()
	{
		return outerClassInfoIndex;
	}
	
	public void setOuterClassInfoIndex(short outerClassInfoIndex)
	{
		this.outerClassInfoIndex = outerClassInfoIndex;
	}
	
	public short getInnerNameIndex()
	{
		return innerNameIndex;
	}

	public void setInnerNameIndex(short innerNameIndex)
	{
		this.innerNameIndex = innerNameIndex;
	}
	
	public short getInnerClassAccessFlags()
	{
		return innerClassAccessFlags;
	}
	
	public void setInnerClassAccessFlags(short innerClassAccessFlags) 
	{
		this.innerClassAccessFlags = innerClassAccessFlags;
	}
	
	public ConstantPoolTypeClass getInnerClassInfoObject() 
	{
		return innerClassInfoObject;
	}

	public void setInnerClassInfoObject(ConstantPoolTypeClass innerClassInfoObject)
	{
		this.innerClassInfoObject = innerClassInfoObject;
	}

	public ConstantPoolTypeClass getOuterClassInfoObject() 
	{
		return outerClassInfoObject;
	}

	public void setOuterClassInfoObject(ConstantPoolTypeClass outerClassInfoObject) 
	{
		this.outerClassInfoObject = outerClassInfoObject;
	}

	public ConstantPoolTypeUtf8 getInnerNameObject() 
	{
		return innerNameObject;
	}

	public void setInnerNameObject(ConstantPoolTypeUtf8 innerNameObject) 
	{
		this.innerNameObject = innerNameObject;
	}
	
	public static void decoupleFromIndices(ClassEntry classEntry, ConstantPool constantPool)
	{
		classEntry.setInnerClassInfoObject((ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, classEntry.innerClassInfoIndex));
		classEntry.setInnerClassInfoIndex((short)0);
		classEntry.setOuterClassInfoObject((ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, classEntry.outerClassInfoIndex));
		classEntry.setOuterClassInfoIndex((short)0);		
		classEntry.setInnerNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, classEntry.innerNameIndex));
		classEntry.setInnerNameIndex((short)0);
	}

	public static ClassEntry deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		ClassEntry attribute = new ClassEntry();
		
		attribute.setInnerClassInfoIndex((short)dis.readUnsignedShort());
		attribute.setOuterClassInfoIndex((short)dis.readUnsignedShort());
		attribute.setInnerNameIndex((short)dis.readUnsignedShort());
		attribute.setInnerClassAccessFlags((short)dis.readUnsignedShort());
		
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static byte[] serialize(ClassEntry classEntry) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(classEntry.getInnerClassInfoIndex(), Short.class));
		baos.write(PNC.toByteArray(classEntry.getOuterClassInfoIndex(), Short.class));
		baos.write(PNC.toByteArray(classEntry.getInnerNameIndex(), Short.class));
		baos.write(PNC.toByteArray(classEntry.getInnerClassAccessFlags(), Short.class));
		
		return baos.toByteArray();
	}
}
