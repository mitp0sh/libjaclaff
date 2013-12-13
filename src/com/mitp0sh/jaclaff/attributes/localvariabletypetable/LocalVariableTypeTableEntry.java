package com.mitp0sh.jaclaff.attributes.localvariabletypetable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class LocalVariableTypeTableEntry 
{
	private short              start_pc = 0;
	private short                length = 0;
	private short            name_index = 0;
	private short       signature_index = 0;
	private short                 index = 0;
	
	/* additional info */
	private int instructionIndexStartPc = 0;
	
	public short getStart_pc() 
	{
		return start_pc;
	}

	public void setStart_pc(short startPc) 
	{
		start_pc = startPc;
	}

	public short getLength() 
	{
		return length;
	}

	public void setLength(short length) 
	{
		this.length = length;
	}

	public short getName_index() 
	{
		return name_index;
	}

	public void setName_index(short nameIndex) 
	{
		name_index = nameIndex;
	}

	public short getSignature_index() 
	{
		return signature_index;
	}

	public void setSignature_index(short signatureIndex) 
	{
		signature_index = signatureIndex;
	}
	
	public short getIndex() 
	{
		return index;
	}
	
	public void setIndex(short index) 
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

	public static LocalVariableTypeTableEntry deserialize(DataInputStream dis) throws IOException
    {
		LocalVariableTypeTableEntry localVariableTypeTableEntry = new LocalVariableTypeTableEntry();
		
		localVariableTypeTableEntry.setStart_pc((short)dis.readUnsignedShort());
		localVariableTypeTableEntry.setLength((short)dis.readUnsignedShort());
		localVariableTypeTableEntry.setName_index((short)dis.readUnsignedShort());
		localVariableTypeTableEntry.setSignature_index((short)dis.readUnsignedShort());
		localVariableTypeTableEntry.setIndex((short)dis.readUnsignedShort());
	    
		return localVariableTypeTableEntry;
    }
	
	public static byte[] serialize(LocalVariableTypeTableEntry localVariableTypeTableEntry) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getStart_pc(), Short.class));
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getLength(), Short.class));
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getName_index(), Short.class));
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getSignature_index(), Short.class));
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getIndex(), Short.class));
				
		return baos.toByteArray();
	}
}
