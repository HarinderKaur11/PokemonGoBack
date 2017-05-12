package model;

public class damageAbility implements ability {
	private int damageValue;
	public String name;
	
	public damageAbility(String newName, int newDamage){
		this.name = newName;
		this.damageValue = newDamage;
	}
	
	public int getDamage(){
		return this.damageValue;
	}
	public void setDamage(int newDamage){
		this.damageValue = newDamage;
	}

	@Override
	public String getName() {
		return this.name;
	}
}
