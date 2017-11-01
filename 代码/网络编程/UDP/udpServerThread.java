package com.stream;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class udpServerThread implements Runnable{
	private DatagramSocket socket;
	private DatagramPacket sendpacket;
	private DatagramPacket recpacket;
	private InetAddress addr;
	private int port;

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		udpServerThread server = new udpServerThread();
			server.serviceClients();
	}
	//构造函数
	public udpServerThread(){
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
		String data="姓名:李健恩\n学号:201510224212\n班级:网络152\n";
		byte[] sendbuf=data.getBytes();
		
		sendpacket=new DatagramPacket(sendbuf,sendbuf.length,addr,port);
		try {
			socket.send(sendpacket);
		} catch (IOException e) {
			System.out.println("数据发送出错");
		}
	}
	//接收数据
	public void receivePacket(){
		addr=recpacket.getAddress();
		port=recpacket.getPort();
		System.out.println("收到来自"+this.addr+":"+this.port+"端口的数据包");
		
		String msg=new String(recpacket.getData(),recpacket.getOffset(),recpacket.getLength());
		System.out.println("收到的请求学号是:"+msg);
	}
	
	public void serviceClients() throws InterruptedException{
		try {
			while(true){
				byte[] buffer=new byte[1024];
				DatagramPacket recpacket=new DatagramPacket(buffer,1024);;
				socket.receive(recpacket);
				this.recpacket=recpacket;
				Thread t=new Thread(this);
				t.start();
			}
		} catch (IOException e) {
			System.out.println("接收数据出错");
		}
	}
	@Override
	public void run() {
		System.out.println("新建线程处理请求");
		receivePacket();
		sendPacket();
	}
}