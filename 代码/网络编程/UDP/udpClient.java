package com.stream;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class udpClient {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		try {
			String j="";
			while(true){
				System.out.println("������Ҫ��ѯ��ѧ��ѧ��(����'quit'������ѯ)\n");
				j=scanner.next();
				if(j=="quit")break;
				System.out.println("����"+j);
				
				//����udp��socket
				DatagramSocket socket=new DatagramSocket();
				//udp���Ķ���
				String message=""+j;
				byte[] sendbuf=message.getBytes();
				InetAddress addr=InetAddress.getByName("127.0.0.1");
				
				DatagramPacket sendPacket=new DatagramPacket(sendbuf,sendbuf.length,addr,7000);
				System.out.println("�������ݵ�:"+addr);
				
				//��������
				socket.send(sendPacket);
				System.out.println("�ȴ���Ӧ");
				
				//�������ݵ�packet
				byte[] recbuf=new byte[256];
				DatagramPacket receivePacket=new DatagramPacket(recbuf,256);
				//��������
				socket.receive(receivePacket);
				String msg=new String(receivePacket.getData(),receivePacket.getOffset(),receivePacket.getLength());
				
				System.out.print("���յ�����:"+msg);
			}
		} catch (IOException e) {
			System.out.println("����ʧ��");
		}
	}

}
