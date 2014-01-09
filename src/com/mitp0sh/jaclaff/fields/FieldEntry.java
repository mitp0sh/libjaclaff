package com.mitp0sh.jaclaff.fields;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class FieldEntry
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
	
	public static void decoupleFromIndices(FieldEntry field, ConstantPool constantPool)
	{
		field.setNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, field.getNameIndex()));
		field.setNameIndex((short)0);
		field.setDescriptorObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, field.getDescIndex()));
		field.setDescIndex((short)0);
	}
	
	public static FieldEntry deserialize(DesCtx ctx) throws IOException
    {
		ConstantPool constantPool = ctx.getConstantPool();
		DataInputStream dis = ctx.getDataInputStream();
		
		FieldEntry fieldEntry = new FieldEntry();
		
		fieldEntry.setAccessFlags((short)dis.readUnsignedShort());
		fieldEntry.setNameIndex((short)dis.readUnsignedShort());
		fieldEntry.setDescIndex((short)dis.readUnsignedShort());
		fieldEntry.setAttributeCount((short)dis.readUnsignedShort());
		if(fieldEntry.getAttributeCount() > 0)
		{
			fieldEntry.setAttributes(Attributes.deserialize(ctx, fieldEntry.getAttributeCount(), null));
		}
		
		/* decouple indices from object */
		decoupleFromIndices(fieldEntry, constantPool);
		
		return fieldEntry;
    }
	
	public static void coupleToIndices(SerCtx ctx, FieldEntry field)
	{
		short nameIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), field.getNameObject());
		field.setNameIndex(nameIndex);
		short descriptorIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), field.getDescriptorObject());
		field.setDescIndex(descriptorIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, FieldEntry fieldEntry) throws IOException
	{
		coupleToIndices(ctx, fieldEntry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(fieldEntry.getAccessFlags(), Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getNameIndex(), Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getDescIndex(), Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getAttributeCount(), Short.class));
		if(fieldEntry.getAttributeCount() > 0)
		{
			baos.write(Attributes.serialize(ctx, fieldEntry.getAttributes(), null));
		}
		
		return baos.toByteArray();
	}
}
