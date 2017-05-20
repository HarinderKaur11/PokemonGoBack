package model;

public class Turn {
	
	private static Player ai;
	private static Player user;
	
	public Turn(Player newAi, Player newuser){
		Turn.ai = newAi;
		Turn.user = newuser;
	}
	
	public static Player getCurrentPlayer(){
		if(!user.getTurn()){
			return ai;
		}
		else{
			return user;
		}
	}
	
	public static void changeTurn(){
		user.setTurn(!user.getTurn());
		ai.setTurn(!ai.getTurn());
	}
	
	public static Player getOpponent(){
		if(user.getTurn()){
			return ai;
		}
		else{
			return user;
		}
	}
}
