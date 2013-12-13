package com.mitp0sh.jaclaff.test;

import java.io.IOException;

import com.mitp0sh.jaclaff.Deserializer;
import com.mitp0sh.jaclaff.VirtualClassFile;

public class Test0 
{
	public static void main(String[] args) 
	{
		VirtualClassFile vcf = null;
		try
		{
			vcf = Deserializer.deserialize("test/LogServiceManager.class");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
		
		vcf.toString();
	}
}
