package model;

public class AIplayer extends Player {
	
	private String name;
	private int score;
	private cardItem deck;
	
	public AIplayer(String newName) {
		super(newName);
		this.name = newName;
		deck = new Deck();
		((Deck) deck).buildDeck(1);
	}
	
	public String getName() {
		return this.name;
	}

	public int getScore() {
		return this.score;
	}
	
}
