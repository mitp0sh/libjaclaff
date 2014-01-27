package com.mitp0sh.jaclaff.attributes.localvariabletypetable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.attributes.code.AbstractInstruction;
import com.mitp0sh.jaclaff.attributes.code.Disassembly;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeUtf8;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class LocalVariableTypeTableEntry extends AbstractReference
{
	private int             startPC = 0;
	private int              length = 0;
	private int           nameIndex = 0;
	private int      signatureIndex = 0;
	private int               index = 0;
	
	private AbstractInstruction startPcInstruction = null;
	private ConstantPoolTypeUtf8        nameObject = null;
	private ConstantPoolTypeUtf8   signatureObject = null;

	public int getStartPC() 
	{
		return startPC;
	}

	public void setStartPC(int startPc) 
	{
		this.startPC = startPc;
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
	
	public AbstractInstruction getStartPcInstruction()
	{
		return startPcInstruction;
	}

	public void setStartPcInstruction(AbstractInstruction startPcInstruction)
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

	public static LocalVariableTypeTableEntry deserialize(DesCtx ctx, AttributeCode attributeCode) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		LocalVariableTypeTableEntry localVariableTypeTableEntry = new LocalVariableTypeTableEntry();
		
		localVariableTypeTableEntry.setStartPC(dis.readUnsignedShort());
		localVariableTypeTableEntry.setLength(dis.readUnsignedShort());
		localVariableTypeTableEntry.setNameIndex(dis.readUnsignedShort());
		localVariableTypeTableEntry.setSignatureIndex(dis.readUnsignedShort());
		localVariableTypeTableEntry.setIndex(dis.readUnsignedShort());
		
		decoupleFromIndices(ctx, localVariableTypeTableEntry);
		decoupleFromOffsets(ctx, localVariableTypeTableEntry, attributeCode.getCode());
		
		return localVariableTypeTableEntry;
    }
	
	private static void decoupleFromIndices(DesCtx ctx, LocalVariableTypeTableEntry entry)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		entry.setNameObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(cp, entry.nameIndex));
		entry.setNameIndex(0);
		
		entry.setSignatureObject((ConstantPoolTypeUtf8)ConstantPool.cpeByIndex(cp, entry.signatureIndex));
		entry.setSignatureIndex(0);
	}
	
	private static void coupleToIndices(SerCtx ctx, LocalVariableTypeTableEntry entry)
	{
		/* retrieve constant pool */
		ConstantPool cp = ctx.getConstantPool();
		
		/* get objects */
		ConstantPoolTypeUtf8 nameObject      = entry.getNameObject();
		ConstantPoolTypeUtf8 signatureObject = entry.getSignatureObject();
		
	    int nameIndex = ConstantPool.indexByCPE(cp, nameObject);
	    entry.setNameIndex(nameIndex);
	    
	    int signatureIndex = ConstantPool.indexByCPE(cp, signatureObject);
	    entry.setSignatureIndex(signatureIndex);
	}
	
	protected static void decoupleFromOffsets(DesCtx ctx, LocalVariableTypeTableEntry localVariableTypeTableEntry, Disassembly disassembly)
	{
		AbstractInstruction startPcInstruction = disassembly.getInstruction(localVariableTypeTableEntry.getStartPC());
		localVariableTypeTableEntry.setStartPcInstruction(startPcInstruction);
	}
	
	protected static void coupleToOffsets(SerCtx ctx, LocalVariableTypeTableEntry localVariableTypeTableEntry, Disassembly disassembly)
	{
		int startPc = disassembly.getInstructionOffset(localVariableTypeTableEntry.getStartPcInstruction());
		localVariableTypeTableEntry.setStartPC(startPc);
	}
	
	public static byte[] serialize(SerCtx ctx, LocalVariableTypeTableEntry localVariableTypeTableEntry, AttributeCode attributeCode) throws IOException
	{
		coupleToIndices(ctx, localVariableTypeTableEntry);
		coupleToOffsets(ctx, localVariableTypeTableEntry, attributeCode.getCode());
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getStartPC(),        Short.class));
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getLength(),         Short.class));
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getNameIndex(),      Short.class));
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getSignatureIndex(), Short.class));
		baos.write(PNC.toByteArray(localVariableTypeTableEntry.getIndex(),          Short.class));
				
		return baos.toByteArray();
	}
}
