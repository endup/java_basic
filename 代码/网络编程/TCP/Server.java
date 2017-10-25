package com.stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	//�ж��Ƿ�Ͽ�����
	private boolean stop=false;
	private DataOutputStream outData;
	private DataInputStream inData;
	private String info;
	private Student stu;
	
	public Server(Socket serverSocket) throws InterruptedException{
		try {
			System.out.println("�½��������̴߳�������");
			
			outData=new DataOutputStream(serverSocket.getOutputStream());
			inData=new DataInputStream(serverSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("�����쳣");
		}
	}
	
	public Student getStudentInfo(int id){
		String name="���";
		String className="����152";
		String phone="1353318xxxx";
		int age=18;
		stu=new Student(id,name,className,phone,age);
		return stu;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			if(this.stop) break;
			try {
				this.info=this.inData.readUTF();
				int msg=Integer.parseInt(this.info);
				//ԭ�t����Ҫ�Ĕ������Ы@ȡ��,�@�e�Ͳ����N�韩��
				Student stu=getStudentInfo(msg);
				
				//���л�
				ObjectOutputStream out=new ObjectOutputStream(this.outData);
				
				out.writeObject(stu);
				out.close();
				
				//this.outData.writeUTF("����:ĳĳ\n"+"ѧ��:"+msg+"\n�Ա�:XXX"+"\n�绰����XXXX");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("�ͻ����ӽ���");
				this.stop=true;
			}
			
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		ServerSocket server;
		try {
			server = new ServerSocket(5633);
			System.out.println("�ȴ�����");
			while(true){
				Socket serverSocket=server.accept();
				Server s= new Server(serverSocket);
				Thread t=new Thread(s);
				t.start();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class Student implements Serializable{
	int id;
	String name;
	String className;
	String phone;
	int age;
	
	public Student(int id,String name,String className,String phone,int age){
		this.id=id;
		this.name=name;
		this.className=className;
		this.phone=phone;
		this.age=age;
	}
}


