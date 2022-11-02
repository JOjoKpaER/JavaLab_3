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
        c.CalcStop();
    }

    @Override
    public void pause() {
        c.CalcPause();
    }

    @Override
    public void resume() {
        c.CalcResume();
    }

    @Override
    public boolean is_alive() {
        return c.isAlive();
    }
}


class Calc extends Thread{

    Updatable updater;

    boolean pause = false;
    
    public Calc(Updatable updater)
    {
        this.updater = updater;
    }

    void CalcStop(){
    	updater.update(0.0);
        this.stop();
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
        for(int i = 0; i< 1000; i++){
        	synchronized (this) {
        		if (pause) {
					try {
						this.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
        		}
        	}
        		updater.update((double) i / 1000);
        		try {
        			sleep(20);
        		} catch (InterruptedException e) {
        				e.printStackTrace();
                	}

        		}
        	}
    }
