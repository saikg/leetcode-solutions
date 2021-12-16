package io.github.saikg.leetcode.s690;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class Solution {

    class Employee {
        public int id;
        public int importance;
        public List<Integer> subordinates;
    };

    public int getImportance(List<Employee> employees, int id) {
        int n = employees.size();
        Map<Integer, Employee> idMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            idMap.put(employees.get(i).id, employees.get(i));
        }

        Stack<Employee> stack = new Stack<>();
        stack.push(idMap.get(id));

        int importance = 0;
        while (!stack.isEmpty()) {
            Employee emp = stack.pop();
            importance += emp.importance;

            for (int sub: emp.subordinates) {
                stack.push(idMap.get(sub));
            }
        }
        return importance;
    }
}