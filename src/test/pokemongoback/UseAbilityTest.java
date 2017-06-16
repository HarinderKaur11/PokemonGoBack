package test.pokemongoback;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;
import model.Energy;
import model.Pokemon;
import model.UserPlayer;
import model.ability;
import model.basicPokemon;
import model.cardItem;
import model.pokemonStage;
import model.swapAbility;
import test.stubs.Deenergize;
import test.stubs.Reenergize;
import test.stubs.damageAbility;
import test.stubs.healingAbility;

public class UseAbilityTest {
	ArrayList<ability> newAbilities=new ArrayList<ability>();
	pokemonStage basic=new basicPokemon();
	Pokemon pk = new Pokemon(2, "Pikachu", basic, 80, newAbilities);
	UserPlayer player= new UserPlayer("john");
	
/*
	@Test
	public void test1() {
		
		// it will check for damage 
		
		
		player.setActivePokemon(pk);
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
	
	public void test2() {
		
		// it will check for swap 
		
		player.setActivePokemon(pk);
		Pokemon rh = new Pokemon(1, "Raichu", basic, 60, newAbilities);
		//UserPlayer opposite= new UserPlayer("Sam");
		
		String activepk = "Pikachu";
		String target="Raichu";
		
		
		System.out.println(pk.getBasePokemonName());
		ability ablt = new swapAbility("john", target, activepk);
		pk.useAbility(ablt);
		//rh.addDamage(10);
		
		System.out.println(pk.getBasePokemonName());


		
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
		
		*/
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

}
