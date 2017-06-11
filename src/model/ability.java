package model;

public abstract class ability {
	
	protected String name;
	protected String abilitytarget;
	
	public String getName(){
		return this.name;
	}
	
	public target getTargetObject(){
		return target.getTargetObject(this.abilitytarget);
	}
	
	public abstract boolean equals(Object o);
	public abstract void useAbility();

}
