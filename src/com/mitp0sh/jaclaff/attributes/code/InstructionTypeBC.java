package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class InstructionTypeBC extends AbstractInstruction
{
	private short operand = 0;

	public InstructionTypeBC(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BC);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_bipush:
		 	{
		 		setLiteral("bipush");
		 		setStackHeadResultType(BasicTypes.T_INT);
		 		setOperandStackDelta(+1);
		 		break;
		 	}
		 	case Mnemonics.BC_newarray:
		 	{
		 		setLiteral("newarray");
		 		setStackHeadResultType(BasicTypes.T_OBJECT);
		 		setCanTrap(true);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
    	    case Mnemonics.BC_bipush:
    	    case Mnemonics.BC_newarray:
    	    {
    	    	return true;
    	    }
		}
		
		return false;
	}
	
	public short getOperand()
	{
		return operand;
	}

	public void setOperand(short operand) 
	{
		this.operand = operand;
	}
	
	public static InstructionTypeBC deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBC instruction = new InstructionTypeBC(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		short operand = dis.readByte();
		instruction.setOperand(operand);
		
		return instruction;
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBC instruction) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		baos.write(new byte[]{(byte)instruction.getOperand()});
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " " + Short.toString(getOperand());
	}

	@Override
	public int getPhysicalSize() 
	{
		return 2;
	}
}
