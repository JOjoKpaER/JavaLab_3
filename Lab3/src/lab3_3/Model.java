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
    
    Object lock = new Object();

    public Calc(Updatable updater)
    {
        this.updater = updater;
    }

    void CalcStop(){
        this.stop();
    }

    void CalcPause(){
    	synchronized (lock) {
    		try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    }

    void CalcResume(){
    	synchronized (lock) {
    		this.notify();
    	}
    }

    @Override
    public void run() {
        for(int i = 0; i< 1000; i++){

            // здесь надо предусмотреть постановку на паузу и остановку
        		updater.update((double) i / 1000);
        		try {
        			sleep(20);
        		} catch (InterruptedException e) {
        				e.printStackTrace();
                	}

        		}
        	}
    }
