package com.eric.dto;

public class CharInfo {
    /**
     * 字符串类型 1_数字串 2_字符串
     */
    private  int charType;
    private  int charLength;
    private boolean escapesFlag;

    public CharInfo(int charType, int charLength) {
        this.charType = charType;
        this.charLength = charLength;
    }

    public int getCharType() {
        return charType;
    }

    public int getCharLength() {
        return charLength;
    }

    public boolean isEscapesFlag() {
        return escapesFlag;
    }

    public void setEscapesFlag(boolean escapesFlag) {
        this.escapesFlag = escapesFlag;
    }
}
