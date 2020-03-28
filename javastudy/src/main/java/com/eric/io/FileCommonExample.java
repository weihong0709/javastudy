package com.eric.io;

import java.io.File;

public class FileCommonExample {
    public void testPath(){
        //File.separator 文件路径分隔符
        System.out.println(File.separator);
        //环境变量分隔符
        System.out.println(File.pathSeparator);
    }
}
