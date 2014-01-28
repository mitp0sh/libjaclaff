package com.mitp0sh.jaclaff.methods;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
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
	
	public static void decoupleFromIndices(MethodEntry m, ConstantPool cp)
	{
		/* decouple from name index */
		int nameIndex = m.getNameIndex();
		AbstractConstantPoolType acptNameObject = ConstantPool.cpeByIndex(cp, nameIndex);
		ConstantPoolTypeUtf8 nameObject = (ConstantPoolTypeUtf8)acptNameObject;
		m.setNameObject(nameObject);
		
		/* decouple from descriptor index */
		int descriptorIndex = m.getDescIndex();
		AbstractConstantPoolType acptDescriptorObject = ConstantPool.cpeByIndex(cp, descriptorIndex);
		ConstantPoolTypeUtf8 descriptorObject = (ConstantPoolTypeUtf8)acptDescriptorObject;
		m.setDescriptorObject(descriptorObject);
	}
	
	public static void coupleWithIndices(SerCtx ctx, MethodEntry m)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		/* couple name object with index */
		ConstantPoolTypeUtf8 nameObject = m.getNameObject();
		int nameIndex = ConstantPool.indexByCPE(cp, nameObject);
		m.setNameIndex(nameIndex);
		
		/* couple descriptor object with index */
		ConstantPoolTypeUtf8 descriptorObject = m.getDescriptorObject();
		int descriptorIndex = ConstantPool.indexByCPE(cp, descriptorObject);
		m.setDescIndex(descriptorIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, MethodEntry m) throws IOException
	{
		/* couple before serialization */
		coupleWithIndices(ctx, m);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(m.getAccessFlags(),    Short.class));
		baos.write(PNC.toByteArray(m.getNameIndex(),      Short.class));
		baos.write(PNC.toByteArray(m.getDescIndex(),      Short.class));
		baos.write(PNC.toByteArray(m.getAttributeCount(), Short.class));
		
		int num = m.getAttributeCount(); 
		if(num > 0)
		{
			baos.write(Attributes.serialize(ctx, m.getAttributes(), null));
		}
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		String name = getNameObject().toString();
		String desc = getDescriptorObject().toString();
		
		return "Method: " + name + ", descriptor -> " + desc;
	}
}
