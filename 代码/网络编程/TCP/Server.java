package com.stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;

public class Server implements Runnable{
	//判断是否断开连接
	private boolean stop=false;
	private DataOutputStream outData;
	private DataInputStream inData;
	private String info;
	private Student stu;
	
	public Server(Socket serverSocket) throws InterruptedException{
		try {
			System.out.println("新建服务器线程处理连接");
			
			outData=new DataOutputStream(serverSocket.getOutputStream());
			inData=new DataInputStream(serverSocket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("出现异常");
		}
	}
	
	public Student getStudentInfo(int id){
		String name="李健恩";
		String className="网络152";
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
				//原則上是要從數據庫中獲取的,這裡就不那麼麻煩了
				Student stu=getStudentInfo(msg);
				
				//序列化
				ObjectOutputStream out=new ObjectOutputStream(this.outData);
				
				out.writeObject(stu);
				out.close();
				
				//this.outData.writeUTF("姓名:某某\n"+"学号:"+msg+"\n性别:XXX"+"\n电话号码XXXX");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("客户连接结束");
				this.stop=true;
			}
			
		}
	}

	public static void main(String[] args) throws InterruptedException {
		
		ServerSocket server;
		try {
			server = new ServerSocket(5633);
			System.out.println("等待连接");
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


