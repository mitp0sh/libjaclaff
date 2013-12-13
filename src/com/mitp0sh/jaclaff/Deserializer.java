package com.mitp0sh.jaclaff;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.fields.Fields;
import com.mitp0sh.jaclaff.interfaces.Interfaces;
import com.mitp0sh.jaclaff.methods.Methods;


public class Deserializer
{
	public static VirtualClassFile deserialize(String filename) throws IOException
	{	
		return Deserializer.deserialize(new DataInputStream(new FileInputStream(new File(filename))));
	}
	
	public static VirtualClassFile deserialize(File file) throws IOException
	{	
		return Deserializer.deserialize(new FileInputStream(file));
	}
	
	public static VirtualClassFile deserialize(FileInputStream fis) throws IOException
	{	
		return Deserializer.deserialize(new DataInputStream(fis));
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
}
