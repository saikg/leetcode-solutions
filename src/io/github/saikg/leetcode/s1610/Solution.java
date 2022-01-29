package io.github.saikg.leetcode.s1610;

import java.util.*;
import java.util.stream.Collectors;

public class Solution {
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {

        List<List<Integer>> nonCoincidentalPoints = points.stream()
                .filter(point -> !(point.get(0) == location.get(0) && point.get(1) == location.get(1)))
                .collect(Collectors.toList());

        int coincidentalPoints = points.size() - nonCoincidentalPoints.size();

        Map<Integer, Integer> angleToPointCount = new HashMap<>();
        for (List<Integer> point : nonCoincidentalPoints) {
            int inclination = inclinationAngle(location, point);
            angleToPointCount.put(inclination, angleToPointCount.getOrDefault(inclination, 0) + 1);
        }

        int maxSeen = 0;
        for (int viewAngle = 0; viewAngle < 360; viewAngle++) {
            int count = 0;
            for (int vision = viewAngle - angle; vision <= viewAngle + angle; vision++) {
                count += angleToPointCount.getOrDefault(vision, 0);
            }
            maxSeen = Math.max(count, maxSeen);
        }

        System.out.println(maxSeen);
        return maxSeen + coincidentalPoints;
    }

    private int inclinationAngle(List<Integer> location, List<Integer> point) {
        int deltaX = point.get(0) - location.get(0);
        int deltaY = point.get(1) - location.get(1);
        double inclinationInRadians = Math.atan2(deltaY, deltaX);
        double inclinationInDegrees = Math.toDegrees(inclinationInRadians);
        inclinationInDegrees = inclinationInDegrees % 360;
        return (int)Math.round(inclinationInDegrees);
    }

    public static void main(String[] args) {

        List<List<Integer>> points = new ArrayList<>();
        points.add(Arrays.asList(1, 1));
        points.add(Arrays.asList(2, 2));
        points.add(Arrays.asList(1, 2));
        points.add(Arrays.asList(2, 1));

        List<Integer> location = Arrays.asList(1, 1);
        int angle = 0;

        Solution solution = new Solution();
        int ans = solution.visiblePoints(points, angle, location);
        System.out.println("ans = " + ans);
    }
}