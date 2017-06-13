package test.pokemongoback;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;

import controller.GameController;
import javafx.scene.layout.HBox;
import model.CardsGroup;
import model.Pokemon;
import model.Trainer;
import model.cardItem;

public class addCardsToAIPanelTest {
	
	cardItem expected;
	cardItem actual;
	HBox panel;
	@Test
	public void test(){

	cardItem trainercard=new Trainer(32,"Potion", null, null);
	cardItem pokemon1=new Pokemon(80, "Pikachu", null, 0, null);

	ArrayList<cardItem> pokemoncard = new ArrayList<cardItem>();
	pokemoncard.add(null);
	pokemoncard.add(trainercard);
	pokemoncard.add(pokemon1);
	
	GameController.getInstance().addCardsToAIPanel(pokemoncard.toArray(new cardItem[pokemoncard.size()]), panel);
	
	for(int i=0; i<3; i++){
		assertEquals(pokemoncard.get(i),GameController.getInstance());
	}	
		
	}
}
