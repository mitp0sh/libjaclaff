package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class AppendFrame extends AbstractStackMapFrame
{
	private ArrayList<VerificationTypeInfo> locals = new ArrayList<VerificationTypeInfo>();
	
	public AppendFrame()
	{
		setStack_map_frame_string_representation("append_frame");
		setStack_map_frame_is_explicit(true);
	}

	public ArrayList<VerificationTypeInfo> getLocals() 
	{
		return locals;
	}

	public void setLocals(ArrayList<VerificationTypeInfo> locals) 
	{
		this.locals = locals;
	}
	
	public static AppendFrame deserialize(DesCtx ctx, short frameType, AttributeCode attributeCode, AbstractStackMapFrame previousFrame) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		ConstantPool constantPool = ctx.getConstantPool();
		
		/* create new append frame instance */
		AppendFrame appendFrame = new AppendFrame();		
		appendFrame.setPreviousFrame(previousFrame);
		
		/* read offset delta */
		int offsetDelta = dis.readUnsignedShort();
		appendFrame.setOffsetDelta(offsetDelta);
		byte k = (byte)(frameType - 251);
		
		/* process all verification type infos */
		for(int i = 0; i < k; i++)
		{
			appendFrame.getLocals().add(VerificationTypeInfo.deserialize(dis, constantPool));
		}
		
		decoupleFromOffsets(ctx, appendFrame, attributeCode);
		
		return appendFrame;
    }
	
	public static byte[] serialize(SerCtx ctx, AppendFrame appendFrame, AttributeCode attributeCode) throws IOException
	{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		/* serialize delta offset */
		baos.write(PNC.toByteArray(appendFrame.offsetDelta, Short.class));
		
		/* serialize all verifications type infos */
		Iterator<VerificationTypeInfo> iter = appendFrame.getLocals().iterator();
		while(iter.hasNext())
		{
			/* serialize current verification type info */
			VerificationTypeInfo verificationTypeInfo = iter.next();
			baos.write(VerificationTypeInfo.serialize(ctx, verificationTypeInfo));
		}
		
		return baos.toByteArray();
	}
}
