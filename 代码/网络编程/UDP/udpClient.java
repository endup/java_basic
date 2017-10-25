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
				String message="send message "+j;
				char[] cArray=message.toCharArray();
				byte[] sendbuf=new byte[cArray.length];
				InetAddress addr=InetAddress.getByName("127.0.0.1");
				
				DatagramPacket sendPacket=new DatagramPacket(sendbuf,cArray.length,addr,7000);
				System.out.println("�������ݵ�:"+addr);
				
				//��������
				socket.send(sendPacket);
				System.out.println("�ȴ���Ӧ");
				
				//�������ݵ�packet
				byte[] recbuf=new byte[256];
				DatagramPacket receivePacket=new DatagramPacket(recbuf,256);
				//��������
				socket.receive(receivePacket);
				
				System.out.println("���յ�����"+receivePacket.getAddress()+"����:");
				
				//��ȡ�յ�������
				ByteArrayInputStream bin=new ByteArrayInputStream(receivePacket.getData(),0,receivePacket.getLength());
				BufferedReader reader=new BufferedReader(new InputStreamReader(bin));
				
					String line=reader.readLine();
					if(line==null){
						socket.close();
						break;
					}
					else{
						System.out.println("�յ���������:"+line);
					}
				Thread.sleep(1000);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("����ʧ��");
		}
	}

}
