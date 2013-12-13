package com.mitp0sh.jaclaff.methods;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;


public class Methods
{
private MethodEntry[] methods = new MethodEntry[0];	
	
	public Methods(short methodCount)
	{
		this.methods = new MethodEntry[methodCount];
	}
	
	public MethodEntry[] getMethods()
	{
		return this.methods;
	}
	
	public static Methods deserialize(DataInputStream dis, short methodsCount, ConstantPool constantPool) throws IOException
    {	
		Methods methods = new Methods(methodsCount);
		
		for(int i = 0; i < methodsCount; i++)
		{			
			methods.getMethods()[i] = MethodEntry.deserialize(dis, constantPool);
		}
		
		return methods;
    }
	
	public static byte[] serialize(Methods methods, ConstantPool constantPool) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < methods.getMethods().length; i++)
		{
			baos.write(MethodEntry.serialize(methods.getMethods()[i], constantPool));
		}
		
		return baos.toByteArray();
	}
}
