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
			//������
			DataInputStream in=new DataInputStream(connection.getInputStream());
			//�����
			DataOutputStream out =new DataOutputStream(connection.getOutputStream());
			String c="12138";
			System.out.println("����Ҫ��ѯ�����id��"+c);
			out.writeUTF(c);
			//������յ������л�����
			ObjectInputStream input=new ObjectInputStream(in);
			try {
				Books books=(Books)input.readObject();
				System.out.println("�ӷ�������ȡ���ķ����л������Ϣ��\n"+
									"\nbookId:"+books.bookId+
									"\n����"+books.name+
									"\n������"+books.type+
									"\n�۸�"+books.cost
									);
				
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("���л���ȡ����ʧ��");
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
