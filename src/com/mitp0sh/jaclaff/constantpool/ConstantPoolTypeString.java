package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeStringDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeString extends AbstractConstantPoolType
{
	private int                  	stringIndex = 0;
	private ConstantPoolTypeUtf8   stringObject = null;

	public ConstantPoolTypeString()
	{
		super.setConstant_pool_string_representation("CONSTANT_String");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING);
	}

	public int getStringIndex()
	{
		return this.stringIndex;
	}

	public void setStringIndex(int index) 
	{
		this.stringIndex = index;
	}
	
	public ConstantPoolTypeUtf8 getStringObject() 
	{
		return stringObject;
	}

	public void setStringObject(ConstantPoolTypeUtf8 object)
	{
		this.stringObject = object;
		
		if(object != null)
		{
			this.addReference(object);
			this.setStringIndex(0);
		}
	}
	
	public static ConstantPoolTypeString deserialize(DataInputStream dis) throws IOException,
	                                                                             InvalidConstantPoolTypeStringDeserializationException
	{
		ConstantPoolTypeString cptString = new ConstantPoolTypeString();
		
		cptString.setStringIndex(dis.readUnsignedShort());
		
		return cptString;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, ConstantPoolTypeString cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int stringIndex = cpt.getStringIndex();
		AbstractConstantPoolType acptClass = ConstantPool.cpeByIndex(cp, stringIndex);
		ConstantPoolTypeUtf8 stringObject = (ConstantPoolTypeUtf8)acptClass;
		cpt.setStringObject(stringObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, ConstantPoolTypeString cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		ConstantPoolTypeUtf8 stringObject = cpt.getStringObject();
		int stringIndex = ConstantPool.indexByCPE(cp, stringObject);
		cpt.setStringIndex(stringIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeString elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getStringIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeString clone()
	{
		/* create new empty instance */
		ConstantPoolTypeString clone = (ConstantPoolTypeString)super.clone();
		
		/* fill with data */		
		clone.setStringObject(this.getStringObject());
		
		return clone;
	}
	
//	@Override
//	public boolean equals(Object obj)
//	{
//		try
//		{
//			ConstantPoolTypeString cpt = (ConstantPoolTypeString)obj;
//			return cpt.stringObject.equals(this.stringObject);
//		}
//		catch(NullPointerException e){}
//		catch(ClassCastException e){}
//		
//		return false;
//	}
}
