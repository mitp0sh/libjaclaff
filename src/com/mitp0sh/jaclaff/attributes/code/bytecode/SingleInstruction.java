package com.mitp0sh.jaclaff.attributes.code.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.constantpool.AbstractConstantPoolType;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;

public class SingleInstruction extends AbstractReference
{
	private ByteCode         byteCode = null;
	private int                offset = 0;
	private int              operand1 = 0;
	private int              operand2 = 0;
	private LookupSwitch lookupSwitch = null;
	private TableSwitch   tableSwitch = null;
	private Boolean      isWidePrefix = false;
	
	private AbstractConstantPoolType operand1Object = null;
	private AbstractConstantPoolType operand2Object = null;
	private SingleInstruction   operand1Instruction = null;
	private SingleInstruction   operand2Instruction = null;
	private SingleInstruction   previousInstruction = null;
	private SingleInstruction       nextInstruction = null;

	public ByteCode getByteCode() 
	{
		return byteCode;
	}

	public void setByteCode(ByteCode byteCode) 
	{
		this.byteCode = byteCode;
	}
	
	public int getOffset()
	{
		return offset;
	}

	public void seOffset(int offset)
	{
		this.offset = offset;
	}
	
	public int getOperand1()
	{
		return operand1;
	}

	public void setOperand1(int operand1)
	{
		this.operand1 = operand1;
	}

	public int getOperand2() 
	{
		return operand2;
	}

	public void setOperand2(int operand2)
	{
		this.operand2 = operand2;
	}

	public LookupSwitch getLookupSwitch() 
	{
		return lookupSwitch;
	}

	public void setLookupSwitch(LookupSwitch lookupSwitch)
	{
		this.lookupSwitch = lookupSwitch;
	}

	public TableSwitch getTableSwitch()
	{
		return tableSwitch;
	}

	public void setTableSwitch(TableSwitch tableSwitch)
	{
		this.tableSwitch = tableSwitch;
	}
	
	public Boolean hasWidePrefix() 
	{
		return isWidePrefix;
	}

	public void setWidePrefix(Boolean isWidePrefix) 
	{
		this.isWidePrefix = isWidePrefix;
	}
	
	public AbstractConstantPoolType getOperand1Object()
	{
		return operand1Object;
	}

	public void setOperand1Object(AbstractConstantPoolType object) 
	{
		this.operand1Object = object;
		
		if(object != null)
		{
			this.setOperand1(0);
			this.addReference(object);
		}
	}

	public AbstractConstantPoolType getOperand2Object() 
	{
		return operand2Object;
	}

	public void setOperand2Object(AbstractConstantPoolType object) 
	{
		this.operand2Object = object;
		
		if(object != null)
		{
			this.setOperand2(0);
			this.addReference(object);
		}
	}
	
	public SingleInstruction getOperand1Instruction() 
	{
		return operand1Instruction;
	}

	public void setOperand1Instruction(SingleInstruction operand1Instruction) 
	{
		this.operand1Instruction = operand1Instruction;
	}

	public SingleInstruction getOperand2Instruction()
	{
		return operand2Instruction;
	}

	public void setOperand2Instruction(SingleInstruction operand2Instruction)
	{
		this.operand2Instruction = operand2Instruction;
	}
	
	public SingleInstruction getPreviousInstruction() 
	{
		return previousInstruction;
	}

	public void setPreviousInstruction(SingleInstruction previousInstruction) 
	{
		this.previousInstruction = previousInstruction;
	}

	public SingleInstruction getNextInstruction() 
	{
		return nextInstruction;
	}

	public void setNextInstruction(SingleInstruction nextInstruction) 
	{
		this.nextInstruction = nextInstruction;
	}
	
	public int getPhysicalInstructionLength()
	{
		String format;
		
		if(hasWidePrefix())
		{
			format = getByteCode().getWideFormat();
		}
		else
		{
			format = getByteCode().getFormat();
		}
		
		if(format.equals(ByteCode.FORMAT_B))
		{
			return 1;
		}
		else
		if(format.equals(ByteCode.FORMAT_BC) ||
		   format.equals(ByteCode.FORMAT_BI))
		{
			return 2;
		}
		else
		if(format.equals(ByteCode.FORMAT_BCC) ||
		   format.equals(ByteCode.FORMAT_BII) ||
		   format.equals(ByteCode.FORMAT_BOO) || 
		   format.equals(ByteCode.FORMAT_BJJ))
		{
			return 3;
		}
		else
		if(format.equals(ByteCode.FORMAT_WBII))
		{
			return 3;
		}
		else
		if(format.equals(ByteCode.FORMAT_BIC))
		{
			return 3;
		}
		else
		if(format.equals(ByteCode.FORMAT_WBIICC))
		{
			return 5;
		}
		else
		if(format.equals(ByteCode.FORMAT_BIIC))
		{
			return 4;
		}
		else
		if(format.equals(ByteCode.FORMAT_BOOOO) ||
		   format.equals(ByteCode.FORMAT_BJJJJ))
		{
			return 5;
		}
		else
		if(format.equals(ByteCode.FORMAT_BJJ__))
		{
			return 5;
		}
		else
		if(format.equals(ByteCode.FORMAT_BLANK))
		{	
			if(getByteCode().getByteCode() == Mnemonics.BC_wide)
			{
				return 1;
			}
			else
			if(getByteCode().getByteCode() == Mnemonics.BC_lookupswitch)
			{
				try
				{
					return LookupSwitch.serialize(getLookupSwitch(), offset).length + 1;
				}
				catch(IOException e)
				{				
					e.printStackTrace();
				}
			}
			else
			if(getByteCode().getByteCode() == Mnemonics.BC_tableswitch)
			{
				try
				{
					return TableSwitch.serialize(getTableSwitch(), offset).length + 1;
				}
				catch(IOException e)
				{	
					e.printStackTrace();
				}
			}
		}
		
		return 0;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, SingleInstruction instruction)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		ByteCode byteCode = instruction.getByteCode();
		if(byteCode.getFormat().equals(ByteCode.FORMAT_BJJ))
		{
			AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, instruction.getOperand1());
			instruction.setOperand1Object(acpt);
		}
		else
		if(byteCode.getFormat().equals(ByteCode.FORMAT_BI) ||
		   byteCode.getFormat().equals(ByteCode.FORMAT_WBII))
		{
			byte bt = byteCode.getBasicType();
			if(bt == BasicTypes.T_INT    ||
			   bt == BasicTypes.T_LONG   ||
			   bt == BasicTypes.T_FLOAT  ||
			   bt == BasicTypes.T_DOUBLE ||
			   bt == BasicTypes.T_VOID)
			{
				return;
			}
			
			AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, instruction.getOperand1());
			instruction.setOperand1Object(acpt);
		}
		else
		if(byteCode.getFormat().equals(ByteCode.FORMAT_BII))
		{
			AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, instruction.getOperand1());
			instruction.setOperand1Object(acpt);
		}
		else
		if(byteCode.getFormat().equals(ByteCode.FORMAT_BC))
		{
			byte bt = byteCode.getBasicType();
			if(bt != BasicTypes.T_OBJECT)
			{
				return;
			}
			
			AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, instruction.getOperand1());
			instruction.setOperand1Object(acpt);
		}
		else
		if(byteCode.getFormat().equals(ByteCode.FORMAT_BIIC))
		{
			AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, instruction.getOperand1());
			instruction.setOperand1Object(acpt);
		}
		else
		if(byteCode.getFormat().equals(ByteCode.FORMAT_BJJ__))
		{
			AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, instruction.getOperand1());
			instruction.setOperand1Object(acpt);
		}
		else
		if(byteCode.getFormat().equals(ByteCode.FORMAT_BJJJJ))
		{
			AbstractConstantPoolType acpt = ConstantPool.cpeByIndex(cp, instruction.getOperand1());
			instruction.setOperand1Object(acpt);
		}
	}
	
	public static void decoupleFromOffsets(MethodInstructions disassembly, SingleInstruction instruction)
	{
		ByteCode bc = instruction.getByteCode();
		if(bc.getFormat().equals(ByteCode.FORMAT_BOO) ||
		   bc.getFormat().equals(ByteCode.FORMAT_BOOOO))
		{
			int targetOffset = instruction.getOffset() + instruction.getOperand1();
			SingleInstruction targetInstruction = null;
			targetInstruction = MethodInstructions.lookupInstructionByOffset(disassembly, targetOffset);
			if(targetInstruction == null)
			{
				System.err.println("error - unable to lookup instruction by offset !!!");
				System.exit(-1);
			}
			
			instruction.setOperand1Instruction(targetInstruction);
			instruction.setOperand1(0);
		}
	}
	
	public static byte[] serialize(SingleInstruction instruction, int offset) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(ByteCode.serialize(instruction, offset));
		
		return baos.toByteArray();
	}
}