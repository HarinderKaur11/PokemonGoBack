package model;

public class UserPlayer implements Player {
	
	private String name;
	private int score;
	private cardItem deck;
	
	public UserPlayer(String newName){
		this.name = newName;
		deck = new Deck();
		((Deck) deck).buildDeck(2);
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public int getScore() {
		return this.score;
	}
	
}
