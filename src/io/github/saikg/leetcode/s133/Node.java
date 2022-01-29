package io.github.saikg.leetcode.s133;

import java.util.ArrayList;
import java.util.List;

public class Node {

    public int val;

    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, List<Node> _neighbours) {
        val = _val;
        neighbors = _neighbours;
    }
}
