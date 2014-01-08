package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.bootstrapmethods.BootstrapMethods;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class AttributeBootstrapMethods extends AbstractAttribute
{
	private BootstrapMethods bootstrapMethods = null;

	public AttributeBootstrapMethods(int numBootstrapMethods) 
	{
		super();
	}
	
	public int getNumBootstrapMethods() 
	{
		return bootstrapMethods.getNumberOfBootstrapMethodEntries();
	}
	
	public BootstrapMethods getBootstrapMethods() 
	{
		return bootstrapMethods;
	}
	
	public void setBootstrapMethods(BootstrapMethods bootstrapMethods) 
	{
		this.bootstrapMethods = bootstrapMethods;
	}
	
	public static AttributeBootstrapMethods deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
	{				
		int num = dis.readUnsignedShort();
		AttributeBootstrapMethods attribute = new AttributeBootstrapMethods(num);
	
		attribute.setBootstrapMethods(BootstrapMethods.deserialize(dis, attribute.getNumBootstrapMethods(), constantPool));
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeBootstrapMethods attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		baos.write(PNC.toByteArray(attribute.getNumBootstrapMethods(), Short.class));
		
		baos.write(BootstrapMethods.serialize(ctx, attribute.getBootstrapMethods()));
		
		return baos.toByteArray();
	}
}
