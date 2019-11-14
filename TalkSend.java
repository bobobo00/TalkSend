package UDP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketException;

public class TalkSend implements Runnable {
	private DatagramSocket client;
	private int port;
	private String toIP;
	private int toPort;
	
	
	public  TalkSend(int port, String toIP, int toPort) {
		try {
			this.client = new DatagramSocket(port);
			this.toIP = toIP;
			this.toPort = toPort;
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		BufferedReader bar=new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			String bos;
			try {
				bos = bar.readLine();
				String str=Thread.currentThread().getName()+":"+bos;
				byte[] datas=str.getBytes();
				DatagramPacket packet=new DatagramPacket(datas,0,datas.length,new InetSocketAddress(this.toIP,this.toPort));
				//·¢ËÍ°ü¹üsend(DatagramPacket)
				client.send(packet);
				if(bos.equals("bye")) {
					break;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.client.close();	
	}

}
