package com.mitp0sh.jaclaff.attributes.code.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;
import com.mitp0sh.jaclaff.attributes.code.bytecode.defs.BasicTypes;
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
	
	public static byte[] serialize(SingleInstruction instruction, int offset) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(ByteCode.serialize(instruction, offset));
		
		return baos.toByteArray();
	}
}