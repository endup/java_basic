import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class tcp_server2 {
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
					int id=Integer.parseInt(s);
					System.out.println("客户想要查询的书id为："+s);
					//序列化图书数据信息
					Books books=new Books(id,"人与自然","自然，人文",15.02);
					ObjectOutputStream output=new ObjectOutputStream(out);
					//发送数据
					output.writeObject(books);
					out.close();
					//发送数据
					//out.writeUTF("图书名字：人与自然\n图书价格：15￥\n图书种类：自然，人文");
					//connection.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("客户端连接中断");
		}
	}
}

class Books implements Serializable{
	int bookId;
	String name;
	String type;
	double cost;
	public Books(int bookId,String name,String type,double cost){
		this.bookId=bookId;
		this.name=name;
		this.type=type;
		this.cost=cost;
	}
}
