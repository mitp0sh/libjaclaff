package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeFieldrefDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeFieldrefDeserializationException(String message)
	{
		super("unable to deserialize constant pool type fieldref: " + message);
	}
}
