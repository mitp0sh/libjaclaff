package com.mitp0sh.jaclaff.attributes.innerclasses;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class ClassEntry extends AbstractReference
{	
	private int                    innerClassInfoIndex = 0;
	private int                    outerClassInfoIndex = 0;
	private int                         innerNameIndex = 0;
	private short                innerClassAccessFlags = 0;

	private ConstantPoolTypeClass innerClassInfoObject = null;
	private ConstantPoolTypeClass outerClassInfoObject = null;
	private ConstantPoolTypeUtf8       innerNameObject = null;
	
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

	public void setInnerClassInfoObject(ConstantPoolTypeClass object)
	{
		this.innerClassInfoObject = object;
		
		if(object != null)
		{
			this.setInnerClassInfoIndex(0);
			this.addReference(object);
		}
	}

	public ConstantPoolTypeClass getOuterClassInfoObject() 
	{
		return outerClassInfoObject;
	}

	public void setOuterClassInfoObject(ConstantPoolTypeClass object) 
	{
		this.outerClassInfoObject = object;
		
		if(object != null)
		{
			this.setOuterClassInfoIndex(0);
			this.addReference(object);
		}
	}

	public ConstantPoolTypeUtf8 getInnerNameObject() 
	{
		return innerNameObject;
	}

	public void setInnerNameObject(ConstantPoolTypeUtf8 object) 
	{
		this.innerNameObject = object;
		
		if(object != null)
		{
			this.setInnerNameIndex(0);
			this.addReference(object);
		}
	}

	public static ClassEntry deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		ClassEntry attribute = new ClassEntry();
		
		attribute.setInnerClassInfoIndex(dis.readUnsignedShort());
		attribute.setOuterClassInfoIndex(dis.readUnsignedShort());
		attribute.setInnerNameIndex(dis.readUnsignedShort());
		attribute.setInnerClassAccessFlags((short)dis.readUnsignedShort());
		
		decoupleFromIndices(ctx, attribute);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(DesCtx ctx, ClassEntry classEntry)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		int innerClassInfoIndex = classEntry.getInnerClassInfoIndex();
		int outerClassInfoIndex = classEntry.getOuterClassInfoIndex();
		int      innerNameIndex = classEntry.getInnerNameIndex();
		
		AbstractConstantPoolType acptInnerClassInfoObject = ConstantPool.cpeByIndex(constantPool, innerClassInfoIndex);
		AbstractConstantPoolType acptOuterClassInfoObject = ConstantPool.cpeByIndex(constantPool, outerClassInfoIndex);
		AbstractConstantPoolType acptInnerNameObject      = ConstantPool.cpeByIndex(constantPool, innerNameIndex);
		
		classEntry.setInnerClassInfoObject((ConstantPoolTypeClass)acptInnerClassInfoObject);
		classEntry.setOuterClassInfoObject((ConstantPoolTypeClass)acptOuterClassInfoObject);	
		classEntry.setInnerNameObject((ConstantPoolTypeUtf8)acptInnerNameObject);
	}
	
	private static void coupleToIndices(SerCtx ctx, ClassEntry classEntry)
	{
		/* retrieve constant pool */
		ConstantPool cp = ctx.getConstantPool();
		
		/* get objects */
		ConstantPoolTypeClass innerClassInfoObject = classEntry.getInnerClassInfoObject();
		ConstantPoolTypeClass outerClassInfoObject = classEntry.getOuterClassInfoObject();
		ConstantPoolTypeUtf8  innerClassNameObject = classEntry.getInnerNameObject();
		
	    int innerClassInfoIndex = ConstantPool.indexByCPE(cp, innerClassInfoObject);
	    classEntry.setInnerClassInfoIndex(innerClassInfoIndex);
	    
	    int outerClassInfoIndex = ConstantPool.indexByCPE(cp, outerClassInfoObject);
	    classEntry.setOuterClassInfoIndex(outerClassInfoIndex);
	    
	    int innerClassNameIndex = ConstantPool.indexByCPE(cp, innerClassNameObject);
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
