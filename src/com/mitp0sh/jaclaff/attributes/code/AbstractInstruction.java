package com.mitp0sh.jaclaff.attributes.code;

import com.mitp0sh.jaclaff.abstraction.AbstractReference;

public abstract class AbstractInstruction extends AbstractReference
{	
	private String formatString                     = null;
	private int byteCodeValue                       = 0;
	private String literal                          = null;
	private byte stackHeadResultType                = BasicTypes.T_VOID;
	private int operandStackDelta                   = 0;
	private boolean canTrap                         = false;
	private int offset                              = 0;
	private AbstractInstruction previousInstruction = null;
	private AbstractInstruction nextInstruction     = null;
	private boolean wide                            = false;
	private boolean wideable                        = false;
	private Disassembly disassembly                 = null;

	public AbstractInstruction(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly)
	{		
		setByteCodeValue(byteCodeValue);
		setDisassembly(disassembly);
		initInstruction(byteCodeValue);
	
		if(previousInstruction != null)
		{
			setPreviousInstruction(previousInstruction);
			previousInstruction.setNextInstruction(this);
		}
	}
	
	public String getFormatString() 
	{
		return formatString;
	}

	public void setFormatString(String formatString) 
	{
		this.formatString = formatString;
	}
	
	public int getByteCodeValue() 
	{
		return byteCodeValue;
	}

	public void setByteCodeValue(int byteCodeValue) 
	{
		this.byteCodeValue = byteCodeValue;
	}
	
	public String getLiteral() 
	{
		return literal;
	}

	public void setLiteral(String literal) 
	{
		this.literal = literal;
	}
	
	public byte getStackHeadResultType() 
	{
		return stackHeadResultType;
	}

	public void setStackHeadResultType(byte stackHeadResultType) 
	{
		this.stackHeadResultType = stackHeadResultType;
	}
	
	public int getOperandStackDelta()
	{
		return operandStackDelta;
	}

	public void setOperandStackDelta(int operandStackDelta)
	{
		this.operandStackDelta = operandStackDelta;
	}
	
	public boolean canTrap() 
	{
		return canTrap;
	}

	public void setCanTrap(boolean canTrap) 
	{
		this.canTrap = canTrap;
	}
	
	public int getOffset()
	{
		return offset;
	}

	public void setOffset(int offset) 
	{
		this.offset = offset;
	}

	public AbstractInstruction getPreviousInstruction() 
	{
		return previousInstruction;
	}

	public void setPreviousInstruction(AbstractInstruction previousInstruction) 
	{
		this.previousInstruction = previousInstruction;
	}

	public AbstractInstruction getNextInstruction() 
	{
		return nextInstruction;
	}

	public void setNextInstruction(AbstractInstruction nextInstruction) 
	{
		this.nextInstruction = nextInstruction;
	}
	
	public boolean isWide() 
	{
		return wide;
	}

	public void setWide(boolean wide) 
	{
		this.wide = wide;
	}
	
	public boolean isWideable() 
	{
		return wideable;
	}

	public void setWideable(boolean wideable) 
	{
		this.wideable = wideable;
	}
	
	public Disassembly getDisassembly() 
	{
		return disassembly;
	}

	public void setDisassembly(Disassembly disassembly) 
	{
		this.disassembly = disassembly;
	}
	
	public abstract void initInstruction(int byteCodeValue);
	public abstract int getPhysicalSize();

	@Override
	public String toString()
	{
		return getLiteral();
	}
}
