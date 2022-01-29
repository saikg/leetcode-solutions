package io.github.saikg.leetcode.s4;

import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

public class Solution2 {
    static class Fraction {
        BigInteger a;
        BigInteger b;

        Fraction(BigInteger a_, BigInteger b_) {
            BigInteger gcd = a_.abs().gcd(b_.abs());
            a = a_.divide(gcd);
            b = b_.divide(gcd);
            if (b.compareTo(BigInteger.ZERO) == -1) {
                a = a.negate();
                b = b.negate();
            }
        }

        Fraction add(Fraction r) {
            return new Fraction(a.multiply(r.b).add(b.multiply(r.a)), b.multiply(r.b));
        }

        Fraction subtract(Fraction r) {
            return new Fraction(a.multiply(r.b).subtract(b.multiply(r.a)), b.multiply(r.b));
        }

        Fraction multiply(Fraction r) {
            return new Fraction(a.multiply(r.a), b.multiply(r.b));
        }

        Fraction div(Fraction r) {
            return new Fraction(a.multiply(r.b), b.multiply(r.a));
        }

        Fraction negate() {
            return new Fraction(a.negate(), b);
        }

        Fraction abs() {
            return new Fraction(a.abs(), b);
        }

        int compareTo(Fraction r) {
            BigInteger x = a.multiply(r.b);
            BigInteger y = b.multiply(r.a);
            return x.compareTo(y);
        }

        public String toString() {
            return a + "/" + b;
        }
    }

    static Fraction ans;

    static void Test(Fraction L, Fraction R, Fraction a, Fraction b, Fraction c) {
        Fraction x = a.multiply(L).add(b).multiply(L).add(c);
        Fraction y = a.multiply(R).add(b).multiply(R).add(c);
//    System.out.println(L + " " + R + " " + a + " " + b + " " + c + " -> " + x + " " + y);
        int sx = x.compareTo(new Fraction(BigInteger.ZERO, BigInteger.ONE));
        int sy = y.compareTo(new Fraction(BigInteger.ZERO, BigInteger.ONE));
        if (sx != sy) {
            ans = new Fraction(BigInteger.ZERO, BigInteger.ONE);
        } else {
            if (sx == -1) {
                x = x.negate();
            }
            if (ans.compareTo(new Fraction(BigInteger.ZERO, BigInteger.ONE)) == -1 || x.compareTo(ans) == -1) {
                ans = x;
            }
            if (sy == -1) {
                y = y.negate();
            }
            if (ans.compareTo(new Fraction(BigInteger.ZERO, BigInteger.ONE)) == -1 || y.compareTo(ans) == -1) {
                ans = y;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        int tt = sc.nextInt();
        for (int qq = 1; qq <= tt; qq++) {
            System.out.print("Case #" + qq + ": ");
            int n = sc.nextInt();
            int w = sc.nextInt();
            int h = sc.nextInt();
            int p = sc.nextInt();
            int q = sc.nextInt();
            int r = sc.nextInt();
            int s = sc.nextInt();
            if (p > r) {
                int tmp = p; p = r; r = tmp;
                tmp = q; q = s; s = tmp;
            }
            // p <= r
            int[] x = new int[n];
            int[] y = new int[n];
            int[] A = new int[n];
            int[] B = new int[n];
            for (int i = 0; i < n; i++) {
                x[i] = sc.nextInt();
                y[i] = sc.nextInt();
                A[i] = sc.nextInt();
                B[i] = sc.nextInt();
            }
            Fraction[] hh = new Fraction[n];
            Fraction[] area = new Fraction[n];
            for (int i = 0; i < n; i++) {
                long a = s;
                long b = -r;
                long c = -a * x[i] - b * y[i];
                Fraction z = new Fraction(BigInteger.valueOf(-a * (x[i] + p) - c), BigInteger.valueOf(b));
                z = z.subtract(new Fraction(BigInteger.valueOf(y[i] + q), BigInteger.ONE));
                z = z.abs();
                hh[i] = z;
                area[i] = z.multiply(new Fraction(BigInteger.valueOf(r), BigInteger.valueOf(2)));
//        System.out.println(i + " " + hh[i] + " " + area[i]);
            }
            long sumB = 0;
            for (int i = 0; i < n; i++) {
                sumB += B[i];
            }
            int[] xs = new int[3 * n + 2];
            for (int i = 0; i < n; i++) {
                xs[3 * i] = x[i];
                xs[3 * i + 1] = x[i] + p;
                xs[3 * i + 2] = x[i] + r;
            }
            xs[3 * n] = 0;
            xs[3 * n + 1] = w;
            Arrays.sort(xs);
            ans = new Fraction(BigInteger.valueOf(-1), BigInteger.ONE);
            for (int it = 0; it < xs.length - 1; it++) {
                int L = xs[it];
                int R = xs[it + 1];
                if (L == R) {
                    continue;
                }
                Fraction a = new Fraction(BigInteger.ZERO, BigInteger.ONE);
                Fraction b = new Fraction(BigInteger.ZERO, BigInteger.ONE);
                Fraction c = new Fraction(BigInteger.valueOf(-sumB), BigInteger.ONE);
                for (int i = 0; i < n; i++) {
                    if (R <= x[i]) {
                        continue;
                    }
                    if (L >= x[i] + r) {
                        c = c.add(new Fraction(BigInteger.valueOf(A[i] + B[i]), BigInteger.ONE));
                        continue;
                    }
                    if (x[i] <= L && R <= x[i] + p) {
                        Fraction coeff = hh[i].div(area[i]).multiply(new Fraction(BigInteger.valueOf(A[i] + B[i]), BigInteger.valueOf(2 * p)));
                        a = a.add(coeff);
                        b = b.subtract(coeff.multiply(new Fraction(BigInteger.valueOf(2 * x[i]), BigInteger.ONE)));
                        c = c.add(coeff.multiply(new Fraction(BigInteger.valueOf(x[i] * 1L * x[i]), BigInteger.ONE)));
                        continue;
                    }
                    if (x[i] + p <= L && R <= x[i] + r) {
                        c = c.add(new Fraction(BigInteger.valueOf(A[i] + B[i]), BigInteger.ONE));
                        Fraction coeff = hh[i].div(area[i]).multiply(new Fraction(BigInteger.valueOf(A[i] + B[i]), BigInteger.valueOf(2 * (r - p))));
                        a = a.subtract(coeff);
                        b = b.add(coeff.multiply(new Fraction(BigInteger.valueOf(2 * (x[i] + r)), BigInteger.ONE)));
                        c = c.subtract(coeff.multiply(new Fraction(BigInteger.valueOf((x[i] + r) * 1L * (x[i] + r)), BigInteger.ONE)));
                        continue;
                    }
                    throw new Exception();
                }
                if (a.a.compareTo(BigInteger.ZERO) != 0) {
                    Fraction v = b.negate().div(a.multiply(new Fraction(BigInteger.valueOf(2), BigInteger.ONE)));
                    if (v.compareTo(new Fraction(BigInteger.valueOf(L), BigInteger.ONE)) == 1) {
                        if (v.compareTo(new Fraction(BigInteger.valueOf(R), BigInteger.ONE)) == -1) {
                            Test(new Fraction(BigInteger.valueOf(L), BigInteger.ONE), v, a, b, c);
                            Test(v, new Fraction(BigInteger.valueOf(R), BigInteger.ONE), a, b, c);
                            continue;
                        }
                    }
                }
                Test(new Fraction(BigInteger.valueOf(L), BigInteger.ONE), new Fraction(BigInteger.valueOf(R), BigInteger.ONE), a, b, c);
            }
            System.out.println(ans.a + "/" + ans.b);
        }
    }
}
