package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javafx.animation.KeyFrame;
import javafx.animation.PathTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Labeled;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.CubicCurveTo;
import javafx.scene.shape.MoveTo;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.AIplayer;
import model.CardsGroup;
import model.Debug;
import model.Player;
import model.Pokemon;
import model.Turn;
import model.UserPlayer;
import model.ability;
import model.cardItem;
import model.damageAbility;

public class GameController {
	
	private UserPlayer user;
	private AIplayer ai;
	

	@FXML private ScrollPane userHandScroll;
	@FXML private HBox userBench;
	@FXML private HBox userHand;
	@FXML private ScrollPane AIHandScroll;
	@FXML private HBox AIBench;
	@FXML private HBox AIHand;
	@FXML private Pane aiActivePokemon;
	@FXML private Pane userActivePokemon;
	@FXML private Button UserEndTurnBtn = new Button();
	@FXML private Label userDamage = new Label();
	@FXML private Label aiDamage = new Label();
	
	public GameController(){
		
	}
	
	public void toss(){
		boolean userTurn = true;
        boolean aiTurn = false;
        String dec;
        ButtonType toss = new ButtonType("Toss", ButtonBar.ButtonData.OK_DONE);
        Alert alert = new Alert(Alert.AlertType.INFORMATION,"It's Time for Toss ",toss);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setHeaderText(null);
        alert.setX(475);
        alert.setY(270);
        alert.setHeight(50);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == toss)
        {
            // ... user chose OK
            //head or tail
            Circle circle = new Circle();
            circle.setCenterX(200.0f);
            circle.setCenterY(135.0f);
            //Setting the radius of the circle
            circle.setRadius(25.0f);
            //Setting the color of the circle
            circle.setFill(javafx.scene.paint.Color.BROWN);
            //Setting the stroke width of the circle
            circle.setStrokeWidth(20);
            //Instantiating the path class
            javafx.scene.shape.Path path = new javafx.scene.shape.Path();
            //Creating the MoveTo path element
            MoveTo moveTo = new MoveTo(200, 250);
            //Creating the Cubic curve path element
            CubicCurveTo cubicCurveTo1 = new CubicCurveTo(200, 250, 200, 250, 200, 250);
            CubicCurveTo cubicCurveTo = new CubicCurveTo(200, 40, 200, 40,200, 40);
            //Adding the path elements to Observable list of the Path class
            path.getElements().add(moveTo);
            path.getElements().add(cubicCurveTo);
            path.getElements().add(cubicCurveTo1);
            //Creating a path transition
            PathTransition pathTransition = new PathTransition();
            //Setting the duration of the path transition
            pathTransition.setDuration(Duration.millis(4000));
            //Setting the node for the transition
            pathTransition.setNode(circle);
            //Setting the path
            pathTransition.setPath(path);
            //Setting the cycle count for the transition
            pathTransition.setCycleCount(1);
            //Setting auto reverse value to false
            //pathTransition.setAutoReverse(true);
            //Playing the animation
            pathTransition.play();
            //Creating a Group object
          // Thread.sleep(1000);
            Random random = new Random();
            int number = random.nextInt(2)+1;
            //public String dec;
            ButtonType Head = new ButtonType("Head", ButtonBar.ButtonData.YES);
            ButtonType Tail = new ButtonType("Tail", ButtonBar.ButtonData.NO);
            Alert ts = new Alert(Alert.AlertType.INFORMATION,"Select Your Coin_S",Head,Tail);
            ts.initStyle(StageStyle.UNDECORATED);
            ts.setHeaderText(null);
            ts.setX(475);
            ts.setY(270);
            System.out.print(number+"\n");
            Optional<ButtonType> result1 = ts.showAndWait();
            if ((result1.get().getText().toString() == "Head") && number==1)
            {
                //num2=1;
                dec="Congratulations you won the toss";
                userTurn = true;
                aiTurn = false;
                System.out.print(dec);
            }
            else if ((result1.get().getText().toString()== "Tail") && number==2)
            {
                // num2=2;
                dec="Congratulations you won the toss";
                userTurn = true;
                aiTurn = false;
                System.out.print(dec);
            }
            else
            {
                dec="Ooops!! You can't make it";
                userTurn = false;
                aiTurn = true;
                System.out.print(dec);
            }

            Group root1 = new Group(circle);
            //Creating a scene object
            Scene scene = new Scene(root1, 400, 350);
            //Setting title to the Stage
            Stage pm1 = new Stage();
            pm1.setScene(scene);
            pm1.initStyle(StageStyle.UNDECORATED);
            pm1.setResizable(false);
            pm1.show();
            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(3),new EventHandler<ActionEvent>()
            {
                        @Override
                        public void handle(ActionEvent event)
                        {
                          pm1.hide();
                            ButtonType toss1 = new ButtonType("Let's Play Now", ButtonBar.ButtonData.OK_DONE);
                            Alert alert1 = new Alert(Alert.AlertType.INFORMATION,""+dec, toss1);
                            alert1.initStyle(StageStyle.UNDECORATED);
                            alert1.setHeaderText(null);
                            alert1.setX(475);
                            alert1.setY(270);
                            alert1.show();
                        }
              }));
              timeline.play();
              init(userTurn,aiTurn);
        }
        else
        {
            // ... user chose CANCEL or closed the dialog
        }
	}
	
	public void init(boolean userTurn,boolean aiTurn){
		user = new UserPlayer("Flash");
		ai = new AIplayer("Future Flash",this);
		UserEndTurnBtn.setOnAction(new EventHandler<ActionEvent>() {
		    @Override public void handle(ActionEvent e) {
		        Turn.getInstance().changeTurn();
		    }
		});
    	addCardsToPanel(user.dealMultipleCards(7),userHand);
    	addCardsToPanel(ai.dealMultipleCards(7), AIHand);
		Turn.getInstance().setPlayer(ai,user,this);
		Debug.message("User turn : "+userTurn);
		Debug.message("AI Turn: "+aiTurn);
		((UserPlayer) user).setTurn(userTurn);
		((AIplayer) ai).setTurn(aiTurn);
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
    
    public void ShowCardDetails(Pokemon pokemon,HBox panel){
    	Pane pokemonCard = new Pane();
    	pokemonCard.getStyleClass().add("pokemonCard");
    	Label cardID = new Label(Integer.toString(pokemon.getID()));
    	Label PokemonStage = new Label(pokemon.getStage());
    	Label PokemonHp = new Label(Integer.toString(pokemon.getHP()));
    	Label PokemonName = new Label(pokemon.getName());
    	Label[] DamageAbility = createMultipleLabels(pokemon.getAbilities(),true);
    	Label[] attackDamage = createMultipleLabels(pokemon.getAbilities(),false);
    	Pane attackName = new Pane();
    	Pane attackValues = new Pane();
    	
    	attackName.getChildren().addAll(DamageAbility);
    	attackValues.getChildren().addAll(attackDamage);
    	pokemonCard.getChildren().add(cardID);
    	pokemonCard.getChildren().add(PokemonStage);
    	pokemonCard.getChildren().add(PokemonHp);
    	pokemonCard.getChildren().add(PokemonName);
    	pokemonCard.getChildren().add(attackName);
    	pokemonCard.getChildren().add(attackValues);
    	    	
    	panel.getChildren().add(pokemonCard);
    }
   
    private FlowPane createPokemonCard(Pokemon pokemon, HBox panel){
    	FlowPane pokemonCard = new FlowPane();
    	//pokemonCard.setStyle("-fx-background-color: #fff;"+"-fx-border-color: #000;"+"-fx-border-width: 1px;"+
    	//		"-fx-pref-width: 52px;"+ "-fx-pref-height: 70px");
    	pokemonCard.getStyleClass().add("pokemonCard");
    	Label cardID = new Label(Integer.toString(pokemon.getID())+"\t");
    	cardID.getStyleClass().add("cardID");
    	Label PokemonStage = new Label(pokemon.getStage()+"\t");
    	Label PokemonHp = new Label(Integer.toString(pokemon.getHP()));
    	Label PokemonName = new Label(pokemon.getName());
    	PokemonName.setPrefWidth(70);
    	pokemonCard.setMaxWidth(88);
    	PokemonName.setWrapText(true);
    	
    	Button button = new Button();   	
    	if(panel == userHand || panel == userBench)
    	{
    		button.setOnAction(new EventHandler<ActionEvent>() {
    			@Override 
    			public void handle(ActionEvent e) {
    				ArrayList<String> optionsList = new ArrayList<String>();
    				pokemonOptions(button, optionsList);
    			}
    	
    	});
    		
    		pokemonCard.getChildren().add(button);
    	}
    	
    	pokemonCard.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				String text = new String();
				Tooltip tttext = new Tooltip();
				//text.setText(IntoString());
				ability[] abilities = pokemon.getAbilities();
				for(int i=0; i<abilities.length;i++){
					text = text + abilities[i].getName() + "\n" ;
				}
				//System.out.println("User bench" + userBench.getChildren().size());
				tttext.setText(text);
				button.setTooltip(tttext);
			}
		
		});
    	
    	pokemonCard.getChildren().add(cardID);
    	pokemonCard.getChildren().add(PokemonStage);
    	pokemonCard.getChildren().add(PokemonHp);
    	pokemonCard.getChildren().add(PokemonName);
    	
    	return pokemonCard;
    }
    
    private void pokemonOptions(Button button, ArrayList<String> optionsList)
    {
    	if(userActivePokemon.getChildren().isEmpty())
		{
    		if(button.getParent().getParent()==userHand){
    			optionsList.add("Make active");
    		}
    		else if(button.getParent().getParent()==userBench){
    			if (userActivePokemon.getChildren().isEmpty())
    			{
    				optionsList.add("Make active");
    			}
    		}
    		else {
    			optionsList.add("Retreat");
    		}
		List<String> dialogData = Arrays.asList(optionsList.toArray(new String[optionsList.size()]));

		Dialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
		dialog.setTitle("Available options");
		dialog.setHeaderText("Select your choice");

		Optional<String> result = dialog.showAndWait();
		String selected = "cancelled.";
				
		if (result.isPresent()) {
		    selected = result.get();
		    if(selected=="Make active"){
		    		button.getParent().setLayoutX(0);
		    		button.getParent().setLayoutY(0);
		    		userActivePokemon.getChildren().add(button.getParent());
		    		user.setActivePokemon((Pokemon) searchCardInHand(((Label) button.getParent().lookup(".cardID")).getText().trim()));
		    		((CardsGroup) user.getInhand()).removeCard(user.getActivePokemon());
		    		//Debug.message(((Label) button.getParent().lookup(".cardID")).getText().trim());
		    }
		    else if(selected=="Put on bench"){
		    		button.getParent().setLayoutX(0);
		    		button.getParent().setLayoutY(0);
		    		Pokemon pokemonBench = (Pokemon) searchCardInHand(((Label) button.getParent().lookup(".cardID")).getText().trim());
		    		userBench.getChildren().add(button.getParent());
		    		user.addCardonBench(pokemonBench);
		    		((CardsGroup) user.getInhand()).removeCard(pokemonBench);
		    }
		}
	}
	else
	{
    	if(button.getParent().getParent()==userHand && userBench.getChildren().size() < 5){
			optionsList.add("Put on bench");
		}
		else if(button.getParent().getParent()==userActivePokemon){
			optionsList.add("Retreat");
			optionsList.add("View card abilities");
		}
		List<String> dialogData = Arrays.asList(optionsList.toArray(new String[optionsList.size()]));

		Dialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
		dialog.setTitle("Available options");
		dialog.setHeaderText("Select your choice");

		Optional<String> result = dialog.showAndWait();
		String selected = "cancelled.";
				
		if (result.isPresent()) {
		    selected = result.get();
		    if(selected=="Put on bench"){
		    	if(button.getParent().getParent()==userHand || button.getParent().getParent()==userBench){
		    		button.getParent().setLayoutX(0);
		    		button.getParent().setLayoutY(0);
		    		userBench.getChildren().add(button.getParent());
		    	}
		    	else{
		    		button.getParent().setLayoutX(0);
		    		button.getParent().setLayoutY(0);
		    		AIBench.getChildren().add(button.getParent());
		    	}
		    }
		}
		}
	}

    
    
    private FlowPane createCard(cardItem card, HBox panel){
    	FlowPane newCard = new FlowPane();
    	
    	newCard.getStyleClass().add("card");
    	Label cardID = new Label(Integer.toString(card.getID())+"\t");
    	Label cardName = new Label(card.getName());
    	cardName.setPrefWidth(70);
    	newCard.setMaxWidth(88);
    	cardName.setWrapText(true);
    	
    	Button button = new Button();   	
    	if(panel == userHand || panel == userBench)
    	{
    		button.setOnAction(new EventHandler<ActionEvent>() {
    			@Override 
    			public void handle(ActionEvent e) {
    				ArrayList<String> optionsList = new ArrayList<String>();
    				trainerOptions(button, optionsList);
    			}
    	
    	});
    		
    		newCard.getChildren().add(button);
    	}
    	
    	newCard.setOnMouseEntered(new EventHandler<MouseEvent>(){

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				String text = new String();
				Tooltip tttext = new Tooltip();
				//text.setText(IntoString());
				//ability[] abilities = card.getAbilities();
				//for(int i=0; i<abilities.length;i++){
				//text = text + abilities[i].getName() + "\n" ;
				}
				//tttext.setText(text);
				//button.setTooltip(tttext);
		
		});
    	
    	newCard.getChildren().add(cardID);
    	newCard.getChildren().add(cardName);
    	
    	return newCard;
    }
    
    private void trainerOptions(Button button, ArrayList<String> optionsList) {
		
		
	}
    
    private Label[] createMultipleLabels(ability[] abilities,boolean value){
    	Label[] labels = new Label[abilities.length];
    	for(int i=0; i< abilities.length; i++){
    		if(value){
    			labels[i] = new Label(abilities[i].getName());
    		}
    		else{
    			labels[i] = new Label(Integer.toString(((damageAbility) abilities[i]).getDamage()));
    		}
    	}
    	return labels;
    }
    
    public HBox getUserBench(){
    	return this.userBench;
    }
    public HBox getAIBench(){
    	return this.AIBench;
    }
    public HBox getUserHand(){
    	return this.userHand;
    }
    public HBox getAIHand(){
    	return this.AIHand;
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
		}else{
			handpanel = userHand;
			benchpanel = userBench;
			activePokemon = userActivePokemon;
			if(ai.getActivePokemon()!=null){
				aiDamage.setText(Integer.toString(ai.getActivePokemon().getDamage()));
			}
		}
		addCardsToAIPanel(player.getInhandCards(), handpanel);	
		addCardsToAIPanel(player.getBenchCards(), benchpanel);
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
	
	private cardItem searchCardInHand(String id){
		Debug.message(Integer.valueOf(id));
		return ((CardsGroup) user.getInhand()).getCard(Integer.valueOf(id));
	}
	
}
