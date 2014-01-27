package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeBCC extends AbstractInstruction
{
	private int operand = 0;

	public InstructionTypeBCC(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BCC);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_sipush:
		 	{
		 		setLiteral("sipush");
		 		setStackHeadResultType(BasicTypes.T_INT);
		 		setOperandStackDelta(+1);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
    	    case Mnemonics.BC_sipush:
    	    {
    	    	return true;
    	    }
		}
		
		return false;
	}
	
	public int getOperand()
	{
		return operand;
	}

	public void setOperand(int operand) 
	{
		this.operand = operand;
	}
	
	public static InstructionTypeBCC deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBCC instruction = new InstructionTypeBCC(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		int operand = dis.readShort();
		instruction.setOperand(operand);
		
		return instruction;
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBCC instruction) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		baos.write(PNC.toByteArray(instruction.getOperand(), Short.class));
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " " + Integer.toString(getOperand());
	}

	@Override
	public int getPhysicalSize()
	{
		return 3;
	}
}
