package com.mitp0sh.jaclaff.attributes.innerclasses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class Classes 
{	
	private ClassEntry[] classes = new ClassEntry[0];	

	public Classes(int numberOfClasses)
	{	
		this.classes = new ClassEntry[numberOfClasses];
	}
	
	public int getNumberOfClasses()
	{
		return this.classes.length;
	}

	public ClassEntry[] getClasses() 
	{
		return classes;
	}

	public static Classes deserialize(DesCtx ctx, int num) throws IOException
	{		
		Classes attribute = new Classes(num);
		
		for(int i = 0; i < num; i++)
		{
			attribute.getClasses()[i] = ClassEntry.deserialize(ctx);
		}
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, Classes classes) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < classes.getNumberOfClasses(); i++)
		{
			baos.write(ClassEntry.serialize(ctx, classes.getClasses()[i]));
		}
		
		return baos.toByteArray();
	}
}
