package test.pokemongoback;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Deck;
import test.stubs.Pokemon;
import test.stubs.cardItem;

public class DeckTest {

	@Test
	public void test() {
		
		Deck deck= new Deck(1);

		deck.buildDeck();
		int actual=deck.getGroupCards().size();

		cardItem[] cards = deck.getCard();
		int expected=60;
		assertEquals(expected,actual);
		
	}

}
