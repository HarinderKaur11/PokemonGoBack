package model;

import java.util.ArrayList;

public class AIplayer extends Player {
	
	private String name;
	private int score;
	private cardItem deck;
	
	public AIplayer(String newName) {
		super(newName);
		this.name = newName;
		deck = new Deck();
		((Deck) deck).buildDeck(1);
	}
	
	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}

	public void setTurn(boolean newTurn){
		this.turn = newTurn;
		if(this.turn==true){
			runAI();
		}
	}
	
	private void runAI(){
		boolean energyCardUsed = false;
		ArrayList<Pokemon> cards = ((CardsGroup) this.inhand).getAllBasicPokemonCard();
		if(this.activePokemon==null){
			boolean noPokemon = true;
			if(cards.size()!=0){
				this.activePokemon = cards.remove(0);
				((CardsGroup) this.inhand).removeCard(this.activePokemon);
				noPokemon = false;
				Debug.message("Active pokemon set: "+this.activePokemon.getName());
			}
			if(noPokemon){
				//declare Mulligan
			}
		}
		if(cards.size()!=0){
			if(bench.size()<5){
				Pokemon card2 = cards.remove(0);
				bench.add(card2);
				Debug.message("Card added to bench: "+ card2.getName());
				((CardsGroup) this.inhand).removeCard(card2);
			}
		}
		

		ArrayList<Energy> energyCards = ((CardsGroup) this.inhand).getAllEnergyCards();
		if(!energyCards.isEmpty() && !energyCardUsed){
			energyCardUsed = checkAndPlayEnergy(energyCards);
		}
		
		ArrayList<Trainer> trainerCard = ((CardsGroup) this.inhand).getAllTranerCards();
		if(!trainerCard.isEmpty() && this.activePokemon!=null){
			((CardsGroup) this.inhand).removeCard(trainerCard.get(0));
			trainerCard.get(0).getAbility().useAbility();
			Debug.message("Trainer card used "+ trainerCard.get(0).getName());
		}
		
		energyCards = ((CardsGroup) this.inhand).getAllEnergyCards();
		if(!energyCards.isEmpty() && !energyCardUsed){
			energyCardUsed = checkAndPlayEnergy(energyCards);
		}
		
		ability attack = null;
		ability[] abilits = this.activePokemon.getAbilities();
		int i = abilits.length;
		while(attack==null && i>0){
			if(((damageAbility) abilits[i-1]).getEnergyInfo().length <= this.activePokemon.getAttachedCardsCount()){
				attack=abilits[i-1];
			}
			i--;
		}
		Turn.getInstance().changeTurn();
	}
	
	private boolean checkAndPlayEnergy(ArrayList<Energy> energyCards){
		Debug.message(this.activePokemon.getAttachedCards().length);
		if(this.activePokemon.getAttachedCards().length<this.activePokemon.totalEnergyRequired()){
			this.activePokemon.attachCard(energyCards.get(0));
			((CardsGroup) this.inhand).removeCard(energyCards.get(0));
			Debug.message("Energy card added to Active pokemon");
			return true;
		}
		else{
			for(Pokemon pokemon : bench){
				if(pokemon.getAttachedCards().length<pokemon.totalEnergyRequired()){
					pokemon.attachCard(energyCards.get(0));
					((CardsGroup) this.inhand).removeCard(energyCards.get(0));
					Debug.message("Energy card added to "+pokemon.getName());
					return true;
				}
			}
		}
		return false;
	}
	
}
