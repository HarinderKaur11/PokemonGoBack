package model;

import java.util.List;
import java.util.ArrayList;

public class Item_Trainer extends Trainer {
	String item;
	
public Item_Trainer(String item){}


	public  void ItemCards(){
	List<Object> items=new ArrayList<Object>();
	
	items.add(new Item_Trainer("Potion BKP 106"));
	items.add(new Item_Trainer(" Red Card GEN 71"));
	items.add(new Item_Trainer("Energy Switch GEN 61"));
	items.add(new Item_Trainer("Switch SUM 132"));
	items.add(new Item_Trainer("Poké Ball GEN 67"));
	items.add(new Item_Trainer("Floral Crown GEN 126"));
	
	
	}

}
