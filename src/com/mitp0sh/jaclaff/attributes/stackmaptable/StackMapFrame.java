package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
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
	
	public static StackMapFrame deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		StackMapFrame stackMapFrame = new StackMapFrame();
		AbstractStackMapFrame asmf = null;
		
		/* read frame type */
		byte frameType = (byte)dis.readUnsignedByte();
		if(frameType >= AbstractStackMapFrame.FRAME_TYPE_SAME_FRAME_START &&
		   frameType <= AbstractStackMapFrame.FRAME_TYPE_SAME_FRAME_END)
		{
			SameFrame sameFrame = SameFrame.deserialize(dis, frameType);
			asmf = sameFrame;
		}
		else
		if(frameType >= AbstractStackMapFrame.FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM_FRAME_START &&
		   frameType <= AbstractStackMapFrame.FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM_FRAME_END)
		{
			SameLocals1StackItemFrame sameLocals1StackItemFrame = SameLocals1StackItemFrame.deserialize(dis, constantPool, frameType);
			asmf = sameLocals1StackItemFrame; 
		}
		else
		if(frameType == AbstractStackMapFrame.FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED)
		{
			SameLocals1StackItemFrameExtended sameLocals1StackItemFrameExtended = SameLocals1StackItemFrameExtended.deserialize(dis, constantPool);
			asmf = sameLocals1StackItemFrameExtended; 
		}
		else
		if(frameType >= AbstractStackMapFrame.FRAME_TYPE_CHOP_FRAME_START &&
		   frameType <= AbstractStackMapFrame.FRAME_TYPE_CHOP_FRAME_END)
		{
			ChopFrame chopFrame = ChopFrame.deserialize(dis, frameType);
			asmf = chopFrame; 
		}
		else
		if(frameType == AbstractStackMapFrame.FRAME_TYPE_SAME_FRAME_EXTENDED)
		{
			SameFrameExtended sameFrameExtended = SameFrameExtended.deserialize(dis);
			asmf = sameFrameExtended;
		}
		else
		if(frameType >= AbstractStackMapFrame.FRAME_TYPE_APPEND_FRAME_START &&
		   frameType <= AbstractStackMapFrame.FRAME_TYPE_APPEND_FRAME_END)
		{
			AppendFrame appendFrame = AppendFrame.deserialize(dis, constantPool, frameType);
			asmf = appendFrame; 
		}
		else
		if(frameType == AbstractStackMapFrame.FRAME_TYPE_FULL_FRAME)
		{
			FullFrame fullFrame = FullFrame.deserialize(dis, constantPool, frameType);
			asmf = fullFrame;
		}
		else
		{
			return null;
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
			byte frameType = (byte)(AbstractStackMapFrame.FRAME_TYPE_SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED);
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
			byte frameType = (byte)(AbstractStackMapFrame.FRAME_TYPE_SAME_FRAME_EXTENDED);
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
			byte frameType = (byte)(AbstractStackMapFrame.FRAME_TYPE_FULL_FRAME);
			baos.write(new byte[]{frameType});
			
			/* serialize full frame payload */
			baos.write(FullFrame.serialize(ctx, fullFrame));
		}
		else
		{
			System.err.println("Unable to serialize StackMapFrame!");
			return null;
		}
		
		return baos.toByteArray();
	}
}

// Each stack_map_frame structure specifies the type state at a particular 
// bytecode offset. Each frame type specifies (explicitly or implicitly) a 
// value, offset_delta, that is used to calculate the actual bytecode offset 
// at which a frame applies. The bytecode offset at which a frame applies is 
// calculated by adding offset_delta + 1 to the bytecode offset of the previous 
// frame, unless the previous frame is the initial frame of the method, in which
// case the bytecode offset is offset_delta.

// By using an offset delta rather than the actual bytecode offset we ensure, 
// by definition, that stack map frames are in the correctly sorted order. 
// Furthermore, by consistently using the formula offset_delta + 1 for all 
// explicit frames, we guarantee the absence of duplicates.

// We say that an instruction in the bytecode has a corresponding stack map 
// frame if the instruction starts at offset i in the code array of a Code 
// attribute, and the Code attribute has a StackMapTable attribute whose 
// entries array has a stack_map_frame structure that applies at bytecode 
// offset i.

// The stack_map_frame structure consists of a one-byte tag followed by zero 
// or more bytes, giving more information, depending upon the tag.

// A stack map frame may belong to one of several frame types:

// union stack_map_frame {
//     same_frame;
//     same_locals_1_stack_item_frame;
//     same_locals_1_stack_item_frame_extended;
//     chop_frame;
//     same_frame_extended;
//     append_frame;
//     full_frame;
// }

// All frame types, even full_frame, rely on the previous frame for some of 
// their semantics. This raises the question of what is the very first frame? 
// The initial frame is implicit, and computed from the method descriptor. 
// (See the Prolog predicate methodInitialStackFrame (§4.10.1.6).)
