package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.code.bytecode.MethodInstructions;
import com.mitp0sh.jaclaff.attributes.code.bytecode.SingleInstruction;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ExceptionTableEntry 
{
	private int                            startPc = 0;
	private int                              endPc = 0;
	private int                          handlerPc = 0;
	private int                          catchType = 0;
	
	private ConstantPoolTypeClass  catchTypeObject = null;
	private SingleInstruction   startPcInstruction = null;
	private SingleInstruction     endPcInstruction = null;
	private SingleInstruction handlerPcInstruction = null;
	
	public int getStartPc()
	{
		return startPc;
	}

	public void setStartPc(int startPc)
	{
		this.startPc = startPc;
	}

	public int getEndPc() 
	{
		return endPc;
	}

	public void setEndPc(int endPc)
	{
		this.endPc = endPc;
	}

	public int getHandlerPc() {
		return handlerPc;
	}

	public void setHandlerPc(int handlerPc)
	{
		this.handlerPc = handlerPc;
	}

	public int getCatchType() 
	{
		return catchType;
	}

	public void setCatchType(int catchType) 
	{
		this.catchType = catchType;
	}
	
	public ConstantPoolTypeClass getCatchTypeObject() 
	{
		return catchTypeObject;
	}

	public void setCatchTypeObject(ConstantPoolTypeClass catchTypeObject) 
	{
		this.catchTypeObject = catchTypeObject;
	}
	
	public SingleInstruction getStartPcInstruction()
	{
		return startPcInstruction;
	}

	public void setStartPcInstruction(SingleInstruction startPcInstruction) 
	{
		this.startPcInstruction = startPcInstruction;
	}

	public SingleInstruction getEndPcInstruction()
	{
		return endPcInstruction;
	}

	public void setEndPcInstruction(SingleInstruction endPcInstruction)
	{
		this.endPcInstruction = endPcInstruction;
	}

	public SingleInstruction getHandlerPcInstruction() 
	{
		return handlerPcInstruction;
	}

	public void setHandlerPcInstruction(SingleInstruction handlerPcInstruction) 
	{
		this.handlerPcInstruction = handlerPcInstruction;
	}

	public static ExceptionTableEntry deserialize(DataInputStream dis, ConstantPool constantPool, MethodInstructions disassembly) throws IOException
    {
		ExceptionTableEntry exceptionTableEntry = new ExceptionTableEntry();
		
		exceptionTableEntry.setStartPc(dis.readUnsignedShort());
		exceptionTableEntry.setEndPc(dis.readUnsignedShort());
		exceptionTableEntry.setHandlerPc(dis.readUnsignedShort());
		exceptionTableEntry.setCatchType(dis.readUnsignedShort());		
		
		decoupleFromIndices(exceptionTableEntry, constantPool);
		decoupleFromOffsets(exceptionTableEntry, disassembly);
	    
		return exceptionTableEntry;
    }
	
	private static void decoupleFromIndices(ExceptionTableEntry ete, ConstantPool constantPool)
	{
		ete.setCatchTypeObject((ConstantPoolTypeClass)ConstantPool.cpeByIndex(constantPool, ete.catchType));
		ete.setCatchType(0);
	}
	
	private static void coupleToIndices(SerCtx ctx, ExceptionTableEntry exceptionTableEntry)
	{
		int catchType = ConstantPool.indexByCPE(ctx.getConstantPool(), exceptionTableEntry.getCatchTypeObject());
		exceptionTableEntry.setCatchType(catchType);
	}
	
	protected static void decoupleFromOffsets(ExceptionTableEntry exceptionTableEntry, MethodInstructions disassembly)
	{
		SingleInstruction startPcInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, exceptionTableEntry.startPc);
		exceptionTableEntry.setStartPcInstruction(startPcInstruction);
		
		SingleInstruction endPcInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, exceptionTableEntry.endPc);
		exceptionTableEntry.setEndPcInstruction(endPcInstruction);
		
		SingleInstruction handlerPcInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, exceptionTableEntry.handlerPc);
		exceptionTableEntry.setHandlerPcInstruction(handlerPcInstruction);
	}
	
	protected static void coupleToOffsets(SerCtx ctx, ExceptionTableEntry exceptionTableEntry, MethodInstructions disassembly)
	{
		int startPc = MethodInstructions.getInstructionOffset(exceptionTableEntry.getStartPcInstruction(), disassembly);
		exceptionTableEntry.setStartPc(startPc);
		
		int endPc = MethodInstructions.getInstructionOffset(exceptionTableEntry.getEndPcInstruction(), disassembly);
		exceptionTableEntry.setEndPc(endPc);
		
		int handlerPc = MethodInstructions.getInstructionOffset(exceptionTableEntry.getHandlerPcInstruction(), disassembly);
		exceptionTableEntry.setHandlerPc(handlerPc);
	}
	
	public static byte[] serialize(SerCtx ctx, ExceptionTableEntry exceptionTableEntry, MethodInstructions disassembly) throws IOException
	{
		coupleToIndices(ctx, exceptionTableEntry);
		coupleToOffsets(ctx, exceptionTableEntry, disassembly);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(exceptionTableEntry.getStartPc(),   Short.class));
		baos.write(PNC.toByteArray(exceptionTableEntry.getEndPc(),     Short.class));
		baos.write(PNC.toByteArray(exceptionTableEntry.getHandlerPc(), Short.class));
		baos.write(PNC.toByteArray(exceptionTableEntry.getCatchType(), Short.class));
		
		return baos.toByteArray();
	}
}
