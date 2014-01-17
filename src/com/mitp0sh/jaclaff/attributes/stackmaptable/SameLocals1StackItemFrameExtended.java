package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class SameLocals1StackItemFrameExtended extends AbstractStackMapFrame
{
	private VerificationTypeInfo stackObject = null;
	
	public SameLocals1StackItemFrameExtended()
	{
		setStack_map_frame_string_representation("same_locals_1_stack_item_frame_extended");
		setStack_map_frame_is_explicit(true);
	}

	public VerificationTypeInfo getStackObject() 
	{
		return stackObject;
	}

	public void setStackObject(VerificationTypeInfo stackObject)
	{
		this.stackObject = stackObject;
	}
	
	public static SameLocals1StackItemFrameExtended deserialize(DesCtx ctx, AttributeCode attributeCode, AbstractStackMapFrame previousFrame) throws IOException
    {
		DataInputStream       dis = ctx.getDataInputStream();
		ConstantPool constantPool = ctx.getConstantPool();
		
		SameLocals1StackItemFrameExtended sameLocals1StackItemFrameExtended = new SameLocals1StackItemFrameExtended();
		sameLocals1StackItemFrameExtended.setPreviousFrame(previousFrame);
		
		/* read offset delta */
		int offsetDelta = dis.readUnsignedShort();
		sameLocals1StackItemFrameExtended.setOffsetDelta(offsetDelta);
		
		/* deserialize single verification type info */
		sameLocals1StackItemFrameExtended.setStackObject(VerificationTypeInfo.deserialize(dis, constantPool));
		
		decoupleFromOffsets(ctx, sameLocals1StackItemFrameExtended, attributeCode);
		
		return sameLocals1StackItemFrameExtended;
    }
	
	public static byte[] serialize(SerCtx ctx, SameLocals1StackItemFrameExtended sameLocals1StackItemFrameExtended, AttributeCode attributeCode) throws IOException
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
