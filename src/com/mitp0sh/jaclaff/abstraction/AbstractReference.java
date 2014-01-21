package com.mitp0sh.jaclaff.abstraction;

import java.util.ArrayList;

public abstract class AbstractReference implements Referenceable
{
	private ArrayList<AbstractReference> references = new ArrayList<AbstractReference>();
	
	@Override
	public void addReference(AbstractReference reference)
	{			
		if(references.contains(reference))
		{
			return;
		}
		
		references.add(reference);
		reference.addReference(this);
	}

	@Override
	public void removeReference(AbstractReference reference)
	{
		references.remove(reference);
	}

	@Override
	public boolean isReferenced()
	{
		return references.size() == 0 ? false : true;
	}

	@Override
	public int getNumReferences()
	{
		return references.size();
	}
}
