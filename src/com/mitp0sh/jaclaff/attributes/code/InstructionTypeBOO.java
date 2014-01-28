package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeBOO extends AbstractInstruction
{
	private short operand                          = 0;
	private AbstractInstruction operandInstruction = null;

	public InstructionTypeBOO(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BOO);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_ifeq:
		 	{
		 		setLiteral("ifeq");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_ifne:
		 	{
		 		setLiteral("ifne");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_iflt:
		 	{
		 		setLiteral("iflt");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_ifge:
		 	{
		 		setLiteral("ifge");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_ifgt:
		 	{
		 		setLiteral("ifgt");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_ifle:
		 	{
		 		setLiteral("ifle");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_if_icmpeq:
		 	{
		 		setLiteral("if_icmpeq");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_if_icmpne:
		 	{
		 		setLiteral("if_icmpne");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_if_icmplt:
		 	{
		 		setLiteral("if_icmplt");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_if_icmpge:
		 	{
		 		setLiteral("if_icmpge");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_if_icmpgt:
		 	{
		 		setLiteral("if_icmpgt");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_if_icmple:
		 	{
		 		setLiteral("if_icmple");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_if_acmpeq:
		 	{
		 		setLiteral("if_acmpeq");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_if_acmpne:
		 	{
		 		setLiteral("if_acmpne");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		break;
		 	}
		 	case Mnemonics.BC_goto:
		 	{
		 		setLiteral("goto");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		break;
		 	}
		 	case Mnemonics.BC_jsr:
		 	{
		 		setLiteral("jsr");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		break;
		 	}
		 	case Mnemonics.BC_ifnull:
		 	{
		 		setLiteral("ifnull");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 	case Mnemonics.BC_ifnonnull:
		 	{
		 		setLiteral("ifnonnull");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_ifeq:
		 	case Mnemonics.BC_ifne:
		 	case Mnemonics.BC_iflt:
		 	case Mnemonics.BC_ifge:
		 	case Mnemonics.BC_ifgt:
		 	case Mnemonics.BC_ifle:
		 	case Mnemonics.BC_if_icmpeq:
		 	case Mnemonics.BC_if_icmpne:
		 	case Mnemonics.BC_if_icmplt:
		 	case Mnemonics.BC_if_icmpge:
		 	case Mnemonics.BC_if_icmpgt:
		 	case Mnemonics.BC_if_icmple:
		 	case Mnemonics.BC_if_acmpeq:
		 	case Mnemonics.BC_if_acmpne:
		 	case Mnemonics.BC_goto:
		 	case Mnemonics.BC_jsr:
		 	case Mnemonics.BC_ifnull:
		 	case Mnemonics.BC_ifnonnull:
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
	
	
	public AbstractInstruction getOperandInstruction() 
	{
		return operandInstruction;
	}

	public void setOperandInstruction(AbstractInstruction operandInstruction) 
	{
		this.operandInstruction = operandInstruction;
	}
	
	public static InstructionTypeBOO deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBOO instruction = new InstructionTypeBOO(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		short operand = dis.readShort();
		instruction.setOperand(operand);
		
		return instruction;
	}
	
	public static void decoupleFromOffsets(DesCtx ctx, InstructionTypeBOO instruction)
	{				
		Disassembly disassembly = instruction.getDisassembly();
		int instructionOffset = instruction.getOperand();
		instructionOffset += disassembly.getInstructionOffset(instruction);
		AbstractInstruction operandInstruction = disassembly.getInstruction(instructionOffset);
		instruction.setOperandInstruction(operandInstruction);
	}
	
	public static void coupleWithOffsets(SerCtx ctx, InstructionTypeBOO instruction)
	{	
		Disassembly disassembly = instruction.getDisassembly();
		AbstractInstruction operandInstruction = instruction.getOperandInstruction();
		int instructionOffset = disassembly.getInstructionOffset(operandInstruction);
		instructionOffset -= disassembly.getInstructionOffset(instruction);
		instruction.setOperand((short)instructionOffset);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBOO instruction) throws IOException
	{
		/* resolve abstraction */
		coupleWithOffsets(ctx, instruction);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		baos.write(PNC.toByteArray(instruction.getOperand(), Short.class));
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		String text = "";
		
		int offset = getDisassembly().getInstructionOffset(this.getOperandInstruction());
		text += " jump -> ";
		text += getOperandInstruction().getLiteral();
		text += " ( @offset: " + offset + " )";
		
		return super.toString() + text;
	}

	@Override
	public int getPhysicalSize() 
	{
		return 3;
	}
}
