package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DeckFileReader {
	private String deck1file = "resources/deck1.ptcgo.txt";
	private String deck2file = "resources/deck2.ptcgo.txt";
	
	public ArrayList<String[]> getDeck1Cards(){
		return getDeck(deck1file);
	}
	
	public ArrayList<String[]> getDeck2Cards(){
		return getDeck(deck2file);
	}
	
	public ArrayList<String[]> getDeck(String filename){
		
		ArrayList<String[]> deck1 = new ArrayList<String[]>();
		BufferedReader br = null;
		FileReader fr = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(filename));
			br.readLine();
			String type = new String();
			
			while ((sCurrentLine = br.readLine()) != null) {
				
				if(sCurrentLine.startsWith("##")){
					type = sCurrentLine.substring(2, 2);
				}
				if(!sCurrentLine.startsWith("* ")){
					continue;
				}
				sCurrentLine = sCurrentLine.replace("* ","");
				String[] cline = sCurrentLine.split(" ", 2);
				String[] card = new String[cline.length+1];
				for(int x=0;x<cline.length;x++){
					card[x] = cline[x];
				}
				card[card.length-1] = type;
				deck1.add(cline);
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
		return deck1;

	}
	public static void main(String[] arg){
		DeckFileReader deck = new DeckFileReader();
		deck.getDeck2Cards();
	}
}
