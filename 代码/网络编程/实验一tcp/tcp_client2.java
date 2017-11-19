import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.UnknownHostException;


public class tcp_client2 {
	public static void main(String[] args) {
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
			//处理接收到的序列化数据
			ObjectInputStream input=new ObjectInputStream(in);
			try {
				Books books=(Books)input.readObject();
				System.out.println("从服务器读取到的反序列化后的信息：\n"+
									"\nbookId:"+books.bookId+
									"\n书名"+books.name+
									"\n书类型"+books.type+
									"\n价格"+books.cost
									);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("序列化读取数据失败");
			}
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			System.out.print(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
