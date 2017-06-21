package test.pokemongoback;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Deck;
import model.Pokemon;
import model.cardItem;

public class DeckTest {

	@Test
	public void test() {
		
Deck deck= new Deck(1);

		deck.buildDeck();
		int actual=deck.getGroupCards().size();
<<<<<<< HEAD
		//cardItem[] cards = deck.getCard();
		//Pokemon pokemon = (Pokemon) cards[0];
=======
		cardItem[] cards = deck.getCard();
>>>>>>> master
		int expected=60;
		assertEquals(expected,actual);
		
	}

}
