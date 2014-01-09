package com.mitp0sh.jaclaff.attributes.stackmaptable;

/* complete */
public abstract class AbstractVariableInfo
{
	private short   variable_info_tag;
	private String variable_info_string_representation;
	
	public static final byte VERIFICATION_TYPE_INFO_TOP_TAG               = 0;
	public static final byte VERIFICATION_TYPE_INFO_INTEGER_TAG           = 1;
	public static final byte VERIFICATION_TYPE_INFO_FLOAT_TAG             = 2;
	public static final byte VERIFICATION_TYPE_INFO_LONG_TAG              = 4;
	public static final byte VERIFICATION_TYPE_INFO_DOUBLE_TAG            = 3;
	public static final byte VERIFICATION_TYPE_INFO_NULL_TAG              = 5;
	public static final byte VERIFICATION_TYPE_INFO_UNINITIALIZEDTHIS_TAG = 6;
	public static final byte VERIFICATION_TYPE_INFO_OBJECT_TAG            = 7;
	public static final byte VERIFICATION_TYPE_INFO_UNINITIALIZED         = 8;
	
	public short getVariable_info_tag() 
	{
		return variable_info_tag;
	}
	
	public void setVariable_info_tag(short variable_info_tag)
	{
		this.variable_info_tag = variable_info_tag;
	}
	
	public String getVariable_info_string_representation()
	{
		return variable_info_string_representation;
	}
	
	public void setVariable_info_string_representation(String variable_info_string_representation)
	{
		this.variable_info_string_representation = variable_info_string_representation;
	}
	
	
}
