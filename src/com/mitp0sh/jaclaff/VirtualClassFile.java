package com.mitp0sh.jaclaff;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.fields.Fields;
import com.mitp0sh.jaclaff.interfaces.Interfaces;
import com.mitp0sh.jaclaff.methods.Methods;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

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
	
	public static VirtualClassFile deserialize(String filename) throws IOException
	{	
		return deserialize(new DataInputStream(new FileInputStream(new File(filename))));
	}
	
	public static VirtualClassFile deserialize(File file) throws IOException
	{	
		return deserialize(new FileInputStream(file));
	}
	
	public static VirtualClassFile deserialize(FileInputStream fis) throws IOException
	{	
		return deserialize(new DataInputStream(fis));
	}
	
	public static VirtualClassFile deserialize(DataInputStream dis) throws IOException
	{
		VirtualClassFile cfd = new VirtualClassFile();
		
		/* read magic */		
		cfd.setMagic(dis.readInt());
		
		/* read minor version */
		cfd.setMinorVersion((short)dis.readUnsignedShort());
		
		/* read major version */
		cfd.setMajorVersion((short)dis.readUnsignedShort());
		
		/* read constant pool count */
		cfd.setConstantPoolCount((short)dis.readUnsignedShort());
		
		/* parse constant pool */
		cfd.setConstantPool(ConstantPool.deserialize(dis, cfd.getConstantPoolCount()));
		
		/* read access flags */
		cfd.setAccessFlags((short)dis.readUnsignedShort());
		
		/* read this field */
		cfd.setThisField((short)dis.readUnsignedShort());
		
		/* read super field */
		cfd.setSuperField((short)dis.readUnsignedShort());
		
		/* read interfaces count */
		cfd.setInterfacesCount((short)dis.readUnsignedShort());
		
		/* parse interfaces */
		cfd.setInterfaces(Interfaces.deserialize(dis, cfd.getInterfacesCount(), cfd.getConstantPool()));
		
		/* read fields count */
		cfd.setFieldsCount((short)dis.readUnsignedShort());
		
		/* parse fields */
		cfd.setFields(Fields.deserialize(dis, cfd.getFieldsCount(), cfd.getConstantPool()));
		
		/* read methods count */
		cfd.setMethodsCount((short)dis.readUnsignedShort());
		
		/* parse methods */
		cfd.setMethods(Methods.deserialize(dis, cfd.getMethodsCount(), cfd.getConstantPool()));
		
		/* read attributes count */
		cfd.setAttributesCount((short)dis.readUnsignedShort());
		
		/* parse methods */
		cfd.setAttributes(Attributes.deserialize(dis, cfd.getAttributesCount(), cfd.getConstantPool()));
		
		return cfd;
	}
	
	public static byte[] serialize(SerCtx ctx) throws IOException
	{
		VirtualClassFile vcf = ctx.getVirtualClassFile();
	
		/* no external constant pool for serialization !? */
		if(ctx.getConstantPool() == null)
		{
			/* set source constant pool */
			ctx.setConstantPool(vcf.getConstantPool());
		}
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(vcf.getMagic(), Integer.class));
		
		baos.write(PNC.toByteArray(vcf.getMinorVersion(), Short.class));
		baos.write(PNC.toByteArray(vcf.getMajorVersion(), Short.class));
		
		baos.write(PNC.toByteArray(vcf.getConstantPoolCount(), Short.class));
		baos.write(ConstantPool.serialize(ctx, ctx.getConstantPool()));
		
		baos.write(PNC.toByteArray(vcf.getAccessFlags(), Short.class));
		
		baos.write(PNC.toByteArray(vcf.getThisField(), Short.class));
		baos.write(PNC.toByteArray(vcf.getSuperField(), Short.class));
		
		baos.write(PNC.toByteArray(vcf.getInterfacesCount(), Short.class));
		baos.write(Interfaces.serialize(vcf.getInterfaces()));
		
		baos.write(PNC.toByteArray(vcf.getFieldsCount(), Short.class));
		baos.write(Fields.serialize(vcf.getFields(), vcf.getConstantPool()));
		
		baos.write(PNC.toByteArray(vcf.getMethodsCount(), Short.class));
		baos.write(Methods.serialize(vcf.getMethods(), vcf.getConstantPool()));
		
		baos.write(PNC.toByteArray(vcf.getAttributesCount(), Short.class));
		baos.write(Attributes.serialize(vcf.getAttributes(), vcf.getConstantPool()));
		
		return baos.toByteArray();
	}
	
	public static void serialize(SerCtx ctx, String fileName) throws IOException
	{		
		/* create file output stream */
		FileOutputStream fos = null;
		
		/* write buffer to file */
		fos = new FileOutputStream(new File(fileName));
		fos.write(serialize(ctx));
		fos.close();	
	}
}
