package com.eric.io;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

public class BioServer {
    public static void server(){
        ServerSocket serverSocket = null;
        InputStream in = null;
        OutputStream out = null;
        try
        {
            //创建Serversocket，可以设置ip地址、端口和backlog参数
            serverSocket = new ServerSocket(8080,50);
            //设置接收缓冲区的大小
            serverSocket.setReceiveBufferSize(100);
            int recvMsgSize = 0;
            byte[] recvBuf = new byte[1024];
            while(true){
                Socket clntSocket = serverSocket.accept();
                //设置发送缓冲区的大小
                clntSocket.setSendBufferSize(10);
                clntSocket.setReceiveBufferSize(10);
                //连接检测

                clntSocket.setKeepAlive(true);
                clntSocket.setOOBInline();
                clntSocket.setReuseAddress();
                clntSocket.setSoLinger(true,10);
                clntSocket.setSoTimeout();
                clntSocket.setTcpNoDelay();
                clntSocket.setPerformancePreferences();
                clntSocket.setTrafficClass();
                SocketAddress clientAddress = clntSocket.getRemoteSocketAddress();
                System.out.println("Handling client at "+clientAddress);
                in = clntSocket.getInputStream();
                out = clntSocket.getOutputStream();
                while((recvMsgSize=in.read(recvBuf))!=-1){
                    byte[] temp = new byte[recvMsgSize];
                    System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                    System.out.println(new String(temp));
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(serverSocket!=null){
                    serverSocket.close();
                }
                if(in!=null){
                    in.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        server();
    }
}
