package com.mitp0sh.jaclaff.attributes.stackmaptable;

import com.mitp0sh.jaclaff.attributes.AttributeCode;
import com.mitp0sh.jaclaff.attributes.code.AbstractInstruction;
import com.mitp0sh.jaclaff.attributes.code.Disassembly;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public abstract class AbstractStackMapFrame
{
	private short                   stack_map_frame_type = 0;
	private String stack_map_frame_string_representation = "";
	private boolean          stack_map_frame_is_explicit = false; 
	private int          stack_map_frame_bytecode_offset = 0;
	public int                               offsetDelta = 0;
	private AbstractStackMapFrame          previousFrame = null;
	private AbstractStackMapFrame              nextFrame = null;
	private AbstractInstruction   offsetDeltaInstruction = null;
	
	public short getStack_map_frame_type() 
	{
		return stack_map_frame_type;
	}
	
	public void setStack_map_frame_type(short stack_map_frame_type) 
	{
		this.stack_map_frame_type = stack_map_frame_type;
	}
	
	public String getStack_map_frame_string_representation() 
	{
		return stack_map_frame_string_representation;
	}
	
	public void setStack_map_frame_string_representation(String stack_map_frame_string_representation) 
	{
		this.stack_map_frame_string_representation = stack_map_frame_string_representation;
	}
	
	public boolean isStack_map_frame_is_explicit()
	{
		return stack_map_frame_is_explicit;
	}

	public void setStack_map_frame_is_explicit(boolean stack_map_frame_is_explicit)
	{
		this.stack_map_frame_is_explicit = stack_map_frame_is_explicit;
	}
	
	public int getStack_map_frame_bytecode_offset() 
	{
		return stack_map_frame_bytecode_offset;
	}

	public void setStack_map_frame_bytecode_offset(int stack_map_frame_bytecode_offset) 
	{
		this.stack_map_frame_bytecode_offset = stack_map_frame_bytecode_offset;
	}
	
	public int getOffsetDelta()
	{
		return offsetDelta;
	}

	public void setOffsetDelta(int offsetDelta) 
	{
		this.offsetDelta = offsetDelta;
	}
	
	public AbstractInstruction getOffsetDeltaInstruction()
	{
		return offsetDeltaInstruction;
	}

	public void setOffsetDeltaInstruction(AbstractInstruction offsetDeltaInstruction) 
	{
		this.offsetDeltaInstruction = offsetDeltaInstruction;
	}
	
	public AbstractStackMapFrame getPreviousFrame() 
	{
		return previousFrame;
	}

	public void setPreviousFrame(AbstractStackMapFrame previousFrame) 
	{
		this.previousFrame = previousFrame;
	}
	
	public AbstractStackMapFrame getNextFrame() 
	{
		return nextFrame;
	}

	public void setNextFrame(AbstractStackMapFrame nextFrame) 
	{
		this.nextFrame = nextFrame;
	}
	
	public boolean isAppendFrame()
	{
		try
		{
			@SuppressWarnings("unused")
			AppendFrame frame = (AppendFrame)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isChopFrame()
	{
		try
		{
			@SuppressWarnings("unused")
			ChopFrame frame = (ChopFrame)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isFullFrame()
	{
		try
		{
			@SuppressWarnings("unused")
			FullFrame frame = (FullFrame)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isSameFrame()
	{
		try
		{
			@SuppressWarnings("unused")
			SameFrame frame = (SameFrame)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isSameLocals1StackItemFrame()
	{
		try
		{
			@SuppressWarnings("unused")
			SameLocals1StackItemFrame frame = (SameLocals1StackItemFrame)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public boolean isSameLocals1StackItemFrameExtended()
	{
		try
		{
			@SuppressWarnings("unused")
			SameLocals1StackItemFrameExtended frame = (SameLocals1StackItemFrameExtended)this;
		}
		catch(ClassCastException e)
		{
			return false;
		}
		
		return true;
	}
	
	public static void decoupleFromOffsets(DesCtx ctx, AbstractStackMapFrame frame, AttributeCode attributeCode)
	{
		AbstractInstruction offsetDeltaInstruction = null;
		offsetDeltaInstruction = StackMapFrame.getDeltaOffsetInstructionFromFrame(attributeCode.getCode(), frame);
		frame.setOffsetDeltaInstruction(offsetDeltaInstruction);
	}
	
	public static void coupleToOffsets(SerCtx ctx, AbstractStackMapFrame frame, AttributeCode attributeCode)
	{
		Disassembly diassembly = attributeCode.getCode();
		AbstractInstruction odiPrev = frame.getPreviousFrame() == null ? null : frame.getPreviousFrame().getOffsetDeltaInstruction();
		AbstractInstruction     odi = frame == null ? null : frame.getOffsetDeltaInstruction();
		
		int   offsetDeltaPrevious = odiPrev == null ? 0 : diassembly.getInstructionOffset(odiPrev);
		int           offsetDelta = odi == null ? 0 : diassembly.getInstructionOffset(odi);
		
		offsetDelta -= offsetDeltaPrevious;
		if(frame.isStack_map_frame_is_explicit() && frame.getPreviousFrame() != null)
		{
			offsetDelta--;
		}
		
		frame.setOffsetDelta(offsetDelta);
	}
}
