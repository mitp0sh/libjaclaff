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
	
	public static byte[] serialize(SerCtx ctx, ChopFrame frame, AttributeCode attributeCode) throws IOException
	{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		/* serialize delta offset */
		baos.write(PNC.toByteArray(frame.offsetDelta, Short.class));
		
		return baos.toByteArray();
	}
}
