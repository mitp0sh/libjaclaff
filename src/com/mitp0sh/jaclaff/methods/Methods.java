package com.mitp0sh.jaclaff.methods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class Methods
{
	private ArrayList<MethodEntry> methods = new ArrayList<MethodEntry>();	
	
	public int getNumberOfMethods()
	{
		return methods.size();
	}
	
	public ArrayList<MethodEntry> getMethods()
	{
		return this.methods;
	}
	
	public static Methods deserialize(DesCtx ctx, int methodsCount) throws IOException
    {	
		Methods methods = new Methods();
		
		for(int i = 0; i < methodsCount; i++)
		{			
			methods.getMethods().add(MethodEntry.deserialize(ctx));
		}
		
		return methods;
    }
	
	public static byte[] serialize(SerCtx ctx, Methods methods) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < methods.getMethods().size(); i++)
		{
			baos.write(MethodEntry.serialize(ctx, methods.getMethods().get(i)));
		}
		
		return baos.toByteArray();
	}
}
