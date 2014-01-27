package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeBOOOO extends AbstractInstruction
{
	private int operand                            = 0;
	private AbstractInstruction operandInstruction = null;

	public InstructionTypeBOOOO(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BOOOO);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_goto_w:
		 	{
		 		setLiteral("goto_w");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		break;
		 	}
		 	case Mnemonics.BC_jsr_w:
		 	{
		 		setLiteral("jsr_w");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_goto_w:
			case Mnemonics.BC_jsr_w:
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
	
	
	public AbstractInstruction getOperandInstruction() 
	{
		return operandInstruction;
	}

	public void setOperandInstruction(AbstractInstruction operandInstruction) 
	{
		this.operandInstruction = operandInstruction;
	}
	
	public static InstructionTypeBOOOO deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBOOOO instruction = new InstructionTypeBOOOO(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		int operand = dis.readInt();
		instruction.setOperand(operand);
		
		/* build abstraction */
		decoupleFromIndices(ctx, instruction);
		
		return instruction;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, InstructionTypeBOOOO instruction)
	{
		// TODO - NOT YET IMPLEMENTED !!!
		
		//ConstantPool cp = ctx.getConstantPool();
		
		//int operandIndex = instruction.getOperand();
		//AbstractConstantPoolType acptOperandObject = null;
		//acptOperandObject = ConstantPool.cpeByIndex(cp, operandIndex);
		//instruction.setOperandObject(acptOperandObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, InstructionTypeBOOOO instruction)
	{
		// TODO - NOT YET IMPLEMENTED !!!
		
		//ConstantPool cp = ctx.getConstantPool();
	
		//AbstractConstantPoolType acptOperandObject = null;
		//acptOperandObject = instruction.getOperandObject();
	    //int operandIndex = ConstantPool.indexByCPE(cp, acptOperandObject);
	    //instruction.setOperand(operandIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBOOOO instruction) throws IOException
	{
		/* resolve abstraction */
		coupleToIndices(ctx, instruction);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		baos.write(PNC.toByteArray(instruction.getOperand(), Integer.class));
		
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
		return 5;
	}
}
