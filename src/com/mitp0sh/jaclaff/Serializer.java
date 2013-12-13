package com.mitp0sh.jaclaff;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.attributes.Attributes;
import com.mitp0sh.jaclaff.constantpool.ConstantPool;
import com.mitp0sh.jaclaff.fields.Fields;
import com.mitp0sh.jaclaff.interfaces.Interfaces;
import com.mitp0sh.jaclaff.methods.Methods;
import com.mitp0sh.jaclaff.util.PNC;


public class Serializer
{
	public static byte[] serialize(VirtualClassFile vcf) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(PNC.toByteArray(vcf.getMagic(), Integer.class));
		baos.write(PNC.toByteArray(vcf.getMinorVersion(), Short.class));
		baos.write(PNC.toByteArray(vcf.getMajorVersion(), Short.class));
		baos.write(PNC.toByteArray(vcf.getConstantPoolCount(), Short.class));
		baos.write(ConstantPool.serialize(vcf.getConstantPool()));
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
	
	public static void serialize(VirtualClassFile vcf, String fileName) throws IOException
	{		
		/* create file output stream */
		FileOutputStream fos = null;
		
		/* write buffer to file */
		fos = new FileOutputStream(new File(fileName));
		fos.write(serialize(vcf));
		fos.close();	
	}
}
