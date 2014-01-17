package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeNameAndTypeDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeNameAndTypeDeserializationException(String message)
	{
		super("unable to deserialize constant pool type nameandtype: " + message);
	}
}
