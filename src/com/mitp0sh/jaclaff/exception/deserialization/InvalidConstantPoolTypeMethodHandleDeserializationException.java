package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeMethodHandleDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeMethodHandleDeserializationException(String message)
	{
		super("unable to deserialize constant pool type methodhandle: " + message);
	}
}
