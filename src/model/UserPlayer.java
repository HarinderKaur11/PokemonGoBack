package model;

import java.util.ArrayList;

public class UserPlayer implements Player {
	
	private String name;
	private int score;
	private cardItem deck;
	private cardItem inhand;
	
	public UserPlayer(String newName){
		this.name = newName;
		deck = new Deck();
		((Deck) deck).buildDeck(2);
	}
	
	public cardItem getDeckCard(){
		return ((Deck) this.deck).removeFirstCard();
	}
	
	public cardItem[] getDeckCards(int i){
		ArrayList<cardItem> cards = new ArrayList<cardItem>();
		for(int x=0; x<i; x++){
			cards.add(getDeckCard());
		}
		return cards.toArray(new cardItem[cards.size()]);
	}
	
	public cardItem dealCard(){
		cardItem newcard = getDeckCard();
		((CardsGroup) inhand).addCard(newcard);
		return newcard;
	}
	
	public cardItem[] dealMultipleCards(int i){
		cardItem[] dealt = getDeckCards(i);
		for(int x=0; x<i; x++){
			((CardsGroup) inhand).addCard(dealt[x]);
		}
		return dealt;
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getScore() {
		return this.score;
	}
	
}
