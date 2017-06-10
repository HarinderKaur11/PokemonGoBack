package test.pokemongoback;
import controller.GameController;

import static org.junit.Assert.*;

import java.util.ArrayList;
import org.junit.Test;
import org.omg.Messaging.SyncScopeHelper;
import javafx.scene.layout.HBox;
import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;

public class RemoveDamageTest1 {

	
	@Test
	public void test(){
		
	
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);
		
		int expected=0;
		pk.removeDamage(10);
		
		int actual=pk.getDamage();
		assertEquals(expected,actual);
		


}
}
 