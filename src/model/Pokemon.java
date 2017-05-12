package model;

import java.util.ArrayList;

public class Pokemon implements cardItem{
	private int id;
	private String cardName = new String();
	private int hitpoints;
	private pokemonStage pStage;
	private int damage = 0;
	private String state = "deck";
	private ArrayList<ability> abilities = new ArrayList<ability>();
	private String status;
	
	public Pokemon(int newId, String name, pokemonStage newPokemonStage, int newHp, ArrayList<ability> newAbilities){
		this.id = newId;
		this.cardName = name;
		this.pStage = newPokemonStage;
		this.hitpoints = newHp;
		this.abilities = newAbilities;
	}
	
	public void addDamage(int newDamage){
		this.damage += newDamage;
		if(this.damage==this.hitpoints || this.damage>=this.hitpoints){
			this.state = "knockedOut";
		}
	}
	
	public void removeDamage(int newHealing){
		if(this.damage<newHealing){
			this.damage = 0;
		}
		else{
			this.damage -= newHealing;
		}
	}
	
	public void setState(String newState){
		this.state = newState;
	}
	
	public String getName(){
		return this.cardName;
	}
	
	public String getStage(){
		return this.pStage.getStage();
	}
	
	public String getState(){
		return this.state;
	}
	
	public int getDamage(){
		return this.damage;
	}
	
	public ability[] getAbilities(){
		return this.abilities.toArray(new ability[this.abilities.size()]);
	}
	
	public boolean equals(Object o){
		Pokemon tempP = (Pokemon) o;
		if(this.cardName == tempP.cardName){
			return true;
		}
		else{
			return false;
		}
	}
}
