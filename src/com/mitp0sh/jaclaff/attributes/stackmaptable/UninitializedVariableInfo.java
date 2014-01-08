package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class UninitializedVariableInfo extends AbstractVariableInfo
{
	private short offset = 0;

	public UninitializedVariableInfo()
	{
		setVariable_info_tag(AbstractVariableInfo.VERIFICATION_TYPE_INFO_UNINITIALIZED);
		setVariable_info_string_representation("Uninitialized_variable_info");
	}
	
	public short getOffset() 
	{
		return offset;
	}

	public void setOffset(short offset) 
	{
		this.offset = offset;
	}
	
	public static UninitializedVariableInfo deserialize(DataInputStream dis) throws IOException
    {
		UninitializedVariableInfo uninitializedVariableInfo = new UninitializedVariableInfo();
		
		uninitializedVariableInfo.setOffset((short)dis.readUnsignedShort());
		
		return uninitializedVariableInfo;
    }
	
	public static byte[] serialize(SerCtx ctx, UninitializedVariableInfo uninitializedVariableInfo) throws IOException
	{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(uninitializedVariableInfo.getOffset(), Short.class));
		
		return baos.toByteArray();
	}
}

// The Uninitialized_variable_info type indicates that the location contains 
// the verification type uninitialized(offset). The offset item indicates the 
// offset, in the code array of the Code attribute (§4.7.3) that contains this 
// StackMapTable attribute, of the new instruction (§new) that created the 
// object being stored in the location.

// Uninitialized_variable_info {
//     u1 tag = ITEM_Uninitialized /* 8 */
//     u2 offset;
// }