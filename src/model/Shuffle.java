package model;

import java.util.Collections;

public class Shuffle extends ability{
	
	private String name;
	private String targetDeck;
	
	public Shuffle(String name, String target)
	{
		this.name = name;
		this.targetDeck = target;
	}
	
	public void useAbility() {
		 Deck shuffle =  (Deck) target.getTargetObject(targetDeck).getTarget();
		 shuffle.shufflecards();
		}

	public boolean equals(Object o) {
		
		return false;
	}

}
