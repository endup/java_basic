import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;


public class tcp5_1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i=1;i<1024;i++){
			try {
				Socket s=new Socket("localhost",i);
				System.out.println("�������ṩtcp����Ķ˿ں�:"+i);
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				System.err.println(e);
				break;
			} catch (IOException e) {
				
			}
			
		}
	}

}
