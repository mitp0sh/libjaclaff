package com.mitp0sh.jaclaff.methods;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class MethodEntry extends AbstractReference
{
	private short                     accessFlags = 0;
	private int                         nameIndex = 0;
	private int                         descIndex = 0;
	
	private ConstantPoolTypeUtf8       nameObject = null;
	private ConstantPoolTypeUtf8 descriptorObject = null;
	private Attributes                 attributes = null;
	
	public short getAccessFlags()
	{
		return accessFlags;
	}
	
	public void setAccessFlags(short accessFlags)
	{
		this.accessFlags = accessFlags;
	}
	
	public int getNameIndex()
	{
		return nameIndex;
	}
	
	public void setNameIndex(int nameIndex)
	{
		this.nameIndex = nameIndex;
	}
	
	public int getDescIndex()
	{
		return descIndex;
	}
	
	public void setDescIndex(int descIndex)
	{
		this.descIndex = descIndex;
	}
	
	public ConstantPoolTypeUtf8 getNameObject() 
	{
		return nameObject;
	}

	public void setNameObject(ConstantPoolTypeUtf8 object) 
	{
		this.nameObject = object;
		
		if(object != null)
		{
			this.setNameIndex(0);
			this.addReference(object);
		}
	}

	public ConstantPoolTypeUtf8 getDescriptorObject() 
	{
		return descriptorObject;
	}

	public void setDescriptorObject(ConstantPoolTypeUtf8 object) 
	{
		this.descriptorObject = object;
		
		if(object != null)
		{
			this.setDescIndex(0);
			this.addReference(object);
		}
	}
	
	public int getAttributeCount()
	{
		Attributes attributes = getAttributes();
		
		if(attributes == null)
		{
			return 0;
		}
		
		return attributes.getAttributesCount();
	}
	
	public Attributes getAttributes()
	{
		return attributes;
	}
	
	public void setAttributes(Attributes attributes)
	{
		this.attributes = attributes;
	}
	
	public static MethodEntry deserialize(DesCtx ctx) throws IOException
    {
		ConstantPool constantPool = ctx.getConstantPool();
		DataInputStream       dis = ctx.getDataInputStream();
		
		MethodEntry methodEntry = new MethodEntry();
		
		methodEntry.setAccessFlags((short)dis.readUnsignedShort());
		methodEntry.setNameIndex(dis.readUnsignedShort());
		methodEntry.setDescIndex(dis.readUnsignedShort());
		int num = dis.readUnsignedShort();		
		
		/* decouple indices from object */
		decoupleFromIndices(methodEntry, constantPool);
		
		if(num > 0)
		{
			methodEntry.setAttributes(Attributes.deserialize(ctx, num, null));
		}
		
		return methodEntry;
    }
	
	public static void decoupleFromIndices(MethodEntry method, ConstantPool constantPool)
	{
		method.setNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, method.getNameIndex()));
		method.setNameIndex(0);
		
		method.setDescriptorObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, method.getDescIndex()));
		method.setDescIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, MethodEntry method)
	{
		int nameIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), method.getNameObject());
		method.setNameIndex(nameIndex);
		
		int descriptorIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), method.getDescriptorObject());
		method.setDescIndex(descriptorIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, MethodEntry methodEntry) throws IOException
	{
		coupleToIndices(ctx, methodEntry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(methodEntry.getAccessFlags(),    Short.class));
		baos.write(PNC.toByteArray(methodEntry.getNameIndex(),      Short.class));
		baos.write(PNC.toByteArray(methodEntry.getDescIndex(),      Short.class));
		baos.write(PNC.toByteArray(methodEntry.getAttributeCount(), Short.class));
		if(methodEntry.getAttributeCount() > 0)
		{
			baos.write(Attributes.serialize(ctx, methodEntry.getAttributes(), null));
		}
		
		return baos.toByteArray();
	}
}
