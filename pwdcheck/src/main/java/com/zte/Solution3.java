package com.zte;

public class Solution3 {
    /**
     * 记录连续出现的字符 起始和终止坐标
     */
    class SameChar {
        int st;
        int en;
        char c;

        SameChar(int st, int en, char c) {
            this.st = st;
            this.en = en;
            this.c = c;
        }

    }

    public int strongPasswordChecker(String str) {
        // 统计小写字符
        int lowerCase = 0;
        // 统计大写字符
        int upwerCase = 0;
        // 统计数字
        int number = 0;
        // 统计连续字符出现的位置
        java.util.ArrayList<SameChar> sameChars = new java.util.ArrayList<SameChar>();
        char[] chars = str.toCharArray();
        if (chars.length == 0) {
            return 6;
        }
        // 记露连续出现的字符
        SameChar sameChar = new SameChar(0, 0, '\0');
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'a' && chars[i] <= 'z') {
                lowerCase++;
            } else if (chars[i] >= 'A' && chars[i] <= 'Z') {
                upwerCase++;
            } else if (chars[i] >= '0' && chars[i] <= '9') {
                number++;
            }
            if (sameChar.c != chars[i]) {
                if (sameChar.en - sameChar.st >= 2) {
                    sameChars.add(new SameChar(sameChar.st, sameChar.en, sameChar.c));
                }
                sameChar.c = chars[i];
                sameChar.st = i;
                sameChar.en = i;
            } else {
                sameChar.en = i;
            }
        }
        if (sameChar.en - sameChar.st >= 2) {
            sameChars.add(new SameChar(sameChar.st, sameChar.en, sameChar.c));
        }
        // 缺失的类型. 只可能是1 or 2
        int needType = count0(lowerCase, upwerCase, number);
        // 连续的字符出现的要消除的个数 连续值-2
        int[] chages = new int[sameChars.size()];
        for (int j = 0; j < sameChars.size(); j++) {
            chages[j] = sameChars.get(j).en - sameChars.get(j).st - 1;
        }
        int res = 0;
        // 如果长度小于6 , 很简单 要补的字符和缺失的类型择大
        if (str.length() < 6) {
            return Integer.max(6 - str.length(), needType);
        }
        // 删除的时候 要有优先概念
        if (str.length() > 20) {
            int index = -1;
            while (needType > 0 && (index = find(chages, 0)) > -1) {
                chages[index] = Integer.max(chages[index] - 3, 0);
                res++;
                needType--;
            }
            int d = str.length() - 20;
            while (d > 0 && (index = find(chages, 1)) > -1) {
                d--;
                res++;
                chages[index]--;
            }
            int n = 0;
            for (int l = 0; l < chages.length; l++) {
                n += chages[l] % 3 == 0 ? chages[l] / 3 : chages[l] / 3 + 1;
            }
            return res + d + needType + n;
        }
        int n = 0;
        for (int l = 0; l < chages.length; l++) {
            n += chages[l] % 3 == 0 ? chages[l] / 3 : chages[l] / 3 + 1;
        }
        return Integer.max(n, needType);
    }

    private int count0(int... array) {
        int n = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                n++;
            }
        }
        return n;
    }

    private int find(int[] array, int n) {
        int n0 = -1;
        int n1 = -1;
        int n2 = -1;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > 0 && array[i] % 3 == 0) {
                n0 = i;
            }
            if (array[i] > 0 && array[i] % 3 == 1) {
                n1 = i;
            }
            if (array[i] > 0 && array[i] % 3 == 2) {
                n2 = i;
            }
        }
        if (n == 0) {
            return n0 > -1 ? n0 : (n2 > -1 ? n2 : n1);
        }
        if (n == 1) {
            return n1 > -1 ? n1 : (n2 > -1 ? n2 : n0);
        }
        return -1;
    }
}

