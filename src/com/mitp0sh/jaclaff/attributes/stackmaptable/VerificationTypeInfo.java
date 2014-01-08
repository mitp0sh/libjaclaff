package com.mitp0sh.jaclaff.attributes.stackmaptable;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class VerificationTypeInfo
{	
	public AbstractVariableInfo variableInfo = null;

	public AbstractVariableInfo getVariableInfo() 
	{
		return variableInfo;
	}

	public void setVariableInfo(AbstractVariableInfo variableInfo) 
	{
		this.variableInfo = variableInfo;
	}
	
	public static VerificationTypeInfo deserialize(DataInputStream dis, ConstantPool constantPool) throws IOException
    {
		VerificationTypeInfo verificationTypeInfo = new VerificationTypeInfo();
		AbstractVariableInfo variableInfo = null;
		
	    byte tag = (byte)dis.readUnsignedByte();
	    switch(tag)
	    {
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_TOP_TAG:
	        {
	        	variableInfo = TopVariableInfo.deserialize(dis);
	        	break;
	        }
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_INTEGER_TAG:
	        {
	        	variableInfo = IntegerVariableInfo.deserialize(dis);
	        	break;
	        }
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_FLOAT_TAG:
	        {
	        	variableInfo = FloatVariableInfo.deserialize(dis);
	        	break;
	        }
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_LONG_TAG:
	        {
	        	variableInfo = LongVariableInfo.deserialize(dis);
	        	break;
	        }
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_DOUBLE_TAG:
	        {
	        	variableInfo = DoubleVariableInfo.deserialize(dis);
	        	break;
	        }
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_NULL_TAG:
	        {
	        	variableInfo = NullVariableInfo.deserialize(dis);
	        	break;
	        }
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_UNINITIALIZEDTHIS_TAG:
	        {
	        	variableInfo = UninitializedThisVariableInfo.deserialize(dis);
	        	break;
	        }
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_OBJECT_TAG:
	        {
	        	variableInfo = ObjectVariableInfo.deserialize(dis, constantPool);
	        	break;
	        }
	        case AbstractVariableInfo.VERIFICATION_TYPE_INFO_UNINITIALIZED:
	        {
	        	variableInfo = UninitializedVariableInfo.deserialize(dis);
	        	break;
	        }
	    	default:
	    	{
	    		return null;
	    	}
	    }
	    
	    /* set variable info */
	    verificationTypeInfo.setVariableInfo(variableInfo);
	    
	    return verificationTypeInfo;
    }
	
	public static byte[] serialize(SerCtx ctx, VerificationTypeInfo verificationTypeInfo) throws IOException
	{	
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String viClassStr = verificationTypeInfo.getVariableInfo().getClass().toString();		
		
		if(viClassStr.equals(TopVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_TOP_TAG});
			baos.write(TopVariableInfo.serialize(ctx, (TopVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		if(viClassStr.equals(IntegerVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_INTEGER_TAG});
			baos.write(IntegerVariableInfo.serialize(ctx, (IntegerVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		if(viClassStr.equals(FloatVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_FLOAT_TAG});
			baos.write(FloatVariableInfo.serialize(ctx, (FloatVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		if(viClassStr.equals(LongVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_LONG_TAG});
			baos.write(LongVariableInfo.serialize(ctx, (LongVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		if(viClassStr.equals(DoubleVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_DOUBLE_TAG});
			baos.write(DoubleVariableInfo.serialize(ctx, (DoubleVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		if(viClassStr.equals(NullVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_NULL_TAG});
			baos.write(NullVariableInfo.serialize(ctx, (NullVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		if(viClassStr.equals(UninitializedThisVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_UNINITIALIZEDTHIS_TAG});
			baos.write(UninitializedThisVariableInfo.serialize(ctx, (UninitializedThisVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		if(viClassStr.equals(ObjectVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_OBJECT_TAG});
			baos.write(ObjectVariableInfo.serialize(ctx, (ObjectVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		if(viClassStr.equals(UninitializedVariableInfo.class.toString()))
		{
			baos.write(new byte[]{AbstractVariableInfo.VERIFICATION_TYPE_INFO_UNINITIALIZED});
			baos.write(UninitializedVariableInfo.serialize(ctx, (UninitializedVariableInfo)verificationTypeInfo.variableInfo));
		}
		else
		{
			return null;
		}
		
		return baos.toByteArray();
	}
}
