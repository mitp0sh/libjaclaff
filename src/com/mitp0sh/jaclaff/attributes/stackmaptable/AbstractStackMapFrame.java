package com.mitp0sh.jaclaff.attributes.stackmaptable;

public abstract class AbstractStackMapFrame
{
	private short  stack_map_frame_type;
	private String stack_map_frame_string_representation;
	
	public static byte FRAME_TYPE_SAME_FRAME_START                        = 0;
	public static byte FRAME_TYPE_SAME_FRAME_END                          = 63;
	public static byte FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM_FRAME_START    = 64;
	public static byte FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM_FRAME_END      = 127;
	public static byte FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED = (byte)247;
	public static byte FRAME_TYPE_CHOP_FRAME_START                        = (byte)248;
	public static byte FRAME_TYPE_CHOP_FRAME_END                          = (byte)250;
	public static byte FRAME_TYPE_SAME_FRAME_EXTENDED                     = (byte)251;
	public static byte FRAME_TYPE_APPEND_FRAME_START                      = (byte)252;
	public static byte FRAME_TYPE_APPEND_FRAME_END                        = (byte)254;
	public static byte FRAME_TYPE_FULL_FRAME                              = (byte)255;
	
	public short getStack_map_frame_type() 
	{
		return stack_map_frame_type;
	}
	
	public void setStack_map_frame_type(short stack_map_frame_type) 
	{
		this.stack_map_frame_type = stack_map_frame_type;
	}
	
	public String getStack_map_frame_string_representation() 
	{
		return stack_map_frame_string_representation;
	}
	
	public void setStack_map_frame_string_representation(String stack_map_frame_string_representation) 
	{
		this.stack_map_frame_string_representation = stack_map_frame_string_representation;
	}
}
