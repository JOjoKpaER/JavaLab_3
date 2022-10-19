package lab3_3;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
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
	
	private Object lock = new Object();
	private double progress;
	
	private Thread thread;
				
    public void initialize() {
    	
    	
    	buttonStart.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			if (buttonStart.getText().contains("start")) {
    				buttonStart.setText("stop");
    				thread = new Thread(task);
    				thread.start();
    			}else {
    				buttonStart.setText("start");
    				thread.stop();
    				progress = 0.0;
    			}
    	    }
    	});
    	
    	buttonPause.setOnAction(new EventHandler<ActionEvent>() {
    		@Override
    		public void handle(ActionEvent e) {
    			
    				if (buttonPause.getText().contains("pause")) {
    					buttonPause.setText("resume");
    					haltThread();
    				}else {
    					buttonPause.setText("pause");
    					resumeThread();
    				}
    			}
    		});
  	
    }
    
    protected AnimationTimer at = new AnimationTimer(){
        @Override
        public void handle(long now) {
        	progressbar.setProgress(progress);
        }
    };
    
    private Runnable task = () -> {
    	synchronized (thread) {
    		at.start();
    		for(int i = 0; i < 1000; i++) {
    			progress = i/1000.0;
				try {
					thread.wait(20);
				} catch (InterruptedException exc) {
					exc.printStackTrace();
				}
    		}
		}
	};
	
	private void haltThread() {
		synchronized (thread) {
			try {
				thread.wait();
			} catch (InterruptedException exc) {
				exc.printStackTrace();
			}
		}
	}
	
	private void resumeThread() {
		synchronized (thread) {
		thread.notify();	
		}
	}
    
}
    	    
    
