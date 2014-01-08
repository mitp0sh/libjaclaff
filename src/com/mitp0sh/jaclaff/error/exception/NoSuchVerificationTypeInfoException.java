package com.mitp0sh.jaclaff.error.exception;

public class NoSuchVerificationTypeInfoException extends Throwable
{
	private static final long serialVersionUID = 1L;
	private static final String        message = "Unable to identify VerificationTypeInfo";
	
	@Override
	public String getMessage()
	{
		return message;
	}
}
