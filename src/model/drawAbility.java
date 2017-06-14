package model;

import controller.GameController;

public class drawAbility extends ability {
	private int drawnumber;
	private String newname;
	private String abilitytarget;
	
	public drawAbility(String newname,int drawnumber, String abilitytarget){
		this.drawnumber = drawnumber;
		this.newname = newname;
		this.abilitytarget = abilitytarget;
	}

	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void useAbility() {
		if(abilitytarget.equals(null)){
			abilitytarget="you";
		}
		// TODO Auto-generated method stub
		Player player = (Player) target.getTargetObject(abilitytarget).getTarget();
		//CardsGroup hand = (CardsGroup) player.getInhand();
		CardsGroup deck = (CardsGroup) player.getDeckCard();
		//player.dealMultipleCards(drawnumber);
		//deck.removeCard(newCard);
		GameController.getInstance().addCardsToPanel(player.dealMultipleCards(drawnumber), GameController.getInstance().getHand(player));
	}

}
