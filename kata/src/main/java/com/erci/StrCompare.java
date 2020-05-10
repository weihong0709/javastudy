package com.erci;

import com.eric.dto.CharInfo;
import com.eric.dto.StrInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrCompare {

    public static int compare(String s1, String s2) {
        try {
            isValid(s1);
            isValid(s2);
            return compare(splitStr(s1), splitStr(s2));
        } catch (InvalidCharException ex) {
            return StrCompareConstants.RESULT_INVALID;
        }

    }

    private static void isValid(String s1) {
        int previousChartype = 0;
        for (int i = 0; i < s1.length(); i++) {
            int currentChartype = getCharType(s1.charAt(i));
            if (i != 0) {
                if (previousChartype == StrCompareConstants.STR_TYPE_ESCAPES && currentChartype == StrCompareConstants.STR_TYPE_ESCAPES) {
                    throw new InvalidCharException("格式不正确，转义符后面不能再带转义符");
                }
            }
            if (i == s1.length() - 1 && currentChartype == StrCompareConstants.STR_TYPE_ESCAPES) {
                throw new InvalidCharException("格式不正确，最后一个字符不能为转义符");
            }
            previousChartype = currentChartype;
        }
    }


    public static Queue<StrInfo> splitStr(String str) {
        Queue<StrInfo> queue = new LinkedList<StrInfo>();
        if (isEmpty(str)) {
            return queue;
        }
        StringBuilder subStrBuilder = new StringBuilder();
        CharInfo firstCharInfo = getCharInfo(str, 0);
        appendSubStr(str, subStrBuilder, firstCharInfo, 0);
        for (int i = firstCharInfo.getCharLength(); i < str.length(); i = i + firstCharInfo.getCharLength()) {
            CharInfo currentCharInfo = getCharInfo(str, i);
            if (firstCharInfo.getCharType() == currentCharInfo.getCharType()) {
                appendSubStr(str, subStrBuilder, currentCharInfo, i);
                firstCharInfo = currentCharInfo;
                continue;
            }
            queue.add(new StrInfo(subStrBuilder.toString(), firstCharInfo.getCharType()));
            firstCharInfo = currentCharInfo;
            subStrBuilder = new StringBuilder();
            appendSubStr(str, subStrBuilder, firstCharInfo, i);

        }
        if (subStrBuilder.length() > 0) {
            queue.add(new StrInfo(subStrBuilder.toString(), firstCharInfo.getCharType()));
        }
        return queue;
    }

    private static void appendSubStr(String str, StringBuilder subStrBuilder, CharInfo firstCharInfo, int pos) {
        if (firstCharInfo.isEscapesFlag()) {
            subStrBuilder.append(str.substring(pos + 1, pos + firstCharInfo.getCharLength()));
        } else {
            subStrBuilder.append(str.charAt(pos));
        }
    }

    private static CharInfo getCharInfo(String str, int pos) {
        int currentCharType = getCharType(str.charAt(pos));
        if (currentCharType == StrCompareConstants.STR_TYPE_NUMBER) {
            return new CharInfo(StrCompareConstants.STR_TYPE_NUMBER, 1);
        } else if (currentCharType == StrCompareConstants.STR_TYPE_ESCAPES) {
            return getEscapesCharInfo(str, pos);
        } else {
            return new CharInfo(StrCompareConstants.STR_TYPE_STR, 1);
        }
    }

    private static CharInfo getEscapesCharInfo(String str, int pos) {
        int charLength = 1;
        int charType = StrCompareConstants.STR_TYPE_STR;
        int begin = pos + 1;
        for (int i = 0; i < 3; i++) {
            int curPos = begin + i;
            if (curPos >= str.length()) {
                break;
            }
            if (getCharType(str.charAt(pos + i + 1)) == StrCompareConstants.STR_TYPE_STR) {
                if (i == 0) {
                    charLength++;
                    charType = StrCompareConstants.STR_TYPE_NUMBER;
                }
                break;
            }
            if (getCharType(str.charAt(pos + i + 1)) == StrCompareConstants.STR_TYPE_ESCAPES) {
                break;
            } else {
                charLength++;
            }

        }
        CharInfo charInfo = new CharInfo(charType, charLength);
        charInfo.setEscapesFlag(true);
        return charInfo;
    }

    private static int getCharType(char input) {
        if (input >= StrCompareConstants.ASCII_NUMBER_BEGIN && input <= StrCompareConstants.ASCII_NUMBER_END) {
            return StrCompareConstants.STR_TYPE_NUMBER;
        } else if (input == StrCompareConstants.ASCII_ESCAPES) {
            return StrCompareConstants.STR_TYPE_ESCAPES;
        } else if (isLowercaseLetter(input) || isUppercaseLetter(input)) {
            return StrCompareConstants.STR_TYPE_STR;
        } else {
            throw new InvalidCharException("字符串格式不正确");
        }
    }

    private static boolean isUppercaseLetter(char input) {
        return input >= StrCompareConstants.ASCII_UPPERCASE_LETTER_BEGIN && input <= StrCompareConstants.ASCII_UPPERCASE_LETTER_END;
    }

    private static boolean isLowercaseLetter(char input) {
        return input >= StrCompareConstants.ASCII_LOWERCASE_LETTER_BEGIN && input <= StrCompareConstants.ASCII_LOWERCASE_LETTER_END;
    }

    public static int compare(Queue<StrInfo> oneStrInfos, Queue<StrInfo> anotherStrInfos) {
        StrInfo oneStrInfo = oneStrInfos.poll();
        while (oneStrInfo != null) {
            StrInfo anotherStrInfo = anotherStrInfos.poll();
            if (anotherStrInfo == null) {
                return StrCompareConstants.RESULT_MORETHAN;
            }
            int result = compare(oneStrInfo, anotherStrInfo);
            if (result == StrCompareConstants.RESULT_EQ) {
                oneStrInfo = oneStrInfos.poll();
            } else {
                return result;
            }
        }

        StrInfo anotherStrInfo = anotherStrInfos.poll();
        if (anotherStrInfo != null) {
            return StrCompareConstants.RESULT_LESSTHAN;
        } else {
            return StrCompareConstants.RESULT_EQ;
        }

    }

    private static int compare(StrInfo oneStrInfo, StrInfo anotherStrInfo) {
        if (oneStrInfo.getStrType() == anotherStrInfo.getStrType()) {
            return compareWhenStrTypeIsSame(oneStrInfo, anotherStrInfo);
        } else {
            return compareWhenStrTypeIsDifferent(oneStrInfo, anotherStrInfo);
        }
    }

    private static boolean isEmpty(String str) {
        return str == null || str.trim().length() == 0;
    }

    private static int compareWhenContainsNull(StrInfo oneStrInfo, StrInfo anotherStrInfo) {
        if (oneStrInfo == null && anotherStrInfo == null) {
            return StrCompareConstants.RESULT_EQ;
        } else if (oneStrInfo == null && anotherStrInfo != null) {

            return StrCompareConstants.RESULT_LESSTHAN;
        } else {
            return StrCompareConstants.RESULT_MORETHAN;
        }
    }

    private static int compareWhenStrTypeIsSame(StrInfo oneStrInfo, StrInfo anotherStrInfo) {
        if (oneStrInfo.getStrType() == StrCompareConstants.STR_TYPE_NUMBER) {
            return compareNumber(oneStrInfo, anotherStrInfo);
        } else {
            return compareString(oneStrInfo, anotherStrInfo);
        }
    }

    private static int compareWhenStrTypeIsDifferent(StrInfo oneStrInfo, StrInfo anotherStrInfo) {
        if (oneStrInfo.getStrType() == StrCompareConstants.STR_TYPE_NUMBER) {
            return StrCompareConstants.RESULT_LESSTHAN;
        } else {
            return StrCompareConstants.RESULT_MORETHAN;
        }
    }


    private static int compareNumber(StrInfo oneStrInfo, StrInfo anotherStrInfo) {
        long numberOne = convertStrToNumber(oneStrInfo.getSubStr());
        long numberTwo = convertStrToNumber(anotherStrInfo.getSubStr());
        if (numberOne > numberTwo) {
            return StrCompareConstants.RESULT_MORETHAN;
        } else if (numberOne == numberTwo) {
            return compareZeroCount(oneStrInfo, anotherStrInfo);
        } else {
            return StrCompareConstants.RESULT_LESSTHAN;
        }
    }

    private static int compareZeroCount(StrInfo oneStrInfo, StrInfo anotherStrInfo) {
        int oneStrZeroCount = getZeroCount(oneStrInfo.getSubStr());
        int anotherStrZeroCount = getZeroCount(anotherStrInfo.getSubStr());
        if (oneStrZeroCount == anotherStrZeroCount) {
            return StrCompareConstants.RESULT_EQ;
        } else if (oneStrZeroCount > anotherStrZeroCount) {
            return StrCompareConstants.RESULT_MORETHAN;
        } else {
            return StrCompareConstants.RESULT_LESSTHAN;
        }
    }

    private static int compareString(StrInfo oneStrInfo, StrInfo anotherStrInfo) {
        int result = oneStrInfo.getSubStr().compareTo(anotherStrInfo.getSubStr());
        if (result == 0) {
            return StrCompareConstants.RESULT_EQ;
        } else if (result < 0) {
            return StrCompareConstants.RESULT_LESSTHAN;
        } else {
            return StrCompareConstants.RESULT_MORETHAN;
        }

    }

    private static Long convertStrToNumber(String str) {
        long result = 0;
        for (int i = 0; i < str.length(); i++) {
            result += convertCharToNumber(str.charAt(i)) * Math.pow(10, str.length() - (i + 1));
        }
        return result;
    }

    private static int convertCharToNumber(char input) {
        if (getCharType(input) == StrCompareConstants.STR_TYPE_NUMBER) {
            return Integer.parseInt(String.valueOf(input));
        } else {
            return input;
        }
    }


    private static int getZeroCount(String str) {
        String regEx = "^0+";
        Pattern pattern = Pattern.compile(regEx);
        Matcher matcher = pattern.matcher(str);
        if (matcher.find()) {
            String zeroStr = matcher.group();
            return zeroStr.length();
        }
        return 0;
    }


}
