package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.util.PNC;


public class ElementValuePairsEntry 
{
	private short          				elementNameIndex = 0;
	private ConstantPoolTypeUtf8	   elementNameObject = null;
	private ElementValue       				elementValue = null;
	
	public short getElementNameIndex()
	{
		return elementNameIndex;
	}
	
	public void setElementNameIndex(short elementNameIndex)
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
	
	public static void decoupleFromIndices(ElementValuePairsEntry evpe, ConstantPool constantPool)
	{
		evpe.setElementNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, evpe.elementNameIndex));
		evpe.setElementNameIndex((short)0);		
	}
	
	public static ElementValuePairsEntry deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		ElementValuePairsEntry elementValuePairsEntry = new ElementValuePairsEntry();
		elementValuePairsEntry.setElementNameIndex((short)dis.readUnsignedShort());
		elementValuePairsEntry.setElementValue(ElementValue.deserialize(dis, constantPool));
		
		decoupleFromIndices(elementValuePairsEntry, constantPool);
		
		return elementValuePairsEntry;
	}
	
	public static byte[] serialize(ElementValuePairsEntry elementValuePairsEntry) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(elementValuePairsEntry.getElementNameIndex(), Short.class));
		baos.write(ElementValue.serialize(elementValuePairsEntry.getElementValue()));
		
		return baos.toByteArray();
	}
}
