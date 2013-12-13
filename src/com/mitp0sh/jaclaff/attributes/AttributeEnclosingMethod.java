package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeNameAndType;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeEnclosingMethod extends AbstractAttribute
{
	private short                         classIndex = 0;
	private ConstantPoolTypeClass        classObject = null;
	private short                        methodIndex = 0;
	private ConstantPoolTypeNameAndType methodObject = null;

	public short getClassIndex() 
	{
		return classIndex;
	}
	
	public void setClassIndex(short classIndex) 
	{
		this.classIndex = classIndex;
	}
	
	public short getMethodIndex() 
	{
		return methodIndex;
	}
	
	public void setMethodIndex(short methodIndex) 
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
	
	public static void decoupleFromIndices(AttributeEnclosingMethod aem, ConstantPool constantPool)
	{
		aem.setClassObject((ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, aem.classIndex));
		aem.setClassIndex((short)0);
		aem.setMethodObject((ConstantPoolTypeNameAndType)ConstantPool.getConstantPoolTypeByIndex(constantPool, aem.methodIndex));
		aem.setMethodIndex((short)0);
	}
	
	public static AttributeEnclosingMethod deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {				
		AttributeEnclosingMethod attribute = new AttributeEnclosingMethod();
		
		attribute.setClassIndex((short)dis.readUnsignedShort());
		attribute.setMethodIndex((short)dis.readUnsignedShort());
		
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static byte[] serialize(AttributeEnclosingMethod attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getClassIndex(), Short.class));
		baos.write(PNC.toByteArray(attribute.getMethodIndex(), Short.class));
		
		return baos.toByteArray();
	}
}
