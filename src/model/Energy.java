package model;

public class Energy implements cardItem {

	
	public String energy;
	
	
	public Energy(String energy) {
		super();
		this.energy = energy;
	}


	@Override
	public String getName() {
		
		return energy;
	}

	
}
