import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class tcp_client {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			String hostname="localhost";
			Socket connection = new Socket (hostname,13);
			connection.setSoTimeout(2000);
			//输入流
			DataInputStream in=new DataInputStream(connection.getInputStream());
			//输出流
			DataOutputStream out =new DataOutputStream(connection.getOutputStream());
			String c="12138";
			System.out.println("发送要查询的书的id："+c);
			out.writeUTF(c);
			
			String i = in.readUTF();
			System.out.println(i);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
