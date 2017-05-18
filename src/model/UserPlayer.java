package model;

import java.util.ArrayList;

public class UserPlayer extends Player {
		
	public UserPlayer(String newName){
		this.name = newName;
		deck = new Deck();
		((Deck) deck).buildDeck(2);
		activeAbilities = new ArrayList<ability>();
	}
		
	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}
	
}
