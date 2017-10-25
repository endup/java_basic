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
	//���캯��
	public udpServer(){
		try {
			socket=new DatagramSocket(7000);
			System.out.println("udp�������˿�:"+socket.getLocalPort());
			
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("δ�󶨶˿�");
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
				System.out.println("�յ�����"+addr+":"+port+"�˿ڵ����ݰ�");
				//��������
				String msg=new String(recpacket.getData(),0,recpacket.getLength());
				System.out.println("�յ���������:"+msg);
				
				//����ѧ����Ϣ
				String data="����:���\nѧ��:201510224212\n�༶:����152\n";
				char[] cArray=data.toCharArray();
				byte[] sendbuf=new byte[cArray.length];
				//���췵�ذ�
				DatagramPacket sendpacket=new DatagramPacket(sendbuf,cArray.length,addr,port);
				socket.send(recpacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.out.println("����δ֪����");
			}
		}
	}

}