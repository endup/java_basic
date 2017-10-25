package com.stream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class udpServer {
	private DatagramSocket socket;
	private Student stu;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		udpServer server = new udpServer();
		server.serviceClients();
	}
	//构造函数
	public udpServer(){
		try {
			socket=new DatagramSocket(7000);
			System.out.println("udp服务开启端口:"+socket.getLocalPort());
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("未绑定端口");
		}
	}
	
	public void serviceClients(){
		byte[] buffer=new byte[4096];
		for(;;){
			DatagramPacket recpacket=new DatagramPacket(buffer,4096);
			try {
				socket.receive(recpacket);
				InetAddress addr=recpacket.getAddress();
				int port=recpacket.getPort();
				System.out.println("收到来自"+addr+":"+port+"端口的数据包");
				//接收数据
				String msg=new String(recpacket.getData(),0,recpacket.getLength());
				System.out.println("收到的请求是:"+msg);
				
				//返回学生信息
				String data="姓名:李健恩\n学号:201510224212\n班级:网络152\n";
				char[] cArray=data.toCharArray();
				byte[] sendbuf=new byte[cArray.length];
				//构造返回包
				DatagramPacket sendpacket=new DatagramPacket(sendbuf,cArray.length,addr,port);
				socket.send(recpacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("出现未知错误");
			}
		}
	}

}