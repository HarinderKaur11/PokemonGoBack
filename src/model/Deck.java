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
	
	public void buildDeck(){
		DeckFileReader filereader = new DeckFileReader();
		ArrayList<String[]> cardsList = filereader.getDeck1Cards();
		pokemonStage stage = new basicPokemon();
		ArrayList<ability> newAbility = new ArrayList<ability>();
		Energy[] EnergyInfo = {new Energy("Fighting")};
		newAbility.add(new damageAbility("Attack", 10, EnergyInfo,"Pokemon"));
		int x = 1;
		for(String[] cards : cardsList){
			int n = Integer.parseInt(cards[0]);
			for(int i=1;i<=n;i++){
				switch(cards[2]){
					case "P":
						deckCards.add(new Pokemon(x, cards[1], stage, 80, newAbility));
					case "T":
						deckCards.add(new Item_Trainer(cards[1]));
					case "E":
						deckCards.add(new Energy(cards[1]));
				}
				x++;
			}
		}
	}
	
	@Override
	public String getName() {
		return this.name;
	}

}
