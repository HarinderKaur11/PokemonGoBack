package model;

import java.util.ArrayList;
import java.util.List;

public class supporter_Trainer extends Trainer {
	String Support;
	
	public supporter_Trainer(String trainer){
		this.Support=trainer;
		
	}


		public  void SupporterCards(){
		List<Trainer>scards=new ArrayList<Trainer>();
		
		scards.add(new Item_Trainer("Wally GEN 127"));
		scards.add(new Item_Trainer("Pokémon Fan Club GEN 69"));
		scards.add(new Item_Trainer("Shauna GEN 72"));
		scards.add(new Item_Trainer("Tierno BKP 112"));
		scards.add(new Item_Trainer("Clemont GEN 59"));
		scards.add(new Item_Trainer("Pokémon Center Lady GEN 68"));
		scards.add(new Item_Trainer("Misty's Determination BKP 104"));
		
		}

}
