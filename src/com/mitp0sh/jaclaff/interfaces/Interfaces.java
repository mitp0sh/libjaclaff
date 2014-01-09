package com.mitp0sh.jaclaff.interfaces;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class Interfaces
{
	private int[]                         interfaces = {0};
	private ConstantPoolTypeClass[] interfaceObjects = new ConstantPoolTypeClass[0];
	
	public Interfaces(int interfacesCount)
	{
		this.interfaces = new int[interfacesCount];
	}
	
	public int getNumberOfInterfaces()
	{
		return this.interfaces.length;
	}
	
	public int[] getInterfaces()
	{
		return this.interfaces;
	}
	
	public ConstantPoolTypeClass[] getInterfaceObjects()
	{
		return interfaceObjects;
	}

	public void setInterfaceObjects(ConstantPoolTypeClass[] interfaceObjects)
	{
		this.interfaceObjects = interfaceObjects;
	}

	public static Interfaces deserialize(DesCtx ctx, int interfacesCount) throws IOException
    {
		DataInputStream       dis = ctx.getDataInputStream();
		ConstantPool constantPool = ctx.getConstantPool();
		
		Interfaces interfaces = new Interfaces(interfacesCount);
		
		for(int i = 0; i < interfacesCount; i++)
		{
			interfaces.getInterfaces()[i] = dis.readUnsignedShort();
		}
		
		decoupleInterfaceFromIndices(constantPool, interfaces);
		
		return interfaces;
    }
	
	private static void decoupleInterfaceFromIndices(ConstantPool constantPool, Interfaces interfaces)
	{
		/* create */
		interfaces.setInterfaceObjects(new ConstantPoolTypeClass[interfaces.getNumberOfInterfaces() & 0xFFFF]);
		
		for(int i = 0;i < interfaces.getNumberOfInterfaces(); i++)
		{	
			int interfaceEntry = interfaces.getInterfaces()[i];			
			interfaces.getInterfaceObjects()[i] = (ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, interfaceEntry);
			interfaces.getInterfaces()[i] = (short)0;
		}
	}
	
	private static void coupleInterfacesToIndices(SerCtx ctx, Interfaces interfaces)
	{
		for(int i = 0;i < interfaces.getNumberOfInterfaces(); i++)
		{			
			ConstantPoolTypeClass ifaceClass = interfaces.getInterfaceObjects()[i];
			short iface = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), ifaceClass);
			interfaces.getInterfaces()[i] = iface;
		}
	}
	
	public static byte[] serialize(SerCtx ctx, Interfaces interfaces) throws IOException
	{
		/* couple to indices */
		coupleInterfacesToIndices(ctx, interfaces);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < interfaces.getNumberOfInterfaces(); i++)
		{
			baos.write(PNC.toByteArray(interfaces.getInterfaces()[i], Short.class));
		}
		
		return baos.toByteArray();
	}
}
