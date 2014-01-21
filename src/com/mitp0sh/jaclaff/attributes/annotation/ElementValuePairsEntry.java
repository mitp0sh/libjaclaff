package com.mitp0sh.jaclaff.attributes.annotation;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class ElementValuePairsEntry extends AbstractReference
{
	private int          		  elementNameIndex = 0;
	
	private ConstantPoolTypeUtf8 elementNameObject = null;
	
	private ElementValue              elementValue = null;
	
	public int getElementNameIndex()
	{
		return elementNameIndex;
	}
	
	public void setElementNameIndex(int elementNameIndex)
	{
		this.elementNameIndex = elementNameIndex;
	}
	
	public ElementValue getElementValue() 
	{
		return elementValue;
	}
	
	public void setElementValue(ElementValue elementValue)
	{
		this.elementValue = elementValue;
	}
	
	public ConstantPoolTypeUtf8 getElementNameObject() 
	{
		return elementNameObject;
	}

	public void setElementNameObject(ConstantPoolTypeUtf8 object)
	{
		this.elementNameObject = object;
		
		if(object != null)
		{
			this.setElementNameIndex(0);
			this.addReference(object);
		}
	}
	
	public static ElementValuePairsEntry deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		ElementValuePairsEntry elementValuePairsEntry = new ElementValuePairsEntry();
		elementValuePairsEntry.setElementNameIndex(dis.readUnsignedShort());
		elementValuePairsEntry.setElementValue(ElementValue.deserialize(ctx));
		
		decoupleFromIndices(ctx, elementValuePairsEntry);
		
		return elementValuePairsEntry;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, ElementValuePairsEntry evpe)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		evpe.setElementNameObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(constantPool, evpe.elementNameIndex));
		evpe.setElementNameIndex(0);		
	}
	
	public static void coupleToIndices(SerCtx ctx, ElementValuePairsEntry elementValuePairsEntry)
	{
		ConstantPool cp = ctx.getConstantPool();
		int elementNameIndex = ConstantPool.indexByCPE(cp, elementValuePairsEntry.getElementNameObject());
		elementValuePairsEntry.setElementNameIndex(elementNameIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ElementValuePairsEntry elementValuePairsEntry) throws IOException
	{
		coupleToIndices(ctx, elementValuePairsEntry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(elementValuePairsEntry.getElementNameIndex(), Short.class));
		baos.write(ElementValue.serialize(ctx, elementValuePairsEntry.getElementValue()));
		
		return baos.toByteArray();
	}
}
