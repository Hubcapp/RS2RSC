/**
 * rscminus
 *
 * This file is part of rscminus.
 *
 * rscminus is free software: you can redistribute it and/or modify it under the terms of the GNU
 * General Public License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * rscminus is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without
 * even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with rscminus. If not,
 * see <http://www.gnu.org/licenses/>.
 *
 * Authors: see <https://github.com/OrN/rscminus>
 */

package rscminus.common;

import com.jagex.runescape.Client;
import com.jagex.runescape.rs2rsc.RSCConfig;
import com.jagex.runescape.rs2rsc.RSCMath;
import rscminus.game.constants.Game;

public class JGameData {
    public static int itemCount;
    public static String itemName[];
    public static String itemExamine[];
    public static String itemCommand[];
    public static int itemPrice[];
    public static boolean itemStackable[];
    public static int itemWearable[];
    public static boolean itemTradable[];
    public static boolean itemMembers[];
    public static int npcCount;
    public static String npcName[];
    public static String npcExamine[];
    public static int npcAttack[];
    public static int npcStrength[];
    public static int npcHits[];
    public static int npcDefense[];
    public static int npcAttackable[];
    public static int npcCombatLevel[];
    public static String npcCommand[];
    public static int animationCount;
    public static String animationName[];
    public static int animationIndex[];
    public static int sceneryCount;
    public static int sceneryWidth[];
    public static int sceneryHeight[];
    public static String sceneryName[];
    public static String sceneryExamine[];
    public static String sceneryCommand1[];
    public static String sceneryCommand2[];
    public static int boundaryCount;
    public static byte boundaryAdjacent[];
    public static byte boundaryPassable[];
    public static int tileCount;
    public static int tileDecoration[];
    public static int tileType[];
    public static int tileAdjacent[];

    public static byte regionCollisionMask[][][][];
    public static byte regionDirection[][][][];
    public static byte regionHeight[][][][];
    public static byte regionColor[][][][];
    public static byte regionDecoration[][][][];
    public static byte regionWallsNorthSouth[][][][];
    public static byte regionWallsEastWest[][][][];
    public static short regionWallsDiagonal[][][][];

    public static boolean init(boolean member) {
        JContent content = new JContent();
        JContent contentMembers = new JContent();

        // Read content0 (Configuration)
        if(!content.open("rsc_cache/content0_229aa476"))
            return false;
        JContentFile string = content.unpack("string.dat");
        if (string == null)
            return false;
        JContentFile integer = content.unpack("integer.dat");
        if (integer == null)
            return false;
        content.close();

        // Read item data
        itemCount = integer.readUnsignedShort();
        itemName = new String[itemCount];
        itemExamine = new String[itemCount];
        itemCommand = new String[itemCount];
        itemPrice = new int[itemCount];
        itemStackable = new boolean[itemCount];
        itemWearable = new int[itemCount];
        itemTradable = new boolean[itemCount];
        itemMembers = new boolean[itemCount];
        for (int i = 0; i < itemCount; i++)
            itemName[i] = string.readString();
        for (int i = 0; i < itemCount; i++)
            itemExamine[i] = string.readString();
        for (int i = 0; i < itemCount; i++)
            itemCommand[i] = string.readString();
        for (int i = 0; i < itemCount; i++)
            integer.skip(2); // Sprite
        for (int i = 0; i < itemCount; i++)
            itemPrice[i] = integer.readUnsignedInt();
        for (int i = 0; i < itemCount; i++)
            itemStackable[i] = (integer.readUnsignedByte() == 0);
        for (int i = 0; i < itemCount; i++)
            integer.skip(1); // Unused
        for (int i = 0; i < itemCount; i++)
            itemWearable[i] = integer.readUnsignedShort();
        for (int i = 0; i < itemCount; i++)
            integer.skip(4); // Mask
        for (int i = 0; i < itemCount; i++)
            itemTradable[i] = (integer.readUnsignedByte() == 0);
        for (int i = 0; i < itemCount; i++)
            itemMembers[i] = (integer.readUnsignedByte() == 1);

        // Read npc data
        npcCount = integer.readUnsignedShort();
        npcName = new String[npcCount];
        npcExamine = new String[npcCount];
        npcAttack = new int[npcCount];
        npcStrength = new int[npcCount];
        npcHits = new int[npcCount];
        npcDefense = new int[npcCount];
        npcAttackable = new int[npcCount];
        npcCommand = new String[npcCount];
        npcCombatLevel = new int[npcCount];
        for (int i = 0; i < npcCount; i++) {
            npcName[i] = string.readString();
        }
        for (int i = 0; i < npcCount; i++)
            npcExamine[i] = string.readString();
        for (int i = 0; i < npcCount; i++)
            npcAttack[i] = integer.readUnsignedByte();
        for (int i = 0; i < npcCount; i++)
            npcStrength[i] = integer.readUnsignedByte();
        for (int i = 0; i < npcCount; i++)
            npcHits[i] = integer.readUnsignedByte();
        for (int i = 0; i < npcCount; i++)
            npcDefense[i] = integer.readUnsignedByte();
        for (int i = 0; i < npcCount; i++)
            npcAttackable[i] = integer.readUnsignedByte();
        for (int i = 0; i < npcCount; i++)
            for (int i2 = 0; i2 < 12; i2++)
                integer.skip(1); // Sprite
        for (int i = 0; i < npcCount; i++)
            integer.skip(4); // Hair color
        for (int i = 0; i < npcCount; i++)
            integer.skip(4); // Top color
        for (int i = 0; i < npcCount; i++)
            integer.skip(4); // Bottom color
        for (int i = 0; i < npcCount; i++)
            integer.skip(4); // Skin color
        for (int i = 0; i < npcCount; i++)
            integer.skip(2); // Width
        for (int i = 0; i < npcCount; i++)
            integer.skip(2); // Height
        for (int i = 0; i < npcCount; i++)
            integer.skip(1); // Walk model (?)
        for (int i = 0; i < npcCount; i++)
            integer.skip(1); // Combat model (?)
        for (int i = 0; i < npcCount; i++)
            integer.skip(1); // Combat animation (?)
        for (int i = 0; i < npcCount; i++)
            npcCommand[i] = string.readString();
        for (int i = 0; i < npcCount; i++)
            if (npcAttackable[i] > 0)
                npcCombatLevel[i] = (int) RSCMath.combatFormulaRSC(npcAttack[i], npcStrength[i], npcDefense[i], npcHits[i], 0, 0, 0);

        // Read texture data
        int textureCount = integer.readUnsignedShort();
        for (int i = 0; i < textureCount; i++) {
            String textureName = string.readString(); // Name
        }

        for (int i = 0; i < textureCount; i++)
            string.readString(); // Subtype name

        // Read animation data
        animationCount = integer.readUnsignedShort();
        animationName = new String[animationCount];
        animationIndex = new int[animationCount];
        for (int i = 0; i < animationCount; i++) {
            animationName[i] = string.readString();
        }

        for (int i = 0; i < animationCount; i++)
            integer.skip(4); // CharacterColour
        for (int i = 0; i < animationCount; i++)
            integer.skip(1); // Unknown
        for (int i = 0; i < animationCount; i++)
            integer.skip(1); // animationHasA
        for (int i = 0; i < animationCount; i++)
            integer.skip(1); // animationHasF
        for (int i = 0; i < animationCount; i++)
            animationIndex[i] = integer.readUnsignedByte(); //animation number

        sceneryCount = integer.readUnsignedShort();
        sceneryWidth = new int[sceneryCount];
        sceneryHeight = new int[sceneryCount];
        sceneryName = new String[sceneryCount];
        sceneryExamine = new String[sceneryCount];
        sceneryCommand1 = new String[sceneryCount];
        sceneryCommand2 = new String[sceneryCount];
        for (int i = 0; i < sceneryCount; i++) {
            sceneryName[i] = string.readString(); // Name
        }

        for (int i = 0; i < sceneryCount; i++)
            sceneryExamine[i] = string.readString(); // Examine
        for (int i = 0; i < sceneryCount; i++)
            sceneryCommand1[i] = string.readString(); // Command 1
        for (int i = 0; i < sceneryCount; i++)
            sceneryCommand2[i] = string.readString(); // Command 2
        for (int i = 0; i < sceneryCount; i++)
            string.readString(); // Unknown
        for (int i = 0; i < sceneryCount; i++)
            sceneryWidth[i] = integer.readUnsignedByte();
        for (int i = 0; i < sceneryCount; i++)
            sceneryHeight[i] = integer.readUnsignedByte();
        for (int i = 0; i < sceneryCount; i++)
            integer.skip(1); // Type
        for (int i = 0; i < sceneryCount; i++)
            integer.skip(1); // Elevation

        boundaryCount = integer.readUnsignedShort();
        boundaryAdjacent = new byte[boundaryCount];
        boundaryPassable = new byte[boundaryCount];
        for (int i = 0; i < boundaryCount; i++) {
            String boundaryName = string.readString(); // Name
        }

        for (int i = 0; i < boundaryCount; i++)
            string.readString(); // Examine
        for (int i = 0; i < boundaryCount; i++)
            string.readString(); // Command 1
        for (int i = 0; i < boundaryCount; i++)
            string.readString(); // Command 2
        for (int i = 0; i < boundaryCount; i++)
            integer.skip(2); // Unknown
        for (int i = 0; i < boundaryCount; i++)
            integer.skip(4); // Texture 1
        for (int i = 0; i < boundaryCount; i++)
            integer.skip(4); // Texture 2
        for (int i = 0; i < boundaryCount; i++)
            boundaryAdjacent[i] = integer.readByte(); // Adjacent
        for (int i = 0; i < boundaryCount; i++)
            boundaryPassable[i] = integer.readByte(); // Collidable

        int roofCount = integer.readUnsignedShort();
        for (int i = 0; i < roofCount; i++)
            integer.skip(1); // Height
        for (int i = 0; i < roofCount; i++)
            integer.skip(1); // Vertices count

        tileCount = integer.readUnsignedShort();
        tileDecoration = new int[tileCount];
        tileType = new int[tileCount];
        tileAdjacent = new int[tileCount];

        for (int i = 0; i < tileCount; i++)
            tileDecoration[i] = integer.readUnsignedInt();
        for (int i = 0; i < tileCount; i++)
            tileType[i] = integer.readUnsignedByte();
        for (int i = 0; i < tileCount; i++)
            tileAdjacent[i] = integer.readUnsignedByte();

        string.close();
        integer.close();

        // Convert member items
        if (!member) {
            for (int i = 0; i < itemCount; i++) {
                if (itemMembers[i]) {
                    itemName[i] = "Members object";
                    itemExamine[i] = "You need to be a member to use this object";
                    itemCommand[i] = "";
                    itemPrice[i] = 0;
                    itemWearable[i] = 0;
                    itemTradable[i] = false;
                }
            }
        }

        int maxRegionWidth = Game.WORLD_WIDTH / Game.REGION_WIDTH;
        int maxRegionHeight = Game.WORLD_HEIGHT / Game.REGION_HEIGHT;
        regionCollisionMask = new byte[maxRegionWidth][maxRegionHeight][Game.REGION_FLOORS][Game.REGION_SIZE];
        regionDirection = new byte[maxRegionWidth][maxRegionHeight][Game.REGION_FLOORS][Game.REGION_SIZE];
        regionHeight = new byte[maxRegionWidth][maxRegionHeight][Game.REGION_FLOORS][Game.REGION_SIZE];
        regionColor = new byte[maxRegionWidth][maxRegionHeight][Game.REGION_FLOORS][Game.REGION_SIZE];
        regionDecoration = new byte[maxRegionWidth][maxRegionHeight][Game.REGION_FLOORS][Game.REGION_SIZE];
        regionWallsNorthSouth = new byte[maxRegionWidth][maxRegionHeight][Game.REGION_FLOORS][Game.REGION_SIZE];
        regionWallsEastWest = new byte[maxRegionWidth][maxRegionHeight][Game.REGION_FLOORS][Game.REGION_SIZE];
        regionWallsDiagonal = new short[maxRegionWidth][maxRegionHeight][Game.REGION_FLOORS][Game.REGION_SIZE];

        // Read content6 (landscape)
        if (!content.open("rsc_cache/content4_ffffffffaaca2b0d"))
            return false;
        if (!contentMembers.open("rsc_cache/content5_6a1d6b00"))
            return false;
        for (int x = 0; x < maxRegionWidth; x++) {
            for (int y = 0; y < maxRegionHeight; y++) {
                for (int floor = 0; floor < Game.REGION_FLOORS; floor++) {
                    if(!loadLandscape(content, x, y, floor) && member)
                        loadLandscape(contentMembers, x, y, floor);
                }
            }
        }
        content.close();
        contentMembers.close();

        // Read content6 (landscape)
        if (!content.open("rsc_cache/content4_ffffffffaaca2b0d"))
            return false;
        if (!contentMembers.open("rsc_cache/content5_6a1d6b00"))
            return false;
        for (int x = 0; x < maxRegionWidth; x++) {
            for (int y = 0; y < maxRegionHeight; y++) {
                for (int floor = 0; floor < Game.REGION_FLOORS; floor++) {
                    if(!loadLandscape(content, x, y, floor) && member)
                        loadLandscape(contentMembers, x, y, floor);
                }
            }
        }
        content.close();
        contentMembers.close();

        // Read content6 (landscape)
        if (!content.open("rsc_cache/content6_ffffffffe997514b"))
            return false;
        if (!contentMembers.open("rsc_cache/content7_3fc5d9e3"))
            return false;
        for (int x = 0; x < maxRegionWidth; x++) {
            for (int y = 0; y < maxRegionHeight; y++) {
                for (int floor = 0; floor < Game.REGION_FLOORS; floor++) {
                    if(!loadTerrain(content, x, y, floor) && member)
                        loadTerrain(contentMembers, x, y, floor);
                }
            }
        }
        content.close();
        contentMembers.close();

        return true;
    }

    private static boolean loadTerrain(JContent content, int x, int y, int floor) {
        String mapName = "m" + floor + x / 10 + x % 10 + y / 10 + y % 10;

        JContentFile height = content.unpack(mapName + ".hei");
        if (height == null) {
            for (int i = 0; i < Game.REGION_SIZE; i++)
                regionHeight[x][y][floor][i] = 0;
            return false;
        }

        // Height
        int prevValue = 0;
        for (int i = 0; i < Game.REGION_SIZE; i++) {
            int h = height.readUnsignedByte();
            if (h >= 128) {
                for (int i2 = 0; i2 < h - 128; i2++) {
                    regionHeight[x][y][floor][i++] = (byte)prevValue;
                }
                i--;
            } else {
                regionHeight[x][y][floor][i] = (byte)h;
                prevValue = h;
            }
        }

        prevValue = 64;
        for (int _x = 0; _x < 48; _x++)
        {
            for (int _y = 0; _y < 48; _y++)
            {
                int i = 48 * _y + _x;
                prevValue = 127 & regionHeight[x][y][floor][i] + prevValue;
                regionHeight[x][y][floor][i] = (byte)(prevValue * 2);
            }
        }

        // Color
        prevValue = 0;
        for (int i = 0; i < Game.REGION_SIZE; i++) {
            int c = height.readUnsignedByte();
            if (c >= 128) {
                for (int i2 = 0; i2 < c - 128; i2++) {
                    regionColor[x][y][floor][i++] = (byte)prevValue;
                }
                i--;
            } else {
                regionColor[x][y][floor][i] = (byte)c;
                prevValue = c;
            }
        }

        prevValue = 35;

        for (int _x = 0; _x < 48; _x++)
        {
            for (int _y = 0; _y < 48; _y++)
            {
                int i = 48 * _y + _x;
                prevValue = 127 & regionColor[x][y][floor][i] + prevValue;
                regionColor[x][y][floor][i] = (byte)(prevValue * 2);
            }
        }

        height.close();

        return true;
    }

    private static boolean loadLandscape(JContent content, int x, int y, int floor) {
        String mapName = "m" + floor + x / 10 + x % 10 + y / 10 + y % 10;

        JContentFile map = content.unpack(mapName + ".dat");
        if (map == null) {
            // Initialize collisions to collidable
            for (int i = 0; i < Game.REGION_SIZE; i++)
                regionCollisionMask[x][y][floor][i] = Game.COLLISION_TILE;
            return false;
        }

        // Clear collisions
        for (int i = 0; i < Game.REGION_SIZE; i++)
            regionCollisionMask[x][y][floor][i] = Game.COLLISION_NONE;

        for (int i = 0; i < Game.REGION_SIZE; i++) {
            int id = map.readUnsignedByte();
            //regionCollisionMask[x][y][floor][i] |= (id > 0 && JGameData.boundaryPassable[id - 1] && JGameData.boundaryAdjacent[id - 1]) ? Game.COLLISION_EASTWEST : Game.COLLISION_NONE;
            regionWallsNorthSouth[x][y][floor][i] = (byte)id;
        }
        for (int i = 0; i < Game.REGION_SIZE; i++) {
            int id = map.readUnsignedByte();
            //regionCollisionMask[x][y][floor][i] |= (id > 0 && JGameData.boundaryPassable[id - 1] && JGameData.boundaryAdjacent[id - 1]) ? Game.COLLISION_NORTHSOUTH : Game.COLLISION_NONE;
            regionWallsEastWest[x][y][floor][i] = (byte)id;
        }

        int data[] = new int[Game.REGION_SIZE];
        for (int i = 0; i < Game.REGION_SIZE; i++)
            regionWallsDiagonal[x][y][floor][i] = (short)map.readUnsignedByte();
        for (int i = 0; i < Game.REGION_SIZE; i++) {
            short val = (short)map.readUnsignedByte();
            if (val > 0)
                regionWallsDiagonal[x][y][floor][i] = (short)(val + 12000);

            //regionCollisionMask[x][y][floor][i] |= (id > 0 && id < 12000 && JGameData.boundaryPassable[id - 1] && JGameData.boundaryAdjacent[id - 1]) ? Game.COLLISION_TILE : Game.COLLISION_NONE;
            //regionCollisionMask[x][y][floor][i] |= (id >= 12000 && JGameData.boundaryPassable[id - 12001] && JGameData.boundaryAdjacent[id - 12001]) ? Game.COLLISION_TILE : Game.COLLISION_NONE;
        }

        // Unknown
        int prevValue = 0;
        for (int i = 0; i < Game.REGION_SIZE; i++) {
            int roof = map.readUnsignedByte();
            if (roof >= 128) {
                for (int i2 = 0; i2 < roof - 128; i2++)
                    i++;
                i--;
            } else {
                prevValue = roof;
            }
        }

        prevValue = 0;
        for (int i = 0; i < Game.REGION_SIZE; i++) {
            int tileDecoration = map.readUnsignedByte();
            if (tileDecoration >= 128) {
                for (int i2 = 0; i2 < tileDecoration - 128; i2++) {
                    if (prevValue > 0) {
                        regionCollisionMask[x][y][floor][i] |= (JGameData.tileAdjacent[prevValue - 1] != 0) ? Game.COLLISION_TILE : Game.COLLISION_NONE;
                        // Two floors?
                        //regionCollisionMask[x][y][floor][i] |= (JGameData.tileType[prevValue - 1] == 2) ? Game.COLLISION_TILE : Game.COLLISION_NONE;
                    }
                    regionDecoration[x][y][floor][i] = (byte)prevValue;
                    i++;
                }
                i--;
            } else {
                if (tileDecoration > 0) {
                    regionCollisionMask[x][y][floor][i] |= (JGameData.tileAdjacent[tileDecoration - 1] != 0) ? Game.COLLISION_TILE : Game.COLLISION_NONE;
                    // Two floors?
                    //regionCollisionMask[x][y][floor][i] |= (JGameData.tileType[tileDecoration - 1] == 2) ? Game.COLLISION_TILE : Game.COLLISION_NONE;
                }
                regionDecoration[x][y][floor][i] = (byte)tileDecoration;
                prevValue = tileDecoration;
            }
        }

        for (int i = 0; i < Game.REGION_SIZE; i++) {
            int tileDirection = map.readUnsignedByte();
            if (tileDirection >= 128) {
                for (int i2 = 0; (tileDirection - 128) > i2; i2++)
                    regionDirection[x][y][floor][i++] = 0;
                i--;
            } else {
                regionDirection[x][y][floor][i] = (byte)tileDirection;
            }
        }

        map.close();

        return true;
    }

    public static int getTileDirection(int x, int y)
    {
        int floor = y / Game.WORLD_Y_OFFSET;
        int floorOffset = floor * Game.WORLD_Y_OFFSET;
        int worldX = Game.WORLD_PLANE_X + x;
        int worldY = Game.WORLD_PLANE_Y - floorOffset + y;
        int chunkX = worldX / Game.REGION_WIDTH;
        int chunkY = worldY / Game.REGION_HEIGHT;
        int localX = worldX - (chunkX * Game.REGION_WIDTH);
        int localY = worldY - (chunkY * Game.REGION_HEIGHT);
        int index = (localX * Game.REGION_HEIGHT) + localY;
        int direction = JGameData.regionDirection[chunkX][chunkY][floor][index];
        return direction;
    }

    public static int getTileHeight(int x, int y)
    {
        int floor = y / Game.WORLD_Y_OFFSET;
        int floorOffset = floor * Game.WORLD_Y_OFFSET;
        int worldX = Game.WORLD_PLANE_X + x;
        int worldY = Game.WORLD_PLANE_Y - floorOffset + y;
        int chunkX = worldX / Game.REGION_WIDTH;
        int chunkY = worldY / Game.REGION_HEIGHT;
        int localX = worldX - (chunkX * Game.REGION_WIDTH);
        int localY = worldY - (chunkY * Game.REGION_HEIGHT);
        int index = (localX * Game.REGION_HEIGHT) + localY;
        int height = 3 * (0xFF & JGameData.regionHeight[chunkX][chunkY][floor][index]);
        return -height;
    }

    public static int getTileColor(int x, int y)
    {
        int floor = y / Game.WORLD_Y_OFFSET;
        int floorOffset = floor * Game.WORLD_Y_OFFSET;
        int worldX = Game.WORLD_PLANE_X + x;
        int worldY = Game.WORLD_PLANE_Y - floorOffset + y;
        int chunkX = worldX / Game.REGION_WIDTH;
        int chunkY = worldY / Game.REGION_HEIGHT;
        int localX = worldX - (chunkX * Game.REGION_WIDTH);
        int localY = worldY - (chunkY * Game.REGION_HEIGHT);
        int index = (localX * Game.REGION_HEIGHT) + localY;
        byte color = JGameData.regionColor[chunkX][chunkY][floor][index];
        return RSCConfig.colorConversion[color & 0xFF];
    }

    public static int getTileDecoration(int x, int y)
    {
        int floor = y / Game.WORLD_Y_OFFSET;
        int floorOffset = floor * Game.WORLD_Y_OFFSET;
        int worldX = Game.WORLD_PLANE_X + x;
        int worldY = Game.WORLD_PLANE_Y - floorOffset + y;
        int chunkX = worldX / Game.REGION_WIDTH;
        int chunkY = worldY / Game.REGION_HEIGHT;
        int localX = worldX - (chunkX * Game.REGION_WIDTH);
        int localY = worldY - (chunkY * Game.REGION_HEIGHT);
        int index = (localX * Game.REGION_HEIGHT) + localY;
        byte decoration = JGameData.regionDecoration[chunkX][chunkY][floor][index];
        return decoration & 0xFF;
    }

    public static byte getWallEastWest(int x, int y)
    {
        int floor = y / Game.WORLD_Y_OFFSET;
        int floorOffset = floor * Game.WORLD_Y_OFFSET;
        int worldX = Game.WORLD_PLANE_X + x;
        int worldY = Game.WORLD_PLANE_Y - floorOffset + y;
        int chunkX = worldX / Game.REGION_WIDTH;
        int chunkY = worldY / Game.REGION_HEIGHT;
        int localX = worldX - (chunkX * Game.REGION_WIDTH);
        int localY = worldY - (chunkY * Game.REGION_HEIGHT);
        int index = (localX * Game.REGION_HEIGHT) + localY;
        byte wall = JGameData.regionWallsEastWest[chunkX][chunkY][floor][index];
        return wall;
    }

    public static byte getWallNorthSouth(int x, int y)
    {
        int floor = y / Game.WORLD_Y_OFFSET;
        int floorOffset = floor * Game.WORLD_Y_OFFSET;
        int worldX = Game.WORLD_PLANE_X + x;
        int worldY = Game.WORLD_PLANE_Y - floorOffset + y;
        int chunkX = worldX / Game.REGION_WIDTH;
        int chunkY = worldY / Game.REGION_HEIGHT;
        int localX = worldX - (chunkX * Game.REGION_WIDTH);
        int localY = worldY - (chunkY * Game.REGION_HEIGHT);
        int index = (localX * Game.REGION_HEIGHT) + localY;
        byte wall = JGameData.regionWallsNorthSouth[chunkX][chunkY][floor][index];
        return wall;
    }

    public static int getWallDiagonal(int x, int y)
    {
        int floor = y / Game.WORLD_Y_OFFSET;
        int floorOffset = floor * Game.WORLD_Y_OFFSET;
        int worldX = Game.WORLD_PLANE_X + x;
        int worldY = Game.WORLD_PLANE_Y - floorOffset + y;
        int chunkX = worldX / Game.REGION_WIDTH;
        int chunkY = worldY / Game.REGION_HEIGHT;
        int localX = worldX - (chunkX * Game.REGION_WIDTH);
        int localY = worldY - (chunkY * Game.REGION_HEIGHT);
        int index = (localX * Game.REGION_HEIGHT) + localY;
        short wall = JGameData.regionWallsDiagonal[chunkX][chunkY][floor][index];
        return wall;
    }
}
