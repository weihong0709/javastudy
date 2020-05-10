package com.eric.dto;

public class StrInfo {
    private String subStr;
    /**
     * 字符串类型 1_数字串 2_字符串
     */
    private  int strType;

    public StrInfo(String subStr, int strType) {
        this.subStr = subStr;
        this.strType = strType;
    }
    public String getSubStr() {
        return subStr;
    }

    public int getStrType() {
        return strType;
    }

}
