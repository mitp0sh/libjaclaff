package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class SameLocals1StackItemFrame extends AbstractStackMapFrame
{
	private byte           offsetDelta = 0;
	private VerificationTypeInfo stack = null;

	public SameLocals1StackItemFrame()
	{
		setStack_map_frame_string_representation("same_locals_1_stack_item_frame");
	}
	
	public byte getOffsetDelta() 
	{
		return offsetDelta;
	}

	public void setOffsetDelta(byte offsetDelta) 
	{
		this.offsetDelta = offsetDelta;
	}
	
	public VerificationTypeInfo getStack() 
	{
		return stack;
	}

	public void setStack(VerificationTypeInfo stack) 
	{
		this.stack = stack;
	}
	
	public static SameLocals1StackItemFrame deserialize(DataInputStream dis, ConstantPool constantPool, byte frameType) throws IOException
    {
		SameLocals1StackItemFrame sameLocals1StackItemFrame = new SameLocals1StackItemFrame();
		sameLocals1StackItemFrame.setOffsetDelta((byte)(frameType - 64));
		
		/* deserialize single verification type info */
		sameLocals1StackItemFrame.setStack(VerificationTypeInfo.deserialize(dis, constantPool));
		
		return sameLocals1StackItemFrame;
    }
	
	public static byte[] serialize(SerCtx ctx, SameLocals1StackItemFrame sameLocals1StackItemFrame) throws IOException
	{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		/* serialize single verification type info */
		baos.write(VerificationTypeInfo.serialize(ctx, sameLocals1StackItemFrame.getStack()));
		
		return baos.toByteArray();
	}
}

// The frame type same_locals_1_stack_item_frame is represented by tags in the 
// range [64, 127]. If the frame_type is same_locals_1_stack_item_frame, it 
// means the frame has exactly the same locals as the previous stack map frame 
// and that the number of stack items is 1. The offset_delta value for the 
// frame is the value (frame_type - 64). There is a verification_type_info 
// following the frame_type for the one stack item.

// same_locals_1_stack_item_frame {
//     u1 frame_type = SAME_LOCALS_1_STACK_ITEM; /* 64-127 */
//     verification_type_info stack[1];
// }

// Tags in the range [128-246] are reserved for future use.
