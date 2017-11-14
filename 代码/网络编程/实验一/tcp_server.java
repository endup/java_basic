import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;


public class tcp_server {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ServerSocket server=new ServerSocket(13);
			System.out.println("服务启动");
			for(;;){
				//等待连接
				Socket connection=server.accept();
				
				System.out.println("接收到数据包来自"+connection.getInetAddress()+
						":"+connection.getPort());
				//输入流
				DataInputStream in=new DataInputStream(connection.getInputStream());
				//输出流
				DataOutputStream out =new DataOutputStream(connection.getOutputStream());
				//处理数据
				while(true){
					//读取数据
					String s=in.readUTF();
					int m=Integer.parseInt(s);
					System.out.println("客户想要查询的书id为："+s);
					//发送数据
					out.writeUTF("图书名字：人与自然\n图书价格：15￥\n图书种类：自然，人文");
					//connection.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("客户端连接中断");
		}

	}

}
