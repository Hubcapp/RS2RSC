package com.jagex.runescape.rs2rsc;

import com.jagex.runescape.*;
import com.jagex.runescape.definition.GameObjectDefinition;
import com.jagex.runescape.definition.ItemDefinition;
import rscminus.common.JGameData;
import rscminus.game.constants.Game;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class RSCConfig {
    public static boolean rscProtocol = true;
    public static int rscVersion = 235;
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

    public static String[] friendServers = new String[200];
    public static boolean[] questComplete = new boolean[Game.QUEST_COUNT];
    public static int[] inventoryID = new int[30];
    public static boolean[] inventoryEquipped = new boolean[30];
    public static int[] inventoryAmount = new int[30];
    public static int inventoryCount = 0;
    public static boolean inventoryUpdate = false;

    public static int playerCount;
    public static Player[] players = new Player[500];

    private static int magicLoc = 128;
    public static int localRegionX;
    public static int localRegionY;
    public static int planeWidth;
    public static int planeHeight;
    public static int planeIndex;

    private static BiMap<Integer, Integer> objectIDTable = new BiMap<Integer, Integer>();
    private static Map<Integer, Integer> objectIDDirTable = new HashMap<Integer, Integer>();

    private static BiMap<Integer, Integer> itemIDTable = new BiMap<Integer, Integer>();

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

    public static void Start(Client client)
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

        // Setup object ids
        objectIDTable.put(0, 1279); // Tree
        objectIDTable.put(1, 1276); // Tree
        objectIDTable.put(2, 884); // Well
        objectIDTable.put(3, 602); // Table
        objectIDTable.put(4, 1342); // Treestump
        objectIDTable.put(5, 1747); // Ladder
        objectIDTable.put(6, 133); // Ladder
        objectIDTable.put(7, 1088); // Chair
        objectIDTable.put(9, 596); // Longtable
        objectIDTable.put(10, 1097); // Throne
        //objectIDTable.put(20, 2440); // Post
        objectIDTable.put(25, 203); // candles
        objectIDTable.put(26, 879); // fountain
        //objectIDTable.put(27, 888); // landscape
        objectIDTable.put(34, 1173); // Fern
        objectIDTable.put(37, 1188); // Flower
        objectIDTable.put(38, 1163); // Mushroom
        objectIDTable.put(63, 1574); // doors
        objectIDTable.put(70, 1286); // Tree
        objectIDTable.put(97, 2732); // fire
        objectIDTable.put(119, 2728); // Cook's Range
        objectIDTable.put(192, 42); // fish (Lure/Bait)
        objectIDTable.put(193, 44); // fish (Net/Bait)

        // Setup object ids directions
        objectIDDirTable.put(1097, 1); // Throne

        itemIDTable.put(4, 1171); // Wooden Shield
        itemIDTable.put(10, 995); // Coins
        itemIDTable.put(12, 1349); // Iron Axe
        itemIDTable.put(14, 1511); // Logs
        itemIDTable.put(16, 1059); // Leather Gloves
        itemIDTable.put(17, 1061); // Boots
        itemIDTable.put(87, 1351); // bronze Axe
        itemIDTable.put(88, 1349); // Steel Axe
        itemIDTable.put(93, 1373); // Rune battle Axe
        itemIDTable.put(104, 1139); // Medium Bronze Helmet
        itemIDTable.put(108, 1155); // Large Bronze Helmet
        itemIDTable.put(113, 1103); // Bronze Chain Mail Body
        itemIDTable.put(117, 1117); // Bronze Plate Mail Body
        itemIDTable.put(124, 1173); // Bronze Square Shield
        itemIDTable.put(132, 2142 ); // cookedmeat
        itemIDTable.put(166, 590); // tinderbox
        itemIDTable.put(171, 2353); // steel bar
        itemIDTable.put(183, 1007); // Cape (red)
        itemIDTable.put(194, 1013); // skirt (pink)
        itemIDTable.put(206, 1075); // Bronze Plate Mail Legs
        itemIDTable.put(288, 1654); // Gold necklace
        itemIDTable.put(296, 1692); // Gold Amulet
        itemIDTable.put(420, 1540); // Anti dragon breath Shield
        itemIDTable.put(428, 1261); // Black Axe
        itemIDTable.put(203, 1355); // Mithril Axe
        itemIDTable.put(204, 1357); // Adamantite Axe
        itemIDTable.put(205, 1375); // bronze battle axe
        itemIDTable.put(319, 1985); // Cheese
        itemIDTable.put(320, 1982); // Tomato
        itemIDTable.put(349, 317); // Raw Shrimp
        itemIDTable.put(375, 301); // Lobster Pot
        itemIDTable.put(376, 303); // Small fishing net
        itemIDTable.put(377, 307); // Fishing Rod
        itemIDTable.put(378, 309); // Fly Fishing Rod
        itemIDTable.put(379, 311); // Harpoon
        itemIDTable.put(405, 1359); // rune Axe
        itemIDTable.put(548, 305); // Big Net
        itemIDTable.put(971, 1050); // Santa's hat
        itemIDTable.put(1288, 1052); // Cape of legends

        System.out.println("using rsc protocol");
    }

    public static RSInterface getInventoryInterface()
    {
        RSInterface tab = null;
        for (int i = 0; i < RSInterface.cache.length; i++)
        {
            if (RSInterface.cache[i] == null)
                continue;
            if (RSInterface.cache[i].inventory)
            {
                tab = RSInterface.cache[i];
                break;
            }
        }
        return tab;
    }

    public static void Update(Client client)
    {
        if (!rscProtocol)
            return;

        // Update local player
        if (Client.localPlayer != null)
            Client.localPlayer.RSC_update();

        // Update other players
        for (int i = 0; i < client.localPlayerCount; i++)
        {
            Player otherPlayer = client.players[client.localPlayers[i]];
            otherPlayer.RSC_update();
        }

        // Update inventory
        if (inventoryUpdate) {
            RSInterface tab = getInventoryInterface();
            int count = inventoryCount;
            int length = Math.min(tab.inventoryItemId.length, inventoryID.length);

            if (tab != null) {
                for (int i = 0; i < count; i++) {
                    tab.inventoryItemId[i] = RSC_TranslateItem(inventoryID[i]) + 1;
                    tab.inventoryStackSize[i] = inventoryAmount[i];
                }

                for (int i = count; i < length; i++)
                {
                    tab.inventoryItemId[i] = -1;
                    tab.inventoryStackSize[i] = 0;
                }
            }

            client.redrawTab = true;
            inventoryUpdate = false;
        }
    }

    public static int RSC_convertXP(int xp)
    {
        return xp / 4;
    }

    public static String RSC_SanitizeMenu(String option)
    {
        if (option.equalsIgnoreCase("examine"))
            return null;
        if (option.equalsIgnoreCase("walkto"))
            return null;

        return option;
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

        // Initialize rsc state
        playerCount = 0;

        // Set settings
        client.sendConfig(166, Settings.getBrightness() + 1); // Brightness
        client.sendConfig(287, Settings.getSplitPrivateChat() ? 1 : 0); // Split private chat
        client.sendConfig(171, 0); // Chat effects

        // Set sidebar interfaces
        client.setSidebarID(1, 3917); // Stats
        //client.setSidebarID(2, 638); // Quest
        client.setSidebarID(3, 3213); // Inventory
        client.setSidebarID(8, 5065); // Friends
        client.setSidebarID(10, 2449); // Logout
        client.setSidebarID(11, 4445); // Settings

        // Setup friends list
        client.friendListStatus = 2;

        getInventoryInterface().resizeInventory(4, 8);

        // Setup player actions
        client.playerActionText[0] = "Duel with";
        client.playerActionText[1] = "Trade with";
        client.playerActionText[2] = "Follow";
        client.playerActionText[3] = "Report abuse";
        client.playerActionUnpinned[0] = true;
        client.playerActionUnpinned[1] = true;
        client.playerActionUnpinned[2] = true;
        client.playerActionUnpinned[3] = true;

        // Translate Objects to rsc
        for (Map.Entry<Integer, Integer> entry : objectIDTable.getKeyEntrySet())
        {
            int rscID = entry.getValue();
            int rs2ID = entry.getKey();
            GameObjectDefinition def = GameObjectDefinition.getDefinition(rs2ID);
            def.name = JGameData.sceneryName[rscID];
            def.description = JGameData.sceneryExamine[rscID].getBytes();
            String action1 = JGameData.sceneryCommand1[rscID];
            if (action1.length() == 0)
                action1 = null;
            else
                action1 = RSC_SanitizeMenu(action1);
            String action2 = JGameData.sceneryCommand2[rscID];
            if (action2.length() == 0)
                action2 = null;
            else
                action2 = RSC_SanitizeMenu(action2);
            def.actions = new String[] { action1, action2, null, null, null };
            def.sizeX = JGameData.sceneryWidth[rscID];
            def.sizeY = JGameData.sceneryHeight[rscID];
        }
    }

    public static void RSC_HandleInterface(int actionID, Client client, Buffer buffer)
    {
        switch (actionID)
        {
            case 952: // Split Private Chat (On)
                Settings.setSplitPrivateChat(true);
                break;
            case 953: // Split Private Chat (Off)
                Settings.setSplitPrivateChat(false);
                break;
            case 5452: // Brightness (Dark)
                Settings.setBrightness(0);
                break;
            case 6273: // Brightness (Normal)
                Settings.setBrightness(1);
                break;
            case 6275: // Brightness (Bright)
                Settings.setBrightness(2);
                break;
            case 6277: // Brightness (Very Bright)
                Settings.setBrightness(3);
                break;
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

    public static Player RSC_getPlayer(Client client, int serverIndex, int areaX, int areaY, int direction)
    {
        // Add player if it doesn't exist
        if (client.players[serverIndex] == null || client.players[serverIndex].index != serverIndex)
        {
            client.players[serverIndex] = new Player();
            client.players[serverIndex].index = serverIndex;
            client.players[serverIndex].setPos(areaX, areaY, true);
            client.players[serverIndex].turnDirection = RSC_ConvertDirection(direction);
            client.players[serverIndex].currentRotation = client.players[serverIndex].turnDirection;
        }

        Player player = client.players[serverIndex];
        boolean isLocal = false;
        boolean isSelf = false;

        if (player.waypointX[0] != areaX || player.waypointY[0] != areaY)
            player.setPos(areaX, areaY, false);

        if (serverIndex == localServerIndex)
        {
            isSelf = true;
            isLocal = true;
            Client.localPlayer = player;
        }

        // Do we already know about this player?
        if (!isSelf) {
            for (int otherServerIndex : client.localPlayers) {
                if (otherServerIndex == serverIndex) {
                    isLocal = true;
                    break;
                }
            }
        }

        if (!isSelf)
            players[playerCount++] = player;

        return player;
    }

    public static int RSC_TranslateObject(int objectID)
    {
        Integer val = objectIDTable.get(objectID);
        if (val == null)
        {
            System.out.println("Unhandled object id: " + objectID);
            return -1;
        }
        return val.intValue();
    }

    public static int RSC_TranslateObjectReverse(int objectID)
    {
        Integer val = objectIDTable.getKey(objectID);
        if (val == null)
        {
            System.out.println("Unhandled reverse object id: " + objectID);
            return -1;
        }
        return val.intValue();
    }

    public static int RSC_TranslateItem(int itemID)
    {
        Integer val = itemIDTable.get(itemID);
        if (val == null)
        {
            System.out.println("Unhandled item id: " + itemID);
            return -1;
        }
        return val.intValue();
    }

    public static int RSC_TranslateItemReverse(int itemID)
    {
        Integer val = itemIDTable.getKey(itemID);
        if (val == null)
        {
            System.out.println("Unhandled reverse item id: " + itemID);
            return -1;
        }
        return val.intValue();
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

            case 12: // Boots
                return RSC_TranslateItem(17);
            case 13: // Large Bronze Helmet
                return RSC_TranslateItem(108);
            case 21: // Bronze Chain Mail Body
                return RSC_TranslateItem(113);
            case 28: // Bronze Plate Mail Body
                return RSC_TranslateItem(117);
            case 37: // Bronze Plate Mail Legs
                return RSC_TranslateItem(206);
            case 47: // Leather Gloves
                return RSC_TranslateItem(16);
            case 63: // Cape (red)
                return RSC_TranslateItem(183);
            case 70: // Medium Bronze Helmet
                return RSC_TranslateItem(104);
            case 80: // Silver amulet
                return 1796;
            case 81: // Gold amulet
                return RSC_TranslateItem(288);
            case 90: // skirt (pink)
                return RSC_TranslateItem(194);
            case 98: // Bronze Square Shield
                return RSC_TranslateItem(124);
            case 105: // Anti dragon breath Shield
                return RSC_TranslateItem(420);
            case 106: // Wooden Shield
                return RSC_TranslateItem(4);
            case 109: // bronze Axe
                return RSC_TranslateItem(205);
            case 114: // rune Axe
                return RSC_TranslateItem(93);
            case 172: // Black amulet
                return 4677;
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
            case 1349: // Iron Axe
            case 1353: // Steel Axe
            case 1361: // Black Axe
            case 1355: // Mithril Axe
            case 1357: // Adamantite Axe
            case 1359: // rune Axe
            case 1375: // bronze battle Axe
            case 1373: // Rune battle Axe
                return true;
        }

        return false;
    }

    public static boolean RSC_isAxe(int itemID)
    {
        switch (itemID)
        {
            case 1351: // bronze Axe
            case 1349: // Iron Axe
            case 1353: // Steel Axe
            case 1361: // Black Axe
            case 1355: // Mithril Axe
            case 1357: // Adamantite Axe
            case 1359: // rune Axe
                return true;
        }

        return false;
    }

    public static boolean RSC_isGlove(int itemID)
    {
        switch (itemID)
        {
            case 1059: // Leather Gloves
                return true;
        }

        return false;
    }

    public static boolean RSC_isAmulet(int itemID)
    {
        switch (itemID)
        {
            case 1692: // Gold Amulet
            case 1654: // Gold necklace
            case 1796: // (RS2) Silver necklace
            case 4677: // (RS2) Crystal pendant
                return true;
        }

        return false;
    }

    public static boolean RSC_isFullHelmet(int itemID)
    {
        switch (itemID)
        {
            case 1155: // bronze Axe
                return true;
        }

        return false;
    }

    public static boolean RSC_isShield(int itemID)
    {
        switch (itemID)
        {
            case 1173: // Bronze Square Shield
            case 1540: // Anti dragon breath Shield
            case 1171: // Wooden Shield
                return true;
        }

        return false;
    }

    public static boolean RSC_isMediumHelmet(int itemID)
    {
        switch (itemID)
        {
            case 1139: // Medium Bronze Helmet
                return true;
        }

        return false;
    }

    public static boolean RSC_isLeg(int itemID)
    {
        switch (itemID)
        {
            case 1013: // skirt (pink)
            case 1075: // Bronze Plate Mail Legs
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

    public static boolean RSC_isPlatebody(int itemID)
    {
        switch (itemID)
        {
            case 1117: // Bronze Plate Mail Body
                return true;
        }

        return false;
    }

    public static boolean RSC_isChainmail(int itemID)
    {
        switch (itemID)
        {
            case 1103: // Bronze Chain Mail Body
                return true;
        }

        return false;
    }

    public static boolean RSC_isCape(int itemID)
    {
        switch (itemID)
        {
            case 1007: // Cape (red)
            case 1052: // Cape of legends
                return true;
        }

        return false;
    }

    public static boolean RSC_isBoot(int itemID)
    {
        switch (itemID)
        {
            case 1061: // Boots
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

    public static int RSC_ConvertDirection(int direction)
    {
        switch (direction)
        {
            case 0: // South
                return 0;
            case 1: // Southeast
                return (int) (Math.atan2(-1, 1) * 325.949) & 0x7FF;
            case 2: // East
                return (int) (Math.atan2(-1, 0) * 325.949) & 0x7FF;
            case 3: // Northeast
                return (int) (Math.atan2(-1, -1) * 325.949) & 0x7FF;
            case 4: // North
                return (int) (Math.atan2(0, -1) * 325.949) & 0x7FF;
            case 5: // Northwest
                return (int) (Math.atan2(1, -1) * 325.949) & 0x7FF;
            case 6: // West
                return (int) (Math.atan2(1, 0) * 325.949) & 0x7FF;
            case 7: // Southwest
                return (int) (Math.atan2(1, 1) * 325.949) & 0x7FF;
            default:
                System.out.println("Unhandled rsc direction: " + direction);
                break;
        }

        return direction;
    }

    public static void RSC_PlayAnimation(Player player, int itemID, int length)
    {
        int rscItemID = itemID;
        itemID = RSC_TranslateItem(itemID);

        // We don't know what item it is, we can't play an animation
        if (itemID == -1)
            return;

        // Reset Animation
        int animation = -1;

        // Fishing
        switch (itemID)
        {
            case 301: // Lobster Pot
                animation = 619;
                break;
            case 303: // Net
                animation = 621;
                break;
            case 305: // Big Net
                animation = 620;
                break;
            case 307: // Fishing Rod
            case 309: // Fly Fishing Rod
                animation = 622;
                break;
            case 311: // Harpoon
                animation = 618;
                break;
        }

        // Woodcutting
        switch (itemID)
        {
            case 1351: // bronze Axe
                animation = 879;
                break;
            case 1349: // Iron Axe
                animation = 877;
                break;
            case 1353: // Steel Axe
                animation = 875;
                break;
            case 1361: // Black Axe
                animation = 873;
                break;
            case 1355: // Mithril Axe
                animation = 871;
                break;
            case 1357: // Adamantite Axe
                animation = 869;
                break;
            case 1359: // rune Axe
                animation = 867;
                break;
        }

        // Firemaking
        if (itemID == 590)
            animation = 733;

        // Restart Animation if it was set
        if (animation != -1)
        {
            player.RSC_queuedAnimation = animation;
            player.RSC_queuedAnimationEnd = Client.tick + 150;
        }
        else
        {
            System.out.println("Unhandled bubble animation: " + rscItemID);
        }
    }

    public static String RSC_removeChatFormatting(String str)
    {
        str = RSC_removeStringFormatting(str, '@');
        str = RSC_removeStringFormatting(str, '~');
        return str;
    }

    public static String RSC_removeStringFormatting(String str, char removeCharacter)
    {
        int pos = -1;
        for (int i = 0; i < str.length(); i++)
        {
            char c = str.charAt(i);
            if (c == removeCharacter)
            {
                if (pos == -1)
                {
                    pos = i;
                }
                else
                {
                    int diff = i - pos;
                    if (diff >= 4)
                    {
                        str = str.substring(0, pos) + str.substring(i + 1);
                        i -= 5;
                        pos = -1;
                    }
                    else
                    {
                        pos = i;
                    }
                }
            }
        }

        return str;
    }

    public static void RSC_sortFriendList(Client client) {
        boolean go = true;

        while (go) {
            go = false;

            for (int index = 0; index < (client.friendsCount - 1); ++index) {
                // TODO: I think this sorting also sorts based on same world or not
                if (client.friendsWorldIds[index] == 0 && client.friendsWorldIds[index + 1] != 0) {
                    String server = friendServers[index];
                    friendServers[index] = friendServers[index + 1];
                    friendServers[index + 1] = server;
                    String name = client.friendsList[index];
                    client.friendsList[index] = client.friendsList[index + 1];
                    client.friendsList[index + 1] = name;
                    int online = client.friendsWorldIds[index];
                    client.friendsWorldIds[index] = client.friendsWorldIds[index + 1];
                    client.friendsWorldIds[index + 1] = online;
                    long hash = client.friendsListAsLongs[index];
                    client.friendsListAsLongs[index] = client.friendsListAsLongs[index + 1];
                    client.friendsListAsLongs[index + 1] = hash;
                    go = true;
                }
            }
        }
        ;
    }

    public static int RSC_fixObjectDirection(int id, int direction)
    {
        if (!objectIDDirTable.containsKey(id))
            return direction;

        direction = (direction + objectIDDirTable.get(id)) % 8;
        return direction;
    }

    public static int RSC_HandleOpcode(int opcode, Client client, Buffer buffer)
    {
        if (!rscProtocol)
            return opcode;

        int ret = -1;

        switch (opcode)
        {
            case 99: // Ground Items
            {
                while (client.packetSize > buffer.position)
                {
                    if (buffer.getUnsignedByte() == 255) {
                        int x = buffer.get() >> 3;
                        int y = buffer.get() >> 3;
                    } else {
                        buffer.position--;
                        int mod = buffer.getUnsignedLEShort();
                        int x = buffer.get();
                        int y = buffer.get();

                        if (('\u8000' & mod) == 0) {
                            int rs2ID = RSCConfig.RSC_TranslateItem(mod);
                            if (rs2ID != -1)
                            {
                                client.RSC_spawnGroundItem(x, y, rs2ID);
                                System.out.println("SPAWN GROUND ITEM: " + mod + ", " + x + ", " + y);
                            }
                        } else {
                            mod &= 32767;
                        }
                    }
                }
                break;
            }
            case 5:
            {
                for (int i = 0; i < Game.QUEST_COUNT; i++)
                    questComplete[i] = (buffer.get() == 1);

                //client.sendInterfaceString(663, "0"); // FREE QUESTS

                break;
            }
            case 149:
            {
                String name = buffer.RSC_readString();
                String oldName = buffer.RSC_readString();
                String server = "";
                int onlineStatus = buffer.getUnsignedByte();

                boolean loggedIn = (4 & onlineStatus) != 0;
                boolean wasAdded = false;
                int index = -1;

                if (loggedIn)
                    server = buffer.RSC_readString();

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
                    wasAdded = true;
                }

                int prevWorldId = client.friendsWorldIds[index];
                client.friendsWorldIds[index] = loggedIn ? 10 : 0;
                friendServers[index] = server;
                RSC_sortFriendList(client);
                client.redrawTab = true;

                if (wasAdded)
                    prevWorldId = client.friendsWorldIds[index];

                if (prevWorldId == 0 && client.friendsWorldIds[index] != 0)
                    client.pushMessage(name + " has logged in.", 5, "");
                else if (prevWorldId != 0 && client.friendsWorldIds[index] == 0)
                    client.pushMessage(name + " has logged out.", 5, "");

                break;
            }
            case 87:
            {
                String sender = buffer.RSC_readString();
                String message = buffer.RSC_cabbage();
                message = RSCConfig.RSC_removeChatFormatting(message);
                client.pushMessage(message, 6, sender);
                break;
            }
            case 111:
            {
                boolean tutorial = buffer.getUnsignedByte() != 0;
                // TODO: What to do with this...
                break;
            }
            case 53: // Inventory
            {
                inventoryCount = buffer.getUnsignedByte();
                for (int i = 0; i < inventoryCount; i++)
                {
                    int mod = buffer.getUnsignedLEShort();
                    inventoryID[i] = mod & 32767;
                    inventoryEquipped[i] = (mod / '\u8000') == 1;
                    inventoryAmount[i] = 1;
                    if (JGameData.itemStackable[inventoryID[i]])
                        inventoryAmount[i] = buffer.RSC_getUnsignedInt3();
                }
                inventoryUpdate = true;
                break;
            }
            case 123: // Inventory remove
            {
                int slot = buffer.getUnsignedByte();
                inventoryCount--;

                for (int i = slot; i < inventoryCount; i++)
                {
                    inventoryID[i] = inventoryID[i + 1];
                    inventoryAmount[i] = inventoryAmount[i + 1];
                    inventoryEquipped[i] = inventoryEquipped[i + 1];
                }
                inventoryUpdate = true;
                break;
            }
            case 90: // Inventory Slot
            {
                int i = buffer.getUnsignedByte();
                int mod = buffer.getUnsignedLEShort();
                inventoryID[i] = mod & 32767;
                inventoryEquipped[i] = (mod / '\u8000') == 1;
                inventoryAmount[i] = 1;
                if (JGameData.itemStackable[inventoryID[i]])
                    inventoryAmount[i] = buffer.RSC_getUnsignedInt3();
                inventoryUpdate = true;
                if (inventoryCount <= i)
                    inventoryCount = i + 1;
                break;
            }
            case 120:
            {
                String sender = buffer.RSC_readString();
                String senderClan = buffer.RSC_readString();
                int modStatus = buffer.getUnsignedByte();
                long unk = buffer.getLong();
                String message = buffer.RSC_cabbage();
                message = RSCConfig.RSC_removeChatFormatting(message);
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
                client.localPlayerCount = playerCount;
                for (int i = 0; i < playerCount; i++)
                    client.localPlayers[i] = players[i].index;

                buffer.initBitAccess();
                localRegionX = buffer.readBits(11);
                localRegionY = buffer.readBits(13);
                int anim = buffer.readBits(4);

                // Load region
                client.RSC_loadRegion(localRegionX, localRegionY);
                localRegionX -= client.regionX;
                localRegionY -= client.regionY;

                int localX = localRegionX;//64 + magicLoc * localRegionX;
                int localY = localRegionY;//64 + magicLoc * localRegionY;

                client.playerPositionX = localX;
                client.playerPositionY = localY;

                // Set local player
                playerCount = 0;
                Player localPlayer = RSC_getPlayer(client, localServerIndex, localX, localY, anim);

                int playerUpdateCount = buffer.readBits(8);

                for (int i = 0; i < playerUpdateCount; i++)
                {
                    Player player = client.players[client.localPlayers[i]];

                    int reqUpdate = buffer.readBits(1);
                    if (reqUpdate != 0) {
                        int updateType = buffer.readBits(1);
                        if (updateType != 0)
                        {
                            int unk = buffer.readBits(2);
                            if (unk == 3)
                                continue;
                            int nextAnim = buffer.readBits(2) + (unk << 2);

                        }
                        else
                        {
                            int nextAnim = buffer.readBits(3);
                        }
                    }

                    players[playerCount++] = player;
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

                    Player otherPlayer = RSC_getPlayer(client, serverIndex, playerX, playerY, otherAnim);
                }

                buffer.finishBitAccess();

                client.loadingMap = false;
                break;
            }
            case 4:
            {
                client.logout();
                break;
            }
            case 48:
            {
                while (client.packetSize > buffer.position)
                {
                    if (buffer.getUnsignedByte() == 255)
                    {
                        // Clear object?
                        int x = buffer.get() >> 3;
                        int y = buffer.get() >> 3;

                        client.RSC_removeGameObject(x, y);

                        System.out.println("Game Object Clear: " + x + ", " + y);
                    }
                    else
                    {
                        buffer.position--;
                        int id = buffer.getUnsignedLEShort();
                        int x = buffer.get();
                        int y = buffer.get();

                        client.RSC_removeGameObject(x, y);

                        if (id == 60000)
                            continue;

                        int rs2ID = RSC_TranslateObject(id);
                        if (rs2ID != -1 && Math.abs(x) <= 8 && Math.abs(y) <= 8)
                        {
                            int localX = Client.localPlayer.waypointX[0] + x;
                            int localY = Client.localPlayer.waypointY[0] + y;
                            int posX = client.regionX + localX;
                            int posY = client.regionY + localY;
                            int orientation = JGameData.getTileDirection(posX, posY);
                            orientation = RSC_fixObjectDirection(rs2ID, orientation);
                            client.RSC_spawnGameObject(x, y, orientation, rs2ID);
                            System.out.println("Spawning " + rs2ID + " at " + x + ", " + y);
                        }
                    }
                }
                break;
            }
            case 234:
            {
                int playerCount = buffer.getUnsignedLEShort();
                for (int i = 0; i < playerCount; i++)
                {
                    int serverIndex = buffer.getUnsignedLEShort();
                    int updateType = buffer.getUnsignedByte();
                    Player player = client.players[serverIndex];

                    switch (updateType)
                    {
                        case 0:
                        {
                            int itemID = buffer.getUnsignedLEShort();
                            RSC_PlayAnimation(player, itemID, 150);
                            break;
                        }
                        case 1:
                        {
                            int mod = buffer.getUnsignedByte();
                            String message = buffer.RSC_cabbage();
                            message = RSCConfig.RSC_removeChatFormatting(message);

                            client.RSC_setTextMessage(player, message);
                            if (player != Client.localPlayer)
                                client.pushMessage(message, 2, player.name);
                            break;
                        }
                        case 2:
                        {
                            int damage = buffer.getUnsignedByte();
                            int hp = buffer.getUnsignedByte();
                            int hpMax = buffer.getUnsignedByte();

                            // Players have red hit splats in rsc
                            player.updateHitData(1, damage, Client.tick);
                            player.loopCycleStatus = Client.tick + 200;
                            player.currentHealth = hp;
                            player.maxHealth = hpMax;
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
                            int handID = -1;
                            int amuletID = -1;
                            int shieldID = -1;
                            int genderID = 0;

                            int hatID = -1;
                            int capeID = -1;
                            int helmetID = -1;
                            int medHelmetID = -1;
                            int legArmorID = -1;
                            int chestArmorID = -1;
                            int armArmorID = -1;
                            int handArmorID = -1;
                            int feetArmorID = -1;

                            int equipCount = buffer.getUnsignedByte();
                            for (int x = 0; x < equipCount; x++) {
                                int id = buffer.getUnsignedByte();
                                if (id == 0)
                                    continue;
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
                                    handID = 68;
                                    armID = 61;
                                }

                                if (RSC_isWeapon(id))
                                    weaponID = id;
                                if (RSC_isHat(id))
                                    hatID = id;
                                if (RSC_isCape(id))
                                    capeID = id;
                                if (RSC_isFullHelmet(id))
                                    helmetID = id;
                                if (RSC_isLeg(id))
                                    legArmorID = id;
                                if (RSC_isPlatebody(id))
                                {
                                    chestArmorID = id;
                                    armArmorID = id;
                                }
                                if (RSC_isChainmail(id))
                                {
                                    chestArmorID = id;
                                    armArmorID = -1;
                                }
                                if (RSC_isMediumHelmet(id))
                                    medHelmetID = id;
                                if (RSC_isGlove(id))
                                    handArmorID = id;
                                if (RSC_isBoot(id))
                                    feetArmorID = id;
                                if (RSC_isAmulet(id))
                                    amuletID = id;
                                if (RSC_isShield(id))
                                    shieldID = id;
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

                            if (handID == -1)
                                handID = 0x100 + 34;
                            else
                                handID = 0x100 + handID;

                            if (amuletID == -1)
                                amuletID = 0;
                            else
                                amuletID = 0x200 + amuletID;

                            if (shieldID == -1)
                                shieldID = 0;
                            else
                                shieldID = 0x200 + shieldID;

                            if (hatID > -1)
                                hatID = 0x200 + hatID;
                            else
                                hatID = 0;

                            if (capeID > -1)
                                capeID = 0x200 + capeID;
                            else
                                capeID = 0;

                            if (helmetID > -1)
                                helmetID = 0x200 + helmetID;

                            if (legArmorID > -1)
                                legArmorID = 0x200 + legArmorID;

                            if (chestArmorID > -1)
                                chestArmorID = 0x200 + chestArmorID;

                            if (medHelmetID > -1)
                                medHelmetID = 0x200 + medHelmetID;

                            if (handArmorID > -1)
                                handArmorID = 0x200 + handArmorID;

                            if (feetArmorID > -1)
                                feetArmorID = 0x200 + feetArmorID;

                            // Full helmet
                            if (helmetID != -1)
                            {
                                beardID = 0; // Remove beard
                                hairID = helmetID;
                            }

                            // Leg armor
                            if (legArmorID != -1)
                                legID = legArmorID;

                            // Chest armor
                            if (chestArmorID != -1)
                                chestID = chestArmorID;

                            // Arm armor
                            if (armArmorID != -1)
                                armID = armArmorID;

                            // Medium helmets
                            if (medHelmetID != -1)
                                hatID = medHelmetID;

                            // Hand armor
                            if (handArmorID != -1)
                                handID = handArmorID;

                            // Feet armor
                            if (feetArmorID != -1)
                                feetID = feetArmorID;

                            // Set appearance
                            player.gender = genderID; // 0 - Male
                            player.appearance[0] = hatID; // Hat
                            player.appearance[1] = capeID; // Cape
                            player.appearance[2] = amuletID; // Amulet
                            player.appearance[3] = weaponID; // Weapon
                            player.appearance[4] = chestID; // Chest
                            player.appearance[5] = shieldID; // Shield
                            player.appearance[6] = armID; // Arms
                            player.appearance[7] = legID; // Legs
                            player.appearance[8] = hairID; // Head
                            player.appearance[9] = handID; // Hands
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
                planeWidth = buffer.getUnsignedLEShort();
                planeHeight = buffer.getUnsignedLEShort();
                planeIndex = buffer.getUnsignedLEShort();
                int planeMultiplier = buffer.getUnsignedLEShort();
                planeHeight -= planeIndex * planeMultiplier;
                System.out.println("Load Area: " + localServerIndex + ", " + planeWidth + ", " + planeHeight + ", " + planeIndex);
                break;
            }
            case 33: // Set XP
            {
                int skill = buffer.getUnsignedByte();
                client.skillExperience[skill] = RSC_convertXP(buffer.getInt());

                client.redrawTab = true;
                break;
            }
            case 159: // Set Skill
            {
                int skill = buffer.getUnsignedByte();
                client.skillLevel[skill] = buffer.getUnsignedByte();
                client.skillMaxLevel[skill] = buffer.getUnsignedByte();
                client.skillExperience[skill] = RSC_convertXP(buffer.getInt());

                client.redrawTab = true;
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
                client.sendConfig(101, questPoints);

                client.redrawTab = true;
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
                message = RSCConfig.RSC_removeChatFormatting(message);
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
                    case 7: // Duel
                        sender = message.substring(0, message.indexOf('(') - 1);
                        message = message.substring(message.indexOf('('));
                        rs2Type = 8;
                        break;
                    case 6: // Trade
                        message = "wishes to trade with you.";
                        rs2Type = 4;
                        break;
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
            case 4:
                responseCode = 5;
                break;
            case 7:
                responseCode = 9;
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
