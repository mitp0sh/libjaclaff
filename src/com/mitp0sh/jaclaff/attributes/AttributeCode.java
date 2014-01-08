package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.code.ExceptionTable;
import com.mitp0sh.jaclaff.attributes.code.bytecode.Disassembler;
import com.mitp0sh.jaclaff.attributes.code.bytecode.MethodInstructions;
import com.mitp0sh.jaclaff.attributes.code.bytecode.SingleInstruction;
import com.mitp0sh.jaclaff.attributes.linenumbertable.LineNumberTableEntry;
import com.mitp0sh.jaclaff.attributes.localvariabletable.LocalVariableTableEntry;
import com.mitp0sh.jaclaff.attributes.localvariabletypetable.LocalVariableTypeTableEntry;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class AttributeCode extends AbstractAttribute
{
	private int                         maxStack = 0;
	private int                        maxLocals = 0;
	private long                      codeLength = 0;
	private MethodInstructions              code = null;
	private int             exceptionTableLength = 0;
	private ExceptionTable        exceptionTable = null;
	private int                  attributesCount = 0;
	private Attributes                attributes = null;
	
	public String getAttributeName()
	{
		return AbstractAttribute.attributeCode;		
	}
	
	public int getMaxStack()
	{
		return this.maxStack;
	}

	public void setMaxStack(int maxStack) 
	{
		this.maxStack = maxStack;
	}

	public int getMaxLocals()
	{
		return this.maxLocals;
	}

	public void setMaxLocals(int maxLocals)
	{
		this.maxLocals = maxLocals;
	}

	public long getCodeLength()
	{
		return this.codeLength;
	}

	public void setCodeLength(long codeLength)
	{
		this.codeLength = codeLength;
	}

	public MethodInstructions getCode()
	{
		return this.code;
	}

	public void setCode(MethodInstructions code)
	{
		this.code = code;
	}

	public int getExceptionTableLength()
	{
		return this.exceptionTableLength;
	}

	public void setExceptionTableLength(int exceptionTableLength)
	{
		this.exceptionTableLength = exceptionTableLength;
	}

	public ExceptionTable getExceptionTable()
	{
		return this.exceptionTable;
	}

	public void setExceptionTable(ExceptionTable exceptionTable)
	{
		this.exceptionTable = exceptionTable;
	}

	public int getAttributesCount()
	{
		return this.attributesCount;
	}

	public void setAttributesCount(int attributesCount)
	{
		this.attributesCount = attributesCount;
	}

	public Attributes getAttributes()
	{
		return this.attributes;
	}

	public void setAttributes(Attributes attributes)
	{
		this.attributes = attributes;
	}
	
	public static AttributeCode deserialize(DesCtx ctx, Object reference0) throws IOException
    {
		ConstantPool constantPool = ctx.getConstantPool();
		DataInputStream dis = ctx.getDataInputStream();
		
	    AttributeCode attribute = new AttributeCode();
	    
	    attribute.setMaxStack(dis.readUnsignedShort());	    
	    attribute.setMaxLocals(dis.readUnsignedShort());	    
	    
	    attribute.setCodeLength(dis.readInt());
	    attribute.setCode(Disassembler.disassemble(dis, attribute.getCodeLength()));
	    
	    attribute.setExceptionTableLength(dis.readUnsignedShort());
	    if(attribute.getExceptionTableLength() > 0)
	    {
	    	attribute.setExceptionTable(ExceptionTable.deserialize(dis, attribute.getExceptionTableLength(), constantPool, attribute.getCode()));
	    }	    
	    
	    attribute.setAttributesCount(dis.readUnsignedShort());
	    if(attribute.getAttributesCount() > 0)
	    {
	    	attribute.setAttributes(Attributes.deserialize(ctx, attribute.getAttributesCount(), attribute));	
	    }	    
	    
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeCode attribute) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(attribute.getMaxStack(), Short.class));
		baos.write(PNC.toByteArray(attribute.getMaxLocals(), Short.class));
		baos.write(PNC.toByteArray(attribute.getCodeLength(), Integer.class));
		baos.write(MethodInstructions.serialize(attribute.getCode()));
		baos.write(PNC.toByteArray(attribute.getExceptionTableLength(), Short.class));
		if(attribute.getExceptionTableLength() > 0)
	    {
			baos.write(ExceptionTable.serialize(ctx, attribute.getExceptionTable(), attribute.getCode()));
	    }
		baos.write(PNC.toByteArray(attribute.getAttributesCount(), Short.class));
		if(attribute.getAttributesCount() > 0)
	    {
			baos.write(Attributes.serialize(ctx, attribute.getAttributes()));
	    }
		
		return baos.toByteArray();
	}
	
	public static void decoupleFromOffsets(Object             	element, 
			                               MethodInstructions 	methodInstructions, 
			                               ConstantPool       	constantPool)
	{
		if("de.microshield.jcfl.attributes.Attributes".equals(element.getClass().getCanonicalName()))
		{
			Attributes attributes = (Attributes)element;
			
			/* iterate attributes to find relevant ones */
			for(int i = 0; i < attributes.getAttributesCount(); i++)
			{
				String attributeName = attributes.getAttributes()[i].getAttributeNameObject().getBytes();
				
				if(AbstractAttribute.attributeLineNumberTable.equals(attributeName))
				{
					AttributeLineNumberTable attribute = (AttributeLineNumberTable)attributes.getAttributes()[i];
				
					/* iterate all line number table entries */
					for(int x = 0; x < attribute.getLineNumberTableLength(); x++)
					{
						LineNumberTableEntry lineNumberTableEntry;
						lineNumberTableEntry = attribute.getLineNumberTable().getLineNumberTable()[i];
						
						/* iterate all instructions to find the instruction we need */
						for(int y = 0; y < methodInstructions.getNumberOfInstructions(); y++)
						{
							SingleInstruction currentInstruction = methodInstructions.getInstructions()[y];
							
							if(lineNumberTableEntry.getStartPc() == currentInstruction.getOffset())
							{
								lineNumberTableEntry.setInstructionIndexStartPc(y);
								break;
							}	
						}
					}
				}
				else
				if(AbstractAttribute.attributeLocalVariableTable.equals(attributeName))
				{
					AttributeLocalVariableTable attribute = (AttributeLocalVariableTable)attributes.getAttributes()[i];
				
					/* iterate all line number table entries */
					for(int x = 0; x < attribute.getLocalVariableTable().getLocalVariableTable().length; x++)
					{
						LocalVariableTableEntry localVariableTableEntry;
						localVariableTableEntry = attribute.getLocalVariableTable().getLocalVariableTable()[x];
						
						/* iterate all instructions to find the instruction we need */
						for(int y = 0; y < methodInstructions.getNumberOfInstructions(); y++)
						{
							SingleInstruction currentInstruction = methodInstructions.getInstructions()[y];
							
							if(localVariableTableEntry.getStart_pc() == currentInstruction.getOffset())
							{
								localVariableTableEntry.setInstructionIndexStartPc(y);
								break;
							}	
						}
					}
				}
				else
				if(AbstractAttribute.attributeLocalVariableTypeTable.equals(attributeName))
				{
					AttributeLocalVariableTypeTable attribute = (AttributeLocalVariableTypeTable)attributes.getAttributes()[i];
				
					/* iterate all line number table entries */
					for(int x = 0; x < attribute.getLocalVariableTypeTable().getLocalVariableTypeTable().length; x++)
					{
						LocalVariableTypeTableEntry localVariableTypeTableEntry;
						localVariableTypeTableEntry = attribute.getLocalVariableTypeTable().getLocalVariableTypeTable()[x];
						
						/* iterate all instructions to find the instruction we need */
						for(int y = 0; y < methodInstructions.getNumberOfInstructions(); y++)
						{
							SingleInstruction currentInstruction = methodInstructions.getInstructions()[y];
							
							if(localVariableTypeTableEntry.getStart_pc() == currentInstruction.getOffset())
							{
								localVariableTypeTableEntry.setInstructionIndexStartPc(y);
								break;
							}	
						}
					}
				}
			}
		}
	}
}
