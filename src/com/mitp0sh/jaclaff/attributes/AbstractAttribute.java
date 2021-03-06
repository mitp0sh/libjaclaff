package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public abstract class AbstractAttribute extends AbstractReference
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
	public static final String attributeStackMapTable                                 = "StackMapTable";
	public static final String attributeBootstrapMethods                              = "BootstrapMethods";
	public static final String attributeVarargs                                       = "Varargs";
	
	private int                    attributeNameIndex = 0;
	private ConstantPoolTypeUtf8  attributeNameObject = null;
	private long                     attributesLength = 0;

	public int getAttributeNameIndex() 
	{
		return attributeNameIndex;
	}

	public void setAttributeNameIndex(int attributeNameIndex) 
	{		
		this.attributeNameIndex = attributeNameIndex;
	}
	
	public ConstantPoolTypeUtf8 getAttributeNameObject()
	{
		return attributeNameObject;
	}

	public void setAttributeNameObject(ConstantPoolTypeUtf8 object)
	{
		this.attributeNameObject = object;
		
		if(object != null)
		{
			this.setAttributeNameIndex(0);
			this.addReference(object);
		}
	}

	public long getAttributeLength() 
	{
		return attributesLength;
	}

	public void setAttributeLength(long attributesLength) 
	{
		this.attributesLength = attributesLength;
	}
	
	public static AbstractAttribute deserialize(DesCtx ctx, Object reference0) throws IOException
    {
		ConstantPool constantPool = ctx.getConstantPool();
		DataInputStream       dis = ctx.getDataInputStream();		
		int    attributeNameIndex = dis.readUnsignedShort();
		long      attributeLength = dis.readInt();
		
		String        attributeName = constantPool.getConstantTypeUtf8Bytes(attributeNameIndex);
		AbstractAttribute attribute = null;
		
		if(attributeName.equals(attributeConstantValue))
		{
			attribute = AttributeConstantValue.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeCode))
		{
			attribute = AttributeCode.deserialize(ctx, reference0);
		}
		else
		if(attributeName.equals(attributeExceptions))
		{
			attribute = AttributeExceptions.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeInnerClasses))
		{
			attribute = AttributeInnerClasses.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeEnclosingMethod))
		{
			attribute = AttributeEnclosingMethod.deserialize(ctx);
		}		
		else
		if(attributeName.equals(attributeSynthetic))
		{
			attribute = AttributeSynthetic.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeSignature))
		{
			attribute = AttributeSignature.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeSourceFile))
		{
			attribute = AttributeSourceFile.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeSourceDebugExtension)) // not tested !!!!!!
		{
			attribute = AttributeSourceDebugExtension.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeLineNumberTable))
		{
			attribute = AttributeLineNumberTable.deserialize(ctx, ((AttributeCode)reference0));
		}
		else
		if(attributeName.equals(attributeLocalVariableTable))
		{
			attribute = AttributeLocalVariableTable.deserialize(ctx, (AttributeCode)reference0);
		}
		else
		if(attributeName.equals(attributeLocalVariableTypeTable))
		{
			attribute = AttributeLocalVariableTypeTable.deserialize(ctx, (AttributeCode)reference0);
		}
		else
		if(attributeName.equals(attributeDeprecated))
		{
			attribute = AttributeDeprecated.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeRuntimeVisibleAnnotations))
		{
			attribute = AttributeRuntimeVisibleAnnotations.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeRuntimeInvisibleAnnotations))
		{
			attribute = AttributeRuntimeInvisibleAnnotations.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeRuntimeVisibleParameterAnnotations))
		{
			attribute = AttributeRuntimeVisibleParameterAnnotations.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeRuntimeInvisibleParameterAnnotations))
		{
			attribute = AttributeRuntimeInvisibleParameterAnnotations.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeAnnotationDefault))
		{
			attribute = AttributeAnnotationDefault.deserialize(ctx);
		}		
		else
		if(attributeName.equals(attributeBridge))
		{
			attribute = AttributeBridge.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeStackMapTable))
		{
			attribute = AttributeStackMapTable.deserialize(ctx, (AttributeCode)reference0);
		}
		else
		if(attributeName.equals(attributeBootstrapMethods)) // not tested !!!!
		{
			attribute = AttributeBootstrapMethods.deserialize(ctx);
		}
		else
		if(attributeName.equals(attributeVarargs))
		{
			attribute = AttributeVarargs.deserialize(ctx);
		}
		else
		{
			System.err.println("Deserialization - Contains custom attribute!");
			attribute = AttributeCustom.deserialize(ctx);			
		}
		
		attribute.setAttributeNameIndex(attributeNameIndex);
		attribute.setAttributeLength(attributeLength);
		
		/* decouple indices from attribute */
		decoupleFromIndices(attribute, constantPool);
		
		return attribute;
    }
	
	public static void decoupleFromIndices(AbstractAttribute attribute, ConstantPool cp)
	{
		/* decouple from name index */
		int nameIndex = attribute.getAttributeNameIndex();
		AbstractConstantPoolType acptNameObject = ConstantPool.cpeByIndex(cp, nameIndex);
		ConstantPoolTypeUtf8 nameObject = (ConstantPoolTypeUtf8)acptNameObject;
		attribute.setAttributeNameObject(nameObject);
	}
	
	public static void coupleWithIndices(SerCtx ctx, AbstractAttribute attribute)
	{
		/* couple with name index */
		ConstantPoolTypeUtf8 nameObject = attribute.getAttributeNameObject();
		int attributeNameIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), nameObject);
		attribute.setAttributeNameIndex(attributeNameIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, AbstractAttribute attribute, Object reference0) throws IOException
	{
		byte[] attributePayLoad = null;
		
		coupleWithIndices(ctx, attribute);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int   attributeNameIndex = attribute.getAttributeNameIndex();
		String     attributeName = ctx.getConstantPool().getConstantTypeUtf8Bytes(attributeNameIndex);
		
		baos.write(PNC.toByteArray(attribute.getAttributeNameIndex(), Short.class));
		
		if(attributeName.equals(attributeConstantValue))
		{
			attributePayLoad = AttributeConstantValue.serialize(ctx, (AttributeConstantValue)attribute); 
		}
		else
		if(attributeName.equals(attributeCode))
		{
			attributePayLoad = AttributeCode.serialize(ctx, (AttributeCode)attribute);
		}
		else
		if(attributeName.equals(attributeExceptions))
		{
			attributePayLoad = AttributeExceptions.serialize(ctx, (AttributeExceptions)attribute);
		}
		else
		if(attributeName.equals(attributeInnerClasses))
		{
			attributePayLoad = AttributeInnerClasses.serialize(ctx, (AttributeInnerClasses)attribute);
		}
		else
		if(attributeName.equals(attributeEnclosingMethod))
		{
			attributePayLoad = AttributeEnclosingMethod.serialize(ctx, (AttributeEnclosingMethod)attribute);
		}		
		else
		if(attributeName.equals(attributeSynthetic))
		{
			/* nothing to do here */
			attributePayLoad = new byte[0];
		}
		else
		if(attributeName.equals(attributeSignature))
		{
			attributePayLoad = AttributeSignature.serialize(ctx, (AttributeSignature)attribute);
		}
		else
		if(attributeName.equals(attributeSourceFile))
		{
			attributePayLoad = AttributeSourceFile.serialize(ctx, (AttributeSourceFile)attribute);
		}
		else
		if(attributeName.equals(attributeSourceDebugExtension))
		{
			attributePayLoad = AttributeSourceDebugExtension.serialize(ctx, (AttributeSourceDebugExtension)attribute);
		}
		else
		if(attributeName.equals(attributeLineNumberTable))
		{
			attributePayLoad = AttributeLineNumberTable.serialize(ctx, (AttributeLineNumberTable)attribute, (AttributeCode)reference0);
		}
		else
		if(attributeName.equals(attributeLocalVariableTable))
		{
			attributePayLoad = AttributeLocalVariableTable.serialize(ctx, (AttributeLocalVariableTable)attribute, (AttributeCode)reference0);
		}
		else
		if(attributeName.equals(attributeLocalVariableTypeTable))
		{
			attributePayLoad = AttributeLocalVariableTypeTable.serialize(ctx, (AttributeLocalVariableTypeTable)attribute, (AttributeCode)reference0);
		}
		else
		if(attributeName.equals(attributeDeprecated))
		{
			/* nothing to do here */
			attributePayLoad = new byte[0];
		}
		else
		if(attributeName.equals(attributeRuntimeVisibleAnnotations))
		{
			attributePayLoad = AttributeRuntimeVisibleAnnotations.serialize(ctx, (AttributeRuntimeVisibleAnnotations)attribute);
		}
		else
		if(attributeName.equals(attributeRuntimeInvisibleAnnotations))
		{
			attributePayLoad = AttributeRuntimeInvisibleAnnotations.serialize(ctx, (AttributeRuntimeInvisibleAnnotations)attribute);
		}
		else
		if(attributeName.equals(attributeRuntimeVisibleParameterAnnotations))
		{
			attributePayLoad = AttributeRuntimeVisibleParameterAnnotations.serialize(ctx, (AttributeRuntimeVisibleParameterAnnotations)attribute);
		}
		else
		if(attributeName.equals(attributeRuntimeInvisibleParameterAnnotations))
		{
			attributePayLoad = AttributeRuntimeInvisibleParameterAnnotations.serialize(ctx, (AttributeRuntimeInvisibleParameterAnnotations)attribute);
		}
		else
		if(attributeName.equals(attributeAnnotationDefault))
		{
			attributePayLoad = AttributeAnnotationDefault.serialize(ctx, (AttributeAnnotationDefault)attribute);
		}		
		else
		if(attributeName.equals(attributeBridge))
		{
			attributePayLoad = AttributeBridge.serialize(ctx, (AttributeBridge)attribute);
		}
		else
		if(attributeName.equals(attributeStackMapTable))
		{
			attributePayLoad = AttributeStackMapTable.serialize(ctx, (AttributeStackMapTable)attribute, (AttributeCode)reference0);
		}
		else
		if(attributeName.equals(attributeBootstrapMethods))
		{
			attributePayLoad = AttributeBootstrapMethods.serialize(ctx, (AttributeBootstrapMethods)attribute);
		}
		else
		if(attributeName.equals(attributeVarargs))
		{
			/* nothing to do here */
			attributePayLoad = new byte[0];
		}
		else
		{
			System.err.println("Serialization - Contains custom attribute!");
			attributePayLoad = AttributeCustom.serialize(ctx, (AttributeCustom)attribute);
		}
		
		baos.write(PNC.toByteArray(attributePayLoad.length, Integer.class));
		baos.write(attributePayLoad);
		
		return baos.toByteArray();
	}
}
