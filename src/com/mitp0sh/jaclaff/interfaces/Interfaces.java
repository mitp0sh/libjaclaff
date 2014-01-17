package com.mitp0sh.jaclaff.interfaces;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class Interfaces extends AbstractReference
{
	private ArrayList<InterfacesEntry> interfaces = new ArrayList<InterfacesEntry>();
	
	public int getNumberOfInterfaces()
	{
		return interfaces.size();
	}
	
	public ArrayList<InterfacesEntry> getInterfaces()
	{
		return interfaces;
	}

	public static Interfaces deserialize(DesCtx ctx, int num) throws IOException
    {
		Interfaces interfaces = new Interfaces();
		for(int i = 0; i < num; i++)
		{
			InterfacesEntry current = InterfacesEntry.deserialize(ctx);
			interfaces.getInterfaces().add(current);
		}
		
		return interfaces;
    }
	
	public static byte[] serialize(SerCtx ctx, Interfaces interfaces) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Iterator<InterfacesEntry> iter = interfaces.getInterfaces().iterator();
		while(iter.hasNext())
		{
			baos.write(InterfacesEntry.serialize(ctx, iter.next()));
		}
		
		return baos.toByteArray();
	}
}
