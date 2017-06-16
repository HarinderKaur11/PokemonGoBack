package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.AIplayer;
import model.Energy;
import model.Player;
import model.Pokemon;
import model.Turn;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.healingAbility;
import model.pokemonStage;
import model.swapAbility;
import model.Deenergize;
import model.damageAbility;


public class UseAbilityTest {
	
	
	@Test
	public void test1() {
		
		// it will check for damage 
		UserPlayer player= new UserPlayer("john");
	    player.setTurn(true);
	    AIplayer ai = new AIplayer("Me");
	    ((AIplayer) ai).setTurn(false);
	   Turn.getInstance().setPlayer(ai, player);
	    ArrayList<ability> newAbilities=new ArrayList<ability>();
		pokemonStage basic=new basicPokemon();
		Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
		player.setActivePokemon(pk);
		ArrayList<Energy> newEnergyInfo=null;
		String count=null;
		
		System.out.println(pk.getDamage());
		pk.addDamage(20);
		ability ablty = new damageAbility("damage",10,newEnergyInfo, "youractive",count);
		ablty.useAbility();
		System.out.println(pk.getDamage());
		
		
		
	}

	
	
	  @Test
	
	public void test2() {
		
		// it will check for swap 
		
		   UserPlayer player= new UserPlayer("john");
		   // player.setTurn(true);
		   // Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities=new ArrayList<ability>();
			pokemonStage basic=new basicPokemon();
			Pokemon pk1 = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
			player.setActivePokemon(pk1);
			//Pokemon pk2 = new Pokemon(3, "Raichu", basic, 60, newAbilities);
		//	cardItem pk2= new Pokemon(3, "Raichu", basic, 60, newAbilities);
			player.getBench().addCard(new Pokemon(3, "Raichu", basic, 60, newAbilities));;
			
			System.out.println("333"+player.getActivePokemon().getName());
			for(cardItem card: player.getBench().getCard()){
				System.out.println("a" + card.getName());
				
			}
			//System.out.println("444"+player.getBench().getName());
			ability ablt3 = new swapAbility("Swap","youractive" , "yourbench");
			ablt3.useAbility();
			System.out.println("111"+player.getActivePokemon().getName());
		
		System.out.println("222"+player.getBench().getBasicPokemonCard().getName());
			
	  }


		
	

	  @Test
		
		public void test3() {
			
			// it will check for healing ability
		    UserPlayer player= new UserPlayer("john");
		    player.setTurn(true);
		    Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities=new ArrayList<ability>();
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
			player.setActivePokemon(pk);
			
			
		    //System.out.println(pk.getDamage());
			pk.addDamage(20);
			ability ablt = new healingAbility("Heal", 10, "youractive");
			ablt.useAbility();
			//System.out.println(pk.getDamage());
			assertEquals(10, pk.getDamage());
			
		}



	
		
		
	  @Test
		
		public void test4() {
			
			// it will check for deenergize and reenergize 
		    UserPlayer player= new UserPlayer("john");
		    player.setTurn(true);
		    Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities=new ArrayList<ability>();
			pokemonStage basic=new basicPokemon();
			Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);			
			player.setActivePokemon(pk);
			String newEnergy = null;
			cardItem card= new Energy(newEnergy);
		
			System.out.println(pk.getAttachedCardsCount());
		    
			pk.attachCard(card);
			ability ablt = new Deenergize("Deenergize", "youractive","1");
			ablt.useAbility();
			System.out.println(pk.getAttachedCardsCount());
		}
	  

}