package model;

public class Add extends ability{

	//add:target:your:trigger:opponent:turn-end:(heal:target:self:20)
	
	private String name, targetPokemon, trigger, triggerCond;
	ability addAbility;
	
	public Add(String name, String target, String trigger, String triggerCond, String addAbility)
	{
		this.name = name;
		this.targetPokemon = target;
		this.trigger = trigger;
		this.triggerCond = triggerCond;
		//this.addAbility = addAbility;
		//ability attribute, string cond, pokemon class ability array list.
	}
	
	public void useAbility() {
		Pokemon pokemon = (Pokemon) target.getTargetObject(targetPokemon).getTarget();
		pokemon.addActiveAbility(addAbility);
	}

	
	public boolean equals(Object o) {
		return false;
	}
	
}
