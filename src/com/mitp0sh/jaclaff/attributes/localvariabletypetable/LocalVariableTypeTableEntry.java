package com.mitp0sh.jaclaff.attributes.localvariabletypetable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.util.PNC;


public class LocalVariableTypeTableEntry 
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

	public static LocalVariableTypeTableEntry deserialize(DataInputStream dis) throws IOException
    {
		LocalVariableTypeTableEntry localVariableTypeTableEntry = new LocalVariableTypeTableEntry();
		
		localVariableTypeTableEntry.setStart_pc(dis.readUnsignedShort());
		localVariableTypeTableEntry.setLength(dis.readUnsignedShort());
		localVariableTypeTableEntry.setName_index(dis.readUnsignedShort());
		localVariableTypeTableEntry.setSignature_index(dis.readUnsignedShort());
		localVariableTypeTableEntry.setIndex(dis.readUnsignedShort());
	    
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
