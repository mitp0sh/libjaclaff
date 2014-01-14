package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.attributes.stackmaptable.AbstractStackMapFrame;
import com.mitp0sh.jaclaff.attributes.stackmaptable.StackMapFrame;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeStackMapTable extends AbstractAttribute
{
	private ArrayList<StackMapFrame> stackMapFrameList = new ArrayList<StackMapFrame>();

	public int getNumberOfEntries() 
	{
		return stackMapFrameList.size();
	}
	
	public ArrayList<StackMapFrame> getStackMapFrameList()
	{
		return stackMapFrameList;
	}
	
	public void setStackMapFrameList(ArrayList<StackMapFrame> stackMapFrameList)
	{
		this.stackMapFrameList = stackMapFrameList;
	}
	
	public static AttributeStackMapTable deserialize(DesCtx ctx, AttributeCode attributeCode) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeStackMapTable attribute = new AttributeStackMapTable();
		
		int                                     num = dis.readUnsignedShort();
		StackMapFrame           previousPreviousSMF = null;
		StackMapFrame                   previousSMF = null;
		AbstractStackMapFrame previousPreviousFrame = null;
		AbstractStackMapFrame         previousFrame = null;
		
		for(int i = 0; i < num; i++)
		{		
			if(previousSMF != null)
			{
				previousPreviousSMF   = previousSMF;
				previousPreviousFrame = previousFrame;
			}
			
			previousSMF   = StackMapFrame.deserialize(ctx, attributeCode, attribute.stackMapFrameList, previousFrame);
			previousFrame = previousSMF.getStackMapFrame();
			attribute.getStackMapFrameList().add(previousSMF);
		}
		
		decoupleFromDeltaOffset(attribute);
		
		return attribute;
    }
	
	public static void decoupleFromDeltaOffset(AttributeStackMapTable attribute)
	{
		ArrayList<StackMapFrame> stackMapFrameList = attribute.getStackMapFrameList();
		Iterator<StackMapFrame> iter= stackMapFrameList.iterator();
		while(iter.hasNext())
		{
			StackMapFrame stackMapFrame = iter.next();
			AbstractStackMapFrame current = stackMapFrame.getStackMapFrame();
			current.setOffsetDelta(0);
			current.setPreviousFrame(null);
			current.setStack_map_frame_bytecode_offset(0);
			current.setStack_map_frame_type((short)0);
		}
	}
	
	public static byte[] serialize(SerCtx ctx, AttributeStackMapTable attribute) throws IOException
	{
		ByteArrayOutputStream baos = new  ByteArrayOutputStream();
		
		int num = attribute.getStackMapFrameList().size();
		baos.write(PNC.toByteArray(num, Short.class));
		
		for(int i = 0; i < num; i++)
		{
			baos.write(StackMapFrame.serialize(ctx, attribute.getStackMapFrameList().get(i)));
		}
		
		return baos.toByteArray();
	}
}
