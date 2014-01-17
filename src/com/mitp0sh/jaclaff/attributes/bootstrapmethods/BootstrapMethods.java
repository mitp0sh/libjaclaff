package com.mitp0sh.jaclaff.attributes.bootstrapmethods;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeInvokeDynamic;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class BootstrapMethods 
{
	private BootstrapMethodEntry[] bootstrapMethodEntries = new BootstrapMethodEntry[0];	

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
	
	public static BootstrapMethods deserialize(DesCtx ctx, int num) throws IOException
	{
		ConstantPool cp = ctx.getConstantPool();
		
		BootstrapMethods bootstrapMethods = new BootstrapMethods(num);
		for(int i = 0; i < num; i++)
		{
			bootstrapMethods.bootstrapMethodEntries[i] = BootstrapMethodEntry.deserialize(ctx);
		}
		
		/* late decouple constant pool type invoke dynamics !!! */
		for(int i = 0; i < cp.getConstantPoolCount(); i++)
		{
			try
			{
				ConstantPoolTypeInvokeDynamic cptid = (ConstantPoolTypeInvokeDynamic)cp.getConstantPool().get(0);
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
