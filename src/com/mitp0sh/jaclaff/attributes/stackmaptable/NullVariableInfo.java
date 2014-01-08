package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;

public class NullVariableInfo extends AbstractVariableInfo
{
	public NullVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_NULL_TAG);
		setVariable_info_string_representation("Null_variable_info");
	}
	
	public static NullVariableInfo deserialize(DataInputStream dis) throws IOException
    {
		return new NullVariableInfo();
    }
	
	public static byte[] serialize(SerCtx ctx, NullVariableInfo nullVariableInfo) throws IOException
	{	
		return new byte[]{};
	}
}

// The Null_variable_info type indicates that location contains the 
// verification type null.

// Null_variable_info {
//     u1 tag = ITEM_Null; /* 5 */
// }
