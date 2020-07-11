package com.eric.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


/**
 * 可以实现阻塞和非阻塞的IO
 */
public class NIOServer {
    private static final int BUF_SIZE=1024;
    private static final int PORT = 8080;
    public static void main(String[] args)
    {
        ServerSocketChannel ssc = null;
        try{
            //创建channel
            ssc= ServerSocketChannel.open();
            //给channel对应的socket绑定IP地址,通过bind操作完成底层的bind和listen方法的调用
            //可以设置backlog，也可以不设置backlog，不设置，默认值为50
            ssc.socket().bind(new InetSocketAddress(PORT),100);
            //配置为非阻塞
            ssc.configureBlocking(true);
            //将channel注册到select上面
            SocketChannel socketChannel =  ssc.accept();
            if (socketChannel != null) {
                socketChannel.configureBlocking(true);
                ByteBuffer byteBuffer = ByteBuffer.allocate(BUF_SIZE);
                int readCount = socketChannel.read(byteBuffer);
                byteBuffer.flip();

                System.out.println("readCount:"+readCount);
                System.out.println(byteBuffer.get());
                System.out.println(byteBuffer.toString());

            }
            System.out.println("success");
        }catch(IOException e){
            e.printStackTrace();
        }finally{
            try{
                if(ssc!=null){
                    ssc.close();
                }
            }catch(IOException e){
                e.printStackTrace();
            }
        }
    }
}
