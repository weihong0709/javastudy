package com.eric.algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;


public class PerfectMatch {
    public static String validateInput(String input) {
        // 检查数字之间的间隔是否为单个空格
        if (!Pattern.matches("^(\\d+ \\d+)( \\d+ \\d+)*$", input)) {
            return "E2";
        }

        // 解析输入
        String[] parts = input.split(" ");

        // 检查数字个数是否为偶数
        if (parts.length % 2 != 0) {
            return "E1";
        }

        // 转换为整数列表
        List<Integer> dimensions = new ArrayList<>();
        for (String part : parts) {
            dimensions.add(Integer.parseInt(part));
        }

        // 获取矩形区域的长和宽
        int R_length = dimensions.get(0);
        int R_width = dimensions.get(1);

        // 检查矩形区域的长和宽是否大于0
        if (R_length <= 0 || R_width <= 0) {
            return "E3";
        }

        // 获取木片的长和宽
        List<int[]> woodPieces = new ArrayList<>();
        for (int i = 2; i < dimensions.size(); i += 2) {
            woodPieces.add(new int[]{dimensions.get(i), dimensions.get(i + 1)});
        }

        // 计算总面积
        int totalAreaR = R_length * R_width;
        int totalAreaX = woodPieces.stream().mapToInt(p -> p[0] * p[1]).sum();

        // 检查总面积是否匹配
        if (totalAreaR != totalAreaX) {
            return "E4";
        }

        return "VALID";
    }
    // 检查输入字符串是否能完美匹配
    public static String checkPerfectMatch(String input) {
        String validationResult = validateInput(input);  // 验证输入是否合法
        if (!validationResult.equals("VALID")) {
            return validationResult;  // 如果不合法，返回错误代码
        }

        // 解析输入字符串
        String[] parts = input.split(" ");
        int R_length = Integer.parseInt(parts[0]);
        int R_width = Integer.parseInt(parts[1]);

        // 获取所有木片的长和宽
        List<int[]> woodPieces = new ArrayList<>();
        for (int i = 2; i < parts.length; i += 2) {
            woodPieces.add(new int[]{Integer.parseInt(parts[i]), Integer.parseInt(parts[i + 1])});
        }

        // 尝试放置木片
        if (canPlacePieces(R_length, R_width, woodPieces)) {
            return "Y";  // 如果能完美匹配，返回Y
        } else {
            return "N";  // 否则返回N
        }
    }

    // 尝试将所有木片放置在矩形区域中
    private static boolean canPlacePieces(int R_length, int R_width, List<int[]> pieces) {
        int[][] grid = new int[R_length][R_width];  // 初始化矩形区域
        return backtrack(grid, pieces, 0, R_length, R_width);  // 使用回溯法放置木片
    }

    // 回溯法尝试放置木片
    private static boolean backtrack(int[][] grid, List<int[]> pieces, int index, int R_length, int R_width) {
        if (index == pieces.size()) {
            return true;  // 所有木片都放置成功，返回true
        }

        int[] piece = pieces.get(index);
        int pieceLength = piece[0];
        int pieceWidth = piece[1];

        // 遍历矩形区域中的每个位置
        for (int i = 0; i < R_length; i++) {
            for (int j = 0; j < R_width; j++) {
                // 检查能否在当前位置放置当前木片
                if (canPlace(grid, pieceLength, pieceWidth, i, j, R_length, R_width)) {
                    placePiece(grid, pieceLength, pieceWidth, i, j, 1);  // 放置木片
                    if (backtrack(grid, pieces, index + 1, R_length, R_width)) {
                        return true;  // 如果成功放置所有木片，返回true
                    }
                    placePiece(grid, pieceLength, pieceWidth, i, j, 0);  // 回溯，移除木片
                }
            }
        }

        return false;  // 当前路径不能成功放置所有木片，返回false
    }

    // 检查能否在指定位置放置木片
    private static boolean canPlace(int[][] grid, int length, int width, int x, int y, int R_length, int R_width) {
        if (x + length > R_length || y + width > R_width) {
            return false;  // 木片超出矩形区域边界，不能放置
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[x + i][y + j] == 1) {
                    return false;  // 木片重叠，不能放置
                }
            }
        }
        return true;  // 可以放置木片
    }

    // 在指定位置放置或移除木片
    private static void placePiece(int[][] grid, int length, int width, int x, int y, int value) {
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < width; j++) {
                grid[x + i][y + j] = value;  // 设置或清除木片位置
            }
        }
    }

    // 主函数用于测试
    public static void main(String[] args) {
        String inputStr = "10 5 10 1 10 1 10 1 5 2 5 2";
        String result = checkPerfectMatch(inputStr);
        System.out.println(result);  // 输出应为 Y 或 N
    }
}
