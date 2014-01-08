package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeNameAndType;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeEnclosingMethod extends AbstractAttribute
{
	private int                           classIndex = 0;
	private ConstantPoolTypeClass        classObject = null;
	private int                          methodIndex = 0;
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

	public void setClassObject(ConstantPoolTypeClass classObject) 
	{
		this.classObject = classObject;
	}

	public ConstantPoolTypeNameAndType getMethodObject() 
	{
		return methodObject;
	}

	public void setMethodObject(ConstantPoolTypeNameAndType methodObject) 
	{
		this.methodObject = methodObject;
	}
	
	public static AttributeEnclosingMethod deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {				
		AttributeEnclosingMethod attribute = new AttributeEnclosingMethod();
		
		attribute.setClassIndex(dis.readUnsignedShort());
		attribute.setMethodIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(AttributeEnclosingMethod aem, ConstantPool constantPool)
	{
		aem.setClassObject((ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, aem.classIndex));
		aem.setClassIndex(0);
		aem.setMethodObject((ConstantPoolTypeNameAndType)ConstantPool.getConstantPoolTypeByIndex(constantPool, aem.methodIndex));
		aem.setMethodIndex(0);
	}
	
	public static void coupleToIndices(SerCtx ctx, AttributeEnclosingMethod attribute)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		short classIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, attribute.getClassObject());
		attribute.setClassIndex(classIndex);
		
		short methodIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, attribute.getMethodObject());
		attribute.setMethodIndex(methodIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, AttributeEnclosingMethod attribute) throws IOException
	{
		coupleToIndices(ctx, attribute);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getClassIndex(), Short.class));
		baos.write(PNC.toByteArray(attribute.getMethodIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
