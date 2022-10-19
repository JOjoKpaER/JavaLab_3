package lab3_2b;

import java.util.ArrayList;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class Controller {
    
	@FXML
	private Button button;
	
	static public ArrayList<Thread> threadsList = new ArrayList<Thread>();
	
    public void initialize() {
    
    	button.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			Runnable task = () -> {
    				while(true) {
    				/*	try {
    						Thread.currentThread().sleep(1);
    					} catch (InterruptedException exc) {
    						exc.printStackTrace();
    					}	*/
    					}
    			};
    			Thread thread = new Thread(task);
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
    	    
    
