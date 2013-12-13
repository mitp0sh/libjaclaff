package com.mitp0sh.jaclaff;

import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.fields.Fields;
import com.mitp0sh.jaclaff.interfaces.Interfaces;
import com.mitp0sh.jaclaff.methods.Methods;

public class VirtualClassFile 
{
	private int                        magic = 0;
	private short               minorVersion = 0;
	private short               majorVersion = 0;
	private short          constantPoolCount = 0;
	private ConstantPool        constantPool = null;	
	private short                accessFlags = 0;
	private short                  thisField = 0;
	private short                 superField = 0;
	private short            interfacesCount = 0;
	private Interfaces            interfaces = null;	
	private short                fieldsCount = 0;
	private Fields                    fields = null;	
	private short               methodsCount = 0;	
	private Methods                  methods = null;
	private short            attributesCount = 0;
	private Attributes            attributes = null;
	
	public int getMagic()
	{
		return this.magic;
	}

	public void setMagic(int magic)
	{
		this.magic = magic;
	}
	
	public short getMinorVersion()
	{
		return this.minorVersion;
	}

	public void setMinorVersion(short MinorVersion)
	{
		this.minorVersion = MinorVersion;
	}

	public short getMajorVersion() 
	{
		return this.majorVersion;
	}

	public void setMajorVersion(short MajorVersion) 
	{
		this.majorVersion = MajorVersion;
	}

	public short getConstantPoolCount() 
	{
		return this.constantPoolCount;
	}

	public void setConstantPoolCount(short ConstantPoolCount) 
	{
		this.constantPoolCount = ConstantPoolCount;
	}
	
	public ConstantPool getConstantPool()
	{
		return this.constantPool;
	}

	public void setConstantPool(ConstantPool ConstantPool)
	{
		this.constantPool = ConstantPool;
	}
	
	public short getAccessFlags() 
	{
		return accessFlags;
	}

	public void setAccessFlags(short accessFlags)
	{
		this.accessFlags = accessFlags;
	}

	public short getThisField()
	{
		return thisField;
	}

	public void setThisField(short thisField) 
	{
		this.thisField = thisField;
	}

	public short getSuperField() 
	{
		return superField;
	}

	public void setSuperField(short superField)
	{
		this.superField = superField;
	}
	
	public short getInterfacesCount() 
	{
		return interfacesCount;
	}

	public void setInterfacesCount(short interfacesCount) 
	{
		this.interfacesCount = interfacesCount;
	}

	public Interfaces getInterfaces()
	{
		return interfaces;
	}

	public void setInterfaces(Interfaces interfaces) 
	{
		this.interfaces = interfaces;
	}
	
	public short getFieldsCount()
	{
		return fieldsCount;
	}

	public void setFieldsCount(short fieldsCount)
	{
		this.fieldsCount = fieldsCount;
	}
	
	public Fields getFields()
	{
		return fields;
	}

	public void setFields(Fields fields)
	{
		this.fields = fields;
	}
	
	public short getMethodsCount() 
	{
		return methodsCount;
	}

	public void setMethodsCount(short methodsCount) 
	{
		this.methodsCount = methodsCount;
	}

	public Methods getMethods() 
	{
		return methods;
	}

	public void setMethods(Methods methods) 
	{
		this.methods = methods;
	}
	
	public short getAttributesCount()
	{
		return attributesCount;
	}

	public void setAttributesCount(short attributesCount) 
	{
		this.attributesCount = attributesCount;
	}

	public Attributes getAttributes()
	{
		return attributes;
	}

	public void setAttributes(Attributes attributes)
	{
		this.attributes = attributes;
	}
}
