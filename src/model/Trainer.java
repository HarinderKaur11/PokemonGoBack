package model;

public class Trainer implements cardItem{

	private String cardName;
	private int id;
	private ability cardAbility;
	
	public Trainer(String name,int id,ability newAbility){
		this.cardName = name;
		this.id = id;
		this.cardAbility = newAbility;
	}
	
	@Override
	public String getName() {
		return this.cardName;
	}
	
	public int getID(){
		return this.id;
	}
	
	public ability getAbility(){
		return this.cardAbility;
	}

}
