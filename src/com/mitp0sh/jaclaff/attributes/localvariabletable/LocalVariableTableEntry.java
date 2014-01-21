package com.mitp0sh.jaclaff.attributes.localvariabletable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.attributes.code.bytecode.MethodInstructions;
import com.mitp0sh.jaclaff.attributes.code.bytecode.SingleInstruction;
import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class LocalVariableTableEntry extends AbstractReference
{
	private int        startPc = 0;
	private int         length = 0;
	private int      nameIndex = 0;
	private int signatureIndex = 0;
	private int          index = 0;
	
	private SingleInstruction startPcInstruction = null;
	
	private ConstantPoolTypeUtf8      nameObject = null;
	private ConstantPoolTypeUtf8 signatureObject = null;

	public int getStartPc() 
	{
		return startPc;
	}

	public void setstartPc(int startPc) 
	{
		this.startPc = startPc;
	}

	public int getLength() 
	{
		return length;
	}

	public void setLength(int length) 
	{
		this.length = length;
	}

	public int getNameIndex() 
	{
		return nameIndex;
	}

	public void setNameIndex(int nameIndex) 
	{
		this.nameIndex = nameIndex;
	}

	public int getSignatureIndex() 
	{
		return signatureIndex;
	}

	public void setSignatureIndex(int signatureIndex) 
	{
		this.signatureIndex = signatureIndex;
	}
	
	public int getIndex() 
	{
		return index;
	}
	
	public void setIndex(int index) 
	{
		this.index = index;
	}
	
	public SingleInstruction getStartPcInstruction() 
	{
		return startPcInstruction;
	}

	public void setStartPcInstruction(SingleInstruction startPcInstruction) 
	{
		this.startPcInstruction = startPcInstruction;
	}
	
	public ConstantPoolTypeUtf8 getNameObject() 
	{
		return nameObject;
	}

	public void setNameObject(ConstantPoolTypeUtf8 object) 
	{
		this.nameObject = object;
		
		if(object != null)
		{
			this.setNameIndex(0);
			this.addReference(object);
		}
	}

	public ConstantPoolTypeUtf8 getSignatureObject() 
	{
		return signatureObject;
	}

	public void setSignatureObject(ConstantPoolTypeUtf8 object) 
	{
		this.signatureObject = object;
		
		if(object != null)
		{
			this.setSignatureIndex(0);
			this.addReference(object);
		}
	}

	public static LocalVariableTableEntry deserialize(DesCtx ctx, AttributeCode attributeCode) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		LocalVariableTableEntry localVariableTableEntry = new LocalVariableTableEntry();
		
		localVariableTableEntry.setstartPc(dis.readUnsignedShort());
		localVariableTableEntry.setLength(dis.readUnsignedShort());
		localVariableTableEntry.setNameIndex(dis.readUnsignedShort());
		localVariableTableEntry.setSignatureIndex(dis.readUnsignedShort());
		localVariableTableEntry.setIndex(dis.readUnsignedShort());
	    
		decoupleFromIndices(ctx, localVariableTableEntry);
		decoupleFromOffsets(ctx, localVariableTableEntry, attributeCode.getCode());
		
		return localVariableTableEntry;
    }
	
	private static void decoupleFromIndices(DesCtx ctx, LocalVariableTableEntry e)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int nameIndex = e.getNameIndex();
		AbstractConstantPoolType acptNameObject = ConstantPool.cpeByIndex(cp, nameIndex);
		ConstantPoolTypeUtf8 nameObject = (ConstantPoolTypeUtf8)acptNameObject; 
		e.setNameObject(nameObject);		
		
		int signatureIndex = e.getSignatureIndex();
		AbstractConstantPoolType acptSignatureObject = ConstantPool.cpeByIndex(cp, signatureIndex);
		ConstantPoolTypeUtf8 signatureObject = (ConstantPoolTypeUtf8)acptSignatureObject;
		e.setSignatureObject(signatureObject);
	}
	
	private static void coupleWithIndices(SerCtx ctx, LocalVariableTableEntry e)
	{
		/* retrieve constant pool */
		ConstantPool cp = ctx.getConstantPool();
		
		/* get objects */
		ConstantPoolTypeUtf8 nameObject      = e.getNameObject();
		ConstantPoolTypeUtf8 signatureObject = e.getSignatureObject();
		
	    int nameIndex = ConstantPool.indexByCPE(cp, nameObject);
	    e.setNameIndex(nameIndex);
	    
	    int signatureIndex = ConstantPool.indexByCPE(cp, signatureObject);
	    e.setSignatureIndex(signatureIndex);
	}
	
	protected static void decoupleFromOffsets(DesCtx ctx, LocalVariableTableEntry localVariableTableEntry, MethodInstructions disassembly)
	{
		SingleInstruction startPcInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, localVariableTableEntry.startPc);
		localVariableTableEntry.setStartPcInstruction(startPcInstruction);
	}
	
	protected static void coupleToOffsets(SerCtx ctx, LocalVariableTableEntry localVariableTableEntry, MethodInstructions disassembly)
	{
		int startPc = MethodInstructions.getInstructionOffset(localVariableTableEntry.getStartPcInstruction(), disassembly);
		localVariableTableEntry.setstartPc(startPc);
	}
	
	public static byte[] serialize(SerCtx ctx, LocalVariableTableEntry localVariableTableEntry, AttributeCode attributeCode) throws IOException
	{
		coupleWithIndices(ctx, localVariableTableEntry);
		coupleToOffsets(ctx, localVariableTableEntry, attributeCode.getCode());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(localVariableTableEntry.getStartPc(),        Short.class));
		baos.write(PNC.toByteArray(localVariableTableEntry.getLength(),         Short.class));
		baos.write(PNC.toByteArray(localVariableTableEntry.getNameIndex(),      Short.class));
		baos.write(PNC.toByteArray(localVariableTableEntry.getSignatureIndex(), Short.class));
		baos.write(PNC.toByteArray(localVariableTableEntry.getIndex(),          Short.class));
				
		return baos.toByteArray();
	}
}
