
public class thread2 extends Thread {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		thread2 t=new thread2();
		t.start();
	}
	
	public void run(){
		for(int i=0;i<10;i++){
			System.out.println(Math.random());
		}
	}
}
