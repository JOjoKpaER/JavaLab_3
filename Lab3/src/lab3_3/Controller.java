package lab3_3;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;


public class Controller {
    
	@FXML
	private Button buttonStart;
	@FXML
	private Button buttonPause;
	@FXML
	private ProgressBar progressbar;
	
    private IModel model;
	
    public void initialize() {
        	
        ModelFactory modelFactory = new ModelFactory();
        model = modelFactory.createInstance();
    	
    	buttonStart.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			if (buttonStart.getText().compareTo("start") == 0) {
    				buttonStart.setText("stop");
    				model.calc(new Updatable() {
						
						@Override
						public void update(double value) {
							Platform.runLater(new Runnable() {
								
								@Override
								public void run() {
									progressbar.setProgress(value);
									
								}
							});
							
						}
					});
    			}else {
    				buttonStart.setText("start");
    				model.stop();
    			}
    		}
    	});
    	
    	buttonPause.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			 if (buttonPause.getText().compareTo("pause") == 0) {
    				 buttonPause.setText("resume");
    				 model.pause();
    			 }else {
    				 buttonPause.setText("pause");
    				 model.resume();
    			 }
    			}
    		});
  	
    }
           
}
    	    
    
