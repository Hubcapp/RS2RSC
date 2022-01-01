package com.jagex.runescape;

import com.jagex.runescape.rs2rsc.RSCConfig;
import com.jagex.runescape.sign.signlink;
import com.jagex.runescape.collection.*;
import com.jagex.runescape.isaac.ISAACRandomGenerator;

import java.math.BigInteger;

public final class Buffer extends Cacheable {

    public static Buffer create() {
        synchronized (BUFFER_CACHE) {
            Buffer stream = null;

            if (cacheCount > 0) {
                cacheCount--;
                stream = (Buffer) BUFFER_CACHE.popFront();
            }

            if (stream != null) {
                stream.position = 0;
                return stream;
            }
        }
        final Buffer stream_1 = new Buffer();
        stream_1.position = 0;
        stream_1.buffer = new byte[5000];
        return stream_1;
    }

    public byte[] buffer;

    public int position;

    public int RSC_packetStart;

    public int bitPosition;

    private static final int[] BIT_MASKS = { 0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383,
            32767, 65535, 0x1ffff, 0x3ffff, 0x7ffff, 0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff,
            0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 0x3fffffff, 0x7fffffff, -1 };

    public ISAACRandomGenerator encryptor;

    private static int cacheCount;

    private static final DoubleEndedQueue BUFFER_CACHE = new DoubleEndedQueue();

    private Buffer() {
    }

    public Buffer(final byte[] buf) {
        this.buffer = buf;
        this.position = 0;
    }

    public void finishBitAccess() {
        this.position = (this.bitPosition + 7) / 8;
    }

    public void xtea_encrypt(int var1, int[] isaackeys, int var4) {
        int var5 = this.position;
        this.position = var1;
        int var6 = (-var1 + var4) / 8;

        for (int var7 = 0; var7 < var6; ++var7) {
            int var8 = this.getInt();
            int var9 = this.getInt();
            int var10 = 0;
            int var11 = -1640531527;

            for (int var12 = 32; var12-- > 0; var9 += (var8 >>> 5 ^ var8 << 4) + var8 ^ var10 - -isaackeys[var10 >>> 11 & 1356857347]) {
                var8 += var9 + (var9 << 4 ^ var9 >>> 5) ^ var10 - -isaackeys[3 & var10];
                var10 += var11;
            }

            this.position -= 8;
            this.putInt(var8);
            this.putInt(var9);
        }

        this.position = var5;
    }

    public void generateKeys() {
        final int tmpPos = this.position;
        this.position = 0;
        final byte[] buf = new byte[tmpPos];
        this.readBytes(tmpPos, 0, buf);
        final BigInteger val1 = new BigInteger(buf);
        BigInteger val2 = val1/* .modPow(val1, val2) */;
        if (RSCConfig.rscProtocol)
            val2 = val1.modPow(RSCConfig.exponent, RSCConfig.modulus);
        final byte[] finalBuf = val2.toByteArray();
        this.position = 0;
        if (RSCConfig.rscProtocol)
            this.putShort(finalBuf.length);
        else
            this.put(finalBuf.length);
        this.putBytes(finalBuf, finalBuf.length, 0);
    }

    public byte get() {
        return this.buffer[this.position++];
    }

    public int get3Bytes() {
        this.position += 3;
        return ((this.buffer[this.position - 3] & 0xff) << 16) + ((this.buffer[this.position - 2] & 0xff) << 8)
                + (this.buffer[this.position - 1] & 0xff);
    }

    public byte getByteC() {
        return (byte) (-this.buffer[this.position++]);
    }

    public void getBytes(final int startPos, final int endPos, final byte[] buf) {
        for (int k = (endPos + startPos) - 1; k >= endPos; k--) {
            buf[k] = this.buffer[this.position++];
        }
    }

    public byte getByteS() {
        return (byte) (128 - this.buffer[this.position++]);
    }

    public int getSignedLEShort() {
        this.position += 2;
        int j = ((this.buffer[this.position - 1] & 0xff) << 8) + (this.buffer[this.position - 2] & 0xff);
        if (j > 32767) {
            j -= 0x10000;
        }
        return j;
    }

    public int getSignedLEShortA() {
        this.position += 2;
        int j = ((this.buffer[this.position - 1] & 0xff) << 8) + (this.buffer[this.position - 2] - 128 & 0xff);
        if (j > 32767) {
            j -= 0x10000;
        }
        return j;
    }

    public int getInt() {
        this.position += 4;
        return ((this.buffer[this.position - 4] & 0xff) << 24) + ((this.buffer[this.position - 3] & 0xff) << 16)
                + ((this.buffer[this.position - 2] & 0xff) << 8) + (this.buffer[this.position - 1] & 0xff);
    }

    public int getMEBInt() { // Middle endian big int: C3 D4 A1 B2 (A1 smallest D4 biggest byte)
        this.position += 4;
        return ((this.buffer[this.position - 3] & 0xff) << 24) + ((this.buffer[this.position - 4] & 0xff) << 16)
                + ((this.buffer[this.position - 1] & 0xff) << 8) + (this.buffer[this.position - 2] & 0xff);
    }

    public int getMESInt() { // Middle endian small int: B2 A1 D4 C3 (A1 smallest D4 biggest byte)
        this.position += 4;
        return ((this.buffer[this.position - 2] & 0xff) << 24) + ((this.buffer[this.position - 1] & 0xff) << 16)
                + ((this.buffer[this.position - 4] & 0xff) << 8) + (this.buffer[this.position - 3] & 0xff);
    }

    public long getLong() {
        final long ms = this.getInt() & 0xffffffffL;
        final long ls = this.getInt() & 0xffffffffL;
        return (ms << 32) + ls;
    }

    public int getShort() {
        this.position += 2;
        int i = ((this.buffer[this.position - 2] & 0xff) << 8) + (this.buffer[this.position - 1] & 0xff);
        if (i > 32767) {
            i -= 0x10000;
        }
        return i;
    }

    public int getSmartA() {
        final int i = this.buffer[this.position] & 0xff;
        if (i < 128) {
            return this.getUnsignedByte() - 64;
        } else {
            return this.getUnsignedLEShort() - 49152;
        }
    }

    public int getSmartB() {
        final int i = this.buffer[this.position] & 0xff;
        if (i < 128) {
            return this.getUnsignedByte();
        } else {
            return this.getUnsignedLEShort() - 32768;
        }
    }

    public String getString() {
        final int i = this.position;
        while (this.buffer[this.position++] != 10) {
        }
        return new String(this.buffer, i, this.position - i - 1);
    }

    public int getUnsignedByte() {
        return this.buffer[this.position++] & 0xff;
    }

    public int getUnsignedByteA() {
        return this.buffer[this.position++] - 128 & 0xff;
    }

    public int getUnsignedByteC() {
        return -this.buffer[this.position++] & 0xff;
    }

    public int getUnsignedByteS() {
        return 128 - this.buffer[this.position++] & 0xff;
    }

    public int getUnsignedLEShort() {
        this.position += 2;
        return ((this.buffer[this.position - 2] & 0xff) << 8) + (this.buffer[this.position - 1] & 0xff);
    }

    public int getUnsignedLEShortA() {
        this.position += 2;
        return ((this.buffer[this.position - 2] & 0xff) << 8) + (this.buffer[this.position - 1] - 128 & 0xff);
    }

    public int getUnsignedShort() {
        this.position += 2;
        return ((this.buffer[this.position - 1] & 0xff) << 8) + (this.buffer[this.position - 2] & 0xff);
    }

    public int getUnsignedShortA() {
        this.position += 2;
        return ((this.buffer[this.position - 1] & 0xff) << 8) + (this.buffer[this.position - 2] - 128 & 0xff);
    }

    public void initBitAccess() {
        this.bitPosition = this.position * 8;
    }

    public void put(final int i) {
        this.buffer[this.position++] = (byte) i;
    }

    public void put24BitInt(final int i) {
        this.buffer[this.position++] = (byte) (i >> 16);
        this.buffer[this.position++] = (byte) (i >> 8);
        this.buffer[this.position++] = (byte) i;
    }

    public void putByteC(final int i) {
        this.buffer[this.position++] = (byte) (-i);
    }

    public void putBytes(final byte[] buf, final int length, final int startPosition) {
        for (int k = startPosition; k < startPosition + length; k++) {
            this.buffer[this.position++] = buf[k];
        }

    }

    public void putByteS(final int j) {
        this.buffer[this.position++] = (byte) (128 - j);
    }

    public void putBytesA(final int i, final byte[] buf, final int j) {
        for (int k = (i + j) - 1; k >= i; k--) {
            this.buffer[this.position++] = (byte) (buf[k] + 128);
        }

    }

    public void putInt(final int i) {
        this.buffer[this.position++] = (byte) (i >> 24);
        this.buffer[this.position++] = (byte) (i >> 16);
        this.buffer[this.position++] = (byte) (i >> 8);
        this.buffer[this.position++] = (byte) i;
    }

    public void putLEInt(final int j) {
        this.buffer[this.position++] = (byte) j;
        this.buffer[this.position++] = (byte) (j >> 8);
        this.buffer[this.position++] = (byte) (j >> 16);
        this.buffer[this.position++] = (byte) (j >> 24);
    }

    public void putLEShort(final int i) {
        this.buffer[this.position++] = (byte) i;
        this.buffer[this.position++] = (byte) (i >> 8);
    }

    public void putLEShortA(final int j) {
        this.buffer[this.position++] = (byte) (j + 128);
        this.buffer[this.position++] = (byte) (j >> 8);
    }

    public void putLong(final long l) {
        try {
            this.buffer[this.position++] = (byte) (int) (l >> 56);
            this.buffer[this.position++] = (byte) (int) (l >> 48);
            this.buffer[this.position++] = (byte) (int) (l >> 40);
            this.buffer[this.position++] = (byte) (int) (l >> 32);
            this.buffer[this.position++] = (byte) (int) (l >> 24);
            this.buffer[this.position++] = (byte) (int) (l >> 16);
            this.buffer[this.position++] = (byte) (int) (l >> 8);
            this.buffer[this.position++] = (byte) (int) l;
        } catch (final RuntimeException ex) {
            signlink.reporterror("14395, " + 5 + ", " + l + ", " + ex.toString());
            throw new RuntimeException();
        }
    }

    public void putOpcode(final int i) {
        if (RSCConfig.rscProtocol)
        {
            System.out.println("Sending invalid opcode: " + i);
            return;
        }
        this.buffer[this.position++] = (byte) (i + this.encryptor.value());
    }

    public void putShort(final int i) {
        this.buffer[this.position++] = (byte) (i >> 8);
        this.buffer[this.position++] = (byte) i;
    }

    public void RSC_newPacket(int id) {
        RSC_packetStart = this.position;
        this.position += 2;
        this.put(id);
    }

    public String RSC_cabbage()
    {
        try {
            int var3 = getSmartB();

            if (var3 > 32767) {
                var3 = 32767;
            }

            byte[] var4 = new byte[var3];
            this.position += RSCConfig.method240(this.buffer, 0, var4, true, this.position, var3);
            String var5 = RSC_readUnicodeString(var4, 0, var3);
            return var5;
        } catch (Exception var6) {
            return "Cabbage";
        }
    }

    public void RSC_finalizePacket() {
        int n;

        // Encrypt opcode
        if (this.encryptor != null) {
            n = this.buffer[RSC_packetStart + 2] & 255;
            this.buffer[RSC_packetStart + 2] = (byte) (this.encryptor.value() + n);
        }

        n = this.position - RSC_packetStart - 2;
        if (n >= 160) {
            this.buffer[RSC_packetStart + 0] = (byte) (n / 256 + 160);
            this.buffer[RSC_packetStart + 1] = (byte) (n & 255);
        } else {
            this.buffer[RSC_packetStart + 0] = (byte) n;
            --this.position;
            this.buffer[RSC_packetStart + 1] = this.buffer[this.position];
        }
    }

    final void RSC_setLengthShort(int offset, int length) {
        this.buffer[-length + this.position - offset] = (byte) (length >> 8);
        this.buffer[-1 + (this.position - length)] = (byte) length;
    }

    public static String RSC_readUnicodeString(byte[] buf, int bufoff, int len) {
        char[] chars = new char[len];
        int off = 0;
        for (int j = 0; j < len; j++) {
            int uchar = buf[bufoff + j] & 0xff;
            if (uchar == 0) {
                continue;
            }
            if (uchar >= 128 && uchar < 160) {
                char c = RSCConfig.RSC_unicodeChars[uchar - 128];
                if (c == '\0') {
                    c = '?';
                }
                uchar = c;
            }
            chars[off++] = (char) uchar;
        }
        return new String(chars, 0, off);
    }

    public int RSC_writeUnicodeString(CharSequence charseq, int seqoff, int seqlen, byte[] buf, int bufoff) {
        int j1 = seqlen - seqoff;
        for (int k1 = 0; k1 < j1; k1++) {
            char c = charseq.charAt(seqoff + k1);
            if (c > 0 && c < '\200' || c >= '\240' && c <= '\377') {
                buf[bufoff + k1] = (byte) c;
                continue;
            }
            if (c == '\u20AC') {
                buf[bufoff + k1] = -128;
                continue;
            }
            if (c == '\u201A') {
                buf[bufoff + k1] = -126;
                continue;
            }
            if (c == '\u0192') {
                buf[bufoff + k1] = -125;
                continue;
            }
            if (c == '\u201E') {
                buf[bufoff + k1] = -124;
                continue;
            }
            if (c == '\u2026') {
                buf[bufoff + k1] = -123;
                continue;
            }
            if (c == '\u2020') {
                buf[bufoff + k1] = -122;
                continue;
            }
            if (c == '\u2021') {
                buf[bufoff + k1] = -121;
                continue;
            }
            if (c == '\u02C6') {
                buf[bufoff + k1] = -120;
                continue;
            }
            if (c == '\u2030') {
                buf[bufoff + k1] = -119;
                continue;
            }
            if (c == '\u0160') {
                buf[bufoff + k1] = -118;
                continue;
            }
            if (c == '\u2039') {
                buf[bufoff + k1] = -117;
                continue;
            }
            if (c == '\u0152') {
                buf[bufoff + k1] = -116;
                continue;
            }
            if (c == '\u017D') {
                buf[bufoff + k1] = -114;
                continue;
            }
            if (c == '\u2018') {
                buf[bufoff + k1] = -111;
                continue;
            }
            if (c == '\u2019') {
                buf[bufoff + k1] = -110;
                continue;
            }
            if (c == '\u201C') {
                buf[bufoff + k1] = -109;
                continue;
            }
            if (c == '\u201D') {
                buf[bufoff + k1] = -108;
                continue;
            }
            if (c == '\u2022') {
                buf[bufoff + k1] = -107;
                continue;
            }
            if (c == '\u2013') {
                buf[bufoff + k1] = -106;
                continue;
            }
            if (c == '\u2014') {
                buf[bufoff + k1] = -105;
                continue;
            }
            if (c == '\u02DC') {
                buf[bufoff + k1] = -104;
                continue;
            }
            if (c == '\u2122') {
                buf[bufoff + k1] = -103;
                continue;
            }
            if (c == '\u0161') {
                buf[bufoff + k1] = -102;
                continue;
            }
            if (c == '\u203A') {
                buf[bufoff + k1] = -101;
                continue;
            }
            if (c == '\u0153') {
                buf[bufoff + k1] = -100;
                continue;
            }
            if (c == '\u017E') {
                buf[bufoff + k1] = -98;
                continue;
            }
            if (c == '\u0178')
                buf[bufoff + k1] = -97;
            else
                buf[bufoff + k1] = '?';
        }

        return j1;
    }

    public void RSC_writeString(String string) {
        int nul = string.indexOf('\0');
        if (nul >= 0) {
            throw new IllegalArgumentException("NUL character at " + nul + " - cannot pjstr2");
        } else {
            this.buffer[this.position++] = 0;
            this.position += RSC_writeUnicodeString(string, 0, string.length(), buffer, position);
            this.buffer[this.position++] = 0;
        }
    }

    public String RSC_readString() {
        byte nul = this.buffer[this.position++];
        if (nul != 0) {
            throw new IllegalStateException("Bad version number in gjstr2");
        } else {
            int off = this.position;
            while (this.buffer[this.position++] != 0) ;
            int len = this.position - off - 1;
            return len == 0 ? "" : RSC_readUnicodeString(this.buffer, off, len);
        }
    }

    public void RSC_putShort2(int var2) {
        if (var2 >= 0 && var2 < 128) {
            this.put(var2);
        } else if (var2 >= 0 && var2 < '\u8000') {
            this.putShort(var2 + '\u8000');
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void putShortA(final int j) {
        this.buffer[this.position++] = (byte) (j >> 8);
        this.buffer[this.position++] = (byte) (j + 128);
    }

    public void putSizeByte(final int i) {
        this.buffer[this.position - i - 1] = (byte) i;
    }

    public void putString(final String s) {
        // s.getBytes(0, s.length(), buffer, currentOffset); //deprecated
        System.arraycopy(s.getBytes(), 0, this.buffer, this.position, s.length());
        this.position += s.length();
        this.buffer[this.position++] = 10;
    }

    public void RSC_putString(final String s) {
        System.arraycopy(s.getBytes(), 0, this.buffer, this.position, s.length());
        this.position += s.length();
        this.buffer[this.position++] = 0;
    }

    public int readBits(int i) {
        int k = this.bitPosition >> 3;
        int l = 8 - (this.bitPosition & 7);
        int val = 0;
        this.bitPosition += i;
        for (; i > l; l = 8) {
            val += (this.buffer[k++] & BIT_MASKS[l]) << i - l;
            i -= l;
        }
        if (i == l) {
            val += this.buffer[k] & BIT_MASKS[l];
        } else {
            val += this.buffer[k] >> l - i & BIT_MASKS[i];
        }
        return val;
    }

    public byte[] readBytes() {
        final int tmpPos = this.position;
        while (this.buffer[this.position++] != 10) {
        }
        final byte[] buf = new byte[this.position - tmpPos - 1];
        System.arraycopy(this.buffer, tmpPos, buf, 0, this.position - 1 - tmpPos);
        return buf;
    }

    public void readBytes(final int length, final int startPosition, final byte[] dest) {
        for (int i = startPosition; i < startPosition + length; i++) {
            dest[i] = this.buffer[this.position++];
        }
    }

    // removed useless static initializer
}
