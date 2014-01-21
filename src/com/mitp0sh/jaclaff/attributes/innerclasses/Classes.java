package com.mitp0sh.jaclaff.attributes.innerclasses;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class Classes 
{	
	private ArrayList<ClassEntry> classes = new ArrayList<ClassEntry>();	
	
	public int getNumberOfClasses()
	{
		return this.classes.size();
	}

	public ArrayList<ClassEntry> getClasses() 
	{
		return classes;
	}

	public static Classes deserialize(DesCtx ctx, int num) throws IOException
	{		
		Classes attribute = new Classes();
		
		for(int i = 0; i < num; i++)
		{
			attribute.getClasses().add(ClassEntry.deserialize(ctx));
		}
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, Classes classes) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Iterator<ClassEntry> iter = classes.getClasses().iterator();
		while(iter.hasNext())
		{
			ClassEntry current = iter.next();
			baos.write(ClassEntry.serialize(ctx, current));
		}
		
		return baos.toByteArray();
	}
}
