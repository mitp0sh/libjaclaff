package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class SameFrameExtended extends AbstractStackMapFrame
{
	public SameFrameExtended()
	{
		setStack_map_frame_string_representation("same_frame_extended");
		setStack_map_frame_is_explicit(true);
	}
	
	public static SameFrameExtended deserialize(DesCtx ctx, AttributeCode attributeCode, AbstractStackMapFrame previousFrame) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		SameFrameExtended sameFrameExtended = new SameFrameExtended();
		sameFrameExtended.setPreviousFrame(previousFrame);
		
		/* read offset delta */
		int offsetDelta = dis.readUnsignedShort();
		sameFrameExtended.setOffsetDelta(offsetDelta);
		
		decoupleFromOffsets(ctx, sameFrameExtended, attributeCode);
		
		return sameFrameExtended;
    }
	
	public static byte[] serialize(SerCtx ctx, SameFrameExtended sameFrameExtended) throws IOException
	{	
		coupleToOffsets(ctx, sameFrameExtended, null);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		baos.write(PNC.toByteArray(sameFrameExtended.getOffsetDelta(), Short.class));
		
		return baos.toByteArray();
	}
}

// The frame type same_frame_extended is represented by the tag value 251. If 
// the frame type is same_frame_extended, it means the frame has exactly the 
// same locals as the previous stack map frame and that the number of stack 
// items is zero.

// same_frame_extended {
//     u1 frame_type = SAME_FRAME_EXTENDED; /* 251 */
//     u2 offset_delta;
// }