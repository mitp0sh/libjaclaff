package com.mitp0sh.jaclaff.attributes.bootstrapmethods;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeMethodHandle;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class BootstrapMethodEntry extends AbstractReference
{
	private int bootstrapMethodRefIndex = 0;
	private int[]    bootstrapArguments = new int[0];
	
	private ConstantPoolTypeMethodHandle         bootstrapMethodRefObject = null;
	private ArrayList<AbstractConstantPoolType> bootstrapArgumentsObjects = new ArrayList<AbstractConstantPoolType>();
	
	public BootstrapMethodEntry(int num)
	{
		this.bootstrapArguments = new int[num];
	}
	
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
		return bootstrapArguments.length;
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

	public void setBootstrapMethodRefObject(ConstantPoolTypeMethodHandle object)
	{
		this.bootstrapMethodRefObject = object;
		
		if(object != null)
		{
			this.setBootstrapMethodRefIndex(0);
			this.addReference(object);
		}
	}

	public ArrayList<AbstractConstantPoolType> getBootstrapArgumentsObjects() 
	{
		return bootstrapArgumentsObjects;
	}

	public void setBootstrapArgumentsObjects(ArrayList<AbstractConstantPoolType> bootstrapArgumentsObjects) 
	{
		this.bootstrapArgumentsObjects = bootstrapArgumentsObjects;
	}
	
	public static BootstrapMethodEntry deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		int bootstrapMethodRefIndex = dis.readUnsignedShort();
		int                     num = dis.readUnsignedShort();
		
		BootstrapMethodEntry bootstrapMethodEntry = new BootstrapMethodEntry(num);
		bootstrapMethodEntry.setBootstrapMethodRefIndex(bootstrapMethodRefIndex);
		
		for(int i = 0; i < bootstrapMethodEntry.getNumBootstrapArguments(); i++)
		{
			bootstrapMethodEntry.bootstrapArguments[i] = dis.readUnsignedShort();
		}
		
		decoupleFromIndices(ctx, bootstrapMethodEntry);
		
		return bootstrapMethodEntry;
    }
	
	public static void decoupleFromIndices(DesCtx ctx, BootstrapMethodEntry bootstrapMethodEntry)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		bootstrapMethodEntry.setBootstrapMethodRefObject((ConstantPoolTypeMethodHandle)ConstantPool.cpeByIndex(cp, bootstrapMethodEntry.getBootstrapMethodRefIndex()));
		bootstrapMethodEntry.setBootstrapMethodRefIndex(0);
		
		int num = bootstrapMethodEntry.getNumBootstrapArguments();
		for(int i = 0; i < num; i++)
		{
			AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, bootstrapMethodEntry.getBootstrapArguments()[i]);
			bootstrapMethodEntry.getBootstrapArgumentsObjects().add(acpt);
			
			// TODO - Find a proper place to do that - refactor whole class, maybe even split class !!!
			if(acpt != null)
			{
				bootstrapMethodEntry.getBootstrapArguments()[i] = 0;
				bootstrapMethodEntry.addReference(acpt);
			}
		}
	}
	
	private static void coupleToIndices(SerCtx ctx, BootstrapMethodEntry bootstrapMethodEntry)
	{
		/* retrieve constant pool */
		ConstantPool cp = ctx.getConstantPool();
		
		/* get objects */
		ConstantPoolTypeMethodHandle methodRef = bootstrapMethodEntry.getBootstrapMethodRefObject();
		
	    int methodRefIndex = ConstantPool.indexByCPE(cp, methodRef);
	    bootstrapMethodEntry.setBootstrapMethodRefIndex(methodRefIndex);
	    
		for(int i = 0; i < bootstrapMethodEntry.getNumBootstrapArguments(); i++)
		{
			int currentBootstrapArgument = ConstantPool.indexByCPE(cp, bootstrapMethodEntry.getBootstrapArgumentsObjects().get(i));
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
