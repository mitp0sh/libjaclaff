package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class SameLocals1StackItemFrameExtended extends AbstractStackMapFrame
{
	private short                offsetDelta = 0;
	private VerificationTypeInfo stackObject = null;
	
	public SameLocals1StackItemFrameExtended()
	{
		setStack_map_frame_string_representation("same_locals_1_stack_item_frame_extended");
	}
	
	public short getOffsetDelta() 
	{
		return offsetDelta;
	}

	public void setOffsetDelta(short offsetDelta) 
	{
		this.offsetDelta = offsetDelta;
	}

	public VerificationTypeInfo getStackObject() 
	{
		return stackObject;
	}

	public void setStackObject(VerificationTypeInfo stackObject)
	{
		this.stackObject = stackObject;
	}
	
	public static SameLocals1StackItemFrameExtended deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		SameLocals1StackItemFrameExtended sameLocals1StackItemFrameExtended = new SameLocals1StackItemFrameExtended();
		
		/* read offset delta */
		short offsetDelta = dis.readShort();
		sameLocals1StackItemFrameExtended.setOffsetDelta(offsetDelta);
		
		/* deserialize single verification type info */
		sameLocals1StackItemFrameExtended.setStackObject(VerificationTypeInfo.deserialize(dis, constantPool));
		
		return sameLocals1StackItemFrameExtended;
    }
	
	public static byte[] serialize(SerCtx ctx, SameLocals1StackItemFrameExtended sameLocals1StackItemFrameExtended) throws IOException
	{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		/* serialize delta offset */
		baos.write(PNC.toByteArray(sameLocals1StackItemFrameExtended.offsetDelta, Short.class));

		/* serialize single verification type info */
		baos.write(VerificationTypeInfo.serialize(ctx, sameLocals1StackItemFrameExtended.getStackObject()));
		
		return baos.toByteArray();
	}
}

// The frame type same_locals_1_stack_item_frame_extended is represented by the
// tag 247. The frame type same_locals_1_stack_item_frame_extended indicates
// that the frame has exactly the same locals as the previous stack map frame 
// and that the number of stack items is 1. The offset_delta value for the 
// frame is given explicitly. There is a verification_type_info following the 
// frame_type for the one stack item.

// same_locals_1_stack_item_frame_extended {
//     u1 frame_type = SAME_LOCALS_1_STACK_ITEM_EXTENDED; /* 247 */
//     u2 offset_delta;
//     verification_type_info stack[1];
//}
