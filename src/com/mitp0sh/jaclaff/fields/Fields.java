package com.mitp0sh.jaclaff.fields;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class Fields
{	
	private FieldEntry[] fields = new FieldEntry[0];	
	
	public Fields(short fieldsCount)
	{
		this.fields = new FieldEntry[fieldsCount & 0xFFFF];
	}
	
	public FieldEntry[] getFields()
	{
		return fields;
	}
	
	public static Fields deserialize(DesCtx ctx, short fieldsCount) throws IOException
    {	
		Fields fields = new Fields(fieldsCount);
		
		for(int i = 0; i < fieldsCount; i++)
		{
			fields.getFields()[i] = FieldEntry.deserialize(ctx);
		}
		
		return fields;
    }
	
	public static byte[] serialize(SerCtx ctx, Fields fields) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < fields.getFields().length; i++)
		{
			baos.write(FieldEntry.serialize(ctx, fields.getFields()[i]));
		}
		
		return baos.toByteArray();
	}
}
