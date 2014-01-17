package com.mitp0sh.jaclaff.interfaces;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InterfacesEntry extends AbstractReference
{
	private int                    interfaceIndex = 0;
	private ConstantPoolTypeClass interfaceObject = null;
	
	public int getInterfaceIndex() 
	{
		return interfaceIndex;
	}
	
	public void setInterfaceIndex(int interfaceIndex) 
	{
		this.interfaceIndex = interfaceIndex;
	}
	
	public ConstantPoolTypeClass getInterfaceObject() 
	{
		return interfaceObject;
	}
	
	public void setInterfaceObject(ConstantPoolTypeClass object) 
	{
		this.interfaceObject = object;
		
		if(object != null)
		{
			this.setInterfaceIndex(0);
			this.addReference(object);
		}
	}
	
	public static InterfacesEntry deserialize(DesCtx ctx) throws IOException
    {
		DataInputStream       dis = ctx.getDataInputStream();
		ConstantPool constantPool = ctx.getConstantPool();
		
		InterfacesEntry entry = new InterfacesEntry();
		
		entry.setInterfaceIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(constantPool, entry);
		
		return entry;
    }
	
	private static void decoupleFromIndices(ConstantPool cp, InterfacesEntry iface)
	{			
		int interfaceIndex = iface.getInterfaceIndex();
		AbstractConstantPoolType acpt = ConstantPool.getConstantPoolTypeByIndex(cp, interfaceIndex);
		ConstantPoolTypeClass cpt = (ConstantPoolTypeClass)acpt;
		iface.setInterfaceObject(cpt);
	}
	
	private static void coupleInterfacesToIndices(SerCtx ctx, InterfacesEntry entry)
	{
		int interfaceIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), entry.interfaceObject);
		entry.setInterfaceIndex(interfaceIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, InterfacesEntry entry) throws IOException
	{
		/* couple to indices */
		coupleInterfacesToIndices(ctx, entry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(entry.getInterfaceIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
