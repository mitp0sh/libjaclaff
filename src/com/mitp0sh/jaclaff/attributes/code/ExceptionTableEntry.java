package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.util.PNC;


public class ExceptionTableEntry 
{
	private short                         startPc = 0;
	private short                           endPc = 0;
	private short                       handlerPc = 0;
	private short                       catchType = 0;
	private ConstantPoolTypeClass catchTypeObject = null;

	/* additional information */
	private int         instructionIndexStartPc = 0;
	private int           instructionIndexEndPc = 0;
	private int       instructionIndexHandlerPc = 0;
	
	public short getStartPc()
	{
		return startPc;
	}

	public void setStartPc(short startPc)
	{
		this.startPc = startPc;
	}

	public short getEndPc() 
	{
		return endPc;
	}

	public void setEndPc(short endPc)
	{
		this.endPc = endPc;
	}

	public short getHandlerPc() {
		return handlerPc;
	}

	public void setHandlerPc(short handlerPc)
	{
		this.handlerPc = handlerPc;
	}

	public short getCatchType() 
	{
		return catchType;
	}

	public void setCatchType(short catchType) 
	{
		this.catchType = catchType;
	}

	public int getInstructionIndexStartPc() 
	{
		return instructionIndexStartPc;
	}

	public void setInstructionIndexStartPc(int instructionIndexStartPc)
	{
		this.instructionIndexStartPc = instructionIndexStartPc;
	}

	public int getInstructionIndexEndPc()
	{
		return instructionIndexEndPc;
	}

	public void setInstructionIndexEndPc(int instructionIndexEndPc)
	{
		this.instructionIndexEndPc = instructionIndexEndPc;
	}

	public int getInstructionIndexHandlerPc()
	{
		return instructionIndexHandlerPc;
	}

	public void setInstructionIndexHandlerPc(int instructionIndexHandlerPc)
{
		this.instructionIndexHandlerPc = instructionIndexHandlerPc;
	}
	
	public ConstantPoolTypeClass getCatchTypeObject() 
	{
		return catchTypeObject;
	}

	public void setCatchTypeObject(ConstantPoolTypeClass catchTypeObject) 
	{
		this.catchTypeObject = catchTypeObject;
	}
	
	private static void decoupleFromIndices(ExceptionTableEntry ete, ConstantPool constantPool)
	{
		ete.setCatchTypeObject((ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, ete.catchType));
		ete.setCatchType((short)0);
	}

	public static ExceptionTableEntry deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		ExceptionTableEntry exceptionTableEntry = new ExceptionTableEntry();
		
		exceptionTableEntry.setStartPc((short)dis.readUnsignedShort());
		exceptionTableEntry.setEndPc((short)dis.readUnsignedShort());
		exceptionTableEntry.setHandlerPc((short)dis.readUnsignedShort());
		exceptionTableEntry.setCatchType((short)dis.readUnsignedShort());		
		
		decoupleFromIndices(exceptionTableEntry, constantPool);
	    
		return exceptionTableEntry;
    }
	
	public static byte[] serialize(ExceptionTableEntry exceptionTableEntry) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(exceptionTableEntry.getStartPc(), Short.class));
		baos.write(PNC.toByteArray(exceptionTableEntry.getEndPc(), Short.class));
		baos.write(PNC.toByteArray(exceptionTableEntry.getHandlerPc(), Short.class));
		baos.write(PNC.toByteArray(exceptionTableEntry.getCatchType(), Short.class));
		
		return baos.toByteArray();
	}
}
