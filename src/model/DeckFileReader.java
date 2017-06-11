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
			String abilityR[] = new String[74];
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

			//parse abilities.txt
			for(String ablty: abilityR)
			{
				String abilityElement = ablty.replace(":", " ").substring(ablty.indexOf(":")+1);
				
//				Debug.message(abilityElement);
				
				for(String a: abilityElement.split(","))
				{
					String array[] = a.split(" ");
//					int index = abilityElement.indexOf(",");
//					getAbilityItem(a.substring(index+1, abilityElement.indexOf(" ")));
					getAbilityItem(array[0]);
				}
				
			}
			
			for(String [] card:deck)
			{
				//parse cards.txt
				switch(card[1])
				{	
					case "pokemon":
						String carditem = String.join(" ", card);
						//String retreat = carditem.substring(carditem.indexOf("retreat cat"), carditem.indexOf("attack"));
						String ability = carditem.substring(carditem.indexOf("attack"));
						
						ArrayList<String> abilityInfo = new ArrayList<String>();
						
						String ability1, ability2; 
						int index = indexOf("\\d\\s+\\d", ability);
						
						ability1 = ability.substring(8, index+1);
						//Debug.message(ability1);
						
						String[] abilityone = ability1.split(",");
						String[] substring11 = abilityone[0].split("\\s+");
						switch(abilityone.length)
						{
							case 1:
								abilityInfo.add((substring11[1]+" "+substring11[2]+" "+ abilityR[Integer.parseInt(substring11[3])-1]));
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
									abilityInfo.add((substring21[1]+" "+substring21[2]+" "+substring22[1]+" "+substring22[2]+" "+abilityR[Integer.parseInt(substring11[3])-1]));
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
	
	public void getAbilityItem(String a)
	{
		Debug.message(a);
		switch(a)
		{
			case "dam":
				break;
			case "cond":
				break;
			case "swap":
				break;
			case "draw":
				break;
			case "deck":
				break;
			case "search":
				break;
			case "redamage":
				break;
			case "reenergize":
				break;
			case "applystat":
				break;
			case "heal":
				break;
			case "add":
				break;
				
		}
	}
}
