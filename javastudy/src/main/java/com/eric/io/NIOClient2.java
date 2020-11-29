package com.eric.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class NIOClient2 {
    public static void main(String[] args) {
        try (SocketChannel socketChannel = SocketChannel.open()) {
            SocketAddress socketAddress = new InetSocketAddress("127.0.0.1",8080);
            socketChannel.connect(socketAddress);
            ByteBuffer byteBuffer = ByteBuffer.allocate(20);
            byteBuffer.put((byte) 10);
            byteBuffer.put((byte) 2);
            byteBuffer.put((byte) 2);
            byteBuffer.put((byte) 2);
            byteBuffer.put((byte) 2);
            byteBuffer.put((byte) 2);
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            Thread.sleep(200000);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
