package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeBJJ extends AbstractInstruction
{
	private int operand                            = 0;
	private AbstractConstantPoolType operandObject = null;

	public InstructionTypeBJJ(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BJJ);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_getstatic:
		 	{
		 		setLiteral("getstatic");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setCanTrap(true);
		 		setOperandStackDelta(+1);
		 		break;
		 	}
		 	case Mnemonics.BC_putstatic:
		 	{
		 		setLiteral("putstatic");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setCanTrap(true);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_getfield:
		 	{
		 		setLiteral("getfield");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setCanTrap(true);
		 		break;
		 	}
		 	case Mnemonics.BC_putfield:
		 	{
		 		setLiteral("putfield");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setCanTrap(true);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_invokevirtual:
		 	{
		 		setLiteral("invokevirtual");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setCanTrap(true);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_invokespecial:
		 	{
		 		setLiteral("invokespecial");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setCanTrap(true);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_invokestatic:
		 	{
		 		setLiteral("invokestatic");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setCanTrap(true);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_getstatic:
		 	case Mnemonics.BC_putstatic:
		 	case Mnemonics.BC_getfield:
		 	case Mnemonics.BC_putfield:
		 	case Mnemonics.BC_invokevirtual:
		 	case Mnemonics.BC_invokespecial:
		 	case Mnemonics.BC_invokestatic:
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
	
	public static InstructionTypeBJJ deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBJJ instruction = new InstructionTypeBJJ(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		int operand = dis.readUnsignedShort();
		instruction.setOperand(operand);
		
		/* build abstraction */
		decoupleFromIndices(ctx, instruction);
		
		return instruction;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, InstructionTypeBJJ instruction)
	{		
		ConstantPool cp = ctx.getConstantPool();
		
		int operandIndex = instruction.getOperand();
		AbstractConstantPoolType acptOperandObject = null;
		acptOperandObject = ConstantPool.cpeByIndex(cp, operandIndex);
		instruction.setOperandObject(acptOperandObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, InstructionTypeBJJ instruction)
	{		
		ConstantPool cp = ctx.getConstantPool();
	
		AbstractConstantPoolType acptOperandObject = null;
		acptOperandObject = instruction.getOperandObject();
	    int operandIndex = ConstantPool.indexByCPE(cp, acptOperandObject);
	    instruction.setOperand(operandIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBJJ instruction) throws IOException
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
		String text = "";
		
		text += super.toString() + "\n";
		text += "                     operand = " + getOperandObject().toString();
		
		return text;
	}

	@Override
	public int getPhysicalSize() 
	{
		return 3;
	}
}
