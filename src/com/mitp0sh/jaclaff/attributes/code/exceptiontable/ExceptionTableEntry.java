package com.mitp0sh.jaclaff.attributes.code.exceptiontable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.code.AbstractInstruction;
import com.mitp0sh.jaclaff.attributes.code.Disassembly;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ExceptionTableEntry 
{
	private int                              startPc = 0;
	private int                                endPc = 0;
	private int                            handlerPc = 0;
	private int                            catchType = 0;
	private ConstantPoolTypeClass    catchTypeObject = null;
	private AbstractInstruction   startPcInstruction = null;
	private AbstractInstruction     endPcInstruction = null;
	private AbstractInstruction handlerPcInstruction = null;
	
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
	
	public AbstractInstruction getStartPcInstruction()
	{
		return startPcInstruction;
	}

	public void setStartPcInstruction(AbstractInstruction startPcInstruction) 
	{
		this.startPcInstruction = startPcInstruction;
	}

	public AbstractInstruction getEndPcInstruction()
	{
		return endPcInstruction;
	}

	public void setEndPcInstruction(AbstractInstruction endPcInstruction)
	{
		this.endPcInstruction = endPcInstruction;
	}

	public AbstractInstruction getHandlerPcInstruction() 
	{
		return handlerPcInstruction;
	}

	public void setHandlerPcInstruction(AbstractInstruction handlerPcInstruction) 
	{
		this.handlerPcInstruction = handlerPcInstruction;
	}

	public static ExceptionTableEntry deserialize(DataInputStream dis, ConstantPool constantPool, Disassembly disassembly) throws IOException
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
	
	protected static void decoupleFromOffsets(ExceptionTableEntry exceptionTableEntry, Disassembly disassembly)
	{
		AbstractInstruction startPcInstruction = disassembly.getInstruction(exceptionTableEntry.getStartPc());
		exceptionTableEntry.setStartPcInstruction(startPcInstruction);
		
		AbstractInstruction endPcInstruction = disassembly.getInstruction(exceptionTableEntry.getEndPc());
		exceptionTableEntry.setEndPcInstruction(endPcInstruction);
		
		AbstractInstruction handlerPcInstruction = disassembly.getInstruction(exceptionTableEntry.getHandlerPc());
		exceptionTableEntry.setHandlerPcInstruction(handlerPcInstruction);
	}
	
	protected static void coupleToOffsets(SerCtx ctx, ExceptionTableEntry exceptionTableEntry, Disassembly disassembly)
	{
		int startPc = disassembly.getInstructionOffset(exceptionTableEntry.getStartPcInstruction());
		exceptionTableEntry.setStartPc(startPc);
		
		int endPc = disassembly.getInstructionOffset(exceptionTableEntry.getEndPcInstruction());
		exceptionTableEntry.setEndPc(endPc);
		
		int handlerPc = disassembly.getInstructionOffset(exceptionTableEntry.getHandlerPcInstruction());
		exceptionTableEntry.setHandlerPc(handlerPc);
	}
	
	public static byte[] serialize(SerCtx ctx, ExceptionTableEntry exceptionTableEntry, Disassembly disassembly) throws IOException
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
