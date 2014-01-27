package com.mitp0sh.jaclaff.attributes.code.deprecated;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.code.Mnemonics;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;



public class ByteCode
{
	public static final String FORMAT_B      = "b";
	public static final String FORMAT_BC     = "bc";
	public static final String FORMAT_BCC    = "bcc";
	public static final String FORMAT_BI     = "bi";
	public static final String FORMAT_WBII   = "wbii";
	public static final String FORMAT_BII    = "bii";	
	public static final String FORMAT_BIC    = "bic";
	public static final String FORMAT_WBIICC = "wbiicc";
	public static final String FORMAT_BIIC   = "biic";
	public static final String FORMAT_BOO    = "boo";
	public static final String FORMAT_BOOOO  = "boooo";
	public static final String FORMAT_BJJ    = "bjj";
	public static final String FORMAT_BJJ__  = "bjj__";
	public static final String FORMAT_BJJJJ  = "bjjjj";	
	public static final String FORMAT_BLANK  = "";
	
	private byte               byteCode = 0;
	private String         byteCodeName = null;
	private String               format = null;
	private String           wideFormat = null;
	private byte              basicType = 0;
	private int        stackConsumption = 0;
	private boolean             canTrap = false;
	private String         standardCode = null;
	private short        physicalLength = 0;

	public ByteCode(byte        byteCode, 
			        String      byteCodeName, 
			        String      format,
			        String      wideFormat, 
			        byte        basicType, 
			        int         stackConsumption,
			        boolean     canTrap, 
			        String      standardCode)
	{
		this.byteCode         = byteCode;
		this.byteCodeName     = byteCodeName;
		this.format           = format;
		this.wideFormat       = wideFormat;
		this.basicType        = basicType;
		this.stackConsumption = stackConsumption;
		this.canTrap          = canTrap;
		this.standardCode     = standardCode;
	}

	public byte getByteCode() 
	{
		return byteCode;
	}

	public String getByteCodeName()
	{
		return byteCodeName;
	}

	public String getFormat()
	{
		return format;
	}

	public String getWideFormat()
	{
		return wideFormat;
	}

	public byte getBasicType() 
	{
		return basicType;
	}

	public int getStackConsumption()
	{
		return stackConsumption;
	}

	public boolean isCanTrap()
	{
		return canTrap;
	}

	public String getStandardCode() 
	{
		return standardCode;
	}
	
	public short getPhysicalLength() 
	{
		return physicalLength;
	}

	public void setPhysicalLength(short physicalLength) 
	{
		this.physicalLength = physicalLength;
	}
	
	public static byte[] serialize(SerCtx ctx, MethodInstructions disassembly, SingleInstruction instruction, int offset) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		String format = "";
		
		if(instruction.hasWidePrefix())
		{
			format = instruction.getByteCode().getWideFormat();
		}
		else
		{
			format = instruction.getByteCode().getFormat();
		}
		
		if(format.equals(FORMAT_B))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
		}
		else
		if(format.equals(FORMAT_BC) ||
		   format.equals(FORMAT_BI))
		{
			SingleInstruction.coupleWithOffsets(ctx, disassembly, instruction);
			
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			baos.write(new byte[]{(byte)instruction.getOperand1()});
		}
		else
		if(format.equals(FORMAT_BCC) ||
		   format.equals(FORMAT_BII) ||
		   format.equals(FORMAT_BOO) || 
		   format.equals(FORMAT_BJJ))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			baos.write(PNC.toByteArray((short)instruction.getOperand1(), Short.class));
		}
		else
		if(format.equals(FORMAT_WBII))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			baos.write(PNC.toByteArray((short)instruction.getOperand1(), Short.class));
		}
		else
		if(format.equals(FORMAT_BIC))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			baos.write(new byte[]{(byte)instruction.getOperand1()});
			baos.write(new byte[]{(byte)instruction.getOperand2()});
		}
		else
		if(format.equals(FORMAT_WBIICC))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			baos.write(PNC.toByteArray((short)instruction.getOperand1(), Short.class));
			baos.write(PNC.toByteArray((short)instruction.getOperand2(), Short.class));
		}
		else
		if(format.equals(FORMAT_BIIC))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			baos.write(PNC.toByteArray((short)instruction.getOperand1(), Short.class));
			baos.write(new byte[]{(byte)instruction.getOperand2()});
		}
		else
		if(format.equals(FORMAT_BOOOO) ||
		   format.equals(FORMAT_BJJJJ))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			baos.write(PNC.toByteArray((int)instruction.getOperand1(), Integer.class));
		}
		else
		if(format.equals(FORMAT_BJJ__))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			baos.write(PNC.toByteArray((short)instruction.getOperand1(), Short.class));
			baos.write(new byte[]{(byte)0});
			baos.write(new byte[]{(byte)0});
		}
		else
		if(format.equals(FORMAT_BLANK))
		{
			baos.write(new byte[]{instruction.getByteCode().getByteCode()});
			
			if(instruction.getByteCode().byteCode == Mnemonics.BC_lookupswitch)
			{
				baos.write(LookupSwitch.serialize(disassembly, instruction.getLookupSwitch(), offset));
			}
			else
			if(instruction.getByteCode().byteCode == Mnemonics.BC_tableswitch)
			{
				baos.write(TableSwitch.serialize(disassembly, instruction.getTableSwitch(), offset));
			}
			else
			if(instruction.getByteCode().byteCode == Mnemonics.BC_wide)
			{
				/* nothing to do here */
			}
			else
			{
				System.out.println("Strange opcode found !?");
			}
		}
		
		return baos.toByteArray();
	}
}
