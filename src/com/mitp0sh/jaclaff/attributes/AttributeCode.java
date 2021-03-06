package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.code.Disassembly;
import com.mitp0sh.jaclaff.attributes.code.exceptiontable.ExceptionTable;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeCode extends AbstractAttribute
{
	private int                         maxStack = 0;
	private int                        maxLocals = 0;
	private Disassembly                     code = null;
	private int             exceptionTableLength = 0;
	private ExceptionTable        exceptionTable = null;
	private int                  attributesCount = 0;
	private Attributes                attributes = null;
	
	public String getAttributeName()
	{
		return AbstractAttribute.attributeCode;		
	}
	
	public int getMaxStack()
	{
		return this.maxStack;
	}

	public void setMaxStack(int maxStack) 
	{
		this.maxStack = maxStack;
	}

	public int getMaxLocals()
	{
		return this.maxLocals;
	}

	public void setMaxLocals(int maxLocals)
	{
		this.maxLocals = maxLocals;
	}

	public long getCodeLength()
	{
		return code.getPhysicalDisassemblySize();
	}

	public Disassembly getCode()
	{
		return this.code;
	}

	public void setCode(Disassembly code) 
	{
		this.code = code;
	}

	public int getExceptionTableLength()
	{
		return this.exceptionTableLength;
	}

	public void setExceptionTableLength(int exceptionTableLength)
	{
		this.exceptionTableLength = exceptionTableLength;
	}

	public ExceptionTable getExceptionTable()
	{
		return this.exceptionTable;
	}

	public void setExceptionTable(ExceptionTable exceptionTable)
	{
		this.exceptionTable = exceptionTable;
	}

	public int getAttributesCount()
	{
		return this.attributesCount;
	}

	public void setAttributesCount(int attributesCount)
	{
		this.attributesCount = attributesCount;
	}

	public Attributes getAttributes()
	{
		return this.attributes;
	}

	public void setAttributes(Attributes attributes)
	{
		this.attributes = attributes;
	}
	
	public static AttributeCode deserialize(DesCtx ctx, Object reference0) throws IOException
    {
		ConstantPool constantPool = ctx.getConstantPool();
		DataInputStream dis = ctx.getDataInputStream();
		
	    AttributeCode attribute = new AttributeCode();
	    
	    attribute.setMaxStack(dis.readUnsignedShort());	    
	    attribute.setMaxLocals(dis.readUnsignedShort());	    
	    
	    long length = dis.readInt();
	    attribute.setCode(Disassembly.deserialize(ctx, length));
	    
	    attribute.setExceptionTableLength(dis.readUnsignedShort());
	    if(attribute.getExceptionTableLength() > 0)
	    {
	    	attribute.setExceptionTable(ExceptionTable.deserialize(dis, attribute.getExceptionTableLength(), constantPool, attribute.getCode()));
	    }	    
	    
	    attribute.setAttributesCount(dis.readUnsignedShort());
	    if(attribute.getAttributesCount() > 0)
	    {
	    	attribute.setAttributes(Attributes.deserialize(ctx, attribute.getAttributesCount(), attribute));	
	    }	    
	    
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeCode attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getMaxStack(), Short.class));
		baos.write(PNC.toByteArray(attribute.getMaxLocals(), Short.class));
		baos.write(PNC.toByteArray(attribute.getCodeLength(), Integer.class));
		baos.write(Disassembly.serialize(ctx, attribute.getCode()));
		baos.write(PNC.toByteArray(attribute.getExceptionTableLength(), Short.class));
		if(attribute.getExceptionTableLength() > 0)
	    {
			baos.write(ExceptionTable.serialize(ctx, attribute.getExceptionTable(), attribute.getCode()));
	    }
		baos.write(PNC.toByteArray(attribute.getAttributesCount(), Short.class));
		if(attribute.getAttributesCount() > 0)
	    {
			baos.write(Attributes.serialize(ctx, attribute.getAttributes(), attribute));
	    }
		
		return baos.toByteArray();
	}
}
