package com.leetcode;


//Version-1
class NumArray {

    int[] sums;
    int[] nums;
    public NumArray(int[] nums) {
        this.sums = new int[nums.length+1];
        this.nums = nums;

        //Initialize sums
        for(int i=0; i<nums.length; i++) {
            sums[i+1] = sums[i] + nums[i];
        }
    }

    public void update(int i, int val) {
        nums[i] = val;

        //Update sums from index i
        for(int p=i; p<nums.length; p++) {
            sums[p+1] = sums[p] + nums[p];
        }
    }

    public int sumRange(int i, int j) {
        return sums[j+1] - sums[i];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(i,val);
 * int param_2 = obj.sumRange(i,j);
 */

//Version-2
class Program {
    //Segment tree
    int[] nums;
    int[] sums;
    int n;

    Program(int[] nums) {
        n = nums.length;
        this.nums = nums;
        this.sums = new int[2 * n];
        create(n);
    }

    //Create
    public void create(int n) {
        for (int i = n, j = 0; i < 2 * n; i++, j++) {
            sums[i] = nums[j];
        }

        for (int i = n - 1; i > 0; i--) {
            sums[i] = sums[2 * i] + sums[2 * i + 1];
        }
    }


    //Update
    public void update(int pos, int val) {
        pos += n;
        sums[pos] = val;
        while (pos > 0) {
            int left = pos;
            int right = pos;
            if (pos % 2 == 0) {
                right = pos + 1;
            } else {
                left = pos - 1;
            }
            // parent is updated after child is updated
            sums[pos / 2] = sums[left] + sums[right];
            pos /= 2;
        }
    }

    //Compute (???)
    public int sumRange(int l, int r) {
        // get leaf with value 'l'
        l += n;
        // get leaf with value 'r'
        r += n;
        int sum = 0;
        while (l <= r) {
            if ((l % 2) == 1) {
                sum += sums[l];
                l++;
            }
            if ((r % 2) == 0) {
                sum += sums[r];
                r--;
            }
            l /= 2;
            r /= 2;
        }
        return sum;
    }
}
