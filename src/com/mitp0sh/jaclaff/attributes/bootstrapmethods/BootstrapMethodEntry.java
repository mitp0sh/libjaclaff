package com.mitp0sh.jaclaff.attributes.bootstrapmethods;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeMethodHandle;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class BootstrapMethodEntry
{
	private int bootstrapMethodRefIndex = 0;
	private int   numBootstrapArguments = 0;
	private int[]    bootstrapArguments = null;
	
	private ConstantPoolTypeMethodHandle         bootstrapMethodRefObject = null;
	private ArrayList<AbstractConstantPoolType> bootstrapArgumentsObjects = new ArrayList<AbstractConstantPoolType>();
	
	public int getBootstrapMethodRefIndex()
	{
		return bootstrapMethodRefIndex;
	}
	
	public void setBootstrapMethodRefIndex(int bootstrapMethodRefIndex)
	{
		this.bootstrapMethodRefIndex = bootstrapMethodRefIndex;
	}
	
	public int getNumBootstrapArguments() 
	{
		return numBootstrapArguments;
	}
	
	public void setNumBootstrapArguments(int numBootstrapArguments) 
	{
		this.numBootstrapArguments = numBootstrapArguments;
	}
	
	public int[] getBootstrapArguments() 
	{
		return bootstrapArguments;
	}
	
	public void setBootstrapArguments(int[] bootstrapArguments) 
	{
		this.bootstrapArguments = bootstrapArguments;
	}
	
	public ConstantPoolTypeMethodHandle getBootstrapMethodRefObject() 
	{
		return bootstrapMethodRefObject;
	}

	public void setBootstrapMethodRefObject(ConstantPoolTypeMethodHandle bootstrapMethodRefObject)
	{
		this.bootstrapMethodRefObject = bootstrapMethodRefObject;
	}

	public ArrayList<AbstractConstantPoolType> getBootstrapArgumentsObjects() 
	{
		return bootstrapArgumentsObjects;
	}

	public void setBootstrapArgumentsObjects(ArrayList<AbstractConstantPoolType> bootstrapArgumentsObjects) 
	{
		this.bootstrapArgumentsObjects = bootstrapArgumentsObjects;
	}
	
	public static BootstrapMethodEntry deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{
		BootstrapMethodEntry bootstrapMethodEntry = new BootstrapMethodEntry();
		
		bootstrapMethodEntry.setBootstrapMethodRefIndex(dis.readUnsignedShort());
		bootstrapMethodEntry.setNumBootstrapArguments(dis.readUnsignedShort());
		
		for(int i = 0; i < bootstrapMethodEntry.getNumBootstrapArguments(); i++)
		{
			bootstrapMethodEntry.bootstrapArguments[i] = dis.readUnsignedShort();
		}
		
		decoupleFromIndices(bootstrapMethodEntry, constantPool);
		
		return bootstrapMethodEntry;
    }
	
	public static void decoupleFromIndices(BootstrapMethodEntry bootstrapMethodEntry, ConstantPool constantPool)
	{
		bootstrapMethodEntry.setBootstrapMethodRefObject((ConstantPoolTypeMethodHandle)ConstantPool.getConstantPoolTypeByIndex(constantPool, bootstrapMethodEntry.getBootstrapMethodRefIndex()));
		bootstrapMethodEntry.setBootstrapMethodRefIndex(0);
		
		for(int i = 0; i < bootstrapMethodEntry.getNumBootstrapArguments(); i++)
		{
			bootstrapMethodEntry.getBootstrapArgumentsObjects().add(ConstantPool.getConstantPoolTypeByIndex(constantPool, bootstrapMethodEntry.getBootstrapArguments()[i]));
			bootstrapMethodEntry.getBootstrapArguments()[i] = 0;
		}
	}
	
	private static void coupleToIndices(SerCtx ctx, BootstrapMethodEntry bootstrapMethodEntry)
	{
		/* retrieve constant pool */
		ConstantPool cp = ctx.getConstantPool();
		
		/* get objects */
		ConstantPoolTypeMethodHandle methodRef = bootstrapMethodEntry.getBootstrapMethodRefObject();
		
	    short methodRefIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, methodRef);
	    bootstrapMethodEntry.setBootstrapMethodRefIndex(methodRefIndex);
	    
		for(int i = 0; i < bootstrapMethodEntry.getNumBootstrapArguments(); i++)
		{
			short currentBootstrapArgument = ConstantPool.getIndexFromConstantPoolEntry(cp, bootstrapMethodEntry.getBootstrapArgumentsObjects().get(i));
			bootstrapMethodEntry.getBootstrapArguments()[i] = currentBootstrapArgument;
		}
	}
	
	public static byte[] serialize(SerCtx ctx, BootstrapMethodEntry bootstrapMethodEntry) throws IOException
	{
		/* couple indices to constant pool indices */
		coupleToIndices(ctx, bootstrapMethodEntry);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(bootstrapMethodEntry.getBootstrapMethodRefIndex(), Short.class));
		
		int num = bootstrapMethodEntry.getNumBootstrapArguments() & 0xffff;
		for(int i = 0; i < num; i++)
		{
			baos.write(PNC.toByteArray(bootstrapMethodEntry.getBootstrapArguments()[i], Short.class));
		}
		
		return baos.toByteArray();
	}
	
	public boolean equals(BootstrapMethodEntry entry)
	{
		if(entry == null)
		{
			return false;
		}
		
		if(!getBootstrapMethodRefObject().equals(entry.getBootstrapMethodRefObject()))
		{
			return false;
		}
		
		if(getBootstrapArgumentsObjects().size() != entry.getBootstrapArgumentsObjects().size())
		{
			return false;
		}
		
		for(int i = 0; i < getBootstrapArgumentsObjects().size(); i++)
		{
			AbstractConstantPoolType current1 = getBootstrapArgumentsObjects().get(i);
			AbstractConstantPoolType current2 = entry.getBootstrapArgumentsObjects().get(i); 
			
			if(!current1.equals(current2))
			{
				return false;
			}
		}
		
		return true;
	}
}
