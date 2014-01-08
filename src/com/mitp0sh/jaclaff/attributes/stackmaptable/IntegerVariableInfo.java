package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;

public class IntegerVariableInfo extends AbstractVariableInfo
{
	public IntegerVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_INTEGER_TAG);
		setVariable_info_string_representation("Integer_variable_info");
	}
	
	public static IntegerVariableInfo deserialize(DataInputStream dis) throws IOException
    {
		return new IntegerVariableInfo();
    }
	
	public static byte[] serialize(SerCtx ctx, IntegerVariableInfo integerVariableInfo) throws IOException
	{	
		return new byte[]{};
	}
}

// The Integer_variable_info type indicates that the location contains the 
// verification type int.

// Integer_variable_info {
//     u1 tag = ITEM_Integer; /* 1 */
// }
