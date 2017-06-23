package test.stubs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.StageStyle;
import test.stubs.*;

public class GameController {
	@FXML private static GameController controller;
	private UserPlayer user;
	private AIplayer ai;
	

	@FXML private ScrollPane userScrollPane;
	@FXML private HBox userBench;
	@FXML private HBox userHand;
	@FXML private ScrollPane aiScrollPane;
	@FXML private HBox AIBench;
	@FXML private HBox AIHand;
	@FXML private HBox aiActivePokemon;
	@FXML private HBox userActivePokemon;
	@FXML private Button UserEndTurnBtn;
	@FXML private Label userDamage;
	@FXML private Label aiDamage;
	@FXML private Pane gameStage;
	@FXML private BorderPane gameBoard;
	@FXML private VBox btndn_rew,aiDisc_deck,AIReward,UIDisc_deck;
	private GameController(){
	}
	
	public static GameController getInstance(){
        if(controller == null){
            controller = new GameController();
        }
        return controller;
    }
	
 	public void init(){
		
 		boolean[] turn = Turn.getInstance().toss();
		user = new UserPlayer("Flash");
		ai = new AIplayer("Future Flash");
		UserEndTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		    	Turn.getInstance().changeTurn();
		    }
		});
    	addCardsToPanel(user.dealMultipleCards(7),userHand);
    	addCardsToPanel(ai.dealMultipleCards(7), AIHand);
		Turn.getInstance().setPlayer(ai,user);
		
		Debug.message("User turn : "+turn[0]);
		Debug.message("AI Turn: "+turn[1]);
		((UserPlayer) user).setTurn(turn[0]);
		((AIplayer) ai).setTurn(turn[1]);
	}    
    public void addCardsToPanel(cardItem[] cards, HBox panel){
    	FlowPane newCard = null;
    	for(cardItem card : cards){
    		if(card instanceof Pokemon){
    			newCard = createPokemonCard((Pokemon) card, panel);
    		}
    		else {
    			newCard = createCard(card, panel);
    		}
    		panel.getChildren().add(newCard);
    	}
    }
    
    public void removeCard(String id, HBox panel){
    	for(Node node: panel.getChildren()){
    		if(node.getStyleClass().contains(id)){
    			panel.getChildren().remove(node);
    		}
    	}
    }
    
    public void addCardToPanel(cardItem card, HBox panel){
    	FlowPane newCard = null;
    	if(card instanceof Pokemon){
    		newCard = createPokemonCard((Pokemon) card, panel);
    	}
    	else {
    		newCard = createCard(card, panel);
    	}
    	panel.getChildren().add(newCard);
    }
      
    private PokemonCard createPokemonCard(Pokemon pokemon, HBox panel){
    	PokemonCard pokemonCard = new PokemonCard(pokemon, panel);
    	//pokemonCard.setStyle("-fx-background-color: #fff;"+"-fx-border-color: #000;"+"-fx-border-width: 1px;"+
    	//		"-fx-pref-width: 52px;"+ "-fx-pref-height: 70px");
    	pokemonCard.addOptionsActionListener(new EventHandler<ActionEvent>() {
    		@Override 
    		public void handle(ActionEvent e) {
    			pokemonOptions(pokemonCard);
    		}
    	});
    	
    	return pokemonCard;
    }
    
    private void pokemonOptions(PokemonCard pokemonCard)
    {
    	ArrayList<String> optionsList = new ArrayList<String>();
    	HBox tempLoc = pokemonCard.getLocation();
    	
    	if(tempLoc==userHand){
    		if(pokemonCard.getCard().getStage()=="basic"){
    			if(userActivePokemon.getChildren().isEmpty()){
    				optionsList.add("Make active");
    			}
    			else if(userBench.getChildren().size() < 5){
    				optionsList.add("Put on bench");
    			}
    		}
    		else{//if pokemon card is stageone pokemon
    			optionsList.add("Evolve");
    		}
    	}
    	else if(tempLoc==userBench){
    		if(userActivePokemon.getChildren().isEmpty()){
    			optionsList.add("Make active");
    		}
    	}
    	else if(tempLoc==userActivePokemon){
			optionsList.add("View card abilities");
    		optionsList.add("Retreat");
    	}
    	if(optionsList.isEmpty()){
    		
    	}
    	else{
    		DialogBoxHandler dialog = new DialogBoxHandler();
    		String selected = dialog.getDialog(optionsList);
        		
        	if (selected!=null) {
        		switch(selected){
        			case "Make active":
        				pokemonCard.setLayoutX(0);
        		   		pokemonCard.setLayoutY(0);
        		   		pokemonCard.setLocation(userActivePokemon);
        		   		user.setActivePokemon(pokemonCard.getCard());
        		   		((CardsGroup) user.getInhand()).removeCard(user.getActivePokemon());
        				break;
        			case "Put on bench":
        				pokemonCard.setLayoutX(0);
        		   		pokemonCard.setLayoutY(0);
        		   		pokemonCard.setLocation(userBench);
        		   		user.getBench().addCard(pokemonCard.getCard());
        		   		((CardsGroup) user.getInhand()).removeCard(pokemonCard.getCard());
        				break;
        			case "Retreat":
            			ArrayList<String> benchCards = new ArrayList<String>();
    			    	for(Node card : userBench.getChildren()){
    						PokemonCard tempCard = (PokemonCard) card;
    						int id = tempCard.getCard().getID();
    						Debug.message(id);
    						benchCards.add(String.valueOf(id));
    			    	}
    			    	List<String> benchData = Arrays.asList(benchCards.toArray(new String[benchCards.size()]));
    			    	
            			@SuppressWarnings({ "rawtypes", "unchecked" })
    					Dialog benchDialog = new ChoiceDialog(benchData.get(0), benchData);
    					benchDialog.setTitle("Select a bench pokemon to be active");
    					benchDialog.setHeaderText("Select your choice");

    					@SuppressWarnings("unchecked") 
    					Optional<String> benchOp = benchDialog.showAndWait();
    					String select = "cancelled.";
    					if(benchOp.isPresent())
    					{
    						select = benchOp.get();
    						Debug.message(select);
    						PokemonCard benchC = null;
    						for(Node tempNode: userBench.getChildren())
    						{
    							PokemonCard tempCard = (PokemonCard) tempNode;
    							if(tempCard.getCard().getID() == Integer.parseInt(select))
    							{
    								benchC = tempCard;
    								System.out.println(benchC.getCard().getName());
    							}
    						}
//    						user.setActivePokemon(null);
    						user.setActivePokemon(benchC.getCard());
    			    		user.getBench().addCard(pokemonCard.getCard());
    			    		
    			    		//userActivePokemon.getChildren().add(benchC);
    			    		benchC.setLocation(userActivePokemon);
    			    		//userBench.getChildren().add(pokemonCard);
    			    		pokemonCard.setLocation(userBench);
    					}
            			break;
        			case "Evolve":
        				PokemonCard card = evolveoptions(pokemonCard);
        				if(card!=null){
        					card.evolve(pokemonCard.getCard());
        					userHand.getChildren().remove(pokemonCard);
        					user.evolve(pokemonCard.getCard(), pokemonCard.getBasicCard());
        				}
        				else{
        					Debug.message("No pokemon found");
        				}
        				break;
        			case "View card abilities": 
        				Debug.message("Showing card abilities");
            			Dialog<String> abilitiesDialog = new Dialog<>();
            			abilitiesDialog.setTitle("Abilities");
            			abilitiesDialog.setHeaderText("Select any ability to use");
            			ButtonType attackButton = new ButtonType("Attack", ButtonData.OK_DONE);
            			abilitiesDialog.getDialogPane().getButtonTypes().addAll(attackButton, ButtonType.CANCEL);
            			GridPane grid = new GridPane();
            			grid.setHgap(10);
            			grid.setVgap(10);
            			grid.setPadding(new Insets(20, 150, 10, 10));
        		   			    	
            			final ToggleGroup group = new ToggleGroup();
        		   	
            			for(ability a : user.getActivePokemon().getAbilities()){
            				FlowPane temppane = new FlowPane();
            				RadioButton rb = new RadioButton(a.getName());
            				
            				if(a instanceof damageAbility && !(user.getActivePokemon().getAttachedCardsCount()>=((damageAbility) a).getEnergyInfo().size())){
            					rb.setDisable(true);
            				}
            				rb.setUserData(a.getName());
            				rb.setToggleGroup(group);
            				temppane.getChildren().add(rb);
            				if(a instanceof damageAbility)
            					temppane.getChildren().add(new Label(Integer.toString(((damageAbility) a).getDamage())));
            				grid.add(temppane, 0, 0);
            			}
            			abilitiesDialog.getDialogPane().setContent(grid);
            			abilitiesDialog.getResult();
            			abilitiesDialog.setResultConverter(dialogButton -> {
            				if (dialogButton == attackButton) {
            					return group.getSelectedToggle().getUserData().toString();
            				}
            				return null;
            			});
        		   	
        		    	
           				Node aButton = abilitiesDialog.getDialogPane().lookupButton(attackButton);
           				aButton.setDisable(true);
        	    	
           				group.selectedToggleProperty().addListener(new ChangeListener<Toggle>(){
           					public void changed(ObservableValue<? extends Toggle> ov,
        	    	            Toggle old_toggle, Toggle new_toggle) {
        	    	                if (group.getSelectedToggle() != null) {
        	    	                	aButton.setDisable(false);
        	    	                }                
        	    	            }
        	    	    });
        	    	
            			Optional<String> result2 = abilitiesDialog.showAndWait();
            			if(result2.isPresent()){
            				for(ability b: user.getActivePokemon().getAbilities()){
            					if(b.getName()==result2.get()){
            						b.useAbility();
            						aiDamage.setText(Integer.toString(ai.getActivePokemon().getDamage()));
            					}
        		    		}
            			}
        		   		break;
        		}
        	}
    	}
	}

	private GeneralCard createCard(cardItem card, HBox panel){
    	GeneralCard newCard = new GeneralCard(card);
    	
    	if(panel == userHand)
    	{
    		newCard.addOptionsActionListener(new EventHandler<ActionEvent>() {
    			@Override 
    			public void handle(ActionEvent e) {
    				if(card instanceof Energy){
    					EnergyOptions(newCard);
    				}
    				else if(card instanceof Trainer){
    					trainerOptions(newCard);
    				}
    			}
    		});
    	}   	
    	return newCard;
    }
   

	private void trainerOptions(GeneralCard newCard) {
		ButtonType UseCard = new ButtonType("Use Card", ButtonBar.ButtonData.YES);
        ButtonType Cancel = new ButtonType("Cancel", ButtonBar.ButtonData.NO);
        Alert ts = new Alert(Alert.AlertType.INFORMATION,"Card Description",UseCard,Cancel);
        ts.initStyle(StageStyle.UNDECORATED);
        ts.setHeaderText(null);
        ts.setX(475);
        ts.setY(270);
        Optional<ButtonType> result1 = ts.showAndWait();
        if (result1.get().getButtonData() == ButtonBar.ButtonData.YES){
        	((Trainer) newCard.getCard()).getAbility().useAbility();
        }
	}
	
	private void EnergyOptions(GeneralCard newcard){
		Pokemon benchC = this.getHandandBenchPokemonsDialog(user);
		if(benchC!=null){
			userHand.getChildren().remove(newcard);
			((CardsGroup) user.getInhand()).removeCard((Pokemon) newcard.getCard());
			benchC.attachCard(newcard.getCard());
		}
    }
        
    public HBox getBench(Player player){
    	if(player instanceof UserPlayer){
    		return this.userBench;
    	}
    	else{
    		return this.AIBench;
    	}
    	
    }
    public HBox getactivepokemon(Player player){
    	if(player instanceof UserPlayer){
    		return this.userActivePokemon;
    	}
    	else{
    		return this.aiActivePokemon;
    	}
    	}
    public HBox getHand(Player player){
    	if(player instanceof UserPlayer){
        	return this.userHand;
    	}
    	else{
    		return this.AIHand;
    	}
    }

	public void refreshCards(Player player) {
		HBox handpanel = null;
		HBox benchpanel = null;
		Pane activePokemon = null;
		if(player instanceof AIplayer){
			handpanel = AIHand;
			benchpanel = AIBench;
			activePokemon = aiActivePokemon;
			if(user.getActivePokemon()!=null){
				Debug.message("adding damage to label");
				userDamage.setText(Integer.toString(user.getActivePokemon().getDamage()));
			}
			else{
				userDamage.setText("0");
			}
		}else{
			handpanel = userHand;
			benchpanel = userBench;
			activePokemon = userActivePokemon;
			if(ai.getActivePokemon()!=null){
				aiDamage.setText(Integer.toString(ai.getActivePokemon().getDamage()));
			}
			else{
				aiDamage.setText("0");
			}
		}
		if(player.getInhandCards()!=null){
			addCardsToAIPanel(player.getInhandCards(), handpanel);	
		}
		if(player.getBench().getCard()!=null){
			addCardsToAIPanel(player.getBench().getCard(), benchpanel);
		}
		if(player.getActivePokemon()!=null){
			activePokemon.getChildren().clear();
			activePokemon.getChildren().add(createPokemonCard(player.getActivePokemon()));
		}
	}
    
	public void addCardsToAIPanel(cardItem[] cards, HBox panel){
		panel.getChildren().clear();
		addCardsToPanel(cards, panel);
	}
	
	 private FlowPane createPokemonCard(Pokemon pokemon){
	    	FlowPane pokemonCard = new FlowPane();
	    	pokemonCard.getStyleClass().add("pokemonCard");
	    	Label cardID = new Label(Integer.toString(pokemon.getID())+"\t");
	    	cardID.getStyleClass().add("cardID");
	    	Label PokemonStage = new Label(pokemon.getStage()+"\t");
	    	Label PokemonHp = new Label(Integer.toString(pokemon.getHP()));
	    	Label PokemonName = new Label(pokemon.getName());
	    	PokemonName.setPrefWidth(70);
	    	pokemonCard.setMaxWidth(88);
	    	PokemonName.setWrapText(true);

	    	pokemonCard.getChildren().add(cardID);
	    	pokemonCard.getChildren().add(PokemonStage);
	    	pokemonCard.getChildren().add(PokemonHp);
	    	pokemonCard.getChildren().add(PokemonName);
	    	
	    	return pokemonCard;
	 }

	public void dealCard(String player) {
		cardItem newcard;
		if(player=="user"){
			newcard = user.dealCard();
			addCardToPanel(newcard, userHand);
		}
		else {
			newcard = ai.dealCard();
			ai.updateGUI();
		}
		
	}
	
	private PokemonCard evolveoptions(PokemonCard card){
		String basicPname = card.getCard().getBasePokemonName();
		ArrayList<PokemonCard> basicpokemons = new ArrayList<PokemonCard>();
		if(!userActivePokemon.getChildren().isEmpty()){
			PokemonCard tempCard = (PokemonCard) userActivePokemon.getChildren().get(0);
			if(tempCard.getCard().getStage().equals("basic")){
				if(tempCard.getCard().getName().equals(basicPname)){
//					tempCard.evolve(card.getCard());
					basicpokemons.add(tempCard);
				}
			}
		}
		for(Node tempNode : userBench.getChildren()){
			PokemonCard tempCard = (PokemonCard) tempNode;
			if(tempCard.getCard().getStage().equals("basic") && tempCard.getCard().getName().equals(basicPname)){
				basicpokemons.add(tempCard);
			}
		}
		ArrayList<String> optionsList = new ArrayList<String>();
		
		for(PokemonCard tempcard : basicpokemons){
			optionsList.add(Integer.toString(tempcard.getCard().getID()));
		}
		if(!optionsList.isEmpty()){
			DialogBoxHandler dialog = new DialogBoxHandler();
			String selected = dialog.getDialog(optionsList);
		
			if (selected!=null) {
				for(PokemonCard tempCard : basicpokemons){
					if(tempCard.getCard().getID()==(Integer.parseInt(selected))){
						return tempCard;
					}
				}
			}
		}
		return null;
	}
	
	public void knockout(){
		Player player = Turn.getInstance().getOpponent();
		if(player!=null){
		if(player instanceof UserPlayer){
			PokemonCard card = (PokemonCard) userActivePokemon.getChildren().remove(0);
			user.getDiscardPile().addCard(card.getCard());
			if(user.getBench().getCard().length != 0){
				ArrayList<String> optionsList = new ArrayList<String>();
				for(cardItem pCard: user.getBench().getCard()){
					optionsList.add(Integer.toString(pCard.getID()));
				}
				DialogBoxHandler dialog = new DialogBoxHandler();
				String selected = dialog.getDialog(optionsList);
			
				if (selected!=null) {
					for(Node nodeCard : userBench.getChildren()){
						if(((PokemonCard) nodeCard).getCard().getID() == Integer.parseInt(selected)){
							((PokemonCard) nodeCard).setLocation(userActivePokemon);
							Pokemon pokemon = ((PokemonCard) nodeCard).getCard();
							user.setActivePokemon(pokemon);
							user.getBench().removeCard(pokemon);
						}
					}
				}
			}
			else{
				winOrLoss();
			}
		}
		else{
			if(ai.getBench().getCard().length != 0){
				PokemonCard card = (PokemonCard) aiActivePokemon.getChildren().remove(0);
				//Exception in thread "JavaFX Application Thread" java.lang.ClassCastException:
				//javafx.scene.layout.FlowPane cannot be cast to view.PokemonCard
				//error on knockout
				ai.getDiscardPile().addCard(card.getCard());
				ai.activePokemonMove();
			}
			else{
				winOrLoss();
			}
		}
		}
	}
	
	private void winOrLoss(){
		ButtonType NewGame = new ButtonType("New Game", ButtonBar.ButtonData.YES);
        ButtonType Close = new ButtonType("Quit Game", ButtonBar.ButtonData.NO);
		Alert ts = new Alert(Alert.AlertType.INFORMATION,"Game Over",NewGame,Close);
        ts.initStyle(StageStyle.UNDECORATED);
        ts.setHeaderText(null);
        ts.setX(475);
        ts.setY(270);
        Optional<ButtonType> result1 = ts.showAndWait();
        if(result1.isPresent()){
        	if(result1.get().getButtonData() == ButtonBar.ButtonData.YES){
        		userBench.getChildren().clear();
        		userActivePokemon.getChildren().clear();
        		userHand.getChildren().clear();
        		AIBench.getChildren().clear();
        		aiActivePokemon.getChildren().clear();
        		AIHand.getChildren().clear();
        		
        		init();
        	}
        	else{
        		Platform.exit();
        	}
        }
	}
	
	public void makeUIResponsive(){
		Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
		double maxHeight = screenBounds.getHeight();
		double maxWidth = screenBounds.getWidth();
		double handHeight = maxHeight/8;
		double boardAreaHeight = maxHeight-(2*handHeight);
		//double boardWidth = maxWidth - 200.0;
		
		gameStage.setPrefHeight(maxHeight);
		gameStage.setPrefWidth(maxWidth);
		
		AIHand.setPrefHeight(handHeight);
		aiScrollPane.setPrefWidth(maxWidth);
		userHand.setPrefHeight(handHeight);
		userScrollPane.setPrefWidth(maxWidth);
		
		gameBoard.setPrefHeight(boardAreaHeight);
//		gameBoard.setPrefWidth(boaWidth);
		userBench.setPrefHeight(handHeight);
		AIBench.setPrefHeight(handHeight);
		//btndn_rew.setLayoutY(gameBoard.getPrefHeight()/6.5);
    //	btndn_rew.setLayoutX(gameBoard.getPrefWidth()/6);
    	//aiDisc_deck.setLayoutY(gameBoard.getPrefHeight()/5.8);
    	//aiDisc_deck.setLayoutX(gameBoard.getPrefWidth()/6);
    	//UIDisc_deck.setLayoutY(gameBoard.getPrefHeight()/2.5);
    	
	}
	
	public Pokemon getHandandBenchPokemonsDialog(Player player){
		HBox panelActive = null;
		HBox panelBench = null;
		if(player instanceof UserPlayer){
			panelActive = userActivePokemon;
			panelBench = userBench;
		}
		else{
			panelActive = aiActivePokemon;
			panelBench = AIBench;
		}
		ArrayList<String> optionsList = new ArrayList<String>();
    	if(! panelActive.getChildren().isEmpty())
	    {
    		optionsList.add("ActivePokemon");
	    }
    	if(! panelBench.getChildren().isEmpty())
    	{
    		optionsList.add("BenchPokemon");
    	}
    	if(!optionsList.isEmpty()){
    		DialogBoxHandler dBox = new DialogBoxHandler();
    		String selected = dBox.getDialog(optionsList);
    		
    		if (selected!=null) {
    		    if(selected=="ActivePokemon"){
			    	return player.getActivePokemon();
			    }
			    else if(selected=="BenchPokemon")
			    {
			    	return this.getPanelPokemonDialog(player,"bench");
			    }
    		}
    	}
    	return null;
	}
	
	
	public Pokemon getPanelPokemonDialog(Player player,String panelOpt){
		HBox panel = null;
		if(player instanceof UserPlayer){
			if(panelOpt.equals("bench")){
				panel = userBench;
			}
			else{
				panel = userHand;
			}
		}
		else{
			if(panelOpt.equals("bench")){
				panel = AIBench;
			}
			else{
				panel = AIHand;
			}
		}
		ArrayList<String> benchCards = new ArrayList<String>();
    	for(Node card : panel.getChildren()){
			PokemonCard tempCard = (PokemonCard) card;
			int id = tempCard.getCard().getID();
			//Debug.message(id);
			benchCards.add(String.valueOf(id));
    	}
    	DialogBoxHandler dBox = new DialogBoxHandler();
		String select = dBox.getDialog(benchCards);

		Pokemon benchC = null;
		if(select!=null)
		{
			//Debug.message(select);
			for(cardItem pokemon: player.getBench().getCard())
			{
				if(pokemon.getID() == Integer.parseInt(select))
				{
					benchC = (Pokemon) pokemon;
				}
			}
		}
		return benchC;
	}
	
}