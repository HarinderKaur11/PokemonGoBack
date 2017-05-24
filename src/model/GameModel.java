package model;

public class GameModel {
	
	private Player user;
	private Player ai;
	
	public GameModel(){
		user = new UserPlayer("Flash");
		ai = new AIplayer("Future Flash");
	}
	
	public void setTurn(boolean userTurn,boolean aiTurn){
		Turn.getInstance().setPlayer(ai,user);
//		ai.setTurn(userTurn);
//		user.setTurn(aiTurn);
	}
	
	
	
	private void gameInitialize(){
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
		Turn.getInstance().changeTurn();
		Debug.showCard(ai.getActivePokemon());
		Debug.message("Lets attack opponents pokemon");
		ability[] userPokemonAbilities = Turn.getInstance().getCurrentPlayer().getActivePokemon().getAbilities();
		Debug.message("Ability " + userPokemonAbilities[0].getName() + " Current Player "+ Turn.getInstance().getCurrentPlayer().getClass().getName());
		Turn.getInstance().getCurrentPlayer().getActivePokemon().useAbility(userPokemonAbilities[0]);
		Debug.message("Damage status for "+Turn.getInstance().getCurrentPlayer().getClass().getSimpleName()+" active pokemon "+Turn.getInstance().getCurrentPlayer().getActivePokemon().getDamage());
		
	}
}
