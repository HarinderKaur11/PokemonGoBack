package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.*;

public class DeckFileReader {
	private String deck1file = "resources/deck1.txt";
	private String deck2file = "resources/deck2.txt";
	private String cardsfile = "resources/cards.txt";
	private String abilityfile = "resources/abilities.txt";
	private String abilityName, target, destination, drawCards, status, trigger, triggerCond, addAbility, damage, condition, condAbility;
	
	String abilityR[] = new String[74];

	ArrayList<String[]> deck = new ArrayList<String[]>();
	
	public DeckFileReader (int i){
		switch(i){
			case 1:	
				readDeck(deck1file);
				break;
			case 2:
				readDeck(deck2file);
				break;
		}
	}
	
	public void readDeck(String filename){
		
		BufferedReader br = null;
		BufferedReader cr = null;
		BufferedReader ar = null;
		FileReader fr = null;

		try {

			String sCurrentLine;
			String cardLine, abilityLine;
			br = new BufferedReader(new FileReader(filename));
			cr = new BufferedReader(new FileReader(cardsfile));
			ar = new BufferedReader(new FileReader(abilityfile));
			
			String cards[] = new String[100];   //change size
//			br.readLine();
			String Deck1[] = new String[60];
			//String type = new String();
			int i=0,j = 0, k=0;
			
			while ((cardLine = cr.readLine()) != null) {
				
				cards[i] = cardLine;
				i++;
			}
			
			while ((abilityLine = ar.readLine()) != null) {
				
				abilityR[k] = abilityLine;
				k++;
			}

			while ((sCurrentLine = br.readLine()) != null) {
				
				Deck1[j] = cards[Integer.parseInt(sCurrentLine)-1];
			
				String[] deckcard = Deck1[j].split(":");
				
				deck.add(deckcard);
						
				j++;
			}

			
			for(String [] card:deck)
			{
				String PokemonName = card[0];
				//parse cards.txt
				switch(card[1])
				{	
					case "pokemon":
						String carditem = String.join(" ", card);
						//String retreat = carditem.substring(carditem.indexOf("retreat cat"), carditem.indexOf("attack"));
						String ability = carditem.substring(carditem.indexOf("attack"));
						
						ArrayList<String> abilityInfo = new ArrayList<String>();
						
						String ability1, ability2; 
						int index = indexOf("\\d\\s+\\d+", ability);
						//Debug.message(index);
						ability1 = ability.substring(8, index);
						//Debug.message(ability1);
						
						String[] abilityone = ability1.split(",");
						String[] substring11 = abilityone[0].split("\\s+");
						switch(abilityone.length)
						{
							case 1:
								abilityInfo.add((substring11[1]+" "+substring11[2]));//+" "+ abilityR[Integer.parseInt(substring11[3])-1]));
								parseAbilities(abilityR[Integer.parseInt(substring11[3])-1]);
								break;
							case 2:
								String[] substring12 = abilityone[1].split("\\s+");
								abilityInfo.add((substring11[1]+" "+substring11[2]+" "+substring12[1]+" "+substring12[2]+" "+abilityR[Integer.parseInt(substring12[3])-1]));
								break;
						}
						
						if(ability.length() >= index+2)
						{
							ability2 = ability.substring(index+2);
							//Debug.message(ability2);
						
							String[] abilitytwo = ability2.split(",");
							String[] substring21 = abilitytwo[0].split("\\s+");
							switch(abilitytwo.length)
							{
								case 1:
//									Debug.message(substring21[3]);
//									Debug.message(abilityR[Integer.parseInt(substring21[3])-1]);
									abilityInfo.add((substring21[1]+" "+substring21[2]+" "+ abilityR[Integer.parseInt(substring21[3])-1]));
									break;
								case 2:
									String[] substring22 = abilitytwo[1].split("\\s+");
									abilityInfo.add((substring21[1]+" "+substring21[2]+" "+substring22[1]+" "+substring22[2]+" "+ abilityR[Integer.parseInt(substring22[3])-1]));
									break;
							}
						}

//						for(String ab: abilityInfo)
//						{
//							Debug.message(ab);
//						}
						//Pokemon pk = new Pokemon();

						break;
					
					case "trainer":
						
						break;
						
					case "energy":
						break;      
				}
				//switch()
			}
			

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
	}
	
	public ArrayList<String[]> getDeck(){
		return this.deck;
	}
	
	public static void main(String[] arg){
		DeckFileReader deck = new DeckFileReader(1);
		deck.getDeck();
	}
	
	public static int indexOf(String pattern, String s) {
		Pattern patternString = Pattern.compile(pattern);
	    Matcher matcher = patternString.matcher(s);
	    return matcher.find() ? matcher.end() : -1;
	}
	
	public void parseAbilities(String ablty)
	{
		//parse abilities.txt			

			abilityName = ablty.substring(0, ablty.indexOf(":"));
			String abilityElement = ablty.replace(":", " ").substring(ablty.indexOf(":")+1);
			abilityElement = abilityElement.replace("(", " (");
	
			ArrayList<String> sub = new ArrayList<String>();
			for (String a: abilityElement.split(","))
			{
				sub.add(a);
					//Debug.message(a);
			}
			
			for(String a: sub)
			{
				if(a.contains("(") && !a.contains(")"))
				{
					
					a = sub.get(0) + "," + sub.get(1);
					sub.remove(1);
				}
				
				String array[] = a.split(" ");
				getAbility(array);
			
		}
	}
	
	public void getAbility(String[] a)
	{
		String a_join = String.join(" ", a);
//		for(String ab: a)
//			Debug.message(ab);
		switch(a[0])
		{
			case "dam":
				//for(String ab: a)
//					Debug.message(String.join(" ", a));
				if(! a_join.contains("choice"))
				{
					if(! a_join.contains("else"))
					{
						target = a_join.substring(indexOf("target ", a_join), a_join.indexOf(" ", indexOf("target ", a_join)));
						target = target.replace("-", "");
						damage = a_join.substring(indexOf("\\d+", a_join)-2);
//						Debug.message(target);
					}
					else
					{
						//implement contains else condition (choice)
						target = a_join.substring(indexOf("\\d+", a_join)-2, a_join.indexOf("else"));
					}
					
				}
				else
				{
					//contains choice
				}
				break;
			case "cond":
				//for(String ab: a)
//					Debug.message(String.join(" ", a));
//				Debug.message(" ");
				condition = a[1];
				condAbility = a_join.substring(a_join.indexOf(a[1]));
//				Debug.message(condAbility);
				break;
			case "swap":
//				for(String ab: a)
//					Debug.message(ab);
				break;
			case "draw":
//				for(String ab: a)
//					Debug.message(ab);
				if(a.length == 3)
				{
					target=a[1];
					drawCards = a[2];
				}
				else
				{
					drawCards = a[1];
				}
				//Debug.message(drawCards);
				break;
			case "deck":
				int i = 0;
				for(String ab: a)
				{
					//Debug.message(ab);
					if(ab.contains("target") && a[i+1].matches("[a-z]+"))
					{
						target = a[i+1];  //remove hyphen in opponent-active
					}
					if(ab.contains("destination"))
					{
						destination = a[i+1];
						//Debug.message(target);
					}
					drawCards= String.valueOf(indexOf("\\d", ab));
					//Debug.message("deck"+drawCards);
					i++;
				}
				break;
			case "search":
				break;
			case "redamage":
				break;
			case "reenergize":
				break;
			case "applystat":
//				for(String ab: a)
//					Debug.message(ab);
				status = a[2];
				target = a[3].replace("-", "");
				//Debug.message(target);
				break;
			case "heal":
				break;
			case "add":
				target = a[2];
				trigger = a[4];
				triggerCond = a[5];
				addAbility = a_join.substring(a_join.indexOf("(")+1, a_join.indexOf(")"));
				break;
			case "shuffle":
				target = a[2];
				break;
				
		}
	}
}
