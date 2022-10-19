package lab3_2a;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class Controller {
    
	@FXML
	private Button button;
	
	static public ArrayList<LoopThread> threadsList = new ArrayList<LoopThread>();
	
    public void initialize() {
    
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			LoopThread thread = new LoopThread();
    			thread.start();
    			threadsList.add(thread);
    	    	}
    	});
    	
    }
    
    @FXML
    public void exitApplication(ActionEvent event) {
    	
    	Platform.exit();
    }
}
    	    
    
