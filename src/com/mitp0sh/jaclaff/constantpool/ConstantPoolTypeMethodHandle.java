package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeMethodHandleDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ConstantPoolTypeMethodHandle extends AbstractConstantPoolType
{	
	private byte                    referenceKind = 0;
	private int                    referenceIndex = 0;
	
	public static final byte         REF_getField = 1;
	public static final byte        REF_getStatic = 2;
	public static final byte         REF_putField = 3;
	public static final byte        REF_putStatic = 4;	
	public static final byte    REF_invokeVirtual = 5;
	public static final byte     REF_invokeStatic = 6;
	public static final byte    REF_invokeSpecial = 7;
	public static final byte REF_newInvokeSpecial = 8;
	public static final byte  REF_invokeInterface = 9;
	
	private AbstractConstantPoolType    reference = null;

	public ConstantPoolTypeMethodHandle()
	{
		super.setConstant_pool_string_representation("CONSTANT_MethodHandle");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODHANDLE);
	}
	
	public byte getReferenceKind() 
	{
		return referenceKind;
	}

	public void setReferenceKind(byte referenceKind) 
	{
		this.referenceKind = referenceKind;
	}
	
	public int getReferenceIndex() 
	{
		return referenceIndex;
	}
	
	public void setReferenceIndex(int referenceIndex) 
	{
		this.referenceIndex = referenceIndex;
	}
	
	public AbstractConstantPoolType getReference() 
	{
		return reference;
	}

	public void setReference(AbstractConstantPoolType reference) 
	{
		this.reference = reference;
	}
	
	public static ConstantPoolTypeMethodHandle deserialize(DataInputStream dis, ConstantPool constantPool) throws InvalidConstantPoolTypeMethodHandleDeserializationException,
	                                                                                                              IOException
	{
		ConstantPoolTypeMethodHandle handle = new ConstantPoolTypeMethodHandle();
		
		handle.setReferenceKind((byte)dis.readUnsignedByte());
		handle.setReferenceIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(handle, constantPool);
		
		return handle;
	}
	
	public static void decoupleFromIndices(ConstantPoolTypeMethodHandle handle, ConstantPool constantPool)
	{
		handle.setReference(ConstantPool.cpeByIndex(constantPool, handle.getReferenceIndex()));
		handle.setReferenceIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, ConstantPoolTypeMethodHandle handle)
	{
		int referenceIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), handle.getReference());
		handle.setReferenceIndex(referenceIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeMethodHandle handle) throws IOException
	{
		coupleToIndices(ctx, handle);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{handle.getReferenceKind()});
		baos.write(PNC.toByteArray(handle.getReferenceIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeMethodHandle clone()
	{
		/* create new empty instance */
		ConstantPoolTypeMethodHandle clone = (ConstantPoolTypeMethodHandle)super.clone();
		
		/* fill instance with original data */
		clone.setReferenceKind(this.referenceKind);
		clone.setReference(this.reference);
		
		return clone;
	}
	
//	@Override
//	public boolean equals(Object obj)
//	{
//		try
//		{
//			ConstantPoolTypeMethodHandle cpt = (ConstantPoolTypeMethodHandle)obj;
//			boolean b0 = cpt.getReferenceKind() == this.referenceKind;
//			boolean b1 = cpt.getReference().equals(this.reference);
//			return b0 && b1;
//		}
//		catch(NullPointerException e){}
//		catch(ClassCastException e){}
//		
//		return false;
//	}
}
