package com.mitp0sh.jaclaff.attributes.generic;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.util.PNC;


public class Value
{
	public static final char ELEMENT_VALUE_TYPE_PRIMITIVE_B = 'B';
	public static final char ELEMENT_VALUE_TYPE_PRIMITIVE_C = 'C';
	public static final char ELEMENT_VALUE_TYPE_PRIMITIVE_D = 'D';
	public static final char ELEMENT_VALUE_TYPE_PRIMITIVE_F = 'F';
	public static final char ELEMENT_VALUE_TYPE_PRIMITIVE_I = 'I';
	public static final char ELEMENT_VALUE_TYPE_PRIMITIVE_J = 'J';
	public static final char ELEMENT_VALUE_TYPE_PRIMITIVE_S = 'S';
	public static final char ELEMENT_VALUE_TYPE_PRIMITIVE_Z = 'Z';
	public static final char ELEMENT_VALUE_TYPE_S           = 's';
	public static final char ELEMENT_VALUE_TYPE_E           = 'e';
	public static final char ELEMENT_VALUE_TYPE_C           = 'c';
	public static final char ELEMENT_VALUE_TYPE_AT          = '@';
	public static final char ELEMENT_VALUE_TYPE_ARRAY       = '[';
	
	private short             			  constValueIndex = 0;
	private AbstractConstantPoolType	 constValueObject = null;
	private EnumConstValue                 enumConstValue = null;
	private short                          classInfoIndex = 0;
	private ConstantPoolTypeUtf8          classInfoObject = null;
	private Annotation                         annotation = null;
	private ArrayValue                         arrayValue = null;
	
	public short getConstValueIndex() 
	{
		return constValueIndex;
	}
	
	public void setConstValueIndex(short constValueIndex) 
	{
		this.constValueIndex = constValueIndex;
	}
	
	public EnumConstValue getEnumConstValue()
	{
		return enumConstValue;
	}
	
	public void setEnumConstValue(EnumConstValue enumConstValue)
	{
		this.enumConstValue = enumConstValue;
	}
	
	public short getClassInfoIndex()
	{
		return classInfoIndex;
	}
	
	public void setClassInfoIndex(short classInfoIndex)
	{
		this.classInfoIndex = classInfoIndex;
	}
	
	public Annotation getAnnotation()
	{
		return annotation;
	}
	
	public void setAnnotation(Annotation annotation)
	{
		this.annotation = annotation;
	}
	
	public ArrayValue getArrayValue()
	{
		return arrayValue;
	}
	
	public void setArrayValue(ArrayValue arrayValue)
	{
		this.arrayValue = arrayValue;
	}
	
	public AbstractConstantPoolType getConstValueObject() 
	{
		return constValueObject;
	}

	public void setConstValueObject(AbstractConstantPoolType constValueObject) 
	{
		this.constValueObject = constValueObject;
	}
	
	public ConstantPoolTypeUtf8 getClassInfoObject() 
	{
		return classInfoObject;
	}

	public void setClassInfoObject(ConstantPoolTypeUtf8 classInfoObject) 
	{
		this.classInfoObject = classInfoObject;
	}
	
	public static void decoupleFromIndices(Value value, char tag, ConstantPool constantPool)
	{
		switch(tag)
		{
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_B:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_C:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_D:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_F:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_I:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_J:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_S:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_Z:
		    case ELEMENT_VALUE_TYPE_S:
		    {
		    	AbstractConstantPoolType cpt = ConstantPool.getConstantPoolTypeByIndex(constantPool, value.getConstValueIndex());
		    	cpt.setConstant_pool_tag((byte)tag);
		    	value.setConstValueObject(cpt);
		    	value.setConstValueIndex((short)0);
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_C:
		    {
		    	value.setClassInfoObject((ConstantPoolTypeUtf8)ConstantPool.getConstantPoolTypeByIndex(constantPool, value.getConstValueIndex()));
		    	value.setClassInfoIndex((short)0);
		    	break;
		    }
		}
	}

	public static Value deserialize(DataInputStream dis, char tag, ConstantPool constantPool) throws IOException
	{
		Value value = new Value();
		
		switch(tag)
		{
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_B:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_C:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_D:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_F:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_I:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_J:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_S:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_Z:
		    case ELEMENT_VALUE_TYPE_S:
		    {
		    	value.setConstValueIndex((short)dis.readUnsignedShort());
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_E:
		    {
		    	value.setEnumConstValue(EnumConstValue.deserialize(dis, constantPool));
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_C:
		    {
		    	value.setClassInfoIndex((short)dis.readUnsignedShort());
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_AT:
		    {
		    	value.setAnnotation(Annotation.deserialize(dis, constantPool));
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_ARRAY:
		    {
		    	value.setArrayValue(ArrayValue.deserialize(dis, constantPool));
		    	break;
		    }
		    default:
		    {
		    	System.out.println("DUnknown element value tag! - " + tag);
		    	break;
		    }
		}
		
		decoupleFromIndices(value, tag, constantPool);
		
		return value;
	}
	
	public static byte[] serialize(Value value, char tag) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		switch(tag)
		{
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_B:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_C:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_D:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_F:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_I:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_J:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_S:
		    case ELEMENT_VALUE_TYPE_PRIMITIVE_Z:
		    case ELEMENT_VALUE_TYPE_S:
		    {
		    	baos.write(PNC.toByteArray(value.getConstValueIndex(), Short.class));
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_E:
		    {
		    	baos.write(EnumConstValue.serialize(value.getEnumConstValue()));
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_C:
		    {
		    	baos.write(PNC.toByteArray(value.getClassInfoIndex(), Short.class));
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_AT:
		    {
		    	baos.write(Annotation.serialize(value.getAnnotation()));
		    	break;
		    }
		    case ELEMENT_VALUE_TYPE_ARRAY:
		    {
		    	baos.write(ArrayValue.serialize(value.getArrayValue()));
		    	break;
		    }
		    default:
		    {
		    	System.out.println("SUnknown element value tag!");
		    	break;
		    }
		}
		
		return baos.toByteArray();
	}
}
