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
	//���캯��
	public udpServer(){
		byte[] buffer=new byte[1024];
		recpacket=new DatagramPacket(buffer,1024);
		try {
			socket=new DatagramSocket(7000);
			System.out.println("udp�������˿�:"+socket.getLocalPort());
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("δ�󶨶˿�");
		}
	}
	//��������
	public void sendPacket(){
		//����ѧ����Ϣ
		String data="���Ƿ����������Ѿ��յ��������Ϣ��\n";
		byte[] sendbuf=data.getBytes();
		
		sendpacket=new DatagramPacket(sendbuf,sendbuf.length,addr,port);
		try {
			socket.send(sendpacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			System.out.println("���ݷ��ͳ���");
		}
	}
	//��������
	public void receivePacket(){
		try {
			socket.receive(recpacket);
		} catch (IOException e) {
			//e.printStackTrace();
			System.out.println("�������ݳ���");
		}
		addr=recpacket.getAddress();
		port=recpacket.getPort();
		System.out.println("�յ�����"+addr+":"+port+"�˿ڵ����ݰ�");
		
		String msg=new String(recpacket.getData(),recpacket.getOffset(),recpacket.getLength());
		System.out.println("�յ���������:"+msg);
	}
	
	public void serviceClients(){
		//��������򵥵Ŀ����ظ���Ӧ�ͻ��˵�
		for(;;){
			receivePacket();
			sendPacket();
		}
	}
}