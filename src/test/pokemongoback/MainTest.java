package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Test;

public class MainTest {
		 
			
			Random random = new Random();
			@Test
			public void testToss() {
				
				for(int j=1;j<=100;j++){
				
		          int number = random.nextInt(2)+1;
		         assertTrue(number==1||number==2);
				}
			}

		}

		
		
		

