package model;

import controller.GameController;

public class DeckAbility extends ability {

	private String targetDestination, count;
	private int amount;
	private boolean choice;
	
	public DeckAbility(String newName, String newTarget, String newTargetDestination, boolean newChoice, int newAmount, String count){
		this.name = newName;
		this.abilitytarget = newTarget;
		this.targetDestination = newTargetDestination;
		this.amount = newAmount;
		this.choice = newChoice;
		this.count = count; //count = yourhand, opponenthand
	}
	
	public void useAbility() {
		if(choice){
			for(int i=0;i<amount;i++){
				cardItem nCard = GameController.getInstance().getPanelPokemonDialog((Player) target.getTargetObject(this.abilitytarget).getTarget(), "hand");
				((CardsGroup) this.getTargetLocation(this.targetDestination,this.abilitytarget)).addCard(nCard);
			}
		}
		else{
			
		}
	}
	
	public boolean equals(Object o) {
		if(o instanceof DeckAbility){
			if(((DeckAbility) o).getName().equals(this.name)){
				return true;
			}
		}
		return false;
	}
}
