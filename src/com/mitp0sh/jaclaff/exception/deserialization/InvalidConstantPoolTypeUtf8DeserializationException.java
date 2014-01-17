package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeUtf8DeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeUtf8DeserializationException(String message)
	{
		super("unable to deserialize constant pool type utf8: " + message);
	}
}
