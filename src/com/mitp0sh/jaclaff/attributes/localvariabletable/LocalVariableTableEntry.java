package com.mitp0sh.jaclaff.attributes.localvariabletable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class LocalVariableTableEntry 
{
	private int              start_pc = 0;
	private int                length = 0;
	private int            name_index = 0;
	private int       signature_index = 0;
	private int                 index = 0;
	
	/* additional info */
	private int instructionIndexStartPc = 0;
	
	public int getStart_pc() 
	{
		return start_pc;
	}

	public void setStart_pc(int startPc) 
	{
		start_pc = startPc;
	}

	public int getLength() 
	{
		return length;
	}

	public void setLength(int length) 
	{
		this.length = length;
	}

	public int getName_index() 
	{
		return name_index;
	}

	public void setName_index(int nameIndex) 
	{
		name_index = nameIndex;
	}

	public int getSignature_index() 
	{
		return signature_index;
	}

	public void setSignature_index(int signatureIndex) 
	{
		signature_index = signatureIndex;
	}
	
	public int getIndex() 
	{
		return index;
	}
	
	public void setIndex(int index) 
	{
		this.index = index;
	}
	
	public int getInstructionIndexStartPc() 
	{
		return instructionIndexStartPc;
	}

	public void setInstructionIndexStartPc(int instructionIndex) 
	{
		this.instructionIndexStartPc = instructionIndex;
	}

	public static LocalVariableTableEntry deserialize(DataInputStream dis) throws IOException
    {
		LocalVariableTableEntry localVariableTableEntry = new LocalVariableTableEntry();
		
		localVariableTableEntry.setStart_pc(dis.readUnsignedShort());
		localVariableTableEntry.setLength(dis.readUnsignedShort());
		localVariableTableEntry.setName_index(dis.readUnsignedShort());
		localVariableTableEntry.setSignature_index(dis.readUnsignedShort());
		localVariableTableEntry.setIndex(dis.readUnsignedShort());
	    
		return localVariableTableEntry;
    }
	
	public static byte[] serialize(LocalVariableTableEntry localVariableTableEntry) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(localVariableTableEntry.getStart_pc(), Short.class));
		baos.write(PNC.toByteArray(localVariableTableEntry.getLength(), Short.class));
		baos.write(PNC.toByteArray(localVariableTableEntry.getName_index(), Short.class));
		baos.write(PNC.toByteArray(localVariableTableEntry.getSignature_index(), Short.class));
		baos.write(PNC.toByteArray(localVariableTableEntry.getIndex(), Short.class));
				
		return baos.toByteArray();
	}
}
