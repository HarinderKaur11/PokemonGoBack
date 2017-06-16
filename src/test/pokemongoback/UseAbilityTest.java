
import model.healingAbility;
import model.pokemonStage;
import model.swapAbility;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import model.AIplayer;
import model.Deenergize;
import model.Energy;
import model.Pokemon;
import model.Reenergize;
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
		player.setActivePokemon(pk);
		  ArrayList<ability> newAbilities=new ArrayList<ability>();
		Pokemon rh = new Pokemon(1, "Raichu", basic, 60, newAbilities);
		//UserPlayer opposite= new UserPlayer("Sam");
		
		//String activepk = "Pikachu";
		String target="Raichu";
		ArrayList<Energy> newEnergyInfo = null;
		String count = null;
		System.out.println(rh.getDamage());
		ability ablt = new damageAbility("Pikachu", 10, newEnergyInfo , target, count);
		ablt.useAbility();
		//pk.useAbility(ablt);
		//rh.addDamage(10);
		
		System.out.println(rh.getDamage());


		
	}
	
	
	  @Test
	  public void test5() {

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


		


	}

	


	  @Test
		
		public void test3() {
			
			// it will check for healing ability

			
			player.setActivePokemon(pk);
			String activepk = "Pikachu";
			System.out.println(pk.getDamage());
			pk.addDamage(20);
			ability ablt = new healingAbility("john", 10, activepk);
			pk.useAbility(ablt);
			System.out.println(pk.getDamage());
		
			
		}
		
		

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

		  cardItem card= new Pokemon(2, "Pikachu", basic, 80, newAbilities);
		  
			player.setActivePokemon(pk);
			
			
		//	Pokemon rh = new Pokemon(1, "Raichu", basic, 60, newAbilities);
			
			String activepk = "Pikachu";
			String target="Raichu";
			System.out.println(pk.totalEnergyRequired());
			ability ablt = new Deenergize("john", target, 1);
			pk.addActiveAbility(ablt);
			pk.useAbility(ablt);
			System.out.println(pk.totalEnergyRequired());
			//ability ablt = new Deenergize("john", target, 10);
			
			//rh.addDamage(10);
			assertEquals(0, pk.totalEnergyRequired());
			ability ablty = new Reenergize("john", target, activepk, 1);
			pk.useAbility(ablty);
			System.out.println(pk.totalEnergyRequired());
			
			assertEquals(1, pk.totalEnergyRequired());
		}



		    UserPlayer player1= new UserPlayer("john");
		    player.setTurn(true);
		    Turn.getInstance().setPlayer(new AIplayer("Me"), player);
		    ArrayList<ability> newAbilities1=new ArrayList<ability>();
			pokemonStage basic2=new basicPokemon();
			Pokemon pk2 = new Pokemon(2, "Pikachu", basic2, 80, newAbilities);			
			player.setActivePokemon(pk2);
			String newEnergy = null;
			cardItem card= new Energy(newEnergy);
		
			System.out.println(pk.getAttachedCardsCount());
		    
			pk.attachCard(card);
			ability ablty = new Deenergize("Deenergize", "youractive","1");
			ablty.useAbility();
			System.out.println(pk.getAttachedCardsCount());
		}
	  

}

