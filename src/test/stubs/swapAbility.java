package test.stubs;

import controller.GameController;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import model.Turn;
import model.UserPlayer;
import model.ability;
import model.cardItem;
import model.target;

public class swapAbility extends ability{
	private String activepokemon, benchpokemon;
	private UserPlayer user;
	
	public swapAbility(String name,String activepokemon, String benchpokemon){
		this.name = name;
		this.activepokemon = activepokemon;
		this.benchpokemon = benchpokemon;
		
	}
	
	
	public boolean equals(Object o) {
		return false;
	}
	
	public void useAbility() {
		
		/*Player player = (Player) getPlayer(benchpokemon);
		cardItem active = (Pokemon) target.getTargetObject(activepokemon).getTarget();
		Pokemon bench = (Pokemon) target.getTargetObject(benchpokemon).getTarget(); 
		CardsGroup benchcards = player.getBench();
		
		benchcards.addCard(active);
		benchcards.removeCard(bench);
		player.setActivePokemon(bench);
		GameController.getInstance().addCardToPanel(active, GameController.getInstance().getBench(player));
		GameController.getInstance().removeCard(String.valueOf(bench.getID()), GameController.getInstance().getBench(player));
		GameController.getInstance().addCardToPanel(bench,GameController.getInstance().getactivepokemon(player));
	*/}
		
		
	/*public Player getPlayer(String playertarget){
		if(playertarget.equals("yourbench")){
			return Turn.getInstance().getCurrentPlayer();
		}
		else{
			return Turn.getInstance().getOpponent();
		}
	}*/
	}
	

