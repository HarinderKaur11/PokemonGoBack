package test.pokemongoback;

import static org.junit.Assert.*;
import org.junit.Test;
import model.Deck;

public class DeckTest {

	@Test
	public void test() {
		
		Deck deck= new Deck();
		deck.buildDeck(1);
		int actual=deck.getGroupCards().size();
		int expected=60;
		assertEquals(expected,actual);
		
	}

}
