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
	//���캯��
	public udpServerThread(){
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
		String data="����:���\nѧ��:201510224212\n�༶:����152\n";
		byte[] sendbuf=data.getBytes();
		
		sendpacket=new DatagramPacket(sendbuf,sendbuf.length,addr,port);
		try {
			socket.send(sendpacket);
		} catch (IOException e) {
			System.out.println("���ݷ��ͳ���");
		}
	}
	//��������
	public void receivePacket(){
		addr=recpacket.getAddress();
		port=recpacket.getPort();
		System.out.println("�յ�����"+this.addr+":"+this.port+"�˿ڵ����ݰ�");
		
		String msg=new String(recpacket.getData(),recpacket.getOffset(),recpacket.getLength());
		System.out.println("�յ�������ѧ����:"+msg);
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
			System.out.println("�������ݳ���");
		}
	}
	@Override
	public void run() {
		System.out.println("�½��̴߳�������");
		receivePacket();
		sendPacket();
	}
}