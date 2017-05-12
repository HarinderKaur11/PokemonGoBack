package model;

public class stageOnePokemon extends pokemonStage{
	private String stage = "StageOne";
	private String basicPokemon;
	
	public stageOnePokemon(String newEvolvePokemon){
		this.basicPokemon = newEvolvePokemon;
	}
	
	public String getStage(){
		return this.stage;
	}
	
	public String getBasicPokemon(){
		return this.basicPokemon;
	}
}
