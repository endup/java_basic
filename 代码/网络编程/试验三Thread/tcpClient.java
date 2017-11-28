package com.stream;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Scanner;

public class tcpClient {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		Scanner scanner=new Scanner(System.in);
		try {
			String j="";
			while(true){
				System.out.println("请输入相关信息(输入bye退出)\n");
				j=scanner.next();
				Socket clientSocket=new Socket("localhost",8080);
				
				DataOutputStream outData=new DataOutputStream(clientSocket.getOutputStream());
				
				outData.writeUTF(j);
				//接收信息
				InputStream timeStream=clientSocket.getInputStream();
				StringBuffer time=new StringBuffer();
				int c;
				
				while((c=timeStream.read())!=-1)
					time.append((char)c);
				
				String timeString =time.toString().trim();
				System.out.println("It is"+timeString+"at"+"localhost");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("连接断开");
		}
	}
}


