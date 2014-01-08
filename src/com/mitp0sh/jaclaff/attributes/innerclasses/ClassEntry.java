package com.mitp0sh.jaclaff.attributes.innerclasses;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ClassEntry
{	
	private int                     innerClassInfoIndex = 0;
	private ConstantPoolTypeClass    innerClassInfoObject = null;
	private int                     outerClassInfoIndex = 0;
	private ConstantPoolTypeClass    outerClassInfoObject = null;
	private int                          innerNameIndex = 0;
	private ConstantPoolTypeUtf8          innerNameObject = null;
	private short                   innerClassAccessFlags = 0;
	
	public int getInnerClassInfoIndex() 
	{
		return innerClassInfoIndex;
	}

	public void setInnerClassInfoIndex(int innerClassInfoIndex)
	{
		this.innerClassInfoIndex = innerClassInfoIndex;
	}
	
	public int getOuterClassInfoIndex()
	{
		return outerClassInfoIndex;
	}
	
	public void setOuterClassInfoIndex(int outerClassInfoIndex)
	{
		this.outerClassInfoIndex = outerClassInfoIndex;
	}
	
	public int getInnerNameIndex()
	{
		return innerNameIndex;
	}

	public void setInnerNameIndex(int innerNameIndex)
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
		classEntry.setInnerClassInfoIndex(0);
		classEntry.setOuterClassInfoObject((ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, classEntry.outerClassInfoIndex));
		classEntry.setOuterClassInfoIndex(0);		
		classEntry.setInnerNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, classEntry.innerNameIndex));
		classEntry.setInnerNameIndex(0);
	}

	public static ClassEntry deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		ClassEntry attribute = new ClassEntry();
		
		attribute.setInnerClassInfoIndex(dis.readUnsignedShort());
		attribute.setOuterClassInfoIndex(dis.readUnsignedShort());
		attribute.setInnerNameIndex(dis.readUnsignedShort());
		attribute.setInnerClassAccessFlags((short)dis.readUnsignedShort());
		
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	private static void coupleToIndices(SerCtx ctx, ClassEntry classEntry)
	{
		/* retrieve constant pool */
		ConstantPool cp = ctx.getConstantPool();
		
		/* get objects */
		ConstantPoolTypeClass innerClassInfoObject = classEntry.getInnerClassInfoObject();
		ConstantPoolTypeClass outerClassInfoObject = classEntry.getOuterClassInfoObject();
		ConstantPoolTypeUtf8  innerClassNameObject = classEntry.getInnerNameObject();
		
	    short innerClassInfoIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, innerClassInfoObject);
	    classEntry.setInnerClassInfoIndex(innerClassInfoIndex);
	    
	    short outerClassInfoIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, outerClassInfoObject);
	    classEntry.setOuterClassInfoIndex(outerClassInfoIndex);
	    
	    short innerClassNameIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, innerClassNameObject);
	    classEntry.setInnerNameIndex(innerClassNameIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ClassEntry classEntry) throws IOException
	{
		/* couple indices to constant pool indices */
		coupleToIndices(ctx, classEntry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(classEntry.getInnerClassInfoIndex(), Short.class));
		baos.write(PNC.toByteArray(classEntry.getOuterClassInfoIndex(), Short.class));
		baos.write(PNC.toByteArray(classEntry.getInnerNameIndex(), Short.class));
		baos.write(PNC.toByteArray(classEntry.getInnerClassAccessFlags(), Short.class));
		
		return baos.toByteArray();
	}
}
