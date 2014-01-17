package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeMethodTypeDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeMethodTypeDeserializationException(String message)
	{
		super("unable to deserialize constant pool type methodtype: " + message);
	}
}
