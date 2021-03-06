package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeMethodTypeDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ConstantPoolTypeMethodType extends AbstractConstantPoolType
{	
	private int                   descriptorIndex = 0;
	private ConstantPoolTypeUtf8 descriptorObject = null;

	public ConstantPoolTypeMethodType()
	{
		super.setConstant_pool_string_representation("CONSTANT_MethodType");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODTYPE);
	}
	
	public int getDescriptorIndex() 
	{
		return descriptorIndex;
	}
	
	public void setDescriptorIndex(int descriptorIndex) 
	{
		this.descriptorIndex = descriptorIndex;
	}

	public ConstantPoolTypeUtf8 getDescriptorObject() 
	{
		return descriptorObject;
	}
	
	public void setDescriptorObject(ConstantPoolTypeUtf8 descriptorObject) 
	{
		this.descriptorObject = descriptorObject;
	}
	
	public static ConstantPoolTypeMethodType deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException,
	                                                                                                            InvalidConstantPoolTypeMethodTypeDeserializationException
	{
		ConstantPoolTypeMethodType type = new ConstantPoolTypeMethodType();
		
		type.setDescriptorIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(type, constantPool);
		
		return type;
	}
	
	public static void decoupleFromIndices(ConstantPoolTypeMethodType type, ConstantPool constantPool)
	{
		type.setDescriptorObject((ConstantPoolTypeUtf8)(ConstantPool.cpeByIndex(constantPool, type.getDescriptorIndex())));
		type.setDescriptorIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, ConstantPoolTypeMethodType type)
	{
		int descriptorIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), type.getDescriptorObject());
		type.setDescriptorIndex(descriptorIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeMethodType type) throws IOException
	{
		coupleToIndices(ctx, type);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(type.getDescriptorIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeMethodType clone()
	{
		/* create new empty instance */
		ConstantPoolTypeMethodType clone = (ConstantPoolTypeMethodType)super.clone();
		
		/* fill instance with original data */
		clone.setDescriptorObject(this.descriptorObject);
		
		return clone;
	}
	
	@Override
	public String toString()
	{	
		return "TODO - NOT YET IMPELEMENTED !!!";
	}
}
