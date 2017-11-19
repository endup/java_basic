package com.stream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.nio.BufferOverflowException;
import java.util.Scanner;

public class udpChatServer implements Runnable{
	private DatagramSocket socket;
	private DatagramPacket sendpacket;
	private DatagramPacket recpacket;
	private InetAddress addr;
	private String sendmsg;
	private int port;
	private Scanner scanner;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		udpChatServer server = new udpChatServer();
		server.serviceClients();
	}
	//构造函数
	public udpChatServer(){
		byte[] buffer=new byte[1024];
		recpacket=new DatagramPacket(buffer,1024);
		scanner=new Scanner(System.in);
		sendmsg="";
		try {
			addr=InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e1) {
			System.out.println("获取对方ip出错");
		}
		try {
			socket=new DatagramSocket(7000);
			System.out.println("udp服务开启端口:"+socket.getLocalPort());
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("未绑定端口");
		}
	}
	//发送数据
	public void sendPacket(){
		//发送信息
		port=7001;
		sendmsg=scanner.next();
		
		byte[] sendbuf=sendmsg.getBytes();
		
		sendpacket=new DatagramPacket(sendbuf,sendbuf.length,addr,port);
		try {
			socket.send(sendpacket);
			System.out.println("<<<<<"+sendmsg);
		} catch (IOException e) {
			System.out.println("数据发送出错");
		}
	}
	//接收数据
	public void receivePacket(){
		try {
			socket.receive(recpacket);
		} catch (IOException e) {
			System.out.println("接收数据出错");
		}
		
		addr=recpacket.getAddress();
		port=recpacket.getPort();
		
		String msg=new String(recpacket.getData(),recpacket.getOffset(),recpacket.getLength());
		System.out.println(">>>>>"+addr+":"+port+":"+msg);
	}
	
	public void serviceClients(){
		while(true){
			udpChatServer server=this;
			Thread t=new Thread(server);
			t.start();
			sendPacket();
		}
	}
	public void run(){
		receivePacket();
	}
}