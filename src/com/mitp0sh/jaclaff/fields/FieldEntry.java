package com.mitp0sh.jaclaff.fields;

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


public class FieldEntry extends AbstractReference
{
	private short                     accessFlags = 0;
	private int                         nameIndex = 0;
	private int                         descIndex = 0;
	
	private ConstantPoolTypeUtf8       nameObject = null;
	private ConstantPoolTypeUtf8 descriptorObject = null;
	private Attributes                 attributes = new Attributes();

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
	
	public static FieldEntry deserialize(DesCtx ctx) throws IOException
    {
		ConstantPool constantPool = ctx.getConstantPool();
		DataInputStream dis = ctx.getDataInputStream();
		
		FieldEntry fieldEntry = new FieldEntry();
		
		fieldEntry.setAccessFlags((short)dis.readUnsignedShort());
		fieldEntry.setNameIndex(dis.readUnsignedShort());
		fieldEntry.setDescIndex(dis.readUnsignedShort());
		
		int attributeCount = dis.readUnsignedShort();
		if(attributeCount > 0)
		{
			fieldEntry.setAttributes(Attributes.deserialize(ctx, attributeCount, null));
		}
		
		/* decouple indices from object */
		decoupleFromIndices(fieldEntry, constantPool);
		
		return fieldEntry;
    }
	
	public static void decoupleFromIndices(FieldEntry field, ConstantPool constantPool)
	{
		field.setNameObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(constantPool, field.getNameIndex()));
		field.setDescriptorObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(constantPool, field.getDescIndex()));
	}
	
	public static void coupleToIndices(SerCtx ctx, FieldEntry field)
	{
		int nameIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), field.getNameObject());
		field.setNameIndex(nameIndex);
		
		int descriptorIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), field.getDescriptorObject());
		field.setDescIndex(descriptorIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, FieldEntry fieldEntry) throws IOException
	{
		coupleToIndices(ctx, fieldEntry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(fieldEntry.getAccessFlags(),    Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getNameIndex(),      Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getDescIndex(),      Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getAttributeCount(), Short.class));
		if(fieldEntry.getAttributeCount() > 0)
		{
			baos.write(Attributes.serialize(ctx, fieldEntry.getAttributes(), null));
		}
		
		return baos.toByteArray();
	}
}
