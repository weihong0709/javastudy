package com.zte;

import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    Queue<Integer> que;

    public int strongPasswordChecker(String s) {
        int len = s.length();
        if (len == 0) return 6;
        int need1 = 1;
        int need2 = 1;
        int need3 = 1;
        que = new PriorityQueue<Integer>((a, b) -> a % 3 - b % 3);
        int cnt = 1;
        if (Character.isLowerCase(s.charAt(0))) need1 = 0;
        if (Character.isUpperCase(s.charAt(0))) need2 = 0;
        if (Character.isDigit(s.charAt(0))) need3 = 0;
        for (int i = 1; i < len; ++i) {
            if (Character.isLowerCase(s.charAt(i))) need1 = 0;
            if (Character.isUpperCase(s.charAt(i))) need2 = 0;
            if (Character.isDigit(s.charAt(i))) need3 = 0;
            if (s.charAt(i) != s.charAt(i - 1)) {
                if (cnt >= 3)
                    que.add(cnt);
                cnt = 1;
            } else {
                ++cnt;
            }
        }
        if (cnt >= 3)
            que.add(cnt);
        int need = need1 + need2 + need3;
        int ans = 0;
        //小于6，取缺失次数和缺少长度的最大值，返回
        if (len < 6) {
            if (len == 5) {
                if (need >= 2 || cnt == 5) return need;
                return 1;
            } else {
                return 6 - len;
            }
        }
        //对连续3位以上相同的进行删除，每次都对，连续出现次数对3进行取余，删除顺序为 等于0>等于1>等于2
        while (!que.isEmpty() && len > 20) {
            int now = que.poll();
            ++ans;--len;
            if (--now >= 3) que.add(now);
        }
        //删除重复的还大于20，结果为还需要删除数量+需要替换的数量
        if (len > 20){
            ans += len - 20 + Math.max(0, need);
        }
        //length不大于20，存在重复的，替换操作需要的次数最少，替换的次数为 长度除以3取整，例如 3 的话是 1 ，4 的话也是1
        else {

            int cunt = 0;
            while (!que.isEmpty()) {
                int now = que.poll();
                cunt += now / 3;
            }
            ans += Math.max(cunt, need);
        }
        return ans;
    }
}


