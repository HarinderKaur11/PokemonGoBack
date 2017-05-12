package model;

public class Energy implements cardItem {

	
	public String energy;
	
	
	public Energy(String newenergy) {	
		this.energy = newenergy;
	}

	@Override
	public String getName() {
		return energy;
	}

	
}
