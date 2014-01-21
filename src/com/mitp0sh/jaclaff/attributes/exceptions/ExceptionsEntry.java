package com.mitp0sh.jaclaff.attributes.exceptions;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ExceptionsEntry extends AbstractReference
{
	private int                    exceptionIndex = 0;
	private ConstantPoolTypeClass exceptionObject = null;
	
	public int getExceptionIndex() 
	{
		return exceptionIndex;
	}
	
	public void setExceptionIndex(int exceptionIndex) 
	{
		this.exceptionIndex = exceptionIndex;
	}
	
	public ConstantPoolTypeClass getExceptionObject() 
	{
		return exceptionObject;
	}
	
	public void setExceptionObject(ConstantPoolTypeClass object) 
	{
		this.exceptionObject = object;
		
		if(object != null)
		{
			this.setExceptionIndex(0);
			this.addReference(object);
		}
	}
	
	public static ExceptionsEntry deserialize(DesCtx ctx) throws IOException
	{		
		DataInputStream dis = ctx.getDataInputStream();
		
		ExceptionsEntry exceptionsEntry = new ExceptionsEntry();
		
		int exceptionIndex = dis.readUnsignedShort();
		exceptionsEntry.setExceptionIndex(exceptionIndex);
		
		decoupleFromIndices(ctx, exceptionsEntry);
		
		return exceptionsEntry;
    }
	
	public static void decoupleFromIndices(DesCtx ctx, ExceptionsEntry exceptionsEntry)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		int exceptionIndex = exceptionsEntry.getExceptionIndex();
		AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(constantPool, exceptionIndex);
		ConstantPoolTypeClass cpt = (ConstantPoolTypeClass)acpt;
		exceptionsEntry.setExceptionObject(cpt);
	}
	
	public static void coupleToIndices(SerCtx ctx, ExceptionsEntry exceptionsEntry)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		ConstantPoolTypeClass cpt = exceptionsEntry.getExceptionObject();
		int exceptionIndex = ConstantPool.indexByCPE(constantPool, cpt);		
		exceptionsEntry.setExceptionIndex(exceptionIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ExceptionsEntry exceptionsEntry) throws IOException
	{
		coupleToIndices(ctx, exceptionsEntry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(exceptionsEntry.getExceptionIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
