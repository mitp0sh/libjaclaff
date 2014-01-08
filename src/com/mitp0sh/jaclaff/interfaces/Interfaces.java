package com.mitp0sh.jaclaff.interfaces;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class Interfaces
{
	private short[]                    interfaces = {0};
	private ConstantPoolTypeClass[] cptInterfaces = {null};
	
	public Interfaces(short interfacesCount)
	{
		this.interfaces = new short[interfacesCount & 0xFFFF];
	}
	
	public short getInterfacesCount()
	{
		return ((short)(this.interfaces.length));
	}
	
	public short[] getInterfaces()
	{
		return this.interfaces;
	}
	
	public ConstantPoolTypeClass[] getCptInterfaces()
	{
		return cptInterfaces;
	}

	public void setCptInterfaces(ConstantPoolTypeClass[] cptInterfaces)
	{
		this.cptInterfaces = cptInterfaces;
	}

	public static Interfaces deserialize(DataInputStream dis, short interfacesCount, ConstantPool constantPool) throws IOException
    {
		Interfaces interfaces = new Interfaces(interfacesCount);
		
		for(int i = 0; i < interfacesCount; i++)
		{
			interfaces.getInterfaces()[i] = (short)dis.readUnsignedShort();
		}
		
		decoupleInterfaceFromIndices(constantPool, interfaces);
		
		return interfaces;
    }
	
	private static void decoupleInterfaceFromIndices(ConstantPool constantPool, Interfaces interfaces)
	{
		/* create */
		interfaces.setCptInterfaces(new ConstantPoolTypeClass[interfaces.getInterfacesCount() & 0xFFFF]);
		
		for(int i = 0;i < interfaces.getInterfacesCount(); i++)
		{	
			short interfaceEntry = interfaces.getInterfaces()[i];			
			interfaces.getCptInterfaces()[i] = (ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, interfaceEntry);
			interfaces.getInterfaces()[i] = (short)0;
		}
	}
	
	private static void coupleInterfacesToIndices(SerCtx ctx, Interfaces interfaces)
	{
		for(int i = 0;i < interfaces.getInterfacesCount(); i++)
		{			
			ConstantPoolTypeClass ifaceClass = interfaces.getCptInterfaces()[i];
			short iface = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), ifaceClass);
			interfaces.getInterfaces()[i] = iface;
		}
	}
	
	public static byte[] serialize(SerCtx ctx, Interfaces interfaces) throws IOException
	{
		/* couple to indices */
		coupleInterfacesToIndices(ctx, interfaces);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < interfaces.getInterfacesCount(); i++)
		{
			baos.write(PNC.toByteArray(interfaces.getInterfaces()[i], Short.class));
		}
		
		return baos.toByteArray();
	}
}
