package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeBIC extends AbstractInstruction
{
	private int operand0                           = 0;
	private int operand1                           = 0;

	public InstructionTypeBIC(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BIC);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_iinc:
		 	{
		 		setLiteral("iinc");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setWideable(true);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_iinc:
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

	public void setOperand0(int operand) 
	{
		this.operand0 = operand;
	}
	
	public int getOperand1()
	{
		return operand1;
	}

	public void setOperand1(int operand) 
	{
		this.operand1 = operand;
	}
	
	public static InstructionTypeBIC deserialize(DesCtx ctx, int byteCodeValue, boolean isWide, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBIC instruction = new InstructionTypeBIC(byteCodeValue, previousInstruction, disassembly);
		instruction.setWide(isWide);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		int operand0 = 0;
		int operand1 = 0;
		if(instruction.isWide())
		{
			operand0 = dis.readUnsignedShort();
			operand1 = dis.readShort();
		}
		else
		{
			operand0 = dis.readUnsignedByte();
			operand1 = dis.readByte();
		}
		instruction.setOperand0(operand0);
		instruction.setOperand1(operand1);
		
		/* build abstraction */
		decoupleFromIndices(ctx, instruction);
		
		return instruction;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, InstructionTypeBIC instruction)
	{
		//ConstantPool cp = ctx.getConstantPool();
		
		//int operandIndex = instruction.getOperand();
		//AbstractConstantPoolType acptOperandObject = null;
		//acptOperandObject = ConstantPool.cpeByIndex(cp, operandIndex);
		//instruction.setOperandObject(acptOperandObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, InstructionTypeBIC instruction)
	{
		//ConstantPool cp = ctx.getConstantPool();
	
		//AbstractConstantPoolType acptOperandObject = null;
		//acptOperandObject = instruction.getOperandObject();
	    //int operandIndex = ConstantPool.indexByCPE(cp, acptOperandObject);
	    //instruction.setOperand(operandIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBIC instruction) throws IOException
	{
		/* resolve abstraction */
		coupleToIndices(ctx, instruction);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		if(instruction.isWide())
		{
			baos.write(PNC.toByteArray(instruction.getOperand0(), Short.class));
			baos.write(PNC.toByteArray(instruction.getOperand1(), Short.class));
		}
		else
		{
			baos.write(new byte[]{(byte)instruction.getOperand0()});
			baos.write(new byte[]{(byte)instruction.getOperand1()});
		}
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		String tail = (isWide() ? " : [wide]" : "");
		return super.toString() + " " + "NOT YET IMPLEMENTED !!!" + tail;
	}

	@Override
	public int getPhysicalSize()
	{
		if(isWide())
		{
			return 5;
		}
		
		return 3;
	}
}
