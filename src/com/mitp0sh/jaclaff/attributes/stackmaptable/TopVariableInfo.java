package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;

public class TopVariableInfo extends AbstractVariableInfo
{
	public TopVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_TOP_TAG);
		setVariable_info_string_representation("Top_variable_info");
	}
	
	public static TopVariableInfo deserialize(DataInputStream dis) throws IOException
    {
		return new TopVariableInfo();
    }
	
	public static byte[] serialize(SerCtx ctx, TopVariableInfo topVariableInfo) throws IOException
	{	
		return new byte[]{};
	}
}

// The Top_variable_info type indicates that the local variable has the 
// verification type top.

// Top_variable_info {
//     u1 tag = ITEM_Top; /* 0 */
// }
