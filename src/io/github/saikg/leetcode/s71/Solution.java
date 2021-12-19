package io.github.saikg.leetcode.s71;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

public class Solution {
    public String simplifyPath(String path) {
        List<String> parts = Arrays.stream(path.split("/"))
                .filter(v -> !(".".equals(v) || v.isEmpty()))
                .collect(Collectors.toList());
        Stack<String> pathStack = new Stack<>();
        for (String pathElement: parts) {
            if (pathElement.equals("..")) {
                if (!pathStack.isEmpty())
                    pathStack.pop();
            } else {
                pathStack.push(pathElement);
            }
        }
        StringBuilder simplifiedPath = new StringBuilder();
        for (String pathElement: pathStack) {
            simplifiedPath.append("/");
            simplifiedPath.append(pathElement);
        }
        return simplifiedPath.length() == 0 ? "/" : simplifiedPath.toString();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String simplifiedPath = solution.simplifyPath("/a/c/./b");
        System.out.println("simplifiedPath = " + simplifiedPath);
    }
}