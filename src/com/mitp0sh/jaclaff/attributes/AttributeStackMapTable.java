package com.mitp0sh.jaclaff.attributes;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.mitp0sh.jaclaff.attributes.stackmaptable.StackMapFrame;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

/* complete */
public class AttributeStackMapTable extends AbstractAttribute
{
	private ArrayList<StackMapFrame> stackMapFrameList = new ArrayList<StackMapFrame>();

	public int getNumberOfEntries() 
	{
		return stackMapFrameList.size();
	}
	
	public ArrayList<StackMapFrame> getStackMapFrameList()
	{
		return stackMapFrameList;
	}
	
	public void setStackMapFrameList(ArrayList<StackMapFrame> stackMapFrameList)
	{
		this.stackMapFrameList = stackMapFrameList;
	}
	
	public static AttributeStackMapTable deserialize(DesCtx ctx) throws IOException
    {
		DataInputStream dis = ctx.getDataInputStream();
		
		AttributeStackMapTable attribute = new AttributeStackMapTable();
		
		int num = dis.readUnsignedShort();
		for(int i = 0; i < num; i++)
		{
			attribute.getStackMapFrameList().add(StackMapFrame.deserialize(ctx));
		}
		
		return attribute;
    }
	
	public static byte[] serialize(SerCtx ctx, AttributeStackMapTable attribute) throws IOException
	{
		ByteArrayOutputStream baos = new  ByteArrayOutputStream();
		
		int num = attribute.getStackMapFrameList().size();
		baos.write(PNC.toByteArray(num, Short.class));
		
		for(int i = 0; i < num; i++)
		{
			baos.write(StackMapFrame.serialize(ctx, attribute.getStackMapFrameList().get(i)));
		}
		
		return baos.toByteArray();
	}
}

// The StackMapTable attribute is a variable-length attribute in the attributes
// table of a Code (§4.7.3) attribute. This attribute is used during the 
// process of verification by type checking (§4.10.1). A method's Code
// attribute may have at most one StackMapTable attribute.

// A StackMapTable attribute consists of zero or more stack map frames. Each 
// stack map frame specifies (either explicitly or implicitly) a bytecode 
// offset, the verification types (§4.10.1.2) for the local variables, and the
// verification types for the operand stack.

// The type checker deals with and manipulates the expected types of a method's 
// local variables and operand stack. Throughout this section, a location 
// refers to either a single local variable or to a single operand stack entry.

// We will use the terms stack map frame and type state interchangeably to 
// describe a mapping from locations in the operand stack and local variables 
// of a method to verification types. We will usually use the term stack map 
// frame when such a mapping is provided in the class file, and the term type 
// state when the mapping is used by the type checker.

// In a class file whose version number is greater than or equal to 50.0, if 
// a method's Code attribute does not have a StackMapTable attribute, it has 
// an implicit stack map attribute. This implicit stack map attribute is 
// equivalent to a StackMapTable attribute with number_of_entries equal to 
// zero.

// The StackMapTable attribute has the following format:

// StackMapTable_attribute {
//     u2              attribute_name_index;
//     u4              attribute_length;
//     u2              number_of_entries;
//     stack_map_frame entries[number_of_entries];
// }

// The items of the StackMapTable_attribute structure are as follows:

// attribute_name_index
// The value of the attribute_name_index item must be a valid index into the 
// constant_pool table. The constant_pool entry at that index must be a 
// CONSTANT_Utf8_info (§4.4.7) structure representing the string 
// "StackMapTable".

// attribute_length
// The value of the attribute_length item indicates the length of the attribute,
// excluding the initial six bytes.

// number_of_entries
// The value of the number_of_entries item gives the number of stack_map_frame 
// entries in the entries table.

// entries
// The entries array gives the method's stack_map_frame structures.
