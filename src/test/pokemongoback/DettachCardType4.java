package test.pokemongoback;

import static org.junit.Assert.*;
import java.util.ArrayList;
import org.junit.Test;
import model.Pokemon;
import model.Trainer;
import model.ability;
import model.basicPokemon;
import model.pokemonStage;

public class DettachCardType4 {

	@Test
	public void test() {
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);
		
		
		
		int expected=0;
		pk.dettachCardType(Trainer.class, 1);
		
		assertEquals(expected,pk.getAttachedCardsCount());
	}

}
