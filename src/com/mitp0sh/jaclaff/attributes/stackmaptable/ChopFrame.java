package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ChopFrame extends AbstractStackMapFrame
{
	private byte k = 0;

	public ChopFrame()
	{
		setStack_map_frame_string_representation("chop_frame");
		setStack_map_frame_is_explicit(true);
	}
	
	public byte getK() 
	{
		return k;
	}

	public void setK(byte k) 
	{
		this.k = k;
	}
	
	public static ChopFrame deserialize(DesCtx ctx, short frameType, AttributeCode attributeCode, AbstractStackMapFrame previousFrame) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		ChopFrame chopFrame = new ChopFrame();
		chopFrame.setPreviousFrame(previousFrame);
		chopFrame.setK((byte)(frameType - 251));
		
		/* read offset delta */
		int offsetDelta = dis.readUnsignedShort();
		chopFrame.setOffsetDelta(offsetDelta);
		
		decoupleFromOffsets(ctx, chopFrame, attributeCode);
		
		return chopFrame;
    }
	
	public static byte[] serialize(SerCtx ctx, ChopFrame frame) throws IOException
	{	
		coupleToOffsets(ctx, frame, null);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		/* serialize delta offset */
		baos.write(PNC.toByteArray(frame.offsetDelta, Short.class));
		
		return baos.toByteArray();
	}
}

// The frame type chop_frame is represented by tags in the range [248-250]. 
// If the frame_type is chop_frame, it means that the operand stack is empty 
// and the current locals are the same as the locals in the previous frame, 
// except that the k last locals are absent. The value of k is given by the 
// formula 251 - frame_type.

// chop_frame {
//     u1 frame_type = CHOP; /* 248-250 */
//     u2 offset_delta;
// }
