package view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;

public class DialogBoxHandler {
	
	@SuppressWarnings("unchecked")
	public Optional<String> getDialog(ArrayList<String> optionsList){
		
		List<String> dialogData = Arrays.asList(optionsList.toArray(new String[optionsList.size()]));
		@SuppressWarnings("rawtypes")
		Dialog dialog = new ChoiceDialog(dialogData.get(0), dialogData);
		dialog.setTitle("Available options");
		dialog.setHeaderText("Select your choice");

		return dialog.showAndWait();				
	}
}
