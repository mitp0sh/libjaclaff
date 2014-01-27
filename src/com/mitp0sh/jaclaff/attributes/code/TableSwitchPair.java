package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class TableSwitchPair
{
	private int index                             = 0;
	private int offset                            = 0;
	private AbstractInstruction offsetInstruction = null;
	
	public int getIndex() 
	{
		return index;
	}

	public void setIndex(int index) 
	{
		this.index = index;
	}

	public int getOffset() 
	{
		return offset;
	}
	
	public void setOffset(int offset) 
	{
		this.offset = offset;
	}
	
	public AbstractInstruction getOffsetInstruction() 
	{
		return offsetInstruction;
	}
	
	public void setOffsetInstruction(AbstractInstruction offsetInstruction) 
	{
		this.offsetInstruction = offsetInstruction;
	}
	
	public static TableSwitchPair deserialize(DesCtx ctx) throws IOException
	{
		TableSwitchPair pair = new TableSwitchPair();
		
		DataInputStream dis = ctx.getDataInputStream();
		
		pair.setOffset(dis.readInt());
		
		decoupleFromOffsets(ctx, pair);
		
		return pair;
	}
	
	public static void decoupleFromOffsets(DesCtx ctx, TableSwitchPair pair)
	{
		// TODO - NOT IMPLEMENTED YET !!!
	}
	
	public static void coupleWithOffsets(SerCtx ctx, TableSwitchPair pair)
	{
		// TODO - NOT IMPLEMENTED YET !!!
	}
	
	public static byte[] serialize(SerCtx ctx, TableSwitchPair pair) throws IOException
	{
		coupleWithOffsets(ctx, pair);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		baos.write(PNC.toByteArray(pair.getOffset(), Integer.class));
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "TABLESWITCHPAIR - NOT IMPLEMENTED YET !!!";
	}
	
	public static int getPhysicalSize() 
	{
		return 4;
	}
}
