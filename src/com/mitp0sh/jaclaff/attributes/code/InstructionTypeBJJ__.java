package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class InstructionTypeBJJ__ extends AbstractInstruction
{
	private int operand                            = 0;
	private AbstractInstruction operandInstruction = null;

	public InstructionTypeBJJ__(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) 
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	@Override
	public void initInstruction(int byteCodeValue) 
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.BJJ__);
		
		/* init instruction */
		switch(byteCodeValue)
		{
		 	case Mnemonics.BC_invokeinterface:
		 	{
		 		setLiteral("invokeinterface");
		 		setStackHeadResultType(BasicTypes.T_ILLEGAL);
		 		setCanTrap(true);
		 		setOperandStackDelta(-1);
		 		break;
		 	}
		 }
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
			case Mnemonics.BC_invokeinterface:
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
	
	public static InstructionTypeBJJ__ deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly) throws IOException
	{
		InstructionTypeBJJ__ instruction = new InstructionTypeBJJ__(byteCodeValue, previousInstruction, disassembly);
		
		DataInputStream dis = ctx.getDataInputStream();
		
		int operand = dis.readUnsignedShort();
		instruction.setOperand(operand);
		dis.skipBytes(2);
		
		/* build abstraction */
		decoupleFromIndices(ctx, instruction);
		
		return instruction;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, InstructionTypeBJJ__ instruction)
	{
		// TODO - NOT YET IMPLEMENTED !!!
		
		//ConstantPool cp = ctx.getConstantPool();
		
		//int operandIndex = instruction.getOperand();
		//AbstractConstantPoolType acptOperandObject = null;
		//acptOperandObject = ConstantPool.cpeByIndex(cp, operandIndex);
		//instruction.setOperandObject(acptOperandObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, InstructionTypeBJJ__ instruction)
	{
		// TODO - NOT YET IMPLEMENTED !!!
		
		//ConstantPool cp = ctx.getConstantPool();
	
		//AbstractConstantPoolType acptOperandObject = null;
		//acptOperandObject = instruction.getOperandObject();
	    //int operandIndex = ConstantPool.indexByCPE(cp, acptOperandObject);
	    //instruction.setOperand(operandIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeBJJ__ instruction) throws IOException
	{
		/* resolve abstraction */
		coupleToIndices(ctx, instruction);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		short skippedBytes = 0;
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		baos.write(PNC.toByteArray(instruction.getOperand(), Short.class));
		baos.write(PNC.toByteArray(skippedBytes, Short.class));
		
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
