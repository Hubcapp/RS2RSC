package com.jagex.runescape.rs2rsc;

import com.jagex.runescape.Buffer;
import com.jagex.runescape.Client;

import java.math.BigInteger;

public class RSCConfig {
    public static boolean rscProtocol = true;
    public static int rscVersion = 235;
    public static String ip = "game.openrsc.com";
    public static int port = 43596;
    public static BigInteger modulus = new BigInteger("7112866275597968156550007489163685737528267584779959617759901583041864787078477876689003422509099353805015177703670715380710894892460637136582066351659813");
    public static BigInteger exponent = new BigInteger("65537");
    public static int jaggrabPort = 43595; // unused, technically

    public static char[] RSC_unicodeChars = {
            '\u20AC', '\0'/**/, '\u201A', '\u0192', '\u201E', '\u2026', '\u2020', '\u2021', '\u02C6', '\u2030',
            '\u0160', '\u2039', '\u0152', '\0'/**/, '\u017D', '\0'/**/, '\0'/**/, '\u2018', '\u2019', '\u201C',
            '\u201D', '\u2022', '\u2013', '\u2014', '\u02DC', '\u2122', '\u0161', '\u203A', '\u0153', '\0'/**/,
            '\u017E', '\u0178'
    };

    private static int[] anIntArray208;
    private static byte[] aByteArray210;

    public static byte[] RSC_stringToUnicode(String str) {
        int strlen = str.length();
        byte[] buf = new byte[strlen];
        for (int i = 0; i < strlen; i++) {
            char c = str.charAt(i);
            if (c > 0 && c < '\200' || c >= '\240' && c <= '\377') {
                buf[i] = (byte) c;
                continue;
            }
            if (c == '\u20AC') {
                buf[i] = -128;
                continue;
            }
            if (c == '\u201A') {
                buf[i] = -126;
                continue;
            }
            if (c == '\u0192') {
                buf[i] = -125;
                continue;
            }
            if (c == '\u201E') {
                buf[i] = -124;
                continue;
            }
            if (c == '\u2026') {
                buf[i] = -123;
                continue;
            }
            if (c == '\u2020') {
                buf[i] = -122;
                continue;
            }
            if (c == '\u2021') {
                buf[i] = -121;
                continue;
            }
            if (c == '\u02C6') {
                buf[i] = -120;
                continue;
            }
            if (c == '\u2030') {
                buf[i] = -119;
                continue;
            }
            if (c == '\u0160') {
                buf[i] = -118;
                continue;
            }
            if (c == '\u2039') {
                buf[i] = -117;
                continue;
            }
            if (c == '\u0152') {
                buf[i] = -116;
                continue;
            }
            if (c == '\u017D') {
                buf[i] = -114;
                continue;
            }
            if (c == '\u2018') {
                buf[i] = -111;
                continue;
            }
            if (c == '\u2019') {
                buf[i] = -110;
                continue;
            }
            if (c == '\u201C') {
                buf[i] = -109;
                continue;
            }
            if (c == '\u201D') {
                buf[i] = -108;
                continue;
            }
            if (c == '\u2022') {
                buf[i] = -107;
                continue;
            }
            if (c == '\u2013') {
                buf[i] = -106;
                continue;
            }
            if (c == '\u2014') {
                buf[i] = -105;
                continue;
            }
            if (c == '\u02DC') {
                buf[i] = -104;
                continue;
            }
            if (c == '\u2122') {
                buf[+i] = -103;
                continue;
            }
            if (c == '\u0161') {
                buf[i] = -102;
                continue;
            }
            if (c == '\u203A') {
                buf[i] = -101;
                continue;
            }
            if (c == '\u0153') {
                buf[i] = -100;
                continue;
            }
            if (c == '\u017E') {
                buf[i] = -98;
                continue;
            }
            if (c == '\u0178')
                buf[i] = -97;
            else
                buf[i] = '?';
        }

        return buf;
    }

    public static int method241(int var1, int var2, byte[] var3, byte[] var4, int var5, int var6) {
        int var7 = 0;
        var2 += var1;

        int var8;
        for (var8 = var6 << 3; var1 < var2; ++var1) {
            int var9 = var4[var1] & 255;
            int var10 = anIntArray208[var9];
            byte var11 = aByteArray210[var9];
            if (var11 == 0) {
                throw new RuntimeException("No codeword for data value " + var9);
            }

            int var12 = var8 >> 3;
            int var13 = var8 & 7;
            var7 &= -var13 >> 31;
            int var14 = (var11 + var13 + -1 >> 3) + var12;
            var8 += var11;
            var13 += 24;
            var3[var12] = (byte) (var7 = (var7 | (var10 >>> var13)));
            if (var14 > var12) {
                ++var12;
                var13 -= 8;
                var3[var12] = (byte) (var7 = var10 >>> var13);
                if (var12 < var14) {
                    ++var12;
                    var13 -= 8;
                    var3[var12] = (byte) (var7 = var10 >>> var13);
                    if (var14 > var12) {
                        var13 -= 8;
                        ++var12;
                        var3[var12] = (byte) (var7 = var10 >>> var13);
                        if (var12 < var14) {
                            ++var12;
                            var13 -= 8;
                            var3[var12] = (byte) (var7 = var10 << -var13);
                        }
                    }
                }
            }
        }

        return (7 + var8 >> 3) - var6;
    }

    public static void Start()
    {
        if (!rscProtocol)
            return;

        aByteArray210 = new byte[]{(byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 21, (byte) 22, (byte) 22, (byte) 20, (byte) 22, (byte) 22, (byte) 22, (byte) 21, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 3, (byte) 8, (byte) 22, (byte) 16, (byte) 22, (byte) 16, (byte) 17, (byte) 7, (byte) 13, (byte) 13, (byte) 13, (byte) 16, (byte) 7, (byte) 10, (byte) 6, (byte) 16, (byte) 10, (byte) 11, (byte) 12, (byte) 12, (byte) 12, (byte) 12, (byte) 13, (byte) 13, (byte) 14, (byte) 14, (byte) 11, (byte) 14, (byte) 19, (byte) 15, (byte) 17, (byte) 8, (byte) 11, (byte) 9, (byte) 10, (byte) 10, (byte) 10, (byte) 10, (byte) 11, (byte) 10, (byte) 9, (byte) 7, (byte) 12, (byte) 11, (byte) 10, (byte) 10, (byte) 9, (byte) 10, (byte) 10, (byte) 12, (byte) 10, (byte) 9, (byte) 8, (byte) 12, (byte) 12, (byte) 9, (byte) 14, (byte) 8, (byte) 12, (byte) 17, (byte) 16, (byte) 17, (byte) 22, (byte) 13, (byte) 21, (byte) 4, (byte) 7, (byte) 6, (byte) 5, (byte) 3, (byte) 6, (byte) 6, (byte) 5, (byte) 4, (byte) 10, (byte) 7, (byte) 5, (byte) 6, (byte) 4, (byte) 4, (byte) 6, (byte) 10, (byte) 5, (byte) 4, (byte) 4, (byte) 5, (byte) 7, (byte) 6, (byte) 10, (byte) 6, (byte) 10, (byte) 22, (byte) 19, (byte) 22, (byte) 14, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 22, (byte) 21, (byte) 22, (byte) 21, (byte) 22, (byte) 22, (byte) 22, (byte) 21, (byte) 22, (byte) 22};
        anIntArray208 = new int[aByteArray210.length];
        int[] var3 = new int[33];

        for (int var5 = 0; var5 < aByteArray210.length; ++var5) {
            byte var6 = aByteArray210[var5];
            if (var6 != 0) {
                int var7 = 1 << 32 + -var6;
                int var8 = var3[var6];
                anIntArray208[var5] = var8;
                int var9;
                int var10;
                int var11;
                int var12;

                if ((var8 & var7) == 0) {
                    var9 = var8 | var7;

                    for (var10 = -1 + var6; var10 >= 1; --var10) {
                        var11 = var3[var10];
                        if (var11 != var8) {
                            break;
                        }

                        var12 = 1 << 32 + -var10;
                        if ((var11 & var12) != 0) {
                            var3[var10] = var3[var10 - 1];
                            break;
                        }

                        var3[var10] = var11 | var12;
                    }
                }
                else
                {
                    var9 = var3[-1 + var6];
                }

                var3[var6] = var9;

                for (var10 = 1 + var6; var10 <= 32; ++var10) {
                    if (var8 == var3[var10]) {
                        var3[var10] = var9;
                    }
                }
            }
        }

        System.out.println("using rsc protocol");
    }

    public static int RSC_HandleOpcode(int opcode, Client client, Buffer buffer)
    {
        if (!rscProtocol)
            return opcode;

        switch (opcode)
        {
            case 51: // SERVER_PRIVACY_SETTINGS
            {
                int blockChat = buffer.getUnsignedByte() > 0 ? 2 : 0;
                int blockPrivate = buffer.getUnsignedByte() > 0 ? 2 : 0;
                int blockTrade = buffer.getUnsignedByte() > 0 ? 2 : 0;
                int blockDuel = buffer.getUnsignedByte() > 0 ? 2 : 0;
                client.publicChatMode = blockChat;
                client.privateChatMode = blockPrivate;
                client.tradeMode = Math.max(blockTrade, blockDuel);
                client.updateChatSettings = true;
                client.redrawChatbox = true;
                break;
            }
            case 131: // SERVER_SEND_MESSAGE
            {
                int type = buffer.getUnsignedByte();
                int unk = buffer.getUnsignedByte();
                String message = buffer.RSC_readString();
                String sender = null;
                String clan = null;
                String color = null;
                if ((unk & 1) != 0)
                    sender = buffer.RSC_readString();
                if ((1 & unk) != 0)
                    clan = buffer.RSC_readString();
                if ((unk & 2) != 0)
                    color = buffer.RSC_readString();

                int rs2Type = 0;
                switch (type)
                {
                    case 3: // Quest
                        rs2Type = 0;
                        break;
                    default:
                        System.out.println("Unhandled message type: " + type);
                        break;
                }

                client.pushMessage(message, rs2Type, sender);
                break;
            }
            default:
            {
                System.out.println("Unhandled opcode: " + opcode);
                break;
            }
        }

        return -1;
    }

    public static int RSC_ResponseCode(int responseCode)
    {
        if (!rscProtocol)
            return responseCode;

        System.out.println("login response: " + responseCode);

        // Login success
        if ((responseCode & 64) != 0)
            responseCode = 2;

        switch (responseCode)
        {
            case 47:
                break;
        }

        return responseCode;
    }

    public static String RSC_format(int var0, String var1, byte var2) {
        int var3 = -109 / ((-78 - var2) / 46);
        String var4 = "";

        for (int var5 = 0; var5 < var0; ++var5) {
            if (var5 >= var1.length()) {
                var4 = var4 + " ";
            } else {
                char var6 = var1.charAt(var5);
                if (var6 >= 97 && var6 <= 122) {
                    var4 = var4 + var6;
                } else if (var6 >= 65 && var6 <= 90) {
                    var4 = var4 + var6;
                } else if (var6 >= 48 && var6 <= 57) {
                    var4 = var4 + var6;
                } else {
                    var4 = var4 + '_';
                }
            }
        }

        return var4;
    }
}
