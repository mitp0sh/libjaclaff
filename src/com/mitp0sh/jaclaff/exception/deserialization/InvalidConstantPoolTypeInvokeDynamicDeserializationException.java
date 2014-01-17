package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeInvokeDynamicDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeInvokeDynamicDeserializationException(String message)
	{
		super("unable to deserialize constant pool type invokedynamic: " + message);
	}
}
