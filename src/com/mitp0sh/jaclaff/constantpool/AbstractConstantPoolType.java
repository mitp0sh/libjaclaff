package com.mitp0sh.jaclaff.constantpool;

public abstract class AbstractConstantPoolType
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
}
