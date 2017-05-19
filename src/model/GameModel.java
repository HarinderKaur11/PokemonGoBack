package model;

public class GameModel {
	
	private Player user;
	private Player ai;
	
	public GameModel(){
		user = new UserPlayer("Flash");
		ai = new AIplayer("Future Flash");
		gameInitialize();
	}
	
	private void gameInitialize(){
		Debug.showCards(ai.dealMultipleCards(7));
		Debug.showCards(user.dealMultipleCards(7));
	}
	
	public static void main(String arg[]){
		GameModel startGame = new GameModel();
	}
}
