package com.mitp0sh.jaclaff.attributes.annotation;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.serialization.SerCtx;

/* complete */
public class ElementValue
{	
	private int                tag = 0; 
	private AbstractValue value = null;
	
	public int getTag()
	{
		return tag;
	}
	
	public void setTag(int tag) 
	{
		this.tag = tag;
	}
	
	public AbstractValue getValue() 
	{
		return value;
	}
	
	public void setValue(AbstractValue value) 
	{
		this.value = value;
	}
	
	public static ElementValue deserialize(DesCtx ctx) throws IOException
	{
		DataInputStream dis = ctx.getDataInputStream();
		
		ElementValue elementValue = new ElementValue();
		
		/* read tag */
		int tag = dis.readUnsignedByte();
		elementValue.setTag(tag);
		
		switch(tag)
		{
		    case ElementValueType.PRIMITIVE_B:
		    case ElementValueType.PRIMITIVE_C:
		    case ElementValueType.PRIMITIVE_D:
		    case ElementValueType.PRIMITIVE_F:
		    case ElementValueType.PRIMITIVE_I:
		    case ElementValueType.PRIMITIVE_J:
		    case ElementValueType.PRIMITIVE_S:
		    case ElementValueType.PRIMITIVE_Z:
		    case ElementValueType.S:
		    {
		    	elementValue.setValue(ConstValueIndex.deserialize(ctx));
		    	break;
		    }
		    case ElementValueType.E:
		    {
		    	elementValue.setValue(EnumConstValue.deserialize(ctx));
		    	break;
		    }
		    case ElementValueType.C:
		    {
		    	elementValue.setValue(ClassInfoIndex.deserialize(ctx));
		    	break;
		    }
		    case ElementValueType.AT:
		    {
		    	elementValue.setValue(Annotation.deserialize(ctx));
		    	break;
		    }
		    case ElementValueType.ARRAY:
		    {
		    	elementValue.setValue(ArrayValue.deserialize(ctx));
		    	break;
		    }
		    default:
		    {
		    	System.err.println("error - unable to deserialize element value !!!");
		    	System.exit(-1);
		    }
		}
		
		return elementValue;
	}
	
	public static byte[] serialize(SerCtx ctx, ElementValue elementValue) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int tag = elementValue.getTag();
		
		baos.write(new byte[]{(byte)tag});
		
		switch(tag)
		{
			case ElementValueType.PRIMITIVE_B:
		    case ElementValueType.PRIMITIVE_C:
		    case ElementValueType.PRIMITIVE_D:
		    case ElementValueType.PRIMITIVE_F:
		    case ElementValueType.PRIMITIVE_I:
		    case ElementValueType.PRIMITIVE_J:
		    case ElementValueType.PRIMITIVE_S:
		    case ElementValueType.PRIMITIVE_Z:
		    case ElementValueType.S:
		    {
		    	baos.write(ConstValueIndex.serialize(ctx, (ConstValueIndex)elementValue.getValue()));
		    	break;
		    }
		    case ElementValueType.E:
		    {
		    	baos.write(EnumConstValue.serialize(ctx, (EnumConstValue)elementValue.getValue()));
		    	break;
		    }
		    case ElementValueType.C:
		    {
		    	baos.write(ClassInfoIndex.serialize(ctx, (ClassInfoIndex)elementValue.getValue()));
		    	break;
		    }
		    case ElementValueType.AT:
		    {
		    	baos.write(Annotation.serialize(ctx, (Annotation)elementValue.getValue()));
		    	break;
		    }
		    case ElementValueType.ARRAY:
		    {
		    	baos.write(ArrayValue.serialize(ctx, (ArrayValue)elementValue.getValue()));
		    	break;
		    }
		    default:
		    {
		    	System.err.println("error - unable to serialize element value !!!");
		    	System.exit(-1);
		    }
		}
		
		return baos.toByteArray();
	}
}
