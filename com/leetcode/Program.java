package com.leetcode;


//Algo:
//1. Move result left by 1 bit
//2. Compute result's 0th bit by n&1
//3. Move number right by 1 bit
//Repeat the above 32 times because its integer
class Program {

    public static int reverseBits(int n) {
        StringBuilder sb = new StringBuilder();
        //Its an integer thus 32 bits
        int result = 0;
        for(int i=0; i<32; i++) {
            result = result << 1; //Move 1bit left. Note: I move result left, because am reversing
            if((n&1) == 1) result++; //Add 1 to latest 0th bit
            n = n >>> 1; //Move 1bit right. Note: I move number right, because am reversing
        }
        return result;
    }

    public static void main(String[] nums) {
        reverseBits(4);
    }
}