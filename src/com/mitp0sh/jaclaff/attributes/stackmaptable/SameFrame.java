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
	
	public static byte[] serialize(SerCtx ctx, SameFrame sameFrame, AttributeCode attributeCode) throws IOException
	{			
		return new byte[]{};
	}
}
