package lab3_1;

public class Main {

	public static void main(String[] args){
	  
		Object lock1 = new Object();
		Object lock2 = new Object();
		
		MyThread thread1 = new MyThread(lock1, lock2);
		thread1.setName("thread1");
		MyThread thread2 = new MyThread(lock2, lock1);
		thread2.setName("thread2");
		
		thread1.start();
		thread2.start();
	}
}
