package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;

public class UninitializedThisVariableInfo extends AbstractVariableInfo
{
	public UninitializedThisVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_UNINITIALIZEDTHIS_TAG);
		setVariable_info_string_representation("UninitializedThis_variable_info");
	}
	
	public static UninitializedThisVariableInfo deserialize(DataInputStream dis) throws IOException
    {
		return new UninitializedThisVariableInfo();
    }
	
	public static byte[] serialize(SerCtx ctx, UninitializedThisVariableInfo uninitializedThisVariableInfo) throws IOException
	{	
		return new byte[]{};
	}
}

// The UninitializedThis_variable_info type indicates that the location contains 
// the verification type uninitializedThis.

// UninitializedThis_variable_info {
//     u1 tag = ITEM_UninitializedThis; /* 6 */
// }
