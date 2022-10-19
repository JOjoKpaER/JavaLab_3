package lab3_1;

public class MyThread extends Thread {

	Object lock;
	Object lockAnother;
			
	public MyThread(Object _lock, Object _lockAnother) {
		lock = _lock;
		lockAnother = _lockAnother;
	}
	
	@Override
	public void run() {
			while(true) {
			System.out.println(Thread.currentThread().getName());
			synchronized (lockAnother) {
				lockAnother.notify();
			}
			synchronized(lock) {
		            try {
		            	this.sleep(100);
		                lock.wait();
		            } catch(InterruptedException e) {
		                e.printStackTrace();
		            }
		        }
			}
	}
	
}
