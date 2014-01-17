package com.mitp0sh.jaclaff.exception.deserialization;

public class InvalidConstantPoolTypeInterfaceMethodrefDeserializationException extends InvalidConstantPoolDeserializationException
{
	private static final long serialVersionUID = -6101902181622969799L;
	
	public InvalidConstantPoolTypeInterfaceMethodrefDeserializationException(String message)
	{
		super("unable to deserialize constant pool type interfacemethodref: " + message);
	}
}
