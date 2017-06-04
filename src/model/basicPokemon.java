package model;

public class basicPokemon extends pokemonStage{
	private String stage = "Basic";
	
	public basicPokemon(){}
	
	public String getStage(){
		return this.stage;
	}

	@Override
	public void evolve(Pokemon basicCard) {
		System.out.println("I cant evolve.");
	}
}
