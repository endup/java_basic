import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class tcp5_4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Socket daytime;
		try {
			String hostname="localhost";
			daytime = new Socket (hostname,13);
			daytime.setSoTimeout(2000);
			InputStream timeStream=daytime.getInputStream();
			StringBuffer time=new StringBuffer();
			int c;
			
			while((c=timeStream.read())!=-1)
				time.append((char)c);
			String timeString =time.toString().trim();
			System.out.println("It is"+timeString+"at"+hostname);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
