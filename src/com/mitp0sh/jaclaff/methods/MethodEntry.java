package com.mitp0sh.jaclaff.methods;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class MethodEntry
{
	private short                     accessFlags = 0;
	private short                       nameIndex = 0;
	private ConstantPoolTypeUtf8       nameObject = null;
	private short                       descIndex = 0;
	private ConstantPoolTypeUtf8 descriptorObject = null;
	private short                  attributeCount = 0;
	private Attributes                 attributes = null;
	
	public short getAccessFlags()
	{
		return accessFlags;
	}
	
	public void setAccessFlags(short accessFlags)
	{
		this.accessFlags = accessFlags;
	}
	
	public short getNameIndex()
	{
		return nameIndex;
	}
	
	public void setNameIndex(short nameIndex)
	{
		this.nameIndex = nameIndex;
	}
	
	public short getDescIndex()
	{
		return descIndex;
	}
	
	public void setDescIndex(short descIndex)
	{
		this.descIndex = descIndex;
	}
	
	public ConstantPoolTypeUtf8 getNameObject() 
	{
		return nameObject;
	}

	public void setNameObject(ConstantPoolTypeUtf8 nameObject) 
	{
		this.nameObject = nameObject;
	}

	public ConstantPoolTypeUtf8 getDescriptorObject() 
	{
		return descriptorObject;
	}

	public void setDescriptorObject(ConstantPoolTypeUtf8 descriptorObject) 
	{
		this.descriptorObject = descriptorObject;
	}
	
	public short getAttributeCount()
	{
		return attributeCount;
	}
	
	public void setAttributeCount(short attributeCount)
	{
		this.attributeCount = attributeCount;
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
		methodEntry.setNameIndex((short)dis.readUnsignedShort());
		methodEntry.setDescIndex((short)dis.readUnsignedShort());
		methodEntry.setAttributeCount((short)dis.readUnsignedShort());		
		if(methodEntry.getAttributeCount() > 0)
		{
			methodEntry.setAttributes(Attributes.deserialize(ctx, methodEntry.getAttributeCount(), null));
		}
		
		/* decouple indices from object */
		decoupleFromIndices(methodEntry, constantPool);
		
		return methodEntry;
    }
	
	public static void decoupleFromIndices(MethodEntry method, ConstantPool constantPool)
	{
		method.setNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, method.getNameIndex()));
		method.setNameIndex((short)0);
		method.setDescriptorObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, method.getDescIndex()));
		method.setDescIndex((short)0);
	}
	
	public static void coupleToIndices(SerCtx ctx, MethodEntry method)
	{
		short nameIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), method.getNameObject());
		method.setNameIndex(nameIndex);
		short descriptorIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), method.getDescriptorObject());
		method.setDescIndex(descriptorIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, MethodEntry methodEntry) throws IOException
	{
		coupleToIndices(ctx, methodEntry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(methodEntry.getAccessFlags(), Short.class));
		baos.write(PNC.toByteArray(methodEntry.getNameIndex(), Short.class));
		baos.write(PNC.toByteArray(methodEntry.getDescIndex(), Short.class));
		baos.write(PNC.toByteArray(methodEntry.getAttributeCount(), Short.class));
		if(methodEntry.getAttributeCount() > 0)
		{
			baos.write(Attributes.serialize(ctx, methodEntry.getAttributes()));
		}
		
		return baos.toByteArray();
	}
}
