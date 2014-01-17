package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class ConstantPool
{
	private static boolean SHOW_WARNINGS = false;
	
	private ArrayList<AbstractConstantPoolType> constantPool = new ArrayList<AbstractConstantPoolType>();
	
	public int getConstantPoolCount() 
	{
		return constantPool.size();
	}	
	
	public ArrayList<AbstractConstantPoolType> getConstantPool()
	{
		return this.constantPool;
	}
	
	public String getConstantTypeUtf8Bytes(int index)
	{	
		if(this.getConstantPool().size() < (index - 1) ||
		   this.getConstantPool().get(index - 1).getClass().getCanonicalName().indexOf("ConstantPoolTypeUtf8") == -1)
		{
			return "";
		}
		return ((ConstantPoolTypeUtf8)(this.getConstantPool().get(index - 1))).getBytes();
	}
	
	public static ConstantPool deserialize(DesCtx ctx, int constantPoolCount) throws InvalidConstantPoolDeserializationException, IOException
    {
		DataInputStream       dis = ctx.getDataInputStream();
		ConstantPool constantPool = new ConstantPool();
		ctx.setConstantPool(constantPool);
		
		int num = constantPoolCount - 1; // constant pool count, when read from disk, is always +1 to effective count
		for(int i = 0; i < num; i++)
		{			
			AbstractConstantPoolType current = null;
			byte constantPoolTag = (byte)dis.readUnsignedByte();
			
			switch(constantPoolTag)
			{
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS:
				{				
					current = ConstantPoolTypeClass.deserialize(dis);	
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF:
				{					 				
					current = ConstantPoolTypeFieldref.deserialize(dis);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF:
				{	 				
					current = ConstantPoolTypeMethodref.deserialize(dis);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF:
				{	 				
					current = ConstantPoolTypeInterfaceMethodref.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING:
				{	 			
					current = ConstantPoolTypeString.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTEGER:
				{					 					
					current = ConstantPoolTypeInteger.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_FLOAT:
				{	 					
					current = ConstantPoolTypeFloat.deserialize(dis);				
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_LONG:
				{	 					
					current = ConstantPoolTypeLong.deserialize(dis);	
					constantPool.getConstantPool().add(current);
					current = new ConstantPoolTypeLong();
					i++;
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_DOUBLE:
				{	 				
					current = ConstantPoolTypeDouble.deserialize(dis);	
					constantPool.getConstantPool().add(current);
					current = new ConstantPoolTypeDouble();
					i++;
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE:
				{	 				
					current = ConstantPoolTypeNameAndType.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_UTF8:
				{	
					current = ConstantPoolTypeUtf8.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INVOKE_DYNAMIC:
				{
					current = ConstantPoolTypeInvokeDynamic.deserialize(dis, constantPool);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODHANDLE:
				{
					current = ConstantPoolTypeMethodHandle.deserialize(dis, constantPool);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODTYPE:
				{
					current = ConstantPoolTypeMethodType.deserialize(dis, constantPool);
					break;
				}
				default:
				{
					System.err.println("error - unable to deserialize constant pool type !!!");
					System.exit(-1);
				}
			}
			
			/* add to constant pool list */
			constantPool.getConstantPool().add(current);
		}
		
		/* decouple indexes */
		decoupleFromIndices(ctx);
		
		return constantPool;
    }
	
	private static void decoupleFromIndices(DesCtx ctx)
	{
		ConstantPool cp = ctx.getConstantPool();
		Iterator<AbstractConstantPoolType> iter = cp.getConstantPool().iterator();
		while(iter.hasNext())
		{
			AbstractConstantPoolType acpt = iter.next();
			int tag = acpt.getConstant_pool_tag();
			switch(tag)
			{
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS:
				{				
					ConstantPoolTypeClass cpt = (ConstantPoolTypeClass)acpt;
					ConstantPoolTypeClass.decoupleFromIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF:
				{
					ConstantPoolTypeFieldref cpt = (ConstantPoolTypeFieldref)acpt;
					ConstantPoolTypeFieldref.decoupleFromIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF:
				{
					ConstantPoolTypeMethodref cpt = (ConstantPoolTypeMethodref)acpt;
					ConstantPoolTypeMethodref.decoupleFromIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF:
				{
					ConstantPoolTypeInterfaceMethodref cpt = (ConstantPoolTypeInterfaceMethodref)acpt;
					ConstantPoolTypeInterfaceMethodref.decoupleFromIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE:
				{
					ConstantPoolTypeNameAndType cpt = (ConstantPoolTypeNameAndType)acpt;
					ConstantPoolTypeNameAndType.decoupleFromIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING:
				{
					ConstantPoolTypeString cpt = (ConstantPoolTypeString)acpt;
					ConstantPoolTypeString.decoupleFromIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INVOKE_DYNAMIC:
				{
					// decoupling done at later stage when attribute bootstrap methods is deserialized !!!!
					break;
				}
			}
		}
	}
	
	private static void coupleConstantPoolEntriesToIndices(SerCtx ctx, ConstantPool cp)
	{
		Iterator<AbstractConstantPoolType> iter = cp.getConstantPool().iterator();
		while(iter.hasNext())
		{
			AbstractConstantPoolType acpt = iter.next();
			int tag = acpt.getConstant_pool_tag();
			switch(tag)
			{
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS:
				{	
					ConstantPoolTypeClass cpt = (ConstantPoolTypeClass)acpt;
					ConstantPoolTypeClass.coupleToIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF:
				{
					ConstantPoolTypeFieldref cpt = (ConstantPoolTypeFieldref)acpt;
					ConstantPoolTypeFieldref.coupleToIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF:
				{
					ConstantPoolTypeMethodref cpt = (ConstantPoolTypeMethodref)acpt;
					ConstantPoolTypeMethodref.coupleToIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF:
				{
					ConstantPoolTypeInterfaceMethodref cpt = (ConstantPoolTypeInterfaceMethodref)acpt;
					ConstantPoolTypeInterfaceMethodref.coupleToIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE:
				{
					ConstantPoolTypeNameAndType cpt = (ConstantPoolTypeNameAndType)acpt;
					ConstantPoolTypeNameAndType.coupleToIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING:
				{
					ConstantPoolTypeString cpt = (ConstantPoolTypeString)acpt;
					ConstantPoolTypeString.coupleToIndices(ctx, cpt);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INVOKE_DYNAMIC:
				{
					ConstantPoolTypeInvokeDynamic cpt = (ConstantPoolTypeInvokeDynamic)acpt;
					ConstantPoolTypeInvokeDynamic.coupleConstantPoolTypeInvokeDynamicEntries(ctx, cpt);
					break;
				}
			}
		}
	}
	
	public static AbstractConstantPoolType getConstantPoolTypeByIndex(ConstantPool constantPool, int index)
	{
		return constantPool.getConstantPool().get(index - 1);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPool cp) throws IOException
	{		
		/* couple to indices */
		coupleConstantPoolEntriesToIndices(ctx, cp);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		Iterator<AbstractConstantPoolType> iter = cp.getConstantPool().iterator();
		while(iter.hasNext())
		{
			AbstractConstantPoolType acpt = iter.next();
			int tag = acpt.getConstant_pool_tag();
			
			switch(tag)
			{
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS:
			    {
			    	baos.write(ConstantPoolTypeClass.serialize(ctx, (ConstantPoolTypeClass)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF:
			    {
			    	baos.write(ConstantPoolTypeFieldref.serialize(ctx, (ConstantPoolTypeFieldref)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF:
			    {
			    	baos.write(ConstantPoolTypeMethodref.serialize(ctx, (ConstantPoolTypeMethodref)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF:
			    {
			    	baos.write(ConstantPoolTypeInterfaceMethodref.serialize(ctx, (ConstantPoolTypeInterfaceMethodref)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING:
			    {
			    	baos.write(ConstantPoolTypeString.serialize(ctx, (ConstantPoolTypeString)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTEGER:
			    {
			    	baos.write(ConstantPoolTypeInteger.serialize(ctx, (ConstantPoolTypeInteger)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_FLOAT:
			    {
			    	baos.write(ConstantPoolTypeFloat.serialize(ctx, (ConstantPoolTypeFloat)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_LONG:
			    {
			    	baos.write(ConstantPoolTypeLong.serialize(ctx, (ConstantPoolTypeLong)acpt));
			    	iter.next();
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_DOUBLE:
			    {
			    	baos.write(ConstantPoolTypeDouble.serialize(ctx, (ConstantPoolTypeDouble)acpt));
			    	iter.next();
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE:
			    {
			    	baos.write(ConstantPoolTypeNameAndType.serialize(ctx, (ConstantPoolTypeNameAndType)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_UTF8:
			    {			    	
			    	baos.write(ConstantPoolTypeUtf8.serialize(ctx, (ConstantPoolTypeUtf8)acpt));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_INVOKE_DYNAMIC:
			    {
			    	baos.write(ConstantPoolTypeInvokeDynamic.serialize(ctx, (ConstantPoolTypeInvokeDynamic)acpt));
			    	break;
			    }
			    default:
				{
					System.err.println("error - unable to serialize constant pool type !!!");
					System.exit(-1);
				}
			}
		}
		
		return baos.toByteArray();
	}
	
	public static int getIndexFromConstantPoolEntry(ConstantPool constantPool, AbstractConstantPoolType cpt)
	{
		if(cpt == null)
		{
			if(SHOW_WARNINGS)
			{
				System.err.println("unable to retrieve index for NULL constant pool entry");	
			}
			
			return 0;
		}
		
		for(int i = 0; i < constantPool.getConstantPoolCount(); i++)
		{	
			AbstractConstantPoolType curr = constantPool.getConstantPool().get(i);		
			if(cpt.equals(curr))
			{
				return i + 1;
			}
		}
	
		if(SHOW_WARNINGS)
		{
			System.err.println("unable to retrieve index for constant pool entry, cpt == " + cpt);
		}
		
		return 0;
	}
}
