package lab3_3;

public class Model implements IModel{
	
	Calc c;

    @Override
    public void calc(Updatable updater) {
        if(c != null)
            this.stop();
        c = new Calc(updater);
        c.start();
    }

    public void stop(){
    	if (c != null)
        c.CalcStop();
    }

    @Override
    public void pause() {
    	if (c != null)
        c.CalcPause();
    }

    @Override
    public void resume() {
    	if (c != null)
        c.CalcResume();
    }

}


class Calc extends Thread{

    Updatable updater;

    private boolean pause = false;
    
    public Calc(Updatable updater)
    {
        this.updater = updater;
    }

    void CalcStop(){
    	updater.update(0.0);
        this.interrupt();
    }

    void CalcPause(){
    	 pause = true;
    }

    void CalcResume(){
    	synchronized (this) {
    		this.notify();
    		pause = false;
    	}
    }

    @Override
    public void run() {
        for(int i = 0; i < 1000; i++){
        	synchronized (this) {
        		if (pause) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						break;
					}
        		}
        	}
        		updater.update(i / 1000.0);
        		try {
        			sleep(20);
        		} catch (InterruptedException e) {
        				break;
                	}

        		}
        	}
    }
