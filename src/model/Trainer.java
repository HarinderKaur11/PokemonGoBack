package model;

public class Trainer implements cardItem{

	private String cardName;
	private int id;
	
	public Trainer(String name,int id){
		this.cardName = name;
		this.id = id;
	}
	
	@Override
	public String getName() {
		return this.cardName;
	}
	
	public int getID(){
		return this.id;
	}

}
