package model;

public class condAbility extends ability{

	/*cond:flip:dam:target:opponent-active:30
		cond:flip:applystat:status:paralyzed:opponent-active
		cond:flip:dam:target:opponent-active:10
		cond:healed:target:your-active:dam:target:opponent-active:80
		cond:flip:deenergize:target:opponent-active:1
		cond:flip:dam:target:opponent-active:30
		cond:flip:dam:target:opponent-active:20
		cond:flip:dam:target:opponent-active:40:else:applystat:status:paralyzed:opponent-active
		cond:flip:deenergize:target:opponent-active:1
		cond:ability:deck:destination:discard:target:choice:you:1:(search:target:you:source:deck:filter:top:8:1,shuffle:target:you)
		cond:count(target:your-active:energy:psychic)>0:dam:target:opponent-active:20
		cond:flip:dam:target:opponent-active:30:else:dam:target:your-active:30
		cond:flip:dam:target:opponent-active:10
		cond:flip:(applystat:status:asleep:opponent-active,applystat:status:poisoned:opponent-active)
		cond:choice:shuffle:target:opponent
		cond:flip:heal:target:your-active:10
	 */
	private String name;
	private String condition;
	
	public condAbility(String name, String condition)
	{
		this.name = name;
		this.condition = condition;
		
	}
	
	public void useAbility() {
		if(condition.equals("flip"))
		{
			
		}
	}
	
	public boolean equals(Object o) {
		return false;
	}

}
