package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class AppendFrame extends AbstractStackMapFrame
{
	private short                      offsetDelta = 0;
	private ArrayList<VerificationTypeInfo> locals = new ArrayList<VerificationTypeInfo>();

	public AppendFrame()
	{
		setStack_map_frame_string_representation("append_frame");
	}
	
	public short getOffsetDelta() 
	{
		return offsetDelta;
	}

	public void setOffsetDelta(short offsetDelta) 
	{
		this.offsetDelta = offsetDelta;
	}

	public ArrayList<VerificationTypeInfo> getLocals() 
	{
		return locals;
	}

	public void setLocals(ArrayList<VerificationTypeInfo> locals) 
	{
		this.locals = locals;
	}
	
	public static AppendFrame deserialize(DesCtx ctx, byte frameType) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		ConstantPool constantPool = ctx.getConstantPool();
		
		/* create new append frame instance */
		AppendFrame appendFrame = new AppendFrame();		
		
		/* read offset delta */
		short offsetDelta = dis.readShort();
		appendFrame.setOffsetDelta(offsetDelta);
		byte k = (byte)(frameType - 251);
		
		/* process all verification type infos */
		for(int i = 0; i < k; i++)
		{
			appendFrame.getLocals().add(VerificationTypeInfo.deserialize(dis, constantPool));
		}
		
		return appendFrame;
    }
	
	public static byte[] serialize(SerCtx ctx, AppendFrame appendFrame) throws IOException
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

// The frame type append_frame is represented by tags in the range [252-254]. 
// If the frame_type is append_frame, it means that the operand stack is empty 
// and the current locals are the same as the locals in the previous frame, 
// except that k additional locals are defined. The value of k is given by 
// the formula frame_type - 251.

// append_frame {
//     u1 frame_type = APPEND; /* 252-254 */
//     u2 offset_delta;
//     verification_type_info locals[frame_type - 251];
// }
    
// The 0th entry in locals represents the type of the first additional local 
// variable. If locals[M] represents local variable N, then locals[M+1] 
// represents local variable N+1 if locals[M] is one of:

// Top_variable_info

// Integer_variable_info

// Float_variable_info

// Null_variable_info

// UninitializedThis_variable_info

// Object_variable_info

// Uninitialized_variable_info

// Otherwise locals[M+1] represents local variable N+2.

// It is an error if, for any index i, locals[i] represents a local variable 
// whose index is greater than the maximum number of local variables for 
// the method.
