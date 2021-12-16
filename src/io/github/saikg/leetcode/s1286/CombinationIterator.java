package io.github.saikg.leetcode.s1286;

class CombinationIterator {

    private final int n, k;
    private final String characters;
    private int bitmask;

    public CombinationIterator(String characters, int combinationLength) {
        this.characters = characters;

        n = characters.length();
        k = combinationLength;
        bitmask = (1 << n) - (1 << (n - k));
    }

    public String next() {
        final String ans = constructString();
        updateBitmask();
        return ans;
    }

    public boolean hasNext() {
        return bitmask > 0;
    }

    private String constructString() {
        final StringBuilder stringBuilder = new StringBuilder(k);
        for (int i = 0; i < n; i++) {
            if ((bitmask & 1 << (n - i - 1)) != 0) {
                stringBuilder.append(characters.charAt(i));
            }
        }
        return stringBuilder.toString();
    }

    private void updateBitmask() {
        bitmask--;
        while (bitmask > 0 && Integer.bitCount(bitmask) != k) {
            bitmask--;
        }
    }

    public static void main(String args[]) {
        CombinationIterator combinationIterator = new CombinationIterator("chp", 1);
        System.out.println(combinationIterator.hasNext());
        System.out.println(combinationIterator.next());
    }
}