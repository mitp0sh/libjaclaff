package com.mitp0sh.jaclaff.fields;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
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
		field.setDescriptorObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, field.getNameIndex()));
		field.setDescIndex((short)0);
	}
	
	public static FieldEntry deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		FieldEntry fieldEntry = new FieldEntry();
		
		fieldEntry.setAccessFlags((short)dis.readUnsignedShort());
		fieldEntry.setNameIndex((short)dis.readUnsignedShort());
		fieldEntry.setDescIndex((short)dis.readUnsignedShort());
		fieldEntry.setAttributeCount((short)dis.readUnsignedShort());
		if(fieldEntry.getAttributeCount() > 0)
		{
			fieldEntry.setAttributes(Attributes.deserialize(dis, fieldEntry.getAttributeCount(), constantPool));
		}
		
		/* decouple indices from object */
		decoupleFromIndices(fieldEntry, constantPool);
		
		return fieldEntry;
    }
	
	public static byte[] serialize(FieldEntry fieldEntry, ConstantPool constantPool) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(fieldEntry.getAccessFlags(), Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getNameIndex(), Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getDescIndex(), Short.class));
		baos.write(PNC.toByteArray(fieldEntry.getAttributeCount(), Short.class));
		if(fieldEntry.getAttributeCount() > 0)
		{
			baos.write(Attributes.serialize(fieldEntry.getAttributes(), constantPool));
		}
		
		return baos.toByteArray();
	}
}
