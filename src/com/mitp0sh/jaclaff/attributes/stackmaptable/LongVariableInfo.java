package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;

public class LongVariableInfo extends AbstractVariableInfo
{
	public LongVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_LONG_TAG);
		setVariable_info_string_representation("Long_variable_info");
	}
	
	public static LongVariableInfo deserialize(DataInputStream dis) throws IOException
    {
		return new LongVariableInfo();
    }
	
	public static byte[] serialize(SerCtx ctx, LongVariableInfo longVariableInfo) throws IOException
	{	
		return new byte[]{};
	}
}

// The Long_variable_info type indicates that the location contains the 
// verification type long.

// Long_variable_info {
//     u1 tag = ITEM_Long; /* 4 */
// }
    
// This structure gives the contents of two locations in the operand stack 
// or in the local variable array.

// If the location is a local variable, then:

// It must not be the local variable with the highest index.

// The next higher numbered local variable contains the verification type top.

// If the location is an operand stack entry, then:

// The current location must not be the topmost location of the operand stack.

// The next location closer to the top of the operand stack contains the 
// verification type top.
