package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.innerclasses.Classes;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeInnerClasses extends AbstractAttribute
{
	private Classes classes = null;

	public int getNumberOfClasses() 
	{
		if(classes != null)
		{
			return classes.getNumberOfClasses();
		}
		return 0;
	}

	public Classes getClasses() 
	{
		return classes;
	}

	public void setClasses(Classes classes) 
	{
		this.classes = classes;
	}

	public static AttributeInnerClasses deserialize(DesCtx ctx) throws IOException
	{				
		DataInputStream dis = ctx.getDataInputStream();
		
		int num = dis.readUnsignedShort();
		AttributeInnerClasses attribute = new AttributeInnerClasses();
		attribute.setClasses(Classes.deserialize(ctx, num));
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeInnerClasses attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		int num = attribute.getNumberOfClasses();
		baos.write(PNC.toByteArray(num, Short.class));
		
		baos.write(Classes.serialize(ctx, attribute.getClasses()));
		
		return baos.toByteArray();
	}
}
