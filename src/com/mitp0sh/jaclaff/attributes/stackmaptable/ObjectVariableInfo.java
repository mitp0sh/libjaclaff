package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ObjectVariableInfo extends AbstractVariableInfo
{
	private int                            cpoolIndex = 0;
	private AbstractConstantPoolType cpoolIndexObject = null;

	public ObjectVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_OBJECT_TAG);
		setVariable_info_string_representation("Object_variable_info");
	}
	
	public int getCPoolIndex()
	{
		return cpoolIndex;
	}

	public void setCPoolIndex(int cpoolIndex) 
	{
		this.cpoolIndex = cpoolIndex;
	}

	public AbstractConstantPoolType getCPoolIndexObject() 
	{
		return cpoolIndexObject;
	}

	public void setCPoolIndexObject(AbstractConstantPoolType object) 
	{
		this.cpoolIndexObject = object;
		
		if(object != null)
		{
			this.setCPoolIndex(0);
			this.addReference(object);
		}
	}
	
	public static ObjectVariableInfo deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		ObjectVariableInfo objectVariableInfo = new ObjectVariableInfo();
		
		objectVariableInfo.setCPoolIndex(((short)dis.readUnsignedShort()));
		
		decoupleFromIndices(objectVariableInfo, constantPool);
		
		return objectVariableInfo;
    }
	
	public static void decoupleFromIndices(ObjectVariableInfo objectVariableInfo, ConstantPool constantPool)
	{
		objectVariableInfo.setCPoolIndexObject(((ConstantPoolTypeClass)ConstantPool.cpeByIndex(constantPool, objectVariableInfo.getCPoolIndex())));
		objectVariableInfo.setCPoolIndex((short)0);
	}
	
	public static void coupleToIndices(SerCtx ctx, ObjectVariableInfo objectVariableInfo)
	{
		int cpoolIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), objectVariableInfo.getCPoolIndexObject());
		objectVariableInfo.setCPoolIndex(cpoolIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ObjectVariableInfo objectVariableInfo) throws IOException
	{	
		coupleToIndices(ctx, objectVariableInfo);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(objectVariableInfo.getCPoolIndex(), Short.class));
		
		return baos.toByteArray();
	}
}

// The Object_variable_info type indicates that the location contains an 
// instance of the class represented by the CONSTANT_Class_info (§4.4.1) 
// structure found in the constant_pool table at the index given by 
// cpool_index.

// Object_variable_info {
//     u1 tag = ITEM_Object; /* 7 */
//     u2 cpool_index;
// }
