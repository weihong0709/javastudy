package com.eric.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOSelectorServer {
    private static final int BUF_SIZE=1024;
    private static final int PORT = 8080;
    private static final int TIMEOUT = 3000;
    public static void main(String[] args)
    {
        selector();
    }

    public static void handleAccept(SelectionKey key) throws IOException {
        ServerSocketChannel ssChannel = (ServerSocketChannel)key.channel();
        SocketChannel sc = ssChannel.accept();
        sc.configureBlocking(false);
        sc.register(key.selector(), SelectionKey.OP_READ, ByteBuffer.allocateDirect(BUF_SIZE));
    }
    public static void handleRead(SelectionKey key) throws IOException{
        SocketChannel sc = (SocketChannel)key.channel();
        ByteBuffer buf = (ByteBuffer)key.attachment();
        long bytesRead = sc.read(buf);
        while(bytesRead>0){
            buf.flip();
            while(buf.hasRemaining()){
                System.out.print((char)buf.get());
            }
            System.out.println();
            buf.clear();
            bytesRead = sc.read(buf);
        }
        if(bytesRead == -1){
            sc.close();
        }
    }
    public static void handleWrite(SelectionKey key) throws IOException{
        ByteBuffer buf = (ByteBuffer)key.attachment();
        buf.flip();
        SocketChannel sc = (SocketChannel) key.channel();
        while(buf.hasRemaining()){
            //此处有返回值没有处理，此处根据是阻塞IO还是非阻塞IO，返回值的情况不一样
            //阻塞IO的话，写完为止，非阻塞IO为实际写入的字节数
            sc.write(buf);
        }
        buf.compact();
    }

    /**
     * 四类事件，每种都表示该事件就绪，例如连接就绪、写就绪？
     * 1. SelectionKey.OP_CONNECT
     * 2. SelectionKey.OP_ACCEPT
     * 3. SelectionKey.OP_READ
     * 4. SelectionKey.OP_WRITE
     */
    public static void selector() {
        Selector selector = null;
        ServerSocketChannel ssc = null;
        try{
            //根据操作系统不同创建不同的select，select是操作系统内核提供的
            selector = Selector.open();
            //创建channel
            ssc= ServerSocketChannel.open();
            //给channel对应的socket绑定IP地址
            ssc.socket().bind(new InetSocketAddress(PORT));
            //配置为非阻塞
            ssc.configureBlocking(false);
            //将channel注册到select上面
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while(true){
                if(selector.select(TIMEOUT) == 0){
                    System.out.println("==");
                    continue;
                }
                Iterator<SelectionKey> iter = selector.selectedKeys().iterator();
                while(iter.hasNext()){
                    SelectionKey key = iter.next();
                    if(key.isAcceptable()){
                        handleAccept(key);
                    }
                    if(key.isReadable()){
                        handleRead(key);
                    }
                    if(key.isWritable() && key.isValid()){
                        handleWrite(key);
                    }
                    if(key.isConnectable()){
                        System.out.println("isConnectable = true");
                    }
                    iter.remove();
                }
            }
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(selector!=null){
                    selector.close();
                }
                if(ssc!=null){
                    ssc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
