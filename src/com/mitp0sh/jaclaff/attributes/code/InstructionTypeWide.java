package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class InstructionTypeWide extends AbstractInstruction
{
	public InstructionTypeWide(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly)
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	public void initInstruction(int byteCodeValue)
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.WIDE);
		
		/* init instruction */
		switch(byteCodeValue)
		{
    	    case Mnemonics.BC_wide:
    	    {
    	    	setLiteral("wide");    	    	
    	    	break;
    	    }
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_wide:
		    {
		    	return true;
		    }
		}
		
		return false;
	}
	
	public static InstructionTypeWide deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly)
	{
		return new InstructionTypeWide(byteCodeValue, previousInstruction, disassembly);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeWide instruction) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public int getPhysicalSize()
	{
		return 1;
	}
}