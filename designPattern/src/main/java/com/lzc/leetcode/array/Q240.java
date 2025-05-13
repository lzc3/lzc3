package com.lzc.leetcode.array;

import com.lzc.leetcode.test.HandleSolution;
import com.lzc.leetcode.test.Solution;

public class Q240 extends Solution {

    @HandleSolution
    private boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int row = matrix.length;
        int col = matrix[0].length;

        int curtRow = 0;
        int curCol = col - 1;

        do {
            int cutValue = matrix[curtRow][curCol];
            if (target == cutValue) {
                return true;
            } else if (target < cutValue) {
                curCol--;
            } else {
                curtRow++;
            }
        } while (curtRow <= row - 1 && curCol >= 0);
        return false;
    }

    @Override
    protected Object[] offerArgs() {
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        int target = 5;

        Object[] objects = new Object[2];
        objects[0] = matrix;
        objects[1] = target;
        return objects;
    }
}
