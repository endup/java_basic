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
					int m=Integer.parseInt(s);
					System.out.println("�ͻ���Ҫ��ѯ����idΪ��"+s);
					//��������
					out.writeUTF("ͼ�����֣�������Ȼ\nͼ��۸�15��\nͼ�����ࣺ��Ȼ������");
					//connection.close();
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("�ͻ��������ж�");
		}

	}

}
