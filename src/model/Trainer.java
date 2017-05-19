package model;

public class Trainer implements cardItem{

	private String cardName;
	
	public Trainer(String name){
		this.cardName = name;
	}
	
	@Override
	public String getName() {
		return this.cardName;
	}

}
