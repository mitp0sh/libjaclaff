package com.mitp0sh.jaclaff.attributes.code.bytecode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class SingleInstruction
{
	private ByteCode         byteCode = null;
	private int                offset = 0;
	private int              operand1 = 0;
	private int              operand2 = 0;
	private LookupSwitch lookupSwitch = null;
	private TableSwitch   tableSwitch = null;
	private Boolean      isWidePrefix = false;

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
	
	public static byte[] serialize(SingleInstruction instruction, int offset) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(ByteCode.serialize(instruction, offset));
		
		return baos.toByteArray();
	}
}