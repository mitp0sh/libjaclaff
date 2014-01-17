package com.mitp0sh.jaclaff.constantpool;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;

public abstract class AbstractConstantPoolType extends AbstractReference
{
	private byte   constant_pool_tag;
	private String constant_pool_string_representation;
	
	public static final byte CONSTANT_POOL_TAG_CLASS              = 7;
	public static final byte CONSTANT_POOL_TAG_FIELDREF           = 9;
	public static final byte CONSTANT_POOL_TAG_METHODREF          = 10;
	public static final byte CONSTANT_POOL_TAG_INTERFACEMETHODREF = 11;
	public static final byte CONSTANT_POOL_TAG_STRING             = 8;
	public static final byte CONSTANT_POOL_TAG_INTEGER            = 3;
	public static final byte CONSTANT_POOL_TAG_FLOAT              = 4;
	public static final byte CONSTANT_POOL_TAG_LONG               = 5;
	public static final byte CONSTANT_POOL_TAG_DOUBLE             = 6;
	public static final byte CONSTANT_POOL_TAG_NAMEANDTYPE        = 12;
	public static final byte CONSTANT_POOL_TAG_UTF8               = 1;
	public static final byte CONSTANT_POOL_TAG_METHODHANDLE       = 15;
	public static final byte CONSTANT_POOL_TAG_METHODTYPE         = 16;
	public static final byte CONSTANT_POOL_TAG_INVOKE_DYNAMIC     = 18;
	
	public byte getConstant_pool_tag()
	{
		return constant_pool_tag;
	}
	
	public void setConstant_pool_tag(byte constantPoolTag)
	{
		constant_pool_tag = constantPoolTag;
	}
	
	public String getConstant_pool_string_representation()
	{
		return constant_pool_string_representation;
	}
	
	public void setConstant_pool_string_representation(String constantPoolStringRepresentation)
	{
		constant_pool_string_representation = constantPoolStringRepresentation;
	}	
	
	public AbstractConstantPoolType clone()
	{
		AbstractConstantPoolType acpt = null;
		switch(constant_pool_tag)
		{
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS:
			{
				acpt = new ConstantPoolTypeClass();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_DOUBLE:
			{
				acpt = new ConstantPoolTypeDouble();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF:
			{
				acpt = new ConstantPoolTypeFieldref();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_FLOAT:
			{
				acpt = new ConstantPoolTypeFloat();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTEGER:
			{
				acpt = new ConstantPoolTypeInteger();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF:
			{
				acpt = new ConstantPoolTypeInterfaceMethodref();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_LONG:
			{
				acpt = new ConstantPoolTypeLong();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF:
			{
				acpt = new ConstantPoolTypeMethodref();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE:
			{
				acpt = new ConstantPoolTypeNameAndType();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING:
			{
				acpt = new ConstantPoolTypeString();
				break;
			}
			case AbstractConstantPoolType.CONSTANT_POOL_TAG_UTF8:
			{
				acpt = new ConstantPoolTypeUtf8();
				break;
			}
			default:
			{
				return null;
			}
		}
		
		return acpt;
	}
}