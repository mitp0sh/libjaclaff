package com.mitp0sh.jaclaff.attributes.innerclasses;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;


public class Classes 
{	
	private ClassEntry[]            classes = null;	

	public Classes(short numberOfClasses)
	{	
		this.classes         = new ClassEntry[numberOfClasses];
	}
	
	public short getNumberOfClasses()
	{
		return (short) this.classes.length;
	}

	public ClassEntry[] getClasses() 
	{
		return classes;
	}

	public static Classes deserialize(DataInputStream dis, short numberOfClasses, ConstantPool constantPool) throws IOException
	{
		Classes attribute = new Classes(numberOfClasses);
		
		for(int i = 0; i < numberOfClasses; i++)
		{
			attribute.getClasses()[i] = ClassEntry.deserialize(dis, constantPool);
		}
		
		return attribute;
    }
	
	public static byte[] serialize(Classes classes) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < classes.getNumberOfClasses(); i++)
		{
			baos.write(ClassEntry.serialize(classes.getClasses()[i]));
		}
		
		return baos.toByteArray();
	}
}
