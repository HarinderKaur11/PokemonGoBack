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
		ai.setTurn(true);
		user.setTurn(false);
		//Testing
		Debug.message("AI cards inhand");
		Debug.showCard(ai.dealMultipleCards(7));
		Debug.message("User cards inhand");
		Debug.showCard(user.dealMultipleCards(7));
		Debug.message("User Choose Active Pokemon");
		cardItem[] inhand = user.getInhandCards();
		user.setActivePokemon(((Pokemon) inhand[1]));
		Debug.showCard(user.getActivePokemon());
		Debug.message("Opponent Choose Active Pokemon");
		inhand = ai.getInhandCards();
		ai.setActivePokemon(((Pokemon) inhand[1]));
		changeTurn();
		Debug.showCard(ai.getActivePokemon());
		Debug.message("Lets attack opponents pokemon");
		ability[] userPokemonAbilities = user.getActivePokemon().getAbilities();
		userPokemonAbilities[0].useAbility(this.getOpponent());
		Debug.message("Damage status for "+this.getOpponent().getClass().getSimpleName()+" active pokemon "+this.getOpponent().getActivePokemon().getDamage());
		
	}
	
	public void changeTurn(){
		user.setTurn(!user.getTurn());
		ai.setTurn(!ai.getTurn());
	}
	
	public Player getOpponent(){
		if(user.getTurn()){
			return ai;
		}
		else{
			return user;
		}
	}
	
	public static void main(String arg[]){
		GameModel startGame = new GameModel();
	}
}
