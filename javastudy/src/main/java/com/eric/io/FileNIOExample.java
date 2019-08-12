package com.eric.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class FileNIOExample {
    /**
     * 传统的文件IO方式
     */
    public static void method2() {
        InputStream in = null;
        try {
            InputStream inputStream = FileNIOExample.class.getResourceAsStream("/test.txt");
            in = new BufferedInputStream(inputStream);
            byte[] buf = new byte[1024];
            int bytesRead = in.read(buf);
            while (bytesRead != -1) {
                for (int i = 0; i < bytesRead; i++)
                    System.out.print((char) buf[i]);
                bytesRead = in.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * NIO方式
     * FileChannel不能配置为非阻塞模式，所以不能跟select一起使用
     */
    public static void method1() {
        RandomAccessFile aFile = null;
        try {
            String filePath = FileNIOExample.class.getResource("/test.txt").getPath();
            aFile = new RandomAccessFile(filePath, "rw");
            FileChannel fileChannel = aFile.getChannel();
            ByteBuffer buf = ByteBuffer.allocate(1024);
            int bytesRead = fileChannel.read(buf);
            System.out.println(bytesRead);
            while (bytesRead != -1) {
                buf.flip();
                while (buf.hasRemaining()) {
                    System.out.print((char) buf.get());
                }
                buf.compact();
                bytesRead = fileChannel.read(buf);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 普通读文件的方式
     */
    public static void method4() {
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try {
            aFile = new RandomAccessFile("/Users/zte/Downloads/jdk-11.0.4_osx-x64_bin.dmg", "rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            ByteBuffer buff = ByteBuffer.allocate((int) aFile.length());
            buff.clear();
            fc.read(buff);
            //System.out.println((char)buff.get((int)(aFile.length()/2-1)));
            //System.out.println((char)buff.get((int)(aFile.length()/2)));
            //System.out.println((char)buff.get((int)(aFile.length()/2)+1));
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 采用内存映射文件的方式读取文件
     * MappedByteBuffer方式
     * MappedByteBuffer有资源释放的问题：被MappedByteBuffer打开的文件只有在垃圾收集时才会被关闭，而这个点是不确定的。
     * 在Javadoc中这里描述：A mapped byte buffer and the file mapping that it represents remian valid until the buffer itself is garbage-collected
     */
    public static void method3() {
        RandomAccessFile aFile = null;
        FileChannel fc = null;
        try {
            aFile = new RandomAccessFile("/Users/zte/Downloads/jdk-11.0.4_osx-x64_bin.dmg", "rw");
            fc = aFile.getChannel();
            long timeBegin = System.currentTimeMillis();
            MappedByteBuffer mbb = fc.map(FileChannel.MapMode.READ_ONLY, 0, aFile.length());
            // System.out.println((char)mbb.get((int)(aFile.length()/2-1)));
            // System.out.println((char)mbb.get((int)(aFile.length()/2)));
            //System.out.println((char)mbb.get((int)(aFile.length()/2)+1));
            long timeEnd = System.currentTimeMillis();
            System.out.println("Read time: " + (timeEnd - timeBegin) + "ms");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (aFile != null) {
                    aFile.close();
                }
                if (fc != null) {
                    fc.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}