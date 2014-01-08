package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;

public class DoubleVariableInfo extends AbstractVariableInfo
{
	public DoubleVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_DOUBLE_TAG);
		setVariable_info_string_representation("Double_variable_info");
	}
	
	public static DoubleVariableInfo deserialize(DataInputStream dis) throws IOException
    {
		return new DoubleVariableInfo();
    }
	
	public static byte[] serialize(SerCtx ctx, DoubleVariableInfo doubleVariableInfo) throws IOException
	{	
		return new byte[]{};
	}
}

// The Double_variable_info type indicates that the location contains the 
// verification type double.

// Double_variable_info {
//     u1 tag = ITEM_Double; /* 3 */
// }
    
// This structure gives the contents of two locations in the operand stack or 
// in the local variable array.

// If the location is a local variable, then:

// It must not be the local variable with the highest index.

// The next higher numbered local variable contains the verification type top.

// If the location is an operand stack entry, then:

// The current location must not be the topmost location of the operand stack.

// The next location closer to the top of the operand stack contains the 
// verification type top.
