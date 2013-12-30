package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.util.PNC;


public abstract class AbstractAttribute
{	
	public static final String attributeConstantValue                                 = "ConstantValue";
	public static final String attributeCode                                          = "Code";
	public static final String attributeExceptions                                    = "Exceptions";
	public static final String attributeInnerClasses                                  = "InnerClasses";
	public static final String attributeEnclosingMethod                               = "EnclosingMethod";
	public static final String attributeSynthetic                                     = "Synthetic";
	public static final String attributeSignature                                     = "Signature";
	public static final String attributeSourceFile                                    = "SourceFile";
	public static final String attributeSourceDebugExtension                          = "SourceDebugExtension";
	public static final String attributeLineNumberTable                               = "LineNumberTable";
	public static final String attributeLocalVariableTable                            = "LocalVariableTable";
	public static final String attributeLocalVariableTypeTable                        = "LocalVariableTypeTable";
	public static final String attributeDeprecated                                    = "Deprecated";	
	public static final String attributeRuntimeVisibleAnnotations                     = "RuntimeVisibleAnnotations";
	public static final String attributeRuntimeInvisibleAnnotations                   = "RuntimeInvisibleAnnotations";
	public static final String attributeRuntimeVisibleParameterAnnotations            = "RuntimeVisibleParameterAnnotations";
	public static final String attributeRuntimeInvisibleParameterAnnotations          = "RuntimeInvisibleParameterAnnotations";
	public static final String attributeAnnotationDefault                             = "AnnotationDefault";
	public static final String attributeBridge                                        = "Bridge";
		
	private short                  attributeNameIndex = 0;
	private ConstantPoolTypeUtf8  attributeNameObject = null;
	private int                      attributesLength = 0;

	public short getAttributeNameIndex() 
	{
		return attributeNameIndex;
	}

	public void setAttributeNameIndex(short attributeNameIndex) 
	{		
		this.attributeNameIndex = attributeNameIndex;
	}
	
	public ConstantPoolTypeUtf8 getAttributeNameObject()
	{
		return attributeNameObject;
	}

	public void setAttributeNameObject(ConstantPoolTypeUtf8 attributeNameObject)
	{
		this.attributeNameObject = attributeNameObject;
	}

	public int getAttributeLength() 
	{
		return attributesLength;
	}

	public void setAttributeLength(int attributesLength) 
	{
		this.attributesLength = attributesLength;
	}
	
	public static AbstractAttribute deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		short    attributeNameIndex = (short)(dis.readUnsignedShort());
		int         attributeLength = (int)(dis.readInt());
		
		String        attributeName = constantPool.getConstantTypeUtf8Bytes(attributeNameIndex);
		AbstractAttribute attribute = null;
		
		if(attributeName.equals(attributeConstantValue))
		{
			attribute = AttributeConstantValue.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeCode))
		{
			attribute = AttributeCode.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeExceptions))
		{
			attribute = AttributeExceptions.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeInnerClasses))
		{
			attribute = AttributeInnerClasses.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeEnclosingMethod))
		{
			attribute = AttributeEnclosingMethod.deserialize(dis, constantPool);
		}		
		else
		if(attributeName.equals(attributeSynthetic))
		{
			attribute = AttributeSynthetic.deserialize(dis);
		}
		else
		if(attributeName.equals(attributeSignature))
		{
			attribute = AttributeSignature.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeSourceFile))
		{
			attribute = AttributeSourceFile.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeSourceDebugExtension))
		{
			attribute = AttributeSourceDebugExtension.deserialize(dis);
		}
		else
		if(attributeName.equals(attributeLineNumberTable))
		{
			attribute = AttributeLineNumberTable.deserialize(dis);
		}
		else
		if(attributeName.equals(attributeLocalVariableTable))
		{
			attribute = AttributeLocalVariableTable.deserialize(dis);
		}
		else
		if(attributeName.equals(attributeLocalVariableTypeTable))
		{
			attribute = AttributeLocalVariableTypeTable.deserialize(dis);
		}
		else
		if(attributeName.equals(attributeDeprecated))
		{
			attribute = AttributeDeprecated.deserialize(dis);
		}
		else
		if(attributeName.equals(attributeRuntimeVisibleAnnotations))
		{
			attribute = AttributeRuntimeVisibleAnnotations.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeRuntimeInvisibleAnnotations))
		{
			attribute = AttributeRuntimeInvisibleAnnotations.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeRuntimeVisibleParameterAnnotations))
		{
			attribute = AttributeRuntimeVisibleParameterAnnotations.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeRuntimeInvisibleParameterAnnotations))
		{
			attribute = AttributeRuntimeInvisibleParameterAnnotations.deserialize(dis, constantPool);
		}
		else
		if(attributeName.equals(attributeAnnotationDefault))
		{
			attribute = AttributeAnnotationDefault.deserialize(dis, constantPool);
		}		
		else
		if(attributeName.equals(attributeBridge))
		{
			attribute = AttributeBridge.deserialize(dis);
		}
		else
		{
			System.out.println();
			System.out.println("Deserialization - Contains custom attribute!");
			System.out.println();
			attribute = AttributeCustom.deserialize(dis);			
		}
		
		attribute.setAttributeNameIndex(attributeNameIndex);
		attribute.setAttributeLength(attributeLength);
		
		/* decouple indices from attribute */
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(AbstractAttribute attribute, ConstantPool constantPool)
	{
		attribute.setAttributeNameObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, attribute.getAttributeNameIndex()));
	}
	
	public static byte[] serialize(AbstractAttribute attribute, ConstantPool constantPool) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		short   attributeNameIndex = attribute.getAttributeNameIndex();
		String       attributeName = constantPool.getConstantTypeUtf8Bytes(attributeNameIndex);
		
		baos.write(PNC.toByteArray(attribute.getAttributeNameIndex(), Short.class));
		baos.write(PNC.toByteArray(attribute.getAttributeLength(), Integer.class));
		
		if(attributeName.equals(attributeConstantValue))
		{
			baos.write(AttributeConstantValue.serialize((AttributeConstantValue)attribute));
		}
		else
		if(attributeName.equals(attributeCode))
		{
			baos.write(AttributeCode.serialize((AttributeCode)attribute, constantPool));
		}
		else
		if(attributeName.equals(attributeExceptions))
		{
			baos.write(AttributeExceptions.serialize((AttributeExceptions)attribute));
		}
		else
		if(attributeName.equals(attributeInnerClasses))
		{
			baos.write(AttributeInnerClasses.serialize((AttributeInnerClasses)attribute));
		}
		else
		if(attributeName.equals(attributeEnclosingMethod))
		{
			baos.write(AttributeEnclosingMethod.serialize((AttributeEnclosingMethod)attribute));
		}		
		else
		if(attributeName.equals(attributeSynthetic))
		{
			/* nothing to do here */
		}
		else
		if(attributeName.equals(attributeSignature))
		{
			baos.write(AttributeSignature.serialize((AttributeSignature)attribute));
		}
		else
		if(attributeName.equals(attributeSourceFile))
		{
			baos.write(AttributeSourceFile.serialize((AttributeSourceFile)attribute));
		}
		else
		if(attributeName.equals(attributeSourceDebugExtension))
		{
			baos.write(AttributeSourceDebugExtension.serialize((AttributeSourceDebugExtension)attribute));
		}
		else
		if(attributeName.equals(attributeLineNumberTable))
		{
			baos.write(AttributeLineNumberTable.serialize((AttributeLineNumberTable)attribute));
		}
		else
		if(attributeName.equals(attributeLocalVariableTable))
		{
			baos.write(AttributeLocalVariableTable.serialize((AttributeLocalVariableTable)attribute));
		}
		else
		if(attributeName.equals(attributeLocalVariableTypeTable))
		{
			baos.write(AttributeLocalVariableTypeTable.serialize((AttributeLocalVariableTypeTable)attribute));
		}
		else
		if(attributeName.equals(attributeDeprecated))
		{
			/* nothing to do here */
		}
		else
		if(attributeName.equals(attributeRuntimeVisibleAnnotations))
		{
			baos.write(AttributeRuntimeVisibleAnnotations.serialize((AttributeRuntimeVisibleAnnotations)attribute));
		}
		else
		if(attributeName.equals(attributeRuntimeInvisibleAnnotations))
		{
			baos.write(AttributeRuntimeInvisibleAnnotations.serialize((AttributeRuntimeInvisibleAnnotations)attribute));
		}
		else
		if(attributeName.equals(attributeRuntimeVisibleParameterAnnotations))
		{
			baos.write(AttributeRuntimeVisibleParameterAnnotations.serialize((AttributeRuntimeVisibleParameterAnnotations)attribute));
		}
		else
		if(attributeName.equals(attributeRuntimeInvisibleParameterAnnotations))
		{
			baos.write(AttributeRuntimeInvisibleParameterAnnotations.serialize((AttributeRuntimeInvisibleParameterAnnotations)attribute));
		}
		else
		if(attributeName.equals(attributeAnnotationDefault))
		{
			baos.write(AttributeAnnotationDefault.serialize((AttributeAnnotationDefault)attribute));
		}		
		else
		if(attributeName.equals(attributeBridge))
		{
			baos.write(AttributeBridge.serialize((AttributeBridge)attribute));
		}
		else
		{
			System.out.println();
			System.out.println("Serialization - Contains custom attribute!");
			System.out.println();
			baos.write(AttributeCustom.serialize((AttributeCustom)attribute));
		}
		
		return baos.toByteArray();
	}
}
