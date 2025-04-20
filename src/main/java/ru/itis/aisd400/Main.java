package ru.itis.aisd400;

import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        String text = "AAA";
        String pattern = "ABCABD";
        FindSubstring findSubstring = new FindSubstring();
        int[] res = findSubstring.fillPrefix(pattern);
        System.out.println(Arrays.toString(res));
    }

}
