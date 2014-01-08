package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.innerclasses.Classes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeInnerClasses extends AbstractAttribute
{
	private int     numberOfClasses = 0;
	private Classes         classes = null;
		
	public AttributeInnerClasses(int numberOfClasses)
	{
		this.numberOfClasses = numberOfClasses;		
	}

	public int getNumberOfClasses() 
	{
		return numberOfClasses;
	}

	public void setNumberOfClasses(int numberOfClasses)
	{
		this.numberOfClasses = numberOfClasses;
	}

	public Classes getClasses() 
	{
		return classes;
	}

	public void setClasses(Classes classes) 
	{
		this.classes = classes;
	}

	public static AttributeInnerClasses deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{				
		AttributeInnerClasses attribute = new AttributeInnerClasses(dis.readUnsignedShort());
	
		attribute.setClasses(Classes.deserialize(dis, attribute.getNumberOfClasses(), constantPool));
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeInnerClasses attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		baos.write(PNC.toByteArray(attribute.getNumberOfClasses(), Short.class));
		
		baos.write(Classes.serialize(ctx, attribute.getClasses()));
		
		return baos.toByteArray();
	}
}
