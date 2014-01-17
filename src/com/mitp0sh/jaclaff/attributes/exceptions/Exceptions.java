package com.mitp0sh.jaclaff.attributes.exceptions;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class Exceptions
{
	private ArrayList<ExceptionsEntry> exceptions = new ArrayList<ExceptionsEntry>();
	
	public ArrayList<ExceptionsEntry> getExpections()
	{
		return exceptions;
	}
	
	public int getNumExceptions()
	{
		return exceptions.size();
	}

	public static Exceptions deserialize(DesCtx ctx, int num) throws IOException
	{		
		Exceptions exceptions = new Exceptions();
		
		for(int i = 0; i < num; i++)
		{
			exceptions.getExpections().add(ExceptionsEntry.deserialize(ctx));
		}
		
		return exceptions;
    }
	
	public static byte[] serialize(SerCtx ctx, Exceptions exceptions) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Iterator<ExceptionsEntry> iter = exceptions.getExpections().iterator();
		while(iter.hasNext())
		{
			ExceptionsEntry current = iter.next();
			baos.write(ExceptionsEntry.serialize(ctx, current));
		}
		
		return baos.toByteArray();
	}
}
