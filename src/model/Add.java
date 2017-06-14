package model;

public class Add extends ability{

	//add:target:your:trigger:opponent:turn-end:(heal:target:self:20)
	
	private String name, targetPokemon, trigger, triggerCondition;
	ability addAbility;
	
	public Add(String name, String target, String trigger, String triggerCondition, String addAbility)
	{
		this.name = name;
		this.targetPokemon = target;
		this.trigger = trigger;
		this.triggerCondition = triggerCondition;
		//this.addAbility = addAbility;
	}
	
	public String getTriggerCondition()
	{
		return this.triggerCondition;
	}

	public void useAbility() {
		Pokemon pokemon = (Pokemon) target.getTargetObject(targetPokemon).getTarget();
		pokemon.addActiveAbility(addAbility);
	}

	
	public boolean equals(Object o) {
		return false;
	}
	
}
