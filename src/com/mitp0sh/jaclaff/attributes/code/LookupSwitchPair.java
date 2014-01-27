package com.mitp0sh.jaclaff.attributes.code;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class LookupSwitchPair
{
	private int  match                            = 0;
	private int offset                            = 0;
	private AbstractInstruction offsetInstruction = null;
	
	public int getMatch()
	{
		return match;
	}
	
	public void setMatch(int match)
	{
		this.match = match;
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
	
	public static LookupSwitchPair deserialize(DesCtx ctx) throws IOException
	{
		LookupSwitchPair pair = new LookupSwitchPair();
		
		DataInputStream dis = ctx.getDataInputStream();
		
		pair.setMatch(dis.readInt());
		pair.setOffset(dis.readInt());
		
		decoupleFromOffsets(ctx, pair);
		
		return pair;
	}
	
	public static void decoupleFromOffsets(DesCtx ctx, LookupSwitchPair pair)
	{
		// TODO - NOT IMPLEMENTED YET !!!
	}
	
	public static void coupleWithOffsets(SerCtx ctx, LookupSwitchPair pair)
	{
		// TODO - NOT IMPLEMENTED YET !!!
	}
	
	public static byte[] serialize(SerCtx ctx, LookupSwitchPair pair) throws IOException
	{
		coupleWithOffsets(ctx, pair);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
	
		baos.write(PNC.toByteArray(pair.getMatch(),  Integer.class));
		baos.write(PNC.toByteArray(pair.getOffset(), Integer.class));
		
		return baos.toByteArray();
	}
	
	@Override
	public String toString()
	{
		return super.toString() + "LOOKUPSWITCHPAIR - NOT IMPLEMENTED YET !!!";
	}
	
	public static int getPhysicalSize() 
	{
		return 8;
	}
}
