package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeMethodrefDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeMethodrefDeserializationException(String message)
	{
		super("unable to deserialize constant pool type methodref: " + message);
	}
}
