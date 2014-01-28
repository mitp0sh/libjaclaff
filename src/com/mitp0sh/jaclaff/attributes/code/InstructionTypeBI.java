package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class InstructionTypeBI extends AbstractInstruction
{
	private int operand                            = 0;
	private AbstractConstantPoolType operandObject = null;

	public InstructionTypeBI(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BI);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_ldc:
		 	{
		 		setLiteral("ldc");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setOperandStackDelta(+1);
		 		setCanTrap(true);
		 		break;
		 	}
		 	case Mnemonics.BC_iload:
		 	{
		 		setLiteral("iload");
		 		setStackHeadResultType(BasicTypes.T_INT);
		 		setOperandStackDelta(+1);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_lload:
		 	{
		 		setLiteral("lload");
		 		setStackHeadResultType(BasicTypes.T_LONG);
		 		setOperandStackDelta(+2);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_fload:
		 	{
		 		setLiteral("fload");
		 		setStackHeadResultType(BasicTypes.T_FLOAT);
		 		setOperandStackDelta(+1);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_dload:
		 	{
		 		setLiteral("dload");
		 		setStackHeadResultType(BasicTypes.T_DOUBLE);
		 		setOperandStackDelta(+2);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_aload:
		 	{
		 		setLiteral("aload");
		 		setStackHeadResultType(BasicTypes.T_OBJECT);
		 		setOperandStackDelta(+1);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_istore:
		 	{
		 		setLiteral("istore");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_lstore:
		 	{
		 		setLiteral("lstore");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_fstore:
		 	{
		 		setLiteral("fstore");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_dstore:
		 	{
		 		setLiteral("dstore");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-2);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_astore:
		 	{
		 		setLiteral("astore");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(-1);
		 		setWideable(true);
		 		break;
		 	}
		 	case Mnemonics.BC_ret:
		 	{
		 		setLiteral("ret");
		 		setStackHeadResultType(BasicTypes.T_VOID);
		 		setOperandStackDelta(0);
		 		setWideable(true);
		 		break;
		 	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_ldc:
		 	case Mnemonics.BC_iload:
		 	case Mnemonics.BC_lload:
		 	case Mnemonics.BC_fload:
		 	case Mnemonics.BC_dload:
		 	case Mnemonics.BC_aload:
		 	case Mnemonics.BC_istore:
		 	case Mnemonics.BC_lstore:
		 	case Mnemonics.BC_fstore:
		 	case Mnemonics.BC_dstore:
		 	case Mnemonics.BC_astore:
		 	case Mnemonics.BC_ret:
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
	
	public static InstructionTypeBI deserialize(DesCtx ctx, int byteCodeValue, boolean isWide, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBI instruction = new InstructionTypeBI(byteCodeValue, previousInstruction, disassembly);
		instruction.setWide(isWide);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		int operand = 0;
		if(instruction.isWide())
		{
			operand = dis.readUnsignedShort();
		}
		else
		{
			operand = dis.readUnsignedByte();
		}		
		instruction.setOperand(operand);
		
		decoupleFromIndices(ctx, instruction);
		
		return instruction;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, InstructionTypeBI instruction)
	{
		if(instruction.getLiteral().equals("fload") ||
		   instruction.getLiteral().equals("fstore"))
		{
			return;
		}
		
		ConstantPool cp = ctx.getConstantPool();
		
		int operandIndex = instruction.getOperand();
		AbstractConstantPoolType acptOperandObject = null;
		acptOperandObject = ConstantPool.cpeByIndex(cp, operandIndex);
		instruction.setOperandObject(acptOperandObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, InstructionTypeBI instruction)
	{
		if(instruction.getLiteral().equals("fload") ||
		   instruction.getLiteral().equals("fstore"))
		{
			return;
		}
		
		ConstantPool cp = ctx.getConstantPool();
	
		AbstractConstantPoolType acptOperandObject = null;
		acptOperandObject = instruction.getOperandObject();
	    int operandIndex = ConstantPool.indexByCPE(cp, acptOperandObject);
	    instruction.setOperand(operandIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBI instruction) throws IOException
	{
		coupleToIndices(ctx, instruction);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		if(instruction.isWide())
		{
			baos.write(PNC.toByteArray(instruction.getOperand(), Short.class));
		}
		else
		{
			baos.write(new byte[]{(byte)instruction.getOperand()});
		}
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		String tail = (isWide() ? " : [wide]" : "");
		return super.toString() + " " + Integer.toString(getOperand()) + tail;
	}

	@Override
	public int getPhysicalSize()
	{
		if(isWide())
		{
			return 3;
		}
		
		return 2;
	}
}
