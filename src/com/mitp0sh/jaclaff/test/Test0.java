package com.mitp0sh.jaclaff.test;

import java.io.IOException;
import java.util.Random;
import java.util.zip.ZipException;

import com.mitp0sh.jaclaff.VirtualClassFile;
import com.mitp0sh.jaclaff.serialization.SerCtx;

public class Test0 
{
	//@SuppressWarnings("unchecked")
	public static void main(String[] args) throws ZipException, IOException 
	{
		VirtualClassFile vcf = VirtualClassFile.deserialize("test/SimpleGUI.class.temp");
		SerCtx serCtx = new SerCtx(vcf, vcf.getConstantPool());
		VirtualClassFile.serialize(serCtx, "test/com/mitp0sh/jaclaff/test/SimpleGUI.class");
		
		/*LinkedList<VirtualClassFile> vcfList = new LinkedList<VirtualClassFile>();
		LinkedList<String> nameList = new LinkedList<String>();
		
		ZipFile zipFile = new ZipFile(new File("/Users/bg/Downloads/JBombSweeper-Beta-2.0.1.jar"));
		
		Enumeration<ZipEntry> enums = ((Enumeration<ZipEntry>) zipFile.entries());
		
		while(enums.hasMoreElements())
		{
			ZipEntry curr = enums.nextElement();
			if(!curr.getName().endsWith(".class"))
			{
				continue;
			}
			
			DataInputStream dis = new DataInputStream(zipFile.getInputStream(curr));
			VirtualClassFile currVCF = Deserializer.deserialize(dis);
            vcfList.add(currVCF);
			
			ConstantPoolTypeClass cptc = (ConstantPoolTypeClass) ConstantPool.getConstantPoolTypeByIndex(currVCF.getConstantPool(), currVCF.getThisField());
			String thisClassString = new String(cptc.getCptName().getBytes());
			String[] thisClassToken = thisClassString.split("/");
			String finalTS = "";
			for(int i = 0; i < thisClassToken.length; i++)
			{
				finalTS += "/" + thisClassToken[i];
			}
			finalTS += ".class";
			nameList.add(finalTS);
			dis.close();
		}
		
		System.out.println("blah");
		
		//Iterator<VirtualClassFile> iters = vcfList.iterator();
		//while(iters.hasNext())
		//{
		//	VirtualClassFile curr = iters.next();
		//	MethodEntry[] methods = curr.getMethods().getMethods();
			
		//	ConstantPoolTypeClass cptc = (ConstantPoolTypeClass) ConstantPool.getConstantPoolTypeByIndex(curr.getConstantPool(), curr.getThisField());
		//	String thisClassString = new String(cptc.getCptName().getBytes());
		//	System.out.println("thisClassString: " + thisClassString + "\n\n");
			
			
		//	for(int x = 0; x < methods.length; x++)
		//	{
		//		MethodEntry method = methods[x];
		//		String methodName = new String(method.getNameObject().getBytes());
		//		if(methodName.contains("<init>") || methodName.contains("<clinit>") || methodName.contains("$"))
		//		{
		//			continue;
		//		}
		//		System.out.println(methodName);
		//		
		//		String randName = "";
		//		randName = "a" + randomString(10);
		//		
		//		method.getNameObject().setBytes(randName);				
		//	}
		//}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(new File("/Users/bg/Downloads/JBombSweeper-Beta-2.0.1.out.jar")));
		
		Iterator<VirtualClassFile> iter = vcfList.iterator();
		Iterator<String> nameIter = nameList.iterator();
		while(iter.hasNext())
		{	
			VirtualClassFile currVCF = iter.next();
			
			String name = nameIter.next();
			ZipEntry ze = new ZipEntry(name);
			
			zos.putNextEntry(ze);
			byte[] buffer = Serializer.serialize(currVCF);
			zos.write(buffer);
			zos.closeEntry();
		}
		
		zos.flush();
		zos.close();*/
	}
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	static String randomString( int len ) 
	{
	   StringBuilder sb = new StringBuilder( len );
	   for( int i = 0; i < len; i++ ) 
	      sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
	   return sb.toString();
	}
}
