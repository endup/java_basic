import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.*;
import java.util.logging.Handler;


public class MTEchoServer {
	private int port =8080;
	private ServerSocket serverSocket;
	private ExecutorService executorService;//�̳߳�
	private final int POOL_SIZE=5;//����CPUʱ�̳߳��й����̵߳���Ŀ

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		new MTEchoServer().service();
	}
	//���캯��
	public MTEchoServer() throws IOException{
		serverSocket=new ServerSocket(port);
		executorService=Executors.newFixedThreadPool(
				Runtime.getRuntime().availableProcessors()*POOL_SIZE
				);
		System.out.println("����������");
	}

	private void service() {
		// TODO Auto-generated method stub
		while(true){
			Socket socket=null;
			try {
				socket=serverSocket.accept();
				executorService.execute(new ClientComm(socket));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
class ClientComm implements Runnable{

	private Socket socket;
	
	public ClientComm(Socket socket){
		this.socket=socket;
	}
	
	private PrintWriter getWriter(Socket socket) throws IOException{
		OutputStream socketOut=socket.getOutputStream();
		return new PrintWriter(socketOut,true);
	}
	
	private BufferedReader getReader(Socket socket) throws IOException{
		InputStream socketIn=socket.getInputStream();
		return new BufferedReader(new InputStreamReader(socketIn));
	}
	
	public String echo(String msg){
		return "echo:"+msg;
	}
	
	public void run(){
		System.out.println("���յ����ݰ�����"+socket.getInetAddress()+
				":"+socket.getPort());
		
		try {
			
			OutputStream out =socket.getOutputStream();
			PrintStream pour=new PrintStream(out);
			
			out.flush();
				
			pour.print(new Date());
			//System.out.println("hi");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			if(socket!=null)
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
}
