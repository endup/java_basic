import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class tcp5_10 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket server=new ServerSocket(13);
			System.out.println("��������");
			for(;;){
				Socket connection=server.accept();
				System.out.println("���յ����ݰ�����"+connection.getInetAddress()+
						":"+connection.getPort());
				OutputStream out =connection.getOutputStream();
				PrintStream pour=new PrintStream(out);
				pour.print(new Date());
				out.flush();
				connection.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		}
		
	}

}
