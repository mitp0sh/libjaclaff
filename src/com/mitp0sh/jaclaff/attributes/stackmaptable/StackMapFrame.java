package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.attributes.code.bytecode.MethodInstructions;
import com.mitp0sh.jaclaff.attributes.code.bytecode.SingleInstruction;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class StackMapFrame
{
	private AbstractStackMapFrame stackMapFrame = null;

	public AbstractStackMapFrame getStackMapFrame() 
	{
		return stackMapFrame;
	}

	public void setStackMapFrame(AbstractStackMapFrame stackMapFrame) 
	{
		this.stackMapFrame = stackMapFrame;
	}
	
	public static StackMapFrame deserialize(DesCtx ctx, AttributeCode attributeCode, ArrayList<StackMapFrame> stackMapFrameList, AbstractStackMapFrame previousFrame) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		StackMapFrame stackMapFrame = new StackMapFrame();
		AbstractStackMapFrame  asmf = null;
		
		/* read frame type */
		short frameType = (short)dis.readUnsignedByte();
		if(frameType >= FrameType.SAME_FRAME_START &&
		   frameType <= FrameType.SAME_FRAME_END)
		{
			SameFrame sameFrame = SameFrame.deserialize(ctx, frameType, attributeCode, previousFrame);
			asmf = sameFrame;
		}
		else
		if(frameType >= FrameType.SAME_LOCALS_1_STACK_ITEM_FRAME_START &&
		   frameType <= FrameType.SAME_LOCALS_1_STACK_ITEM_FRAME_END)
		{
			SameLocals1StackItemFrame sameLocals1StackItemFrame = SameLocals1StackItemFrame.deserialize(ctx, frameType, attributeCode, previousFrame);
			asmf = sameLocals1StackItemFrame; 
		}
		else
		if(frameType == FrameType.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED)
		{
			SameLocals1StackItemFrameExtended sameLocals1StackItemFrameExtended = SameLocals1StackItemFrameExtended.deserialize(ctx, attributeCode, previousFrame);
			asmf = sameLocals1StackItemFrameExtended; 
		}
		else
		if(frameType >= FrameType.CHOP_FRAME_START &&
		   frameType <= FrameType.CHOP_FRAME_END)
		{
			ChopFrame chopFrame = ChopFrame.deserialize(ctx, frameType, attributeCode, previousFrame);
			asmf = chopFrame; 
		}
		else
		if(frameType == FrameType.SAME_FRAME_EXTENDED)
		{
			SameFrameExtended sameFrameExtended = SameFrameExtended.deserialize(ctx, attributeCode, previousFrame);
			asmf = sameFrameExtended;
		}
		else
		if(frameType >= FrameType.APPEND_FRAME_START &&
		   frameType <= FrameType.APPEND_FRAME_END)
		{
			AppendFrame appendFrame = AppendFrame.deserialize(ctx, frameType, attributeCode, previousFrame);
			asmf = appendFrame; 
		}
		else
		if(frameType == FrameType.FULL_FRAME)
		{
			FullFrame fullFrame = FullFrame.deserialize(ctx, frameType, attributeCode, previousFrame);
			asmf = fullFrame;
		}
		else
		{
			System.err.println("error - unable to deserialize stack map frame !!!");
			System.exit(-1);
		}
	
		stackMapFrame.setStackMapFrame(asmf);
		
		return stackMapFrame;
    }
	
	@SuppressWarnings("rawtypes")
	public static byte[] serialize(SerCtx ctx, StackMapFrame stackMapFrame) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		Class smfClass = stackMapFrame.getStackMapFrame().getClass();
		
		if(smfClass.toString().equals((SameFrame.class.toString())))
		{
			/* retrieve append frame */
			SameFrame sameFrame = (SameFrame)stackMapFrame.getStackMapFrame();
			
			/* serialize frame type */
			byte frameType = (byte)(sameFrame.getOffsetDelta());
			baos.write(new byte[]{frameType});
			
			/* serialize same frame payload */
			baos.write(SameFrame.serialize(ctx, sameFrame));
		}
		else
		if(smfClass.toString().equals((SameLocals1StackItemFrame.class.toString())))
		{
			/* retrieve append frame */
			SameLocals1StackItemFrame sameLocals1StackItemFrame = (SameLocals1StackItemFrame)stackMapFrame.getStackMapFrame();
			
			/* serialize frame type */
			byte frameType = (byte)(64 + sameLocals1StackItemFrame.getOffsetDelta());
			baos.write(new byte[]{frameType});
			
			/* serialize sameLocals1StackItemFrame frame payload */			
			baos.write(SameLocals1StackItemFrame.serialize(ctx, sameLocals1StackItemFrame));
		}
		else
		if(smfClass.toString().equals((SameLocals1StackItemFrameExtended.class.toString())))
		{
			/* serialize frame type */
			byte frameType = (byte)(FrameType.SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED);
			baos.write(new byte[]{frameType});
			
			/* serialize sameLocals1StackItemFrameExtended frame payload */	
			baos.write(SameLocals1StackItemFrameExtended.serialize(ctx, (SameLocals1StackItemFrameExtended)stackMapFrame.getStackMapFrame()));
		}
		else
		if(smfClass.toString().equals((ChopFrame.class.toString())))
		{
			/* retrieve append frame */
			ChopFrame chopFrame = (ChopFrame)stackMapFrame.getStackMapFrame();
			
			/* serialize frame type */
			byte frameType = (byte)(251 + chopFrame.getK());
			baos.write(new byte[]{frameType});
			
			baos.write(ChopFrame.serialize(ctx, chopFrame));
		}
		else
		if(smfClass.toString().equals((SameFrameExtended.class.toString())))
		{
			/* serialize frame type */
			byte frameType = (byte)(FrameType.SAME_FRAME_EXTENDED);
			baos.write(new byte[]{frameType});
			
			/* serialize same frame extended payload */
			baos.write(SameFrameExtended.serialize(ctx, (SameFrameExtended)stackMapFrame.getStackMapFrame()));
		}
		else
		if(smfClass.toString().equals(AppendFrame.class.toString()))
		{			
			/* retrieve append frame */
			AppendFrame appendFrame = (AppendFrame)stackMapFrame.getStackMapFrame();
			
			/* serialize frame type */
			byte frameType = (byte)(251 + (byte)appendFrame.getLocals().size());
			baos.write(new byte[]{frameType});
			
			/* serialize append frame payload */
			baos.write(AppendFrame.serialize(ctx, appendFrame));
		}
		else
		if(smfClass.toString().equals(FullFrame.class.toString()))
		{
			FullFrame fullFrame = (FullFrame)stackMapFrame.getStackMapFrame();
			
			/* serialize frame type */
			byte frameType = (byte)(FrameType.FULL_FRAME);
			baos.write(new byte[]{frameType});
			
			/* serialize full frame payload */
			baos.write(FullFrame.serialize(ctx, fullFrame));
		}
		else
		{
			System.err.println("error - unable to serialize stack map frame !!!");
			System.exit(-1);
		}
		
		return baos.toByteArray();
	}
	
	public static SingleInstruction getDeltaOffsetInstructionFromFrame(MethodInstructions disassembly, AbstractStackMapFrame frame)
	{
		int                  byteCodeOffset = 0;
		int     byteCodeOffsetPreviousFrame = 0;
		AbstractStackMapFrame previousFrame = frame.getPreviousFrame();
		if(previousFrame != null)
		{
			byteCodeOffsetPreviousFrame = previousFrame.getStack_map_frame_bytecode_offset();
		}
		byteCodeOffset += byteCodeOffsetPreviousFrame;
		byteCodeOffset += frame.getOffsetDelta();		
		
		if(previousFrame != null)
		{
			if(frame.isStack_map_frame_is_explicit())
			{
				byteCodeOffset++;
			}
		}

		frame.setStack_map_frame_bytecode_offset(byteCodeOffset);
		
		SingleInstruction instruction = MethodInstructions.lookupInstructionByOffset(disassembly, byteCodeOffset);
		if(instruction == null)
		{
			System.err.println("error - unable to couple delta offset with instruction object !!!");
			System.exit(-1);
		}
	
		return instruction;	
	}
}
