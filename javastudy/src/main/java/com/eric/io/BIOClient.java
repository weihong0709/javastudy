package com.eric.io;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;

public class BIOClient {
    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1",8080);
            InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream();
            out.write("hello server".getBytes());
            out.flush();
            byte[] recvBuf = new byte[120];
            int recvMsgSize = 0;
            while ((recvMsgSize=in.read(recvBuf))!=-1){
                System.out.println("recvMsgSize:"+recvMsgSize);
                byte[] temp = new byte[recvMsgSize];
                System.arraycopy(recvBuf, 0, temp, 0, recvMsgSize);
                System.out.println(new String(temp));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
