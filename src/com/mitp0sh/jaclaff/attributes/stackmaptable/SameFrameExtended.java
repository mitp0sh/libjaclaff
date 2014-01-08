package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class SameFrameExtended extends AbstractStackMapFrame
{
	private short offsetDelta = 0;

	public SameFrameExtended()
	{
		setStack_map_frame_string_representation("same_frame_extended");
	}
	
	public short getOffsetDelta() 
	{
		return offsetDelta;
	}

	public void setOffsetDelta(short offsetDelta) 
	{
		this.offsetDelta = offsetDelta;
	}
	
	public static SameFrameExtended deserialize(DataInputStream dis) throws IOException
    {
		SameFrameExtended sameFrameExtended = new SameFrameExtended();
		
		/* read offset delta */
		short offsetDelta = (byte)dis.readUnsignedShort();
		sameFrameExtended.setOffsetDelta(offsetDelta);
		
		return sameFrameExtended;
    }
	
	public static byte[] serialize(SerCtx ctx, SameFrameExtended sameFrameExtended) throws IOException
	{	
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