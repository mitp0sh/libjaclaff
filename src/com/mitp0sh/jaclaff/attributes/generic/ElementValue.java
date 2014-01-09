package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class ElementValue
{
	private char    tag = 0; 
	private Value value = null;
	
	public char getTag()
	{
		return tag;
	}
	
	public void setTag(char tag) 
	{
		this.tag = tag;
	}
	
	public Value getValue() 
	{
		return value;
	}
	
	public void setValue(Value value) 
	{
		this.value = value;
	}
	
	public static ElementValue deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		ElementValue elementValue = new ElementValue();
		
		elementValue.setTag((char)dis.readUnsignedByte());
		elementValue.setValue(Value.deserialize(ctx, elementValue.getTag()));
		
		return elementValue;
	}
	
	public static byte[] serialize(SerCtx ctx, ElementValue elementValue) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)elementValue.getTag()});
		baos.write(Value.serialize(ctx, elementValue.getValue(), elementValue.getTag()));
		
		return baos.toByteArray();
	}
}
