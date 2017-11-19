package com.stream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class udpServer{
	private DatagramSocket socket;
	private DatagramPacket sendpacket;
	private DatagramPacket recpacket;
	private InetAddress addr;
	private int port;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		udpServer server = new udpServer();
		server.serviceClients();
	}
	//构造函数
	public udpServer(){
		byte[] buffer=new byte[1024];
		recpacket=new DatagramPacket(buffer,1024);
		try {
			socket=new DatagramSocket(7000);
			System.out.println("udp服务开启端口:"+socket.getLocalPort());
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("未绑定端口");
		}
	}
	//发送数据
	public void sendPacket(){
		//返回学生信息
		String data="我是服务器，我已经收到了你的信息了\n";
		byte[] sendbuf=data.getBytes();
		
		sendpacket=new DatagramPacket(sendbuf,sendbuf.length,addr,port);
		try {
			socket.send(sendpacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("数据发送出错");
		}
	}
	//接收数据
	public void receivePacket(){
		try {
			socket.receive(recpacket);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("接收数据出错");
		}
		addr=recpacket.getAddress();
		port=recpacket.getPort();
		System.out.println("收到来自"+addr+":"+port+"端口的数据包");
		
		String msg=new String(recpacket.getData(),recpacket.getOffset(),recpacket.getLength());
		System.out.println("收到的数据是:"+msg);
	}
	
	public void serviceClients(){
		//这样是最简单的可以重复响应客户端的
		for(;;){
			receivePacket();
			sendPacket();
		}
	}
}