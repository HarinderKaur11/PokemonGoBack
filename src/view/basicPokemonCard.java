package view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import model.ability;
import model.Pokemon;

public class basicPokemonCard extends FlowPane{
	private Label cardName;
	private Label PokemonStage; 
	private Label PokemonHp;
	private Label cardID;
	private Button button;
	private HBox location;
	
	private Pokemon card;
	
	public basicPokemonCard(Pokemon newCard){
		this.card = newCard;
		init();
	}
		
	public void init(){

    	this.setMaxWidth(88);
		this.getStyleClass().add("pokemonCard");
    	this.cardID = new Label(Integer.toString(card.getID())+"\t");
    	this.cardID.getStyleClass().add("cardID");
    	this.PokemonStage = new Label(card.getStage()+"\t");
    	this.PokemonHp = new Label(Integer.toString(card.getHP()));
    	this.cardName = new Label(card.getName());
    	this.cardName.setPrefWidth(70);
    	this.cardName.setWrapText(true);
    	this.button = new Button();

    	this.setOnMouseEntered(new EventHandler<MouseEvent>(){

    		@Override
    		public void handle(MouseEvent event){
    			String text = new String();
    			Tooltip tttext = new Tooltip();
    			//text.setText(IntoString());
    			ability[] abilities = card.getAbilities();
    			for(int i=0; i<abilities.length;i++){
    				text = text + abilities[i].getName() + "\n" ;
    			}
    			//System.out.println("User bench" + userBench.getChildren().size());
    			tttext.setText(text);
    			button.setTooltip(tttext);
    		}
    	
    	});
    	
    	this.getChildren().add(this.cardID);
    	this.getChildren().add(this.PokemonStage);
    	this.getChildren().add(this.PokemonHp);
    	this.getChildren().add(this.cardName);
    	this.getChildren().add(this.button);    	
    	
	}
	
	public void setLocation(HBox newLocation){
		this.location = newLocation;
		this.location.getChildren().add(this);
	}
	
	public HBox getLocation(){
		return this.location;
	}
	
	public void addOptionsActionListener(EventHandler<ActionEvent> evt){
    		button.setOnAction(evt);
	}

	public Pokemon getCard() {
		return this.card;
	}		
	
}
