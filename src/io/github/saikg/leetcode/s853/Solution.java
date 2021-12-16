package io.github.saikg.leetcode.s853;

import java.util.Arrays;
import java.util.Comparator;

public class Solution {

    public int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        Car[] cars = new Car[n];
        for (int i = 0; i < n; i++) {
            cars[i] = new Car(position[i], speed[i]);
        }
        Arrays.sort(cars, Comparator.comparingInt(a -> a.pos));

        int ans = 0;
        double maxTime = 0;
        for (int i = n-1; i >= 0; i--) {
            Car car = cars[i];
            double timeToFinish = (target - car.pos) / ((double)car.speed);
            if (timeToFinish > maxTime) {
                ans++;
                maxTime = timeToFinish;
            }
        }
        return ans;
    }

    class Car {
        int pos, speed;
        Car(int p, int sp) {
            pos = p;
            speed = sp;
        }
    }
}