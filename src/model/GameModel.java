package model;

public class GameModel {
	
	private Player user;
	private Player ai;
	
	public GameModel(){
		user = new UserPlayer("Flash");
		ai = new AIplayer("Future Flash");
		Turn turn = new Turn(ai,user);
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
		cardItem inhand = user.getPokemonFromHand();
		user.setActivePokemon(((Pokemon) inhand));
		Debug.showCard(user.getActivePokemon());
		Debug.message("Opponent Choose Active Pokemon");
		inhand = ai.getPokemonFromHand();
		ai.setActivePokemon(((Pokemon) inhand));
		Turn.changeTurn();
		Debug.showCard(ai.getActivePokemon());
		Debug.message("Lets attack opponents pokemon");
		ability[] userPokemonAbilities = user.getActivePokemon().getAbilities();
		Turn.getCurrentPlayer().getActivePokemon().useAbility(userPokemonAbilities[0]);
		Debug.message("Damage status for "+Turn.getOpponent().getClass().getSimpleName()+" active pokemon "+Turn.getOpponent().getActivePokemon().getDamage());
		
	}
	
	public static void main(String arg[]){
		GameModel startGame = new GameModel();
	}
}
