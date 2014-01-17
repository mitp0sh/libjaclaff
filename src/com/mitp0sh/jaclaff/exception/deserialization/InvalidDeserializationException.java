package com.mitp0sh.jaclaff.exception.deserialization;

import com.mitp0sh.jaclaff.exception.JaClaFFException;

public class InvalidDeserializationException extends JaClaFFException
{
	private static final long serialVersionUID = 5860923589926025330L;

	private String message = null;
	
	public InvalidDeserializationException(String message)
	{
		this.message = message;
	}

	@Override
	public String getMessage() 
	{
		return this.message;
	}
}
