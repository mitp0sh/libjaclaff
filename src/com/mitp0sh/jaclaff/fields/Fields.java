package com.mitp0sh.jaclaff.fields;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;


public class Fields
{	
	private FieldEntry[] fields = new FieldEntry[0];	
	
	public Fields(short fieldsCount)
	{
		this.fields = new FieldEntry[fieldsCount];
	}
	
	public FieldEntry[] getFields()
	{
		return fields;
	}
	
	public static Fields deserialize(DataInputStream dis, short fieldsCount, ConstantPool constantPool) throws IOException
    {	
		Fields fields = new Fields(fieldsCount);
		
		for(int i = 0; i < fieldsCount; i++)
		{
			fields.getFields()[i] = FieldEntry.deserialize(dis, constantPool);
		}
		
		return fields;
    }
	
	public static byte[] serialize(Fields fields, ConstantPool constantPool) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 0; i < fields.getFields().length; i++)
		{
			baos.write(FieldEntry.serialize(fields.getFields()[i], constantPool));
		}
		
		return baos.toByteArray();
	}
}
