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
	
	public static byte[] serialize(SerCtx ctx, FullFrame fullFrame) throws IOException
	{	
		coupleToOffsets(ctx, fullFrame, null);
		
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

// The frame type full_frame is represented by the tag value 255.

// full_frame {
//     u1 frame_type = FULL_FRAME; /* 255 */
//     u2 offset_delta;
//     u2 number_of_locals;
//     verification_type_info locals[number_of_locals];
//     u2 number_of_stack_items;
//     verification_type_info stack[number_of_stack_items];
// }
    
// The 0th entry in locals represents the type of local variable 0. If 
// locals[M] represents local variable N, then locals[M+1] represents local 
// variable N+1 if locals[M] is one of:

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

// The 0th entry in stack represents the type of the bottom of the stack, 
// and subsequent entries represent types of stack elements closer to the 
// top of the operand stack. We shall refer to the bottom element of the 
// stack as stack element 0, and to subsequent elements as stack element 
// 1, 2 etc. If stack[M] represents stack element N, then stack[M+1] 
// represents stack element N+1 if stack[M] is one of:

// Top_variable_info

// Integer_variable_info

// Float_variable_info

// Null_variable_info

// UninitializedThis_variable_info

// Object_variable_info

// Uninitialized_variable_info

// Otherwise, stack[M+1] represents stack element N+2.

// It is an error if, for any index i, stack[i] represents a stack entry whose 
// index is greater than the maximum operand stack size for the method.

// The verification_type_info structure consists of a one-byte tag followed by 
// zero or more bytes, giving more information about the tag. Each 
// verification_type_info structure specifies the verification type of one or 
// two locations.

// union verification_type_info {
//     Top_variable_info;
//     Integer_variable_info;
//     Float_variable_info;
//     Long_variable_info;
//     Double_variable_info;
//     Null_variable_info;
//     UninitializedThis_variable_info;
//     Object_variable_info;
//     Uninitialized_variable_info;
// }
