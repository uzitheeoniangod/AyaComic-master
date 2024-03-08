package com.example.aya.demo;

import javax.validation.constraints.Null;
import java.util.*;

public class Solution {
    public int[] maxDepthAfterSplit(String seq) {
        int[] ans = new int [seq.length()];
        int idx = 0;
        for(char c: seq.toCharArray()) {
            ans[idx++] = c == '(' ? idx & 1 : ((idx + 1) & 1);
        }
        return ans;
    }


    public static void main(String[] args) {


    }
}

