package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeBII extends AbstractInstruction
{
	private int operand                            = 0;
	private AbstractConstantPoolType operandObject = null;

	public InstructionTypeBII(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BII);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_ldc_w:
		 	{
		 		setLiteral("ldc_w");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setOperandStackDelta(+1);
		 		setCanTrap(true);
		 		break;
		 	}
		 	case Mnemonics.BC_ldc2_w:
		 	{
		 		setLiteral("ldc2_w");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setOperandStackDelta(+2);
		 		setCanTrap(true);
		 		break;
		 	}
		 	case Mnemonics.BC_new:
		 	{
		 		setLiteral("new");
		 		setStackHeadResultType(BasicTypes.T_OBJECT);
		 		setOperandStackDelta(+1);
		 		setCanTrap(true);
		 		break;
		 	}
		 	case Mnemonics.BC_anewarray:
		 	{
		 		setLiteral("anewarray");
		 		setStackHeadResultType(BasicTypes.T_OBJECT);
		 		setOperandStackDelta(0);
		 		setCanTrap(true);
		 		break;
		 	}
		 	case Mnemonics.BC_checkcast:
		 	{
		 		setLiteral("checkcast");
		 		setStackHeadResultType(BasicTypes.T_OBJECT);
		 		setOperandStackDelta(0);
		 		setCanTrap(true);
		 		break;
		 	}
		 	case Mnemonics.BC_instanceof:
		 	{
		 		setLiteral("instanceof");
		 		setStackHeadResultType(BasicTypes.T_INT);
		 		setOperandStackDelta(0);
		 		setCanTrap(true);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_ldc_w:
		 	case Mnemonics.BC_ldc2_w:
		 	case Mnemonics.BC_new:
		 	case Mnemonics.BC_anewarray:
		 	case Mnemonics.BC_checkcast:
		 	case Mnemonics.BC_instanceof:
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
	
	public AbstractConstantPoolType getOperandObject()
	{
		return operandObject;
	}

	public void setOperandObject(AbstractConstantPoolType operandObject) 
	{
		this.operandObject = operandObject;
	}
	
	public static InstructionTypeBII deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBII instruction = new InstructionTypeBII(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		int operand = dis.readUnsignedShort();
		instruction.setOperand(operand);
		
		/* build abstraction */
		decoupleFromIndices(ctx, instruction);
		
		return instruction;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, InstructionTypeBII instruction)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int operandIndex = instruction.getOperand();
		AbstractConstantPoolType acptOperandObject = null;
		acptOperandObject = ConstantPool.cpeByIndex(cp, operandIndex);
		instruction.setOperandObject(acptOperandObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, InstructionTypeBII instruction)
	{
		ConstantPool cp = ctx.getConstantPool();
	
		AbstractConstantPoolType acptOperandObject = null;
		acptOperandObject = instruction.getOperandObject();
	    int operandIndex = ConstantPool.indexByCPE(cp, acptOperandObject);
	    instruction.setOperand(operandIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBII instruction) throws IOException
	{
		/* resolve abstraction */
		coupleToIndices(ctx, instruction);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		baos.write(PNC.toByteArray(instruction.getOperand(), Short.class));
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " " + getOperandObject().toString();
	}

	@Override
	public int getPhysicalSize() 
	{
		return 3;
	}
}
