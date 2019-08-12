package com.eric.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * 分散（scatter）从Channel中读取是指在读操作时将读取的数据写入多个buffer中。因此，Channel将从Channel中读取的数据“分散（scatter）”到多个Buffer中。
 * <p>
 * 聚集（gather）写入Channel是指在写操作时将多个buffer的数据写入同一个Channel，因此，Channel 将多个Buffer中的数据“聚集（gather）”后发送到Channel。
 * <p>
 * scatter / gather经常用于需要将传输的数据分开处理的场合，例如传输一个由消息头和消息体组成的消息，你可能会将消息体和消息头分散到不同的buffer中，
 * 这样你可以方便的处理消息头和消息体
 */
public class ScattingAndGather {
    public static void main(String args[]) {
        gather();
        scatter();
    }

    public static void gather() {
        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(10);
        byte[] b1 = {65, 32};
        byte[] b2 = {68, 69};
        header.put(b1);
        body.put(b2);
        ByteBuffer[] buffs = {header, body};
        printByteBuffer(header);
        printByteBuffer(body);
        try {
            String filePath = FileNIOExample.class.getResource("/test.txt").getPath();
            System.out.println(filePath);
            FileOutputStream os = new FileOutputStream(filePath);
            FileChannel channel = os.getChannel();
            //聚集
            channel.write(buffs);
            channel.close();
            os.close();
            //分数
            //channel.read(buffs);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void scatter() {
        ByteBuffer header = ByteBuffer.allocate(10);
        ByteBuffer body = ByteBuffer.allocate(10);
        ByteBuffer[] buffs = {header, body};
        try {
            String filePath = FileNIOExample.class.getResource("/test.txt").getPath();
            System.out.println(filePath);
            FileInputStream os = new FileInputStream(filePath);
            FileChannel channel = os.getChannel();
            //分数
            channel.read(buffs);
            printByteBuffer(header);
            printByteBuffer(body);
            channel.close();
            os.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void printByteBuffer(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.println(byteBuffer.get());
        }
        byteBuffer.compact();
    }
}
