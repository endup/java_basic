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
			System.out.println("��������");
			for(;;){
				//�ȴ�����
				Socket connection=server.accept();
				
				System.out.println("���յ����ݰ�����"+connection.getInetAddress()+
						":"+connection.getPort());
				//������
				DataInputStream in=new DataInputStream(connection.getInputStream());
				//�����
				DataOutputStream out =new DataOutputStream(connection.getOutputStream());
				//��������
				while(true){
					//��ȡ����
					String s=in.readUTF();
					int id=Integer.parseInt(s);
					System.out.println("�ͻ���Ҫ��ѯ����idΪ��"+s);
					//���л�ͼ��������Ϣ
					Books books=new Books(id,"������Ȼ","��Ȼ������",15.02);
					ObjectOutputStream output=new ObjectOutputStream(out);
					//��������
					output.writeObject(books);
					out.close();
					//��������
					//out.writeUTF("ͼ�����֣�������Ȼ\nͼ��۸�15��\nͼ�����ࣺ��Ȼ������");
					//connection.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ͻ��������ж�");
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
