package com.mitp0sh.jaclaff.exception.deserialization;


public class InvalidConstantPoolTypeClassDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6214589340112055139L;
	
	public InvalidConstantPoolTypeClassDeserializationException(String message)
	{
		super("unable to deserialize constant pool type class: " + message);
	}
}
