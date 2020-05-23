package com.practice.leetcode._0005;

public class Solution {
    private static String longestPalindrome(String s) {
        if(s==null || s.length()<=1){
            return s;
        }
        int first = 0;
        int last = s.length() - 1;
        while(first<last){
            if(s.charAt(first)==s.charAt(last)){
                first++;
                last--;
            }else{
                break;
            }
        }
        if(first-last==0||first-last==1){
            return s;
        } else{
            String A = longestPalindrome(s.substring(1));
            String B = longestPalindrome(s.substring(0,s.length()-1));
            return A.length()>B.length()?A:B;
        }
    }

    public static void main(String[] args) {
        String str = "babaddtatt";
        System.out.println(longestPalindrome(str));
    }

}
