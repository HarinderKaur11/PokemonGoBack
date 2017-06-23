package test.stubs;

public class DeckAbility extends ability {

	private String targetDestination;
	private int amount;
	private String choice;
	
	public DeckAbility(String newName, String newTarget, String newTargetDestination, int newAmount, String newChoice){
		this.name = newName;
		this.abilitytarget = newTarget;
		this.targetDestination = newTargetDestination;
		this.amount = newAmount;
		this.choice = newChoice;
	}
	
	public void useAbility() {
		if(choice!=null){
				this.amount = ((CardsGroup) target.getTargetObject(this.choice).getTarget()).getGroupCards().size();
		}
		for(int i=0;i<amount;i++){
			cardItem nCard = ((CardsGroup) target.getTargetObject(this.choice).getTarget()).removeFirstCard();
			((CardsGroup) this.getTargetLocation(this.targetDestination,this.abilitytarget)).addCard(nCard);
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
