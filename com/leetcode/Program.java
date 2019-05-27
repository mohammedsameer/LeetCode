
package com.leetcode;

import java.util.*;

/**
 * Algo:
 * 1. Loop through intervals and add the new interval at right index
 * 2. Loop through and merge intervals
 */
public class Program {
    public static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> tempResult = new ArrayList<>();
        List<Interval> result = new ArrayList<>();
        tempResult.add(intervals.get(0));
        boolean isInserted = false;

        //Simply insert new interval at right location
        for(int index=1; index<intervals.size(); index++) {
            Interval prev = tempResult.get(tempResult.size()-1);
            Interval curr = intervals.get(index);
            //If new interval is smaller
            if(!isInserted && newInterval.start<=prev.start) {
                tempResult.remove(tempResult.size() - 1);
                tempResult.add(newInterval);
                tempResult.add(prev);
                isInserted = true;
            }

            tempResult.add(curr);
        }

        //Merge intervals
        result.add(tempResult.get(0));
        for (int index=1; index<tempResult.size(); index++) {
            Interval prev = result.get(result.size()-1);
            Interval curr = tempResult.get(index);
            if(prev.end >= curr.start) {
                //Pop up and merge the intervals
                Interval merge = new Interval(prev.start, Math.max(prev.end, curr.end));
                result.remove(result.size()-1);
                result.add(merge);
            } else {
                //No overlap add new entry
                result.add(curr);
            }
        }

        return result;
    }


    public static void main(String[] args) {
        List<Interval> intervals = new ArrayList<>();
        intervals.add(new Interval(1,4));
        intervals.add(new Interval(6,9));
        intervals.add(new Interval(17,18));

        insert(intervals, new Interval(3, 7));
    }
}