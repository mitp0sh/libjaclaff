package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.AttributeBootstrapMethods;
import com.mitp0sh.jaclaff.attributes.bootstrapmethods.BootstrapMethodEntry;
import com.mitp0sh.jaclaff.attributes.bootstrapmethods.BootstrapMethods;
import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeInvokeDynamicDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class ConstantPoolTypeInvokeDynamic extends AbstractConstantPoolType
{
	private int bootstrapMethodAttrIndex = 0;
	private int         nameAndTypeIndex = 0;
	
	private BootstrapMethodEntry boostrapMethodAttr = null;
	private ConstantPoolTypeNameAndType nameAndType = null;

	public ConstantPoolTypeInvokeDynamic()
	{
		super.setConstant_pool_string_representation("CONSTANT_InvokeDynamic");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_INVOKE_DYNAMIC);
	}
	
	public int getBootstrapMethodAttrIndex()
	{
		return bootstrapMethodAttrIndex;
	}

	public void setBootstrapMethodAttrIndex(int bootstrapMethodAttrIndex) 
	{
		this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
	}

	public int getNameAndTypeIndex() 
	{
		return nameAndTypeIndex;
	}

	public void setNameAndTypeIndex(int nameAndTypeIndex) 
	{
		this.nameAndTypeIndex = nameAndTypeIndex;
	}
	
	public BootstrapMethodEntry getBoostrapMethodAttr() 
	{
		return boostrapMethodAttr;
	}

	public void setBoostrapMethodAttr(BootstrapMethodEntry boostrapMethodAttr) 
	{
		this.boostrapMethodAttr = boostrapMethodAttr;
	}
	
	public ConstantPoolTypeNameAndType getNameAndType() 
	{
		return nameAndType;
	}

	public void setNameAndType(ConstantPoolTypeNameAndType nameAndType) 
	{
		this.nameAndType = nameAndType;
	}
	
	public static ConstantPoolTypeInvokeDynamic deserialize(DataInputStream dis, ConstantPool constantPool) throws InvalidConstantPoolTypeInvokeDynamicDeserializationException, IOException
	{
		ConstantPoolTypeInvokeDynamic cptInvokeDynamic = new ConstantPoolTypeInvokeDynamic();
		
		cptInvokeDynamic.setBootstrapMethodAttrIndex(dis.readShort());
		cptInvokeDynamic.setNameAndTypeIndex(dis.readShort());
	
		return cptInvokeDynamic;
	}
	
	/* method is executed but NOT here. done at a later point at deserialization of bootstrap methods attribute */
	public static void decoupleConstantPoolTypeInvokeDynamicEntries(ConstantPoolTypeInvokeDynamic cptid, BootstrapMethodEntry entries[], ConstantPool cp)
	{
		cptid.setBoostrapMethodAttr(entries[cptid.bootstrapMethodAttrIndex]);
		cptid.setBootstrapMethodAttrIndex(0);
		
		cptid.setNameAndType((ConstantPoolTypeNameAndType)(ConstantPool.cpeByIndex(cp, cptid.getNameAndTypeIndex())));
		cptid.setNameAndTypeIndex(0);
	}
	
	public static void coupleConstantPoolTypeInvokeDynamicEntries(SerCtx ctx, ConstantPoolTypeInvokeDynamic cptid)
	{
		int nameAndTypeIndex = ConstantPool.indexByCPE(ctx.getConstantPool(), cptid.getNameAndType());
		cptid.setNameAndTypeIndex(nameAndTypeIndex);
		
		/* search bootstrap attribute */
		AttributeBootstrapMethods attribute = null;
		for(int i = 0; i < ctx.getVirtualClassFile().getAttributes().getAttributesCount(); i++)
		{
			try
			{
				attribute = (AttributeBootstrapMethods)ctx.getVirtualClassFile().getAttributes().getAttributes().get(i);
				break;
			}
			catch(ClassCastException e)
			{
				continue;
			}
		}
		
		if(attribute == null)
		{
			return;
		}
		
		BootstrapMethods bootstrapMethods = attribute.getBootstrapMethods();
		for(int i = 0; i < bootstrapMethods.getBootstrapMethodEntries().length; i++)
		{
			BootstrapMethodEntry current = bootstrapMethods.getBootstrapMethodEntries()[i];
			if(cptid.getBoostrapMethodAttr().equals(current))
			{
				cptid.setBootstrapMethodAttrIndex(i);
			}
		}	
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeInvokeDynamic elem) throws IOException
	{
		coupleConstantPoolTypeInvokeDynamicEntries(ctx, elem);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getBootstrapMethodAttrIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getNameAndTypeIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeInvokeDynamic clone()
	{
		/* create new empty instance */
		ConstantPoolTypeInvokeDynamic clone = (ConstantPoolTypeInvokeDynamic)super.clone();
		
		/* fill instance with original data */
		clone.setBoostrapMethodAttr(getBoostrapMethodAttr());
		clone.setNameAndType(getNameAndType());
		
		return clone;
	}
	
//	public boolean equals(ConstantPoolTypeInvokeDynamic obj)
//	{
//		try
//		{
//			ConstantPoolTypeInvokeDynamic cpt = (ConstantPoolTypeInvokeDynamic)obj;
//			boolean b0 = cpt.getBoostrapMethodAttr().equals(obj.getBoostrapMethodAttr());
//			boolean b1 = cpt.getNameAndType().equals(obj.getNameAndType());
//			return b0 && b1;
//		}
//		catch(NullPointerException e){}
//		catch(ClassCastException e){}
//		
//		return false;
//	}
}
