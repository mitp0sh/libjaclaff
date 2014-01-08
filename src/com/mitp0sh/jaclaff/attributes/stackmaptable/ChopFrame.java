package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ChopFrame extends AbstractStackMapFrame
{
	private short offsetDelta = 0;
	private byte            k = 0;

	public ChopFrame()
	{
		setStack_map_frame_string_representation("chop_frame");
	}
	
	public short getOffsetDelta() 
	{
		return offsetDelta;
	}

	public void setOffsetDelta(short offsetDelta) 
	{
		this.offsetDelta = offsetDelta;
	}
	
	public byte getK() 
	{
		return k;
	}

	public void setK(byte k) 
	{
		this.k = k;
	}
	
	public static ChopFrame deserialize(DataInputStream dis, byte frameType) throws IOException
    {
		ChopFrame chopFrame = new ChopFrame();
		chopFrame.setK((byte)(frameType - 251));
		
		/* read offset delta */
		short offsetDelta = dis.readShort();
		chopFrame.setOffsetDelta(offsetDelta);
		
		return chopFrame;
    }
	
	public static byte[] serialize(SerCtx ctx, ChopFrame chopFrame) throws IOException
	{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		/* serialize delta offset */
		baos.write(PNC.toByteArray(chopFrame.offsetDelta, Short.class));
		
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
