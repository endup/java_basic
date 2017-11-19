package com.stream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class udpChatClient implements Runnable{
	private DatagramSocket socket;
	private DatagramPacket sendpacket;
	private DatagramPacket recpacket;
	private InetAddress addr;
	private String sendmsg;
	private int port;
	private Scanner scanner;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		udpChatClient server = new udpChatClient();
		server.chatWithServer();
	}
	//构造函数
	public udpChatClient(){
		byte[] buffer=new byte[1024];
		try {
			addr=InetAddress.getByName("127.0.0.1");
		} catch (UnknownHostException e1) {
			System.out.println("获取服务器ip出错");
		}
		recpacket=new DatagramPacket(buffer,1024);
		scanner=new Scanner(System.in);
		sendmsg="";
		try {
			socket=new DatagramSocket(7001);
			System.out.println("客户机开启");
		} catch (SocketException e) {
			System.out.println("未绑定端口");
		}
	}
	//发送数据
	public void sendPacket(){
		port=7000;
		//发送信息
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
	
	public void chatWithServer(){
		for(;;){
			udpChatClient client = this;
			Thread t=new Thread(client);
			t.start();
			sendPacket();
		}
	}
	
	public void run() {
		receivePacket();
	}
}