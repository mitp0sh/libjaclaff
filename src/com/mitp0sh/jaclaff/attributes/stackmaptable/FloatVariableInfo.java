package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;

public class FloatVariableInfo extends AbstractVariableInfo
{
	public FloatVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_FLOAT_TAG);
		setVariable_info_string_representation("Float_variable_info");
	}
	
	public static FloatVariableInfo deserialize(DataInputStream dis) throws IOException
    {
		return new FloatVariableInfo();
    }
	
	public static byte[] serialize(SerCtx ctx, FloatVariableInfo floatVariableInfo) throws IOException
	{	
		return new byte[]{};
	}
}

// The Float_variable_info type indicates that the location contains the 
// verification type float.

// Float_variable_info {
//     u1 tag = ITEM_Float; /* 2 */
// }
