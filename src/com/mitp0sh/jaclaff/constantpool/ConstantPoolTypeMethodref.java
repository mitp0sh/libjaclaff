package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeMethodrefDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeMethodref extends AbstractConstantPoolType
{
	private int                           classIndex = 0;	
	private int                     nameAndTypeIndex = 0;	
	private ConstantPoolTypeClass             classObject = null;
	private ConstantPoolTypeNameAndType nameAndTypeObject = null;
	
	public ConstantPoolTypeMethodref()
	{
		super.setConstant_pool_string_representation("CONSTANT_Methodref");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_METHODREF);
	}
	
	public int getClassIndex()
	{
		return this.classIndex;
	}
	
	public void setClassIndex(int index) 
	{
		this.classIndex = index;
	}
	
	public int getNameAndTypeIndex() 
	{
		return this.nameAndTypeIndex;
	}
	
	public void setNameAndTypeIndex(int index)
	{
		this.nameAndTypeIndex = index;
	}
	
	public ConstantPoolTypeClass getClassObject() 
	{
		return classObject;
	}

	public void setClassObject(ConstantPoolTypeClass object) 
	{
		this.classObject = object;
		
		if(object != null)
		{
			this.setClassIndex(0);
			this.addReference(object);
		}
	}

	public ConstantPoolTypeNameAndType getNameAndTypeObject() 
	{
		return nameAndTypeObject;
	}

	public void setNameAndTypeObject(ConstantPoolTypeNameAndType object)
	{
		this.nameAndTypeObject = object;
		
		if(object != null)
		{
			this.setNameAndTypeIndex(0);
			this.addReference(object);
		}
	}
	
	public static ConstantPoolTypeMethodref deserialize(DataInputStream dis) throws InvalidConstantPoolTypeMethodrefDeserializationException,
	                                                                                IOException
	{
		ConstantPoolTypeMethodref cptMethodref = new ConstantPoolTypeMethodref();
		
		cptMethodref.setClassIndex(dis.readUnsignedShort());
		cptMethodref.setNameAndTypeIndex(dis.readUnsignedShort());
		
		return cptMethodref;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, ConstantPoolTypeMethodref cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int classIndex = cpt.getClassIndex();
		AbstractConstantPoolType acptClass = ConstantPool.cpeByIndex(cp, classIndex);
		ConstantPoolTypeClass classObject = (ConstantPoolTypeClass)acptClass;
		cpt.setClassObject(classObject);

		int NameAndTypeIndex= cpt.getNameAndTypeIndex();
		AbstractConstantPoolType acptNameAndType = ConstantPool.cpeByIndex(cp, NameAndTypeIndex);
		ConstantPoolTypeNameAndType nameAndTypeObject =  (ConstantPoolTypeNameAndType)acptNameAndType;
		cpt.setNameAndTypeObject(nameAndTypeObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, ConstantPoolTypeMethodref cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		ConstantPoolTypeClass classObject = cpt.getClassObject();
		int classIndex = ConstantPool.indexByCPE(cp, classObject);
		cpt.setClassIndex(classIndex);
		
		ConstantPoolTypeNameAndType nameAndTypeObject = cpt.getNameAndTypeObject();
		int nameAndTypeIndex = ConstantPool.indexByCPE(cp, nameAndTypeObject); 
		cpt.setNameAndTypeIndex(nameAndTypeIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeMethodref elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getClassIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getNameAndTypeIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeMethodref clone()
	{
		/* create new empty instance */
		ConstantPoolTypeMethodref clone = (ConstantPoolTypeMethodref)super.clone();
		
		/* fill instance with original data */
		clone.setClassObject(this.getClassObject());
		clone.setNameAndTypeObject(this.getNameAndTypeObject());	
		
		return clone;
	}
	
	//@Override
	//public boolean equals(Object obj)
	//{
	//	try
	//	{
	//		ConstantPoolTypeMethodref cpt = (ConstantPoolTypeMethodref)obj;
	//		boolean b0 = cpt.classObject.equals(this.classObject);
	//		boolean b1 = cpt.nameAndTypeObject.equals(this.nameAndTypeObject);
	//		return b0 && b1;
	//	}
    //  catch(NullPointerException e){}
	//	catch(ClassCastException e){}
	//	
	//	return false;
	//}
}
