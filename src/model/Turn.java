package model;

import controller.GameController;

public class Turn {
	
	private static Turn turn;
	private AIplayer ai;
	private UserPlayer user;
	private GameController controller;
	
	private Turn(){
		
	}
	
	public static Turn getInstance(){
        if(turn == null){
            turn = new Turn();
        }
        return turn;
    }
	
	public void setPlayer(AIplayer newAi, UserPlayer newuser, GameController newControl){
		ai = newAi;
		user = newuser;
		this.controller = newControl;
	}
	
	public Player getCurrentPlayer(){
		if(!user.getTurn()){
			return ai;
		}
		else{
			return user;
		}
	}
	
	public void changeTurn(){
		if(user.getTurn()){
			user.setTurn(false);
			controller.dealCard("ai");
			ai.setTurn(true);
		}else{
			user.setTurn(true);
			controller.dealCard("user");
			ai.setTurn(false);			
		}
	}
	
	public Player getOpponent(){
		if(user.getTurn()){
			return ai;
		}
		else{
			return user;
		}
	}
}
