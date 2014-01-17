package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeFloatDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeFloatDeserializationException(String message)
	{
		super("unable to deserialize constant pool type float: " + message);
	}
}
