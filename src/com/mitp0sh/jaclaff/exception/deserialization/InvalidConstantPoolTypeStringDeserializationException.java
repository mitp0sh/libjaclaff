package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeStringDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeStringDeserializationException(String message)
	{
		super("unable to deserialize constant pool type string: " + message);
	}
}
