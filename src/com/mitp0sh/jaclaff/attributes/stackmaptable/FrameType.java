package com.mitp0sh.jaclaff.attributes.stackmaptable;

public final class FrameType
{
	public static final short SAME_FRAME_START                        = 0;
	public static final short SAME_FRAME_END                          = 63;
	public static final short SAME_LOCALS_1_STACK_ITEM_FRAME_START    = 64;
	public static final short SAME_LOCALS_1_STACK_ITEM_FRAME_END      = 127;
	public static final short SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = 247;
	public static final short CHOP_FRAME_START                        = 248;
	public static final short CHOP_FRAME_END                          = 250;
	public static final short SAME_FRAME_EXTENDED                     = 251;
	public static final short APPEND_FRAME_START                      = 252;
	public static final short APPEND_FRAME_END                        = 254;
	public static final short FULL_FRAME                              = 255;
}
