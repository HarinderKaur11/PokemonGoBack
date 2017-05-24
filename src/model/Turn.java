package model;

public class Turn {
	
	private static Turn turn;
	private AIplayer ai;
	private UserPlayer user;
	
	private Turn(){
		
	}
	
	public static Turn getInstance(){
        if(turn == null){
            turn = new Turn();
        }
        return turn;
    }
	
	public void setPlayer(AIplayer newAi, UserPlayer newuser){
		ai = newAi;
		user = newuser;
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
			((UserPlayer) user).setTurn(false);
			((AIplayer) ai).setTurn(true);
		}else{
			((UserPlayer) user).setTurn(true);
			((AIplayer) ai).setTurn(false);			
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
