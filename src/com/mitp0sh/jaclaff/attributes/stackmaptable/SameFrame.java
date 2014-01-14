package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class SameFrame extends AbstractStackMapFrame
{
	public SameFrame()
	{
		setStack_map_frame_string_representation("same_frame");
		setStack_map_frame_is_explicit(true);
	}
	
	public static SameFrame deserialize(DesCtx ctx, short frameType, AttributeCode attributeCode, AbstractStackMapFrame previousFrame) throws IOException
    {
		SameFrame sameFrame = new SameFrame();
		sameFrame.setPreviousFrame(previousFrame);
		sameFrame.setOffsetDelta(frameType);
		
		decoupleFromOffsets(ctx, sameFrame, attributeCode);
		
		return sameFrame;
    }
	
	public static byte[] serialize(SerCtx ctx, SameFrame sameFrame) throws IOException
	{	
		coupleToOffsets(ctx, sameFrame, null);
		
		return new byte[]{};
	}
}

// The frame type same_frame is represented by tags in the range [0-63]. If 
// the frame type is same_frame, it means the frame has exactly the same 
// locals as the previous stack map frame and that the number of stack items 
// is zero. The offset_delta value for the frame is the value of the tag item,
// frame_type.

// same_frame {
//     u1 frame_type = SAME; /* 0-63 */
// }
