package com.mitp0sh.jaclaff.attributes.annotation;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;


public abstract class AbstractValue extends AbstractReference
{
	private short                   element_value_type = 0;
	private String element_value_string_representation = "";

	public short getElement_value_type()
	{
		return element_value_type;
	}
	
	public void setElement_value_type(short element_value_type) 
	{
		this.element_value_type = element_value_type;
	}
	
	public String getElement_value_string_representation() 
	{
		return element_value_string_representation;
	}
	
	public void setElement_value_string_representation(String element_value_string_representation) 
	{
		this.element_value_string_representation = element_value_string_representation;
	}
	
	public boolean isConstantValueIndex()
	{
		try
		{
			@SuppressWarnings("unused")
			ConstValueIndex frame = (ConstValueIndex)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isConstantValue()
	{
		try
		{
			@SuppressWarnings("unused")
			ConstValue frame = (ConstValue)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isClassInfoIndex()
	{
		try
		{
			@SuppressWarnings("unused")
			ClassInfoIndex frame = (ClassInfoIndex)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isAnnotationValue()
	{
		try
		{
			@SuppressWarnings("unused")
			Annotation frame = (Annotation)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
}
