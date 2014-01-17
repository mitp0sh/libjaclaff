package com.mitp0sh.jaclaff.fields;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;


public class Fields
{	
	private ArrayList<FieldEntry> fields = new ArrayList<FieldEntry>();	
	
	public int getNumberOfFields()
	{
		return fields.size();
	}
	
	public ArrayList<FieldEntry> getFields()
	{
		return fields;
	}
	
	public static Fields deserialize(DesCtx ctx, int fieldsCount) throws IOException
    {	
		Fields fields = new Fields();
		
		for(int i = 0; i < fieldsCount; i++)
		{
			fields.getFields().add(FieldEntry.deserialize(ctx));
		}
		
		return fields;
    }
	
	public static byte[] serialize(SerCtx ctx, Fields fields) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < fields.getFields().size(); i++)
		{
			baos.write(FieldEntry.serialize(ctx, fields.getFields().get(i)));
		}
		
		return baos.toByteArray();
	}
}
