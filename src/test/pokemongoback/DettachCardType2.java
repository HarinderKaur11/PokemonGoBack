package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Energy;
import model.Pokemon;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;

public class DettachCardType2 {

	@Test
	public void test() {
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);
		
		cardItem newCard = new Energy("Lighting", 2);
		cardItem newCard1 = new Energy("Colorless", 3);
		cardItem newCard2 = new Energy("Water", 4);
		cardItem newCard3 = new Energy("Fight", 5);
		cardItem newCard4 = new Energy("Psychic", 6);
		
		pk.attachCard(newCard);
		pk.attachCard(newCard1);
		pk.attachCard(newCard2);
		pk.attachCard(newCard3);
		pk.attachCard(newCard4);
		
		
		int expected=4;
		pk.dettachCardType(Energy.class, 1);
		
		assertEquals(expected,pk.getAttachedCardsCount());
	}

}
