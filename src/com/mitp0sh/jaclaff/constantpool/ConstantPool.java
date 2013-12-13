package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

public class ConstantPool
{
	private AbstractConstantPoolType[] constantPool;

	public ConstantPool(short constantPoolCount)
	{
		this.setConstantPool(new AbstractConstantPoolType[constantPoolCount]);
	}
	
	public short getConstantPoolCount() 
	{
		return (short)(this.constantPool.length - 1);
	}	
	
	public AbstractConstantPoolType[] getConstantPool()
	{
		return this.constantPool;
	}

	public void setConstantPool(AbstractConstantPoolType[] constantPool)
	{
		this.constantPool = constantPool;
	}
	
	public String getConstantTypeUtf8Bytes(short index)
	{	
		if(this.getConstantPool().length < index ||
		   this.getConstantPool()[index].getClass().getCanonicalName().indexOf("ConstantPoolTypeUtf8") == -1)
		{
			return "";
		}
		return ((ConstantPoolTypeUtf8)(this.getConstantPool()[index])).getBytes();
	}
	
	public static ConstantPool deserialize(DataInputStream dis, short constantPoolCount) throws IOException
    {
		ConstantPool constantPool = new ConstantPool(constantPoolCount);
		
		for(int i = 1; i <= constantPool.getConstantPoolCount(); i++)
		{
			byte constantPoolTag = (byte)dis.readUnsignedByte();
			
			switch(constantPoolTag)
			{
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS:
				{					 					
					constantPool.getConstantPool()[i] = ConstantPoolTypeClass.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF:
				{					 				
					constantPool.getConstantPool()[i] = ConstantPoolTypeFieldref.deserialize(dis);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF:
				{	 				
					constantPool.getConstantPool()[i] = ConstantPoolTypeMethodref.deserialize(dis);
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF:
				{	 				
					constantPool.getConstantPool()[i] = ConstantPoolTypeInterfaceMethodref.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING:
				{	 			
					constantPool.getConstantPool()[i] = ConstantPoolTypeString.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTEGER:
				{					 					
					constantPool.getConstantPool()[i] = ConstantPoolTypeInteger.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_FLOAT:
				{	 					
					constantPool.getConstantPool()[i] = ConstantPoolTypeFloat.deserialize(dis);				
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_LONG:
				{	 					
					constantPool.getConstantPool()[i] = ConstantPoolTypeLong.deserialize(dis);					
					i++;			
					constantPool.getConstantPool()[i] = new ConstantPoolTypeLong();
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_DOUBLE:
				{	 				
					constantPool.getConstantPool()[i] = ConstantPoolTypeDouble.deserialize(dis);
					i++;		
					constantPool.getConstantPool()[i] = new ConstantPoolTypeDouble();		
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE:
				{	 				
					constantPool.getConstantPool()[i] = ConstantPoolTypeNameAndType.deserialize(dis);					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_UTF8:
				{	
					constantPool.getConstantPool()[i] = ConstantPoolTypeUtf8.deserialize(dis);					
					break;
				}
				default:
				{
					System.out.println("FATAL DESERIALIZATION ERROR: Unknown constant pool tag!");
					break;
				}
			}
		}
		
		/* decouple indexes */
		decoupleConstantPoolEntriesFromIndices(constantPool);
		
		return constantPool;
    }
	
	private static void decoupleConstantPoolEntriesFromIndices(ConstantPool constantPool)
	{
		for(int i = 1;i < constantPool.getConstantPoolCount(); i++)
		{	
			AbstractConstantPoolType abstractCPT = constantPool.getConstantPool()[i];
			
			switch(abstractCPT.getConstant_pool_tag())
			{
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS:
				{	
					ConstantPoolTypeClass entry = (ConstantPoolTypeClass)abstractCPT;
					entry.setCptName((ConstantPoolTypeUtf8)getConstantPoolTypeByIndex(constantPool, entry.getNameIndex()));
					entry.setNameIndex((short)0);
					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF:
				{
					ConstantPoolTypeFieldref entry = (ConstantPoolTypeFieldref)abstractCPT;
					entry.setCptClass((ConstantPoolTypeClass)getConstantPoolTypeByIndex(constantPool, entry.getClassIndex()));
					entry.setClassIndex((short)0);
					entry.setCptNameAndType((ConstantPoolTypeNameAndType)getConstantPoolTypeByIndex(constantPool, entry.getNameAndTypeIndex()));
					entry.setNameAndTypeIndex((short)0);
					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF:
				{
					ConstantPoolTypeMethodref entry = (ConstantPoolTypeMethodref)abstractCPT;
					entry.setCptClass((ConstantPoolTypeClass)getConstantPoolTypeByIndex(constantPool, entry.getClassIndex()));
					entry.setClassIndex((short)0);
					entry.setCptNameAndType((ConstantPoolTypeNameAndType)getConstantPoolTypeByIndex(constantPool, entry.getNameAndTypeIndex()));
					entry.setNameAndTypeIndex((short)0);
					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF:
				{
					ConstantPoolTypeInterfaceMethodref entry = (ConstantPoolTypeInterfaceMethodref)abstractCPT;
					entry.setCptClass((ConstantPoolTypeClass)getConstantPoolTypeByIndex(constantPool, entry.getClassIndex()));
					entry.setClassIndex((short)0);
					entry.setCptNameAndType((ConstantPoolTypeNameAndType)getConstantPoolTypeByIndex(constantPool, entry.getNameAndTypeIndex()));
					entry.setNameAndTypeIndex((short)0);
					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE:
				{
					ConstantPoolTypeNameAndType entry = (ConstantPoolTypeNameAndType)abstractCPT;
					entry.setCptName((ConstantPoolTypeUtf8)getConstantPoolTypeByIndex(constantPool, entry.getNameIndex()));
					entry.setNameIndex((short)0);
					entry.setCptDescriptor((ConstantPoolTypeUtf8)getConstantPoolTypeByIndex(constantPool, entry.getDescriptorIndex()));
					entry.setDescriptorIndex((short)0);
					
					break;
				}
				case AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING:
				{
					ConstantPoolTypeString entry = (ConstantPoolTypeString)abstractCPT;
					entry.setCptString((ConstantPoolTypeUtf8)getConstantPoolTypeByIndex(constantPool, entry.getStringIndex()));
					entry.setStringIndex((short)0);
					
					break;
				}
			}
		}
	}
	
	public static AbstractConstantPoolType getConstantPoolTypeByIndex(ConstantPool constantPool, int index)
	{
		return constantPool.constantPool[index];
	}
	
	public static byte[] serialize(ConstantPool constantPool) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		for(int i = 1; i <= constantPool.getConstantPoolCount(); i++)
		{
			AbstractConstantPoolType elem;
			byte                     tag;
			
			elem = constantPool.getConstantPool()[i];
			tag  = elem.getConstant_pool_tag();
			
			switch(tag)
			{
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_CLASS:
			    {
			    	baos.write(ConstantPoolTypeClass.serialize((ConstantPoolTypeClass)elem));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF:
			    {
			    	baos.write(ConstantPoolTypeFieldref.serialize((ConstantPoolTypeFieldref)elem));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF:
			    {
			    	baos.write(ConstantPoolTypeMethodref.serialize((ConstantPoolTypeMethodref)elem));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF:
			    {
			    	baos.write(ConstantPoolTypeInterfaceMethodref.serialize((ConstantPoolTypeInterfaceMethodref)elem));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_STRING:
			    {
			    	baos.write(ConstantPoolTypeString.serialize((ConstantPoolTypeString)elem));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_INTEGER:
			    {
			    	baos.write(ConstantPoolTypeInteger.serialize((ConstantPoolTypeInteger)elem));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_FLOAT:
			    {
			    	baos.write(ConstantPoolTypeFloat.serialize((ConstantPoolTypeFloat)elem));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_LONG:
			    {
			    	baos.write(ConstantPoolTypeLong.serialize((ConstantPoolTypeLong)elem));
			    	i++;
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_DOUBLE:
			    {
			    	baos.write(ConstantPoolTypeDouble.serialize((ConstantPoolTypeDouble)elem));
			    	i++;
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_NAMEANDTYPE:
			    {
			    	baos.write(ConstantPoolTypeNameAndType.serialize((ConstantPoolTypeNameAndType)elem));
			    	break;
			    }
			    case AbstractConstantPoolType.CONSTANT_POOL_TAG_UTF8:
			    {
			    	baos.write(ConstantPoolTypeUtf8.serialize((ConstantPoolTypeUtf8)elem));
			    	break;
			    }
			    default:
				{
					System.out.println("FATAL SERIALIZATION ERROR: Unknown constant pool tag!");
					break;
				}
			}
		}
		
		return baos.toByteArray();
	}

	
	
	
	
	
	
	
	
}