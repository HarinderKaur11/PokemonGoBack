package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

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
			String cardLine;
			br = new BufferedReader(new FileReader(filename));
			cr = new BufferedReader(new FileReader(cardsfile));
			ar = new BufferedReader(new FileReader(abilityfile));
			
			String cards[] = new String[100];   //change size
//			br.readLine();
			String Deck1[] = new String[60];
			//String type = new String();
			int i=0,j = 0;
			
			while ((cardLine = cr.readLine()) != null) {
				
				cards[i] = cardLine;
				i++;
			}
			
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				
				Deck1[j] = cards[Integer.parseInt(sCurrentLine)-1];
				//Debug.message(Deck1[j]);
				String[] deckcard = Deck1[j].split(":");
				//Debug.message(deckcard.length + " " + deckcard[1]);
				//String[] card = deckcard[0];
				deck.add(deckcard);
						
//				switch(deckcard[1]){
//				case "trainer":
//					break;
//				
//				case "energy":
//					break;
//					
//				case "pokemon":
//					break;
//				}
				
				
				//Debug.message(sCurrentLine);
				j++;
				
				
//				if(sCurrentLine.startsWith("##")){
//					type = sCurrentLine.substring(2, 3);
//					//Debug.message(type);
//				}
//				if(!sCurrentLine.startsWith("* ")){
//					continue;
//				}
//				sCurrentLine = sCurrentLine.replace("* ","");
//				String[] cline = sCurrentLine.split(" ", 2);
//				String[] card = new String[cline.length+1];
//				for(int x=0;x<cline.length;x++){
//					card[x] = cline[x];
//				}
//				card[card.length-1] = type;
//				deck.add(card);
//				//Debug.message(card[0]+" "+card[1]+" "+card[2]);
				
			}
			
			for(String [] card:deck){
				if(card[1].equals("pokemon")){
					//Debug.message(card[0]);
				String carditem = String.join(" ", card);
				//String carditem = Arrays.toString(card);
				//Debug.message(carditem);
				String retreat = carditem.substring(carditem.indexOf("retreat cat"), carditem.indexOf("attack"));
				String ability = carditem.substring(carditem.indexOf("attack"));
				String [] abilities = ability.split(",");
				//Debug.message(abilities[0]);
				String [] abilityone = abilities[0].split("\\s+");
				Debug.message(abilityone.length);
				switch (abilities.length) {
				case 2:
					
					if(abilityone.length == 5){
					//String	abilityfirst = abilities[0];
					String abilitysec = abilities[1];	
					}
					break;
				case 3:
					//String [] abilityone = abilities[0].split("//s+");
					if(abilityone.length == 5){
					String	abilityfirst = abilities[0];
					String[] abilitytwo = abilities[1].split("\\s+");
					if(abilitytwo.length==4){
						String abilitysecond = abilities[1];
						//String abilitythird = abilities[2];
					}
					else if (abilitytwo.length == 3){
						String abilitysecond = abilities[1] + abilities[2];
			//Do more code
					}
					}
					break;
				default:
					break;
				}
				Debug.message(ability);
				}        
				
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
}
