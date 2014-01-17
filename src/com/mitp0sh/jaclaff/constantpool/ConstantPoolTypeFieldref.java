package com.mitp0sh.jaclaff.constantpool;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

import com.mitp0sh.jaclaff.deserialization.DesCtx;
import com.mitp0sh.jaclaff.exception.deserialization.InvalidConstantPoolTypeFieldrefDeserializationException;
import com.mitp0sh.jaclaff.serialization.SerCtx;
import com.mitp0sh.jaclaff.util.PNC;


public class ConstantPoolTypeFieldref extends AbstractConstantPoolType
{
	private int                           classIndex = 0;	
	private int                     nameAndTypeIndex = 0;	
	private ConstantPoolTypeClass             classObject = null;
	private ConstantPoolTypeNameAndType nameAndTypeObject = null;

	public ConstantPoolTypeFieldref()
	{
		super.setConstant_pool_string_representation("CONSTANT_Fieldref");
		super.setConstant_pool_tag(AbstractConstantPoolType.CONSTANT_POOL_TAG_FIELDREF);
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
			
			/* cross reference objects */
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
			
			/* cross reference objects */
			this.addReference(object);
		}
	}
	
	public static ConstantPoolTypeFieldref deserialize(DataInputStream dis) throws InvalidConstantPoolTypeFieldrefDeserializationException, IOException
	{
		ConstantPoolTypeFieldref cptFieldref = new ConstantPoolTypeFieldref();
		
		cptFieldref.setClassIndex(dis.readUnsignedShort());
		cptFieldref.setNameAndTypeIndex(dis.readUnsignedShort());
		
		return cptFieldref;
	}
	
	public static void decoupleFromIndices(DesCtx ctx, ConstantPoolTypeFieldref cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		int classIndex = cpt.getClassIndex();
		AbstractConstantPoolType acptClass = ConstantPool.getConstantPoolTypeByIndex(cp, classIndex);
		ConstantPoolTypeClass classObject = (ConstantPoolTypeClass)acptClass;
		cpt.setClassObject(classObject);

		int NameAndTypeIndex= cpt.getNameAndTypeIndex();
		AbstractConstantPoolType acptNameAndType = ConstantPool.getConstantPoolTypeByIndex(cp, NameAndTypeIndex);
		ConstantPoolTypeNameAndType nameAndTypeObject =  (ConstantPoolTypeNameAndType)acptNameAndType;
		cpt.setNameAndTypeObject(nameAndTypeObject);
	}
	
	public static void coupleToIndices(SerCtx ctx, ConstantPoolTypeFieldref cpt)
	{
		ConstantPool cp = ctx.getConstantPool();
		
		ConstantPoolTypeClass classObject = cpt.getClassObject();
		int classIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, classObject);
		cpt.setClassIndex(classIndex);
		
		ConstantPoolTypeNameAndType nameAndTypeObject = cpt.getNameAndTypeObject();
		int nameAndTypeIndex = ConstantPool.getIndexFromConstantPoolEntry(cp, nameAndTypeObject); 
		cpt.setNameAndTypeIndex(nameAndTypeIndex);
	}
	
	public static byte[] serialize(SerCtx ctx, ConstantPoolTypeFieldref elem) throws IOException
	{
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		baos.write(new byte[]{elem.getConstant_pool_tag()});
		baos.write(PNC.toByteArray(elem.getClassIndex(), Short.class));
		baos.write(PNC.toByteArray(elem.getNameAndTypeIndex(), Short.class));
		
		return baos.toByteArray();
	}
	
	public ConstantPoolTypeFieldref clone()
	{
		/* create new empty instance */
		ConstantPoolTypeFieldref clone = (ConstantPoolTypeFieldref)super.clone();
		
		/* fill instance with original data */
		clone.setClassObject(this.getClassObject());
		clone.setNameAndTypeObject(this.getNameAndTypeObject());
		
		return clone;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		try
		{
			ConstantPoolTypeFieldref cpt = (ConstantPoolTypeFieldref)obj;
			boolean b0 = cpt.classObject.equals(this.classObject);
			boolean b1 = cpt.nameAndTypeObject.equals(this.nameAndTypeObject);
			return b0 && b1;
		}
		catch(NullPointerException e){}
		catch(ClassCastException e){}
		
		return false;
	}
}
