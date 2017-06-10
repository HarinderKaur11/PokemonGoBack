package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.Pokemon;
import model.Trainer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;

public class DettachCardType6 {

	@Test
	public void test() {
		ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);
		ability ability = null;
		
		cardItem newCard = new Trainer("Misty's Determination", 22,ability);
		cardItem newCard1 = new Trainer("Clemont",23 ,ability);
		cardItem newCard2 = new Trainer("Potion", 24,ability);
		cardItem newCard3 = new Trainer("Tierno", 25,ability);
		cardItem newCard4 = new Trainer("Pokémon Center Lady", 26,ability);
		
		pk.attachCard(newCard);
		pk.attachCard(newCard1);
		pk.attachCard(newCard2);
		pk.attachCard(newCard3);
		pk.attachCard(newCard4);
		
		
		int expected=0;
		pk.dettachCardType(Trainer.class, 5);
		
		assertEquals(expected,pk.getAttachedCardsCount());
	}

}
