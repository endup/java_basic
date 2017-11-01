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
				System.out.println("请输入要查询的学生学号(输入'quit'结束查询)\n");
				j=scanner.next();
				if(j=="quit")break;
				System.out.println("输入"+j);
				
				//构造udp的socket
				DatagramSocket socket=new DatagramSocket();
				//udp报文对象
				String message=""+j;
				byte[] sendbuf=message.getBytes();
				InetAddress addr=InetAddress.getByName("127.0.0.1");
				
				DatagramPacket sendPacket=new DatagramPacket(sendbuf,sendbuf.length,addr,7000);
				System.out.println("发送数据到:"+addr);
				
				//发送数据
				socket.send(sendPacket);
				System.out.println("等待回应");
				
				//接收数据的packet
				byte[] recbuf=new byte[256];
				DatagramPacket receivePacket=new DatagramPacket(recbuf,256);
				//接收数据
				socket.receive(receivePacket);
				String msg=new String(receivePacket.getData(),receivePacket.getOffset(),receivePacket.getLength());
				
				System.out.print("接收到数据:"+msg);
			}
		} catch (IOException e) {
			System.out.println("发送失败");
		}
	}

}
