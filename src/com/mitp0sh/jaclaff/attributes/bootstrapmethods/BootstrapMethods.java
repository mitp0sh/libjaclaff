package com.mitp0sh.jaclaff.attributes.bootstrapmethods;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeInvokeDynamic;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class BootstrapMethods 
{
	private BootstrapMethodEntry[] bootstrapMethodEntries = null;	

	public BootstrapMethods(int numberOfBootstrapMethods)
	{	
		bootstrapMethodEntries = new BootstrapMethodEntry[numberOfBootstrapMethods];
	}
	
	public int getNumberOfBootstrapMethodEntries()
	{
		return bootstrapMethodEntries.length;
	}

	public BootstrapMethodEntry[] getBootstrapMethodEntries() 
	{
		return bootstrapMethodEntries;
	}
	
	public static BootstrapMethods deserialize(DataInputStream dis, int numberOfBootstrapMethodEntries, ConstantPool cp) throws IOException
	{
		BootstrapMethods bootstrapMethods = new BootstrapMethods(numberOfBootstrapMethodEntries);
		for(int i = 0; i < numberOfBootstrapMethodEntries; i++)
		{
			bootstrapMethods.bootstrapMethodEntries[i] = BootstrapMethodEntry.deserialize(dis, cp);
		}
		
		/* late decouple constant pool type invoke dynamics !!! */
		for(int i = 1; i <= cp.getConstantPoolCount(); i++)
		{
			try
			{
				ConstantPoolTypeInvokeDynamic cptid = (ConstantPoolTypeInvokeDynamic)cp.getConstantPool()[i];
				ConstantPoolTypeInvokeDynamic.decoupleConstantPoolTypeInvokeDynamicEntries(cptid, bootstrapMethods.bootstrapMethodEntries, cp);
			}
			catch(ClassCastException e)
			{
				continue;
			}
		}
		
		return bootstrapMethods;
    }
	
	public static byte[] serialize(SerCtx ctx, BootstrapMethods bootstrapMethods) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		for(int i = 0; i < bootstrapMethods.getNumberOfBootstrapMethodEntries(); i++)
		{
			baos.write(BootstrapMethodEntry.serialize(ctx, bootstrapMethods.getBootstrapMethodEntries()[i]));
		}
		
		return baos.toByteArray();
	}
}
