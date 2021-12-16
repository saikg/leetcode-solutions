package io.github.saikg.leetcode.s1650;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution {
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node parent;
    }

    public Node lowestCommonAncestor(Node p, Node q) {
        Set<Node> pathToRoot = new HashSet<>();
        while (p != null) {
            pathToRoot.add(p);
            p = p.parent;
        }

        while (!pathToRoot.contains(q)) {
            q = q.parent;
        }
        return q;
    }
}