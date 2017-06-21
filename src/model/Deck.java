package model;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends CardsGroup{
	
	private String name;
	private int deckNumber;

	private String abilityName, target, destination, drawCards, status, energyinfo, abilityparse ;
	private String damage, condition, condAbility, trigger, triggerCond, addAbility, source, filter, filterCat, count;
	private boolean choice;
	private DeckFileReader db;
	
	public Deck(){}
	
	public Deck(int n){
		this.deckNumber = n;
	}
	
	public void buildDeck(){
		db = new DeckFileReader(this.deckNumber);
		this.buildDeck(db.getDeck(), db);
	}
	
	public void readFile(){
		db = new DeckFileReader(this.deckNumber);
	}
	
	public void buildDeck(ArrayList<String[]> cardsList, DeckFileReader db){
		
		
//		ArrayList<ability> newAbility = new ArrayList<ability>();
//		ArrayList<Energy> EnergyInfo = new ArrayList<Energy>();
//		EnergyInfo.add(new Energy("Fighting"));
//		newAbility.add(new damageAbility("Attack", 10, EnergyInfo, "opponentactive", null));
		
		int x = 1;
		for(String[] card : cardsList){
				CardParser cparser = new CardParser(this);
				//Debug.message("Card no. "+ x + " Name: "+card[0]);
				switch(card[1]){
					
					case "pokemon":
						this.getGroupCards().add(cparser.createPokemon(x, card));
						break;
					case "trainer":
						this.getGroupCards().add(cparser.createTrainer(x, card));
						break;
					case "energy":
						this.getGroupCards().add(new Energy(card[0],x));
						//this.getGroupCards().
						break;
				}
				x++;
			//}
			//Debug.message(this.getGroupCards().size());
		}
		//this.shufflecards();
	}

	public void display(){
		Debug.showCard(this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]));
	}
	
	public void shufflecards(){
		Collections.shuffle(this.getGroupCards());
		//Debug.showCard(this.getGroupCards().toArray(new cardItem[this.getGroupCards().size()]));
	}
	
	@Override
	public String getName() {
		return this.name;
	}
	
	public String getAbilityString(int line){
		return db.abilityR[line];
	}
	
}
