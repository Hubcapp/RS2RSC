package com.jagex.runescape.rs2rsc;

import java.applet.Applet;

public final class Isaac {

    private static final int SIZEL = 8;
    private static final int SIZE = 1 << SIZEL;
    private static final int MASK = (SIZE - 1) << 2;
    private final int[] rsl = new int[SIZE];
    private final int[] mem = new int[SIZE];
    private static final int GOLDEN_RATIO = 0x9e3779b9;
    static byte[][] aByteArrayArray438 = new byte[1000][];
    static int[] anIntArray444 = new int[256];
    static Applet anApplet445;
    static long aLong449;
    private int acc, brs, cnt;
    private int gen;

    public Isaac(int[] seed) {
        for (int i = 0; i < seed.length; ++i) {
            rsl[i] = seed[i];
        }
        init();
    }

    public int next() {
        if (gen-- == 0) {
            generate();
            gen = SIZE - 1;
        }
        return rsl[gen];
    }

    private void init() {
        int a, b, c, d, e, f, g, h;

        a = b = c = d =
                e = f = g = h = GOLDEN_RATIO;

        for (int i = 0; i < 4; ++i) {
            a ^= b << 11;
            d += a;
            b += c;
            b ^= c >>> 2;
            e += b;
            c += d;
            c ^= d << 8;
            d += e;
            f += c;
            d ^= e >>> 16;
            e += f;
            g += d;
            e ^= f << 10;
            h += e;
            f += g;
            f ^= g >>> 4;
            a += f;
            g += h;
            g ^= h << 8;
            h += a;
            b += g;
            h ^= a >>> 9;
            c += h;
            a += b;
        }

        for (int i = 0; i < SIZE; i += 8) {
            a += rsl[i];
            b += rsl[i + 1];
            c += rsl[i + 2];
            d += rsl[i + 3];
            e += rsl[i + 4];
            f += rsl[i + 5];
            g += rsl[i + 6];
            h += rsl[i + 7];

            a ^= b << 11;
            d += a;
            b += c;
            b ^= c >>> 2;
            e += b;
            c += d;
            c ^= d << 8;
            d += e;
            f += c;
            d ^= e >>> 16;
            e += f;
            g += d;
            e ^= f << 10;
            h += e;
            f += g;
            f ^= g >>> 4;
            a += f;
            g += h;
            g ^= h << 8;
            h += a;
            b += g;
            h ^= a >>> 9;
            c += h;
            a += b;

            mem[i] = a;
            mem[i + 1] = b;
            mem[2 + i] = c;
            mem[i - -3] = d;
            mem[4 + i] = e;
            mem[i - -5] = f;
            mem[6 + i] = g;
            mem[i - -7] = h;
        }

        for (int i = 0; i < SIZE; i += 8) {
            a += mem[i];
            b += mem[i + 1];
            c += mem[i + 2];
            d += mem[i + 3];
            e += mem[i + 4];
            f += mem[i + 5];
            g += mem[i + 6];
            h += mem[i + 7];

            a ^= b << 11;
            d += a;
            b += c;
            b ^= c >>> 2;
            e += b;
            c += d;
            c ^= d << 8;
            d += e;
            f += c;
            d ^= e >>> 16;
            e += f;
            g += d;
            e ^= f << 10;
            h += e;
            f += g;
            f ^= g >>> 4;
            a += f;
            g += h;
            g ^= h << 8;
            h += a;
            b += g;
            h ^= a >>> 9;
            c += h;
            a += b;

            mem[i] = a;
            mem[i + 1] = b;
            mem[i + 2] = c;
            mem[i + 3] = d;
            mem[i + 4] = e;
            mem[i + 5] = f;
            mem[i + 6] = g;
            mem[i + 7] = h;
        }

        generate();
        gen = SIZE;
    }

    private void generate() {
        int x, y;

        cnt += 1;
        brs += cnt;

        for (int i = 0; i < SIZE; ++i) {
            switch (i & 3) {
                case 0:
                    acc ^= acc << 13;
                    break;
                case 1:
                    acc ^= acc >>> 6;
                    break;
                case 2:
                    acc ^= acc << 2;
                    break;
                case 3:
                    acc ^= acc >>> 16;
                    break;
            }
            x = mem[i];
            acc += mem[255 & 128 + i];
            mem[i] = y = acc + mem[(x & MASK) >> 2] + brs;
            rsl[i] = brs = x + mem[((y >> SIZEL) & MASK) >> 2];
        }
    }

}
