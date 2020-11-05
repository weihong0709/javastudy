package com.eric.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class NioClient {
    public static void client(){

        SocketChannel socketChannel = null;
        try
        {
            socketChannel = SocketChannel.open();
            // 设置非阻塞方式
            socketChannel.configureBlocking(false);
            // 初始化对象
            Selector selector = Selector.open();
            // 判断连接是否成功
            if (socketChannel.connect(new InetSocketAddress("127.0.0.1",8080))){
                socketChannel.register(selector, SelectionKey.OP_READ);// 注册读操作
                write(socketChannel);
            } else {
                socketChannel.register(selector, SelectionKey.OP_CONNECT);// 注册可连接状态
            }

            while (true){
                try {
                    selector.select(1 * 1000); // 配置等待时间
                    // 获取就绪通道的键集
                    Set<SelectionKey> keys = selector.selectedKeys();
                    SelectionKey selectionKey = null;
                    Iterator<SelectionKey> iterator = keys.iterator();
                    while (iterator.hasNext()){
                        selectionKey = iterator.next();
                        iterator.remove();
                        handleKey(selectionKey,socketChannel,selector);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    break;
                }
            }

        }
        catch (IOException | InterruptedException e)
        {
            e.printStackTrace();
        }
        finally{
            try{
                if(socketChannel!=null){
                    socketChannel.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }

    private static  void handleKey(SelectionKey selectionKey,SocketChannel socketChannel,Selector selector) {
        if (selectionKey.isValid()){
            if(selectionKey.isReadable()){
                ByteBuffer responseBuffer = ByteBuffer.allocate(1024); // 1024个字节
                try {
                    int responseLength = socketChannel.read(responseBuffer); // 读取内容到responseBuffer中
                    // 判断是否读到值
                    if(responseLength > 0){
                        responseBuffer.flip();// 移动position到头部
                        byte[] bytes = new byte[responseBuffer.remaining()];
                        //transfers bytes from this buffer into the given destination array.
                        responseBuffer.get(bytes);
                        String response = new String(bytes,"utf-8");
                        System.out.println("Server response is :" + response);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(selectionKey.isConnectable()){
                try {
                    if (socketChannel.finishConnect()){
                        socketChannel.register(selector, SelectionKey.OP_READ); // 变为读的
                        write(socketChannel);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }


        }

    }

    public static void main(String[] args) {
        client();
    }

    private static void write(SocketChannel socketChannel) throws IOException, InterruptedException {
        ByteBuffer buffer = ByteBuffer.allocate(1024);
        if(socketChannel.finishConnect())
        {
            int i=0;
            while(true)
            {
                TimeUnit.SECONDS.sleep(1);
                String info = "I'm "+i+++"-th information from client";
                buffer.clear();
                buffer.put(info.getBytes());
                buffer.flip();
                while(buffer.hasRemaining()){
                    System.out.println(buffer);
                    socketChannel.write(buffer);
                }
            }
        }
    }
}
