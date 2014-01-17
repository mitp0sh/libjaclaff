package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeNameAndType;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeEnclosingMethod extends AbstractAttribute
{
	private int                           classIndex = 0;
	private int                          methodIndex = 0;
	
	private ConstantPoolTypeClass        classObject = null;
	private ConstantPoolTypeNameAndType methodObject = null;

	public int getClassIndex() 
	{
		return classIndex;
	}
	
	public void setClassIndex(int classIndex) 
	{
		this.classIndex = classIndex;
	}
	
	public int getMethodIndex() 
	{
		return methodIndex;
	}
	
	public void setMethodIndex(int methodIndex) 
	{
		this.methodIndex = methodIndex;
	}
	
	public ConstantPoolTypeClass getClassObject() 
	{
		return classObject;
	}

	public void setClassObject(ConstantPoolTypeClass object) 
	{
		this.classObject = object;
		
		if(object != null)
		{
			this.setClassIndex(0);
			this.addReference(object);
		}
	}

	public ConstantPoolTypeNameAndType getMethodObject() 
	{
		return methodObject;
	}

	public void setMethodObject(ConstantPoolTypeNameAndType object) 
	{
		this.methodObject = object;
		
		if(object != null)
		{
			this.setMethodIndex(0);
			this.addReference(object);
		}
	}
	
	public static AttributeEnclosingMethod deserialize(DesCtx ctx) throws IOException
    {				
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeEnclosingMethod attribute = new AttributeEnclosingMethod();
		
		attribute.setClassIndex(dis.readUnsignedShort());
		attribute.setMethodIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(ctx, attribute);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(DesCtx ctx, AttributeEnclosingMethod attribute)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		ConstantPoolTypeClass classObject = null;
		classObject = (ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(cp, attribute.classIndex);
		attribute.setClassObject(classObject);
		
		ConstantPoolTypeNameAndType methodObject = null;
		methodObject = (ConstantPoolTypeNameAndType)ConstantPool.getConstantPoolTypeByIndex(cp, attribute.methodIndex);
		attribute.setMethodObject(methodObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, AttributeEnclosingMethod attribute)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int classIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, attribute.getClassObject());
		attribute.setClassIndex(classIndex);
		
		int methodIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, attribute.getMethodObject());
		attribute.setMethodIndex(methodIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, AttributeEnclosingMethod attribute) throws IOException
	{
		coupleToIndices(ctx, attribute);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getClassIndex(),  Short.class));
		baos.write(PNC.toByteArray(attribute.getMethodIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
