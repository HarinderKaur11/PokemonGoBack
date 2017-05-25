package model;

public class damageAbility implements ability {
	private int damageValue;
	private String name;
	private Energy[] energyRequired;
	private String target;
	
	public damageAbility(String newName, int newDamage, Energy[] newEnergyInfo,String newTarget){
		this.name = newName;
		this.damageValue = newDamage;
		this.energyRequired = newEnergyInfo;
		this.target = newTarget;
	}
	
	public void setDamage(int newDamage){
		this.damageValue = newDamage;
	}
	
	public void useAbility(){
		//Debug.message("Attacking Opponent pokemon "+Turn.getInstance().getOpponent().getActivePokemon().getName());
		if(Turn.getInstance().getOpponent().getActivePokemon()!=null){
			Turn.getInstance().getOpponent().getActivePokemon().addDamage(this.damageValue);
		}
		Turn.getInstance().changeTurn();
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public int getDamage(){
		return this.damageValue;
	}
	
	public Energy[] getEnergyInfo(){
		return this.energyRequired;
	}
	public String getTarget(){
		return target;
	}
	
	public boolean equals(Object o){
		damageAbility tempAbility = (damageAbility) o;
		if(this.name == tempAbility.name){
			return true;
		}		
		return false;
	}
	
}