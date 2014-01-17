package com.mitp0sh.jaclaff;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.constantpool.ConstantPoolTypeClass;
import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.exception.deserialization.InvalidDeserializationException;
import com.mitp0sh.jaclaff.fields.Fields;
import com.mitp0sh.jaclaff.interfaces.Interfaces;
import com.mitp0sh.jaclaff.methods.Methods;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;

public class VirtualClassFile 
{
	private int                 magic = 0;
	private short        minorVersion = 0;
	private short        majorVersion = 0;
	private ConstantPool constantPool = null;	
	private short         accessFlags = 0;
	private int             thisField = 0;
	private int            superField = 0;
	private Interfaces     interfaces = null;	
	private Fields             fields = null;		
	private Methods           methods = null;
	private int       attributesCount = 0;
	private Attributes     attributes = null;
	
	private ConstantPoolTypeClass  thisFieldObject = null;
	private ConstantPoolTypeClass superFieldObject = null;
	
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

	public int getThisField()
	{
		return thisField;
	}

	public void setThisField(int thisField) 
	{
		this.thisField = thisField;
	}

	public int getSuperField() 
	{
		return superField;
	}

	public void setSuperField(int superField)
	{
		this.superField = superField;
	}
	
	public int getInterfacesCount() 
	{
		if(interfaces != null)
		{
			return interfaces.getNumberOfInterfaces();
		}
		
		return 0;
	}

	public Interfaces getInterfaces()
	{
		return interfaces;
	}

	public void setInterfaces(Interfaces interfaces) 
	{
		this.interfaces = interfaces;
	}
	
	public int getFieldsCount()
	{
		if(fields != null)
		{
			return fields.getNumberOfFields();
		}
		
		return 0;
	}
	
	public Fields getFields()
	{
		return fields;
	}

	public void setFields(Fields fields)
	{
		this.fields = fields;
	}
	
	public int getMethodsCount() 
	{
		if(methods != null)
		{
			return methods.getNumberOfMethods();
		}
		
		return 0;
	}

	public Methods getMethods() 
	{
		return methods;
	}

	public void setMethods(Methods methods) 
	{
		this.methods = methods;
	}
	
	public int getAttributesCount()
	{
		return attributesCount;
	}

	public void setAttributesCount(int attributesCount) 
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
	
	public ConstantPoolTypeClass getThisFieldObject()
	{
		return thisFieldObject;
	}

	public void setThisFieldObject(ConstantPoolTypeClass thisFieldObject) 
	{
		this.thisFieldObject = thisFieldObject;
	}

	public ConstantPoolTypeClass getSuperFieldObject() 
	{
		return superFieldObject;
	}

	public void setSuperFieldObject(ConstantPoolTypeClass superFieldObject) 
	{
		this.superFieldObject = superFieldObject;
	}
	
	public static VirtualClassFile deserialize(DesCtx ctx, String filename) throws InvalidDeserializationException
	{	
		try
		{
			return deserialize(ctx, new DataInputStream(new FileInputStream(new File(filename))));
		}
		catch(FileNotFoundException e)
		{
			throw new InvalidDeserializationException(e.getMessage());
		}
	}
	
	public static VirtualClassFile deserialize(DesCtx ctx, File file) throws InvalidDeserializationException
	{	
		try
		{
			return deserialize(ctx, new FileInputStream(file));
		}
		catch(FileNotFoundException e) 
		{
			throw new InvalidDeserializationException(e.getMessage());
		}
	}
	
	public static VirtualClassFile deserialize(DesCtx ctx, FileInputStream fis) throws InvalidDeserializationException
	{	
		return deserialize(ctx, new DataInputStream(fis));
	}
	
	public static VirtualClassFile deserialize(DesCtx ctx, DataInputStream dis) throws InvalidDeserializationException
	{
		if(dis != null)
		{
			/* overwrite data input stream */
			ctx.setDataInputStream(dis);
		}
		
		VirtualClassFile cfd = new VirtualClassFile();
		
		try
		{
			/* read magic */
			cfd.setMagic(dis.readInt());
		
			/* read minor version */
			cfd.setMinorVersion((short)dis.readUnsignedShort());
			
			/* read major version */
			cfd.setMajorVersion((short)dis.readUnsignedShort());
			
			/* read constant pool count */
			int constantPoolCount = dis.readUnsignedShort();
			
			/* parse constant pool */
			cfd.setConstantPool(ConstantPool.deserialize(ctx, constantPoolCount));
			
			/* read access flags */
			cfd.setAccessFlags((short)dis.readUnsignedShort());
			
			/* read this field */
			cfd.setThisField(dis.readUnsignedShort());
			
			/* read super field */
			cfd.setSuperField(dis.readUnsignedShort());
			
			/* read interfaces count */
			int numberOfInterfaces = dis.readUnsignedShort();
			
			/* parse interfaces */
			cfd.setInterfaces(Interfaces.deserialize(ctx, numberOfInterfaces));
			
			/* read fields count */
			int numberOfFields = dis.readUnsignedShort();
			
			/* parse fields */
			cfd.setFields(Fields.deserialize(ctx, numberOfFields));
			
			/* read methods count */
			int numberOfMethods = dis.readUnsignedShort();
			
			/* parse methods */
			cfd.setMethods(Methods.deserialize(ctx, numberOfMethods));
			
			/* read attributes count */
			cfd.setAttributesCount(dis.readUnsignedShort());
			
			/* parse methods */
			cfd.setAttributes(Attributes.deserialize(ctx, cfd.getAttributesCount(), null));
			
			/* decouple from indices */
			decoupleFromIndices(ctx, cfd);
		}
		catch(IOException e)
		{
			throw new InvalidDeserializationException(e.getMessage());
		}
		
		return cfd;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, VirtualClassFile vcf)
	{
		ConstantPool constantPool = ctx.getConstantPool();
		
		ConstantPoolTypeClass thisFieldObject = null;
		thisFieldObject = (ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, vcf.getThisField());
		vcf.setThisFieldObject(thisFieldObject);
		
		ConstantPoolTypeClass superFieldObject = null;
		superFieldObject = (ConstantPoolTypeClass)ConstantPool.getConstantPoolTypeByIndex(constantPool, vcf.getSuperField());
		vcf.setSuperFieldObject(superFieldObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, VirtualClassFile vcf)
	{
		int thisFieldIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), vcf.getThisFieldObject());
		vcf.setThisField(thisFieldIndex);
		
		int superFieldIndex = ConstantPool.getIndexFromConstantPoolEntry(ctx.getConstantPool(), vcf.getSuperFieldObject());
		vcf.setSuperField(superFieldIndex);
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
		
		/* couple to indices */
		coupleToIndices(ctx, vcf);
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(vcf.getMagic(), Integer.class));
		
		baos.write(PNC.toByteArray(vcf.getMinorVersion(), Short.class));
		baos.write(PNC.toByteArray(vcf.getMajorVersion(), Short.class));
		
		int constantPoolCount = ctx.getConstantPool().getConstantPoolCount() + 1;
		baos.write(PNC.toByteArray(constantPoolCount, Short.class));
		baos.write(ConstantPool.serialize(ctx, ctx.getConstantPool()));
		
		baos.write(PNC.toByteArray(vcf.getAccessFlags(), Short.class));
		
		baos.write(PNC.toByteArray(vcf.getThisField(), Short.class));
		baos.write(PNC.toByteArray(vcf.getSuperField(), Short.class));
		
		baos.write(PNC.toByteArray(vcf.getInterfacesCount(), Short.class));
		baos.write(Interfaces.serialize(ctx, ctx.getVirtualClassFile().getInterfaces()));
		
		baos.write(PNC.toByteArray(vcf.getFieldsCount(), Short.class));
		baos.write(Fields.serialize(ctx, ctx.getVirtualClassFile().getFields()));
		
		baos.write(PNC.toByteArray(vcf.getMethodsCount(), Short.class));
		baos.write(Methods.serialize(ctx, ctx.getVirtualClassFile().getMethods()));
		
		baos.write(PNC.toByteArray(vcf.getAttributesCount(), Short.class));
		baos.write(Attributes.serialize(ctx, ctx.getVirtualClassFile().getAttributes(), null));
		
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
