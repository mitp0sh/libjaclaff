package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class InstructionTypeB extends AbstractInstruction
{
	public InstructionTypeB(int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly)
	{
		super(byteCodeValue, previousInstruction, disassembly);
	}

	public void initInstruction(int byteCodeValue)
	{
		/* set instruction format string */
		setFormatString(InstructionTypes.B);
		
		/* init instruction */
		switch(byteCodeValue)
		{
    	    case Mnemonics.BC_nop:
    	    {
    	    	setLiteral("nop");
    	    	break;
    	    }
    	    case Mnemonics.BC_aconst_null:
    	    {
    	    	setLiteral("aconst_null");
    	    	setStackHeadResultType(BasicTypes.T_OBJECT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iconst_m1:
    	    {
    	    	setLiteral("iconst_m1");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iconst_0:
    	    {
    	    	setLiteral("iconst_0");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iconst_1:
    	    {
    	    	setLiteral("iconst_1");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iconst_2:
    	    {
    	    	setLiteral("iconst_2");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iconst_3:
    	    {
    	    	setLiteral("iconst_3");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iconst_4:
    	    {
    	    	setLiteral("iconst_4");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iconst_5:
    	    {
    	    	setLiteral("iconst_5");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_lconst_0:
    	    {
    	    	setLiteral("lconst_0");
    	    	setStackHeadResultType(BasicTypes.T_LONG);
    	    	setOperandStackDelta(+2);
    	    	break;
    	    }
    	    case Mnemonics.BC_lconst_1:
    	    {
    	    	setLiteral("lconst_1");
    	    	setStackHeadResultType(BasicTypes.T_LONG);
    	    	setOperandStackDelta(+2);
    	    	break;
    	    }
    	    case Mnemonics.BC_fconst_0:
    	    {
    	    	setLiteral("fconst_0");
    	    	setStackHeadResultType(BasicTypes.T_FLOAT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_fconst_1:
    	    {
    	    	setLiteral("fconst_1");
    	    	setStackHeadResultType(BasicTypes.T_FLOAT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_fconst_2:
    	    {
    	    	setLiteral("fconst_2");
    	    	setStackHeadResultType(BasicTypes.T_FLOAT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_dconst_0:
    	    {
    	    	setLiteral("dconst_0");
    	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
    	    	setOperandStackDelta(+2);
    	    	break;
    	    }
    	    case Mnemonics.BC_dconst_1:
    	    {
    	    	setLiteral("dconst_1");
    	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
    	    	setOperandStackDelta(+2);
    	    	break;  
    	    }
    	    case Mnemonics.BC_iload_0:
    	    {
    	    	setLiteral("iload_0");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iload_1:
    	    {
    	    	setLiteral("iload_1");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iload_2:
    	    {
    	    	setLiteral("iload_2");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_iload_3:
    	    {
    	    	setLiteral("iload_3");
    	    	setStackHeadResultType(BasicTypes.T_INT);
    	    	setOperandStackDelta(+1);
    	    	break;
    	    }
    	    case Mnemonics.BC_lload_0: 
    	    {
    	    	setLiteral("lload_0");
    	    	setStackHeadResultType(BasicTypes.T_LONG);
    	    	setOperandStackDelta(+2);
    	    	break;
    	    }
      	    case Mnemonics.BC_lload_1:
      	    {
      	    	setLiteral("lload_1");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_lload_2: 
      	    {
      	    	setLiteral("lload_2");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_lload_3: 
      	    {
      	    	setLiteral("lload_3");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_fload_0: 
      	    {
      	    	setLiteral("fload_0");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_fload_1:
      	    {
      	    	setLiteral("fload_1");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_fload_2: 
      	    {
      	    	setLiteral("fload_2");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_fload_3:
      	    {
      	    	setLiteral("fload_3");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dload_0: 
      	    {
      	    	setLiteral("dload_0");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dload_1:
      	    {
      	    	setLiteral("dload_1");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dload_2:
      	    {
      	    	setLiteral("dload_2");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dload_3:
      	    {
      	    	setLiteral("dload_3");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_aload_0:
      	    {
      	    	setLiteral("aload_0");
      	    	setStackHeadResultType(BasicTypes.T_OBJECT);
      	    	setOperandStackDelta(+1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_aload_1:
      	    {
      	    	setLiteral("aload_1");
      	    	setStackHeadResultType(BasicTypes.T_OBJECT);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_aload_2: 
      	    {
      	    	setLiteral("aload_2");
      	    	setStackHeadResultType(BasicTypes.T_OBJECT);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_aload_3:
      	    {
      	    	setLiteral("aload_3");
      	    	setStackHeadResultType(BasicTypes.T_OBJECT);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_iaload: 
      	    {
      	    	setLiteral("iaload");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_laload:
      	    {
      	    	setLiteral("laload");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_faload: 
      	    {
      	    	setLiteral("faload");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_daload: 
      	    {
      	    	setLiteral("daload");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_aaload: 
      	    {
      	    	setLiteral("aaload");
      	    	setStackHeadResultType(BasicTypes.T_OBJECT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_baload:
      	    {
      	    	setLiteral("baload");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_caload: 
      	    {
      	    	setLiteral("caload");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_saload: 
      	    {
      	    	setLiteral("saload");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_istore_0: 
      	    {
      	    	setLiteral("istore_0");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_istore_1: 
      	    {
      	    	setLiteral("istore_1");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_istore_2:
      	    { 
      	    	setLiteral("istore_2");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_istore_3: 
      	    {
      	    	setLiteral("istore_3");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_lstore_0:
      	    {
      	    	setLiteral("lstore_0");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_lstore_1:
      	    {
      	    	setLiteral("lstore_1");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_lstore_2:
      	    {
      	    	setLiteral("lstore_2");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_lstore_3: 
      	    {
      	    	setLiteral("lstore_3");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_fstore_0:
      	    {
      	    	setLiteral("fstore_0");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_fstore_1: 
      	    {
      	    	setLiteral("");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_fstore_2:
      	    {
      	    	setLiteral("fstore_1");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_fstore_3: 
      	    {
      	    	setLiteral("fstore_3");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dstore_0:
      	    {
      	    	setLiteral("dstore_0");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dstore_1: 
      	    {
      	    	setLiteral("dstore_1");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dstore_2:
      	    {
      	    	setLiteral("dstore_2");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dstore_3: 
      	    {
      	    	setLiteral("dstore_3");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_astore_0:
      	    {
      	    	setLiteral("astore_0");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_astore_1: 
      	    {
      	    	setLiteral("astore_1");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_astore_2: 
      	    {
      	    	setLiteral("astore_2");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_astore_3: 
      	    {
      	    	setLiteral("astore_3");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_iastore:
      	    {
      	    	setLiteral("iastore");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_lastore: 
      	    {
      	    	setLiteral("lastore");
      	    	setOperandStackDelta(-4);
      	    	break;
      	    }
      	    case Mnemonics.BC_fastore: 
      	    {
      	    	setLiteral("fastore");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_dastore: 
      	    {
      	    	setLiteral("dastore");
      	    	setOperandStackDelta(-4);
      	    	break;
      	    }
      	    case Mnemonics.BC_aastore:
      	    {
      	    	setLiteral("aastore");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_bastore:
      	    {
      	    	setLiteral("bastore");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_castore:
      	    {
      	    	setLiteral("castore");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_sastore:
      	    {
      	    	setLiteral("sastore");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_pop: 
      	    {
      	    	setLiteral("pop");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_pop2:
      	    {
      	    	setLiteral("pop2");
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dup: 
      	    {
      	    	setLiteral("dup");
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dup_x1: 
      	    {
      	    	setLiteral("dup_x1");
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dup_x2: 
      	    {
      	    	setLiteral("dup_x2");
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dup2: 
      	    {
      	    	setLiteral("dup2");
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dup2_x1: 
      	    {
      	    	setLiteral("dup2_x1");
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_dup2_x2: 
      	    {
      	    	setLiteral("dup2_x2");
      	    	setOperandStackDelta(+2);
      	    	break;
      	    }
      	    case Mnemonics.BC_swap: 
      	    {
      	    	setLiteral("swap");
      	    	break;
      	    }
      	    case Mnemonics.BC_iadd: 
      	    {
      	    	setLiteral("iadd");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_ladd: 
      	    {
      	    	setLiteral("ladd");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_fadd:
      	    {
      	    	setLiteral("fadd");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dadd:
      	    {
      	    	setLiteral("dadd");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_isub:
      	    {
      	    	setLiteral("isub");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_lsub: 
      	    {
      	    	setLiteral("lsub");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_fsub: 
      	    {
      	    	setLiteral("fsub");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dsub:
      	    {
      	    	setLiteral("dsub");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_imul: 
      	    {
      	    	setLiteral("imul");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_lmul: 
      	    {
      	    	setLiteral("lmul");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_fmul: 
      	    {
      	    	setLiteral("fmul");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dmul: 
      	    {
      	    	setLiteral("dmul");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_idiv: 
      	    {
      	    	setLiteral("idiv");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_ldiv: 
      	    {
      	    	setLiteral("ldiv");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-2);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_fdiv: 
      	    {
      	    	setLiteral("fdiv");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_ddiv: 
      	    {
      	    	setLiteral("ddiv");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_irem: 
      	    {
      	    	setLiteral("irem");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_lrem: 
      	    {
      	    	setLiteral("lrem");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-2);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_frem:
      	    {
      	    	setLiteral("frem");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_drem: 
      	    {
      	    	setLiteral("drem");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_ineg: 
      	    {
      	    	setLiteral("ineg");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_lneg:
      	    {
      	    	setLiteral("lneg");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setCanTrap(true);
      	    	break;
      	    }
      	    case Mnemonics.BC_fneg: 
      	    {
      	    	setLiteral("fneg");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	break;
      	    }
      	    case Mnemonics.BC_dneg: 
      	    {
      	    	setLiteral("dneg");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	break;
      	    }
      	    case Mnemonics.BC_ishl: 
      	    {
      	    	setLiteral("ishl");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_lshl: 
      	    {
      	    	setLiteral("lshl");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_ishr:
      	    {
      	    	setLiteral("ishr");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_lshr: 
      	    {
      	    	setLiteral("lshr");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_iushr:
      	    {
      	    	setLiteral("iushr");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_lushr: 
      	    {
      	    	setLiteral("lushr");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_iand: 
      	    {
      	    	setLiteral("iand");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_land: 
      	    {
      	    	setLiteral("land");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_ior: 
      	    {
      	    	setLiteral("ior");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_lor: 
      	    {
      	    	setLiteral("lor");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_ixor:
      	    {
      	    	setLiteral("ixor");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_lxor: 
      	    {
      	    	setLiteral("lxor");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(-2);
      	    	break;
      	    }
      	    case Mnemonics.BC_i2l:
      	    {
      	    	setLiteral("i2l");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_i2f:
      	    {
      	    	setLiteral("i2f");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	break;
      	    }
      	    case Mnemonics.BC_i2d: 
      	    {
      	    	setLiteral("i2d");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_l2i: 
      	    {
      	    	setLiteral("l2i");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_l2f: 
      	    {
      	    	setLiteral("l2f");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_l2d: 
      	    {
      	    	setLiteral("l2d");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	break;
      	    }
      	    case Mnemonics.BC_f2i:
      	    {
      	    	setLiteral("f2i");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	break;
      	    }
      	    case Mnemonics.BC_f2l: 
      	    {
      	    	setLiteral("f2l");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_f2d: 
      	    {
      	    	setLiteral("f2d");
      	    	setStackHeadResultType(BasicTypes.T_DOUBLE);
      	    	setOperandStackDelta(+1);
      	    	break;
      	    }
      	    case Mnemonics.BC_d2i:
      	    {
      	    	setLiteral("d2i");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_d2l: 
      	    {
      	    	setLiteral("d2l");
      	    	setStackHeadResultType(BasicTypes.T_LONG);
      	    	break;
      	    }
      	    case Mnemonics.BC_d2f:
      	    {
      	    	setLiteral("d2f");
      	    	setStackHeadResultType(BasicTypes.T_FLOAT);
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_i2b: 
      	    {
      	    	setLiteral("i2b");
      	    	setStackHeadResultType(BasicTypes.T_BYTE);
      	    	break;
      	    }
      	    case Mnemonics.BC_i2c: 
      	    {
      	    	setLiteral("i2c");
      	    	setStackHeadResultType(BasicTypes.T_CHAR);
      	    	break;
      	    }
      	    case Mnemonics.BC_i2s: 
      	    {
      	    	setLiteral("i2s");
      	    	setStackHeadResultType(BasicTypes.T_SHORT);
      	    	break;
      	    }
      	    case Mnemonics.BC_lcmp:
      	    {
      	    	setLiteral("lcmp");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_fcmpl: 
      	    {
      	    	setLiteral("fcmpl");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_fcmpg:
      	    {
      	    	setLiteral("fcmpg");
      	    	setOperandStackDelta(-1);
      	    	break;
      	    }
      	    case Mnemonics.BC_dcmpl:
      	    {
      	    	setLiteral("dcmpl");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_dcmpg:
      	    {
      	    	setLiteral("dcmpg");
      	    	setOperandStackDelta(-3);
      	    	break;
      	    }
      	    case Mnemonics.BC_ireturn:
      	    {
      	    	setLiteral("ireturn");
      	    	setStackHeadResultType(BasicTypes.T_INT);
      	    	setOperandStackDelta(-1);
      	    	setCanTrap(true);
      	    	break;
      	    }
        	case Mnemonics.BC_lreturn: 
        	{
        		setLiteral("lreturn");
        		setStackHeadResultType(BasicTypes.T_LONG);
        		setOperandStackDelta(-2);
        		setCanTrap(true);
        		break;
        	}
        	case Mnemonics.BC_freturn: 
        	{
        		setLiteral("freturn");
        		setStackHeadResultType(BasicTypes.T_FLOAT);
        		setOperandStackDelta(-1);
        		setCanTrap(true);
        		break;
        	}
        	case Mnemonics.BC_dreturn:
        	{
        		setLiteral("dreturn");
        		setStackHeadResultType(BasicTypes.T_DOUBLE);
        		setOperandStackDelta(-2);
        		setCanTrap(true);
        		break;
        	}
        	case Mnemonics.BC_areturn:
        	{
        		setLiteral("areturn");
        		setStackHeadResultType(BasicTypes.T_OBJECT);
        		setOperandStackDelta(-1);
        		setCanTrap(true);
        		break;
        	}
        	case Mnemonics.BC_return: 
        	{
        		setLiteral("return");
        		setCanTrap(true);
        		break;
        	}
        	case Mnemonics.BC_arraylength: 
        	{
        		setLiteral("arraylength");
        		setCanTrap(true);
        		break;
        	}
        	case Mnemonics.BC_athrow:
        	{
        		setLiteral("athrow");
        		setOperandStackDelta(-1);
        		setCanTrap(true);
        		break;
        	}
        	case Mnemonics.BC_monitorenter: 
        	{
        		setLiteral("monitorenter");
        		setOperandStackDelta(-1);
        		setCanTrap(true);
        		break;
        	}
        	case Mnemonics.BC_monitorexit: 
        	{
        		setLiteral("monitorexit");
        		setOperandStackDelta(-1);
        		setCanTrap(true);
        		break;
        	}
		}
	}
	
	public static boolean isOfType(int byteCodeValue)
	{
		switch(byteCodeValue)
		{
    	    case Mnemonics.BC_nop:
    	    case Mnemonics.BC_aconst_null:
    	    case Mnemonics.BC_iconst_m1:
    	    case Mnemonics.BC_iconst_0:
    	    case Mnemonics.BC_iconst_1:
    	    case Mnemonics.BC_iconst_2:
    	    case Mnemonics.BC_iconst_3:
    	    case Mnemonics.BC_iconst_4:
    	    case Mnemonics.BC_iconst_5:
    	    case Mnemonics.BC_lconst_0:
    	    case Mnemonics.BC_lconst_1:
    	    case Mnemonics.BC_fconst_0:
    	    case Mnemonics.BC_fconst_1:
    	    case Mnemonics.BC_fconst_2:
    	    case Mnemonics.BC_dconst_0:
    	    case Mnemonics.BC_dconst_1:
    	    case Mnemonics.BC_iload_0:
    	    case Mnemonics.BC_iload_1:
    	    case Mnemonics.BC_iload_2:
    	    case Mnemonics.BC_iload_3:
    	    case Mnemonics.BC_lload_0: 
    	    case Mnemonics.BC_lload_1:
      	    case Mnemonics.BC_lload_2: 
      	    case Mnemonics.BC_lload_3: 
      	    case Mnemonics.BC_fload_0: 
      	    case Mnemonics.BC_fload_1:
      	    case Mnemonics.BC_fload_2: 
      	    case Mnemonics.BC_fload_3:
      	    case Mnemonics.BC_dload_0: 
      	    case Mnemonics.BC_dload_1:
      	    case Mnemonics.BC_dload_2:
      	    case Mnemonics.BC_dload_3:
      	    case Mnemonics.BC_aload_0:
      	    case Mnemonics.BC_aload_1:
      	    case Mnemonics.BC_aload_2: 
      	    case Mnemonics.BC_aload_3:
      	    case Mnemonics.BC_iaload: 
      	    case Mnemonics.BC_laload:
      	    case Mnemonics.BC_faload: 
      	    case Mnemonics.BC_daload: 
      	    case Mnemonics.BC_aaload: 
      	    case Mnemonics.BC_baload:
      	    case Mnemonics.BC_caload: 
      	    case Mnemonics.BC_saload: 
      	    case Mnemonics.BC_istore_0: 
      	    case Mnemonics.BC_istore_1: 
      	    case Mnemonics.BC_istore_2:
      	    case Mnemonics.BC_istore_3: 
      	    case Mnemonics.BC_lstore_0:
      	    case Mnemonics.BC_lstore_1:
      	    case Mnemonics.BC_lstore_2:
      	    case Mnemonics.BC_lstore_3: 
      	    case Mnemonics.BC_fstore_0:
      	    case Mnemonics.BC_fstore_1: 
      	    case Mnemonics.BC_fstore_2:
      	    case Mnemonics.BC_fstore_3: 
      	    case Mnemonics.BC_dstore_0:
      	    case Mnemonics.BC_dstore_1: 
      	    case Mnemonics.BC_dstore_2:
      	    case Mnemonics.BC_dstore_3: 
      	    case Mnemonics.BC_astore_0:
      	    case Mnemonics.BC_astore_1: 
      	    case Mnemonics.BC_astore_2: 
      	    case Mnemonics.BC_astore_3: 
      	    case Mnemonics.BC_iastore:
      	    case Mnemonics.BC_lastore: 
      	    case Mnemonics.BC_fastore: 
      	    case Mnemonics.BC_dastore: 
      	    case Mnemonics.BC_aastore:
      	    case Mnemonics.BC_bastore:
      	    case Mnemonics.BC_castore:
      	    case Mnemonics.BC_sastore:
      	    case Mnemonics.BC_pop: 
      	    case Mnemonics.BC_pop2:
      	    case Mnemonics.BC_dup: 
      	    case Mnemonics.BC_dup_x1: 
      	    case Mnemonics.BC_dup_x2: 
      	    case Mnemonics.BC_dup2: 
      	    case Mnemonics.BC_dup2_x1: 
      	    case Mnemonics.BC_dup2_x2: 
      	    case Mnemonics.BC_swap: 
      	    case Mnemonics.BC_iadd: 
      	    case Mnemonics.BC_ladd: 
      	    case Mnemonics.BC_fadd:
      	    case Mnemonics.BC_dadd:
      	    case Mnemonics.BC_isub:
      	    case Mnemonics.BC_lsub: 
      	    case Mnemonics.BC_fsub: 
      	    case Mnemonics.BC_dsub:
      	    case Mnemonics.BC_imul: 
      	    case Mnemonics.BC_lmul: 
      	    case Mnemonics.BC_fmul: 
      	    case Mnemonics.BC_dmul: 
      	    case Mnemonics.BC_idiv: 
      	    case Mnemonics.BC_ldiv: 
      	    case Mnemonics.BC_fdiv: 
      	    case Mnemonics.BC_ddiv: 
      	    case Mnemonics.BC_irem: 
      	    case Mnemonics.BC_lrem: 
      	    case Mnemonics.BC_frem:
      	    case Mnemonics.BC_drem: 
      	    case Mnemonics.BC_ineg: 
      	    case Mnemonics.BC_lneg:
      	    case Mnemonics.BC_fneg: 
      	    case Mnemonics.BC_dneg: 
      	    case Mnemonics.BC_ishl: 
      	    case Mnemonics.BC_lshl: 
      	    case Mnemonics.BC_ishr:
      	    case Mnemonics.BC_lshr: 
      	    case Mnemonics.BC_iushr:
      	    case Mnemonics.BC_lushr: 
      	    case Mnemonics.BC_iand: 
      	    case Mnemonics.BC_land: 
      	    case Mnemonics.BC_ior: 
      	    case Mnemonics.BC_lor: 
      	    case Mnemonics.BC_ixor:
      	    case Mnemonics.BC_lxor: 
      	    case Mnemonics.BC_i2l:
      	    case Mnemonics.BC_i2f:
      	    case Mnemonics.BC_i2d: 
      	    case Mnemonics.BC_l2i: 
      	    case Mnemonics.BC_l2f: 
      	    case Mnemonics.BC_l2d: 
      	    case Mnemonics.BC_f2i:
      	    case Mnemonics.BC_f2l: 
      	    case Mnemonics.BC_f2d: 
      	    case Mnemonics.BC_d2i:
      	    case Mnemonics.BC_d2l: 
      	    case Mnemonics.BC_d2f:
      	    case Mnemonics.BC_i2b: 
      	    case Mnemonics.BC_i2c: 
      	    case Mnemonics.BC_i2s: 
      	    case Mnemonics.BC_lcmp:
      	    case Mnemonics.BC_fcmpl: 
      	    case Mnemonics.BC_fcmpg:
      	    case Mnemonics.BC_dcmpl:
      	    case Mnemonics.BC_dcmpg:
      	    case Mnemonics.BC_ireturn:
      	    case Mnemonics.BC_lreturn: 
        	case Mnemonics.BC_freturn: 
        	case Mnemonics.BC_dreturn:
        	case Mnemonics.BC_areturn:
        	case Mnemonics.BC_return: 
        	case Mnemonics.BC_arraylength: 
        	case Mnemonics.BC_athrow:
        	case Mnemonics.BC_monitorenter: 
        	case Mnemonics.BC_monitorexit: 
        	{
        		return true;
        	}
		}
		
		return false;
	}
	
	public static InstructionTypeB deserialize(DesCtx ctx, int byteCodeValue, AbstractInstruction previousInstruction, Disassembly disassembly)
	{
		return new InstructionTypeB(byteCodeValue, previousInstruction, disassembly);
	}
	
	public static byte[] serialize(SerCtx ctx, InstructionTypeB instruction) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{(byte)instruction.getByteCodeValue()});
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		return super.toString();
	}

	@Override
	public int getPhysicalSize()
	{
		return 1;
	}
}