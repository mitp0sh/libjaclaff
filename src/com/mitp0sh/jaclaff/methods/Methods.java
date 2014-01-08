package com.mitp0sh.jaclaff.methods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class Methods
{
private MethodEntry[] methods = new MethodEntry[0];	
	
	public Methods(short methodCount)
	{
		this.methods = new MethodEntry[methodCount & 0xFFFF];
	}
	
	public MethodEntry[] getMethods()
	{
		return this.methods;
	}
	
	public static Methods deserialize(DesCtx ctx, short methodsCount) throws IOException
    {	
		Methods methods = new Methods(methodsCount);
		
		for(int i = 0; i < methodsCount; i++)
		{			
			methods.getMethods()[i] = MethodEntry.deserialize(ctx);
		}
		
		return methods;
    }
	
	public static byte[] serialize(SerCtx ctx, Methods methods) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < methods.getMethods().length; i++)
		{
			baos.write(MethodEntry.serialize(ctx, methods.getMethods()[i]));
		}
		
		return baos.toByteArray();
	}
}
