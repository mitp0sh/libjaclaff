package com.mitp0sh.jaclaff.abstraction;

public interface Referenceable
{
	public void    addReference(AbstractReference reference);
	public void    removeReference(AbstractReference reference);
	public boolean isReferenced();
	public int     getNumReferences();
}
