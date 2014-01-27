package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeBIIC extends AbstractInstruction
{
	private int operand0                            = 0;
	private byte operand1                           = 0;
	
	public InstructionTypeBIIC(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BIIC);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_multianewarray:
		 	{
		 		setLiteral("multianewarray");
		 		setStackHeadResultType(BasicTypes.T_OBJECT);
		 		setOperandStackDelta(+1);
		 		setCanTrap(true);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_multianewarray:
    	    {
    	    	return true;
    	    }
		}
		
		return false;
	}
	
	public int getOperand0()
	{
		return operand0;
	}

	public void setOperand0(int operand0) 
	{
		this.operand0 = operand0;
	}
	
	public byte getOperand1()
	{
		return operand1;
	}

	public void setOperand1(byte operand1) 
	{
		this.operand1 = operand1;
	}
	
	public static InstructionTypeBIIC deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBIIC instruction = new InstructionTypeBIIC(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		int operand0  = dis.readUnsignedShort();
		byte operand1 = dis.readByte();
		
		instruction.setOperand0(operand0);
		instruction.setOperand1(operand1);
		
		/* build abstraction */
		decoupleFromIndices(ctx, instruction);
		
		return instruction;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, InstructionTypeBIIC instruction)
	{
		// TODO - NOT YET IMPLEMENTED !!!
		
		//ConstantPool cp = ctx.getConstantPool();
		
		//int operandIndex = instruction.getOperand();
		//AbstractConstantPoolType acptOperandObject = null;
		//acptOperandObject = ConstantPool.cpeByIndex(cp, operandIndex);
		//instruction.setOperandObject(acptOperandObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, InstructionTypeBIIC instruction)
	{
		// TODO - NOT YET IMPLEMENTED !!!
		
		//ConstantPool cp = ctx.getConstantPool();
	
		//AbstractConstantPoolType acptOperandObject = null;
		//acptOperandObject = instruction.getOperandObject();
	    //int operandIndex = ConstantPool.indexByCPE(cp, acptOperandObject);
	    //instruction.setOperand(operandIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBIIC instruction) throws IOException
	{
		/* resolve abstraction */
		coupleToIndices(ctx, instruction);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		baos.write(PNC.toByteArray(instruction.getOperand0(), Short.class));
		baos.write(new byte[]{instruction.getOperand1()});
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		return "NOT YET IMPLEMENTED !!!";
	}

	@Override
	public int getPhysicalSize() 
	{
		return 4;
	}
}
