package io.github.saikg.leetcode.s42;

import java.util.Stack;

public class Solution {
    public int trap(int[] height) {
        return twoPointer(height);
    }

    // brute-force implementation
    private int bruteForce(int[] height) {
        int n = height.length;
        int[] leftBound = new int[n];
        int[] rightBound = new int[n];
        int currHeight = 0;

        for (int i = 0; i < n; i++) {
            currHeight = Math.max(currHeight, height[i]);
            leftBound[i] = currHeight;
        }

        currHeight = 0;
        for (int i = n-1; i >=0 ; i--) {
            currHeight = Math.max(currHeight, height[i]);
            rightBound[i] = currHeight;
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += Math.max(0, Math.min(leftBound[i], rightBound[i]) - height[i]);
        }
        return ans;
    }

    // monotonic-stack implementation
    private int stackImpl(int[] height) {
        int trappedWater = 0;
        Stack<Integer> positionStack = new Stack<>();
        for (int i = 0; i < height.length; i++) {
            while (!positionStack.isEmpty() && height[positionStack.peek()] < height[i]) {
                int intermediateSmallerTowerPos = positionStack.pop();
                if (positionStack.isEmpty()) {
                    break;
                }
                int leftTowerPos = positionStack.peek();
                int compensatedHeight = Math.min(height[leftTowerPos], height[i]) - height[intermediateSmallerTowerPos];
                int distance = i - leftTowerPos - 1;
                trappedWater += compensatedHeight * distance;
            }
            positionStack.push(i);
        }
        return trappedWater;
    }

    // two-pointer approach
    private int twoPointer(int[] height) {
        int leftMax = 0, rightMax = 0;
        int left = 0, right = height.length - 1;
        int trappedWater = 0;
        while (left < right) {
            if (height[left] < height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                } else {
                    trappedWater += (leftMax - height[left]);
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                } else {
                    trappedWater += (rightMax - height[right]);
                }
                right--;
            }
        }
        return trappedWater;
    }
}