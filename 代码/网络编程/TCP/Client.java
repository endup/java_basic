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
				System.out.println("请输入要查询的学生学号(输入886结束查询)\n");
				j=scanner.nextInt();
				if(j==886)break;
				System.out.println("输入"+j);
				Socket clientSocket=new Socket("localhost",5633);
				DataOutputStream outData=new DataOutputStream(clientSocket.getOutputStream());
				DataInputStream inData=new DataInputStream(clientSocket.getInputStream());
				
				outData.writeUTF(""+j);
				
				//反序列化
				ObjectInputStream in=new ObjectInputStream(inData);
				
				try {
					Student stu=(Student)in.readObject();
					System.out.println("hello");
					System.out.println("學生信息:\n"+"id:"+stu.id+
										"\n姓名"+stu.name
										+"\n班級:"+stu.className+
										"\n電話:"+stu.phone+
										"\n年齡:"+stu.age+"\n");
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
			System.out.println("连接断开");
		}
	}
}


