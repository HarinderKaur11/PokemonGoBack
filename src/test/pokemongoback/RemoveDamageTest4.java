package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.ability;
import model.basicPokemon;
import model.pokemonStage;

public class RemoveDamageTest4 {

	@Test
	public void test() {
	
		

		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);
		
		int expected=0;
		pk.addDamage(10);
		pk.removeDamage(20);
		
		int actual=pk.getDamage();
		assertEquals(expected,actual);
	}

}
