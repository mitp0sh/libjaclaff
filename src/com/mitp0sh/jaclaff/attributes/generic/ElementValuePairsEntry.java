package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ElementValuePairsEntry 
{
	private int          				elementNameIndex = 0;
	private ConstantPoolTypeUtf8	   elementNameObject = null;
	private ElementValue       				elementValue = null;
	
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

	public void setElementNameObject(ConstantPoolTypeUtf8 elementNameObject)
	{
		this.elementNameObject = elementNameObject;
	}
	
	public static ElementValuePairsEntry deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		ElementValuePairsEntry elementValuePairsEntry = new ElementValuePairsEntry();
		elementValuePairsEntry.setElementNameIndex(dis.readUnsignedShort());
		elementValuePairsEntry.setElementValue(ElementValue.deserialize(dis, constantPool));
		
		decoupleFromIndices(elementValuePairsEntry, constantPool);
		
		return elementValuePairsEntry;
	}
	
	public static void decoupleFromIndices(ElementValuePairsEntry evpe, ConstantPool constantPool)
	{
		evpe.setElementNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, evpe.elementNameIndex));
		evpe.setElementNameIndex(0);		
	}
	
	public static void coupleToIndices(SerCtx ctx, ElementValuePairsEntry elementValuePairsEntry)
	{
		ConstantPool cp = ctx.getConstantPool();
		short elementNameIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, elementValuePairsEntry.getElementNameObject());
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
