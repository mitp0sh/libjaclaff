package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeConstantValue extends AbstractAttribute
{	 
	private int                        constantValueIndex = 0;	
	private AbstractConstantPoolType  constantValueObject = null;	

	public String getAttributeName()
	{
		return AbstractAttribute.attributeConstantValue;
	}

	public int getConstantValueIndex() 
	{
		return constantValueIndex;
	}

	public void setConstantValueIndex(int constantValueIndex) 
	{
		this.constantValueIndex = constantValueIndex;
	}
	
	public AbstractConstantPoolType getConstantValueObject() 
	{
		return constantValueObject;
	}

	public void setConstantValueObject(AbstractConstantPoolType object) 
	{
		this.constantValueObject = object;
		
		if(object != null)
		{
			this.setConstantValueIndex(0);
			this.addReference(object);
		}
	}
	
	public static AttributeConstantValue deserialize(DesCtx ctx) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
	    AttributeConstantValue attribute = new AttributeConstantValue();
	    
	    attribute.setConstantValueIndex(dis.readUnsignedShort());	
	    
	    decoupleFromIndices(ctx, attribute);
		
	    return attribute;
    }
	
	public static void decoupleFromIndices(DesCtx ctx, AttributeConstantValue attribute)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		attribute.setConstantValueObject(ConstantPool.cpeByIndex(constantPool, attribute.constantValueIndex));
		attribute.setConstantValueIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, AttributeConstantValue attribute)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int constantValueIndex = ConstantPool.indexByCPE(cp, attribute.constantValueObject);
		attribute.setConstantValueIndex(constantValueIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, AttributeConstantValue attribute) throws IOException
	{
		coupleToIndices(ctx, attribute);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getConstantValueIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
