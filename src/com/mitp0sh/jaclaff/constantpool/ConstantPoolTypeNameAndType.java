package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeNameAndTypeDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeNameAndType extends AbstractConstantPoolType
{
	private int                         nameIndex = 0;	
	private int                   descriptorIndex = 0;	
	
	private ConstantPoolTypeUtf8       nameObject = null;
	private ConstantPoolTypeUtf8 descriptorObject = null;

	public ConstantPoolTypeNameAndType()
	{
		super.setConstant_pool_string_representation("CONSTANT_NameAndType");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE);
	}
	
	public int getNameIndex()
	{
		return this.nameIndex;
	}
	
	public void setNameIndex(int index) 
	{
		this.nameIndex = index;
	}
	
	public int getDescriptorIndex() 
	{
		return this.descriptorIndex;
	}
	
	public void setDescriptorIndex(int index)
	{
		this.descriptorIndex = index;
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
			this.setDescriptorIndex(0);
			this.addReference(object);
		}
	}
	
	public static ConstantPoolTypeNameAndType deserialize(DataInputStream dis) throws IOException,
	                                                                                  InvalidConstantPoolTypeNameAndTypeDeserializationException
	{
		ConstantPoolTypeNameAndType cptNameAndType = new ConstantPoolTypeNameAndType();
		
		cptNameAndType.setNameIndex(dis.readUnsignedShort());
		cptNameAndType.setDescriptorIndex(dis.readUnsignedShort());
		
		return cptNameAndType;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, ConstantPoolTypeNameAndType cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int nameIndex = cpt.getNameIndex();
		AbstractConstantPoolType acptName = ConstantPool.getConstantPoolTypeByIndex(cp, nameIndex);
		ConstantPoolTypeUtf8 nameObject = (ConstantPoolTypeUtf8)acptName;
		cpt.setNameObject(nameObject);

		int descriptorIndex = cpt.getDescriptorIndex();
		AbstractConstantPoolType acptDescriptor = ConstantPool.getConstantPoolTypeByIndex(cp, descriptorIndex);
		ConstantPoolTypeUtf8 descriptorObject =  (ConstantPoolTypeUtf8)acptDescriptor;
		cpt.setDescriptorObject(descriptorObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, ConstantPoolTypeNameAndType cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		ConstantPoolTypeUtf8 nameObject = cpt.getNameObject();
		int nameIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, nameObject);
		cpt.setNameIndex(nameIndex);
		
		ConstantPoolTypeUtf8 descriptorObject = cpt.getDescriptorObject();
		int descriptorIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, descriptorObject); 
		cpt.setDescriptorIndex(descriptorIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeNameAndType elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getNameIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getDescriptorIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeNameAndType clone()
	{
		/* create new empty instance */
		ConstantPoolTypeNameAndType clone = (ConstantPoolTypeNameAndType)super.clone();
		
		/* fill instance with original data */
		clone.setNameObject(this.getNameObject());
		clone.setDescriptorObject(this.getDescriptorObject());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		try
		{
			ConstantPoolTypeNameAndType cpt = (ConstantPoolTypeNameAndType)obj;
			boolean b0 = cpt.nameObject.equals(this.nameObject);
			boolean b1 = cpt.descriptorObject.equals(this.descriptorObject);
			return b0 && b1;
		}
		catch(NullPointerException e){}
		catch(ClassCastException e){}
		
		return false;
	}
}
