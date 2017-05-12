package model;

import java.util.ArrayList;

public class Deck implements cardItem {
	
	private String name;
	private ArrayList<cardItem> deckCards = new ArrayList<cardItem>();
	
	public void addCard(cardItem newCard){
		this.deckCards.add(newCard);
	}
	public cardItem removeFirstCard(){
		return this.deckCards.get(0);
	}
	public cardItem[] getCard(){
		return this.deckCards.toArray(new cardItem[this.deckCards.size()]);
	}
	public void removeCard(cardItem newCard){
		this.deckCards.remove(newCard);
	}
	
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

}
