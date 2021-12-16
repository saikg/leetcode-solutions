package io.github.saikg.leetcode.s22;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {
    public List<String> generateParenthesis(int n) {
        if (n == 1) {
            return Collections.singletonList("()");
        }

        List<String> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (String left: generateParenthesis(i)) {
                for (String right: generateParenthesis(n - i - 1)) {
                    result.add('(' + left + ')' + right);
                }
            }
        }
        return result;
    }
}
