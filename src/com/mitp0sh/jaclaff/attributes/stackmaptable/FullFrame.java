package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class FullFrame extends AbstractStackMapFrame
{
	private ArrayList<VerificationTypeInfo> locals = new ArrayList<VerificationTypeInfo>();
	private ArrayList<VerificationTypeInfo>  stack = new ArrayList<VerificationTypeInfo>();
	
	public FullFrame()
	{
		setStack_map_frame_string_representation("full_frame");
		setStack_map_frame_is_explicit(true);
	}

	public int getNumberOfLocals() 
	{
		return locals.size();
	}

	public ArrayList<VerificationTypeInfo> getLocals() 
	{
		return locals;
	}

	public void setLocals(ArrayList<VerificationTypeInfo> locals) 
	{
		this.locals = locals;
	}

	public int getNumberOfStackItems() 
	{
		return stack.size();
	}

	public ArrayList<VerificationTypeInfo> getStack() 
	{
		return stack;
	}

	public void setStack(ArrayList<VerificationTypeInfo> stack) 
	{
		this.stack = stack;
	}
	
	public static FullFrame deserialize(DesCtx ctx, short frameType, AttributeCode code, AbstractStackMapFrame previousFrame) throws IOException
    {
		DataInputStream       dis = ctx.getDataInputStream();
		ConstantPool constantPool = ctx.getConstantPool();
		
		/* create new append frame instance */
		FullFrame fullFrame = new FullFrame();	
		fullFrame.setPreviousFrame(previousFrame);
		
		/* read offset delta */
		int offsetDelta = dis.readUnsignedShort();
		fullFrame.setOffsetDelta(offsetDelta);
		
		int numberOfLocals = dis.readUnsignedShort();		
		for(int i = 0; i < numberOfLocals; i++)
		{
			fullFrame.locals.add(VerificationTypeInfo.deserialize(dis, constantPool));
		}
		
		int numberOfStackItems = dis.readUnsignedShort();	
		for(int i = 0; i < numberOfStackItems; i++)
		{
			fullFrame.stack.add(VerificationTypeInfo.deserialize(dis, constantPool));
		}
		
		decoupleFromOffsets(ctx, fullFrame, code);
		
		return fullFrame;
    }
	
	public static byte[] serialize(SerCtx ctx, FullFrame fullFrame, AttributeCode attributeCode) throws IOException
	{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		/* serialize delta offset */
		baos.write(PNC.toByteArray(fullFrame.getOffsetDelta(), Short.class));
		
		/* write number of locals */
		baos.write(PNC.toByteArray(fullFrame.getNumberOfLocals(), Short.class));
		
		/* write locals verification type infos */
		for(int i = 0; i < fullFrame.getNumberOfLocals(); i++)
		{
			baos.write(VerificationTypeInfo.serialize(ctx, fullFrame.getLocals().get(i)));
		}
		
		/* write number of stack items */
		baos.write(PNC.toByteArray(fullFrame.getNumberOfStackItems(), Short.class));
		
		/* write stack item verification type infos */
		for(int i = 0; i < fullFrame.getNumberOfStackItems(); i++)
		{
			baos.write(VerificationTypeInfo.serialize(ctx, fullFrame.getStack().get(i)));
		}
		
		return baos.toByteArray();
	}
}
