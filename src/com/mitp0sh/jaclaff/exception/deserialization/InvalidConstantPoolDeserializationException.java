package com.mitp0sh.jaclaff.exception.deserialization;


public class InvalidConstantPoolDeserializationException extends InvalidDeserializationException
{
	private static final long serialVersionUID = -2774702642362978837L;

	public InvalidConstantPoolDeserializationException(String message) 
	{
		super(message);
	}
}
