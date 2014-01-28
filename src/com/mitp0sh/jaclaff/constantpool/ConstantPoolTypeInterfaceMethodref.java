package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeInterfaceMethodrefDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeInterfaceMethodref extends AbstractConstantPoolType
{
	private int                             classIndex = 0;	
	private int                       nameAndTypeIndex = 0;	
	private ConstantPoolTypeClass             classObject = null;
	private ConstantPoolTypeNameAndType nameAndTypeObject = null;
	
	public ConstantPoolTypeInterfaceMethodref()
	{
		super.setConstant_pool_string_representation("CONSTANT_InterfaceMethodref");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_INTERFACEMETHODREF);
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
	
	public static ConstantPoolTypeInterfaceMethodref deserialize(DataInputStream dis) throws InvalidConstantPoolTypeInterfaceMethodrefDeserializationException, IOException
	{
		ConstantPoolTypeInterfaceMethodref cptInterfaceMethodref = new ConstantPoolTypeInterfaceMethodref();
		
		cptInterfaceMethodref.setClassIndex(dis.readUnsignedShort());
		cptInterfaceMethodref.setNameAndTypeIndex(dis.readUnsignedShort());
		
		return cptInterfaceMethodref;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, ConstantPoolTypeInterfaceMethodref cpt)
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
	
	public static void coupleToIndices(SerCtx ctx, ConstantPoolTypeInterfaceMethodref cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		ConstantPoolTypeClass classObject = cpt.getClassObject();
		int classIndex = ConstantPool.indexByCPE(cp, classObject);
		cpt.setClassIndex(classIndex);
		
		ConstantPoolTypeNameAndType nameAndTypeObject = cpt.getNameAndTypeObject();
		int nameAndTypeIndex = ConstantPool.indexByCPE(cp, nameAndTypeObject); 
		cpt.setNameAndTypeIndex(nameAndTypeIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeInterfaceMethodref elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getClassIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getNameAndTypeIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeInterfaceMethodref clone()
	{
		/* create new empty instance */
		ConstantPoolTypeInterfaceMethodref clone = (ConstantPoolTypeInterfaceMethodref)super.clone();
		
		/* fill instance with original data */
		clone.setClassObject(this.getClassObject());
		clone.setNameAndTypeObject(this.getNameAndTypeObject());
		
		return clone;
	}
	
	@Override
	public String toString()
	{	
		String text = "";
		
		String classStr = getClassObject().toString();
		
		String nameAndTypeStr = getNameAndTypeObject().toString();
		
		text = classStr + "." + nameAndTypeStr;
		
		return text;
	}
}
