import java.util.Random;


public class thread1 implements Runnable {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thread1 thread = new thread1();
		Thread t=new Thread(thread);
		t.start();
	}

	public void run() {
		// TODO Auto-generated method stub
		Random r=new Random();
		for(int i=0;i<10;i++){
			System.out.println(r.nextInt());	
		}
		
	}

}
