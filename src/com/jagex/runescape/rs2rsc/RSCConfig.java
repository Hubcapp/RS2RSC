package com.jagex.runescape.rs2rsc;

import com.jagex.runescape.Buffer;
import com.jagex.runescape.Client;
import com.jagex.runescape.Player;
import com.jagex.runescape.TextClass;
import com.jagex.runescape.definition.EntityDefinition;

import java.math.BigInteger;

public class RSCConfig {
    public static boolean rscProtocol = true;
    public static int rscVersion = 235;
    public static String ip = "game.openrsc.com";
    public static int port = 43596;
    public static BigInteger modulus = new BigInteger("7112866275597968156550007489163685737528267584779959617759901583041864787078477876689003422509099353805015177703670715380710894892460637136582066351659813");
    public static BigInteger exponent = new BigInteger("65537");
    public static int jaggrabPort = 43595; // unused, technically

    public static int DEFAULT_BRIGHTNESS = 1;

    public static char[] RSC_unicodeChars = {
            '\u20AC', '\0'/**/, '\u201A', '\u0192', '\u201E', '\u2026', '\u2020', '\u2021', '\u02C6', '\u2030',
            '\u0160', '\u2039', '\u0152', '\0'/**/, '\u017D', '\0'/**/, '\0'/**/, '\u2018', '\u2019', '\u201C',
            '\u201D', '\u2022', '\u2013', '\u2014', '\u02DC', '\u2122', '\u0161', '\u203A', '\u0153', '\0'/**/,
            '\u017E', '\u0178'
    };

    private static int[] anIntArray208;
    private static int[] anIntArray209;
    private static byte[] aByteArray210;
    public static int localServerIndex;

    private static int magicLoc = 128;
    private static int localRegionX;
    private static int localRegionY;
    private static int regionX;
    private static int regionY;

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

    public static int method240(byte[] var1, int var2, byte[] var3, boolean var4, int var5, int var6) {
        if (var6 == 0) {
            return 0;
        } else {
            var6 += var2;
            int var7 = 0;
            if (!var4) {
                method240((byte[]) null, -4, (byte[]) null, false, -81, -40);
            }

            int var8 = var5;

            while (true) {
                byte var9 = var1[var8];
                if (var9 >= 0) {
                    ++var7;
                } else {
                    var7 = anIntArray209[var7];
                }

                int var10;
                if ((var10 = anIntArray209[var7]) < 0) {
                    var3[var2++] = (byte) (~var10);
                    if (var6 <= var2) {
                        break;
                    }

                    var7 = 0;
                }

                if ((64 & var9) == 0) {
                    ++var7;
                } else {
                    var7 = anIntArray209[var7];
                }

                if ((var10 = anIntArray209[var7]) < 0) {
                    var3[var2++] = (byte) (~var10);
                    if (var2 >= var6) {
                        break;
                    }

                    var7 = 0;
                }

                if ((var9 & 32) != 0) {
                    var7 = anIntArray209[var7];
                } else {
                    ++var7;
                }

                if ((var10 = anIntArray209[var7]) < 0) {
                    var3[var2++] = (byte) (~var10);
                    if (var2 >= var6) {
                        break;
                    }

                    var7 = 0;
                }

                if ((var9 & 16) == 0) {
                    ++var7;
                } else {
                    var7 = anIntArray209[var7];
                }

                if ((var10 = anIntArray209[var7]) < 0) {
                    var3[var2++] = (byte) (~var10);
                    if (var2 >= var6) {
                        break;
                    }

                    var7 = 0;
                }

                if ((var9 & 8) == 0) {
                    ++var7;
                } else {
                    var7 = anIntArray209[var7];
                }

                if ((var10 = anIntArray209[var7]) < 0) {
                    var3[var2++] = (byte) (~var10);
                    if (var6 <= var2) {
                        break;
                    }

                    var7 = 0;
                }

                if ((4 & var9) == 0) {
                    ++var7;
                } else {
                    var7 = anIntArray209[var7];
                }

                if ((var10 = anIntArray209[var7]) < 0) {
                    var3[var2++] = (byte) (~var10);
                    if (var2 >= var6) {
                        break;
                    }

                    var7 = 0;
                }

                if ((2 & var9) != 0) {
                    var7 = anIntArray209[var7];
                } else {
                    ++var7;
                }

                if ((var10 = anIntArray209[var7]) < 0) {
                    var3[var2++] = (byte) (~var10);
                    if (var2 >= var6) {
                        break;
                    }

                    var7 = 0;
                }

                if ((var9 & 1) == 0) {
                    ++var7;
                } else {
                    var7 = anIntArray209[var7];
                }

                if ((var10 = anIntArray209[var7]) < 0) {
                    var3[var2++] = (byte) (~var10);
                    if (var6 <= var2) {
                        break;
                    }

                    var7 = 0;
                }

                ++var8;
            }

            return -var5 + var8 - -1;
        }
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
        anIntArray209 = new int[8];
        int[] var3 = new int[33];
        int var4 = 0;

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

                var11 = 0;

                for (var12 = 0; var12 < var6; ++var12) {
                    int var13 = Integer.MIN_VALUE >>> var12;
                    if ((var8 & var13) != 0) {
                        if (anIntArray209[var11] == 0) {
                            anIntArray209[var11] = var4;
                        }

                        var11 = anIntArray209[var11];
                    } else {
                        ++var11;
                    }

                    var13 >>>= 1;
                    if (var11 >= anIntArray209.length) {
                        int[] var14 = new int[2 * anIntArray209.length];

                        for (int var15 = 0; var15 < anIntArray209.length; ++var15) {
                            var14[var15] = anIntArray209[var15];
                        }

                        anIntArray209 = var14;
                    }
                }

                if (var11 >= var4)
                    var4 = var11 - -1;

                anIntArray209[var11] = ~var5;
            }
        }

        System.out.println("using rsc protocol");
    }

    public static int RSC_convertXP(int xp)
    {
        return xp / 4;
    }

    public static void RSC_HandleLogin(Client client)
    {
        if (!rscProtocol)
        {
            return;
        }
        /*
            TODO:

            - Remove unused skills
            - Remove accept aid
         */

        // Set settings
        client.sendConfig(166, DEFAULT_BRIGHTNESS + 1); // Brightness
        client.sendConfig(287, 0); // Split private chat
        client.sendConfig(171, 0); // Chat effects

        // Set sidebar interfaces
        client.setSidebarID(1, 3917); // Stats
        client.setSidebarID(8, 5065); // Friends
        client.setSidebarID(10, 2449); // Logout
        client.setSidebarID(11, 4445); // Settings

        // Setup friends list
        client.friendListStatus = 2;
    }

    public static void RSC_HandleInterface(int actionID, Client client, Buffer buffer)
    {
        switch (actionID)
        {
            case 6279: // Mouse Buttons (One)
            {
                buffer.RSC_newPacket(111);
                buffer.put(2);
                buffer.put(1);
                buffer.RSC_finalizePacket();
                break;
            }
            case 6278: // Mouse Buttons (Two)
            {
                buffer.RSC_newPacket(111);
                buffer.put(2);
                buffer.put(0);
                buffer.RSC_finalizePacket();
                break;
            }
            case 2458: // Logout button
            {
                // TODO: Handle combat timer
                buffer.RSC_newPacket(102);
                buffer.RSC_finalizePacket();
                break;
            }
            default:
            {
                System.out.println("Unhandled interface action id: " + actionID);
                break;
            }
        }
    }

    public static Player RSC_getPlayer(Client client, int serverIndex, int areaX, int areaY)
    {
        // Add player if it doesn't exist
        if (client.players[serverIndex] == null || client.players[serverIndex].index != serverIndex)
        {
            client.players[serverIndex] = new Player();
            client.players[serverIndex].index = serverIndex;
        }

        Player player = client.players[serverIndex];
        boolean isLocal = false;

        if (serverIndex == localServerIndex)
        {
            isLocal = true;
            Client.localPlayer = player;
        }

        player.setPos(areaX, areaY, true);

        // Do we already know about this player?
        if (!isLocal) {
            for (int otherServerIndex : client.localPlayers) {
                if (otherServerIndex == serverIndex) {
                    isLocal = true;
                    break;
                }
            }
        }

        if (!isLocal)
        {
            client.localPlayers[client.localPlayerCount++] = serverIndex;
            System.out.println("Registering " + serverIndex);
        }

        return player;
    }

    public static int RSC_TranslateItem(int itemID)
    {
        switch (itemID)
        {
            case 87: // bronze Axe
                return 1351;
            case 405: // rune Axe
                return 1359;
            case 971: // Santa's hat
                return 1050;
            case 1288: // Cape of legends
                return 1052;
        }

        return 0;
    }

    public static int RSC_TranslateEquipment(int equipID)
    {
        switch (equipID)
        {
            case 1: // Head, without beard
                return -2;
            case 2:
            case 3: // Legs, but they're always the same
                return -1;
            case 4:
                return -6;
            case 5:
                return -5;
            case 7: // Head, with beard
                return -3;
            case 8: // Head, without beard and bald
                return -4;


            case 109: // bronze Axe
                return RSC_TranslateItem(87);
            case 114: // rune Axe
                return RSC_TranslateItem(405);
            case 209: // Santa's hat
                return RSC_TranslateItem(971);
            case 226: // Cape of legends
                return RSC_TranslateItem(1288);


            default:
                System.out.println("Unhandled equipment id: " + equipID);
                break;
        }

        return -1;
    }

    public static boolean RSC_isWeapon(int itemID)
    {
        switch (itemID)
        {
            case 1351: // bronze Axe
            case 1359: // rune Axe
                return true;
        }

        return false;
    }

    public static boolean RSC_isHat(int itemID)
    {
        switch (itemID)
        {
            case 1050: // Santa's hat
                return true;
        }

        return false;
    }

    public static boolean RSC_isCape(int itemID)
    {
        switch (itemID)
        {
            case 1052: // Cape of legends
                return true;
        }

        return false;
    }

    public static int RSC_TranslateTopColor(int color)
    {
        // 1 - Black
        // 3 - Dark Blue
        // 4 - Yellow
        switch (color)
        {
            case 0: // Red
                return 6;
            case 11: // Black
                return 1;
            case 8: // Blue
                return 7;
            case 14: // White
                return 5;
            default:
                System.out.println("Unhandled top color: " + color);
                break;
        }

        return color;
    }

    public static int RSC_TranslateBottomColor(int color)
    {
        // 15 - Light blue
        switch (color)
        {
            case 5: // Dark Green
                return 0;
            case 9: // Blue
                return 8;
            case 11: // Black
                return 2;
            case 14: // White
                return 6;
            default:
                System.out.println("Unhandled bottom color: " + color);
                break;
        }

        return color;
    }

    public static int RSC_TranslateSkinColor(int color)
    {
        // Skin colors seem to remain the same
        // TODO: Double check, until this TODO is gone, it hasn't been checked!
        return color;
    }

    public static int RSC_TranslateHairColor(int color)
    {
        // 5 - Blonde
        // 8 - Blue
        // 9 - Green
        switch (color)
        {
            case 2: // Brown
                return 0;
            case 6: // Orange
                return 10;
            case 7: // White
                return 1;
            default:
                System.out.println("Unhandled hair color: " + color);
                break;
        }

        return color;
    }

    public static int RSC_HandleOpcode(int opcode, Client client, Buffer buffer)
    {
        if (!rscProtocol)
            return opcode;

        int ret = -1;

        switch (opcode)
        {
            case 149:
            {
                String name = buffer.RSC_readString();
                String oldName = buffer.RSC_readString();
                int onlineStatus = buffer.getUnsignedByte();

                boolean loggedIn = (4 & onlineStatus) != 0;
                int index = -1;

                if (loggedIn)
                {
                    String server = buffer.RSC_readString();
                }

                for (int i = 0; i < client.friendsCount; i++)
                {
                    if (client.friendsList[i].equals(name))
                    {
                        index = i;
                        break;
                    }
                }

                if (index == -1)
                {
                    index = client.friendsCount++;

                    client.friendsList[index] = name;
                    client.friendsListAsLongs[index] = TextClass.nameToLong(name.trim());

                    System.out.println("Registering friend " + name);
                }

                System.out.println("Updating friend '" + name + "' '" + oldName + "': " + loggedIn + " " + onlineStatus);

                client.friendsWorldIds[index] = loggedIn ? 10 : 0;
                client.redrawTab = true;

                break;
            }
            case 87:
            {
                String sender = buffer.RSC_readString();
                String message = buffer.RSC_cabbage();
                client.pushMessage(message, 6, sender);
                break;
            }
            case 120:
            {
                String sender = buffer.RSC_readString();
                String senderClan = buffer.RSC_readString();
                int modStatus = buffer.getUnsignedByte();
                long unk = buffer.getLong();
                String message = buffer.RSC_cabbage();
                client.pushMessage(message, 7, sender);
                break;
            }
            case 240: // Settings
            {
                int cameraMode = buffer.getUnsignedByte(); // TODO: Maybe use camera mode?
                client.sendConfig(170, buffer.getUnsignedByte()); // Mouse Buttons
                int sound = buffer.getUnsignedByte();
                break;
            }
            case 191: // Local Player
            {
                buffer.initBitAccess();
                localRegionX = buffer.readBits(11);
                localRegionY = buffer.readBits(13);
                int anim = buffer.readBits(4);

                System.out.println(localRegionX + ", " + localRegionY + " SPAWN");

                int localX = 50;
                int localY = 50;

                // Set local player
                Player localPlayer = RSC_getPlayer(client, localServerIndex, localX, localY);

                // Load region
                client.RSC_loadRegion(384, 384);

                int playerCount = buffer.readBits(8);
                for (int i = 0; i < playerCount; i++)
                {
                    // TODO: CONTINUE HERE
                    int reqUpdate = buffer.readBits(1);
                    if (reqUpdate != 0) {
                        int updateType = buffer.readBits(1);
                        if (updateType != 0)
                        {
                            int unk = buffer.readBits(2);
                            if (unk == 3)
                                continue;
                            int nextAnim = buffer.readBits(2);
                        }
                        else
                        {
                            int nextAnim = buffer.readBits(3);
                        }
                    }
                }

                while (client.packetSize * 8 > buffer.bitPosition + 24)
                {
                    int serverIndex = buffer.readBits(11);
                    int areaX = buffer.readBits(5);
                    if (areaX > 15)
                        areaX -= 32;
                    int areaY = buffer.readBits(5);
                    if (areaY > 15)
                        areaY -= 32;
                    int otherAnim = buffer.readBits(4);

                    int playerX = localX + areaX;
                    int playerY = localY + areaY;

                    System.out.println("Load player " + serverIndex + " " + playerX + ", " + playerY);

                    RSC_getPlayer(client, serverIndex, playerX, playerY);
                }

                buffer.finishBitAccess();
                break;
            }
            case 4:
            {
                client.logout();
                break;
            }
            case 234:
            {
                int playerCount = buffer.getUnsignedLEShort();
                for (int i = 0; i < playerCount; i++)
                {
                    int serverIndex = buffer.getUnsignedLEShort();
                    Player player = client.players[serverIndex];

                    int updateType = buffer.getUnsignedByte();
                    switch (updateType)
                    {
                        case 1:
                        {
                            int mod = buffer.getUnsignedByte();
                            String message = buffer.RSC_cabbage();
                            client.RSC_setTextMessage(player, message);
                            break;
                        }
                        case 5:
                        {
                            buffer.getUnsignedLEShort();
                            String username = buffer.RSC_readString();
                            buffer.RSC_readString();

                            // Handle equipment
                            int weaponID = -1;
                            int hairID = -1;
                            int beardID = -1;
                            int legID = -1;
                            int feetID = -1;
                            int chestID = -1;
                            int armID = -1;
                            int genderID = 0;

                            int hatID = -1;
                            int capeID = -1;

                            int equipCount = buffer.getUnsignedByte();
                            for (int x = 0; x < equipCount; x++) {
                                int id = buffer.getUnsignedByte();
                                if (id == 0)
                                    continue;
                                System.out.println("Equip: " + id);
                                id = RSC_TranslateEquipment(id);
                                if (id == -1)
                                    continue;

                                if (id == -3) // Hair with beard
                                {
                                    hairID = 3;
                                    beardID = 11;
                                }
                                if (id == -4) // No hair
                                {
                                    hairID = 0;
                                    beardID = 14;
                                }
                                if (id == -6) // Long Hair
                                {
                                    hairID = 2;
                                    beardID = 14;
                                }
                                if (id == -2) // Short hair, no beard
                                {
                                    hairID = 5;
                                    beardID = 14;
                                }

                                // Female
                                if (id == -5)
                                {
                                    genderID = 1;
                                    legID = 70;
                                    feetID = 79;
                                    chestID = 56;
                                    armID = 61;
                                }

                                if (RSC_isWeapon(id))
                                    weaponID = id;
                                if (RSC_isHat(id))
                                    hatID = id;
                                if (RSC_isCape(id))
                                    capeID = id;
                            }

                            if (genderID > 0)
                            {
                                if (beardID != 11)
                                    beardID = -1;

                                switch (hairID)
                                {
                                    case 0:
                                        hairID = 45;
                                        break;
                                    case 2:
                                        hairID = 48;
                                        break;
                                    case 3:
                                        // Bearded lady, do nothing
                                        break;
                                    case 5:
                                        hairID = 51;
                                    default:
                                        System.out.println("Unhandled female hair conversion: " + hairID);
                                        break;
                                }
                            }

                            int colorHair = buffer.getUnsignedByte();
                            int colorTop = buffer.getUnsignedByte();
                            int colorBottom = buffer.getUnsignedByte();
                            int colorSkin = buffer.getUnsignedByte();
                            int level = buffer.getUnsignedByte();
                            int skull = buffer.getUnsignedByte();

                            player.name = username;
                            player.npcAppearance = null;
                            player.combatLevel = level;

                            System.out.println(username + ": " + serverIndex);

                            // Set colors
                            // 0 - hair
                            // 1 - top
                            // 2 - bottom
                            // 3 - feet
                            // 4 - skin
                            colorHair = RSC_TranslateHairColor(colorHair);
                            colorTop = RSC_TranslateTopColor(colorTop);
                            colorBottom = RSC_TranslateBottomColor(colorBottom);
                            colorSkin = RSC_TranslateSkinColor(colorSkin);
                            player.bodyPartColour[0] = colorHair;
                            player.bodyPartColour[1] = colorTop;
                            player.bodyPartColour[2] = colorBottom;
                            player.bodyPartColour[4] = colorSkin;

                            // Set animations
                            player.standAnimationId = 0x328;
                            player.standTurnAnimationId = 0x337;
                            player.walkAnimationId = 0x333;
                            player.turnAboutAnimationId = 0x334;
                            player.turnRightAnimationId = 0x335;
                            player.turnLeftAnimationId = 0x336;

                            if (weaponID == -1)
                                weaponID = 0;
                            else
                                weaponID = 0x200 + weaponID;

                            if (legID == -1)
                                legID = 0x100 + 36;
                            else
                                legID = 0x100 + legID;

                            if (chestID == -1)
                                chestID = 0x100 + 19;
                            else
                                chestID = 0x100 + chestID;

                            if (feetID == -1)
                                feetID = 0x100 + 42;
                            else
                                feetID = 0x100 + feetID;

                            if (hairID > -1)
                                hairID = 0x100 + hairID;
                            else
                                hairID = 0;

                            if (armID == -1)
                                armID = 0x100 + 26;
                            else
                                armID = 0x100 + armID;

                            if (beardID > -1)
                                beardID = 0x100 + beardID;
                            else
                                beardID = 0;

                            if (hatID > -1)
                                hatID = 0x200 + hatID;
                            else
                                hatID = 0;

                            if (capeID > -1)
                                capeID = 0x200 + capeID;
                            else
                                capeID = 0;

                            System.out.println(genderID + " GENDER");

                            // Set appearance
                            player.gender = genderID; // 0 - Male
                            player.appearance[0] = hatID; // Hat
                            player.appearance[1] = capeID; // Cape
                            player.appearance[2] = 0; // Amulet
                            player.appearance[3] = weaponID; // Weapon
                            player.appearance[4] = chestID; // Chest
                            player.appearance[5] = 0; // Shield
                            player.appearance[6] = armID; // Arms
                            player.appearance[7] = legID; // Legs
                            player.appearance[8] = hairID; // Head
                            player.appearance[9] = 0x100 + 34; // Hands
                            player.appearance[10] = feetID; // Feet
                            player.appearance[11] = beardID; // Beard
                            player.appearanceOffset = 0L;
                            for (int slot = 0; slot < 12; slot++)
                            {
                                player.appearanceOffset <<= 4;
                                if (player.appearance[slot] >= 256) {
                                    player.appearanceOffset += player.appearance[slot] - 256;
                                }
                            }
                            if (player.appearance[0] >= 256)
                                player.appearanceOffset += player.appearance[0] - 256 >> 4;
                            if (player.appearance[1] >= 256)
                                player.appearanceOffset += player.appearance[1] - 256 >> 8;
                            for (int bodyPart = 0; bodyPart < 5; bodyPart++)
                            {
                                player.appearanceOffset <<= 3;
                                player.appearanceOffset += player.bodyPartColour[bodyPart];
                            }
                            player.appearanceOffset <<= 1;
                            player.appearanceOffset += player.gender;

                            player.visible = true;

                            client.loadingMap = false;
                            break;
                        }
                        default:
                        {
                            System.out.println("Unknown update type: " + updateType);
                            break;
                        }
                    }
                }
                break;
            }
            case 25: // Load Area
            {
                localServerIndex = buffer.getUnsignedLEShort();
                int planeWidth = buffer.getUnsignedLEShort();
                int planeHeight = buffer.getUnsignedLEShort();
                int planeIndex = buffer.getUnsignedLEShort();
                int planeMultiplier = buffer.getUnsignedLEShort();
                planeHeight -= planeIndex * planeMultiplier;
                System.out.println("Load Area: " + localServerIndex + ", " + planeWidth + ", " + planeHeight + ", " + planeIndex);
                break;
            }
            case 156: // Set Stats
            {
                for (int skill = 0; skill < 18; ++skill)
                    client.skillLevel[skill] = buffer.getUnsignedByte();
                for (int skill = 0; skill < 18; ++skill)
                    client.skillMaxLevel[skill] = buffer.getUnsignedByte();
                for (int skill = 0; skill < 18; ++skill)
                    client.skillExperience[skill] = RSC_convertXP(buffer.getInt());

                int questPoints = buffer.getUnsignedByte();
                // TODO: Set quest points
                break;
            }
            case 51: // Privacy Settings
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
            case 131: // Send Message
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

        return ret;
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
