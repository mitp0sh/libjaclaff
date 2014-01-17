package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeDoubleDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeDoubleDeserializationException(String message)
	{
		super("unable to deserialize constant pool type double: " + message);
	}
}
