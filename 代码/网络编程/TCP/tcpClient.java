package com.stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		try {
			int j=0;
			while(true){
				System.out.println("������Ҫ��ѯ��ѧ��ѧ��(����886������ѯ)\n");
				j=scanner.nextInt();
				if(j==886)break;
				System.out.println("����"+j);
				Socket clientSocket=new Socket("localhost",5633);
				DataOutputStream outData=new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream inData=new DataInputStream(clientSocket.getInputStream());
				
				outData.writeUTF(""+j);
				
				//�����л�
				ObjectInputStream in=new ObjectInputStream(inData);
				
				try {
					Student stu=(Student)in.readObject();
					System.out.println("hello");
					System.out.println("�W����Ϣ:\n"+"id:"+stu.id+
										"\n����"+stu.name
										+"\n�༉:"+stu.className+
										"\n�Ԓ:"+stu.phone+
										"\n���g:"+stu.age+"\n");
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				//String info=inData.readUTF();
				//System.out.println(info);
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("���ӶϿ�");
		}
	}
}


