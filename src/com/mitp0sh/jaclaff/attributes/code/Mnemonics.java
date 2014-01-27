package com.mitp0sh.jaclaff.attributes.code;

import com.mitp0sh.jaclaff.attributes.code.deprecated.ByteCode;


public class Mnemonics
{
	public static final int BC_nop                  =   0; // 0x00
    public static final int BC_aconst_null          =   1; // 0x01
    public static final int BC_iconst_m1            =   2; // 0x02
    public static final int BC_iconst_0             =   3; // 0x03
    public static final int BC_iconst_1             =   4; // 0x04
    public static final int BC_iconst_2             =   5; // 0x05
    public static final int BC_iconst_3             =   6; // 0x06
    public static final int BC_iconst_4             =   7; // 0x07
    public static final int BC_iconst_5             =   8; // 0x08
    public static final int BC_lconst_0             =   9; // 0x09
    public static final int BC_lconst_1             =  10; // 0x0a
    public static final int BC_fconst_0             =  11; // 0x0b
    public static final int BC_fconst_1             =  12; // 0x0c
    public static final int BC_fconst_2             =  13; // 0x0d
    public static final int BC_dconst_0             =  14; // 0x0e
    public static final int BC_dconst_1             =  15; // 0x0f
    public static final int BC_bipush               =  16; // 0x10
    public static final int BC_sipush               =  17; // 0x11
    public static final int BC_ldc                  =  18; // 0x12
    public static final int BC_ldc_w                =  19; // 0x13
    public static final int BC_ldc2_w               =  20; // 0x14
    public static final int BC_iload                =  21; // 0x15
    public static final int BC_lload                =  22; // 0x16
    public static final int BC_fload                =  23; // 0x17
    public static final int BC_dload                =  24; // 0x18
    public static final int BC_aload                =  25; // 0x19
    public static final int BC_iload_0              =  26; // 0x1a
    public static final int BC_iload_1              =  27; // 0x1b
    public static final int BC_iload_2              =  28; // 0x1c
    public static final int BC_iload_3              =  29; // 0x1d
    public static final int BC_lload_0              =  30; // 0x1e
    public static final int BC_lload_1              =  31; // 0x1f
    public static final int BC_lload_2              =  32; // 0x20
    public static final int BC_lload_3              =  33; // 0x21
    public static final int BC_fload_0              =  34; // 0x22
    public static final int BC_fload_1              =  35; // 0x23
    public static final int BC_fload_2              =  36; // 0x24
    public static final int BC_fload_3              =  37; // 0x25
    public static final int BC_dload_0              =  38; // 0x26
    public static final int BC_dload_1              =  39; // 0x27
    public static final int BC_dload_2              =  40; // 0x28
    public static final int BC_dload_3              =  41; // 0x29
    public static final int BC_aload_0              =  42; // 0x2a
    public static final int BC_aload_1              =  43; // 0x2b
    public static final int BC_aload_2              =  44; // 0x2c
    public static final int BC_aload_3              =  45; // 0x2d
    public static final int BC_iaload               =  46; // 0x2e
    public static final int BC_laload               =  47; // 0x2f
    public static final int BC_faload               =  48; // 0x30
    public static final int BC_daload               =  49; // 0x31
    public static final int BC_aaload               =  50; // 0x32
    public static final int BC_baload               =  51; // 0x33
    public static final int BC_caload               =  52; // 0x34
    public static final int BC_saload               =  53; // 0x35
    public static final int BC_istore               =  54; // 0x36
    public static final int BC_lstore               =  55; // 0x37
    public static final int BC_fstore               =  56; // 0x38
    public static final int BC_dstore               =  57; // 0x39
    public static final int BC_astore               =  58; // 0x3a
    public static final int BC_istore_0             =  59; // 0x3b
    public static final int BC_istore_1             =  60; // 0x3c
    public static final int BC_istore_2             =  61; // 0x3d
    public static final int BC_istore_3             =  62; // 0x3e
    public static final int BC_lstore_0             =  63; // 0x3f
    public static final int BC_lstore_1             =  64; // 0x40
    public static final int BC_lstore_2             =  65; // 0x41
    public static final int BC_lstore_3             =  66; // 0x42
    public static final int BC_fstore_0             =  67; // 0x43
    public static final int BC_fstore_1             =  68; // 0x44
    public static final int BC_fstore_2             =  69; // 0x45
    public static final int BC_fstore_3             =  70; // 0x46
    public static final int BC_dstore_0             =  71; // 0x47
    public static final int BC_dstore_1             =  72; // 0x48
    public static final int BC_dstore_2             =  73; // 0x49
    public static final int BC_dstore_3             =  74; // 0x4a
    public static final int BC_astore_0             =  75; // 0x4b
    public static final int BC_astore_1             =  76; // 0x4c
    public static final int BC_astore_2             =  77; // 0x4d
    public static final int BC_astore_3             =  78; // 0x4e
    public static final int BC_iastore              =  79; // 0x4f
    public static final int BC_lastore              =  80; // 0x50
    public static final int BC_fastore              =  81; // 0x51
    public static final int BC_dastore              =  82; // 0x52
    public static final int BC_aastore              =  83; // 0x53
    public static final int BC_bastore              =  84; // 0x54
    public static final int BC_castore              =  85; // 0x55
    public static final int BC_sastore              =  86; // 0x56
    public static final int BC_pop                  =  87; // 0x57
    public static final int BC_pop2                 =  88; // 0x58
    public static final int BC_dup                  =  89; // 0x59
    public static final int BC_dup_x1               =  90; // 0x5a
    public static final int BC_dup_x2               =  91; // 0x5b
    public static final int BC_dup2                 =  92; // 0x5c
    public static final int BC_dup2_x1              =  93; // 0x5d
    public static final int BC_dup2_x2              =  94; // 0x5e
    public static final int BC_swap                 =  95; // 0x5f
    public static final int BC_iadd                 =  96; // 0x60
    public static final int BC_ladd                 =  97; // 0x61
    public static final int BC_fadd                 =  98; // 0x62
    public static final int BC_dadd                 =  99; // 0x63
    public static final int BC_isub                 = 100; // 0x64
    public static final int BC_lsub                 = 101; // 0x65
    public static final int BC_fsub                 = 102; // 0x66
    public static final int BC_dsub                 = 103; // 0x67
    public static final int BC_imul                 = 104; // 0x68
    public static final int BC_lmul                 = 105; // 0x69
    public static final int BC_fmul                 = 106; // 0x6a
    public static final int BC_dmul                 = 107; // 0x6b
    public static final int BC_idiv                 = 108; // 0x6c
    public static final int BC_ldiv                 = 109; // 0x6d
    public static final int BC_fdiv                 = 110; // 0x6e
    public static final int BC_ddiv                 = 111; // 0x6f
    public static final int BC_irem                 = 112; // 0x70
    public static final int BC_lrem                 = 113; // 0x71
    public static final int BC_frem                 = 114; // 0x72
    public static final int BC_drem                 = 115; // 0x73
    public static final int BC_ineg                 = 116; // 0x74
    public static final int BC_lneg                 = 117; // 0x75
    public static final int BC_fneg                 = 118; // 0x76
    public static final int BC_dneg                 = 119; // 0x77
    public static final int BC_ishl                 = 120; // 0x78
    public static final int BC_lshl                 = 121; // 0x79
    public static final int BC_ishr                 = 122; // 0x7a
    public static final int BC_lshr                 = 123; // 0x7b
    public static final int BC_iushr                = 124; // 0x7c
    public static final int BC_lushr                = 125; // 0x7d
    public static final int BC_iand                 = 126; // 0x7e
    public static final int BC_land                 = 127; // 0x7f
    public static final int BC_ior                  = 128; // 0x80
    public static final int BC_lor                  = 129; // 0x81
    public static final int BC_ixor                 = 130; // 0x82
    public static final int BC_lxor                 = 131; // 0x83
    public static final int BC_iinc                 = 132; // 0x84
    public static final int BC_i2l                  = 133; // 0x85
    public static final int BC_i2f                  = 134; // 0x86
    public static final int BC_i2d                  = 135; // 0x87
    public static final int BC_l2i                  = 136; // 0x88
    public static final int BC_l2f                  = 137; // 0x89
    public static final int BC_l2d                  = 138; // 0x8a
    public static final int BC_f2i                  = 139; // 0x8b
    public static final int BC_f2l                  = 140; // 0x8c
    public static final int BC_f2d                  = 141; // 0x8d
    public static final int BC_d2i                  = 142; // 0x8e
    public static final int BC_d2l                  = 143; // 0x8f
    public static final int BC_d2f                  = 144; // 0x90
    public static final int BC_i2b                  = 145; // 0x91
    public static final int BC_i2c                  = 146; // 0x92
    public static final int BC_i2s                  = 147; // 0x93
    public static final int BC_lcmp                 = 148; // 0x94
    public static final int BC_fcmpl                = 149; // 0x95
    public static final int BC_fcmpg                = 150; // 0x96
    public static final int BC_dcmpl                = 151; // 0x97
    public static final int BC_dcmpg                = 152; // 0x98
    public static final int BC_ifeq                 = 153; // 0x99
    public static final int BC_ifne                 = 154; // 0x9a
    public static final int BC_iflt                 = 155; // 0x9b
    public static final int BC_ifge                 = 156; // 0x9c
    public static final int BC_ifgt                 = 157; // 0x9d
    public static final int BC_ifle                 = 158; // 0x9e
    public static final int BC_if_icmpeq            = 159; // 0x9f
    public static final int BC_if_icmpne            = 160; // 0xa0
    public static final int BC_if_icmplt            = 161; // 0xa1
    public static final int BC_if_icmpge            = 162; // 0xa2
    public static final int BC_if_icmpgt            = 163; // 0xa3
    public static final int BC_if_icmple            = 164; // 0xa4
    public static final int BC_if_acmpeq            = 165; // 0xa5
    public static final int BC_if_acmpne            = 166; // 0xa6
    public static final int BC_goto                 = 167; // 0xa7
    public static final int BC_jsr                  = 168; // 0xa8
    public static final int BC_ret                  = 169; // 0xa9
    public static final int BC_tableswitch          = 170; // 0xaa
    public static final int BC_lookupswitch         = 171; // 0xab
    public static final int BC_ireturn              = 172; // 0xac
    public static final int BC_lreturn              = 173; // 0xad
    public static final int BC_freturn              = 174; // 0xae
    public static final int BC_dreturn              = 175; // 0xaf
    public static final int BC_areturn              = 176; // 0xb0
    public static final int BC_return               = 177; // 0xb1
    public static final int BC_getstatic            = 178; // 0xb2
    public static final int BC_putstatic            = 179; // 0xb3
    public static final int BC_getfield             = 180; // 0xb4
    public static final int BC_putfield             = 181; // 0xb5
    public static final int BC_invokevirtual        = 182; // 0xb6
    public static final int BC_invokespecial        = 183; // 0xb7
    public static final int BC_invokestatic         = 184; // 0xb8
    public static final int BC_invokeinterface      = 185; // 0xb9
    public static final int BC_invokedynamic        = 186; // 0xba
    public static final int BC_new                  = 187; // 0xbb
    public static final int BC_newarray             = 188; // 0xbc
    public static final int BC_anewarray            = 189; // 0xbd
    public static final int BC_arraylength          = 190; // 0xbe
    public static final int BC_athrow               = 191; // 0xbf
    public static final int BC_checkcast            = 192; // 0xc0
    public static final int BC_instanceof           = 193; // 0xc1
    public static final int BC_monitorenter         = 194; // 0xc2
    public static final int BC_monitorexit          = 195; // 0xc3
    public static final int BC_wide                 = 196; // 0xc4
    public static final int BC_multianewarray       = 197; // 0xc5
    public static final int BC_ifnull               = 198; // 0xc6
    public static final int BC_ifnonnull            = 199; // 0xc7
    public static final int BC_goto_w               = 200; // 0xc8
    public static final int BC_jsr_w                = 201; // 0xc9
    public static final int BC_breakpoint           = 202; // 0xca
    
    public static final int NUM_OF_REGULAR_BYTECODES = BC_breakpoint + 1;
    
    public static final int FBC_agetfield                 = NUM_OF_REGULAR_BYTECODES;
    public static final int FBC_bgetfield                 = NUM_OF_REGULAR_BYTECODES + 1;
    public static final int FBC_cgetfield                 = NUM_OF_REGULAR_BYTECODES + 2;
    public static final int FBC_dgetfield                 = NUM_OF_REGULAR_BYTECODES + 3;
    public static final int FBC_fgetfield                 = NUM_OF_REGULAR_BYTECODES + 4;
    public static final int FBC_igetfield                 = NUM_OF_REGULAR_BYTECODES + 5;
    public static final int FBC_lgetfield                 = NUM_OF_REGULAR_BYTECODES + 6;
    public static final int FBC_sgetfield                 = NUM_OF_REGULAR_BYTECODES + 7;

    public static final int FBC_aputfield                 = NUM_OF_REGULAR_BYTECODES + 8;
    public static final int FBC_bputfield                 = NUM_OF_REGULAR_BYTECODES + 9;
    public static final int FBC_cputfield                 = NUM_OF_REGULAR_BYTECODES + 10;
    public static final int FBC_dputfield                 = NUM_OF_REGULAR_BYTECODES + 11;
    public static final int FBC_fputfield                 = NUM_OF_REGULAR_BYTECODES + 12;
    public static final int FBC_iputfield                 = NUM_OF_REGULAR_BYTECODES + 13;
    public static final int FBC_lputfield                 = NUM_OF_REGULAR_BYTECODES + 14;
    public static final int FBC_sputfield                 = NUM_OF_REGULAR_BYTECODES + 15;

    public static final int FBC_aload_0                   = NUM_OF_REGULAR_BYTECODES + 16;
    public static final int FBC_iaccess_0                 = NUM_OF_REGULAR_BYTECODES + 17;
    public static final int FBC_aaccess_0                 = NUM_OF_REGULAR_BYTECODES + 18;
    public static final int FBC_faccess_0                 = NUM_OF_REGULAR_BYTECODES + 19;

    public static final int FBC_iload                     = NUM_OF_REGULAR_BYTECODES + 20;
    public static final int FBC_iload2                    = NUM_OF_REGULAR_BYTECODES + 21;
    public static final int FBC_icaload                   = NUM_OF_REGULAR_BYTECODES + 22;

    public static final int FBC_invokefinal               = NUM_OF_REGULAR_BYTECODES + 23;
    public static final int FBC_linearswitch              = NUM_OF_REGULAR_BYTECODES + 24;
    public static final int FBC_binaryswitch              = NUM_OF_REGULAR_BYTECODES + 25;

    public static final int FBC_return_register_finalizer = NUM_OF_REGULAR_BYTECODES + 26;
    
    public static ByteCode[] JBC = null;
    
    public static ByteCode[] getJBC()
    {
		if(JBC == null)
		{
			initialize();
		}
    	
    	return JBC;
	}

	public static void initialize()
    {
//    	JBC = new ByteCode[NUM_OF_REGULAR_BYTECODES & 0xFF];
//    	
//    	JBC[BC_nop] 
//    	    = new ByteCode(BC_nop, "nop", "b", null, BasicTypes.T_VOID, 0, false, null);
//    	JBC[BC_aconst_null]
//    	    = new ByteCode(BC_aconst_null, "aconst_null", "b", null, BasicTypes.T_OBJECT, 1, false, null);
//    	JBC[BC_iconst_m1]
//    	    = new ByteCode(BC_iconst_m1, "iconst_m1", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iconst_0]
//    	    = new ByteCode(BC_iconst_0, "iconst_0", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iconst_1]
//    	    = new ByteCode(BC_iconst_1, "iconst_1", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iconst_2]
//    	    = new ByteCode(BC_iconst_2, "iconst_2", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iconst_3]
//    	    = new ByteCode(BC_iconst_3, "iconst_3", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iconst_4]
//    	    = new ByteCode(BC_iconst_4, "iconst_4", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iconst_5]
//    	    = new ByteCode(BC_iconst_5, "iconst_5", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_lconst_0]
//    	    = new ByteCode(BC_lconst_0, "lconst_0", "b", null, BasicTypes.T_LONG, 2, false, null);
//    	JBC[BC_lconst_1]
//    	    = new ByteCode(BC_lconst_1, "lconst_1", "b", null, BasicTypes.T_LONG, 2, false, null);
//    	JBC[BC_fconst_0]
//    	    = new ByteCode(BC_fconst_0, "fconst_0", "b", null, BasicTypes.T_FLOAT, 1, false, null);
//    	JBC[BC_fconst_1]
//    	    = new ByteCode(BC_fconst_1, "fconst_1", "b", null, BasicTypes.T_FLOAT, 1, false, null);
//    	JBC[BC_fconst_2]
//    	    = new ByteCode(BC_fconst_2, "fconst_2", "b", null, BasicTypes.T_FLOAT, 1, false, null);
//    	JBC[BC_dconst_0]
//    	    = new ByteCode(BC_dconst_0, "dconst_0", "b", null, BasicTypes.T_DOUBLE, 2, false, null);
//    	JBC[BC_dconst_1]
//    	    = new ByteCode(BC_dconst_1, "dconst_1", "b", null, BasicTypes.T_DOUBLE, 2, false, null);
//    	JBC[BC_bipush]
//    	    = new ByteCode(BC_bipush, "bipush", "bc", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_sipush]
//    	    = new ByteCode(BC_sipush, "sipush", "bcc", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_ldc]
//    	    = new ByteCode(BC_ldc, "ldc", "bi", null, BasicTypes.T_ILLEGAL, 1, true, null);
//    	JBC[BC_ldc_w]
//    	    = new ByteCode(BC_ldc_w, "ldc_w", "bii", null, BasicTypes.T_ILLEGAL, 1, true, null);
//    	JBC[BC_ldc2_w]
//    	    = new ByteCode(BC_ldc2_w, "ldc2_w", "bii", null, BasicTypes.T_ILLEGAL, 2, true, null);
//    	JBC[BC_iload]
//    	    = new ByteCode(BC_iload, "iload", "bi", "wbii", BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_lload]
//    	    = new ByteCode(BC_lload, "lload", "bi", "wbii", BasicTypes.T_LONG, 2, false, null);
//    	JBC[BC_fload]
//    	    = new ByteCode(BC_fload, "fload", "bi", "wbii", BasicTypes.T_FLOAT, 1, false, null);
//    	JBC[BC_dload]
//    	    = new ByteCode(BC_dload, "dload", "bi", "wbii", BasicTypes.T_DOUBLE, 2, false, null);
//    	JBC[BC_aload]
//    	    = new ByteCode(BC_aload, "aload", "bi", "wbii", BasicTypes.T_OBJECT, 1, false, null);
//    	JBC[BC_iload_0]
//    	    = new ByteCode(BC_iload_0, "iload_0", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iload_1]
//    	    = new ByteCode(BC_iload_1, "iload_1", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iload_2]
//    	    = new ByteCode(BC_iload_2, "iload_2", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_iload_3]
//    	    = new ByteCode(BC_iload_3, "iload_3", "b", null, BasicTypes.T_INT, 1, false, null);
//    	JBC[BC_lload_0]
//    	    = new ByteCode(BC_lload_0, "lload_0", "b", null, BasicTypes.T_LONG, 2, false, null);
//    	JBC[BC_lload_1]
//    	    = new ByteCode(BC_lload_1, "lload_1", "b", null, BasicTypes.T_LONG, 2, false, null);
//    	JBC[BC_lload_2]
//    	    = new ByteCode(BC_lload_2, "lload_2", "b", null, BasicTypes.T_LONG, 2, false, null);
//    	JBC[BC_lload_3]
//    	    = new ByteCode(BC_lload_3, "lload_3", "b", null, BasicTypes.T_LONG, 2, false, null);
//    	JBC[BC_fload_0]
//    	    = new ByteCode(BC_fload_0, "fload_0", "b", null, BasicTypes.T_FLOAT, 1, false, null);
//    	JBC[BC_fload_1]
//    	    = new ByteCode(BC_fload_1, "fload_1", "b", null, BasicTypes.T_FLOAT, 1, false, null);
//    	JBC[BC_fload_2]
//    	    = new ByteCode(BC_fload_2, "fload_2", "b", null, BasicTypes.T_FLOAT, 1, false, null);
//    	JBC[BC_fload_3]
//    	    = new ByteCode(BC_fload_3, "fload_3", "b", null, BasicTypes.T_FLOAT, 1, false, null);
//    	JBC[BC_dload_0]
//    	    = new ByteCode(BC_dload_0, "dload_0", "b", null, BasicTypes.T_DOUBLE, 2, false, null);
//    	JBC[BC_dload_1]
//    	    = new ByteCode(BC_dload_1, "dload_1", "b", null, BasicTypes.T_DOUBLE, 2, false, null);
//    	JBC[BC_dload_2]
//    	    = new ByteCode(BC_dload_2, "dload_2", "b", null, BasicTypes.T_DOUBLE, 2, false, null);
//    	JBC[BC_dload_3]
//    	    = new ByteCode(BC_dload_3, "dload_3", "b", null, BasicTypes.T_DOUBLE, 2, false, null);
//    	JBC[BC_aload_0]
//    	    = new ByteCode(BC_aload_0, "aload_0", "b", null, BasicTypes.T_OBJECT, 1, true, null);
//    	JBC[BC_aload_1]
//    	    = new ByteCode(BC_aload_1, "aload_1", "b", null, BasicTypes.T_OBJECT, 1, false, null);
//    	JBC[BC_aload_2]
//    	    = new ByteCode(BC_aload_2, "aload_2", "b", null, BasicTypes.T_OBJECT, 1, false, null);
//    	JBC[BC_aload_3]
//    	    = new ByteCode(BC_aload_3, "aload_3", "b", null, BasicTypes.T_OBJECT, 1, false, null);
//    	JBC[BC_iaload]
//    	    = new ByteCode(BC_iaload, "iaload", "b", null, BasicTypes.T_INT, -1, true, null);
//    	JBC[BC_laload]
//    	    = new ByteCode(BC_laload, "laload", "b", null, BasicTypes.T_LONG, 0, true, null);
//    	JBC[BC_faload]
//    	    = new ByteCode(BC_faload, "faload", "b", null, BasicTypes.T_FLOAT, -1, true, null);
//    	JBC[BC_daload]
//    	    = new ByteCode(BC_daload, "daload", "b", null, BasicTypes.T_DOUBLE, 0, true, null);
//    	JBC[BC_aaload]
//    	    = new ByteCode(BC_aaload, "aaload", "b", null, BasicTypes.T_OBJECT, -1, true, null);
//    	JBC[BC_baload]
//    	    = new ByteCode(BC_baload, "baload", "b", null, BasicTypes.T_INT, -1, true, null);
//    	JBC[BC_caload]
//    	    = new ByteCode(BC_caload, "caload", "b", null, BasicTypes.T_INT, -1, true, null);
//    	JBC[BC_saload]
//    	    = new ByteCode(BC_saload, "saload", "b", null, BasicTypes.T_INT, -1, true, null);
//    	JBC[BC_istore]
//    	    = new ByteCode(BC_istore, "istore", "bi", "wbii", BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_lstore]
//    	    = new ByteCode(BC_lstore, "lstore", "bi", "wbii", BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_fstore]
//    	    = new ByteCode(BC_fstore, "fstore", "bi", "wbii", BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_dstore]
//    	    = new ByteCode(BC_dstore, "dstore", "bi", "wbii", BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_astore]
//    	    = new ByteCode(BC_astore, "astore", "bi", "wbii", BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_istore_0]
//    	    = new ByteCode(BC_istore_0, "istore_0", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_istore_1]
//    	    = new ByteCode(BC_istore_1, "istore_1", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_istore_2]
//    	    = new ByteCode(BC_istore_2, "istore_2", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_istore_3]
//    	    = new ByteCode(BC_istore_3, "istore_3", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_lstore_0]
//    	    = new ByteCode(BC_lstore_0, "lstore_0", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_lstore_1]
//    	    = new ByteCode(BC_lstore_1, "lstore_1", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_lstore_2]
//    	    = new ByteCode(BC_lstore_2, "lstore_2", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_lstore_3]
//    	    = new ByteCode(BC_lstore_3, "lstore_3", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_fstore_0]
//    	    = new ByteCode(BC_fstore_0, "fstore_0", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_fstore_1]
//    	    = new ByteCode(BC_fstore_1, "fstore_1", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_fstore_2]
//    	    = new ByteCode(BC_fstore_2, "fstore_2", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_fstore_3]
//    	    = new ByteCode(BC_fstore_3, "fstore_3", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_dstore_0]
//    	    = new ByteCode(BC_dstore_0, "dstore_0", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_dstore_1]
//    	    = new ByteCode(BC_dstore_1, "dstore_1", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_dstore_2]
//    	    = new ByteCode(BC_dstore_2, "dstore_2", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_dstore_3]
//    	    = new ByteCode(BC_dstore_3, "dstore_3", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_astore_0]
//    	    = new ByteCode(BC_astore_0, "astore_0", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_astore_1]
//    	    = new ByteCode(BC_astore_1, "astore_1", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_astore_2]
//    	    = new ByteCode(BC_astore_2, "astore_2", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_astore_3]
//    	    = new ByteCode(BC_astore_3, "astore_3", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_iastore]
//    	    = new ByteCode(BC_iastore, "iastore", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_lastore]
//    	    = new ByteCode(BC_lastore, "lastore", "b", null, BasicTypes.T_VOID, -4, false, null);
//    	JBC[BC_fastore]
//    	    = new ByteCode(BC_fastore, "fastore", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_dastore]
//    	    = new ByteCode(BC_dastore, "dastore", "b", null, BasicTypes.T_VOID, -4, false, null);
//    	JBC[BC_aastore]
//    	    = new ByteCode(BC_aastore, "aastore", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_bastore]
//    	    = new ByteCode(BC_bastore, "bastore", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_castore]
//    	    = new ByteCode(BC_castore, "castore", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_sastore]
//    	    = new ByteCode(BC_sastore, "sastore", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_pop]
//    	    = new ByteCode(BC_pop, "pop", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_pop2]
//    	    = new ByteCode(BC_pop2, "pop2", "b", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_dup]
//    	    = new ByteCode(BC_dup, "dup", "b", null, BasicTypes.T_VOID, 1, false, null);
//    	JBC[BC_dup_x1]
//    	    = new ByteCode(BC_dup_x1, "dup_x1", "b", null, BasicTypes.T_VOID, 1, false, null);
//    	JBC[BC_dup_x2]
//    	    = new ByteCode(BC_dup_x2, "dup_x2", "b", null, BasicTypes.T_VOID, 1, false, null);
//    	JBC[BC_dup2]
//    	    = new ByteCode(BC_dup2, "dup2", "b", null, BasicTypes.T_VOID, 2, false, null);
//    	JBC[BC_dup2_x1]
//    	    = new ByteCode(BC_dup2_x1, "dup2_x1", "b", null, BasicTypes.T_VOID, 2, false, null);
//    	JBC[BC_dup2_x2]
//    	    = new ByteCode(BC_dup2_x2, "dup2_x2", "b", null, BasicTypes.T_VOID, 2, false, null);
//    	JBC[BC_swap]
//    	    = new ByteCode(BC_swap, "swap", "b", null, BasicTypes.T_VOID, 0, false, null);
//    	JBC[BC_iadd]
//    	    = new ByteCode(BC_iadd, "iadd", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_ladd]
//    	    = new ByteCode(BC_ladd, "ladd", "b", null, BasicTypes.T_LONG, -2, false, null);
//    	JBC[BC_fadd]
//    	    = new ByteCode(BC_fadd, "fadd", "b", null, BasicTypes.T_FLOAT, -1, false, null);
//    	JBC[BC_dadd]
//    	    = new ByteCode(BC_dadd, "dadd", "b", null, BasicTypes.T_DOUBLE, -2, false, null);    	
//    	JBC[BC_isub]
//    	    = new ByteCode(BC_isub, "isub", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_lsub]
//    	    = new ByteCode(BC_lsub, "lsub", "b", null, BasicTypes.T_LONG, -2, false, null);
//    	JBC[BC_fsub]
//    	    = new ByteCode(BC_fsub, "fsub", "b", null, BasicTypes.T_FLOAT, -1, false, null);
//    	JBC[BC_dsub]
//    	    = new ByteCode(BC_dsub, "dsub", "b", null, BasicTypes.T_DOUBLE, -2, false, null);
//    	JBC[BC_imul]
//    	    = new ByteCode(BC_imul, "imul", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_lmul]
//    	    = new ByteCode(BC_lmul, "lmul", "b", null, BasicTypes.T_LONG, -2, false, null);
//    	JBC[BC_fmul]
//    	    = new ByteCode(BC_fmul, "fmul", "b", null, BasicTypes.T_FLOAT, -1, false, null);
//    	JBC[BC_dmul]
//    	    = new ByteCode(BC_dmul, "dmul", "b", null, BasicTypes.T_DOUBLE, -2, false, null);
//    	JBC[BC_idiv]
//    	    = new ByteCode(BC_idiv, "idiv", "b", null, BasicTypes.T_INT, -1, true, null);
//    	JBC[BC_ldiv]
//    	    = new ByteCode(BC_ldiv, "ldiv", "b", null, BasicTypes.T_LONG, -2, true, null);
//    	JBC[BC_fdiv]
//    	    = new ByteCode(BC_fdiv, "fdiv", "b", null, BasicTypes.T_FLOAT, -1, false, null);
//    	JBC[BC_ddiv]
//    	    = new ByteCode(BC_ddiv, "ddiv", "b", null, BasicTypes.T_DOUBLE, -2, false, null);
//    	JBC[BC_irem]
//    	    = new ByteCode(BC_irem, "irem", "b", null, BasicTypes.T_INT, -1, true, null);
//    	JBC[BC_lrem]
//    	    = new ByteCode(BC_lrem, "lrem", "b", null, BasicTypes.T_LONG, -2, true, null);
//    	JBC[BC_frem]
//    	    = new ByteCode(BC_frem, "frem", "b", null, BasicTypes.T_FLOAT, -1, false, null);
//    	JBC[BC_drem]
//    	    = new ByteCode(BC_drem, "drem", "b", null, BasicTypes.T_DOUBLE, -2, false, null);
//    	JBC[BC_ineg]
//    	    = new ByteCode(BC_ineg, "ineg", "b", null, BasicTypes.T_INT, 0, true, null);
//    	JBC[BC_lneg]
//    	    = new ByteCode(BC_lneg, "lneg", "b", null, BasicTypes.T_LONG, 0, true, null);
//    	JBC[BC_fneg]
//    	    = new ByteCode(BC_fneg, "fneg", "b", null, BasicTypes.T_FLOAT, 0, false, null);
//    	JBC[BC_dneg]
//    	    = new ByteCode(BC_dneg, "dneg", "b", null, BasicTypes.T_DOUBLE, 0, false, null);
//    	JBC[BC_ishl]
//    	    = new ByteCode(BC_ishl, "ishl", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_lshl]
//    	    = new ByteCode(BC_lshl, "lshl", "b", null, BasicTypes.T_LONG, -1, false, null);
//    	JBC[BC_ishr]
//    	    = new ByteCode(BC_ishr, "ishr", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_lshr]
//    	    = new ByteCode(BC_lshr, "lshr", "b", null, BasicTypes.T_LONG, -1, false, null);
//    	JBC[BC_iushr]
//    	    = new ByteCode(BC_iushr, "iushr", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_lushr]
//    	    = new ByteCode(BC_lushr, "lushr", "b", null, BasicTypes.T_LONG, -1, false, null);
//    	JBC[BC_iand]
//    	    = new ByteCode(BC_iand, "iand", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_land]
//    	    = new ByteCode(BC_land, "land", "b", null, BasicTypes.T_LONG, -2, false, null);
//    	JBC[BC_ior & 0xFF]
//    	    = new ByteCode(BC_ior, "ior", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_lor & 0xFF]
//    	    = new ByteCode(BC_lor, "lor", "b", null, BasicTypes.T_LONG, -2, false, null);
//    	JBC[BC_ixor & 0xFF]
//    	    = new ByteCode(BC_ixor, "ixor", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_lxor & 0xFF]
//    	    = new ByteCode(BC_lxor, "lxor", "b", null, BasicTypes.T_LONG, -2, false, null);
//    	JBC[BC_iinc & 0xFF]
//    	    = new ByteCode(BC_iinc, "iinc", "bic", "wbiicc", BasicTypes.T_VOID, 0, false, null);
//    	JBC[BC_i2l & 0xFF]
//    	    = new ByteCode(BC_i2l, "i2l", "b", null, BasicTypes.T_LONG, 1, false, null);
//    	JBC[BC_i2f & 0xFF]
//    	    = new ByteCode(BC_i2f, "i2f", "b", null, BasicTypes.T_FLOAT, 0, false, null);
//    	JBC[BC_i2d & 0xFF]
//    	    = new ByteCode(BC_i2d, "i2d", "b", null, BasicTypes.T_DOUBLE, 1, false, null);
//    	JBC[BC_l2i & 0xFF]
//    	    = new ByteCode(BC_l2i, "l2i", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_l2f & 0xFF]
//    	    = new ByteCode(BC_l2f, "l2f", "b", null, BasicTypes.T_FLOAT, -1, false, null);
//    	JBC[BC_l2d & 0xFF]
//    	    = new ByteCode(BC_l2d, "l2d", "b", null, BasicTypes.T_FLOAT, 0, false, null);
//    	JBC[BC_f2i & 0xFF]
//    	    = new ByteCode(BC_f2i, "f2i", "b", null, BasicTypes.T_INT, 0, false, null);
//    	JBC[BC_f2l & 0xFF]
//    	    = new ByteCode(BC_f2l, "f2l", "b", null, BasicTypes.T_LONG, 1, false, null);
//    	JBC[BC_f2d & 0xFF]
//    	    = new ByteCode(BC_f2d, "f2d", "b", null, BasicTypes.T_DOUBLE, 1, false, null);
//    	JBC[BC_d2i & 0xFF]
//    	    = new ByteCode(BC_d2i, "d2i", "b", null, BasicTypes.T_INT, -1, false, null);
//    	JBC[BC_d2l & 0xFF]
//    	    = new ByteCode(BC_d2l, "d2l", "b", null, BasicTypes.T_LONG, 0, false, null);
//    	JBC[BC_d2f & 0xFF]
//    	    = new ByteCode(BC_d2f, "d2f", "b", null, BasicTypes.T_FLOAT, -1, false, null);
//    	JBC[BC_i2b & 0xFF]
//    	    = new ByteCode(BC_i2b, "i2b", "b", null, BasicTypes.T_BYTE, 0, false, null);
//    	JBC[BC_i2c & 0xFF]
//    	    = new ByteCode(BC_i2c, "i2c", "b", null, BasicTypes.T_CHAR, 0, false, null);
//    	JBC[BC_i2s & 0xFF]
//    	    = new ByteCode(BC_i2s, "i2s", "b", null, BasicTypes.T_SHORT, 0, false, null);
//    	JBC[BC_lcmp & 0xFF]
//    	    = new ByteCode(BC_lcmp, "lcmp", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_fcmpl & 0xFF]
//    	    = new ByteCode(BC_fcmpl, "fcmpl", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_fcmpg & 0xFF]
//    	    = new ByteCode(BC_fcmpg, "fcmpg", "b", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_dcmpl & 0xFF]
//    	    = new ByteCode(BC_dcmpl, "dcmpl", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_dcmpg & 0xFF]
//    	    = new ByteCode(BC_dcmpg, "dcmpg", "b", null, BasicTypes.T_VOID, -3, false, null);
//    	JBC[BC_ifeq & 0xFF]
//    	    = new ByteCode(BC_ifeq, "ifeq", "boo", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_ifne & 0xFF]
//    	    = new ByteCode(BC_ifne, "ifne", "boo", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_iflt & 0xFF]
//    	    = new ByteCode(BC_iflt, "iflt", "boo", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_ifge & 0xFF]
//    	    = new ByteCode(BC_ifge, "ifge", "boo", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_ifgt & 0xFF]
//    	    = new ByteCode(BC_ifgt, "ifgt", "boo", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_ifle & 0xFF]
//    	    = new ByteCode(BC_ifle, "ifle", "boo", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_if_icmpeq & 0xFF]
//    	    = new ByteCode(BC_if_icmpeq, "if_icmpeq", "boo", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_if_icmpne & 0xFF]
//    	    = new ByteCode(BC_if_icmpne, "if_icmpne", "boo", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_if_icmplt & 0xFF]
//    	    = new ByteCode(BC_if_icmplt, "if_icmplt", "boo", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_if_icmpge & 0xFF]
//    	    = new ByteCode(BC_if_icmpge, "if_icmpge", "boo", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_if_icmpgt & 0xFF]
//    	    = new ByteCode(BC_if_icmpgt, "if_icmpgt", "boo", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_if_icmple & 0xFF]
//    	    = new ByteCode(BC_if_icmple, "if_icmple", "boo", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_if_acmpeq & 0xFF]
//    	    = new ByteCode(BC_if_acmpeq, "if_acmpeq", "boo", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_if_acmpne & 0xFF]
//    	    = new ByteCode(BC_if_acmpne, "if_acmpne", "boo", null, BasicTypes.T_VOID, -2, false, null);
//    	JBC[BC_goto & 0xFF]
//    	    = new ByteCode(BC_goto, "goto", "boo", null, BasicTypes.T_VOID, 0, false, null);
//    	JBC[BC_jsr & 0xFF]
//    	    = new ByteCode(BC_jsr, "jsr", "boo", null, BasicTypes.T_INT, 0, false, null);
//    	JBC[BC_ret & 0xFF]
//    	    = new ByteCode(BC_ret, "ret", "bi", "wbii", BasicTypes.T_VOID, 0, false, null);
//    	JBC[BC_tableswitch & 0xFF]
//    	    = new ByteCode(BC_tableswitch, "tableswitch", "", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_lookupswitch & 0xFF]
//    	    = new ByteCode(BC_lookupswitch, "lookupswitch", "", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_ireturn & 0xFF]
//    	    = new ByteCode(BC_ireturn, "ireturn", "b", null, BasicTypes.T_INT, -1, true, null);
//    	JBC[BC_lreturn & 0xFF]
//    	    = new ByteCode(BC_lreturn, "lreturn", "b", null, BasicTypes.T_LONG, -2, true, null);
//    	JBC[BC_freturn & 0xFF]
//    	    = new ByteCode(BC_freturn, "freturn", "b", null, BasicTypes.T_FLOAT, -1, true, null);
//    	JBC[BC_dreturn & 0xFF]
//    	    = new ByteCode(BC_dreturn, "dreturn", "b", null, BasicTypes.T_DOUBLE, -2, true, null);
//    	JBC[BC_areturn & 0xFF]
//    	    = new ByteCode(BC_areturn, "areturn", "b", null, BasicTypes.T_OBJECT, -1, true, null);
//    	JBC[BC_return & 0xFF]
//    	    = new ByteCode(BC_return, "return", "b", null, BasicTypes.T_VOID, 0, true, null);
//    	JBC[BC_getstatic & 0xFF]
//    	    = new ByteCode(BC_getstatic, "getstatic", "bjj", null, BasicTypes.T_ILLEGAL, 1, true, null);
//    	JBC[BC_putstatic & 0xFF]
//    	    = new ByteCode(BC_putstatic, "putstatic", "bjj", null, BasicTypes.T_ILLEGAL, -1, true, null);
//    	JBC[BC_getfield & 0xFF]
//    	    = new ByteCode(BC_getfield, "getfield", "bjj", null, BasicTypes.T_ILLEGAL, 0, true, null);
//    	JBC[BC_putfield & 0xFF]
//    	    = new ByteCode(BC_putfield, "putfield", "bjj", null, BasicTypes.T_ILLEGAL, -2, true, null);
//    	JBC[BC_invokevirtual & 0xFF]
//    	    = new ByteCode(BC_invokevirtual, "invokevirtual", "bjj", null, BasicTypes.T_ILLEGAL, -1, true, null);
//    	JBC[BC_invokespecial & 0xFF]
//    	    = new ByteCode(BC_invokespecial, "invokespecial", "bjj", null, BasicTypes.T_ILLEGAL, -1, true, null);
//    	JBC[BC_invokestatic & 0xFF]
//    	    = new ByteCode(BC_invokestatic, "invokestatic", "bjj", null, BasicTypes.T_ILLEGAL, 0, true, null);
//    	JBC[BC_invokeinterface & 0xFF]
//    	    = new ByteCode(BC_invokeinterface, "invokeinterface", "bjj__", null, BasicTypes.T_ILLEGAL, -1, true, null);
//    	JBC[BC_invokedynamic & 0xFF]
//    	    = new ByteCode(BC_invokedynamic, "invokedynamic", "bjjjj", null, BasicTypes.T_ILLEGAL, -1, true, null);
//    	JBC[BC_new & 0xFF]
//    	    = new ByteCode(BC_new, "new", "bii", null, BasicTypes.T_OBJECT, 1, true, null);
//    	JBC[BC_newarray & 0xFF]
//    	    = new ByteCode(BC_newarray, "newarray", "bc", null, BasicTypes.T_OBJECT, 0, true, null);
//    	JBC[BC_anewarray & 0xFF]
//    	    = new ByteCode(BC_anewarray, "anewarray", "bii", null, BasicTypes.T_OBJECT, 0, true, null);
//    	JBC[BC_arraylength & 0xFF]
//    	    = new ByteCode(BC_arraylength, "arraylength", "b", null, BasicTypes.T_VOID, 0, true, null);
//    	JBC[BC_athrow & 0xFF]
//    	    = new ByteCode(BC_athrow, "athrow", "b", null, BasicTypes.T_VOID, -1, true, null);
//    	JBC[BC_checkcast & 0xFF]
//    	    = new ByteCode(BC_checkcast, "checkcast", "bii", null, BasicTypes.T_OBJECT, 0, true, null);
//    	JBC[BC_instanceof & 0xFF]
//    	    = new ByteCode(BC_instanceof, "instanceof", "bii", null, BasicTypes.T_INT, 0, true, null);
//    	JBC[BC_monitorenter & 0xFF]
//    	    = new ByteCode(BC_monitorenter, "monitorenter", "b", null, BasicTypes.T_VOID, -1, true, null);
//    	JBC[BC_monitorexit & 0xFF]
//    	    = new ByteCode(BC_monitorexit, "monitorexit", "b", null, BasicTypes.T_VOID, -1, true, null);
//    	JBC[BC_wide & 0xFF]
//    	    = new ByteCode(BC_wide, "wide", "", null, BasicTypes.T_VOID, 0, false, null);
//    	JBC[BC_multianewarray & 0xFF]
//    	    = new ByteCode(BC_multianewarray, "multianewarray", "biic", null, BasicTypes.T_OBJECT, 1, true, null);
//    	JBC[BC_ifnull & 0xFF]
//    	    = new ByteCode(BC_ifnull, "ifnull", "boo", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_ifnonnull & 0xFF]
//    	    = new ByteCode(BC_ifnonnull, "ifnonnull", "boo", null, BasicTypes.T_VOID, -1, false, null);
//    	JBC[BC_goto_w & 0xFF]
//    	    = new ByteCode(BC_goto_w, "goto_w", "boooo", null, BasicTypes.T_VOID, 0, false, null);
//    	JBC[BC_jsr_w & 0xFF]
//    	    = new ByteCode(BC_jsr_w, "jsr_w", "boooo", null, BasicTypes.T_INT, 0, false, null);
//    	JBC[BC_breakpoint & 0xFF]
//    	    = new ByteCode(BC_breakpoint, "breakpoint", "", null, BasicTypes.T_VOID, 0, true, null); 
    }
}
